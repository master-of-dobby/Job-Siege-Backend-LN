package LN.job_siege_app.Services;

import LN.job_siege_app.Models.Users;
import LN.job_siege_app.Repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JWTService jwtService;

    public Users register(Users users){
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return userRepo.save(users);
    }

    public List<Users> getUsers() {
        List<Users> users = userRepo.findAll();

        if (users.isEmpty()) {
            System.out.println("No Users found!");
        } else {
            System.out.println("Retrieved " + users.size() + " users.");
            users.forEach(user -> System.out.println(user.getEmail())); // Print user emails for confirmation
        }

        return users;
    }

    public String verifyUser(Users users) {

        Authentication authentication =
                authenticationManager.authenticate(
                (new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword())));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(users.getEmail());
        }

        return "FAILED";
    }

}
