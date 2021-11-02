/**
 * Type of user that holds all information a student would have
 * such as an average rating, major, and technical skills
 * @author Ian McDevitt
 */
package dataTypes;

import java.util.ArrayList;
import java.util.UUID;

import database.Database;
import enums.Major;

public class Student extends User {
    private Major major;
    private boolean createdResume;
    private ArrayList<Employment> employments;
    private ArrayList<Education> educations;
    private ArrayList<String> technicalSkills;
    private double averageRating;
    private boolean removed;

    public Student(UUID id, String username, String password, String email, String firstName, String lastName,
            boolean approved, Major major, boolean createdResume, ArrayList<Employment> employments,
            ArrayList<Education> educations, ArrayList<String> technicalSkills, double averageRating, boolean removed) {
        super(id, username, password, email, firstName, lastName, approved, removed);
        this.major = major;
        this.createdResume = createdResume;
        this.employments = employments;
        this.educations = educations;
        this.technicalSkills = technicalSkills;
        this.averageRating = averageRating;
    }
    /**
     * Creates a review for target employer, with a score and a comment
     * @param employer
     * @param score
     * @param comment
     */
    public void reviewEmployer(Employer employer, int score, String comment) {
        Review rating = new Review.Builder().reviewer(this).reviewee(employer).rating(score).comment(comment).build();
        Database.getInstance().addReview(rating);
    }
    /**
     * Applies self to target job
     * @param job
     */
    public void apply(JobPosting job) {
        job.addApplicant(this);
    }
    /**
     * Adds employment to self
     * @param employment
     */
    public void addEmployment(Employment employment) {
        this.employments.add(employment);
    }
    /**
     * Exchanges list of employments with another list of employments
     * @param employment
     */
    public void editEmployment(ArrayList<Employment> employment) {
        this.employments = employment;
    }
    /**
     * Gets list of employments
     * @return employments
     */
    public ArrayList<Employment> getEmployments() {
        return this.employments;
    }
    /**
     * Exchanges list of technical skills with another list of skills
     * @param technicalSkills
     */
    public void changeTechnicalSkills(ArrayList<String> technicalSkills) {
        this.technicalSkills = technicalSkills;
    }
    /**
     * Adds a technical skill to the list of technical skills
     * @param skill
     */
    public void addTechincalSkill(String skill) {
        this.technicalSkills.add(skill);
    }
    /**
     * Removes a technical skill from the list of technical skills
     * @param skill
     */
    public void removeTechincalSkill(String skill) {
        this.technicalSkills.remove(skill);
    }
    /**
     * Adds an eductaion to the list of education
     * @param education
     */
    public void addEducation(Education education) {
        this.educations.add(education);
    }
    /**
     * Removes an eductaion from the list of educations
     * @param education
     */
    public void removeEducation(Education education) {
        this.educations.remove(education);
    }
    /**
     * Gets the list of educations
     * @return
     */
    public ArrayList<Education> getEducations() {
        return this.educations;
    }
    /**
     * Gets the student's major
     * @return major
     */
    public Major getMajor() {
        return this.major;
    }
    /**
     * Gets if the student has creates a resume
     * @return if the student has created a resume
     */
    public boolean hasCreatedResume() {
        return this.createdResume;
    }
    /**
     * Gets the list of reviews attatched to this student
     * @return null
     */
    public ArrayList<Review> getReviews() {
        return null;
    }
    /**
     * Gets the average rating of the student
     * @return average rating
     */
    public double getAverageReview() {
        return averageRating;
    }
    /**
     * @return username
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * @return password
     */
    public String getPassword() {
        return this.password;
    }
    /**
     * @return email
     */
    public String getEmail() {
        return this.email;
    }
    /**
     * Sets the major of a student to another major
     */
    public void setMajor(Major major) {
        this.major = major;
    }
    /**
     * Changes if the student has created a resume or not
     * @param createdResume
     */
    public void setCreated(Boolean createdResume) {
        this.createdResume = createdResume;
    }
    /**
     * Sets the reviews attached to this student to another list of reviews
     */
    public void setReviews(ArrayList<Review> reviews) {

    }
    /**
     * Checks if the student has ben removed
     */
    public boolean isRemoved() {
        return removed;
    }
    /**
     * Sets the user to be removed or not
     * @param removed
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
    /**
     * Returns a list of technical skills
     * @return technicalSkills
     */
    public ArrayList<String> getTechnicalSkills() {
        return technicalSkills;
    }
    /**
     * Creates a String version of a student's resume and 
     * @return String of everything in student's resume
     */
    public String getPrintableResume() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getFullName() + "\n");
        sb.append(this.getEmail() + "\n");
        sb.append(this.getMajor() + " major\n");
        sb.append("Employments:\n");
        for (Employment e : this.getEmployments()) {
            sb.append(e.toString() + "\n");
        }
        sb.append("Educations:\n");
        for (Education e : this.getEducations()) {
            sb.append(e.toString() + "\n");
        }
        sb.append("Technical Skills:\n");
        for (String s : this.getTechnicalSkills()) {
            sb.append("\t" + s + "\n");
        }
        return sb.toString();
    }
    /**
     * Prints the student's resume
     */
    public String toString() {
        return getPrintableResume();
    }
    /**
     * Checks if the UUID matches
     * @param student
     * @return if ids match
     */
    public boolean equals(Student student) {
        return this.id == student.getId();
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
        /**
         * Generates a random UUID and sets the Major to its default
         */
        public Builder() {
            id = UUID.randomUUID();
            major = Major.NA;
        }
        /**
         * Sets UUID to a new UUID
         * @param id
         * @return UUID
         */
        public Builder id(UUID id) {
            this.id = id;
            return this;
        }
        /**
         * Sets username to a new username
         * @param username
         * @return username
         */
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        /**
         * Sets password to a new password
         * @param password
         * @return password
         */
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        /**
         * Sets email to a new email
         * @param email
         * @return email
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        /**
         * Sets fristname to a new firstname
         * @param firstName
         * @return firstname
         */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        /**
         * Sets lastname to a new lastname
         * @param lastName
         * @return lastname
         */
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        /**
         * Sets if the account is approved to a new approval state
         * @param approved
         * @return approval
         */
        public Builder approved(boolean approved) {
            this.approved = approved;
            return this;
        }
        /**
         * Sets the major to a new major
         * @param major
         * @return major
         */
        public Builder major(Major major) {
            this.major = major;
            return this;
        }
        /**
         * Sets if the resume is created to a new state of resume creation
         * @param createdResume
         * @return createdResume
         */
        public Builder createdResume(boolean createdResume) {
            this.createdResume = createdResume;
            return this;
        }
        /**
         * Sets the list of employments to a new list of employments
         * @param employments
         * @return employments
         */
        public Builder employments(ArrayList<Employment> employments) {
            this.employments = employments;
            return this;
        }
        /**
         * Sets the list of education to a new list of education
         * @param educations
         * @return educations
         */
        public Builder educations(ArrayList<Education> educations) {
            this.educations = educations;
            return this;
        }
        /**
         * Sets list of technical skills to anew list of skills
         * @param technicalSkills
         * @return technical skills
         */
        public Builder technicalSkills(ArrayList<String> technicalSkills) {
            this.technicalSkills = technicalSkills;
            return this;
        }
        /**
         * Sets average rating to a new average rating
         * @param averageRating
         * @return averageRating
         */
        public Builder averageRating(double averageRating) {
            this.averageRating = averageRating;
            return this;
        }
        /**
         * Changes state of account (removed/unremoved) to a new state
         * @param removed
         * @return removed
         */
        public Builder removed(boolean removed) {
            this.removed = removed;
            return this;
        }
        /**
         * Creates a new student based on the inputs
         * @return Student
         */
        public Student build() {
            return new Student(id, username, password, email, firstName, lastName, approved, major, createdResume,
                    employments, educations, technicalSkills, averageRating, removed);
        }

    }
}