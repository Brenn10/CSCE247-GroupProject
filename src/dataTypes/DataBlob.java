package dataTypes;
import java.util.ArrayList;

public class DataBlob {
    private ArrayList<User> users;
    private ArrayList<Review> reviews;
    private ArrayList<JobPosting> jobPostings;

    public DataBlob() {
        users = new ArrayList<User>();
        reviews = new ArrayList<Review>();
        jobPostings = new ArrayList<JobPosting>();
    }
    
    public void addUser(User user) {
        users.add(user);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void addJobPosting(JobPosting jobPosting) {
        jobPostings.add(jobPosting);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<JobPosting> getJobPostings() {
        return jobPostings;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void setJobPostings(ArrayList<JobPosting> jobPostings) {
        this.jobPostings = jobPostings;
    }  
}
