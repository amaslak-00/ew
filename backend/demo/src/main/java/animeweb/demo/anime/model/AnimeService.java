package animeweb.demo.anime.model;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAllAnime() {
        return animeRepository.findAll();
    }


    public List<String> getAllGenres(){
        return animeRepository.getdisctincGenre();
    }

    public List<Anime> getOnGoing() {
        return animeRepository.getDistinctStatus();
    }

    public Anime getAnimeById(Long animeId) {
        Optional<Anime> animeOptional= animeRepository.findById(animeId);
        if (animeOptional.isPresent()) {
            return animeOptional.get();
        }
        else {
            return null;
        }
    }

    public void addNewAnime(Anime anime) {
        Optional<Anime> animeOptional = animeRepository.findAnimeByTitle(anime.getTitle());
        if (animeOptional.isPresent()) {
            throw new IllegalStateException("Title is already in a DB");
        }
        animeRepository.save(anime);
    }


    @Transactional
    public void updateAnime(Long animeID, Integer numberOfSeasons) {
        Anime anime = animeRepository.findById(animeID)
                .orElseThrow(() -> new IllegalStateException(
                        "anime woth id" + animeID + "does not exist"
                ));
        anime.setNumberOfSeasons(numberOfSeasons);
    }

    public void deleteAnime(Long animeID) {
        boolean exists = animeRepository.existsById(animeID);
        if (!exists) {
            throw new IllegalStateException("anime woth id" + animeID + "does not exist");
        } else {
            animeRepository.deleteById(animeID);
        }
    }

}
