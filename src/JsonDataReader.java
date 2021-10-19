import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonDataReader extends DataReader {
    private String adminFilePath;
    private String studentFilePath;
    private String employerFilePath;
    private String professorFilePath;
    private String reviewFilePath;
    private String jobPostingFilePath;

    private static final JSONParser parser = new JSONParser();

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
        DataBlob dataBlob = new DataBlob();

        ArrayList<Administrator> adminList = readAdministrators();
        for (Administrator admin : adminList) {
            dataBlob.addUser(admin);
        }       

        return dataBlob;
    }

    private ArrayList<Administrator> readAdministrators() {
        ArrayList<Administrator> adminList = new ArrayList<Administrator>();
        try {
            FileReader reader = new FileReader(adminFilePath);
            JSONArray jsonList = (JSONArray) parser.parse(reader);
            for (Object adminObj : jsonList) {
                JSONObject adminJson = (JSONObject) adminObj;
                Administrator admin = new Administrator.Builder()
                    .id(UUID.fromString((String) adminJson.get("id")))
                    .username((String) adminJson.get("username"))
                    .password((String) adminJson.get("password"))
                    .email((String) adminJson.get("email"))
                    .firstName((String) adminJson.get("firstName"))
                    .lastName((String) adminJson.get("lastName"))
                    .approved((boolean) adminJson.get("approved"))
                    .build();
                adminList.add(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminList;
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
