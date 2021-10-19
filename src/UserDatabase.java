import java.util.ArrayList;
public class UserDatabase {
  
    private UserDatabase userDatabase;
    private ArrayList<User> users;
    private ArrayList<User> removedUsers;

    private UserDatabase() {
        users = new ArrayList<User>();
        removedUsers = new ArrayList<User>();
    }

    public UserDatabase getInstance() {
        userDatabase = new UserDatabase();
        return userDatabase;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        // add user to databse
    }

}

