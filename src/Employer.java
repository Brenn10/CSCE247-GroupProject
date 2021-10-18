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
        JobPosting jobToAdd = job;
        // add to database somehow
    }

    public void makePosting(String description, ArrayList<String> requirements, 
    double hourlyWage, String status, ArrayList<JobApplication> applicants) {
        JobPosting posting = new JobPosting(this, description, requirements, hourlyWage, status, applicants);
        // somehow add it to the database 
    }

    public void editPosting(JobPosting job, String toChange) {
        // for this to work, we would need getters/setters? and define how to Change works
    }
    public void removePosting(JobPosting job) {
        // something with the database that I am unsure about
    }
    public ArrayList<JobApplication> viewApplicants(JobPosting job) {
         return job.getApplicants();
    }
    public void rateStudent(Student student, int score, String comment) {
        Review rating = new Review(this, student, score, comment);
    }
}