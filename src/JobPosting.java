import java.util.ArrayList;

public class JobPosting {
    private Employer employer;
    private String description;
    private ArrayList<String> requirements;
    private double hourlyWage;
    private String status;
    private ArrayList<JobApplication> applicants;

    public JobPosting(Employer employer, String description, ArrayList<String> requirements, 
    double hourlyWage, JobPostingStatus status, ArrayList<JobApplication> applicants) {
        this.employer = employer;
        this.description = description;
        this.requirements = requirements;
        this.hourlyWage = hourlyWage;
        this.status = status;
        this.applicants = applicants;
    }

}
