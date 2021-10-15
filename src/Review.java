public class Review {
    private User reviewer;
    private User reviewee;
    private int rating;
    private String comment;

    public Review (User reveiwer, User reviewee, int rating, String comment) {
        this.reviewer = reveiwer;
        this.reviewee = reviewee;
        this.rating = rating;
        this.comment = comment;
    }

    public User getReviewer() {
        return this.reviewer;
    }

    public User getReviewee() {
        return this.reviewee;
    }

    public int getRating() {
        return this.rating;
    }

    public String getComment() {
        return this.comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCommment(String comment) {
        this.comment = comment;
    }

}