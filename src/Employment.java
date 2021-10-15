import java.util.ArrayList;

public class Employment {
    private String company;
    private String title;
    private String dates;
    private ArrayList<String> details;
    
    public Employment(String company, String title, String dates, ArrayList<String> description) {
        this.company = company;
        this.title = title;
        this.dates = dates;
        this.details = details;
    }

    public String toString(){
        return "";
    }
}
