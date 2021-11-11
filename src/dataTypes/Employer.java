package dataTypes;

import java.util.ArrayList;
import java.util.UUID;

import database.Database;
import enums.JobPostingStatus;

/**
 * Employer User datatype
 * 
 * @author Ian McDevitt
 */
public class Employer extends User {
    private String company;
    private ArrayList<JobPosting> postings;
    private ArrayList<Review> reviews;
    private double averageRating;
    private boolean removed;

    /**
     * Constructor
     * 
     * @param id            their unique identifier
     * @param username      the account username
     * @param password      the account password
     * @param email         the user's email
     * @param firstName     the user's first name
     * @param lastName      the user's last name
     * @param approved      boolean if the user have been approved or not
     * @param company       the company the user works for
     * @param averageRating the average rating of the employer
     * @param removed       boolean if they have been removed or not
     */
    public Employer(UUID id, String username, String password, String email, String firstName, String lastName,
            boolean approved, String company, double averageRating, boolean removed) {
        super(id, username, password, email, firstName, lastName, approved, removed);
        this.company = company;
        this.averageRating = averageRating;

    }

    /**
     * Make posting allows an employer to create a new job posting
     * 
     * @param job the posting they want to make
     */
    public void makePosting(JobPosting job) {
        postings.add(job);
        Database.getInstance().addPosting(job);
    }

    /**
     * If the employer wants to change the description
     * 
     * @param job      the job they want to change
     * @param toChange what they want to description to get changed to
     */
    public void editDescription(JobPosting job, String toChange) {
        job.setDescription(toChange);
    }

    /**
     * Add a new requirement to the ArrayList
     * 
     * @param job      the job they want to add to
     * @param toChange the requirement they want to add to the ArrayList
     */
    public void addPostingRequirement(JobPosting job, String toChange) {
        job.addRequirement(toChange);
    }

    /**
     * Remove a requirement
     * 
     * @param job      the job they want to remove from
     * @param toRemove the requirement they want to remove
     */
    public void removePostingRequirement(JobPosting job, String toRemove) {
        job.removeRequriement(toRemove);
    }

    /**
     * Edit the wage of a job
     * 
     * @param job  the job they want to change
     * @param wage wage they want to change it o
     */
    public void editPostingWage(JobPosting job, double wage) {
        job.setWage(wage);
    }

    /**
     * Edit the status of a job
     * 
     * @param job    the job they want to edit
     * @param status what they want to change the status to
     */
    public void editPostingStatus(JobPosting job, JobPostingStatus status) {
        job.setStatus(status);

    }

    /**
     * Remove a job posting
     * 
     * @param job posting the want to remove
     */
    public void removePosting(JobPosting job) {
        Database.getInstance().getPostings().remove(job);
        Database.getInstance().getRemovedPostings().add(job);
    }

    /**
     * get applicants for a specific job
     * 
     * @param job the job posting they want to look at
     * @return the ArrayList of student applicants
     */
    public ArrayList<Student> viewApplicants(JobPosting job) {
        return job.getApplicants();
    }

    /**
     * Employer has the option to rate a student
     * 
     * @param student the student they want to rate
     * @param score   the score they want to give the student
     * @param comment a comment about a student
     */
    public void rateStudent(Student student, int score, String comment) {
        Review rating = new Review.Builder().reviewer(this).reviewee(student).rating(score).comment(comment).build();
        Database.getInstance().addReview(rating);
    }

    // get and set methods

    /**
     * Get method for company
     * 
     * @return the String company
     */
    public String getCompany() {
        return this.company;
    }

    /**
     * Get method for postings
     * 
     * @return the ArrayList of postings
     */
    public ArrayList<JobPosting> getPostings() {
        this.postings = Database.getInstance().getPostingsByEmployer(this);
        return this.postings;
    }

    /**
     * Get method for reviews
     * 
     * @return the ArrayList of Reviews
     */
    public ArrayList<Review> getReviews() {
        return this.reviews;
    }

    /**
     * Get method for averageRating
     * 
     * @return the double averageRating
     */
    public double getAverageRating() {
        return this.averageRating;
    }

    /**
     * Get method for username
     * 
     * @return the String username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Get method for password
     * 
     * @return the String password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Get method for email
     * 
     * @return the String email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set method for company
     * 
     * @param company what we want to set the company to
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Set method for postings
     * 
     * @param posting what we want to set the posting to
     */
    public void setPostings(ArrayList<JobPosting> postings) {
        this.postings = postings;
    }

    /**
     * Set method for reviews
     * 
     * @param reviews what we want to set the review to
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Set method for averageRating
     * 
     * @param rating what we want to set the rating to
     */
    public void setAverageRating(double rating) {
        this.averageRating = rating;
    }

    /**
     * Set method for username
     * 
     * @param username what we want to set the username to
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set method for passowrd
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set method for email
     * 
     * @param email the email we want to set it to
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get method for removed
     * 
     * @return returns true or false depending on if the user has been removed or
     *         not
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
     * Equals method for comparision between employers
     * 
     * @param employer the employer we want to compare to
     * @return true if they are the same, false otherwise
     */
    public boolean equals(Employer employer) {
        return this.id == employer.getId();
    }

    /**
     * toString method
     * 
     * @return String the employer printed
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
     * Builder class allows for building a new employer
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
         * sets the ID
         * 
         * @param id what we want to set the ID to
         * @return the Builder with this ID
         */
        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        /**
         * sets the username
         * 
         * @param username what we want to set the username to
         * @return the Builder with this username
         */
        public Builder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * sets the password
         * 
         * @param password what we want to set the password to
         * @return the Builder with this password
         */
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * sets the email
         * 
         * @param email what we want to set the email to
         * @return the Builder with this email
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * sets the firstname
         * 
         * @param firstname what we want to set the firstname to
         * @return the Builder with this firstname
         */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * sets the last name
         * 
         * @param lastname what we want to set the lastname to
         * @return the Builder with this lastname
         */
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * sets the approved
         * 
         * @param approved what we want to set the approved to
         * @return the Builder with this email
         */
        public Builder approved(boolean approved) {
            this.approved = approved;
            return this;
        }

        /**
         * sets the averageRating
         * 
         * @param averageRating what we want to set the averageRating to
         * @return the Builder with this averageRating
         */
        public Builder averageRating(double averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        /**
         * sets the company
         * 
         * @param company what we want to set the company to
         * @return the Builder with this company
         */
        public Builder company(String company) {
            this.company = company;
            return this;
        }

        /**
         * sets the removed
         * 
         * @param removed what we want to set the removed to
         * @return the Builder with this removed
         */
        public Builder removed(boolean removed) {
            this.removed = removed;
            return this;
        }

        /**
         * creates the new Employer user
         * 
         * @return a new Employer user
         */
        public Employer build() {
            return new Employer(id, username, password, email, firstName, lastName, approved, company, averageRating,
                    removed);
        }

    }

}