package database;
import java.util.ArrayList;

import dataTypes.Review;
import dataTypes.User;

public class ReviewDatabase {
    private static ReviewDatabase reviewDatabase;

    private ReviewDatabase() {
    }

    public static ReviewDatabase getInstance() {
        if(reviewDatabase == null)
            reviewDatabase = new ReviewDatabase();
        return reviewDatabase;
    }

    public void addReview(Review review) {
        Database.getInstance().getReviews().add(review);
        Database.getInstance().writeToFileReviews();
    }

    public void removeReveiw(Review review) {
        review.setRemoved(true);
        Database.getInstance().writeToFileReviews();
    }

    public ArrayList<Review> getReviews() {
        return Database.getInstance().getReviews();
    }

    public ArrayList<Review> getRemovedReviews() {
        ArrayList<Review> removedReviews = new ArrayList<>();
        for(Review review : Database.getInstance().getReviews()) {
            if(review.isRemoved())
                removedReviews.add(review);
        }
        return removedReviews;
    }

    public Review getReviewByReviewer(User user) {
        for(Review review: Database.getInstance().getReviews()) {
            if(review.getReviewer().getUsername().equals(user.getUsername()))
                return review;
        }
        return null;
    }

    public Review getReviewByReviewee(User user) {
        for(Review review: Database.getInstance().getReviews()) {
            if(review.getReviewee().getUsername().equals(user.getUsername()))
                return review;
        }
        return null;
    }

    public Review getReviewByRevieweeAndReviewer(String reviewerUser, String revieweeUser) {
        for(Review review: Database.getInstance().getReviews()) {
            if(review.getReviewer().getUsername().equals(reviewerUser) && 
            review.getReviewee().getUsername().equals(revieweeUser))
                return review;
        }
        return null;
    }

    public ArrayList<Review> getReviewsByReviewee(User user) {
        ArrayList<Review> reviewsByReviewee = new ArrayList<Review>();
        for (Review review: Database.getInstance().getReviews()) {
            if(review.getReviewee().equals(user) && !review.isRemoved())
                reviewsByReviewee.add(review);
        }
        return reviewsByReviewee;
    }

    public ArrayList<Review> getReviewsByReviewer(User user) {
        ArrayList<Review> reviewsByReviewer = new ArrayList<Review>();
        for (Review review: Database.getInstance().getReviews()) {
            if(review.getReviewer().equals(user) && !review.isRemoved())
                reviewsByReviewer.add(review);
        }
        return reviewsByReviewer;
    }
}