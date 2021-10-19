import java.util.ArrayList;

public class JobPostingDatabase {
    private JobPostingDatabase jobPostingDatabase;
    private ArrayList<JobPosting> postings;
    private ArrayList<JobPosting> removedPostings;

    private JobPostingDatabase() {
        postings = new ArrayList<JobPosting>();
        removedPostings = new ArrayList<JobPosting>();
    }

    public JobPostingDatabase getInstance() {
        jobPostingDatabase = new JobPostingDatabase();
        return jobPostingDatabase;
    }

    public void addPostng(JobPosting posting) {
        // add posting to database 
    }

  
}
