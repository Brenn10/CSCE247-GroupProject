
/**
 * Facilitates all information going to/from the data readers/writers
 * to verify user inputs throughout the program
 * @author Brennan Cain
 */

import dataTypes.User;
import database.Database;
import database.JsonDataReader;
import database.JsonDataWriter;
import database.Database;

/**
 * JobSystem Facade to run the system
 * 
 * @author Trey Sturman
 */
public class JobSystem {
    private static JobSystem instance;

    /**
     * Constructor sets default values
     */
    private JobSystem() {
        Database.getInstance().setDataReader(new JsonDataReader("data/Administrators.json", "data/Students.json",
                "data/Employers.json", "data/Professors.json", "data/Reviews.json", "data/JobPostings.json"));
        Database.getInstance().setDataWriter(new JsonDataWriter("data/Administrators.json", "data/Students.json",
                "data/Employers.json", "data/Professors.json", "data/Reviews.json", "data/JobPostings.json"));
    }

    /**
     * singleton get instance method there will only be one instance
     * 
     * @return the one JobSystem instance
     */
    public static JobSystem getInstance() {
        if (instance == null) {
            instance = new JobSystem();
        }
        return instance;
    }

    /**
     * Allows a user to log in
     * 
     * @param username the user's username
     * @param password the user's password
     * @return the User with that username and password
     */
    public User login(String username, String password) {
        User user = Database.getInstance().findByUsername(username);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    /**
     * Allows a user to sign up
     * 
     * @param user the User who wants to sign up
     * @return if that username is already taken, it will return false
     */
    public boolean signup(User user) {
        if (Database.getInstance().findByUsername(user.getUsername()) == null) {
            Database.getInstance().addUser(user);
            return true;
        }
        return false;
    }

    /**
     * loads the data from the JSON files
     */
    public void loadData() {
        Database.getInstance().loadFromFile();
    }

    /**
     * writes data to the JSON files
     */
    public void saveData() {
        Database.getInstance().writeToFile();
    }
}
