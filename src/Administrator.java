public class Administrator extends User {
    public Administrator(String username, String password, String email) {
        super();
    }
    private boolean isEmailValid(String email) {
        return true;
    }
    public void editUser(UserDatabase user, String toChange) {

    }
    public void setJobPosting(Employer employer, JobPostingDatabase job) {

    }
    public void removeUser(User user) {

    }
    public void removeReviews(Review review) {

    }
    public void removeResume(Student student) {

    }
    public void removeJobPosting(Employer employer, JobPosting jobposting) {

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