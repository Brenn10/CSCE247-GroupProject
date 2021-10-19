public class Education {
    private String place;
    private double gpa;
    private String gradDate;

    public Education(String place, double gpa, String gradeDate) {
        this.place = place;
        this.gpa = gpa;
        this.gradDate = gradeDate;
    }

    public String toString() {
        return "Educated at " + place + " until graduation on " + gradDate + ". GPA at graduation: " + gpa;
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
