import java.util.ArrayList;

public class Resume {
    private ArrayList<Employment> employments;
    private ArrayList<Education> educations;
    private double gpa;
    private ArrayList<String> technicalSkills;
    private double averageRating;

    public Resume(ArrayList<Employment> employments, ArrayList<Education> educations, 
    double gpa, ArrayList<String> technicalSkills, double averageRating) {
        this.employments = employments;
        this.educations = educations;
        this.gpa = gpa;
        this.technicalSkills = technicalSkills;
        this.averageRating = averageRating;
    }

    public void addEmployment(Employment employment) {
        this.employments.add(employment);
    }

    public void addEducation(Education education) {
        educations.add(education);
    }

    public void addSkill(String skill) {
        technicalSkills.add(skill);
    }

    // getters and setters 
    public ArrayList<Employment> getEmployment() {
        return this.employments;
    }
    
    public ArrayList<Education> getEducation() {
        return this.educations;
    }

     public ArrayList<String> getSkills() {
      return this.technicalSkills;
    }

    public double getGPA() {
        return this.gpa;
    }

    public double getAverage() {
        return this.averageRating;
    }

    public void setEmployment(ArrayList<Employment> employments) {
        this.employments = employments;
    }

    public void setEducation(ArrayList<Education> educations) {
        this.educations = educations;
    }

    public void setSkills(ArrayList<String> technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    public void setAverage(double averageRating) {
        this.averageRating = averageRating;
    }
}
