package animeweb.demo.user;

import animeweb.demo.useranime.UserAnime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByName(user.getName());
        if(userOptional.isPresent()){
            throw new IllegalStateException("name taken");
        }
        userRepository.save(user);
    }

    public void deleteUserByID(Long userID) {
        boolean exists = userRepository.existsById(userID);
        if (!exists) {
            throw new IllegalStateException(
                    "user with id" + userID + "does not exist"
            );
        } else {
            userRepository.deleteById(userID);
        }
    }

    @Transactional
    public void updateUser(Long userID, String name, String password) {
        User user = userRepository.findById(userID)
                .orElseThrow(()-> new IllegalStateException(
                        "user with id" + userID + "does not exist"
                ));
        if (password != null &&
                password.length() > 0 &&
                !password.equals(user.getPassword())) {
            user.setPassword(password);
        }

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(user.getName(), name)) {
            Optional<User> studentOptional = userRepository
                    .findUserByName(name);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("username taken");
            }
            user.setName(name);

        }
    }

    public void builderUserAnime(){
        UserAnime userAnime = UserAnime.builder().anime(null).build();
        //find anime
        //find user
        //build obj if anime exists
        //save useranime
    }
}
