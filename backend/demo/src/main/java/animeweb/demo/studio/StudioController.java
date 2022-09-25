package animeweb.demo.studio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/studio")
@CrossOrigin(origins = "http://localhost:4200")
public class StudioController {

    @Autowired
    private final StudioService studioService;

    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

    @GetMapping
    public List<Studio> getStudios(){
        return studioService.getStudios();
    }

    @PostMapping
    public void registerNewStudio(@RequestBody Studio studio){
        studioService.addNewStudio(studio);
    }

    @DeleteMapping(path = "{studioID}")
    public void deleteStudio(@PathVariable("studioID") Long studioID){
        studioService.deleteStudio(studioID);
    }

}
