/**
 * Hosts all items pertaining to Employer interaction with the 
 * Neurotic Job Search, including
 * Creating Job Postings, Edit Job Postings, Reviewing Students
 * Viewing Job Postings, Viewing Reviews From Self
 * @author Robert Sturman
 */
import java.util.ArrayList;
import java.util.Scanner;

import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.Student;
import dataTypes.Review.Builder;
import database.Database;
import enums.JobPostingStatus;

public class EmployerUI {
    Scanner employScanner;

    public EmployerUI() {
        employScanner = new Scanner(System.in);
    }
    /**
     * Creates a main menu Interface for the user to interact with
     * @param employer
     */
    public void doMainMenu(Employer employer) {  
        System.out.println("Welcome Employer " + employer.getFirstName() + " " + employer.getLastName());

        boolean doLoop = true;
        while (doLoop) {
            System.out.println("Please select a valid option:");
            System.out.println("(1) Enter Job Edit Mode");
            System.out.println("(2) Enter Student Review Mode");
            System.out.println("(0) Exit Neurotic Job Search");
            switch(employScanner.nextInt()) {
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
        //employScanner.close();
    }
    /**
     * Creates a menu for the employer to navigate through their Job Postings
     * @param employer
     */
    private void doJobEditMenu(Employer employer) {
        System.out.println("Here are your current Job Postings: ");
        if (Database.getInstance().getPostingsByEmployer(employer)!=null) {
            for(JobPosting i : Database.getInstance().getPostingsByEmployer(employer)) {
                System.out.println(i.getJobTitle());
            }
        }
        System.out.println("Would you like to:");
        System.out.println("(1) Edit a Current Job Posting");
        System.out.println("(2) Create a New Job Posting");
        System.out.println("(3) View Job Posting");
        System.out.println("(0) Exit Job Edit Mode");
        switch(employScanner.nextInt()) {
            case 1:
                editJob(employer);
                break;
            case 2:
                makeJob(employer);
                break;
            case 3:
                viewJob(employer);
                break;
            case 0:
                System.out.println("Exiting Job Edit Menu");
                return;
            }
    }
/**
 * Allows the employer to edit any of their existing Job Postings
 * @param employer
 */
private void editJob(Employer employer) {
    if (Database.getInstance().getPostingsByEmployer(employer) == null) {
        System.out.println("Sorry, there are no jobs to edit!");
        return;
    }
    boolean jobMatched = false;
    JobPosting matchedJob = null;
    while (!jobMatched) {
        System.out.println("Which job posting would you like to edit?");
        System.out.println("Input (0) to exit");
        String selectedTitle = employScanner.nextLine();
       if (selectedTitle.equals("0")) {
        System.out.println("Exiting Job Edit Mode");
        employScanner.close();
        return;
       }
       if( Database.getInstance().getPostingsByEmployer(employer) != null) {
        for(JobPosting i : Database.getInstance().getPostingsByEmployer(employer)) {
        if (i.getJobTitle().equals(selectedTitle)) {
            jobMatched = true;
            matchedJob = i;
            }
        }
    }
    if(matchedJob == null) {
        return;
    }
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
        int changeNum = employScanner.nextInt();
        switch(changeNum) {
            case 1:
            System.out.println("Please input the new Title");
            matchedJob.setTitle(employScanner.nextLine());
                break;
            case 2:
            System.out.println("Please input the new Description");
                matchedJob.setDescription(employScanner.nextLine());    
                break;
            case 3:
            System.out.println("Would you like to add a requirement(1) or change an existing one?(2)");
                int requirementNum = employScanner.nextInt();
                switch(requirementNum) {
                    case 1:
                        System.out.println("Please input the new requirement");
                        matchedJob.addRequirement(employScanner.nextLine());
                        break;
                    case 2:
                        ArrayList<String> allRequirements = matchedJob.getRequirements();
                        for (int i = 0; i<allRequirements.size()-1; i++) {
                            System.out.print((i+1) + " )" + allRequirements.get(i));
                        }
                        System.out.println("Please input the number of the requirement you would like to edit");
                        requirementNum = employScanner.nextInt()+1;
                        if (requirementNum > allRequirements.size() || requirementNum <= 0) {
                            System.out.println("Sorry, that number is not in range");
                            break;
                        }
                        System.out.println("Please input the new Requirement");
                        allRequirements.set(requirementNum, employScanner.nextLine());
                        matchedJob.setRequirements(allRequirements);
                        break;
                    default:
                        System.out.println("Please input a correct option");
                        break;
                }
                break;
            case 4:
            System.out.println("Please input the new Hourly Wage");
                matchedJob.setWage(employScanner.nextDouble());
                break;
            case 5:
            System.out.println("Please input the new Status");
            String stats;
            boolean rightThing = false;
                while (!rightThing) {
                    stats = employScanner.nextLine();
                    if (stats.equalsIgnoreCase(JobPostingStatus.NA.toString())) {
                        matchedJob.setStatus(JobPostingStatus.NA);
                        rightThing = true;
                    } else if (stats.equalsIgnoreCase(JobPostingStatus.OPEN.toString())) {
                        matchedJob.setStatus(JobPostingStatus.OPEN);
                        rightThing = true;
                    } else if (stats.equalsIgnoreCase(JobPostingStatus.PENDING.toString())) {
                        matchedJob.setStatus(JobPostingStatus.PENDING);
                        rightThing = true;
                    } else if (stats.equalsIgnoreCase(JobPostingStatus.CLOSED.toString())) {
                        matchedJob.setStatus(JobPostingStatus.CLOSED);
                        rightThing = true;
                    } else {
                        System.out.println("Invalid Status");
                    }
                }
                
                break;
            case 0:
            System.out.println("Exiting JobEdit");
                keepLooping = false;
                break;
            default:
            System.out.println("Exiting JobEdit");
                break;
        }
    }
}
/**
 * Prints target job from the employer's posted jobs
 * @param employer
 */
private void viewJob (Employer employer) {
    if (Database.getInstance().getPostingsByEmployer(employer) == null) {
        System.out.println("Sorry, there are no jobs to review!");
        return;
    }
    ArrayList<JobPosting> allPostings = Database.getInstance().getPostingsByEmployer(employer);
    for (int i = 0; i<allPostings.size()-1; i++) {
        System.out.print((i+1) + ")");
        System.out.println(allPostings.get(i).getJobTitle());
    }
    System.out.println("Select the job you'd like to view by its number");
    Integer jobNumber = employScanner.nextInt();
    System.out.println(allPostings.get(jobNumber-1));
    System.out.println("Current Applicants: ");
    ArrayList<Student> applicants = allPostings.get(jobNumber-1).getApplicants();
    for( Student i : applicants) {
        System.out.println(i);
    }
}
/**
 * Creates a new job postings based on user input
 * @param employer
 */
private void makeJob (Employer employer) {
    dataTypes.JobPosting.Builder newJob = new JobPosting.Builder();
    System.out.println("Title");
    String jobTitle = employScanner.nextLine();
    newJob.jobTitle(jobTitle);
    newJob.employer(employer);
    System.out.println("Description");
    String description = employScanner.nextLine();
    newJob.description(description);
    System.out.println("Requirements");
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
    System.out.println("Current Status (NA, OPEN, PENDING, CLOSED)");
    newJob.status(JobPostingStatus.NA);
    String stats;
    boolean rightThing = false;
        while (!rightThing) {
            stats = employScanner.nextLine();
            if (stats.equalsIgnoreCase(JobPostingStatus.NA.toString())) {
                newJob.status(JobPostingStatus.NA);
                rightThing = true;
            } else if (stats.equalsIgnoreCase(JobPostingStatus.OPEN.toString())) {
                newJob.status(JobPostingStatus.OPEN);
                rightThing = true;
            } else if (stats.equalsIgnoreCase(JobPostingStatus.PENDING.toString())) {
                newJob.status(JobPostingStatus.PENDING);
                rightThing = true;
            } else if (stats.equalsIgnoreCase(JobPostingStatus.CLOSED.toString())) {
                newJob.status(JobPostingStatus.CLOSED);
                rightThing = true;
            } else {
                System.out.println("Invalid Status");
            }
        }
    
    Database.getInstance().addPosting(newJob.build());
}

/**
 * Creates a menu for the employer to edit any of the students that have applied 
 * to their job postings
 * @param employer
 */
private void doStudentReviewMenu(Employer employer) {
    if (Database.getInstance().getPostingsByEmployer(employer) == null) {
        System.out.println("Sorry, you have no postings to review students from!");
        return;
    }
    for (JobPosting i : Database.getInstance().getPostingsByEmployer(employer)) {
        System.out.println("Job Posting: ");
        System.out.println(i.getJobTitle());
        for (Student j : i.getApplicants()) {
            System.out.println(j.getFirstName() + " " + j.getLastName());
        }
    }
    boolean studentFound = false;
    Student foundStudent = null;
    while (!studentFound) {
        System.out.println("Please input a student's first name, or (0) to quit");
        String studentName = employScanner.nextLine();
        if (studentName.equals("0")) {
            employScanner.close();
            return;
        }
        for (JobPosting i : Database.getInstance().getPostingsByEmployer(employer)) {
            for (Student j : i.getApplicants()) {
                if(studentName.equalsIgnoreCase(j.getFirstName())){
                    studentFound = true;
                    foundStudent = j;
                }
            }
        }
    }
    if(foundStudent == null) {
        employScanner.close();
        return;
    }
    boolean keepLooping = true;
    while (keepLooping) {
        int employerInput = employScanner.nextInt();
        System.out.println("Would you like to (1) create a review (2) edit a review or (0) quit");
        switch (employerInput) {
            case 1:
                createNewReview(foundStudent, employer);
                break;
            case 2:
                editReview(foundStudent);
                break;
            case 0:
                System.out.println("Exiting Review Menu");
                keepLooping = false;
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }

    }
}

/**
 * Creates a new review for a student from an employer
 * @param foundStudent
 * @param employer
 */
private void createNewReview(Student foundStudent, Employer employer) {
    Builder newReview = new Review.Builder();
    System.out.println("What do you rate" + foundStudent.getFirstName());
    Integer rating = employScanner.nextInt();
    newReview.rating(rating);
    System.out.println("What would you like to comment?");
    String comment = employScanner.nextLine();
    newReview.comment(comment);
    newReview.reviewer(foundStudent);
    newReview.reviewee(employer);
    Database.getInstance().addReview(newReview.build());
    employScanner.close();
    // for testing
    //System.out.println(newReview.build());
}
/**
 * Allows the user to edit a student's review
 * @param foundStudent
 */
private void editReview(Student foundStudent) {
        ArrayList <Review> selfReviews = Database.getInstance().getReviewsByReviewee(foundStudent);

        for (int i = 0; i < selfReviews.size()-1; i++) {
            System.out.print(i+1 + ")");
            System.out.println(selfReviews.get(i));
        }
        System.out.println("Please Select a Review from the list");
        
        int reviewInput = employScanner.nextInt();
        if(reviewInput<=0 || reviewInput >= selfReviews.size()-1) {
            System.out.println("ERROR: Out of range");
            employScanner.close();
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
            reviewInput = employScanner.nextInt();
            switch (reviewInput) {
                case 1:
                    int rating = employScanner.nextInt();
                    chosenReview.setRating(rating);
                    break;
                case 2:
                    String comment = employScanner.nextLine();
                    chosenReview.setCommment(comment);
                    break;
                case 0: 
                    System.out.println("Exiting Review Mode");
                    employScanner.close();
                    return;
            }
        }
        employScanner.close();
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    public static void doSignup() {
        Scanner input = new Scanner(System.in);
        System.out.print("Username:");
        String username = input.nextLine();
        System.out.print("Password:");
        String password = input.nextLine();
        System.out.print("First Name:");
        String firstName = input.nextLine();
        System.out.print("Last Name:");
        String lastName = input.nextLine();
        System.out.print("Email:");
        String email = input.nextLine();
        System.out.print("Company Name:");
        String company = input.nextLine();

        Employer newEmployer = new Employer.Builder().username(username)
                                                    .password(password)
                                                    .firstName(firstName)
                                                    .lastName(lastName)
                                                    .email(email)
                                                    .company(company)
                                                    .build();
        Database.getInstance().addUser(newEmployer);
        input.close();
    }
}
