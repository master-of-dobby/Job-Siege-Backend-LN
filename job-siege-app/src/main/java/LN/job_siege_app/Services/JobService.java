package LN.job_siege_app.Services;

import LN.job_siege_app.Models.Jobs;
import LN.job_siege_app.Repos.JobsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobsRepo jobRepository;

    @Autowired
    public JobService(JobsRepo jobRepository){
        this.jobRepository = jobRepository;
    }


    public Jobs addJob(Jobs job){
        return jobRepository.save(job);
    }

    public List<Jobs> findJobs(){
        return jobRepository.findAll();
    }

}
