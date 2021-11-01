package database;

import java.util.ArrayList;

import dataTypes.DataBlob;
import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.User;

/**
 * The overall database that allows for data reading and writing
 * 
 * @author Brennan Cain
 */
public class Database {
    private static Database instance = null; // singleton

    DataReader reader;
    DataWriter writer;

    private ArrayList<User> users;
    private ArrayList<Review> reviews;
    private ArrayList<JobPosting> postings;

    /**
     * Constructor The Database class is a singleton so the constructor is not
     * accessible outside of the class
     */
    private Database() {
    }

    /**
     * Singleton getInstance method there is only one Database object
     * 
     * @return Database object (singleton)
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Set the Database reader attribute
     * 
     * @param reader set the attribute this.reader to reader
     */
    public void setDataReader(DataReader reader) {
        this.reader = reader;
    }

    /**
     * Set the Database writer attribute
     * 
     * @param writer set the attribute this.writer to writer
     */
    public void setDataWriter(DataWriter writer) {
        this.writer = writer;
    }

    /**
     * Reads all of the different data we have stored in JSON files and puts them in
     * arrays
     */
    public void loadFromFile() {
        DataBlob blob = reader.read();
        users = blob.getUsers();
        reviews = blob.getReviews();
        postings = blob.getJobPostings();
    }

    /**
     * updates all of the JSON files
     */
    public void writeToFile() {
        writer.write(users, reviews, postings);
    }

    /**
     * updates just the postings JSON file
     */
    public void writeToFilePostings() {
        writer.writeJobPostings(postings);
    }

    /**
     * updates just the reviews JSON file
     */
    public void writeToFileReviews() {
        writer.writeReviews(reviews);
    }

    /**
     * updates just the user JSON files
     */
    public void writeToFileUsers() {
        writer.writeUsers(users);
    }

    /**
     * Get method for jobPostings
     * 
     * @return an ArrayList with all of the postings in the database
     */
    public ArrayList<JobPosting> getJobPostings() {
        return postings;
    }

    /**
     * Get method for reviews
     * 
     * @return an ArrayList with all of the reviews in the database
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Get method for users
     * 
     * @return an ArrayList with all of the users in the database
     */
    public ArrayList<User> getUsers() {
        return users;
    }
}
