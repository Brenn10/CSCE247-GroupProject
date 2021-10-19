import java.util.ArrayList;

public class Professor extends User {
    private ArrayList<Student> studentReviewed;

    public Professor(String username, String password, String email) {
        super(username, password, email);
    }
    private boolean isEmailValid(String email) {
        return true;
    }
    public void rateStudent(Student student, int rating, String comment) {
        Review reveiwToAdd = new Review(this, student, rating, comment);
        // add to database somehow
    }

    public ArrayList<Student> getStudentReviewed() {
        return this.studentReviewed;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setStudentReviewed(ArrayList<Student> studentReviewed) {
        this.studentReviewed = studentReviewed;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}