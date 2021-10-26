package dataTypes;
import java.util.ArrayList;
import java.util.UUID;

import database.ReviewDatabase;
import enums.Major;

public class Student extends User {
    private Major major;
    private boolean createdResume;
    private ArrayList<Employment> employments;
    private ArrayList<Education> educations;
    private ArrayList<String> technicalSkills;
    private double averageRating;
    private boolean removed;

    public Student(UUID id,
                   String username, 
                   String password, 
                   String email,
                   String firstName,
                   String lastName,
                   boolean approved,
                   Major major,
                   boolean createdResume,
                   ArrayList<Employment> employments,
                   ArrayList<Education> educations,
                   ArrayList<String> technicalSkills,
                   double averageRating,
                   boolean removed) {
        super(id, username, password, email, firstName, lastName, approved);
        this.major = major;
        this.createdResume = createdResume;
        this.employments = employments;
        this.educations = educations;
        this.technicalSkills = technicalSkills;
        this.averageRating = averageRating;
        this.removed = removed;
    }

    public void reviewEmployer(Employer employer, int score, String comment) {
        Review rating = new Review.Builder().reviewer(this)
                                            .reviewee(employer)
                                            .rating(score)
                                            .comment(comment)
                                            .build();
        ReviewDatabase.getInstance().addReview(rating);
        // TODO something with the database 
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
    public ArrayList<Employment> getEmployments() {
        return this.employments;
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
    public ArrayList<Education> getEducations() {
        return this.educations;
    }

    public Major getMajor() {
        return this.major;
    }
    public boolean hasCreatedResume() {
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

    public void setMajor(Major major) {
        this.major = major;
    }
    public void setCreated(Boolean createdResume) {
        this.createdResume = createdResume;
    }
    public void setReviews(ArrayList<Review> reviews) {

    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public static class Builder {
        private UUID id;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private boolean approved;
        private Major major;
        private boolean createdResume;
        private ArrayList<Employment> employments;
        private ArrayList<Education> educations;
        private ArrayList<String> technicalSkills;
        private double averageRating;
        private boolean removed;

        public Builder() {
            id = UUID.randomUUID();
            major = Major.NA;
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

        public Builder major(Major major) {
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

        public Builder removed(boolean removed) {
            this.removed = removed;
            return this;
        }

        public Student build() {
            return new Student(id, username, password, email, firstName, lastName, approved, major, createdResume, employments, educations, technicalSkills, averageRating, removed);
        }
    }
}