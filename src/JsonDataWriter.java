import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonDataWriter extends DataWriter {
    private DataBlob dataBlob;

    private String adminFilePath;
    private String studentFilePath;
    private String employerFilePath;
    private String professorFilePath;
    private String reviewFilePath;
    private String jobPostingFilePath;

    private static final JSONParser parser = new JSONParser();

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
