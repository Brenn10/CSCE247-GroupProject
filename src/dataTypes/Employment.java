package dataTypes;
import java.util.ArrayList;

public class Employment {
    private String company;
    private String title;
    private String dates;
    private ArrayList<String> details;
    
    public Employment(String company, String title, String dates, ArrayList<String> details) {
        this.company = company;
        this.title = title;
        this.dates = dates;
        this.details = details;
    }

    public String getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }

    public String getDates() {
        return dates;
    }

    public ArrayList<String> getDetails() {
        return details;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(title + " at " + company + "\n");
        sb.append(dates + "\n");
        for(String detail : details){
            sb.append(detail + "\n");
        }
        return sb.toString();
    }

    public static class Builder {
        private String company;
        private String title;
        private String dates;
        private ArrayList<String> details;

        public Builder() {
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder dates(String dates) {
            this.dates = dates;
            return this;
        }

        public Builder details(ArrayList<String> details) {
            this.details = details;
            return this;
        }

        public Employment build() {
            return new Employment(company, title, dates, details);
        }
    }
}
