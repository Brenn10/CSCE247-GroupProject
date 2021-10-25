import java.util.ArrayList;
public class UserDatabase {
  
    private static UserDatabase userDatabase;
    private ArrayList<User> users;
    private ArrayList<User> removedUsers;

    private UserDatabase() {
        users = new ArrayList<User>();
        removedUsers = new ArrayList<User>();
    }

    public static UserDatabase getInstance() {
        if(userDatabase == null)
            userDatabase = new UserDatabase();
        return userDatabase;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<User> getRemovedUsers() {
        return this.removedUsers;
    }

    public void addUser(User user) {
        users.add(user);
        // add user to databse
    }

}

