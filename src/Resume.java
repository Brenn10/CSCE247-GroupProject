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


}
