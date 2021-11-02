package enums;

/**
 * Major enum The system is for computing studnets, so their option for major is
 * limited
 * 
 * @author Brennan Cain and Stella Garcia
 */
public enum Major {
    NA("NA"), COMPUTER_SCIENCE("Computer Science"), COMPUTER_ENGINEERING("Computer Engineering"),
    INTEGRATED_INFORMATION_TECHNOLOGY("Integrated Information Technology"),
    COMPUTER_INFORMATION_SYSTEMS("Computer Information Systems");

    private String major;

    /**
     * Set method for major
     * 
     * @param major the major we want to set to
     */
    private Major(String major) {
        this.major = major;
    }

    /**
     * toString method
     * 
     * @return String what we want to print
     */
    @Override
    public String toString() {
        return major;
    }
}
