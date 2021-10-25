import java.util.ArrayList;
import java.util.UUID;

public class Employer extends User {
    private String company;
    private ArrayList<JobPosting> postings;
    private ArrayList<Review> reviews;
    private double averageRating;

    public Employer(UUID id, 
            String username, 
            String password,
            String email, 
            String firstName,
            String lastName,
            boolean approved,
            String company,
            double averageRating) {
        super(id, username, password, email, firstName, lastName, approved);
        this.company = company;
        this.averageRating = averageRating;
    }
    public void makePosting(JobPosting job) {
        postings.add(job);
        // add to database somehow
    }

    //overload make posting method
    public void makePosting(String description, ArrayList<String> requirements, 
    double hourlyWage, String status, ArrayList<Student> applicants) {
        makePosting(new JobPosting(this, description, requirements, hourlyWage, status, applicants));
    }

    // edit posting methods 
    public  void editDescription(JobPosting job, String toChange) {
        job.setDescription(toChange);
    }
    public void addPostingRequirement(JobPosting job, String toChange) {
        job.addRequirement(toChange);
    }
    public void removePostingRequirement(JobPosting job, String toRemove) {
        job.removeRequriement(toRemove);
    }
    public void editPostingWage(JobPosting job, double wage) {
        job.setWage(wage);
    }
    public void editPostingStatus(JobPosting job, String status) {
        job.setStatus(status);
    }
    public void removePosting(JobPosting job) {
        // something with the database that I am unsure about
    }
    public ArrayList<Student> viewApplicants(JobPosting job) {
         return job.getApplicants();
    }
    
    public void rateStudent(Student student, int score, String comment) {
        Review rating = new Review.Builder().reviewer(this)
                                            .reviewee(student)
                                            .rating(score)
                                            .comment(comment)
                                            .build();
        ReviewDatabase.getInstance().addReview(rating);
        //TODO something with the database 
    }

    // get and set methods 
    public String getCompany() {
        return this.company;
    }

    public ArrayList<JobPosting> getPostings() {
        return this.postings;
    }

    public ArrayList<Review> getReviews() {
        return this.reviews;
    }

    public double getAverageRating() {
        return this.averageRating;
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

    public void setCompany(String company) {
       this.company = company;
    }

    public void setPostings(ArrayList<JobPosting> postings) {
        this.postings = postings;
    }

    public void setReviews(ArrayList<Review> reviews) {
       this.reviews = reviews;
    }

    public void setAverageRating(double rating) {
        this.averageRating = rating;
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
        private double averageRating;
        private String company;


        public Builder() {
            this.id = UUID.randomUUID();
            this.approved = false;
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

        public Builder averageRating(double averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Employer build() {
            return new Employer(id, username, password, email, firstName, lastName, approved, company, averageRating);
        }

    }

}