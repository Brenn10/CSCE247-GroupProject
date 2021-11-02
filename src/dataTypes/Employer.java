package dataTypes;

import java.util.ArrayList;
import java.util.UUID;

import database.JobPostingDatabase;
import database.ReviewDatabase;
import enums.JobPostingStatus;

/**
 * Employer User Data type of User
 * @author Ian McDevitt
 */
public class Employer extends User {
    private String company;
    private ArrayList<JobPosting> postings;
    private ArrayList<Review> reviews;
    private double averageRating;
    private boolean removed;
/**
 * creates constructor for Employer User
 * @param id randomly generated refrence number 
 * @param username the name selcted by the user that will be displayed to others
 * @param password a keyword used for account safety
 * @param email an email address used for account verification
 * @param firstName the First name of the User
 * @param lastName the Last name of the User
 * @param approved if the account is approved by checking the email
 * @param company the company name 
 * @param averageRating the rating of the company 
 * @param removed if the company has been removed from vewing
 */
    public Employer(UUID id, String username, String password, String email, String firstName, String lastName,
            boolean approved, String company, double averageRating, boolean removed) {
        super(id, username, password, email, firstName, lastName, approved, removed);
        this.company = company;
        this.averageRating = averageRating;
        
    }
/**
 * takes in a job and adds it to the job post datbase
 * @param job
 */
    public void makePosting(JobPosting job) {
        postings.add(job);
        JobPostingDatabase.getInstance().addPosting(job);
    }

    /**
     * overload make posting method that adds a job ot the jobposting database by taking the correct parameters of a job
     * @param description a breif message describing the job
     * @param requirements a short list of expectations 
     * @param hourlyWage a double dispalying the wage
     * @param status a boolean letting the user know if their application was apporved
     * @param applicants a list of students who applied
     */
    public void makePosting(String description, ArrayList<String> requirements, double hourlyWage,
            JobPostingStatus status, ArrayList<Student> applicants) {
        makePosting(new JobPosting.Builder().description(description).requirements(requirements).hourlyWage(hourlyWage)
                .status(status).applicants(applicants).build());
    }

    /**
     * takes in a job and string to change the desciption
     * @param job a job
     * @param toChange a string to change the description of the job
     */
    public void editDescription(JobPosting job, String toChange) {
        job.setDescription(toChange);
    }
/**
 * add news jobposting requirment to job
 * @param job
 * @param toChange
 */
    public void addPostingRequirement(JobPosting job, String toChange) {
        job.addRequirement(toChange);
    }
/**
 * removes a requirment from the jobposting passed in
 * @param job
 * @param toRemove
 */
    public void removePostingRequirement(JobPosting job, String toRemove) {
        job.removeRequriement(toRemove);
    }
/**
 * changes the wage of the jobpsoting passed in
 * @param job
 * @param wage
 */
    public void editPostingWage(JobPosting job, double wage) {
        job.setWage(wage);
    }
/**
 * changes the status of the jobposting passed in
 * @param job
 * @param status
 */
    public void editPostingStatus(JobPosting job, JobPostingStatus status) {
        job.setStatus(status);

    }
/**
 * remove the current job posting 
 * @param job
 */
    public void removePosting(JobPosting job) {
        JobPostingDatabase.getInstance().getPostings().remove(job);
        JobPostingDatabase.getInstance().getRemovedPostings().add(job);
    }
/**
 * takes in job postings and returns job posting applicants 
 * @param job
 * @return jobposting applicants
 */
    public ArrayList<Student> viewApplicants(JobPosting job) {
        return job.getApplicants();
    }
/**
 * takes in a student user, a score given, and a comment about the student
 * and adds a review to the current student
 * @param student
 * @param score
 * @param comment
 */
    public void rateStudent(Student student, int score, String comment) {
        Review rating = new Review.Builder().reviewer(this).reviewee(student).rating(score).comment(comment).build();
        ReviewDatabase.getInstance().addReview(rating);
    }

