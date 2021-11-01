package database;

import java.util.ArrayList;

import dataTypes.Student;
import dataTypes.Employer;
import dataTypes.Professor;
import dataTypes.Admin;
import dataTypes.User;
import utilities.Logger;

/**
 * The jobPosting database
 * 
 * @author Stella Garcia, Breannan Cain, Ian McDevitt
 */
public class UserDatabase {
    private static UserDatabase userDatabase; // singleton instance

    /**
     * Constructor The UserDatabase class is a singleton so the constructor is not
     * accessible outside of the class
     */
    private UserDatabase() {
    }

    /**
     * Singleton getInstance method there is only one UserDatabase object
     * 
     * @return UserDatabase object (singleton)
     */
    public static UserDatabase getInstance() {
        if (userDatabase == null)
            userDatabase = new UserDatabase();
        return userDatabase;
    }

    /**
     * Get method for all of the users
     * 
     * @return ArrayList of all Users
     */
    public ArrayList<User> getUsers() {
        return Database.getInstance().getUsers();
    }

    /**
     * Get method for all of the students
     * 
     * @return ArrayList of all student users
     */
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        for (User user : Database.getInstance().getUsers()) {
            if (user instanceof Student) {
                Student student = (Student) user;
                if (!student.isRemoved())
                    students.add(student);
            }
        }
        return students;
    }

    /**
     * Get method for all of the employer
     * 
     * @return ArrayList of all employer users
     */
    public ArrayList<Employer> getEmployers() {
        ArrayList<Employer> employers = new ArrayList<Employer>();
        for (User user : Database.getInstance().getUsers()) {
            if (user instanceof Employer) {
                Employer employer = (Employer) user;
                if (!employer.isRemoved())
                    employers.add(employer);
            }
        }
        return employers;
    }

    /**
     * Get method for all of the professor
     * 
     * @return ArrayList of all professor users
     */
    public ArrayList<Professor> getProfessor() {
        ArrayList<Professor> professors = new ArrayList<Professor>();
        for (User user : Database.getInstance().getUsers()) {
            if (user instanceof Professor) {
                Professor professor = (Professor) user;
                if (!professor.isRemoved())
                    professors.add(professor);
            }
        }
        return professors;
    }

    /**
     * Get method for all of the admins
     * 
     * @return ArrayList of all admin users
     */
    public ArrayList<Admin> getAdmin() {
        ArrayList<Admin> admins = new ArrayList<Admin>();
        for (User user : Database.getInstance().getUsers()) {
            if (user instanceof Admin) {
                Admin admin = (Admin) user;
                admins.add(admin);
            }
        }
        return admins;
    }

    /**
     * Get method for all users unapproved by an admin
     * 
     * @return ArrayList of all unapproved users
     */
    public ArrayList<User> getUnapprovedUsers() {
        ArrayList<User> unapprovedUsers = new ArrayList<User>();
        for (User user : Database.getInstance().getUsers()) {
            if (!user.isApproved())
                unapprovedUsers.add(user);
        }
        return unapprovedUsers;
    }

    /**
     * When a user is to be removed, we set the removed attribute to true and update
     * the JSON file
     * 
     * @param user User to be added
     */
    public void removeUser(User user) {
        user.setRemoved(true);
        Database.getInstance().writeToFileUsers();
    }

    /**
     * Get method for all of the removed
     * 
     * @return ArrayList of all removed users
     */
    public ArrayList<User> getRemovedUsers() {
        ArrayList<User> removedUsers = new ArrayList<User>();
        for (User user : Database.getInstance().getUsers()) {
            if (user.isRemoved())
                removedUsers.add(user);
        }
        return removedUsers;
    }

    /**
     * Updates the database
     */
    public void updateDatabase() {
        Database.getInstance().writeToFileUsers();
    }

    /**
     * When we add a user, we add it to the ArrayList of users in database and then
     * update the JSON file
     * 
     * @param user
     */
    public void addUser(User user) {
        Database.getInstance().getUsers().add(user);
        Database.getInstance().writeToFileUsers();
    }

    /**
     * Searches all users and returns one with a specific username
     * 
     * @param username username in question
     * @return the User with that username
     */
    public User findByUsername(String username) {
        for (User user : Database.getInstance().getUsers()) {
            Logger.getInstance().log("UserDatabase.findByUsername: " + user.getUsername());
            if (user.getUsername().equalsIgnoreCase(username)) {
                Logger.getInstance().log("UserDatabase.findByUsername: " + user.getUsername() + " found");
                return user;
            }
        }
        return null;
    }

    /**
     * Searches all users and returns the one with a specific name
     * 
     * @param name the name in question
     * @return the user with that name
     */
    public User findByName(String name) {
        for (User user : Database.getInstance().getUsers()) {
            if (user.getFullName().equalsIgnoreCase(name))
                return user;
        }
        return null;
    }

    /**
     * Searches all users and returns the one with a specific email
     * 
     * @param email the email in question
     * @return the user with that email
     */
    public User getUserByEmail(String email) {
        for (User user : Database.getInstance().getUsers()) {
            if (user.getEmail().equalsIgnoreCase(email))
                return user;
        }
        return null;
    }
}
