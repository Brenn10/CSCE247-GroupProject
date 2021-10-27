
import java.util.Scanner;
import dataTypes.Student;
import database.JobPostingDatabase;
import database.ReviewDatabase;

public class StudentUI {
    public StudentUI() {

    }

    /**
     * student
     * 
     * View Job Listings -1 View Job Applications -2 Edit/Create Resume -3 Check
     * Review -4
     */
    public void doMainMenu(Student student) {
        boolean keepLooping = true;
        Scanner input = new Scanner(System.in);
        int option = 0;
        System.out.println("Welcome Student " + student.getFirstName() + " " + student.getLastName());
        while (keepLooping) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("(1) Enter Resume Editing Mode\n");
            System.out.println("(2) Enter Job Search Mode\n");
            System.out.println("(3) View Reviews\n");
            System.out.println("(0) Exit Neurotic Job Search");
            option = input.nextInt();
            if (option == 0) {
                keepLooping = false;
            } else if (option == 1) {
                // show current resume if availiable, ask which part to edit
                resumeMenu();

            } else if (option == 2) {
                // show all jobs, ask for specific job name
                jobMenu();

            } else if (option == 3) {
                // show all reviews attatched to self
                ReviewDatabase.getInstance().getReviewByReviewee(student);

            } else {
                System.out.println("ERROR: The number you typed is not an option!\n");
            }
        }
        input.close();
    }

    private void resumeMenu() {
        // ask what the user would like to change (number options)
        Scanner userInput = new Scanner(System.in);
        boolean keepLooping = true;
        while (keepLooping) {
            System.out.println("Welcome to the Resume Menu. Please indicate what item you would like to change: \n");
            String input = userInput.nextLine();

            if (input.equals("obj"))
                keepLooping = false;
        } // continue doing this

        userInput.close();
    }

    private void jobMenu() {
        JobPostingDatabase.getInstance().getPostings();
    }
}
