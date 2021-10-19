import java.util.ArrayList;

public abstract class DataWriter {
    public DataWriter() {}

    public abstract boolean write(ArrayList<User> users, ArrayList<Review> reviews, ArrayList<JobPosting> postings);
}
