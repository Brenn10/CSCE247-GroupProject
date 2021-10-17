import java.util.ArrayList;

public class Professor extends User {
    private ArrayList<Student> studentReviewed;

    public Professor(String username, String password, String email) {
        super(username, password, email);
    }
    private boolean isEmailValid(String email) {
        return true;
    }
    public void rateStudent(Student student, double rating, String comment) {

    }

}