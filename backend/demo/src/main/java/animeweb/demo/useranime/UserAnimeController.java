package animeweb.demo.useranime;

import animeweb.demo.anime.model.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "api/useranime")
@CrossOrigin(origins = "http://localhost:4200")
public class UserAnimeController {

    @Autowired
    private final UserAnimeService userAnimeService;

    public UserAnimeController(UserAnimeService userAnimeService) {
        this.userAnimeService = userAnimeService;
    }

    @PostMapping
    public void addUserAnime(@RequestBody UserAnimeDTO userAnimeDTO){userAnimeService.addUserAnime(userAnimeDTO);}

    @PostMapping(path = "{userId}/{animeId}")
    public void addComment(@RequestBody String comment, @PathVariable("userId") long userId,
                           @PathVariable("animeId") long animeId){userAnimeService.addComment(userId, animeId,comment);}

    @GetMapping(path = "{userId}")
    public Collection<UserAnime> getAllAnimes(@PathVariable("userId") Long userId){
        return userAnimeService.getAllAnimes(userId);
    }

    @GetMapping(path = "user/{userAnimeId}")
    public UserAnime getUserAnime(@PathVariable("userAnimeId") long userAnimeId){
        return userAnimeService.getUserAnime(userAnimeId);
    }
    @GetMapping(path = "{userId}/{animeId}")
    public UserAnime getUserAnimeByIds(@PathVariable("userId") long userId,
                                       @PathVariable("animeId") long animeId){
        return userAnimeService.getUserAnimeByIds(userId, animeId);
    }


}
