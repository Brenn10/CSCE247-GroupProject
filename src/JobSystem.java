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
        User user = Database.getInstance().findByUsername(username);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean signup(User user) {
        if (Database.getInstance().findByUsername(user.getUsername()) == null) {
            // TODO ask user for other inputs. ex. what type of user, email, etc.
            Database.getInstance().addUser(user);
            return true;
        }
        return false;
    }

    public void loadData() {
        Database.getInstance().loadFromFile();
    }

    public void saveData() {
        Database.getInstance().writeToFile();
    }
}
