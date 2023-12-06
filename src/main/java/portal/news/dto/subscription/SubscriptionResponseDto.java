package portal.news.dto.subscription;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record SubscriptionResponseDto(

        String redirect_url,
         LocalDateTime start_date,
         LocalDateTime end_date
) {
}
