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
        writeReviews(reviews);
        writeJobPostings(postings);
    }
    
    private void writeReviews(ArrayList<Review> reviews) {
        JSONArray jsonReviews = new JSONArray();
        for (Review review : reviews) {
            jsonReviews.add(jsonify(review));
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

    private void writeJobPostings(ArrayList<JobPosting> postings) {
        JSONArray jsonPostings = new JSONArray();
        for (JobPosting posting : postings) {
            jsonPostings.add(jsonify(posting));
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

        FileWriter adminWriter = null, studentWriter = null, employerWriter = null, professorWriter = null;
        try {
            adminWriter = new FileWriter(adminFilePath);
            adminWriter.write(adminArray.toJSONString());

            studentWriter = new FileWriter(studentFilePath);
            studentWriter.write(studentArray.toJSONString());

            employerWriter = new FileWriter(employerFilePath);
            employerWriter.write(employerArray.toJSONString());

            professorWriter = new FileWriter(professorFilePath);
            professorWriter.write(professorArray.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (adminWriter != null) {
                try {
                    adminWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (studentWriter != null) {
                try {
                    studentWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (employerWriter != null) {
                try {
                    employerWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (adminWriter != null) {
                try {
                    adminWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private JSONObject jsonify(Student student) { //TODO: implement
        JSONObject studentJson = new JSONObject();
        studentJson.put(JsonDataLabels.USER_ID.toString(), student.getId());
        studentJson.put(JsonDataLabels.USER_USERNAME, student.getUsername());
        studentJson.put(JsonDataLabels.USER_PASSWORD, student.getPassword());
        studentJson.put(JsonDataLabels.USER_FIRSTNAME, student.getFirstName());
        studentJson.put(JsonDataLabels.USER_LASTNAME, student.getLastName());
        studentJson.put(JsonDataLabels.USER_EMAIL, student.getEmail());
        studentJson.put(JsonDataLabels.USER_APPROVED, student.isApproved());
        studentJson.put(JsonDataLabels.STUDENT_MAJOR, student.getMajor().toString());
        
        studentJson.put(JsonDataLabels.STUDENT_CREATEDRESUME, student.hasCreatedResume());
        studentJson.put(JsonDataLabels.REMOVED, student.isRemoved());
        if(student.hasCreatedResume()) {
            JSONArray jsonEmployments = new JSONArray();
            for (Employment employment : student.getEmployments()) {
                JSONObject jsonEmployment = new JSONObject();
                jsonEmployment.put(JsonDataLabels.STUDENT_EMPLOYMENT_COMPANY, employment.getCompany());
                jsonEmployment.put(JsonDataLabels.STUDENT_EMPLOYMENT_DATES, employment.getDates());
                jsonEmployment.put(JsonDataLabels.STUDENT_EMPLOYMENT_TITLE, employment.getTitle());

                JSONArray details = new JSONArray();
                for (String detail : employment.getDetails()) {
                    details.add(detail);
                }
                jsonEmployment.put(JsonDataLabels.STUDENT_EMPLOYMENT_DETAILS, details);

                jsonEmployments.add(jsonEmployment);
            }
            studentJson.put(JsonDataLabels.STUDENT_EMPLOYMENTS, jsonEmployments);
        
        
            JSONArray jsonEducations = new JSONArray();
            for (Education education : student.getEducations()) {
                JSONObject jsonEducation = new JSONObject();
                jsonEducation.put(JsonDataLabels.STUDENT_EDUCATION_GPA, education.getGpa());
                jsonEducation.put(JsonDataLabels.STUDENT_EDUCATION_GRADDATE, education.getGradDate());
                jsonEducation.put(JsonDataLabels.STUDENT_EDUCATION_PLACE, education.getPlace());

                jsonEducations.add(jsonEducation);
            }
            studentJson.put(JsonDataLabels.STUDENT_EDUCATIONS.toString(), jsonEducations);
        }
        
        return studentJson;
    }

    private JSONObject jsonify(Employer employer) {
        JSONObject employerJson = new JSONObject();
        employerJson.put(JsonDataLabels.USER_ID, employer.getId());
        employerJson.put(JsonDataLabels.USER_USERNAME, employer.getUsername());
        employerJson.put(JsonDataLabels.USER_PASSWORD, employer.getPassword());
        employerJson.put(JsonDataLabels.USER_FIRSTNAME, employer.getFirstName());
        employerJson.put(JsonDataLabels.USER_LASTNAME, employer.getLastName());
        employerJson.put(JsonDataLabels.USER_EMAIL, employer.getEmail());
        employerJson.put(JsonDataLabels.USER_APPROVED, employer.isApproved());
        employerJson.put(JsonDataLabels.EMPLOYER_COMPANY, employer.getCompany());
        employerJson.put(JsonDataLabels.EMPLOYER_AVERAGERATING, employer.getAverageRating());
        employerJson.put(JsonDataLabels.REMOVED, employer.isRemoved());
        return employerJson;
    }

    private JSONObject jsonify(Professor professor) {
        JSONObject professorJson = new JSONObject();
        professorJson.put(JsonDataLabels.USER_ID, professor.getId());
        professorJson.put(JsonDataLabels.USER_USERNAME, professor.getUsername());
        professorJson.put(JsonDataLabels.USER_PASSWORD, professor.getPassword());
        professorJson.put(JsonDataLabels.USER_FIRSTNAME, professor.getFirstName());
        professorJson.put(JsonDataLabels.USER_LASTNAME, professor.getLastName());
        professorJson.put(JsonDataLabels.USER_EMAIL, professor.getEmail());
        professorJson.put(JsonDataLabels.USER_APPROVED, professor.isApproved());
        professorJson.put(JsonDataLabels.REMOVED, professor.isRemoved());
        return professorJson;
    }

    private JSONObject jsonify(Administrator admin) {
        JSONObject adminJson = new JSONObject();
        adminJson.put(JsonDataLabels.USER_ID, admin.getId().toString());
        adminJson.put(JsonDataLabels.USER_USERNAME, admin.getUsername());
        adminJson.put(JsonDataLabels.USER_PASSWORD, admin.getPassword());
        adminJson.put(JsonDataLabels.USER_FIRSTNAME, admin.getFirstName());
        adminJson.put(JsonDataLabels.USER_LASTNAME, admin.getLastName());
        adminJson.put(JsonDataLabels.USER_EMAIL, admin.getEmail());
        adminJson.put(JsonDataLabels.USER_APPROVED, admin.isApproved());
        return adminJson;
    }

    private JSONObject jsonify(Review review) { //TODO: implement
        JSONObject reviewJson = new JSONObject();

        reviewJson.put(JsonDataLabels.REVIEW_ID, review.getId().toString());
        reviewJson.put(JsonDataLabels.REVIEW_REVIEWEE, review.getReviewee().getId().toString());
        reviewJson.put(JsonDataLabels.REVIEW_REVIEWER, review.getReviewer().getId().toString());
        reviewJson.put(JsonDataLabels.REVIEW_RATING, review.getRating());
        reviewJson.put(JsonDataLabels.REVIEW_COMMENT, review.getComment());
        reviewJson.put(JsonDataLabels.REMOVED, review.isRemoved());
        return reviewJson;
    }

    private JSONObject jsonify(JobPosting posting) { //TODO: implement
        JSONObject postingJson = new JSONObject();

        postingJson.put(JsonDataLabels.JOBPOSTING_ID, posting.getId().toString());
        postingJson.put(JsonDataLabels.JOBPOSTING_EMPLOYER, posting.getEmployer().getId().toString());
        postingJson.put(JsonDataLabels.JOBPOSTING_TITLE, posting.getJobTitle());
        postingJson.put(JsonDataLabels.JOBPOSTING_DESCRIPTION, posting.getDescription());

        JSONArray jsonRequirements = new JSONArray();
        for (String skill : posting.getRequirements()) {
            jsonRequirements.add(skill);
        }
        postingJson.put(JsonDataLabels.JOBPOSTING_REQUIREMENTS, jsonRequirements);

        postingJson.put(JsonDataLabels.JOBPOSTING_HOURLYWAGE, posting.getWage());
        postingJson.put(JsonDataLabels.JOBPOSTING_STATUS, posting.getStatus().toString());

        JSONArray jsonApplicants = new JSONArray();
        for (Student student : posting.getApplicants()) {
            jsonApplicants.add(student.getId().toString());
        }
        postingJson.put(JsonDataLabels.JOBPOSTING_APPLICANTS, jsonApplicants);
        postingJson.put(JsonDataLabels.REMOVED, posting.isRemoved());
        return postingJson;
    }
}
