import java.util.UUID;

public class Review {
    private UUID id;
    private User reviewer;
    private User reviewee;
    private int rating;
    private String comment;

    public Review (UUID id, User reveiwer, User reviewee, int rating, String comment) {
        this.id = id;
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


    public static class Builder {
        private UUID id;
        private User reviewer;
        private User reviewee;
        private int rating;
        private String comment;

        public Builder() {
            this.id = UUID.randomUUID();
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }
        
        public Builder reviewer(User reviewer) {
            this.reviewer = reviewer;
            return this;
        }

        public Builder reviewee(User reviewee) {
            this.reviewee = reviewee;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Review build() {
            return new Review(id, reviewer, reviewee, rating, comment);
        }
    }
}