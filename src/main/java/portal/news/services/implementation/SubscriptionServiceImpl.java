package portal.news.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.news.dto.subscription.SubscriptionRequestDto;
import portal.news.dto.subscription.SubscriptionResponseDto;
import portal.news.exceptions.entity.EntityAlreadyExistsException;
import portal.news.exceptions.entity.EntityNotFoundException;
import portal.news.mappers.SubscriptionMapper;
import portal.news.models.Subscription;
import portal.news.models.User;
import portal.news.repositories.SubscriptionRepository;
import portal.news.repositories.UserRepository;
import portal.news.services.SubscriptionService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionMapper mapper;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    @Override
    public SubscriptionResponseDto getById(long id) {
        Subscription subscription = getEntityById(id);

        SubscriptionResponseDto responseDto = mapper.toDto(subscription);
        return responseDto;
    }

    @Override
    @Transactional
    public SubscriptionResponseDto create(SubscriptionRequestDto requestDto) {
        String userEmail = requestDto.email();

        Optional<Subscription> existingSubscription = subscriptionRepository.findByUserEmail(userEmail);

        if(existingSubscription.isPresent()){
            Subscription existing = existingSubscription.get();
            LocalDateTime now = LocalDateTime.now();
            Duration remainingTime = Duration.between(now, existing.getEnd_date());

            throw new EntityAlreadyExistsException("User already has a subscription that expires in "
                    + remainingTime.toDays() + " days.");
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = now;
        LocalDateTime endDate;
        switch (requestDto.typeOfSubscription()) {
            case 1:
                endDate = now.plusWeeks(1);
                break;
            case 2:
                endDate = now.plusMonths(1);
                break;
            case 3:
                endDate = now.plusYears(1);
                break;
            default:
                throw new IllegalArgumentException("Invalid typeOfSubscription: " + requestDto.typeOfSubscription());
        }
        Subscription subscription =Subscription.builder()
                .userEmail(requestDto.email())
                .start_date(startDate)
                .end_date(endDate)
                .build();
        subscription = subscriptionRepository.save(subscription);

        User user = userRepository.findByEmail(requestDto.email())
                .orElseThrow(() -> new EntityNotFoundException("User with this email " + requestDto.email() + " does not exists"));

        user.setSubscription_paid(true);

        SubscriptionResponseDto responseDto = SubscriptionResponseDto.builder()
                .id(subscription.getId())
                .start_date(subscription.getStart_date())
                .end_date(subscription.getEnd_date())
                .build();
        return responseDto;
    }

    @Override
    @Transactional
    public SubscriptionResponseDto update(long id, SubscriptionRequestDto requestDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(long id) {
        Subscription subscription = getEntityById(id);
        subscriptionRepository.delete(subscription);
    }

    @Override
    public Subscription getEntityById(long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID " + id + " does not exist"));
    }

    private void throwExceptionIfSubscriptionExists(long id) {
        subscriptionRepository.findById(id)
                .ifPresent(foundSubscription -> {
                    throw new EntityAlreadyExistsException(
                            "Subscription with the name " + foundSubscription + " already exists"
                    );
                });
    }
}
