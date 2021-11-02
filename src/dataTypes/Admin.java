package dataTypes;

import java.util.UUID;

import database.Database;

/**
 * Admin User dataType type of User
 * 
 * @author Stella Garcia and Ian McDevitt
 */
public class Admin extends User {
    /**
     * Constructor
     * 
     * @param id        the unique identifier
     * @param username  the account username
     * @param password  the account password
     * @param email     the user's email
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param approved  if the user has been approved or not (always true for admin,
     *                  as an admin has to create another admin account)
     */
    public Admin(UUID id, String username, String password, String email, String firstName, String lastName,
            boolean approved) {
        super(id, username, password, email, firstName, lastName, approved, false);
    }

    /**
     * Creates a new job posting
     * 
     * @param employer the employer in which the job is connected to
     * @param job      the job that we want to post
     */
    public void setJobPosting(Employer employer, JobPosting job) {
        employer.makePosting(job);
    }

    /**
     * Remove a User
     * 
     * @param user the user account we want to remomve
     */
    public void removeUser(User user) {
        Database.getInstance().removeUser(user);
    }

    /**
     * Remove a rewiew
     * 
     * @param review the review we want to remove
     */
    public void removeReviews(Review review) {
        Database.getInstance().removeReveiw(review);
    }

    /**
     * Remove a resume
     * 
     * @param student the student whose resume we want to delete
     */
    public void removeResume(Student student) {
        student.setCreated(false);
    }

    /**
     * Remove a job posting
     * 
     * @param jobposting the posting to be deleted
     */
    public void removeJobPosting(JobPosting jobposting) {
        Database.getInstance().removePosting(jobposting);
    }

    /**
     * Add a user
     * 
     * @param user the user we want to add
     */
    public void addUser(User user) {
        Database.getInstance().addUser(user);
    }

    /**
     * toString method when we want to print an admin type
     * 
     * @return the String to print
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getFullName() + "\n");
        sb.append(this.getEmail() + "\n");
        sb.append(this.getUsername() + "\n");
        return sb.toString();
    }

    /**
     * Compares to admin users for equality
     * 
     * @param admin the admin to compare to
     * @return true or false depending on if they are equal or not
     */
    public boolean equals(Admin admin) {
        return this.id == admin.getId(); // ID's are unique to each user, so its the only thing we have to compare
    }

    /**
     * Builder class allows for building a new admin
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

        /**
         * Constructor sets all of the values to their default
         */
        public Builder() {
            id = UUID.randomUUID();
            username = "";
            password = "";
            email = "";
            firstName = "";
            lastName = "";
            approved = false;
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
         * creates the new admin user
         * 
         * @return a new Admin user
         */
        public Admin build() {
            return new Admin(id, username, password, email, firstName, lastName, approved);
        }
    }
}