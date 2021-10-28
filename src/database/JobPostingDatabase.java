package database;
import java.util.ArrayList;

import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Student;

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

    public ArrayList<JobPosting> getPostingsByStudent(Student student) {
        ArrayList<JobPosting> applications = new ArrayList<JobPosting>();
        for(JobPosting posting : postings) {
            if(posting.getApplicants().contains(student) && !posting.isRemoved())
                applications.add(posting);
        }
        return applications;
    }

    public ArrayList<JobPosting> getPostingsByEmployer(Employer employer) {
        ArrayList<JobPosting> returnPostings = new ArrayList<JobPosting>();
        for(JobPosting posting: postings) {
            if(posting.getEmployer().equals(employer)); //TODO implement equals
                returnPostings.add(posting);
        }
        return returnPostings;
    }

    public ArrayList<JobPosting> getOpenPostings() {
        ArrayList<JobPosting> openPostings = new ArrayList<JobPosting>();
        for(JobPosting posting : postings) {
            if(!posting.isRemoved())
                openPostings.add(posting);
        }
        return openPostings;
    }

  
}
