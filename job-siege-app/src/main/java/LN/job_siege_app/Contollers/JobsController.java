package LN.job_siege_app.Contollers;

import LN.job_siege_app.Models.Jobs;
import LN.job_siege_app.Models.Users;
import LN.job_siege_app.Repos.UserRepo;
import LN.job_siege_app.Services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class JobsController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserRepo userRepository;

    @PostMapping("/add-jobs")
    public ResponseEntity<Jobs> addJob(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Jobs job) {
        Users user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        return new ResponseEntity<>(jobService.addJob(user.getId(), job), HttpStatus.CREATED);
    }

    @GetMapping("/get-jobs")
    public ResponseEntity<List<Jobs>> getJobs(@AuthenticationPrincipal UserDetails userDetails) {
        Users user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        return new ResponseEntity<>(jobService.findJobs(user.getId()), HttpStatus.OK);
    }
}