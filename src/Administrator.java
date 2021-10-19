import java.util.ArrayList;

public class Administrator extends User {
    public Administrator(String username, String password, String email) {
         super(username, password, email);
    }
    private boolean isEmailValid(String email) {
        return email.contains("email.sc.edu");
    }
    public void editUser(UserDatabase user, String toChange) {
        // TO-DO 
    }
    public void setJobPosting(Employer employer, JobPosting job) {
        employer.makePosting(job);
    }
    public void removeUser(User user) {
        // TO-DO something with database
    }
    public void removeReviews(Review review) {
        // TO-DO something with database
    }
    public void removeResume(Student student) {
        // TO-DO something with database
    }
    public void removeJobPosting(Employer employer, JobPosting jobposting) {
        // TO-DO something with database
    }
    public void addUser(String userType, ArrayList<String> neededInfo) {
        User toAdd;
        userType = userType.toLowerCase();
        // neededInfo will contain the write information about the new user
        // the first three indeces will always be username, password, email
        if(userType.equals("administrator"))
            toAdd = new Administrator(neededInfo.get(0), neededInfo.get(1), neededInfo.get(2));
        else if(userType.equals("professor"))
            toAdd = new Professor(neededInfo.get(0), neededInfo.get(1), neededInfo.get(2));
        else if(userType.equals("student"))
            toAdd = new Student(neededInfo.get(0), neededInfo.get(1), neededInfo.get(2), neededInfo.get(3)); // index 3 will equal major if student
        else if(userType.equals("employer"))
            toAdd = new Employer(neededInfo.get(0), neededInfo.get(1), neededInfo.get(2), neededInfo.get(3)); // index 3 will eqyal company if student
        // TO-DO something with the database to add the user
    }
    /*public void addEmployer(Employer employer) {
        Employer toADD = employer;
        // add to database 
    }
    public void addResume(Resume resume) {
        //add resume to resume database
    }
    public void addReview(Review review) {
        
    }*/

     public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

     public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}