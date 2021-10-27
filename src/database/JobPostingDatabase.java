package database;
import java.util.ArrayList;

import dataTypes.JobPosting;

public class JobPostingDatabase {
    private static JobPostingDatabase jobPostingDatabase;
    private ArrayList<JobPosting> postings;
    private ArrayList<JobPosting> removedPostings;

    private JobPostingDatabase() {
        postings = new ArrayList<JobPosting>();
        removedPostings = new ArrayList<JobPosting>();
    }

    public static JobPostingDatabase getInstance() {
        if(jobPostingDatabase == null)
            jobPostingDatabase = new JobPostingDatabase();
        return jobPostingDatabase;
    }

    public void addPosting(JobPosting posting) {
        postings.add(posting);
        Database.getInstance().writeToFilePostings(postings);
        //this should work if you change the database file writeToFile to take postings as its only argument.
        //check Database.java
        //TODO add posting to database 
    }

    public void removePosting(JobPosting posting) {
        removedPostings.add(posting);
        posting.setRemoved(true);
        Database.getInstance().writeToFilePostings(postings);
    }

    public ArrayList<JobPosting> getPostings() {
        return this.postings;
    }

    public ArrayList<JobPosting> getRemovedPostings() {
        return this.removedPostings;
    }

  
}
