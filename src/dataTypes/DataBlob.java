package dataTypes;

import java.util.ArrayList;

/**
 * Datablob datatype Aid in data access Keeps track of the ArrayLists
 * 
 * @author Brennan Cain
 */
public class DataBlob {
    private ArrayList<User> users;
    private ArrayList<Review> reviews;
    private ArrayList<JobPosting> jobPostings;

    /**
     * Constructor Initializes attributes of class
     */
    public DataBlob() {
        users = new ArrayList<User>();
        reviews = new ArrayList<Review>();
        jobPostings = new ArrayList<JobPosting>();
    }

    /**
     * Add user to ArrayList of Users
     * 
     * @param user the user to add
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Add review to ArrayList of Review
     * 
     * @param review the user to add
     */
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
     * Add posting to ArrayList of JobPostings
     * 
     * @param jobPosting the posting to add
     */
    public void addJobPosting(JobPosting jobPosting) {
        jobPostings.add(jobPosting);
    }

    /**
     * Get method for ArrayList of users
     * 
     * @return the ArrayList of users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Get method for ArrayList of reviews
     * 
     * @return the ArrayList of reviews
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Get method for ArrayList of posting
     * 
     * @return the ArrayList of postings
     */
    public ArrayList<JobPosting> getJobPostings() {
        return jobPostings;
    }

    /**
     * Set method for ArrayList of users
     * 
     * @param users the ArrayList of users to set
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Set method for ArrayList of reviews
     * 
     * @param reviews the ArrayList of review
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Set method for ArrayList of jobPosting
     * 
     * @param jobPosting the ArrayList of jobPosting
     */
    public void setJobPostings(ArrayList<JobPosting> jobPostings) {
        this.jobPostings = jobPostings;
    }
}
