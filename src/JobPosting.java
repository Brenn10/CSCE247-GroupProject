import java.util.ArrayList;
import java.util.UUID;

public class JobPosting {
    private UUID id;
    private Employer employer;
    private String description;
    private ArrayList<String> requirements;
    private double hourlyWage;
    private JobPostingStatus status;
    private ArrayList<Student> applicants;

    public JobPosting(UUID id, Employer employer, String description, ArrayList<String> requirements, 
    double hourlyWage, JobPostingStatus status, ArrayList<Student> applicants) {
        this.id = id;
        this.employer = employer;
        this.description = description;
        this.requirements = requirements;
        this.hourlyWage = hourlyWage;
        this.status = status;
        this.applicants = applicants;
    }

    public void addRequirement(String requirement) {
        this.requirements.add(requirement);
    }

    public void removeRequriement(String toRemove) {
        this.requirements.remove(toRemove);
    }

    public void addApplicant(Student applicant) {
        this.applicants.add(applicant);
    }

    public Employer getEmployer() {
        return this.employer;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<String> getRequirements() {
        return this.requirements;
    }

    public double getWage() {
        return this.hourlyWage;
    }

    public UUID getID() {
        return this.id;
    }

    public JobPostingStatus getStatus() {
        return this.status;
    }

    public ArrayList<Student> getApplicants() {
        return this.applicants;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public void setDescription(String description) {
       this.description = description;
    }

    public void setRequirements(ArrayList<String> requirements) {
        this.requirements = requirements;
    }

    public void setWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public void setStatus(JobPostingStatus status) {
        // would require something with the enum
        this.status = status;
    }

    public void setApplicants(ArrayList<Student> applicants) {
       this.applicants = applicants;
    }

    public static class Builder {
        private UUID id;
        private Employer employer;
        private String description;
        private ArrayList<String> requirements;
        private double hourlyWage;
        private JobPostingStatus status;
        private ArrayList<Student> applicants;

        public Builder() {
            id = UUID.randomUUID();
            this.requirements = new ArrayList<String>();
            this.applicants = new ArrayList<Student>();
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder employer(Employer employer) {
            this.employer = employer;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder requirements(ArrayList<String> requirements) {
            this.requirements = requirements;
            return this;
        }

        public Builder hourlyWage(double hourlyWage) {
            this.hourlyWage = hourlyWage;
            return this;
        }

        public Builder status(JobPostingStatus status) {
            this.status = status;
            return this;
        }

        public Builder applicants(ArrayList<Student> applicants) {
            this.applicants = applicants;
            return this;
        }

        public JobPosting build() {
            return new JobPosting(id, employer, description, requirements, hourlyWage, status, applicants);
        }
    }
}
