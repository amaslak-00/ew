package animeweb.demo.useranime;

import animeweb.demo.anime.model.Anime;
import animeweb.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserAnimeRepository extends JpaRepository<UserAnime, Long> {

    @Query("SELECT u FROM UserAnime u WHERE u.user.id =?1")
    Optional <Collection<UserAnime>> findUserById(Long Id);

    @Query("SELECT u FROM UserAnime u WHERE u.user.id=?1 and u.anime.id=?2")
    Optional <UserAnime> findByIds(Long userId, Long animeId);

}
