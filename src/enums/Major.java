package enums;
public enum Major {
    NA("NA"),
    COMPUTER_SCIENCE("Computer Science"),	
    COMPUTER_ENGINEERING("Computer Engineering"),
    INTEGRATED_INFORMATION_TECHNOLOGY("Integrated Information Technology"),
    COMPUTER_INFORMATION_SYSTEMS("Computer Information Systems");
    
    private String major;
    private Major(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return major;
    }
}
