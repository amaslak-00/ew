package animeweb.demo.useranime;

import animeweb.demo.anime.model.Anime;
import animeweb.demo.anime.model.AnimeRepository;
import animeweb.demo.user.User;
import animeweb.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserAnimeService {

    @Autowired
    private final UserAnimeRepository userAnimeRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AnimeRepository animeRepository;

    public UserAnimeService(UserAnimeRepository userAnimeRepository, UserRepository userRepository, AnimeRepository animeRepository) {
        this.userAnimeRepository = userAnimeRepository;
        this.userRepository = userRepository;
        this.animeRepository = animeRepository;
    }

    public void addUserAnime(UserAnimeDTO userAnimeDTO) {
        Optional<User> userOptional = userRepository.findById(userAnimeDTO.getUserId());
        if (!userOptional.isPresent()) {
            throw new IllegalStateException("Title is already in a DB");
        }
        Optional<Anime> animeOptional = animeRepository.findById(userAnimeDTO.getAnimeId());
        if (!animeOptional.isPresent()) {
            throw new IllegalStateException("Title is already in a DB");
        }

        UserAnime userAnime = UserAnime.builder()
                .anime(animeOptional.get())
                .user(userOptional.get())
                .isWatched(userAnimeDTO.getIsWatched())
                .build();

        userAnimeRepository.save(userAnime);
    }

    public void addComment(Long userId, Long animeId, String comment) {
        UserAnime useranime = getUserAnimeByIds(userId, animeId);
        useranime.setComment(comment);
        userAnimeRepository.save(useranime);
    }

    public UserAnime getUserAnime(Long userAnimeId){
        Optional<UserAnime> userAnimeOptional = userAnimeRepository.findById(userAnimeId);
        if(userAnimeOptional.isPresent()){
            return userAnimeOptional.get();
        }
        else {
            return null;
        }
    }


    public Collection<UserAnime> getAllAnimes(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException(
                    "user with id" + userId + "does not exist"
            );
        } else {
            return userAnimeRepository.findUserById(userId).orElseThrow(() -> new IllegalStateException("not find"));
        }

    }

    public UserAnime getUserAnimeByIds(Long userId, Long animeId) {
        Optional<UserAnime> userAnimeOptional = userAnimeRepository.findByIds(userId, animeId);
        if(userAnimeOptional.isPresent()){
            return userAnimeOptional.get();
        }
        else {
            return null;
        }
    }
}
