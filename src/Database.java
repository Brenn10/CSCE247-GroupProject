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
        DataBlob blob = reader.read();
        users = blob.getUsers();
        reviews = blob.getReviews();
        postings = blob.getJobPostings();
    }

    public void writeToFile() {
        writer.write(users, reviews, postings);
    }

    public ArrayList<JobPosting> getJobPostings() {
        return postings;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
