import java.util.ArrayList;

public class JsonDataReader extends DataReader {
    private String adminFilePath;
    private String studentFilePath;
    private String employerFilePath;
    private String professorFilePath;
    private String reviewFilePath;
    private String jobPostingFilePath;

    public JsonDataReader(String adminFilePath, 
                          String studentFilePath, 
                          String employerFilePath, 
                          String professorFilePath, 
                          String reviewFilePath, 
                          String jobPostingFilePath) {
        this.adminFilePath = adminFilePath;
        this.studentFilePath = studentFilePath;
        this.employerFilePath = employerFilePath;
        this.professorFilePath = professorFilePath;
        this.reviewFilePath = reviewFilePath;               
        this.jobPostingFilePath = jobPostingFilePath;
    }

    public DataBlob read() {
        return null;
    }

    private ArrayList<Administrator> readAdministrators() {
        return null;
    }

    private ArrayList<Student> readStudents() {
        return null;
    }

    private ArrayList<Employer> readEmployers() {
        return null;
    }

    private ArrayList<Professor> readProfessors() {
        return null;
    }

    private ArrayList<Review> readReviews() {
        return null;
    }

    private ArrayList<JobPosting> readJobPostings() {
        return null;
    }
    
}
