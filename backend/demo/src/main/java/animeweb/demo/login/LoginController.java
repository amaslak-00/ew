package animeweb.demo.login;


import animeweb.demo.registration.RegistrationRequest;
import animeweb.demo.user.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/login")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public User login(@RequestBody Login login ){
        return loginService.login(login);
    };


}
