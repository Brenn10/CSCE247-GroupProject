package database;
import java.util.ArrayList;

import dataTypes.DataBlob;
import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.User;

public class Database {
    private static Database instance = null;

    DataReader reader;
    DataWriter writer;

    private ArrayList<User> users;
    private ArrayList<Review> reviews;
    private ArrayList<JobPosting> postings;

    private Database() {}
    
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void setDataReader(DataReader reader) {
        this.reader = reader;
    }

    public void setDataWriter(DataWriter writer) {
        this.writer = writer;
    }

    public void loadFromFile() {
        DataBlob blob = reader.read();
        users = blob.getUsers();
        reviews = blob.getReviews();
        postings = blob.getJobPostings();
    }

    public void writeToFile() {
        writer.write(users, reviews, postings);
    }
    /** 
     * 
     * This should fix the problem as far as I can tell for adding jobpostings, reviews, and users to the database
     * check code in the following: JobPostingDatabase, ReviewDatabase, and UserDatabase
     * 
    public void writeToFilePostings() {
        writer.write( postings);
    }
    public void writeToFileReviews() {
        writer.write(reviews);
    }
    public void writeToFileUsers() {
        writer.write(users);
    }
    */
    public ArrayList<JobPosting> getJobPostings() {
        return postings;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
