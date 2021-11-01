package database;

import java.util.ArrayList;

import dataTypes.Review;
import dataTypes.User;

/**
 * ReviewDatabase class
 * 
 * @author Stella Garcia and Ian McDevitt
 */
public class ReviewDatabase {
    private static ReviewDatabase reviewDatabase; // singleton instance

    /**
     * Constructor The ReviewDatabase class is a singleton so the constructor is not
     * accessible outside of the class
     */
    private ReviewDatabase() {
    }

    /**
     * Singleton getInstance method there is only one ReviewDatabase object
     * 
     * @return ReviewDatabase object (singleton)
     */
    public static ReviewDatabase getInstance() {
        if (reviewDatabase == null)
            reviewDatabase = new ReviewDatabase();
        return reviewDatabase;
    }

    /**
     * When a new review is added, we add it to the Database's ArrayList and update
     * the JSON file
     * 
     * @param review the review to be added
     */
    public void addReview(Review review) {
        Database.getInstance().getReviews().add(review);
        Database.getInstance().writeToFileReviews();
    }

    /**
     * When a review is to be removed, we set it's removed attribute to true and
     * update the JSON
     * 
     * @param posting the review to be removed
     */
    public void removeReveiw(Review review) {
        review.setRemoved(true);
        Database.getInstance().writeToFileReviews();
    }

    /**
     * Get method for all of the reviews
     * 
     * @return ArrayList of all Reviews
     */
    public ArrayList<Review> getReviews() {
        ArrayList<Review> reviews = Database.getInstance().getReviews();
        ArrayList<Review> toReturn = new ArrayList<Review>();
        for (Review review : reviews)
            if (!review.isRemoved())
                toReturn.add(review);
        return toReturn;
    }

    /**
     * Get method for all of the removed Reviews
     * 
     * @return ArrayList of all removed reviews
     */
    public ArrayList<Review> getRemovedReviews() {
        ArrayList<Review> removedReviews = new ArrayList<>();
        for (Review review : Database.getInstance().getReviews()) {
            if (review.isRemoved())
                removedReviews.add(review);
        }
        return removedReviews;
    }

    /**
     * gets a single review made by a user
     * 
     * @param user the user in question
     * @return the review made by said user
     */
    public Review getReviewByReviewer(User user) {
        for (Review review : Database.getInstance().getReviews()) {
            if (review.getReviewer().getUsername().equals(user.getUsername()))
                return review;
        }
        return null;
    }

    /**
     * gets a single review of a user
     * 
     * @param user the user in question
     * @return the review of said user
     */
    public Review getReviewByReviewee(User user) {
        for (Review review : Database.getInstance().getReviews()) {
            if (review.getReviewee().getUsername().equals(user.getUsername()))
                return review;
        }
        return null;
    }

    /**
     * Gets a specific review by a user about another user
     * 
     * @param reviewerUser the user who reviews
     * @param revieweeUser the user reviewed
     * @return The review made by reviewer about reviewee
     */
    public Review getReviewByRevieweeAndReviewer(String reviewerUser, String revieweeUser) {
        for (Review review : Database.getInstance().getReviews()) {
            if (review.getReviewer().getUsername().equals(reviewerUser)
                    && review.getReviewee().getUsername().equals(revieweeUser))
                return review;
        }
        return null;
    }

    /**
     * Gets all reviews about one specifc user
     * 
     * @param user the user in question
     * @return ArrayList of all the reviews
     */
    public ArrayList<Review> getReviewsByReviewee(User user) {
        ArrayList<Review> reviewsByReviewee = new ArrayList<Review>();
        for (Review review : Database.getInstance().getReviews()) {
            if (review.getReviewee().equals(user) && !review.isRemoved())
                reviewsByReviewee.add(review);
        }
        return reviewsByReviewee;
    }

    /**
     * Gets all reviews made by one specifc user
     * 
     * @param user the user in question
     * @return ArrayList of all the reviews
     */
    public ArrayList<Review> getReviewsByReviewer(User user) {
        ArrayList<Review> reviewsByReviewer = new ArrayList<Review>();
        for (Review review : Database.getInstance().getReviews()) {
            if (review.getReviewer().equals(user) && !review.isRemoved())
                reviewsByReviewer.add(review);
        }
        return reviewsByReviewer;
    }
}