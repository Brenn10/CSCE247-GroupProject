public class Administrator extends User {
    public Administrator(String username, String password, String email) {
        super(username, password, email);
    }
    private boolean isEmailValid(String email) {
        return true;
    }
    public void editUser(UserDatabase user, String toChange) {

    }
    public void setJobPosting(Employer employer, JobPosting job) {
        employer.makePosting(job);
    }
    public void removeUser(User user) {
        // something with database
    }
    public void removeReviews(Review review) {
        // something with database
    }
    public void removeResume(Student student) {
        // something with database
    }
    public void removeJobPosting(Employer employer, JobPosting jobposting) {
        // something with database
    }
    public void addUser(User user, String userType) {
        
    }
    public void addEmployer(JobPostingDatabase employer) {

    }
    public void addResume(Resume resume) {

    }
    public void addReview(Review review) {

    }
}