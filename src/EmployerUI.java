
import java.util.Scanner;

import dataTypes.Employer;
import dataTypes.Review;
import database.ReviewDatabase;
import database.UserDatabase;

public class EmployerUI {

    /**
     * employer
     * 
     * Create Job Posting
     * Edit Job P
     * View Job P
     * Review Student
     * Edit Review
     * View Reviews (of self to all stu)
     */
    public void doMainMenu(Employer employer) {
        Scanner input = new Scanner (System.in);
        System.out.println("Welcome Employer " + employer.getFirstName() + " " + employer.getLastName());

        boolean doLoop = true;
        while (doLoop) {
            System.out.println("Please select a valid option:");
            System.out.println("(1) Enter Job Edit Mode");
            System.out.println("(2) Enter Student Review Mode");
            System.out.println("(0) Exit Neurotic Job Search");
            switch(input.nextInt()) {
                case 1:
                    doJobEditMenu(employer);
                    break;
                case 2:
                    doStudentReviewMenu(employer);
                    break;
                case 0:
                    doLoop = false;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
        input.close();
    }

    private void doJobEditMenu(Employer employer) {}

    private void doStudentReviewMenu(Employer employer) {
        // same for professors AND Employers
        UserDatabase.getInstance().getStudents();
        //ask for specific student
        System.out.println("Please select a student's first name"); // or something similar
        
        ReviewDatabase.getInstance().addReview(new Review.Builder().build()); // chosen student
        // loop until 0 chosen or student selected and rated successfully
    }
}
