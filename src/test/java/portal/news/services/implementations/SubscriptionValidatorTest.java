package portal.news.services.implementations;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import portal.news.models.Subscription;
import portal.news.validators.SubscriptionValidator;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubscriptionValidatorTest {

    @Test
    public void testValidSubscription() {
        Subscription validSubscription = new Subscription();
        validSubscription.setEnd_date(LocalDateTime.now().plusDays(1));

        SubscriptionValidator validator = new SubscriptionValidator();

        validator.initialize(null);

        ConstraintValidatorContext context = null;

        boolean isValid = validator.isValid(validSubscription, context);

        assertTrue(isValid);
    }

    @Test
    public void testExpiredSubscription() {
        Subscription expiredSubscription = new Subscription();
        expiredSubscription.setEnd_date(LocalDateTime.now().minusDays(1));

        SubscriptionValidator validator = new SubscriptionValidator();

        validator.initialize(null);

        ConstraintValidatorContext context = null;

        boolean isValid = validator.isValid(expiredSubscription, context);

        assertFalse(isValid);
    }
}