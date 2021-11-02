package dataTypes;

import java.util.ArrayList;
import java.util.UUID;

import enums.JobPostingStatus;

/**
 * JobPosting data type
 * 
 * @author Stella Garcia
 */
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

    /**
     * Constructor
     * 
     * @param id           the unique ID for the posting
     * @param employer     the employer associated with the posting
     * @param jobTitle     the title of the posting
     * @param description  the deescription of the job
     * @param requirements the requirement for the job
     * @param hourlyWage   the hourly wage of the job
     * @param status       the status of the job
     * @param applicants   ArrayList of applicants
     * @param removed      boolean if the posting has been removed or not
     */
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

    /**
     * Adds a requirement to the ArrayList
     * 
     * @param requirement the requirement to add
     */
    public void addRequirement(String requirement) {
        this.requirements.add(requirement);
    }

    /**
     * remove a requirement from the ArrayList
     * 
     * @param toRemove the requirement to remove
     */
    public void removeRequriement(String toRemove) {
        this.requirements.remove(toRemove);
    }

    /**
     * add an applicant to the ArrayList
     * 
     * @param applicant the student to add
     */
    public void addApplicant(Student applicant) {
        this.applicants.add(applicant);
    }

    /**
     * Get method for emplpyer
     * 
     * @return the employer associated with this job posting
     */
    public Employer getEmployer() {
        return this.employer;
    }

    /**
     * Get method for title of job
     * 
     * @return the title of the job
     */
    public String getJobTitle() {
        return this.jobTitle;
    }

    /**
     * Get method for description of job
     * 
     * @return the description of job
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get method for the requirements of job
     * 
     * @return the ArrayList requirements of the job
     */
    public ArrayList<String> getRequirements() {
        return this.requirements;
    }

    /**
     * Get method for wage of job
     * 
     * @return the wage of the job
     */
    public double getWage() {
        return this.hourlyWage;
    }

    /**
     * Get method for ID of job
     * 
     * @return the unique ID for the job
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Get method for status of the job
     * 
     * @return the status of the job
     */
    public JobPostingStatus getStatus() {
        return this.status;
    }

    /**
     * Get method for applicants of the job
     * 
     * @return the ArrayList of applicants
     */
    public ArrayList<Student> getApplicants() {
        return this.applicants;
    }

    /**
     * Set method for title
     * 
     * @param title the title we want to set it to
     */
    public void setTitle(String title) {
        this.jobTitle = title;
    }

    /**
     * Set method of the employer
     * 
     * @param employer the employer we want to set it to
     */
    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    /**
     * Set method for description
     * 
     * @param description the description we want to set it to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set method for requirements of the job
     * 
     * @param requirements the ArrayList we want to set it to
     */
    public void setRequirements(ArrayList<String> requirements) {
        this.requirements = requirements;
    }

    /**
     * Set method for hourly wage of the job
     * 
     * @param hourlyWage the wage we want to set it to
     */
    public void setWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    /**
     * Set method for status of the job
     * 
     * @param status the status we want to set it to
     */
    public void setStatus(JobPostingStatus status) {
        this.status = status;
    }

    /**
     * Set method for applicant of job
     * 
     * @param applicants the ArrayList we want to set it to
     */
    public void setApplicants(ArrayList<Student> applicants) {
        this.applicants = applicants;
    }

    /**
     * Get method for removed status of job
     * 
     * @return if it is removed or not
     */
    public boolean isRemoved() {
        return removed;
    }

    /**
     * Set method for removed
     * 
     * @param removed what we want to set removed to
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    /**
     * toString method
     * 
     * @return String what we want to print out
     */
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

    /**
     * Builder class allows for building a new JobPosting
     * 
     * @author Brennan Cain
     */
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

        /**
         * Constructor sets all of the default values
         */
        public Builder() {
            id = UUID.randomUUID();
            this.requirements = new ArrayList<String>();
            this.applicants = new ArrayList<Student>();
        }

        /**
         * Get method for Builder with a ID
         * 
         * @param id the ID we want to set to
         * @return a Builder with that ID
         */
        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        /**
         * Get method for Builder with a Employer
         * 
         * @param employer the employer we want to set to
         * @return a Builder with that employer
         */
        public Builder employer(Employer employer) {
            this.employer = employer;
            return this;
        }

        /**
         * Get method for Builder with a title
         * 
         * @param jobTitle the title we want to set to
         * @return a Builder with that title
         */
        public Builder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        /**
         * Get method for Builder with a description
         * 
         * @param description the description we want to set to
         * @return a Builder with that description
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Get method for Builder with requirements
         * 
         * @param requirements the requirements we want to set to
         * @return a Builder with that requirements
         */
        public Builder requirements(ArrayList<String> requirements) {
            this.requirements = requirements;
            return this;
        }

        /**
         * Get method for Builder with a hourlWage
         * 
         * @param hourlyWage the hourlyWage we want to set to
         * @return a Builder with that ID
         */
        public Builder hourlyWage(double hourlyWage) {
            this.hourlyWage = hourlyWage;
            return this;
        }

        /**
         * Get method for Builder with a status
         * 
         * @param status the status we want to set to
         * @return a Builder with that status
         */
        public Builder status(JobPostingStatus status) {
            this.status = status;
            return this;
        }

        /**
         * Get method for Builder with applicants
         * 
         * @param applicants the applicants we want to set to
         * @return a Builder with that applicants
         */
        public Builder applicants(ArrayList<Student> applicants) {
            this.applicants = applicants;
            return this;
        }

        /**
         * Get method for Builder with removed status
         * 
         * @param removed the boolean we want to set to
         * @return a Builder with that removed
         */
        public Builder removed(boolean removed) {
            this.removed = removed;
            return this;
        }

        /**
         * Build method creates a new JobPosting
         * 
         * @return the newly built JobPosting
         */
        public JobPosting build() {
            return new JobPosting(id, employer, jobTitle, description, requirements, hourlyWage, status, applicants,
                    removed);
        }
    }
}
