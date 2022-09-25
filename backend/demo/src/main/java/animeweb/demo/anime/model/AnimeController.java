package animeweb.demo.anime.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/anime")
public class AnimeController {

    @Autowired
    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public List<Anime> getAllAnime(){
        return animeService.getAllAnime();
    }

    @GetMapping(path = "genre")
    public List<String> getAllGenres(){return animeService.getAllGenres();}

    @GetMapping(path = "ongoing")
    public List<Anime> getOnGoing(){ return animeService.getOnGoing();}

    @GetMapping(path = "{animeId}")
    public Anime getAnimeById(@PathVariable("animeId") long animeId){return animeService.getAnimeById(animeId);}

    @PostMapping
    public void registerNewAnime(@RequestBody Anime anime){
        animeService.addNewAnime(anime);
    }
    @DeleteMapping(path = "{animeId}")
    public void deleteAnime(@PathVariable("animeId") Long animeId){
        animeService.deleteAnime(animeId);
    }
    @PutMapping(path="{animeID}")
    public void updateAnime(@PathVariable("animeID") Long animeID,
                            @RequestParam(required = true) Integer numberOfSeasons){
        animeService.updateAnime(animeID,numberOfSeasons);
    }
}
