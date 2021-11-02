
import java.util.ArrayList;
import java.util.Scanner;

import dataTypes.Admin;
import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Professor;
import dataTypes.Review;
import dataTypes.Student;
import dataTypes.User;
import database.Database;
import enums.Major;

/**
 * AdminUI Runs the admin type user
 * 
 * @author Stella Garcia
 */
public class AdminUI {

    /**
     * Admin Does everything that each other would do view as prof, stu, empl
     * 
     * PLUS Approve User View Unapproved Users Delete User
     */
    Admin admin;
    Scanner input = new Scanner(System.in);

    /**
     * The main menu for the administrator user
     * 
     * @param admin what admin is access the program
     */
    public void doMainMenu(Admin admin) {
        this.admin = admin;
        System.out.println("Welcome " + admin.getFirstName() + " " + admin.getLastName());
        boolean doMenu = true;
        int option;
        while (doMenu) {
            /*
             * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
             */
            System.out.println("(1) Access User accounts");
            System.out.println("(2) Look through Unapproved users");
            System.out.println("(3) Access Job Postings");
            System.out.println("(4) Access reviews");
            System.out.println("(5) Create a new administrator account");
            System.out.println("(6) Exit the program");
            System.out.print("What would you like to do: ");
            option = Integer.parseInt(input.nextLine());
            switch (option) {
            case 1:
                accessUsers(); // sub menu
                break;
            case 2:
                approvalMenu(); // sub menu
                break;
            case 3:
                accessPostings(); // sub menu
                break;
            case 4:
                accessReviews(); // sub menu
                break;
            case 5:
                makeAdmin(); // sub menu
                break;
            case 6:
                doMenu = false;
                break;
            default:
                System.out.println("Invalid option " + option);
            }
        }

        System.out.print("\033[H\033[2J");
        System.out.flush(); // clearing the screen
        System.out.println("Logging you out, have a great day!");
        // input.close();
    }

    /**
     * The user access part of the admin (does not include creating a new admin
     * account)
     */
    private void accessUsers() {
        boolean access = true;
        int option;
        while (access) {
            /*
             * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
             */
            System.out.println("Which users would you like to access?");
            System.out.println("(1) Students");
            System.out.println("(2) Employers");
            System.out.println("(3) Professors");
            System.out.println("(4) Exit to main menu");
            option = Integer.parseInt(input.nextLine());
            switch (option) {
            case 1:
                runStudents(); // sub menu
                break;
            case 2:
                runEmployers(); // sub menu
                break;
            case 3:
                runProfessors(); // sub menu
                break;
            case 4:
                access = false;
                break;
            default:
                System.out.println("Invalid option " + option);
            }
        }
        /*
         * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
         */
        System.out.println("Returning to main menu!");
    }

    /**
     * Handles the student access part for the admin
     */
    private void runStudents() {
        boolean go = true;
        int option;
        String username;
        User search;
        while (go) {
            /*
             * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
             */
            System.out.println("What would you like to do with the Student users?");
            System.out.println("(1) See all of the students");
            System.out.println("(2) Search for a student");
            System.out.println("(3) Remove a student");
            System.out.println("(4) Create a new student account");
            System.out.println("(5) Exit student menu");
            option = Integer.parseInt(input.nextLine());
            switch (option) {
            case 1:
                ArrayList<Student> students = Database.getInstance().getStudents();
                for (Student student : students)
                    System.out.println(student);
                break;
            case 2:
                System.out.println("Please enter the students username:");
                username = input.nextLine();
                search = Database.getInstance().findByUsername(username);
                if (search != null) {
                    System.out.println("That student exists, here is their information:");
                    System.out.println(search);
                } else
                    System.out.println("A student with that username does not exist!");
                break;
            case 3:
                System.out.println("Please enter the students username that you would like to delete!");
                username = input.nextLine();
                search = Database.getInstance().findByUsername(username);
                if (search != null) {
                    System.out.println("Here is that student's information:");
                    System.out.println(search);
                    System.out.println("Deleting student...");
                    admin.removeUser(search);

                } else
                    System.out.println("A student with that username does not exist!");
                break;
            case 4:
                System.out.println("Please enter a username for the student:");
                User userTest;
                String userName;
                do {
                    userName = input.nextLine();
                    userTest = Database.getInstance().findByUsername(userName);
                    if (userTest != null)
                        System.out.println("That username is taken, please try another!");
                } while (userTest != null);

                System.out.println("Please enter a password for the student:");
                String password = input.nextLine();
                System.out.println("Please enter the student's email:");
                String email = input.nextLine();
                System.out.println("Please enter the student's first name:");
                String firstName = input.nextLine();
                System.out.println("Please enter the student's last name:");
                String lastName = input.nextLine();
                System.out.println("Please enter the student's major");
                System.out.println("(0) for Computer Science");
                System.out.println("(1) for Computer Engineering");
                System.out.println("(2) for Integrated Information Technology");
                System.out.println("(3) for Computer Information Systems");
                System.out.println("Enter anything else for undecided");
                int majorSelect = Integer.parseInt(input.nextLine());
                input.nextLine();
                Major major;
                switch (majorSelect) {
                case 1:
                    major = Major.COMPUTER_SCIENCE;
                case 2:
                    major = Major.COMPUTER_ENGINEERING;
                case 3:
                    major = Major.INTEGRATED_INFORMATION_TECHNOLOGY;
                case 4:
                    major = Major.COMPUTER_INFORMATION_SYSTEMS;
                default:
                    major = Major.NA;
                }
                Student student = new Student.Builder().username(userName).password(password).email(email)
                        .firstName(firstName).lastName(lastName).approved(true).major(major).createdResume(false)
                        .employments(null).educations(null).technicalSkills(null).averageRating(0).removed(false)
                        .build();
                admin.addUser(student);
                System.out.println("A new student account @" + userName + " has been created");
                break;
            case 5:
                go = false;
                break;
            default:
                System.out.println("Invalid option " + option);
            }
        }
        /*
         * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
         */
        System.out.println("Exiting student menu!");
    }

