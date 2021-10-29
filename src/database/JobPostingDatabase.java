package database;
import java.util.ArrayList;

import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Student;

public class JobPostingDatabase {
    private static JobPostingDatabase jobPostingDatabase;

    private JobPostingDatabase() {
    }

    public static JobPostingDatabase getInstance() {
        if(jobPostingDatabase == null)
            jobPostingDatabase = new JobPostingDatabase();
        return jobPostingDatabase;
    }

    public void addPosting(JobPosting posting) {
        Database.getInstance().getJobPostings().add(posting);
        Database.getInstance().writeToFilePostings();
    }

    public void removePosting(JobPosting posting) {
        posting.setRemoved(true);
        Database.getInstance().writeToFilePostings();
    }

    public ArrayList<JobPosting> getPostings() {
        return Database.getInstance().getJobPostings();
    }

    public ArrayList<JobPosting> getRemovedPostings() {
        ArrayList<JobPosting> removedPostings = new ArrayList<JobPosting>();
        for(JobPosting posting : Database.getInstance().getJobPostings()) {
            if(posting.isRemoved())
                removedPostings.add(posting);
        }
        return removedPostings;
    }

    public JobPosting getPostingByEmployerAndTitle(String employerUser, String title) {

        for(JobPosting posting : Database.getInstance().getJobPostings()) {
            if(posting.getEmployer().getUsername().equals(employerUser) &&
            posting.getJobTitle().equals(title))
                return posting;
        }
        return null;
    }

    public ArrayList<JobPosting> getPostingsByStudent(Student student) {
        ArrayList<JobPosting> applications = new ArrayList<JobPosting>();
        for(JobPosting posting : Database.getInstance().getJobPostings()) {
            if(posting.getApplicants().contains(student) && !posting.isRemoved())
                applications.add(posting);
        }
        return applications;
    }

    public ArrayList<JobPosting> getPostingsByEmployer(Employer employer) {
        ArrayList<JobPosting> returnPostings = new ArrayList<JobPosting>();
        for(JobPosting posting : Database.getInstance().getJobPostings()) {
            if(posting.getEmployer().equals(employer)); //TODO implement equals
                returnPostings.add(posting);
        }
        return returnPostings;
    }

    public ArrayList<JobPosting> getOpenPostings() {
        ArrayList<JobPosting> openPostings = new ArrayList<JobPosting>();
        for(JobPosting posting : Database.getInstance().getJobPostings()) {
            if(!posting.isRemoved())
                openPostings.add(posting);
        }
        return openPostings;
    }

    public ArrayList<JobPosting> getOpenPostingByRequirement(String requirement) {
        ArrayList<JobPosting> openPostings = new ArrayList<JobPosting>();
        for(JobPosting posting : Database.getInstance().getJobPostings()) {
            if(!posting.isRemoved()) {
                for (String postDetail : posting.getRequirements()) {
                    if(postDetail.toLowerCase().contains(requirement.toLowerCase())) {
                        openPostings.add(posting);
                        break;
                    }
                }
            }
        }
        return openPostings;
    }
  
}
