package dataTypes;

import java.util.ArrayList;
import java.util.UUID;

import database.JobPostingDatabase;
import database.ReviewDatabase;
import enums.JobPostingStatus;

/**
<<<<<<< HEAD
 * Employer User Data type of User
=======
 * Employer User datatype
 * 
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
 * @author Ian McDevitt
 */
public class Employer extends User {
    private String company;
    private ArrayList<JobPosting> postings;
    private ArrayList<Review> reviews;
    private double averageRating;
    private boolean removed;
<<<<<<< HEAD
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
=======

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
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
    public Employer(UUID id, String username, String password, String email, String firstName, String lastName,
            boolean approved, String company, double averageRating, boolean removed) {
        super(id, username, password, email, firstName, lastName, approved, removed);
        this.company = company;
        this.averageRating = averageRating;

    }
<<<<<<< HEAD
/**
 * takes in a job and adds it to the job post datbase
 * @param job
 */
=======

    /**
     * Make posting allows an employer to create a new job posting
     * 
     * @param job the posting they want to make
     */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
    public void makePosting(JobPosting job) {
        postings.add(job);
        JobPostingDatabase.getInstance().addPosting(job);
    }

    /**
<<<<<<< HEAD
     * overload make posting method that adds a job ot the jobposting database by taking the correct parameters of a job
     * @param description a breif message describing the job
     * @param requirements a short list of expectations 
     * @param hourlyWage a double dispalying the wage
     * @param status a boolean letting the user know if their application was apporved
     * @param applicants a list of students who applied
=======
     * Make posting allows an employer to create a new job posting overloaded method
     * 
     * @param description  a description of the job
     * @param requirements the requirements for the job ArrayList
     * @param hourlyWage   the hourly wage of the job
     * @param status       if the job is open, pending, or closed
     * @param applicants   an ArrayList of student applicants
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
     */
    public void makePosting(String description, ArrayList<String> requirements, double hourlyWage,
            JobPostingStatus status, ArrayList<Student> applicants) {
        makePosting(new JobPosting.Builder().description(description).requirements(requirements).hourlyWage(hourlyWage)
                .status(status).applicants(applicants).build());
    }

    /**
<<<<<<< HEAD
     * takes in a job and string to change the desciption
     * @param job a job
     * @param toChange a string to change the description of the job
=======
     * If the employer wants to change the description
     * 
     * @param job      the job they want to change
     * @param toChange what they want to description to get changed to
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
     */
    public void editDescription(JobPosting job, String toChange) {
        job.setDescription(toChange);
    }
<<<<<<< HEAD
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
=======

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
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
    public void editPostingStatus(JobPosting job, JobPostingStatus status) {
        job.setStatus(status);

    }
<<<<<<< HEAD
/**
 * remove the current job posting 
 * @param job
 */
=======

    /**
     * Remove a job posting
     * 
     * @param job posting the want to remove
     */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
    public void removePosting(JobPosting job) {
        JobPostingDatabase.getInstance().getPostings().remove(job);
        JobPostingDatabase.getInstance().getRemovedPostings().add(job);
    }
<<<<<<< HEAD
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
=======

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
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
    public void rateStudent(Student student, int score, String comment) {
        Review rating = new Review.Builder().reviewer(this).reviewee(student).rating(score).comment(comment).build();
        ReviewDatabase.getInstance().addReview(rating);
    }

<<<<<<< HEAD
    /**
     * returns the company name of Employer
     * @return
=======
    // get and set methods

    /**
     * Get method for company
     * 
     * @return the String company
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
     */
    public String getCompany() {
        return this.company;
    }
<<<<<<< HEAD
/**
 * returns currrent jobposting of Employer
 * @return
 */
=======

    /**
     * Get method for postings
     * 
     * @return the ArrayList of postings
     */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
    public ArrayList<JobPosting> getPostings() {
        this.postings = JobPostingDatabase.getInstance().getPostingsByEmployer(this);
        return this.postings;
    }
<<<<<<< HEAD
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
=======

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
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getFullName() + "\n");
        sb.append(this.getEmail() + "\n");
        sb.append(this.getUsername() + "\n");
        sb.append(this.getCompany() + "\n");
        sb.append(this.getAverageRating() + "\n");
        return sb.toString();
    }
