package animeweb.demo.anime.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

    @Query("SELECT a FROM Anime a WHERE a.title =?1")
    Optional<Anime> findAnimeByTitle(String title);

    @Query("SELECT DISTINCT a.genre FROM Anime a")
    List<String> getdisctincGenre();

    @Query("SELECT a FROM Anime a WHERE a.isOngoing = true ")
    List<Anime> getDistinctStatus();


    //DISTINCT!!!!!
}
