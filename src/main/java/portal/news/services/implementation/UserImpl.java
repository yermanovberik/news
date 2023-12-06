package portal.news.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import portal.news.dto.avatar.AvatarResponseDto;
import portal.news.dto.file.FileRequestDto;
import portal.news.dto.file.FileResponseDto;
import portal.news.dto.user.UserRequestDto;
import portal.news.dto.user.UserResponseDto;
import portal.news.enums.Role;
import portal.news.exceptions.entity.EntityAlreadyExistsException;
import portal.news.exceptions.entity.EntityNotFoundException;
import portal.news.mappers.UserMapper;
import portal.news.models.User;
import portal.news.repositories.UserRepository;
import portal.news.services.FileService;
import portal.news.services.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    private final FileService fileService;




    @Override
    public UserResponseDto getById(long id) {
        User user = getEntityById(id);

        UserResponseDto responseDto = UserResponseDto
                .builder()
                .id(id)
                .subscription_paid(user.isSubscription_paid())
                .email(user.getEmail())
                .build();
        return responseDto;
    }

    @Override
    @Transactional
    public UserResponseDto create(UserRequestDto requestDto) {
        throwExceptionIfUserExists(requestDto.email());
        User user = User.builder()
                .role(Role.USER)
                .username(requestDto.username())
                .firstName(requestDto.firstName())
                .lastName(requestDto.lastName())
                .email(requestDto.email())
                .subscription_paid(false)
                .build();
        user.setPassword(
                passwordEncoder.encode(requestDto.password())
        );
        user = userRepository.save(user);
        UserResponseDto responseDto = UserResponseDto.
                builder()
                .email(user.getEmail())
                .id(user.getId())
                .subscription_paid(user.isSubscription_paid())
                .build();
        return responseDto;
    }

    @Override
    @Transactional
    public UserResponseDto update(long id, UserRequestDto requestDto) {
        User user = getEntityById(id);
        if (requestDto.email() != null) {
            user.setEmail(requestDto.email());
        }
        if (requestDto.password() != null) {
            user.setPassword(requestDto.password());
        }
        if (requestDto.firstName() != null) {
            user.setFirstName(requestDto.firstName());
        }
        if (requestDto.lastName() != null) {
            user.setLastName(requestDto.lastName());
        }
        if (requestDto.username() != null) {
            user.setUsername(requestDto.username());
        }
        UserResponseDto responseDto = userMapper.toDto(user);
        return responseDto;
    }

    @Override
    @Transactional
    public void delete(long id) {
        User user = getEntityById(id);
        userRepository.delete(user);
    }

    @Override
    public User getEntityById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " does not exist"));
    }

    @Override
    public void throwExceptionIfUserExists(String email) {
        userRepository.findByEmail(email)
                .ifPresent(foundUser -> {
                    throw new EntityAlreadyExistsException(
                            "User with the email " + foundUser.getEmail() + " already exists"
                    );
                });
    }

    public void updateSubscriptionStatus(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + email));

        user.setSubscription_paid(true);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public AvatarResponseDto createUserWithAvatar(String email, MultipartFile avatarFile) {
        log.info("Creating User with Avatar");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User this email " + email + " does not exists"));
            FileRequestDto fileRequestDto = FileRequestDto.builder()
                            .file(avatarFile)
                             .build();
            FileResponseDto fileResponseDto = fileService.create(fileRequestDto);
            System.out.println(fileResponseDto);
            user.setAvatarPath(fileResponseDto.path());

            user = userRepository.save(user);

            log.info("User created with ID: {}", user.getId());
            AvatarResponseDto avatarResponseDto = new AvatarResponseDto(fileResponseDto.path());
            return avatarResponseDto;
    }




}
