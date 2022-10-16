package animeweb.demo.user;

import animeweb.demo.studio.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/account")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }




}
