import java.util.ArrayList;

public class ReviewDatabase {
    private static ReviewDatabase reviewDatabase;
    private ArrayList<Review> reviews;
    private ArrayList<Review> removedReviews;

    private ReviewDatabase() {
        reviews = new ArrayList<Review>();
        removedReviews = new ArrayList<Review>();
    }

    public static ReviewDatabase getInstance() {
        if(reviewDatabase == null)
            reviewDatabase = new ReviewDatabase();
        return reviewDatabase;
    }

    public void addReview(Review review) {
        
    }

    public void removeReveiw(Review review) {

    }

    public Review getReviewByReviewer(User user) {
        return null;
    }

    public Review getReviewByReviewee(User user) {
        return null;
    }
}