<<<<<<< HEAD
/**
     * Builder class allows for building a new admin
=======

    /**
     * Builder class allows for building a new employer
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
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
<<<<<<< HEAD
        /**
        * Constructor sets all of the values to their default
        */
=======

        /**
         * Constructor sets all of the values to their default
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Builder() {
            this.id = UUID.randomUUID();
            this.approved = false;
        }
<<<<<<< HEAD
/**
 * builds a new id for Employer
 * @param id
 * @return
 */
=======

        /**
         * sets the ID
         * 
         * @param id what we want to set the ID to
         * @return the Builder with this ID
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Builder id(UUID id) {
            this.id = id;
            return this;
        }
<<<<<<< HEAD
/**
 * builds a new Username for current Employer
 * @param username
 * @return
 */
=======

        /**
         * sets the username
         * 
         * @param username what we want to set the username to
         * @return the Builder with this username
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Builder username(String username) {
            this.username = username;
            return this;
        }
<<<<<<< HEAD
/**
 * builds a new password for current Employer
 * @param password
 * @return
 */
=======

        /**
         * sets the password
         * 
         * @param password what we want to set the password to
         * @return the Builder with this password
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Builder password(String password) {
            this.password = password;
            return this;
        }
<<<<<<< HEAD
/**
 * builds a new email for current Employer
 * @param email
 * @return
 */
=======

        /**
         * sets the email
         * 
         * @param email what we want to set the email to
         * @return the Builder with this email
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Builder email(String email) {
            this.email = email;
            return this;
        }
<<<<<<< HEAD
/**
 * builds a new first name for current Employer
 * @param firstName
 * @return
 */
=======

        /**
         * sets the firstname
         * 
         * @param firstname what we want to set the firstname to
         * @return the Builder with this firstname
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
<<<<<<< HEAD
/**
 * builds a new last name for current Employer
 * @param lastName
 * @return
 */
=======

        /**
         * sets the last name
         * 
         * @param lastname what we want to set the lastname to
         * @return the Builder with this lastname
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
<<<<<<< HEAD
/**
 * builds a approval status for current Employer
 * @param approved
 * @return
 */
=======

        /**
         * sets the approved
         * 
         * @param approved what we want to set the approved to
         * @return the Builder with this email
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Builder approved(boolean approved) {
            this.approved = approved;
            return this;
        }
<<<<<<< HEAD
/**
 * builds an  average rating for current Employer
 * @param averageRating
 * @return
 */
=======

        /**
         * sets the averageRating
         * 
         * @param averageRating what we want to set the averageRating to
         * @return the Builder with this averageRating
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Builder averageRating(double averageRating) {
            this.averageRating = averageRating;
            return this;
        }
<<<<<<< HEAD
/**
 * builds a new company name for Current Employer
 * @param company
 * @return
 */
=======

        /**
         * sets the company
         * 
         * @param company what we want to set the company to
         * @return the Builder with this company
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Builder company(String company) {
            this.company = company;
            return this;
        }
<<<<<<< HEAD
/**
 * builds a boolean value to be checked if removed for current Employer
 * @param removed
 * @return
 */
=======

        /**
         * sets the removed
         * 
         * @param removed what we want to set the removed to
         * @return the Builder with this removed
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Builder removed(boolean removed) {
            this.removed = removed;
            return this;
        }
<<<<<<< HEAD
/**
 * builds a new Employer for all parameterers
 * @return
 */
=======

        /**
         * creates the new Employer user
         * 
         * @return a new Employer user
         */
>>>>>>> 5091e4c3250f45fbc0dc40aa658cd3094863c8e2
        public Employer build() {
            return new Employer(id, username, password, email, firstName, lastName, approved, company, averageRating,
                    removed);
        }

    }

}