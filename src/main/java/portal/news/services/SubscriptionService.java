package portal.news.services;

import portal.news.dto.subscription.SubscriptionRequestDto;
import portal.news.dto.subscription.SubscriptionResponseDto;
import portal.news.models.Subscription;

public interface SubscriptionService extends CrudService<Subscription, SubscriptionRequestDto, SubscriptionResponseDto> {
}
