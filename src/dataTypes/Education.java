package dataTypes;
public class Education {
    private String place;
    private double gpa;
    private String gradDate;

    public Education(String place, double gpa, String gradeDate) {
        this.place = place;
        this.gpa = gpa;
        this.gradDate = gradeDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getGradDate() {
        return gradDate;
    }

    public void setGradDate(String gradDate) {
        this.gradDate = gradDate;
    }

    public String toString() {
        return "\t" + place + "\n\t"+ gradDate + "\n\tGPA: " + gpa;
    }

    public static class Builder {
        private String place;
        private double gpa;
        private String gradDate;

        public Builder() {

        }

        public Builder place(String place) {
            this.place = place;
            return this;
        }

        public Builder gpa(double gpa) {
            this.gpa = gpa;
            return this;
        }

        public Builder gradDate(String gradDate) {
            this.gradDate = gradDate;
            return this;
        }

        public Education build() {
            return new Education(place, gpa, gradDate);
        }
    }
}
