package database;

import java.util.ArrayList;

import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Student;

/**
 * The jobPosting database
 * 
 * @author Stella Garcia and Ian McDevitt
 */
public class JobPostingDatabase {
    private static JobPostingDatabase jobPostingDatabase; // singleton instance

    /**
     * Constructor The JobPostingDatabase class is a singleton so the constructor is
     * not accessible outside of the class
     */
    private JobPostingDatabase() {
    }

    /**
     * Singleton getInstance method there is only one JobPostingDatabase object
     * 
     * @return JobPostingDatabase object (singleton)
     */
    public static JobPostingDatabase getInstance() {
        if (jobPostingDatabase == null)
            jobPostingDatabase = new JobPostingDatabase();
        return jobPostingDatabase;
    }

    /**
     * When a new posting is added, we add it to the Database's ArrayList and update
     * the JSON
     * 
     * @param posting the posting to be added
     */
    public void addPosting(JobPosting posting) {
        Database.getInstance().getJobPostings().add(posting);
        Database.getInstance().writeToFilePostings();
    }

    /**
     * When a posting is to be removed, we set it's removed attribute to true and
     * update the JSON
     * 
     * @param posting the posting to be removed
     */
    public void removePosting(JobPosting posting) {
        posting.setRemoved(true);
        Database.getInstance().writeToFilePostings();
    }

    /**
     * Get method for all of the postings
     * 
     * @return ArrayList of all of the postings
     */
    public ArrayList<JobPosting> getPostings() {
        ArrayList<JobPosting> postings = Database.getInstance().getJobPostings();
        ArrayList<JobPosting> toReturn = new ArrayList<JobPosting>();
        for (JobPosting posting : postings)
            if (!posting.isRemoved())
                toReturn.add(posting);
        return toReturn;
    }

    /**
     * Get method for all of the removed postings
     * 
     * @return ArrayList of all of the removed postings
     */
    public ArrayList<JobPosting> getRemovedPostings() {
        ArrayList<JobPosting> removedPostings = new ArrayList<JobPosting>();
        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (posting.isRemoved())
                removedPostings.add(posting);
        }
        return removedPostings;
    }

    /**
     * Get method for a specific posting
     * 
     * @param employerUser The employer user who made the post
     * @param title        The title of the post
     * @return JobPosting the specific post we are looking for
     */
    public JobPosting getPostingByEmployerAndTitle(String employerUser, String title) {

        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (posting.getEmployer().getUsername().equals(employerUser) && posting.getJobTitle().equals(title))
                return posting;
        }
        return null;
    }

    /**
     * Gets all of the postings a specific student user has applied to
     * 
     * @param student the student in question
     * @return ArrayList of all of the jobs they applied for
     */
    public ArrayList<JobPosting> getPostingsByStudent(Student student) {
        ArrayList<JobPosting> applications = new ArrayList<JobPosting>();
        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (posting.getApplicants().contains(student) && !posting.isRemoved())
                applications.add(posting);
        }
        return applications;
    }

    /**
     * Gets all of the postings made by a specifc employer users
     * 
     * @param employer the employer in question
     * @return ArrayList of all of the posts
     */
    public ArrayList<JobPosting> getPostingsByEmployer(Employer employer) {
        ArrayList<JobPosting> returnPostings = new ArrayList<JobPosting>();
        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (posting.getEmployer().equals(employer))
                ; // TODO implement equals
            returnPostings.add(posting);
        }
        return returnPostings;
    }

    /**
     * get method for all of the postings that are still open
     * 
     * @return ArrayList of all open postings
     */
    public ArrayList<JobPosting> getOpenPostings() {
        ArrayList<JobPosting> openPostings = new ArrayList<JobPosting>();
        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (!posting.isRemoved())
                openPostings.add(posting);
        }
        return openPostings;
    }

    /**
     * Searches all of the jobs with a specific requirement
     * 
     * @param requirement the requirement they are looking for
     * @return ArrayList of all jobs with that requirement
     * @author Brennan Cain
     */
    public ArrayList<JobPosting> getOpenPostingByRequirement(String requirement) {
        ArrayList<JobPosting> openPostings = new ArrayList<JobPosting>();
        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (!posting.isRemoved()) {
                for (String postDetail : posting.getRequirements()) {
                    if (postDetail.toLowerCase().contains(requirement.toLowerCase())) {
                        openPostings.add(posting);
                        break;
                    }
                }
            }
        }
        return openPostings;
    }
}
