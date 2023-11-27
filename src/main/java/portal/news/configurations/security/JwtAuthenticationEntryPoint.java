package portal.news.configurations.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import portal.news.dto.apiException.ApiExceptionResponseDto;
import portal.news.exceptions.ApiExceptionResponseFactory;
import portal.news.exceptions.auth.ApiAuthenticationException;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
    private final ApiExceptionResponseFactory apiExceptionResponseFactory;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        if (authException instanceof ApiAuthenticationException apiAuthenticationException) {
            httpStatus = apiAuthenticationException.getHttpStatus();
        }

        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiExceptionResponseDto errorResponse = apiExceptionResponseFactory.createApiExceptionResponseDto(
                httpStatus,
                authException.getMessage()
        );

        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        try (PrintWriter writer = response.getWriter()) {
            writer.write(jsonResponse);
            writer.flush();
        }
    }

}
