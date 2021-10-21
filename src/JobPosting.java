import java.util.ArrayList;

public class JobPosting {
    private Employer employer;
    private String description;
    private ArrayList<String> requirements;
    private double hourlyWage;
    private String status;
    private ArrayList<Student> applicants;

    public JobPosting(Employer employer, String description, ArrayList<String> requirements, 
    double hourlyWage, String status, ArrayList<Student> applicants) {
        this.employer = employer;
        this.description = description;
        this.requirements = requirements;
        this.hourlyWage = hourlyWage;
        this.status = status;
        this.applicants = applicants;
    }

    public void addRequirement(String requirement) {
        this.requirements.add(requirement);
    }

    public void removeRequriement(String toRemove) {
        this.requirements.remove(toRemove);
    }

    public void addApplicant(Student applicant) {
        this.applicants.add(applicant);
    }

    public Employer getEmployer() {
        return this.employer;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<String> getRequirements() {
        return this.requirements;
    }

    public double getWage() {
        return this.hourlyWage;
    }

    public String getStatus() {
        return this.status;
    }

    public ArrayList<Student> getApplicants() {
        return this.applicants;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public void setDescription(String description) {
       this.description = description;
    }

    public void Requirements(ArrayList<String> requirements) {
        this.requirements = requirements;
    }

    public void setWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public void setStatus(String status) {
        // would require something with the enum
        this.status = status;
    }

    public void setApplicants(ArrayList<Student> applicants) {
       this.applicants = applicants;
    }

    

}
