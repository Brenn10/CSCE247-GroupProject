import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {
    private String major;
    private boolean createdResume;
    private ArrayList<Employment> employments;
    private ArrayList<Education> educations;
    private ArrayList<String> technicalSkills;
    private double averageRating;

    public Student(UUID id,
                   String username, 
                   String password, 
                   String email,
                   String firstName,
                   String lastName,
                   boolean approved,
                   String major,
                   boolean createdResume,
                   ArrayList<Employment> employments,
                   ArrayList<Education> educations,
                   ArrayList<String> technicalSkills,
                   double averageRating) {
        super(id, username, password, email, firstName, lastName, approved);
        this.major = major;
        this.createdResume = createdResume;
        this.employments = employments;
        this.educations = educations;
        this.technicalSkills = technicalSkills;
        this.averageRating = averageRating;
    }

    public void reviewEmployer(double rating) {
        //TODO database
    }
    public void apply(JobPosting job) {
        job.addApplicant(this);
    }
    public void addEmployment(Employment employment) {
        this.employments.add(employment);
    }
    public void editEmployment(ArrayList<Employment> employment) {
        this.employments = employment;
    }
    public void changeTechnicalSkills(ArrayList<String> technicalSkills) {
        this.technicalSkills = technicalSkills;
    }
    public void addTechincalSkill(String skill) {
        this.technicalSkills.add(skill);
    }
    public void removeTechincalSkill(String skill) {
        this.technicalSkills.remove(skill);
    }
    public void addEducation(Education education) {
        this.educations.add(education);
    }
    public void removeEducation(Education education) {
        this.educations.remove(education);
    }
    // get and set methods 

    public String getMajor() {
        return this.major;
    }
    public boolean getCreated() {
        return this.createdResume;
    }
    public ArrayList<Review> getReviews() {
        return null;
    }
    public double getAverageReview() {
        return averageRating;
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

    public void setMajor(String major) {
        this.major = major;
    }
    public void setCreated(Boolean createdResume) {
        this.createdResume = createdResume;
    }
    public void setReviews(ArrayList<Review> reviews) {

    }

    public static class Builder {
        private UUID id;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private boolean approved;
        private String major;
        private boolean createdResume;
        private ArrayList<Employment> employments;
        private ArrayList<Education> educations;
        private ArrayList<String> technicalSkills;
        private double averageRating;

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

        public Builder major(String major) {
            this.major = major;
            return this;
        }

        public Builder createdResume(boolean createdResume) {
            this.createdResume = createdResume;
            return this;
        }

        public Builder employments(ArrayList<Employment> employments) {
            this.employments = employments;
            return this;
        }

        public Builder educations(ArrayList<Education> educations) {
            this.educations = educations;
            return this;
        }

        public Builder technicalSkills(ArrayList<String> technicalSkills) {
            this.technicalSkills = technicalSkills;
            return this;
        }

        public Builder averageRating(double averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        public Student build() {
            return new Student(id, username, password, email, firstName, lastName, approved, major, createdResume, employments, educations, technicalSkills, averageRating);
        }
    }
}