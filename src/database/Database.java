package database;

import java.util.ArrayList;

import dataTypes.Admin;
import dataTypes.DataBlob;
import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Professor;
import dataTypes.Review;
import dataTypes.Student;
import dataTypes.User;
import utilities.Logger;

/**
 * The overall database that allows for data reading and writing
 * 
 * @author Brennan Cain
 */
public class Database {
    private static Database instance = null; // singleton

    DataReader reader;
    DataWriter writer;

    private ArrayList<User> users;
    private ArrayList<Review> reviews;
    private ArrayList<JobPosting> postings;

    /**
     * Constructor The Database class is a singleton so the constructor is not
     * accessible outside of the class
     */
    private Database() {
    }

    /**
     * Singleton getInstance method there is only one Database object
     * 
     * @return Database object (singleton)
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Set the Database reader attribute
     * 
     * @param reader set the attribute this.reader to reader
     */
    public void setDataReader(DataReader reader) {
        this.reader = reader;
    }

    /**
     * Set the Database writer attribute
     * 
     * @param writer set the attribute this.writer to writer
     */
    public void setDataWriter(DataWriter writer) {
        this.writer = writer;
    }

    /**
     * Reads all of the different data we have stored in JSON files and puts them in
     * arrays
     */
    public void loadFromFile() {
        DataBlob blob = reader.read();
        users = blob.getUsers();
        reviews = blob.getReviews();
        postings = blob.getJobPostings();
    }

    /**
     * updates all of the JSON files
     */
    public void writeToFile() {
        writer.write(users, reviews, postings);
    }

    /**
     * Get method for jobPostings
     * 
     * @return an ArrayList with all of the postings in the database
     */
    public ArrayList<JobPosting> getJobPostings() {
        return postings;
    }

    /**
     * When a new posting is added, we add it to the Database's ArrayList and update
     * the JSON
     * 
     * @param posting the posting to be added
     */
    public void addPosting(JobPosting posting) {
        postings.add(posting);
    }

    /**
     * When a posting is to be removed, we set it's removed attribute to true and
     * update the JSON
     * 
     * @param posting the posting to be removed
     */
    public void removePosting(JobPosting posting) {
        posting.setRemoved(true);
    }

    /**
     * Get method for all of the postings
     * 
     * @return ArrayList of all of the postings
     */
    public ArrayList<JobPosting> getPostings() {
        ArrayList<JobPosting> postings = Database.getInstance().getJobPostings();
        ArrayList<JobPosting> toReturn = new ArrayList<JobPosting>();
        for (JobPosting posting : postings)
            if (!posting.isRemoved())
                toReturn.add(posting);
        return toReturn;
    }

