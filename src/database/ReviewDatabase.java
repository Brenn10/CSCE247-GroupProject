package database;
import java.util.ArrayList;

import dataTypes.Review;
import dataTypes.User;

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
        reviews.add(review);
        Database.getInstance().writeToFileReviews(reviews);
    }

    public void removeReveiw(Review review) {
        removedReviews.add(review);
        review.setRemoved(true);
        Database.getInstance().writeToFileReviews(reviews);

    }

    public ArrayList<Review> getReviews() {
        return this.reviews;
    }

    public ArrayList<Review> getRemovedReviews() {
        return this.removedReviews;
    }

    public Review getReviewByReviewer(User user) {
        for(Review review: reviews) {
            if(review.getReviewer().getUsername().equals(user.getUsername()))
                return review;
        }
        return null;
    }

    public Review getReviewByReviewee(User user) {
        for(Review review: reviews) {
            if(review.getReviewee().getUsername().equals(user.getUsername()))
                return review;
        }
        return null;
    }

    public Review getReviewByRevieweeAndReviewer(String reviewerUser, String revieweeUser) {
        for(Review review: reviews) {
            if(review.getReviewer().getUsername().equals(reviewerUser) && 
            review.getReviewee().getUsername().equals(revieweeUser))
                return review;
        }
        return null;
    }

    public ArrayList<Review> getReviewsByReviewee(User user) {
        ArrayList<Review> reviewsByReviewee = new ArrayList<Review>();
        for (Review review: reviews) {
            if(review.getReviewee().equals(user) && !review.isRemoved())
                reviewsByReviewee.add(review);
        }
        return reviewsByReviewee;
    }

    public ArrayList<Review> getReviewsByReviewer(User user) {
        ArrayList<Review> reviewsByReviewer = new ArrayList<Review>();
        for (Review review: reviews) {
            if(review.getReviewer().equals(user) && !review.isRemoved())
                reviewsByReviewer.add(review);
        }
        return reviewsByReviewer;
    }
}