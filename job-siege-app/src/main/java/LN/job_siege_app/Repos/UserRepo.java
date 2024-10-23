package LN.job_siege_app.Repos;

import LN.job_siege_app.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<Users, UUID> {

    Optional<Users> findByEmail(String email);
}
