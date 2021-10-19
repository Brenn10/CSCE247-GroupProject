import java.util.ArrayList;
import java.util.UUID;

public class Professor extends User {

    public Professor(UUID id, 
            String username, 
            String password,
            String email, 
            String firstName,
            String lastName,
            boolean approved) {
        super(id, username, password, email, firstName, lastName, approved);
    }
    private boolean isEmailValid(String email) {
        return true;
    }
    public void rateStudent(Student student, int rating, String comment) {
        Review reveiwToAdd = new Review(this, student, rating, comment);
        // add to database somehow
    }

    public ArrayList<Student> getStudentReviewed() {
        return null;
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