package dataTypes;
import java.util.UUID;

import database.JobPostingDatabase;
import database.ReviewDatabase;
import database.UserDatabase;

public class Admin extends User {
    public Admin(UUID id,
                         String username, 
                         String password, 
                         String email,
                         String firstName,
                         String lastName,
                         boolean approved) {
         super(id, username, password, email, firstName, lastName, approved, false);
    }

    public void setJobPosting(Employer employer, JobPosting job) {
        employer.makePosting(job);
    }
    public void removeUser(User user) {
        UserDatabase.getInstance().removeUser(user);
    }
    public void removeReviews(Review review) {
        ReviewDatabase.getInstance().removeReveiw(review);
        // TODO something with database
    }
    public void removeResume(Student student) {
        // TODO something with database
    }
    public void removeJobPosting(Employer employer, JobPosting jobposting) {
        JobPostingDatabase.getInstance().removePosting(jobposting);
    }
    public void addUser(User user) {
        UserDatabase.getInstance().addUser(user);
    }

    public String toString() {
        return "@" + this.username + ":/n" + this.firstName + " " + this.lastName + "/n" 
        + " email: " + this.email;
    }

    public static class Builder {
        private UUID id;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private boolean approved;

        public Builder() {
            id = UUID.randomUUID();
            username = "";
            password = "";
            email = "";
            firstName = "";
            lastName = "";
            approved = false;
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder approved(boolean approved) {
            this.approved = approved;
            return this;
        }

        public Admin build() {
            return new Admin(id, username, password, email, firstName, lastName, approved);
        }
    }
}