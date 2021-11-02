package dataTypes;

import java.util.ArrayList;

/**
 * Emmployment datatype part of the resume for student
 * 
 * @author Stella Garcia
 */
public class Employment {
    private String company;
    private String title;
    private String dates;
    private ArrayList<String> details;

    /**
     * Constructor
     * 
     * @param company the company they worked for
     * @param title   the title they held
     * @param dates   the dates they worked there
     * @param details the details of thier employment
     */
    public Employment(String company, String title, String dates, ArrayList<String> details) {
        this.company = company;
        this.title = title;
        this.dates = dates;
        this.details = details;
    }

    /**
     * Get method for company
     * 
     * @return the company String
     */
    public String getCompany() {
        return company;
    }

    /**
     * Get method for title
     * 
     * @return the title String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get method for dates
     * 
     * @return the dates String
     */
    public String getDates() {
        return dates;
    }

    /**
     * Get method for details
     * 
     * @return the ArrayList of details
     */
    public ArrayList<String> getDetails() {
        return details;
    }

    /**
     * toString method
     * 
     * @return String they want to print
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t" + title + " at " + company + "\n");
        sb.append("\t" + dates);
        for (String detail : details) {
            sb.append("\n\t" + detail);
        }
        return sb.toString();
    }

    /**
     * Builder class allows for building a new Employment
     * 
     * @author Brennan Cain
     */
    public static class Builder {
        private String company;
        private String title;
        private String dates;
        private ArrayList<String> details;

        /**
         * Intentionally empty constructor
         */
        public Builder() {
        }

        /**
         * Get method for Builder with a company
         * 
         * @param company the company we want to set to
         * @return a Builder with that company
         */
        public Builder company(String company) {
            this.company = company;
            return this;
        }

        /**
         * Get method for Builder with a title
         * 
         * @param title the title we want to set to
         * @return a Builder with that title
         */
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        /**
         * Get method for Builder with dates
         * 
         * @param dates the dates we want to set to
         * @return a Builder with those dates
         */
        public Builder dates(String dates) {
            this.dates = dates;
            return this;
        }

        /**
         * Get method for Builder with details
         * 
         * @param details the details we want to set to
         * @return a Builder witht those details
         */
        public Builder details(ArrayList<String> details) {
            this.details = details;
            return this;
        }

        /**
         * Build method creates a new Employment
         * 
         * @return the newly built Employment
         */
        public Employment build() {
            return new Employment(company, title, dates, details);
        }
    }
}