    /**
     * returns the company name of Employer
     * @return
     */
    public String getCompany() {
        return this.company;
    }
/**
 * returns currrent jobposting of Employer
 * @return
 */
    public ArrayList<JobPosting> getPostings() {
        this.postings = JobPostingDatabase.getInstance().getPostingsByEmployer(this);
        return this.postings;
    }
/**
 * return ArrayList of reviews of Employer
 * @return
 */
    public ArrayList<Review> getReviews() {
        return this.reviews;
    }
/**
 * return the average rating of Employer
 * @return
 */
    public double getAverageRating() {
        return this.averageRating;
    }
/**
 * return the username of Employer
 * @return
 */
    public String getUsername() {
        return this.username;
    }
/**
 * returns the password of Employer
 * @return
 */
    public String getPassword() {
        return this.password;
    }
/**
 * returns email of Employer
 * @return
 */
    public String getEmail() {
        return this.email;
    }
/**
 * Set the name of the Company
 * @param company
 */
    public void setCompany(String company) {
        this.company = company;
    }
/**
 * set the current posting
 * @param postings
 */
    public void setPostings(ArrayList<JobPosting> postings) {
        this.postings = postings;
    }
/**
 * set the current Review
 * @param reviews
 */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
/**
 * set the average Rating
 * @param rating
 */
    public void setAverageRating(double rating) {
        this.averageRating = rating;
    }
/**
 * set the username
 * @param username
 */
    public void setUsername(String username) {
        this.username = username;
    }
/**
 * set the password
 * @param password
 */
    public void setPassword(String password) {
        this.password = password;
    }
/**
 * set the email
 * @param email
 */
    public void setEmail(String email) {
        this.email = email;
    }
/**
 * returns the boolean if removed
 * @return 
 */
    public boolean isRemoved() {
        return removed;
    }
/**
 * set the boolean value if removed
 */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
/**
 * check if current  id == to an exisitng employer's id
 * @param employer
 * @return
 */
    public boolean equals(Employer employer) {
        return this.id == employer.getId();
    }
/**
 * a print string to print all accounnt information 
 */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getFullName() + "\n");
        sb.append(this.getEmail() + "\n");
        sb.append(this.getUsername() + "\n");
        sb.append(this.getCompany() + "\n");
        sb.append(this.getAverageRating() + "\n");
        return sb.toString();
    }
/**
     * Builder class allows for building a new admin
     * 
     * @author Brennan Cain
     */
    public static class Builder {
        private UUID id;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private boolean approved;
        private double averageRating;
        private String company;
        private boolean removed;
        /**
        * Constructor sets all of the values to their default
        */
        public Builder() {
            this.id = UUID.randomUUID();
            this.approved = false;
        }
/**
 * builds a new id for Employer
 * @param id
 * @return
 */
        public Builder id(UUID id) {
            this.id = id;
            return this;
        }
/**
 * builds a new Username for current Employer
 * @param username
 * @return
 */
        public Builder username(String username) {
            this.username = username;
            return this;
        }
/**
 * builds a new password for current Employer
 * @param password
 * @return
 */
        public Builder password(String password) {
            this.password = password;
            return this;
        }
/**
 * builds a new email for current Employer
 * @param email
 * @return
 */
        public Builder email(String email) {
            this.email = email;
            return this;
        }
/**
 * builds a new first name for current Employer
 * @param firstName
 * @return
 */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
/**
 * builds a new last name for current Employer
 * @param lastName
 * @return
 */
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
/**
 * builds a approval status for current Employer
 * @param approved
 * @return
 */
        public Builder approved(boolean approved) {
            this.approved = approved;
            return this;
        }
/**
 * builds an  average rating for current Employer
 * @param averageRating
 * @return
 */
        public Builder averageRating(double averageRating) {
            this.averageRating = averageRating;
            return this;
        }
/**
 * builds a new company name for Current Employer
 * @param company
 * @return
 */
        public Builder company(String company) {
            this.company = company;
            return this;
        }
/**
 * builds a boolean value to be checked if removed for current Employer
 * @param removed
 * @return
 */
        public Builder removed(boolean removed) {
            this.removed = removed;
            return this;
        }
/**
 * builds a new Employer for all parameterers
 * @return
 */
        public Employer build() {
            return new Employer(id, username, password, email, firstName, lastName, approved, company, averageRating,
                    removed);
        }

    }

}