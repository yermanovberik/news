package portal.news.dto.user;

public record UserResponseDto(
        Long id,
        String email,

        boolean subscription_paid
) {
}
