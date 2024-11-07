package LN.job_siege_app.Services;

import LN.job_siege_app.Models.Jobs;
import LN.job_siege_app.Models.Users;
import LN.job_siege_app.Repos.JobsRepo;
import LN.job_siege_app.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {

    @Autowired
    private JobsRepo jobRepository;

    @Autowired
    private UserRepo userRepo;

    public Jobs addJob(UUID userId,Jobs job){
        Users user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        job.setUser(user);
        return jobRepository.save(job);
    }

    public List<Jobs> findJobs(UUID userId){
        return jobRepository.findByUserId(userId);
    }

//    public void deleteJob(UUID jobId, UUID userId){
//        jobRepository.deleteByIdAndUserId(jobId, userId);
//    }

}
