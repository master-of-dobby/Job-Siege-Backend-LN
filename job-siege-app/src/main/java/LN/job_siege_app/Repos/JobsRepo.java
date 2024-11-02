package LN.job_siege_app.Repos;

import LN.job_siege_app.Models.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepo extends JpaRepository<Jobs, String> {
}
