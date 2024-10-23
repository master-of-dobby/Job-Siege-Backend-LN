package LN.job_siege_app.Services;

import LN.job_siege_app.Models.Users;
import LN.job_siege_app.Models.UserPrincipal;
import LN.job_siege_app.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> users = userRepo.findByEmail(email);

        if(!users.isPresent()){
            throw new UsernameNotFoundException("USER NOT FOUND");
        }

        Users foundUser = users.get();
        System.out.println("User found: " + foundUser.getEmail());  // Log user information
        System.out.println("Encoded password: " + foundUser.getPassword());

        return new UserPrincipal(foundUser);
    }
}
