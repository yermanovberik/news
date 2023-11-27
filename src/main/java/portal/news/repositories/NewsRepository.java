package portal.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portal.news.models.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
