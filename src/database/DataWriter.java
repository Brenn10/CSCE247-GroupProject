package database;
import java.util.ArrayList;

import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.User;

public abstract class DataWriter {
    public DataWriter() {}

    public abstract void write(ArrayList<User> users, ArrayList<Review> reviews, ArrayList<JobPosting> postings);
}
