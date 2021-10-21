import java.util.ArrayList;

public class Student extends User {
    private Resume resume;
    private String major;
    private boolean createdResume;
    private ArrayList<Review> reviews;

    public Student(String username, String password, String email, String major) {
        super(username, password, email);
        this.username = username;
        this.password = password;
        this.email = email;
        this.major = major;
        createdResume = false;
        reviews = new ArrayList<Review>();

    }
    private boolean isEmailValid(String email) {
        return email.contains("email.sc.edu");
    }
    public void reviewEmployer(double rating) {
        
    }
    public void apply(JobPosting job) {
        JobApplication applicant = new JobApplication(this.resume);
        job.addApplicant(applicant);
    }
    public void changeGPA(double gpa){ 
        this.resume.setGPA(gpa);
    }
    public void addEmployment(Employment employment) {
        resume.addEmployment(employment);
    }
    public void editEmployment(ArrayList<Employment> employment) {
        this.resume.setEmployment(employment);
    }
    public void changeTechnicalSkills(ArrayList<String> techincalSkills) {
        this.resume.setSkills(techincalSkills);
    }
    public void addTechincalSkill(String skill) {
        this.resume.addSkill(skill);
    }
    public void removeTechincalSkill(String skill) {
        this.resume.getSkills().remove(skill);
    }
    public void addEducation(Education education) {
        this.resume.addEducation(education);
    }
    public void removeEducation(Education education) {
        this.resume.getEducation().remove(education);
    }
    // get and set methods 
   
    public Resume getResume() {
        return this.resume;    
    }

    public String getMajor() {
        return this.major;
    }
    public boolean getCreated() {
        return this.createdResume;
    }
    public ArrayList<Review> getReviews() {
        return this.reviews;
    }
    public double getAverageReview() {
        return 7.0;
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

    public void setResume(Resume resume) {
        this.resume = resume;    
    }

    public void setMajor(String major) {
        this.major = major;
    }
    public void setCreated(Boolean createdResume) {
        this.createdResume = createdResume;
    }
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
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