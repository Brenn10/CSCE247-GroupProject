package database;

import java.util.ArrayList;

import dataTypes.DataBlob;
import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.User;

/**
 * Abstract class for DataWriter
 * 
 * @author Brennan Cain
 */

public abstract class DataWriter {
    /**
     * Intentionally empty constructor
     */
    public DataWriter() {
    }

    /**
     * abstact method that will write to the file
     */
    public abstract void write(DataBlob data);

    /**
     * abstact method that will update everything to the files
     * 
     * @param users    the ArrayList of users to write
     * @param reviews  the ArrayList of reviews to write
     * @param postings the ArrayList of postings to write
     */
    public abstract void write(ArrayList<User> users, ArrayList<Review> reviews, ArrayList<JobPosting> postings);

    /**
     * abstract method that will update the users
     * 
     * @param users the ArrayList of users to write
     */
    public abstract void writeUsers(ArrayList<User> users);

    /**
     * abstract method that will update the postings
     * 
     * @param postings the ArrayList of postings to write
     */
    public abstract void writeJobPostings(ArrayList<JobPosting> postings);

    /**
     * abstract method that will update the reviews
     * 
     * @param reviews the ArrayList of reviews to write
     */
    public abstract void writeReviews(ArrayList<Review> reviews);

}
