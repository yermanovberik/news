package portal.news.dto.user;

import lombok.Builder;

@Builder
public record UserResponseDto(
        Long id,
        String email,

        boolean subscription_paid
) {
}
