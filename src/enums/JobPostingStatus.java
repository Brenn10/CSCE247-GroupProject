package enums;

/**
 * JobPostingStatus enum 4 options on what a jobPosting can be
 * 
 * @author Brennan Cain and Stella Garcia
 */
public enum JobPostingStatus {
    NA("NA"), OPEN("Open"), PENDING("Pending"), CLOSED("Closed");

    private String status;

    /**
     * Set method for status
     * 
     * @param status the status we want to set to
     */
    private JobPostingStatus(String status) {
        this.status = status;
    }

    /**
     * toString method
     * 
     * @return String what we want to print
     */
    @Override
    public String toString() {
        return status;
    }
}
