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
        this.details = description;
    }

    public String toString(){
        String toReturn = "Employed at " + company + " as a " + title + " during " + dates + "\n Additional Details";

        for(String detail: details) 
            toReturn = toReturn + "\n-" + detail;
        return toReturn;
    }
}