    /**
     * Handles the employer access part for the admin
     */
    private void runEmployers() {
        boolean go = true;
        int option;
        String username;
        User search;
        while (go) {
            /*
             * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
             */
            System.out.println("What would you like to do with the Employer users?");
            System.out.println("(1) See all of the employers");
            System.out.println("(2) Search for a employer");
            System.out.println("(3) Remove an employer");
            System.out.println("(4) Create a new employer account");
            System.out.println("(5) Exit employer menu");
            option = Integer.parseInt(input.nextLine());
            switch (option) {
            case 1:
                ArrayList<Employer> employers = Database.getInstance().getEmployers();
                for (Employer employer : employers)
                    System.out.println(employer);
                break;
            case 2:
                System.out.println("Please enter the employer's username:");
                username = input.nextLine();
                search = Database.getInstance().findByUsername(username);
                if (search != null) {
                    System.out.println("That employer exists, here is their information:");
                    System.out.println(search);
                } else
                    System.out.println("An employer with that username does not exist!");
                break;
            case 3:
                System.out.println("Please enter the employer's username that you would like to delete!");
                username = input.nextLine();
                search = Database.getInstance().findByUsername(username);
                if (search != null) {
                    System.out.println("Here is that employers's information:");
                    System.out.println(search);
                    System.out.println("Deleting employer...");
                    admin.removeUser(search);

                } else
                    System.out.println("A employer with that username does not exist!");
                break;
            case 4:
                System.out.println("Please enter a username for the employer:");
                User userTest;
                String userName;
                do {
                    userName = input.nextLine();
                    userTest = Database.getInstance().findByUsername(userName);
                    if (userTest != null)
                        System.out.println("That username is taken, please try another!");
                } while (userTest != null);
                System.out.println("Please enter a password for the employer:");
                String password = input.nextLine();
                System.out.println("Please enter the employer's email:");
                String email = input.nextLine();
                System.out.println("Please enter the employer's first name:");
                String firstName = input.nextLine();
                System.out.println("Please enter the employer's last name:");
                String lastName = input.nextLine();
                System.out.println("Please enter the employer's company's name");
                String company = input.nextLine();

                Employer employer = new Employer.Builder().username(userName).password(password).email(email)
                        .firstName(firstName).lastName(lastName).approved(true).company(company).averageRating(0)
                        .removed(false).build();
                admin.addUser(employer);
                System.out.println("A new employer account @" + userName + " has been created");
                break;
            case 5:
                go = false;
            default:
                System.out.println("Invalid option " + option);
            }
        }
        /*
         * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
         */
        System.out.println("Exiting employer menu!");
    }

