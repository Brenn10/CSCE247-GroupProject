package database;
import java.util.ArrayList;

import dataTypes.Student;
import dataTypes.User;
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

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        for(User user : users) {
            if(user instanceof Student) {
                Student student = (Student) user;
                if(!student.isRemoved())
                    students.add(student);
            }
        }
        return students;
    }


    public ArrayList<User> getRemovedUsers() {
        return this.removedUsers;
    }

    public void addUser(User user) {
        users.add(user);
        // add user to databse
    }

    public User findByUsername(String username) {
        for(User user : users) {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }
}

