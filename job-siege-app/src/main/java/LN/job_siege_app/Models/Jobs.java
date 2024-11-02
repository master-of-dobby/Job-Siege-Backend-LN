package LN.job_siege_app.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Jobs {
    @Id
    private String jobId;

    private String companyName;
    private String jobTitle;
    private Long salary;
    private String jobLocation;
    private Date applicatioinDate;
    private String applicationStatus;
    private String contactPerson;
    private String notes;
}
