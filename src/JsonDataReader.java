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
        
        ArrayList<Student> studentList = readStudents();
        for (Student student : studentList) {
            dataBlob.addUser(student);
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
        ArrayList<Student> studentList = new ArrayList<Student>();
        try {
            FileReader reader = new FileReader(studentFilePath);
            JSONArray jsonList = (JSONArray) parser.parse(reader);
            for (Object studentObj : jsonList) {
                JSONObject studentJson = (JSONObject) studentObj;

                ArrayList<String> skills = new ArrayList<String>();
                JSONArray skillsJson = (JSONArray) studentJson.get("skills");
                for (Object skillObj : skillsJson) {
                    skills.add((String) skillObj);
                }

                ArrayList<Employment> employments = new ArrayList<Employment>();
                JSONArray employmentsJson = (JSONArray) studentJson.get("employments");
                for (Object employmentObj : employmentsJson) {
                    JSONObject employmentJson = (JSONObject) employmentObj;

                    ArrayList<String> details = new ArrayList<String>();
                    for (Object detail : (JSONArray) employmentJson.get("details")) {
                        details.add((String) detail);
                    }

                    Employment employment = new Employment.Builder()
                        .company((String) employmentJson.get("company"))
                        .title((String) employmentJson.get("title"))
                        .dates((String) employmentJson.get("dates"))
                        .details(details)
                        .build();
                    employments.add(employment);
                }

                ArrayList<Education> educations = new ArrayList<Education>();
                JSONArray educationsJson = (JSONArray) studentJson.get("educations");
                for (Object educationObj : educationsJson) {
                    JSONObject educationJson = (JSONObject) educationObj;

                    Education education = new Education.Builder()
                        .place((String) educationJson.get("place"))
                        .gpa((double) educationJson.get("gpa"))
                        .gradDate((String) educationJson.get("gradDate"))
                        .build();
                    educations.add(education);
                }

                Major major = Major.NA;
                String majorStr = (String) studentJson.get("major");
                if (majorStr.equalsIgnoreCase("computer science")) {
                    major = Major.COMPUTER_SCIENCE;
                } else if (majorStr.equalsIgnoreCase("computer engineering")) {
                    major = Major.COMPUTER_ENGINEERING;
                } else if (majorStr.equalsIgnoreCase("computer information systems")) {
                    major = Major.COMPUTER_INFORMATION_SYSTEMS;
                } else if (majorStr.equalsIgnoreCase("integrated information technology")) {
                    major = Major.INTEGRATED_INFORMATION_TECHNOLOGY;
                }

                Student student = new Student.Builder()
                    .id(UUID.fromString((String) studentJson.get("id")))
                    .username((String) studentJson.get("username"))
                    .password((String) studentJson.get("password"))
                    .email((String) studentJson.get("email"))
                    .firstName((String) studentJson.get("firstName"))
                    .lastName((String) studentJson.get("lastName"))
                    .approved((boolean) studentJson.get("approved"))
                    .major((String) studentJson.get("major"))
                    .createdResume((boolean) studentJson.get("createdResume"))
                    .employments(employments)
                    .educations(educations)
                    .technicalSkills(skills)
                    .averageRating((double) studentJson.get("averageRating"))
                    .build();
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
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
