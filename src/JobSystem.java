import dataTypes.User;
import database.Database;
import database.JsonDataReader;
import database.JsonDataWriter;
import database.UserDatabase;

public class JobSystem {
    private static JobSystem instance;

    private JobSystem () {
        Database.getInstance()
                .setDataReader(new JsonDataReader(
                        "data/Administrators.json",
                        "data/Students.json", 
                        "data/Employers.json",
                        "data/Professors.json",
                        "data/Reviews.json",
                        "data/JobPostings.json"));
        Database.getInstance()  
                .setDataWriter(new JsonDataWriter(
                        "data/Administrators.json",
                        "data/Students.json", 
                        "data/Employers.json",
                        "data/Professors.json",
                        "data/Reviews.json",
                        "data/JobPostings.json"));
    }

    public static JobSystem getInstance() {
        if (instance == null) {
            instance = new JobSystem();
        }
        return instance;    
    }


    public User login (String username, String password) {
        User user = UserDatabase.getInstance().findByUsername(username);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean signup(User user) {
        if (UserDatabase.getInstance().findByUsername(user.getUsername()) == null) {
            // TODO ask user for other inputs. ex. what type of user, email, etc.
            UserDatabase.getInstance().addUser(user);
            return true;
        }
        return false;
    }

    // String description, ArrayList<String> requirements, 
    // double hourlyWage, String status
    public void addPosting() {
        // add the posting
        
    }
    public void editPosting() {
        
    }
    public void deletePosting() {
           
    }

    public boolean rateStudent(String studentName) {
        // return false if couldn't find student
        boolean studentFound  = false;
        
        return studentFound;
    }
    public void showAllStudent() {
        // display all students
    }
    public void showAllJobs() {
        // display all jobPostings for current user
    }
    public void getSelfReviews() {
        // show all reviews attatched to current User
    }
    public void rateEmployer() {
        // add review to employer
    }
    public void addEmployement() {
        
    }
    public void removeEmployment() {
        
    }
    public void addSkill() {
        
    }
    public void editSkill() {
        
    }
    public void removeSkill() {
        
    }
    public void createResume() {
        /**
         * because student can edit things that go in resume
         * skills, employment, education, etc.
         * and because only one resume per student
         * create resume should be called instead of a "editResume"- at least in here
         */

    }
    public void removeResume() {
        
    }
    public void applyPosting() {
        
    }
    public void viewUnnaprovedAccount() {
        
    }
    public void approveAccount() {
        
    }
    public void removeAccount() {
        
    }
}
