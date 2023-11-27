package portal.news.dto.subscription;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record SubscriptionResponseDto(
         Long id,
         LocalDateTime start_date,
         LocalDateTime end_date
) {
}
