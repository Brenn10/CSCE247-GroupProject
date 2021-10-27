
import java.util.Scanner;

import dataTypes.Admin;

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
            System.out.println("(1) Access as another user"); // how would this be stated? go to user X and edit as if they're that person
            System.out.println("(2) Enter User Approval Mode");
            System.out.println("(0) Exit Neurotic Job Search");
            System.out.print("What would you like to do: ");
            int option = input.nextInt();
            switch(option) {
                case 1:
                    disguise(admin);
                    break;
                case 2:
                    approvalMenu(admin);
                    break;
                case 0:
                    doMenu = false;
                    break;
                default:
                    System.out.println("Invalid option " + option);
            }
        }
        input.close();
    }
    private void disguise(Admin admin) { // TODO (from brenn10): which requirement is this for?
        // ask for what type of user you want to be as
        System.out.println("WARNING: you must re-sign-in to access other Admin roles\n\n"
                        +"What type of user would you like to sign into?");
        // show all users of that type, ask which one you want to access
        System.out.println("Which user would you like to disguise yourself as?");
        // set current user to that, run that verification type
    }

    private void approvalMenu(Admin admin) {
        // show all users not approved OR everyone
        System.out.println("Would you like to view all users or just the unnaproved ones?");
        // select a user, ask for either delete or approve
        System.out.println("Which user would you like to edit?");

        System.out.println("Are you SURE you want to remove that user(Y)? \n" +
                            "You can always unnaprove them instead!");
        // confirm deletion
    }
}
