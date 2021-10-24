import java.util.ArrayList;
import java.util.UUID;

public class Professor extends User {

    public Professor(UUID id, 
            String username, 
            String password,
            String email, 
            String firstName,
            String lastName,
            boolean approved) {
        super(id, username, password, email, firstName, lastName, approved);
    }
   
    public void rateStudent(Student student, int rating, String comment) {
        Review reviewToAdd = new Review(this, student, rating, comment);
        ReviewDatabase.getInstance().addReview(reviewToAdd); 
    }

    public ArrayList<Student> getStudentReviewed() {
        //TODO access all of the students reviewed by this professor
        return null;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
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
            this.id = UUID.randomUUID();
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

        public Professor build() {
            return new Professor(id, username, password, email, firstName, lastName, approved);
        }
    }
}