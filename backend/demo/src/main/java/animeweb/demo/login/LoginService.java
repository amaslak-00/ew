package animeweb.demo.login;

import animeweb.demo.registration.EmailValidator;
import animeweb.demo.security.PasswordEncoder;
import animeweb.demo.user.User;
import animeweb.demo.user.UserRepository;
import animeweb.demo.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.spec.EncodedKeySpec;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private final UserRepository userRepository;
    private final EmailValidator emailValidator;
    public User login(Login login) {

        boolean isValidEmail = emailValidator.test(login.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }


        Optional<User> user = userRepository.findByEmail(login.getEmail());

        if(user.isPresent()){

            if(encoder.matches(login.getPassword(), user.get().getPassword())){
                return user.get();
            }
            else{
                throw new IllegalStateException("password does not match your email");
            }
        }

        throw new IllegalStateException("fail");
    }
}
