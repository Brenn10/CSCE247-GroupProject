import java.util.ArrayList;
import java.util.Scanner;

public class JobSystem {
    //100% have unneeded methods in here
    private static User currentUser; // admin, employeer, student
    private ArrayList<User> users; // for getting current user (login)

    // 0 unapproved users, 1 prof, 2 student, 3 employer, 4 admin
    private static int userVerify; 
    
    public JobSystem () {
        Database.getInstance()
                .setDataReader(new JsonDataReader(
                        "data/Administrators.json",
                        "data/Students.json", 
                        "data/Employers.json",
                        "data/Professors.json",
                        "data/Reviews.json",
                        "data/JobPostings.json"));
    }

    public static boolean login (String username, String password) {
        //if (UserDatabase.haveUser(username)) {
            //if(Users.matchPassword(username, password)) {
                //currentUser = users.getUser(username);
                // getCurrentVerify();
                //return true;
           // }
        //}
    return false;
    }
    public static boolean signup (String username, String password) {
        // scanner in here to make sure password == confirm password
        Scanner passwordChecker = new Scanner(System.in);

        System.out.println("Please Confirm the Password");

        String password2 = passwordChecker.nextLine();
        if(password2 == password) {
            // what type of profile, email, etc.
            getCurrentVerify();
            return true;
        }
        return false;
    }
    private static void getCurrentVerify(){
        // only call this once the CurrentUser is set
        String userClassList = currentUser.getClass().toString();
        // delete first X indecies to get name of the class (unsure how many)
        userClassList = userClassList.substring(15);


        if(userClassList.equals("Employer")) {
            userVerify = 3;
        }
        if(userClassList.equals("Student")) {
            userVerify = 2;
        }
        if(userClassList.equals("Professor")) {
            userVerify = 1;
        }
        if(userClassList.equals("Admin")) {
            userVerify = 4;
        }
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void addPosting() {
        // add the posting
    }
    public void editPosting() {
        
    }
    public void deletePosting() {
        // example of how I think the numbers will work:
        if (!(userVerify >= 3)) return;
        // only Admin and Employeers can edit postings
           
    }
    public void viewPosting() {
        // show posting normally
        if(userVerify == 3) {
            // show posting with applicants
        }
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
        // display all jobPostings
    }
    public void getSelfReviews() {

    }
    public void rateEmployer() {
        
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
    public int getVerify() {
        return userVerify;
    }
}
