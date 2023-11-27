package portal.news.services;

import org.springframework.stereotype.Service;
import portal.news.dto.auth.AuthenticationRequestDto;
import portal.news.dto.auth.AuthenticationResponseDto;
import portal.news.dto.auth.RegisterRequestDto;

@Service
public interface AuthenticationService {

    AuthenticationResponseDto register(RegisterRequestDto request);
    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);
    AuthenticationResponseDto refreshToken(String authHeader);

}
