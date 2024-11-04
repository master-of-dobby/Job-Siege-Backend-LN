package LN.job_siege_app.Contollers;

import LN.job_siege_app.Models.Jobs;
import LN.job_siege_app.Services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class JobsController {

    private final JobService jobService;

    @PostMapping("/add-jobs")
    public ResponseEntity<Jobs> addJob(@RequestBody Jobs job) {
        Jobs createdJob = jobService.addJob(job);
        System.out.println(createdJob);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @GetMapping("/get-jobs")
    public ResponseEntity<List<Jobs>> getJobs() {
        List<Jobs> jobsList = jobService.findJobs();
        return new ResponseEntity<>(jobsList, HttpStatus.OK);
    }
}