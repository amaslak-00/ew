package animeweb.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @PostMapping
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userID}")
    public void deleteUser(@PathVariable("userID") Long userID){
        userService.deleteUserByID(userID);
    }
    @PutMapping(path = "{userID}")
    public void updateUser(@PathVariable("userID") Long userID,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String password){
        userService.updateUser(userID, name, password);
    }
}
