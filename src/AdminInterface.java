import java.util.Scanner;

public class AdminInterface {
    private JobSystem jobSystem;

    public AdminInterface (JobSystem jobSystem) {
        this.jobSystem = jobSystem;
    }
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
    public void doAdmin() {
        boolean keepLooping = true;
        Scanner input = new Scanner (System.in);
        int option = 0;
        System.out.println("Welcome Admin " + jobSystem.getCurrentUser().username);
        while (keepLooping) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("(1) Access as another user \n"); // how would this be stated? go to user X and edit as if they're that person
            System.out.println("(2) Enter User Approval Mode\n");
            System.out.println("(0) Exit Neurotic Job Search");
            option = input.nextInt();
            if (option == 0) {
                keepLooping = false;
            } else if(option == 1) {
                disguise();
                keepLooping = false;
            } else if (option == 2) {
                // show all users, ask for deletion, or approval
                approvalMenu();
            } else {
                System.out.println("ERROR: The number you typed is not an option!\n");
            }
        }
        input.close();
    }
    private void disguise() {
        // ask for what type of user you want to be as
        System.out.println("WARNING: you must re-sign-in to access other Admin roles\n\n"
                        +"What type of user would you like to sign into?");
        // show all users of that type, ask which one you want to access
        System.out.println("Which user would you like to disguise yourself as?");
        // set current user to that, run that verification type
    }
    private void approvalMenu() {
        // show all users not approved OR everyone
        System.out.println("Would you like to view all users or just the unnaproved ones?");
        // select a user, ask for either delete or approve
        System.out.println("Which user would you like to edit?");

        System.out.println("Are you SURE you want to remove that user(Y)? \n" +
                            "You can always unnaprove them instead!");
        // confirm deletion
    }
}
