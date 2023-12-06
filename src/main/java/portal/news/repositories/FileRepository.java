package portal.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import portal.news.models.File;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findByPath(String path);
}
