package portal.news.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import portal.news.validators.SubscriptionValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SubscriptionValidator.class)
public @interface ValidSubscription {
    String message() default "Subscription has expired";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
