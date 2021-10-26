import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonDataWriter extends DataWriter {
    private String adminFilePath;
    private String studentFilePath;
    private String employerFilePath;
    private String professorFilePath;
    private String reviewFilePath;
    private String jobPostingFilePath;

    public JsonDataWriter(String adminFilePath, 
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

    public void write(ArrayList<User> users, ArrayList<Review> reviews, ArrayList<JobPosting> postings) {
        writeUsers(users);
        writeReviews(users, reviews);
        writeJobPostings(users, postings);
    }
    
    private void writeReviews(ArrayList<User> users, ArrayList<Review> reviews) {
        JSONArray jsonReviews = new JSONArray();
        for (Review review : reviews) {
            jsonReviews.add(jsonify(users, review));
        }
        FileWriter reviewWriter = null;
        try {
            reviewWriter = new FileWriter(reviewFilePath);
            reviewWriter.write(jsonReviews.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reviewWriter != null) {
                try {
                    reviewWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeJobPostings(ArrayList<User> users, ArrayList<JobPosting> postings) {
        JSONArray jsonPostings = new JSONArray();
        for (JobPosting posting : postings) {
            jsonPostings.add(jsonify(users, posting));
        }
        FileWriter postingWriter = null;
        try {
            postingWriter = new FileWriter(jobPostingFilePath);
            postingWriter.write(jsonPostings.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(postingWriter != null) {
                try {
                    postingWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeUsers(ArrayList<User> users) {
        JSONArray studentArray = new JSONArray();
        JSONArray employerArray = new JSONArray();
        JSONArray professorArray = new JSONArray();
        JSONArray adminArray = new JSONArray();

        for (User user : users) {
            if (user instanceof Student) {
                studentArray.add(jsonify((Student) user));
            } else if (user instanceof Employer) {
                employerArray.add(jsonify((Employer) user));
            } else if (user instanceof Professor) {
                professorArray.add(jsonify((Professor) user));
            } else if (user instanceof Administrator) {
                adminArray.add(jsonify((Administrator) user));
            } else {
                throw new IllegalArgumentException("User type not supported");
            }
        }


        try {
            FileWriter adminWriter = new FileWriter(adminFilePath);
            adminWriter.write(adminArray.toJSONString());

            FileWriter studentWriter = new FileWriter(studentFilePath);
            studentWriter.write(studentArray.toJSONString());

            FileWriter employerWriter = new FileWriter(employerFilePath);
            employerWriter.write(employerArray.toJSONString());

            FileWriter professorWriter = new FileWriter(professorFilePath);
            professorWriter.write(professorArray.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject jsonify(Student student) { //TODO: implement
        JSONObject studentJson = new JSONObject();
        studentJson.put(JsonDataLabels.USER_ID.toString(), student.getId());
        studentJson.put(JsonDataLabels.USER_USERNAME.toString(), student.getUsername());
        studentJson.put(JsonDataLabels.USER_PASSWORD.toString(), student.getPassword());
        studentJson.put(JsonDataLabels.USER_FIRSTNAME.toString(), student.getFirstName());
        studentJson.put(JsonDataLabels.USER_LASTNAME.toString(), student.getLastName());
        studentJson.put(JsonDataLabels.USER_EMAIL.toString(), student.getEmail());
        studentJson.put(JsonDataLabels.USER_APPROVED.toString(), student.isApproved());
        studentJson.put(JsonDataLabels.STUDENT_MAJOR.toString(), student.getMajor().toString());
        
        studentJson.put(JsonDataLabels.STUDENT_CREATEDRESUME.toString(), student.hasCreatedResume());

        if(student.hasCreatedResume()) {
            JSONArray jsonEmployments = new JSONArray();
            for (Employment employment : student.getEmployments()) {
                JSONObject jsonEmployment = new JSONObject();
                jsonEmployment.put(JsonDataLabels.STUDENT_EMPLOYMENT_COMPANY.toString(), employment.getCompany());
                jsonEmployment.put(JsonDataLabels.STUDENT_EMPLOYMENT_DATES.toString(), employment.getDates());
                jsonEmployment.put(JsonDataLabels.STUDENT_EMPLOYMENT_TITLE.toString(), employment.getTitle());

                JSONArray details = new JSONArray();
                for (String detail : employment.getDetails()) {
                    details.add(detail);
                }
                jsonEmployment.put(JsonDataLabels.STUDENT_EMPLOYMENT_DETAILS.toString(), details);

                jsonEmployments.add(jsonEmployment);
            }
            studentJson.put(JsonDataLabels.STUDENT_EMPLOYMENTS.toString(), jsonEmployments);
        
        
            JSONArray jsonEducations = new JSONArray();
            for (Education education : student.getEducations()) {
                JSONObject jsonEducation = new JSONObject();
                jsonEducation.put(JsonDataLabels.STUDENT_EDUCATION_GPA.toString(), education.getGpa());
                jsonEducation.put(JsonDataLabels.STUDENT_EDUCATION_GRADDATE.toString(), education.getGradDate());
                jsonEducation.put(JsonDataLabels.STUDENT_EDUCATION_PLACE.toString(), education.getPlace());

                jsonEducations.add(jsonEducation);
            }
            studentJson.put(JsonDataLabels.STUDENT_EDUCATIONS.toString(), jsonEducations);
        }
        
        return studentJson;
    }

    private JSONObject jsonify(Employer employer) { //TODO: implement
        JSONObject employerJson = new JSONObject();
        return employerJson;
    }

    private JSONObject jsonify(Professor professor) { //TODO: implement
        JSONObject professorJson = new JSONObject();
        return professorJson;
    }

    private JSONObject jsonify(Administrator admin) { //TODO: implement
        JSONObject adminJson = new JSONObject();
        return adminJson;
    }

    private JSONObject jsonify(ArrayList<User> users, Review review) { //TODO: implement
        JSONObject reviewJson = new JSONObject();
        return reviewJson;
    }

    private JSONObject jsonify(ArrayList<User> users, JobPosting posting) { //TODO: implement
        JSONObject postingJson = new JSONObject();
        return postingJson;
    }
}