    /**
     * Get method for all of the removed postings
     * 
     * @return ArrayList of all of the removed postings
     */
    public ArrayList<JobPosting> getRemovedPostings() {
        ArrayList<JobPosting> removedPostings = new ArrayList<JobPosting>();
        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (posting.isRemoved())
                removedPostings.add(posting);
        }
        return removedPostings;
    }

    /**
     * Get method for a specific posting
     * 
     * @param employerUser The employer user who made the post
     * @param title        The title of the post
     * @return JobPosting the specific post we are looking for
     */
    public JobPosting getPostingByEmployerAndTitle(String employerUser, String title) {

        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (posting.getEmployer().getUsername().equals(employerUser) && posting.getJobTitle().equals(title))
                return posting;
        }
        return null;
    }

    /**
     * Gets all of the postings a specific student user has applied to
     * 
     * @param student the student in question
     * @return ArrayList of all of the jobs they applied for
     */
    public ArrayList<JobPosting> getPostingsByStudent(Student student) {
        ArrayList<JobPosting> applications = new ArrayList<JobPosting>();
        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (posting.getApplicants().contains(student) && !posting.isRemoved())
                applications.add(posting);
        }
        return applications;
    }

    /**
     * Gets all of the postings made by a specifc employer users
     * 
     * @param employer the employer in question
     * @return ArrayList of all of the posts
     */
    public ArrayList<JobPosting> getPostingsByEmployer(Employer employer) {
        ArrayList<JobPosting> returnPostings = new ArrayList<JobPosting>();
        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (posting.getEmployer().equals(employer))
                ; // TODO implement equals
            returnPostings.add(posting);
        }
        return returnPostings;
    }

    /**
     * get method for all of the postings that are still open
     * 
     * @return ArrayList of all open postings
     */
    public ArrayList<JobPosting> getOpenPostings() {
        ArrayList<JobPosting> openPostings = new ArrayList<JobPosting>();
        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (!posting.isRemoved())
                openPostings.add(posting);
        }
        return openPostings;
    }

    /**
     * Searches all of the jobs with a specific requirement
     * 
     * @param requirement the requirement they are looking for
     * @return ArrayList of all jobs with that requirement
     * @author Brennan Cain
     */
    public ArrayList<JobPosting> getOpenPostingByRequirement(String requirement) {
        ArrayList<JobPosting> openPostings = new ArrayList<JobPosting>();
        for (JobPosting posting : Database.getInstance().getJobPostings()) {
            if (!posting.isRemoved()) {
                for (String postDetail : posting.getRequirements()) {
                    if (postDetail.toLowerCase().contains(requirement.toLowerCase())) {
                        openPostings.add(posting);
                        break;
                    }
                }
            }
        }
        return openPostings;
    }

    /**
     * When a new review is added, we add it to the Database's ArrayList and update
     * the JSON file
     * 
     * @param review the review to be added
     */
    public void addReview(Review review) {
        Database.getInstance().getReviews().add(review);
    }

    /**
     * When a review is to be removed, we set it's removed attribute to true and
     * update the JSON
     * 
     * @param posting the review to be removed
     */
    public void removeReveiw(Review review) {
        review.setRemoved(true);
    }

    /**
     * Get method for all of the reviews
     * 
     * @return ArrayList of all Reviews
     */
    public ArrayList<Review> getReviews() {
        ArrayList<Review> reviews = Database.getInstance().getReviews();
        ArrayList<Review> toReturn = new ArrayList<Review>();
        for (Review review : reviews)
            if (!review.isRemoved())
                toReturn.add(review);
        return toReturn;
    }

    /**
     * Get method for all of the removed Reviews
     * 
     * @return ArrayList of all removed reviews
     */
    public ArrayList<Review> getRemovedReviews() {
        ArrayList<Review> removedReviews = new ArrayList<>();
        for (Review review : Database.getInstance().getReviews()) {
            if (review.isRemoved())
                removedReviews.add(review);
        }
        return removedReviews;
    }

    /**
     * gets a single review made by a user
     * 
     * @param user the user in question
     * @return the review made by said user
     */
    public Review getReviewByReviewer(User user) {
        for (Review review : Database.getInstance().getReviews()) {
            if (review.getReviewer().getUsername().equals(user.getUsername()))
                return review;
        }
        return null;
    }

    /**
     * gets a single review of a user
     * 
     * @param user the user in question
     * @return the review of said user
     */
    public Review getReviewByReviewee(User user) {
        for (Review review : Database.getInstance().getReviews()) {
            if (review.getReviewee().getUsername().equals(user.getUsername()))
                return review;
        }
        return null;
    }

    /**
     * Gets a specific review by a user about another user
     * 
     * @param reviewerUser the user who reviews
     * @param revieweeUser the user reviewed
     * @return The review made by reviewer about reviewee
     */
    public Review getReviewByRevieweeAndReviewer(String reviewerUser, String revieweeUser) {
        for (Review review : Database.getInstance().getReviews()) {
            if (review.getReviewer().getUsername().equals(reviewerUser)
                    && review.getReviewee().getUsername().equals(revieweeUser))
                return review;
        }
        return null;
    }

    /**
     * Gets all reviews about one specifc user
     * 
     * @param user the user in question
     * @return ArrayList of all the reviews
     */
    public ArrayList<Review> getReviewsByReviewee(User user) {
        ArrayList<Review> reviewsByReviewee = new ArrayList<Review>();
        for (Review review : Database.getInstance().getReviews()) {
            if (review.getReviewee().equals(user) && !review.isRemoved())
                reviewsByReviewee.add(review);
        }
        return reviewsByReviewee;
    }

    /**
     * Gets all reviews made by one specifc user
     * 
     * @param user the user in question
     * @return ArrayList of all the reviews
     */
    public ArrayList<Review> getReviewsByReviewer(User user) {
        ArrayList<Review> reviewsByReviewer = new ArrayList<Review>();
        for (Review review : Database.getInstance().getReviews()) {
            if (review.getReviewer().equals(user) && !review.isRemoved())
                reviewsByReviewer.add(review);
        }
        return reviewsByReviewer;
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
     * When we add a user, we add it to the ArrayList of users in database and then
     * update the JSON file
     * 
     * @param user
     */
    public void addUser(User user) {
        Database.getInstance().getUsers().add(user);
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
