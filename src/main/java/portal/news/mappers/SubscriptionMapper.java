package portal.news.mappers;

import org.mapstruct.Mapper;
import portal.news.dto.subscription.SubscriptionRequestDto;
import portal.news.dto.subscription.SubscriptionResponseDto;
import portal.news.models.Subscription;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper extends Mappable<Subscription, SubscriptionRequestDto, SubscriptionResponseDto> {
    @Override
    SubscriptionResponseDto toDto(Subscription entity);

    @Override
    List<SubscriptionResponseDto> toDto(List<Subscription> entities);

    @Override
    Subscription toEntity(SubscriptionRequestDto request);

    @Override
    List<Subscription> toEntity(List<SubscriptionRequestDto> requests);
}
