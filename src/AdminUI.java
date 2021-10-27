
import java.util.ArrayList;
import java.util.Scanner;

import dataTypes.Admin;
import dataTypes.Student;
import dataTypes.User;
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
<<<<<<< HEAD
            System.out.println("(1) Access user accounts"); 
            System.out.println("(2) Access Job Postings");
            System.out.println("(3) Access reviews");
            System.out.println("(4) Exit the program ");
=======
            //System.out.println("(1) Access as another user"); // how would this be stated? go to user X and edit as if they're that person
            System.out.println("(1) Enter User Approval Mode");
            System.out.println("(0) Exit Neurotic Job Search");
>>>>>>> e7d3b79b86afb0fbea1d73e5c6ba48f2c0dcbfed
            System.out.print("What would you like to do: ");
            int option = input.nextInt();
            switch(option) {
                case 1:
<<<<<<< HEAD
                    accessUsers(admin);
                    break;
                case 2:
                    accessPostings(admin);
=======
                    approvalMenu(admin);
>>>>>>> e7d3b79b86afb0fbea1d73e5c6ba48f2c0dcbfed
                    break;
                case 3:
                    accessReviews(admin);
                    break;
                case 4:
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
<<<<<<< HEAD

    private void accessUsers(Admin admin) {
        Scanner input = new Scanner (System.in);
        UserDatabase userDatabase = UserDatabase.getInstance();
        boolean access = true;
        int option;
        while(access) {
            System.out.println("Which users would you like to access?");
            System.out.println("(1) Students");
            System.out.println("(2) Employers");
            System.out.println("(3) Professors");
            System.out.println("(4) Exit to main menu");
            option = input.nextInt();
            switch(option) {
                case 1:
                    runStudents(admin, userDatabase);
                    break;
                case 2:
                    accessPostings(admin);
                    break;
                case 3:
                    accessReviews(admin);
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

    private void runStudents(Admin admin, UserDatabase userDatabase) {
        Scanner input = new Scanner (System.in);
        boolean go = true;
        int option;
        String username;
        User search;
        while(go) {
             System.out.println("What would you like to do with the Student users?");
            System.out.println("(1) See all of the students");
            System.out.println("(2) Search for a student");
            System.out.println("(3) Remove a student");
            System.out.println("(4) Create a new student account");
            System.out.println("(5) Exit student menu");
            option = input.nextInt();
            switch(option) {
                case 1:
                    ArrayList<Student> students = userDatabase.getStudents();
                    for(Student student: students)
                        System.out.println(student); // TODO implement toString method in students
                    break;
                case 2:
                    System.out.println("Please enter the students username");
                    username = input.nextLine();
                    search = userDatabase.findByUsername(username);
                    if(search!= null)
                    {
                        System.out.println("That student exists, here is their information:");
                        System.out.println(search); // TODO implement toString method in users
                    } else
                        System.out.println("A student with that username does not exist!");
                    break;
                case 3:
                    System.out.println("Please enter the students username that you would like to delete!");
                    username = input.nextLine();
                    search = userDatabase.findByUsername(username);
                if(search!= null)
                {
                    System.out.println("Here is that student's information:");
                    System.out.println(search); // TODO implement toString method in users
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

    private void accessPostings(Admin admin) {

    }

    private void accessReviews(Admin admin) {

    }
    /*private void disguise(Admin admin) { // TODO (from brenn10): which requirement is this for?
        // ask for what type of user you want to be as
        System.out.println("WARNING: you must re-sign-in to access other Admin roles\n\n"
                        +"What type of user would you like to sign into?");
        // show all users of that type, ask which one you want to access
        System.out.println("Which user would you like to disguise yourself as?");
        // set current user to that, run that verification type
    }
=======
>>>>>>> e7d3b79b86afb0fbea1d73e5c6ba48f2c0dcbfed

    private void approvalMenu(Admin admin) {
        // show all users not approved OR everyone
        System.out.println("Would you like to view all users or just the unnaproved ones?");
        // select a user, ask for either delete or approve
        System.out.println("Which user would you like to edit?");

        System.out.println("Are you SURE you want to remove that user(Y)? \n" +
                            "You can always unnaprove them instead!");
        // confirm deletion
    }*/

    
}
