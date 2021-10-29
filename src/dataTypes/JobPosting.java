package dataTypes;
import java.util.ArrayList;
import java.util.UUID;

import enums.JobPostingStatus;

public class JobPosting {
    private UUID id;
    private Employer employer;
    public String jobTitle;
    private String description;
    private ArrayList<String> requirements;
    private double hourlyWage;
    private JobPostingStatus status;
    private ArrayList<Student> applicants;
    private boolean removed;

    public JobPosting(UUID id, Employer employer, String jobTitle, String description, ArrayList<String> requirements, 
    double hourlyWage, JobPostingStatus status, ArrayList<Student> applicants, boolean removed) {
        this.id = id;
        this.employer = employer;
        this.jobTitle = jobTitle;
        this.description = description;
        this.requirements = requirements;
        this.hourlyWage = hourlyWage;
        this.status = status;
        this.applicants = applicants;
        this.removed = removed;
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

    public String getJobTitle() {
        return this.jobTitle;
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

    public UUID getId() {
        return this.id;
    }

    public JobPostingStatus getStatus() {
        return this.status;
    }

    public ArrayList<Student> getApplicants() {
        return this.applicants;
    }
    public void setTitle(String title) {
        this.jobTitle = title;
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

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.jobTitle + " at " + this.employer.getCompany() + "\n");
        sb.append("Description: " + this.description + "\n");
        sb.append("Requirements: \n");
        for (String requirement : this.requirements) {
            sb.append("\t" + requirement + "\n");
        }
        sb.append("Hourly Wage: " + this.hourlyWage + "\n");
        sb.append("Status: " + this.status);

        return sb.toString();
    }

    public static class Builder {
        private UUID id;
        private Employer employer;
        private String jobTitle;
        private String description;
        private ArrayList<String> requirements;
        private double hourlyWage;
        private JobPostingStatus status;
        private ArrayList<Student> applicants;
        private boolean removed;

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

        public Builder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
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

        public Builder removed(boolean removed) {
            this.removed = removed;
            return this;
        }

        public JobPosting build() {
            return new JobPosting(id, employer, jobTitle, description, requirements, hourlyWage, status, applicants, removed);
        }
    }
}
