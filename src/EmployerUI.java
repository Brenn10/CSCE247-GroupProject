
import java.util.ArrayList;
import java.util.Scanner;

import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.Student;
import dataTypes.Review.Builder;
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
                editJob(employer);
                break;
            case 2:
                makeJob(employer);
                break;
            case 0:
                // exit message?
                employerScanner.close();
                return;
            }
        employerScanner.close();
    }
private void editJob(Employer employer) {
    Scanner employerScanner = new Scanner(System.in);
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
    employerScanner.close();
}
private void makeJob (Employer employer) {
    Scanner employScanner = new Scanner(System.in);
    dataTypes.JobPosting.Builder newJob = new JobPosting.Builder();
    
    JobPostingDatabase.getInstance().addPosting(newJob.build());
    employScanner.close();
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
        Student foundStudent = null;
        while (!studentFound) {
            System.out.println("Please input a student's first name, or (0) to quit");
            String studentName = employerScanner.nextLine();
            if (studentName.equals("0")) {
                employerScanner.close();
                return;
            }
            for (JobPosting i : employer.getPostings()) {
                for (Student j : i.getApplicants()) {
                    if(studentName.equals(j.getFirstName())){
                        studentFound = true;
                        foundStudent = j;
                    }
                }
            }
        }
        if(foundStudent == null) {
            employerScanner.close();
            return;
        }

        System.out.println(" Would you like to 1 create a review 2 edit a review or 0 quit");
        
        boolean keepLooping = true;
        while (keepLooping) {
            int employerInput = employerScanner.nextInt();
            switch (employerInput) {
                case 1:
                createNewReview(foundStudent, employer);
                break;
                case 2:
                editReview(foundStudent);
                break;
                case 0 :
                keepLooping = false;
                break;
                default:
                System.out.println("Invalid Option");
                break;
            }

        }
        employerScanner.close();
    }


private void createNewReview(Student foundStudent, Employer employer) {
    Builder newReview = new Review.Builder();
    Scanner employerScanner = new Scanner(System.in);
    System.out.println("What do you rate" + foundStudent.getFirstName());
    Integer rating = employerScanner.nextInt();
    newReview.rating(rating);
    System.out.println("What would you like to comment?");
    String comment = employerScanner.nextLine();
    newReview.comment(comment);
    newReview.reviewer(foundStudent);
    newReview.reviewee(employer);
    ReviewDatabase.getInstance().addReview(newReview.build()); // chosen student
    employerScanner.close();
    // for testing to make sure it works
    System.out.println(newReview.build());
}

private void editReview(Student foundStudent) {
        Scanner employerScanner = new Scanner(System.in);
        ReviewDatabase reviewDatabase = ReviewDatabase.getInstance();
        ArrayList <Review> selfReviews = reviewDatabase.getReviewsByReviewee(foundStudent);

        for (int i = 0; i < selfReviews.size(); i++) {
            System.out.print(i+1 + ")");
            System.out.println(selfReviews.get(i));
        }
        System.out.println("Please Select a Review from the list");
        
        int reviewInput = employerScanner.nextInt();
        if(reviewInput<=0 || reviewInput >= selfReviews.size()) {
            System.out.println("ERROR: Out of range");
            employerScanner.close();
            return;
        }
        Review chosenReview = selfReviews.get(reviewInput-1);
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        System.out.println(chosenReview);
        System.out.println("What would you like to change?");
        System.out.println("1) Rating");
        System.out.println("2) Comment");
        System.out.println("0) Exit");
        boolean keepLooping = true;
        while(keepLooping) {
            reviewInput = employerScanner.nextInt();
            switch (reviewInput) {
                case 1:
                    int rating = employerScanner.nextInt();
                    chosenReview.setRating(rating);
                    break;
                case 2:
                    String comment = employerScanner.nextLine();
                    chosenReview.setCommment(comment);
                    break;
                case 0: 
                    employerScanner.close();
                    return;
            }
        }
        employerScanner.close();
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public static void doSignup() {
    }
}
