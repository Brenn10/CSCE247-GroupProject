
import java.util.ArrayList;
import java.util.Scanner;

import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.Student;
import database.ReviewDatabase;
import database.UserDatabase;
import database.JobPostingDatabase;

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
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    private void doJobEditMenu(Employer employer) {
        System.out.println("Here are your current Job Postings: ");
        for(JobPosting i : employer.getPostings()) {
            System.out.println(i.getJobTitle());
        }
        Scanner employerScanner = new Scanner(System.in);
        System.out.println("Would you like to:");
        System.out.println("(1) Edit a Current Job Posting");
        System.out.println("(2) Create a New Job Posting");
        System.out.println("(0) Exit Job Edit Mode");
        switch(employerScanner.nextInt()) {
            case 1:
                System.out.println("Which one would you like to edit?");
                boolean jobMatched = false;
                JobPosting matchedJob;
                while (!jobMatched) {
                  String selectedTitle = employerScanner.nextLine();
                  for(JobPosting i : employer.getPostings()) {
                      if (i.getJobTitle().equals(selectedTitle)) {
                         jobMatched = true;
                         matchedJob = i;
                     }
                   }
                }
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();

                break;
            case 2:

                break;
            case 0:
                employerScanner.close();
                return;
            }
        employerScanner.close();
    }

    private void doStudentReviewMenu(Employer employer) {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        // since this is for just the employer, look through all self job postings, post all students attatched to self
        for (JobPosting i : employer.getPostings()) {
            System.out.println("Job Posting: ");
            System.out.println(i.getJobTitle());
            for (Student j : i.getApplicants()) {
                System.out.println(j.getFirstName() + " " + j.getLastName());
            }
        }
        boolean studentFound = false;
        Scanner employerScanner = new Scanner(System.in);
        Student foundStudent;
        while (!studentFound) {
            System.out.println("Please select a student's first name, or (0) to quit");
            String studentName = employerScanner.nextLine();
            if (studentName.equals("0"))
                return;
            for (JobPosting i : employer.getPostings()) {
                for (Student j : i.getApplicants()) {
                    if(studentName.equals(j.getFirstName())){
                        studentFound = true;
                        foundStudent = j;
                    }
                }
            }
        }
        
        // TODO write review for chosen student (foundStudent)
        
        // TODO review how builder interacts with review, how to input items into review
        ReviewDatabase.getInstance().addReview(new Review.Builder().build()); // chosen student

        employerScanner.close();
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public static void doSignup() {
    }
}
