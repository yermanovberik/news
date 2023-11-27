package portal.news.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import portal.news.annotations.ValidSubscription;
import portal.news.models.Subscription;

import java.time.LocalDateTime;

public class SubscriptionValidator implements ConstraintValidator<ValidSubscription, Subscription> {
    @Override
    public void initialize(ValidSubscription constraintAnnotation) {
    }
    @Override
    public boolean isValid(Subscription subscription, ConstraintValidatorContext context) {
        if (subscription == null || subscription.getEnd_date() == null) {
            return true;
        }

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime endDate = subscription.getEnd_date();

        return currentDate.isBefore(endDate) || currentDate.isEqual(endDate);
    }
}
