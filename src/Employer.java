import java.util.ArrayList;

public class Employer extends User {
    private String company;
    private ArrayList<JobPosting> postings;
    private ArrayList<Review> reviews;
    private double averageRating;

    public Employer(String username, String password, String email, String company) {
        super(username, password, email);
    }
    public void makePosting(JobPosting job) {

    }
    public void editPosting(JobPosting job, String toChange) {

    }
    public void removePosting(JobPosting job) {

    }
    public ArrayList<JobPosting> viewApplicants(JobPosting job) {
         return postings;
    }
    public void rateStudent(Student student, double rating, String comment) {

    }
}