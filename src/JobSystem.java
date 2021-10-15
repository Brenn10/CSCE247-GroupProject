import java.util.ArrayList;

public class JobSystem {
    private static User currentUser; // admin, employeer, student
    private ArrayList<User> users; // for getting current user (login)

    // 0 unapproved users, 1 prof, 2 student, 3 employer, 4 admin (easy to check)
    private static int userVerify; 
    
    public JobSystem () {
        // update vars from json files 
    }

    public boolean login (String username, String password) {
        // depends on how users/user will function 
        if (users.haveUser(username)) {
            if(users.matchPassword(username, password)) {
                currentUser = users.getUser(username);
                userVerify = currentUser.getVerify;
                return true;
            }
        }
        return false;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void createAccount() {
        User newuser = User();
        currentUser = newUser;
        return;
    }
    public void addPosting() {
        
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
    public void rateStudent() {
        
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
}
