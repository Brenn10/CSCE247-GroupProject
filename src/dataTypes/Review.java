/**
 * Holds all items pertaining to a review, such as reviewer, a comment, and a rating
 * @author Stella Garcia, Ian McDevitt
 */
package dataTypes;

import java.util.UUID;

public class Review {
    private UUID id;
    private User reviewer;
    private User reviewee;
    private int rating;
    private String comment;
    private boolean removed;

    public Review(UUID id, User reveiwer, User reviewee, int rating, String comment, boolean removed) {
        this.id = id;
        this.reviewer = reveiwer;
        this.reviewee = reviewee;
        this.rating = rating;
        this.comment = comment;
        this.removed = removed;
    }
    /**
     * @return reviewer
     */
    public User getReviewer() {
        return this.reviewer;
    }
    /**
     * @return reviewee
     */
    public User getReviewee() {
        return this.reviewee;
    }
    /**
     * @return rating
     */
    public int getRating() {
        return this.rating;
    }
    /**
     * @return comment
     */
    public String getComment() {
        return this.comment;
    }
    /**
     * @return UUID
     */
    public UUID getId() {
        return this.id;
    }
    /**
     * Sets the rating to a new rating
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    /**
     * Sets the comment to a new comment
     * @param comment
     */
    public void setCommment(String comment) {
        this.comment = comment;
    }
    /**
     * @return removed status
     */
    public boolean isRemoved() {
        return removed;
    }
    /**
     * Sets the review to be removed or not
     * @param removed
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
    /**
     * Prints the review by reviewer from reviewee, what rating, and what comment
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Review of " + reviewee.getFullName() + " by " + this.reviewer.getFullName() + ": ");
        sb.append("\n\tRating: " + this.rating);
        sb.append("\n\tComment: " + this.comment);
        return sb.toString();
    }
    /**
     * @author Brennan Cain
     */
    public static class Builder {
        private UUID id;
        private User reviewer;
        private User reviewee;
        private int rating;
        private String comment;
        private boolean removed;

        public Builder() {
            this.id = UUID.randomUUID();
        }
        /**
         * Sets UUID to a new UUID
         * @param id
         * @return id
         */
        public Builder id(UUID id) {
            this.id = id;
            return this;
        }
        /**
         * Sets reviewer to a new reviewer
         * @param reviewer
         * @return reviewer
         */
        public Builder reviewer(User reviewer) {
            this.reviewer = reviewer;
            return this;
        }
        /**
         * Sets reviewiee to a new reviewee
         * @param reviewee
         * @return reviewee
         */
        public Builder reviewee(User reviewee) {
            this.reviewee = reviewee;
            return this;
        }
        /**
         * Sets rating to a new rating
         * @param rating
         * @return rating
         */
        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }
        /**
         * Sets comment to a new comment
         * @param comment
         * @return comment
         */
        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }
        /**
         * Changes state of review (removed/unremoved) to a new state
         * @param removed
         * @return removed
         */
        public Builder removed(boolean removed) {
            this.removed = removed;
            return this;
        }
        /**
         * Creates a new Review based on the inputs of Builder
         * @return Review
         */
        public Review build() {
            return new Review(id, reviewer, reviewee, rating, comment, removed);
        }
    }
}