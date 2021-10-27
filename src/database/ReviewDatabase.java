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
        //this should work if you change the database file writeToFile to take postings as its only argument.
        //check Database.java
    }

    public void removeReveiw(Review review) {
        removedReviews.add(review);
        reviews.remove(review);
        //TODO database

    }

    public Review getReviewByReviewer(User user) {
        return null;
    }

    public Review getReviewByReviewee(User user) {
        return null;
    }
}