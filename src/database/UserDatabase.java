package database;
import java.util.ArrayList;

import dataTypes.Student;
import dataTypes.Employer;
import dataTypes.Professor;
import dataTypes.Admin;
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

    public ArrayList<Employer> getEmployers() {
        ArrayList<Employer> employers= new ArrayList<Employer>();
        for(User user : users) {
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
        for(User user : users) {
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
        for(User user : users) {
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
        for(User user: users) {
            if(!user.isApproved())
                unapprovedUsers.add(user);
        }
        return unapprovedUsers;
    }

    public void removeUser(User user) {
        removedUsers.add(user);
        user.setRemoved(true);
        Database.getInstance().writeToFileUsers(users);
    }


    public ArrayList<User> getRemovedUsers() {
        return this.removedUsers;
    }

    public void addUser(User user) {
        users.add(user);
        Database.getInstance().writeToFileUsers(users);
        //this should work if you change the database file writeToFile to take postings as its only argument.
        //check Database.java
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

