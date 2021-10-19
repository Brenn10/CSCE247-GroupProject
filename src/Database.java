import java.util.ArrayList;

public class Database {
    private static Database instance = null;

    DataReader reader;
    DataWriter writer;

    private ArrayList<User> users;
    private ArrayList<Review> reviews;
    private ArrayList<JobPosting> postings;

    private Database() {}
    
    public Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void setDataReader(DataReader reader) {
        this.reader = reader;
    }

    public void setDataWriter(DataWriter writer) {
        this.writer = writer;
    }

    public void loadFromFile() {
        users = reader.getUsers();
        reviews = reader.getReviews();
        postings = reader.getJobPostings();
    }

    public void writeToFile() {
        writer.write(users, reviews, postings);
    }

    public ArrayList<JobPosting> getJobPostings() {
        return reader.getJobPostings();
    }

    public ArrayList<Review> getReviews() {
        return reader.getReviews();
    }

    public ArrayList<User> getUsers() {
        return reader.getUsers();
    }
}
