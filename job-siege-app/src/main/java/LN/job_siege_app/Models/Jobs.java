package LN.job_siege_app.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID jobId;

    private String referenceId;
    private String companyName;
    private String jobDescription;
    private String jobTitle;
    private float salary;
    private String jobLocation;
    private Date applicatioinDate;
    private String applicationStatus;
    private String contactPerson;
    private boolean shortlisted;
    private String applicationLink;
    private Date followingDate;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public void setUser(Users user) {
        this.user = user;
    }


}
