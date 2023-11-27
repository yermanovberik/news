package portal.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import portal.news.models.Subscription;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByUserEmail(String email);
}