    /**
     * Handles the professor access part for the admin
     */
    private void runProfessors() {
        boolean go = true;
        int option;
        String username;
        User search;
        while (go) {
            /*
             * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
             */
            System.out.println("What would you like to do with the Professor users?");
            System.out.println("(1) See all of the professors");
            System.out.println("(2) Search for a professor");
            System.out.println("(3) Remove a professor");
            System.out.println("(4) Create a new professor account");
            System.out.println("(5) Exit professor menu");
            option = Integer.parseInt(input.nextLine());
            switch (option) {
            case 1:
                ArrayList<Professor> professors = Database.getInstance().getProfessor();
                for (Professor prof : professors)
                    System.out.println(prof);
                break;
            case 2:
                System.out.println("Please enter the professors username:");
                username = input.nextLine();
                search = Database.getInstance().findByUsername(username);
                if (search != null) {
                    System.out.println("That professor exists, here is their information:");
                    System.out.println(search);
                } else
                    System.out.println("A professor with that username does not exist!");
                break;
            case 3:
                System.out.println("Please enter the professor's username that you would like to delete!");
                username = input.nextLine();
                search = Database.getInstance().findByUsername(username);
                if (search != null) {
                    System.out.println("Here is that professor's information:");
                    System.out.println(search);
                    System.out.println("Deleting professor...");
                    admin.removeUser(search);

                } else
                    System.out.println("A professor with that username does not exist!");
                break;
            case 4:
                System.out.println("Please enter a username for the professor:");
                User userTest;
                String userName;
                do {
                    userName = input.nextLine();
                    userTest = Database.getInstance().findByUsername(userName);
                    if (userTest != null)
                        System.out.println("That username is taken, please try another!");
                } while (userTest != null);

                System.out.println("Please enter a password for the professor:");
                String password = input.nextLine();
                System.out.println("Please enter the professor's email:");
                String email = input.nextLine();
                System.out.println("Please enter the professor's first name:");
                String firstName = input.nextLine();
                System.out.println("Please enter the professor's last name:");
                String lastName = input.nextLine();

                Professor professor = new Professor.Builder().username(userName).password(password).email(email)
                        .firstName(firstName).lastName(lastName).approved(true).removed(false).build();
                admin.addUser(professor);
                System.out.println("A new professor account @" + userName + " has been created");
                break;
            case 5:
                go = false;
            default:
                System.out.println("Invalid option " + option);
            }
        }
        /*
         * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
         */
        System.out.println("Exiting professor menu!");
    }

    /**
     * The admin can go through unapproved users and either approve them or not The
     * admin also gets the option to delete users they don't approve or leave them
     * as unapproved
     */
    private void approvalMenu() {
        /*
         * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
         */
        System.out.println("Lets go through all of the pending approval requests");
        System.out.println("Enter 'STOP' at any time to quit");
        ArrayList<User> unapprovedUsers = Database.getInstance().getUnapprovedUsers();
        for (User user : unapprovedUsers) {
            System.out.println("The following user is waiting for approval");
            System.out.println(user);
            System.out.println("Would you like to approve them? Enter (Y) for yes and (N) for no");
            String answer = input.nextLine();
            answer = answer.toUpperCase();
            if (answer.equals("STOP"))
                return;
            if (answer.equals("Y")) {
                System.out.println("User approved!");
                user.setApproved(true);
            } else {
                System.out.println("User not approved!");
                System.out.println("Would you like to remove unapproved user? Enter (Y) for yes and (N) for no");
                answer = input.nextLine();
                answer = answer.toUpperCase();
                if (answer.equals("STOP"))
                    return;
                if (answer.equals("Y")) {
                    System.out.println("Removing user");
                    admin.removeUser(user);
                } else {
                    System.out.println("Leaving user as an unapproved user");
                }

            }
        }
    }

    /**
     * This is the job posting access part for the admin
     */
    private void accessPostings() {
        boolean go = true;
        int option;
        while (go) {
            /*
             * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
             */
            System.out.println("What would you like to do with the job postings?");
            System.out.println("(1)Look through all postings");
            System.out.println("(2) Search for a job posting");
            System.out.println("(3) Remove a job posting");
            System.out.println("(4) View removed job postings");
            System.out.println("(5) Return to the main menu");
            option = Integer.parseInt(input.nextLine());
            switch (option) {
            case 1:
                ArrayList<JobPosting> postings = Database.getInstance().getPostings();
                for (JobPosting posting : postings)
                    System.out.println(posting);
                break;
            case 2:
                System.out.println("Please enter the employer's username who posted the job");
                String employerUser = input.nextLine();
                User employer = Database.getInstance().findByUsername(employerUser);
                if (employer != null) {
                    ArrayList<JobPosting> postingByUser = Database.getInstance()
                            .getPostingsByEmployer((Employer) employer);
                    if (postingByUser != null) {
                        System.out.println("Here are the postings by @" + employerUser);
                        for (JobPosting posting : postingByUser)
                            System.out.println(posting);
                    } else {
                        System.out.println("That user has not posted any jobs");
                    }
                } else {
                    System.out.println("No user exists with that name");
                }
                break;
            case 3:
                System.out.println(
                        "To remove a job posting, we need the Employer's username and the title of the postion");
                System.out.println("What is the Employer's username?");
                String user = input.nextLine();
                System.out.println("What is the job's title?");
                String title = input.nextLine();
                User userOf = Database.getInstance().findByUsername(user);
                if (userOf != null) {
                    JobPosting toRemove = Database.getInstance().getPostingByEmployerAndTitle(user, title);
                    if (toRemove != null) {
                        System.out.println("Removing...");
                        admin.removeJobPosting(toRemove);
                    } else {
                        System.out.println(
                                "A job posting made by @" + user + " with the title '" + title + "' does not exist!");
                    }
                } else {
                    System.out.println("User @" + user + " does not exist!");
                }
                break;
            case 4:
                ArrayList<JobPosting> removedPostings = Database.getInstance().getRemovedPostings();
                for (JobPosting posting : removedPostings)
                    System.out.println(posting);
                break;
            case 5:
                go = false;
            default:
                System.out.println("Invalid option " + option);
            }
        }
        /*
         * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
         */
        System.out.println("Returning to main menu!");
    }

