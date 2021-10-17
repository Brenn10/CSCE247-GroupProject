import java.util.ArrayList;

public class Student extends User {
    private Resume resume;
    private String major;
    private boolean createdResume;
    private ArrayList<Review> reviews;

    public Student(String username, String password, String email, String major) {
        super(username, password, email);
    }
    private boolean isEmailValid(String email) {
        return true;
    }
    public void reviewEmployer(double rating) {

    }
    public void apply(JobPosting job) {

    }
    public void changeGPA(double gpa){ 

    }
    public void addEmployment(Employment employment) {

    }
    public void editEmployment(ArrayList<Employment> Employers) {

    }
    public void changeTechnicalSkills(ArrayList<String> techincalSkills) {

    }
    public void addTechincalSkill(String skill) {

    }
    public void removeTechincalSkill(String skill) {

    }
    public double getAverageReview() {
        return 7.0;
    }
    public Resume getResume() {
        return null;
    }
    public void addEducation() {

    }
    public void removeEducation() {

    }
}