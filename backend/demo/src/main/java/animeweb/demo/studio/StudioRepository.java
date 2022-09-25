package animeweb.demo.studio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudioRepository extends JpaRepository<Studio, Long> {

    @Query("SELECT s FROM Studio s WHERE s.name =?1")
    Optional<Studio> findStudioByName(String name);
}