    /**
     * This is the review access part for the admin
     */
    private void accessReviews() {
        boolean go = true;
        int option;
        int option2;
        while (go) {
            /*
             * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
             */
            System.out.println("What would you like to do with the reviews?");
            System.out.println("(1) See all reviews");
            System.out.println("(2) Search for a review");
            System.out.println("(3) Remove a review");
            System.out.println("(4) View removed reviews");
            System.out.println("(5) Return to main menu");
            option = Integer.parseInt(input.nextLine());
            switch (option) {
            case 1:
                System.out.println("Here are all of the reviews");
                ArrayList<Review> reviews = Database.getInstance().getReviews();
                for (Review review : reviews)
                    System.out.println(review);
                break;
            case 2:
                String username;
                User user;
                ArrayList<Review> reviewFound;
                System.out.println("How would you like to search?");
                System.out.println("(1) By reviewer");
                System.out.println("(2) By reviewee");
                option2 = Integer.parseInt(input.nextLine());
                switch (option2) {
                case 1:
                    System.out.println("Please enter the username for the reviewer");
                    username = input.nextLine();
                    user = Database.getInstance().findByUsername(username);
                    if (username != null) {
                        reviewFound = Database.getInstance().getReviewsByReviewer(user);
                        if (reviewFound != null) {
                            System.out.println("Here are all reviews made by @" + username);
                            for (Review review : reviewFound)
                                System.out.println(review);
                        } else {
                            System.out.println("@" + username + " has not made any reviews");
                        }
                    } else {
                        System.out.println("There is no user with that username");
                    }
                    break;
                case 2:
                    System.out.println("Please enter the username for the reviewee");
                    username = input.nextLine();
                    user = Database.getInstance().findByUsername(username);
                    if (username != null) {
                        reviewFound = Database.getInstance().getReviewsByReviewee(user);
                        if (reviewFound != null) {
                            System.out.println("Here are all reviews made about @" + username);
                            for (Review review : reviewFound)
                                System.out.println(review);
                        } else {
                            System.out.println("@" + username + " has not had any reviews made about them");
                        }
                    } else {
                        System.out.println("There is no user with that username");
                    }

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
                Review toRemove = Database.getInstance().getReviewByRevieweeAndReviewer(reviewerUser,
                        revieweeUser);
                if (toRemove != null) {
                    System.out.println("Removing...");
                    admin.removeReviews(toRemove);
                } else {
                    System.out.println("@" + reviewerUser + " has not made a review about @" + revieweeUser);
                }
                break;
            case 4:
                System.out.println("Here are all of the removed reviews");
                ArrayList<Review> removedReviews = Database.getInstance().getRemovedReviews();
                for (Review review : removedReviews)
                    System.out.println(review);
                break;
            case 5:
                go = false;
            default:
                System.out.println("Invalid option " + option);
            }
        }
        /*
         * System.out.print("\033[H\033[2J"); System.out.flush(); // clearing the screen
         */
        System.out.println("Returning to main menu!");
    }

    /**
     * This allows existing admins to create new admins
     */
    private void makeAdmin() {
        System.out.println("Please enter a username for the new administrator:");
        User userTest;
        String userName;
        do {
            userName = input.nextLine();
            userTest = Database.getInstance().findByUsername(userName);
            if (userTest != null)
                System.out.println("That username is taken, please try another!");
        } while (userTest != null);

        System.out.println("Please enter a password:");
        String password = input.nextLine();
        System.out.println("Please enter the administrator's email:");
        String email = input.nextLine();
        System.out.println("Please enter the adminstrator's first name:");
        String firstName = input.nextLine();
        System.out.println("Please enter the adminstrator's last name:");
        String lastName = input.nextLine();

        Admin adminToAdd = new Admin.Builder().username(userName).password(password).email(email).firstName(firstName)
                .lastName(lastName).approved(true).build();
        admin.addUser(adminToAdd);
        System.out.println("A new administrator account @" + userName + " has been created");
    }

}