
import java.util.ArrayList;
import java.util.Scanner;

import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.Student;
import dataTypes.Review.Builder;
import database.ReviewDatabase;
import database.UserDatabase;
import enums.JobPostingStatus;
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
        // TODO make an option to just view a posting and its students
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
    JobPosting matchedJob = null;
    while (!jobMatched) {
        String selectedTitle = employerScanner.nextLine();
        // TODO create an exit option
        for(JobPosting i : employer.getPostings()) {
        if (i.getJobTitle().equals(selectedTitle)) {
            jobMatched = true;
            matchedJob = i;
            }
        }
    }
    if(matchedJob == null) {
        employerScanner.close();
        return;
    }
    System.out.print(matchedJob);

    
    System.out.println("What would you like to change?");
    System.out.println("1) Title");
    System.out.println("2) Description");
    System.out.println("3) Requirements");
    System.out.println("4) Hourly Wage");
    System.out.println("5) Current Status");
    System.out.println("0) Exit");
    boolean keepLooping = true;
    while (keepLooping) {
        int changeNum = employerScanner.nextInt();
        switch(changeNum) {
            case 1:
            System.out.println("Please input the new Title");
            matchedJob.setTitle(employerScanner.nextLine());
                break;
            case 2:
            System.out.println("Please input the new Description");
                matchedJob.setDescription(employerScanner.nextLine());    
                break;
            case 3:
            System.out.println("Would you like to add a requirement(1) or change an existing one?(2)");
                int requirementNum = employerScanner.nextInt();
                switch(requirementNum) {
                    case 1:
                        System.out.println("Please input the new requirement");
                        matchedJob.addRequirement(employerScanner.nextLine());
                        break;
                    case 2:
                    ArrayList<String> allRequirements = matchedJob.getRequirements();
                    for (int i = 0; i<allRequirements.size(); i++) {
                        System.out.print((i+1) + " )" + allRequirements.get(i));
                    }
                    System.out.println("Please input the number of the requirement you would like to edit");
                    requirementNum = employerScanner.nextInt()+1;
                    if (requirementNum > allRequirements.size()+1 || requirementNum <= 0) {
                        // error message?
                        break;
                    }
                    System.out.println("Please input the new Requirement");
                    allRequirements.set(requirementNum, employerScanner.nextLine());
                    matchedJob.setRequirements(allRequirements);
                        break;
                    default:
                        // error message?
                        break;
                }
                break;
            case 4:
            System.out.println("Please input the new Hourly Wage");
                matchedJob.setWage(employerScanner.nextDouble());
                break;
            case 5:
            System.out.println("Please input the new Status");
                // do the enum thing
                break;
            case 0:
            System.out.println("Exiting JobEdit");
                break;
            default:
            System.out.println("Exiting JobEdit");
                break;
        }
    }
    employerScanner.close();
}
private void makeJob (Employer employer) {
// can newjob.--(nextLine) work? 
    Scanner employScanner = new Scanner(System.in);
    dataTypes.JobPosting.Builder newJob = new JobPosting.Builder();
    System.out.println("Title"); // remember to add empl.company
    String jobTitle = employScanner.nextLine();
    newJob.jobTitle(jobTitle);
    newJob.employer(employer);
    System.out.println("Description");
    String description = employScanner.nextLine();
    newJob.description(description);
    System.out.println("Requirements"); // loop until no more
    boolean keepLooping = true;
    ArrayList<String> requirements = new ArrayList<>();
    while (keepLooping) {
        String requirement = employScanner.nextLine();
        System.out.println("Input a requirement, or (0) to continue");
        if(requirement.equals("0")) {
            break;
        }
        requirements.add(requirement);
    }
    newJob.requirements(requirements);
    System.out.println("Hourly Wage");
    double hourlyWage = employScanner.nextDouble();
    newJob.hourlyWage(hourlyWage);
    System.out.println("Current Status ()"); // list enum
    // TODO see how to use enum efficiently
    
    JobPostingDatabase.getInstance().addPosting(newJob.build());
    employScanner.close();
    // used for testing
    //System.out.println(newJob.build());
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

    System.out.println("Would you like to 1 create a review 2 edit a review or 0 quit");
    
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
    // for testing
    //System.out.println(newReview.build());
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
        // do this
    }
}
