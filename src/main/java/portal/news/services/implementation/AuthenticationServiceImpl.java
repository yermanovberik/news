package portal.news.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.news.dto.auth.AuthenticationRequestDto;
import portal.news.dto.auth.AuthenticationResponseDto;
import portal.news.dto.auth.RegisterRequestDto;
import portal.news.enums.Role;
import portal.news.exceptions.auth.JwtSubjectMissingException;
import portal.news.exceptions.auth.JwtTokenExpiredException;
import portal.news.exceptions.entity.EntityNotFoundException;
import portal.news.jwt.JwtFactory;
import portal.news.jwt.JwtParser;
import portal.news.jwt.JwtValidator;
import portal.news.models.User;
import portal.news.repositories.UserRepository;
import portal.news.services.AuthenticationService;
import portal.news.services.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtFactory jwtFactory;
    private final JwtValidator jwtValidator;
    private final JwtParser jwtParser;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    @Transactional
    public AuthenticationResponseDto register(RegisterRequestDto request) {
        userService.throwExceptionIfUserExists(request.email());

        User user = User.builder()
                .email(request.email())
                .firstName(request.firsName())
                .lastName(request.lastName())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String accessToken = jwtFactory.generateAccessToken(user);
        String refreshToken = jwtFactory.generateRefreshToken(user);

        return AuthenticationResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        log.info("Received authentication request for email: {}", request.email());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );


        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with email " + request.email() + " not found"
                ));

        String accessToken = jwtFactory.generateAccessToken(user);
        String refreshToken = jwtFactory.generateRefreshToken(user);

        log.info("User authenticated successfully with email: {}", user.getEmail());

        return AuthenticationResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponseDto refreshToken(String authHeader) {
        log.info("Received refresh token request");

        String refreshToken = authHeader.substring(7);

        User user = jwtParser.extractEmail(refreshToken)
                .map(userRepository::findByEmail)
                .orElseThrow(() -> new JwtSubjectMissingException("JWT subject cannot be null"))
                .orElseThrow(() -> new EntityNotFoundException("User with this email was not found"));

        jwtValidator.ifTokenExpiredThrow(refreshToken, () -> new JwtTokenExpiredException("Refresh token has expired"));

        String accessToken = jwtFactory.generateAccessToken(user);

        log.info("User refreshed token successfully with email: {}", user.getEmail());

        return AuthenticationResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
