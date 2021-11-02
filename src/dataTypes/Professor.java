package dataTypes;

import java.util.ArrayList;
import java.util.UUID;
import database.Database;

/**
 * Professor User datatype
 * 
 * @author Ian McDevitt
 */
public class Professor extends User {
    private boolean removed;
    private ArrayList<Student> students;

    /**
     * Constructor
     * 
     * @param id        their unique identifier
     * @param username  the account username
     * @param password  the acoount passowrd
     * @param email     the user's email
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param approved  if the user is approved by admin or not
     * @param removed   if the user has been removed or not
     */
    public Professor(UUID id, String username, String password, String email, String firstName, String lastName,
            boolean approved, boolean removed) {
        super(id, username, password, email, firstName, lastName, approved, removed);
    }

    /**
     * Create a rating for a student
     * 
     * @param student the student they want to rate
     * @param rating  the rating they are giving
     * @param comment a comment about the student
     */
    public void rateStudent(Student student, int rating, String comment) {
        Review reviewToAdd = new Review.Builder().reviewer(this).reviewee(student).rating(rating).comment(comment)
                .build();
        Database.getInstance().addReview(reviewToAdd);
    }

    /**
     * Gets an array of all the student reviews they have left
     * 
     * @param user the reviewer
     * @return ArrayList of reviews
     */
    public ArrayList<Review> getStudentReviewed(User user) {
        ArrayList<Review> reviews = new ArrayList<Review>();
        reviews = Database.getInstance().getReviewsByReviewer(user);
        return reviews;
    }

    /**
     * Get method for all of the students the professor has reviewed
     * 
     * @return ArrayList of students
     */
    public ArrayList<Student> getStudents() {
        return this.students;
    }

    /**
     * Get method for username
     * 
     * @return the String username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Get method for password
     * 
     * @return the String password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Get method for email
     * 
     * @return the String email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set method for username
     * 
     * @param username what we want to set the username to
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set method for password
     * 
     * @param password what we want to set the password to
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set method for email
     * 
     * @param email what we want to set teh email to
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get method for removed
     * 
     * @return boolean if the professor has been removed or not
     */
    public boolean isRemoved() {
        return removed;
    }

    /**
     * Set method for removed
     * 
     * @return the boolean removed we want to set it to
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    /**
     * toString method
     * 
     * @return String we want to print
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getFullName() + "\n");
        sb.append(this.getEmail() + "\n");
        sb.append(this.getUsername() + "\n");
        return sb.toString();
    }

    /**
     * Equals method
     * 
     * @param professor we want to compare to
     * @return true if they are the same user, false otherwise
     */
    public boolean equals(Professor professor) {
        return this.id == professor.getId();
    }

    /**
     * Builder class allows for building a new Professor
     * 
     * @author Brennan Cain
     */
    public static class Builder {
        private UUID id;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private boolean approved;
        private boolean removed;

        /**
         * Constructor sets default values
         */
        public Builder() {
            this.id = UUID.randomUUID();
        }

        /**
         * sets the ID
         * 
         * @param id what we want to set the ID to
         * @return a Builder with that ID
         */
        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        /**
         * sets the username
         * 
         * @param username what we want to set the username to
         * @return a Builder with that username
         */
        public Builder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * sets the password
         * 
         * @param password what we want to set the password to
         * @return a Builder with that password
         */
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * sets the email
         * 
         * @param email what we want to set the email to
         * @return a Builder with that email
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * sets the firstName
         * 
         * @param firstName what we want to set the firstName to
         * @return a Builder with that first name
         */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * sets the lastName
         * 
         * @param lastName what we want to set the lastName to
         * @return a Builder with that lastname
         */
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * sets the approved boolean
         * 
         * @param approved if the user is approved or not
         * @return a Builder with that approval status
         */
        public Builder approved(boolean approved) {
            this.approved = approved;
            return this;
        }

        /**
         * sets the removed boolean
         * 
         * @param removed if the user is removed or not
         * @return a Builder with that removal status
         */
        public Builder removed(boolean removed) {
            this.removed = removed;
            return this;
        }

        /**
         * creates the new admin user
         * 
         * @return a new Admin user
         */
        public Professor build() {
            return new Professor(id, username, password, email, firstName, lastName, approved, removed);
        }
    }
}