//import java.util.ArrayList;
import java.util.UUID;

public class Administrator extends User {
    public Administrator(UUID id,
                         String username, 
                         String password, 
                         String email,
                         String firstName,
                         String lastName,
                         boolean approved) {
         super(id, username, password, email, firstName, lastName, approved);
    }

    /*public void editUser(UserDatabase user, String toChange) {
        // TO-DO 
    }*/
    public void setJobPosting(Employer employer, JobPosting job) {
        employer.makePosting(job);
    }
    public void removeUser(User user) {
        UserDatabase.getInstance().getUsers().remove(user);
        UserDatabase.getInstance().getRemovedUsers().add(user);
        // TO-DO something with database
    }
    public void removeReviews(Review review) {
        ReviewDatabase.getInstance().removeReveiw(review);
        // TO-DO something with database
    }
    public void removeResume(Student student) {
        // TO-DO something with database
    }
    public void removeJobPosting(Employer employer, JobPosting jobposting) {
        JobPostingDatabase.getInstance().getPostings().remove(jobposting);
        JobPostingDatabase.getInstance().getRemovedPostings().add(jobposting);
        // TO-DO something with database
    }
    public void addUser(User user) {
        UserDatabase.getInstance().getUsers().add(user);
    }
    /*public void addEmployer(Employer employer) {
        Employer toADD = employer;
        // add to database 
    }
    public void addResume(Resume resume) {
        //add resume to resume database
    }
    public void addReview(Review review) {
        
    }*/

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

        public Administrator build() {
            return new Administrator(id, username, password, email, firstName, lastName, approved);
        }
    }
}