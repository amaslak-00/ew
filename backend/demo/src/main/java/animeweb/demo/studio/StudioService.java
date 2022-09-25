package animeweb.demo.studio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudioService {

    @Autowired
    private final StudioRepository studioRepository;

    public StudioService(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    public List<Studio> getStudios() {
       return studioRepository.findAll();
    }

    public void addNewStudio(Studio studio) {
        Optional<Studio> studioOptional = studioRepository.findStudioByName(studio.getName());
        if(studioOptional.isPresent()){
            throw new IllegalStateException("studio already exists");
        }
        studioRepository.save(studio);
    }

    public void deleteStudio(Long studioID) {
        boolean exists = studioRepository.existsById(studioID);
        if (!exists) {
            throw new IllegalStateException(
                    "studio with id" + studioID + "does not exist"
            );
        } else {
            studioRepository.deleteById(studioID);
        }
    }
}
