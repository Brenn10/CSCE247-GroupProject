package database;
import java.util.ArrayList;

import dataTypes.Student;
import dataTypes.Employer;
import dataTypes.Professor;
import dataTypes.Admin;
import dataTypes.User;
import utilities.Logger;
public class UserDatabase {
    private static UserDatabase userDatabase;

    private UserDatabase() {
    }

    public static UserDatabase getInstance() {
        if(userDatabase == null)
            userDatabase = new UserDatabase();
        return userDatabase;
    }

    public ArrayList<User> getUsers() {
        return Database.getInstance().getUsers();
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        for(User user : Database.getInstance().getUsers()) {
            if(user instanceof Student) {
                Student student = (Student) user;
                if(!student.isRemoved())
                    students.add(student);
            }
        }
        return students;
    }

    public ArrayList<Employer> getEmployers() {
        ArrayList<Employer> employers= new ArrayList<Employer>();
        for(User user : Database.getInstance().getUsers()) {
            if(user instanceof Employer) {
                Employer employer = (Employer) user;
                if(!employer.isRemoved())
                    employers.add(employer);
            }
        }
        return employers;
    }

    public ArrayList<Professor> getProfessor() {
        ArrayList<Professor> professors= new ArrayList<Professor>();
        for(User user : Database.getInstance().getUsers()) {
            if(user instanceof Professor) {
                Professor professor = (Professor) user;
                if(!professor.isRemoved())
                    professors.add(professor);
            }
        }
        return professors;
    }

    public ArrayList<Admin> getAdmin() {
        ArrayList<Admin> admins= new ArrayList<Admin>();
        for(User user : Database.getInstance().getUsers()) {
            if(user instanceof Admin) {
                Admin admin = (Admin) user;
                // if(!admin.isRemoved()) TODO i dont know if we need this, but admin does not have an isRemoved method yet
                    admins.add(admin);
            }
        }
        return admins;
    }

    public ArrayList<User> getUnapprovedUsers() {
        ArrayList<User> unapprovedUsers = new ArrayList<User>();
        for(User user: Database.getInstance().getUsers()) {
            if(!user.isApproved())
                unapprovedUsers.add(user);
        }
        return unapprovedUsers;
    }

    public void removeUser(User user) {
        user.setRemoved(true);
        Database.getInstance().writeToFileUsers();
    }


    public ArrayList<User> getRemovedUsers() {
        ArrayList<User> removedUsers = new ArrayList<User>();
        for(User user: Database.getInstance().getUsers()) {
            if(user.isRemoved())
                removedUsers.add(user);
        }
        return removedUsers;
    }

    public void updateDatabase() {
        Database.getInstance().writeToFileUsers();
    }
    
    public void addUser(User user) {
        Database.getInstance().getUsers().add(user);
        Database.getInstance().writeToFileUsers();
        //this should work if you change the database file writeToFile to take postings as its only argument.
        //check Database.java
        // add user to databse
    }

    public User findByUsername(String username) {
        for(User user : Database.getInstance().getUsers()) {
            Logger.getInstance().log("UserDatabase.findByUsername: " + user.getUsername());
            if(user.getUsername().equalsIgnoreCase(username)) {
                Logger.getInstance().log("UserDatabase.findByUsername: " + user.getUsername() + " found");
                return user;
            }
        }
        return null;
    }
    public User findByName(String name) {
        for(User user : Database.getInstance().getUsers()) {
            if(user.getFullName().equalsIgnoreCase(name))
                return user;
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for(User user : Database.getInstance().getUsers()) {
            if(user.getEmail().equalsIgnoreCase(email))
                return user;
        }
        return null;
    }
}

