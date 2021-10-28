
import java.util.ArrayList;
import java.util.Scanner;

import dataTypes.Admin;
import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Professor;
import dataTypes.Review;
import dataTypes.Student;
import dataTypes.User;
import database.JobPostingDatabase;
import database.ReviewDatabase;
import database.UserDatabase;

public class AdminUI {
    
    /**
     * Admin 
     * Does everything that each other would do
     * view as prof, stu, empl
     * 
     * PLUS
     * Approve User
     * View Unapproved Users
     * Delete User
     */
    public void doMainMenu(Admin admin) {
        Scanner input = new Scanner (System.in);
        System.out.println("Welcome " + admin.getFirstName() + " " + admin.getLastName());
        boolean doMenu = true;
        while (doMenu) {
            System.out.print("\033[H\033[2J");
            System.out.flush(); // clearing the screen 
            System.out.println("(1) Access User accounts"); 
            System.out.println("(2) Look through Unapproved users"); // Decide on useres?
            System.out.println("(3) Access Job Postings");
            System.out.println("(4) Access reviews");
            System.out.println("(5) Exit the program");
            System.out.print("What would you like to do: ");
            int option = input.nextInt();
            switch(option) {
                case 1:
                    accessUsers(admin);
                    break;
                case 2:
                    approvalMenu(admin); 
                    break;
                case 3:
                    accessPostings(admin);
                    break;
                case 4:
                    accessReviews(admin);
                    break;
                case 5:
                    doMenu = false;
                    break;
                default:
                    System.out.println("Invalid option " + option);
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush(); // clearing the screen 
        System.out.println("Logging you out, have a great day!");
        input.close();
    }

    private void accessUsers(Admin admin) {
        Scanner input = new Scanner (System.in);
        boolean access = true;
        int option;
        while(access) {
            System.out.print("\033[H\033[2J");
            System.out.flush(); // clearing the screen 
            System.out.println("Which users would you like to access?");
            System.out.println("(1) Students");
            System.out.println("(2) Employers");
            System.out.println("(3) Professors");
            System.out.println("(4) Exit to main menu");
            option = input.nextInt();
            switch(option) {
                case 1:
                    runStudents(admin);
                    break;
                case 2:
                    runEmployers(admin);
                    break;
                case 3:
                    runProfessors(admin);
                    break;
                case 4:
                    access = false;
                    break;
                default:
                    System.out.println("Invalid option " + option);
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush(); // clearing the screen 
        System.out.println("Returning to main menu!");
        input.close();
    }

    private void runStudents(Admin admin) {
        Scanner input = new Scanner (System.in);
        boolean go = true;
        int option;
        String username;
        User search;
        while(go) {
            System.out.print("\033[H\033[2J");
            System.out.flush(); // clearing the screen 
            System.out.println("What would you like to do with the Student users?");
            System.out.println("(1) See all of the students");
            System.out.println("(2) Search for a student");
            System.out.println("(3) Remove a student");
            System.out.println("(4) Create a new student account");
            System.out.println("(5) Exit student menu");
            option = input.nextInt();
            switch(option) {
                case 1:
                    ArrayList<Student> students = UserDatabase.getInstance().getStudents();
                    for(Student student: students)
                        System.out.println(student);
                    break;
                case 2:
                    System.out.println("Please enter the students username:");
                    username = input.nextLine();
                    search = UserDatabase.getInstance().findByUsername(username);
                    if(search!= null)
                    {
                        System.out.println("That student exists, here is their information:");
                        System.out.println(search); 
                    } else
                        System.out.println("A student with that username does not exist!");
                    break;
                case 3:
                    System.out.println("Please enter the students username that you would like to delete!");
                    username = input.nextLine();
                    search = UserDatabase.getInstance().findByUsername(username);
                if(search!= null)
                {
                    System.out.println("Here is that student's information:");
                    System.out.println(search); 
                    //TODO will eventually add a "Are you sure you want to delete" type thinge
                    System.out.println("Deleting student...");
                    admin.removeUser(search);

                } else
                    System.out.println("A student with that username does not exist!");
                    break;
                case 4:
                    System.out.println("Please enter a username for the student:");
                    String userName = input.nextLine(); // will eventually add something to check for duplicates 
                    System.out.println("Please enter a password for the student:");
                    String password = input.nextLine();
                    System.out.println("Please enter their email:");
                    String email = input.nextLine();
                    System.out.println("Please enter their first name:");
                    String firstName = input.nextLine();
                    System.out.println("Please enter their last name:");
                    String lastName = input.nextLine();
                    System.out.println("Please enter their major");
                    String major = input.nextLine();
                    // TODO use builder to make student!!
                    break;
                case 5:
                    go = false;
                default:
                    System.out.println("Invalid option " + option);
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush(); // clearing the screen 
        System.out.println("Exiting student menu!");
        input.close();
    }

    private void runEmployers(Admin admin) {
        Scanner input = new Scanner (System.in);
        boolean go = true;
        int option;
        String username;
        User search;
        while(go) {
            System.out.print("\033[H\033[2J");
            System.out.flush(); // clearing the screen 
            System.out.println("What would you like to do with the Employer users?");
            System.out.println("(1) See all of the employers");
            System.out.println("(2) Search for a employer");
            System.out.println("(3) Remove an employer");
            System.out.println("(4) Create a new employer account");
            System.out.println("(5) Exit employer menu");
            option = input.nextInt();
            switch(option) {
                case 1:
                    ArrayList<Employer> employers = UserDatabase.getInstance().getEmployers();
                    for(Employer employer: employers)
                        System.out.println(employer);
                    break;
                case 2:
                    System.out.println("Please enter the employer's username:");
                    username = input.nextLine();
                    search = UserDatabase.getInstance().findByUsername(username);
                    if(search!= null)
                    {
                        System.out.println("That employer exists, here is their information:");
                        System.out.println(search); 
                    } else
                        System.out.println("An employer with that username does not exist!");
                    break;
                case 3:
                    System.out.println("Please enter the employers username that you would like to delete!");
                    username = input.nextLine();
                    search = UserDatabase.getInstance().findByUsername(username);
                if(search!= null)
                {
                    System.out.println("Here is that employers's information:");
                    System.out.println(search); 
                    //TODO will eventually add a "Are you sure you want to delete" type thinge
                    System.out.println("Deleting employer...");
                    admin.removeUser(search);

                } else
                    System.out.println("A employer with that username does not exist!");
                    break;
                case 4:
                    System.out.println("Please enter a username for the student:");
                    String userName = input.nextLine(); // will eventually add something to check for duplicates 
                    System.out.println("Please enter a password for the student:");
                    String password = input.nextLine();
                    System.out.println("Please enter their email:");
                    String email = input.nextLine();
                    System.out.println("Please enter their first name:");
                    String firstName = input.nextLine();
                    System.out.println("Please enter their last name:");
                    String lastName = input.nextLine();
                    System.out.println("Please enter their company's name");
                    String company = input.nextLine();
                    // TODO use builder to make employer!!
                    break;
                case 5:
                    go = false;
                default:
                    System.out.println("Invalid option " + option);
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush(); // clearing the screen 
        System.out.println("Exiting employer menu!");
        input.close();
    }

    private void runProfessors(Admin admin) {
        Scanner input = new Scanner (System.in);
        boolean go = true;
        int option;
        String username;
        User search;
        while(go) {
            System.out.print("\033[H\033[2J");
            System.out.flush(); // clearing the screen 
            System.out.println("What would you like to do with the Professor users?");
            System.out.println("(1) See all of the professors");
            System.out.println("(2) Search for a professor");
            System.out.println("(3) Remove a professor");
            System.out.println("(4) Create a new professor account");
            System.out.println("(5) Exit professor menu");
            option = input.nextInt();
            switch(option) {
                case 1:
                    ArrayList<Professor> professors = UserDatabase.getInstance().getProfessor();
                    for(Professor prof: professors)
                        System.out.println(prof); 
                    break;
                case 2:
                    System.out.println("Please enter the professors username:");
                    username = input.nextLine();
                    search = UserDatabase.getInstance().findByUsername(username);
                    if(search!= null)
                    {
                        System.out.println("That professor exists, here is their information:");
                        System.out.println(search); 
                    } else
                        System.out.println("A professor with that username does not exist!");
                    break;
                case 3:
                    System.out.println("Please enter the professor's username that you would like to delete!");
                    username = input.nextLine();
                    search = UserDatabase.getInstance().findByUsername(username);
                if(search!= null)
                {
                    System.out.println("Here is that professor's information:");
                    System.out.println(search); 
                    //TODO will eventually add a "Are you sure you want to delete" type thinge
                    System.out.println("Deleting professor...");
                    admin.removeUser(search);

                } else
                    System.out.println("A professor with that username does not exist!");
                    break;
                case 4:
                    System.out.println("Please enter a username for the student:");
                    String userName = input.nextLine(); // will eventually add something to check for duplicates 
                    System.out.println("Please enter a password for the student:");
                    String password = input.nextLine();
                    System.out.println("Please enter their email:");
                    String email = input.nextLine();
                    System.out.println("Please enter their first name:");
                    String firstName = input.nextLine();
                    System.out.println("Please enter their last name:");
                    String lastName = input.nextLine();
                    // TODO use builder to make professor!!
                    break;
                case 5:
                    go = false;
                default:
                    System.out.println("Invalid option " + option);
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush(); // clearing the screen 
        System.out.println("Exiting professor menu!");
        input.close();
    }

    private void approvalMenu(Admin admin) {
        System.out.print("\033[H\033[2J");
        System.out.flush(); // clearing the screen 
        Scanner input = new Scanner(System.in);
        System.out.println("Lets go through all of the pending approval requests");
        ArrayList<User> unapprovedUsers = UserDatabase.getInstance().getUnapprovedUsers();
        for(User user: unapprovedUsers) {
            System.out.println("The following user is waiting for approval");
            System.out.println(user); 
            System.out.println("Would you like to approve them? Enter (Y) for yes and (N) for no");
            String answer = input.nextLine();
            answer = answer.toUpperCase();
            if(answer.equals("Y")) {
                System.out.println("User approved!");
                user.setApproved(true);
                UserDatabase.getInstance().updateDatabase();
            } else {
                System.out.println("User not approved!");
                System.out.println("Would you like to remove unapproved user? Enter (Y) for yes and (N) for no");
                answer = input.nextLine();
                answer = answer.toUpperCase();
                if(answer.equals("Y")) {
                    System.out.println("Removing user");
                    admin.removeUser(user);
                } else {
                    System.out.println("Leaving user as an unapproved user");
                }

            }
        }
    }


    private void accessPostings(Admin admin) {
        Scanner input = new Scanner(System.in);
        boolean go = true;
        int option;
        while(go) {
            System.out.print("\033[H\033[2J");
            System.out.flush(); // clearing the screen 
            System.out.println("What would you like to do with the job postings?");
            System.out.println("(1)Look through all postings");
            System.out.println("(2) Search for a job posting");
            System.out.println("(3) Remove a job posting");
            System.out.println("(4) View removed job postings");
            System.out.println("(5) Return to the main menu");
            option = input.nextInt();
            switch(option) {
                case 1:
                    ArrayList<JobPosting> postings = JobPostingDatabase.getInstance().getPostings();
                    for(JobPosting posting: postings)
                        System.out.println(posting);
                    break;
                case 2:
                    //TODO implement
                    break;
                case 3:
                    //TODO implement
                    break;
                case 4:
                ArrayList<JobPosting> removedPostings = JobPostingDatabase.getInstance().getRemovedPostings();
                for(JobPosting posting: removedPostings)
                    System.out.println(posting);
                    break;
                case 5:
                    go = false;
                default:
                System.out.println("Invalid option " + option);
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush(); // clearing the screen 
        System.out.println("Returning to main menu!");
        input.close();
    }

    private void accessReviews(Admin admin) {
        Scanner input = new Scanner(System.in);
        boolean go = true;
        int option;
        int option2;
        while(go) {
            System.out.print("\033[H\033[2J");
            System.out.flush(); // clearing the screen 
            System.out.println("What would you like to do with the reviews?");
            System.out.println("(1) See all reviews");
            System.out.println("(2) Search for a review");
            System.out.println("(3) Remove a review");
            System.out.println("(4) View removed reviews");
            System.out.println("(5) Return to main menu");
            option = input.nextInt();
            switch(option) {
                case 1:
                    System.out.println("Here are all of the reviews");
                    ArrayList<Review> reviews = ReviewDatabase.getInstance().getReviews();
                    for(Review review: reviews)
                        System.out.println(review); 
                    break;
                case 2:
                    String username;
                    User user;
                    ArrayList <Review> reviewFound;
                    System.out.println("How would you like to search?");
                    System.out.println("(1) By reviewer");
                    System.out.println("(2) By reviewee");
                    option2 = input.nextInt();
                        switch(option2) {
                            case 1:
                            System.out.println("Please enter the username for the reviewer");
                            username = input.nextLine();
                            user = UserDatabase.getInstance().findByUsername(username);
                            if(username != null) {
                            reviewFound = ReviewDatabase.getInstance().getReviewsByReviewer(user);
                                if(reviewFound != null) {
                                    System.out.println("Here are all reviews made by @" + username);
                                    for(Review review: reviewFound)
                                            System.out.println(review);
                                } else {
                                System.out.println("@" + username + " has not made any reviews");
                                }
                            } else {
                                System.out.println("There is no user with that username");
                            } //TODO make it so they have the option to search again
                                break;
                            case 2:
                                System.out.println("Please enter the username for the reviewee");
                                username = input.nextLine();
                                user = UserDatabase.getInstance().findByUsername(username);
                                if(username != null) {
                                reviewFound = ReviewDatabase.getInstance().getReviewsByReviewee(user);
                                    if(reviewFound != null) {
                                        System.out.println("Here are all reviews made about @" + username);
                                        for(Review review: reviewFound)
                                            System.out.println(review);
                                    } else {
                                    System.out.println("@" + username + " has not had any reviews made about them");
                                    }
                                } else {
                                    System.out.println("There is no user with that username");
                                } //TODO make it so they have the option to search again
                                
                                break;
                            default:
                            System.out.println("Invalid option " + option);
                        }
                    break;
                case 3:
                    String reviewerUser;
                    String revieweeUser;
                    System.out.println("To remove a review, we need both the reviewer and reviewees usernames");
                    System.out.println("Reviwewer username:");
                    reviewerUser = input.nextLine();
                    System.out.println("Reviewee username: ");
                    revieweeUser = input.nextLine();
                    System.out.println("Searching for review to remove...");
                    Review toRemove = ReviewDatabase.getInstance().getReviewByRevieweeAndReviewer(reviewerUser, revieweeUser);
                    if(toRemove!=null) {
                        System.out.println("Removing...");
                        admin.removeReviews(toRemove);
                    } else {
                        System.out.println("@" + reviewerUser + " has not made a review about @" + revieweeUser);
                    }
                    break;
                case 4:
                        System.out.println("Here are all of the removed reviews");
                    ArrayList<Review> removedReviews = ReviewDatabase.getInstance().getRemovedReviews();
                    for(Review review: removedReviews)
                        System.out.println(review);
                    break;
                case 5:
                    go = false;
                default:
                System.out.println("Invalid option " + option);
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush(); // clearing the screen 
        System.out.println("Returning to main menu!");
        input.close();
    }
  
}
