package LN.job_siege_app.Contollers;

import LN.job_siege_app.Models.Jobs;
import LN.job_siege_app.Repos.JobsRepo;
import LN.job_siege_app.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobsController {

    private final JobService jobService;

    @Autowired
    public JobsController(JobService jobService){
        this.jobService = jobService;
    }

    @PostMapping("/add-jobs")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Jobs> addJob(@RequestBody Jobs job){
        return new ResponseEntity<>(jobService.addJob(job), HttpStatus.CREATED);
    }

    @GetMapping("/get-jobs")
    public ResponseEntity<List<Jobs>> getJobs(){
        return new ResponseEntity<>(jobService.findJobs(), HttpStatus.OK);
    }
}
