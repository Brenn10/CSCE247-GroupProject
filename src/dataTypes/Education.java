package dataTypes;

/**
 * Eduction datatype part of the resume for student
 * 
 * @author Stella Garcia
 */
public class Education {
    private String place;
    private double gpa;
    private String gradDate;

    /**
     * Constructor
     * 
     * @param place     the place they attended
     * @param gpa       the gpa they received
     * @param gradeDate when they graduate(d)
     */
    public Education(String place, double gpa, String gradeDate) {
        this.place = place;
        this.gpa = gpa;
        this.gradDate = gradeDate;
    }

    /**
     * Get method for the place
     * 
     * @return String of place
     */
    public String getPlace() {
        return place;
    }

    /**
     * Set method for place
     * 
     * @param place what we want to set the place to
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Get method for GPA
     * 
     * @return double the GPA
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Set method of GPA
     * 
     * @param gpa what we want to set the GPA to
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    /**
     * Get method for gradData
     * 
     * @return String of their graduation date
     */
    public String getGradDate() {
        return gradDate;
    }

    /**
     * Set method for the gradDate
     * 
     * @param gradDate what we want to set the gradDate to
     */
    public void setGradDate(String gradDate) {
        this.gradDate = gradDate;
    }

    /**
     * toString method
     * 
     * @return String when we want to print everything
     */
    public String toString() {
        return "\t" + place + "\n\t" + gradDate + "\n\tGPA: " + gpa;
    }

    /**
     * Builder class allows for building a new Education
     * 
     * @author Brennan Cain
     */
    public static class Builder {
        private String place;
        private double gpa;
        private String gradDate;

        /**
         * Intentionally empty constructor
         */
        public Builder() {

        }

        /**
         * Get method for Buidler with a place
         * 
         * @param place the place we want to set
         * @return a Builder with that place
         */
        public Builder place(String place) {
            this.place = place;
            return this;
        }

        /**
         * Get method for Buidler with a GPA
         * 
         * @param gpa the gpa we want to set
         * @return a Builder with that gpa
         */
        public Builder gpa(double gpa) {
            this.gpa = gpa;
            return this;
        }

        /**
         * Get method for Buidler with a gradDate
         * 
         * @param gradDate the gradDate we want to set
         * @return a Builder with that gradDate
         */
        public Builder gradDate(String gradDate) {
            this.gradDate = gradDate;
            return this;
        }

        /**
         * Build method creates a new Education
         * 
         * @return the newly built Education
         */
        public Education build() {
            return new Education(place, gpa, gradDate);
        }
    }
}
