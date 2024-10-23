package LN.job_siege_app.Contollers;

import LN.job_siege_app.Models.Users;
import LN.job_siege_app.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers(){
        List<Users> users = userService.getUsers();

        logger.info("Retrieved users : {}", users);

        if(users == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users users){
        Users isSucceed = userService.register(users);

        if(isSucceed == null) {
            logger.warn("User Registration failed for : {}", users);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(isSucceed);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users){
        return userService.verifyUser(users);
    }

    @GetMapping("/home")
    public String home(){
        return "Welcome Home";
    }
}
