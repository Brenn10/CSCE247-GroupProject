import java.util.Scanner;

public class UserInterface {
    private JobSystem jobSystem;
    // when created, run the program
    public UserInterface() {
        Scanner input = new Scanner (System.in);
        int inputter;
        String login = "";
        boolean availableNext = false;
     System.out.println("Welcome to the Neurotic Job Search!");
     System.out.println("Would you like to \n(1) Login or \n (2) Sign Up?");
       // welcome, login/signup
       while (!availableNext) {
           inputter = input.nextInt();
           
       if (inputter == 1) {
           // do login
           login = input.nextLine(); // username
           String password = input.nextLine();
           availableNext = true; // delete once login is finished
           //availableNext = jobSystem.login(login, password);
       } else if (inputter == 2) {
           // do signup
           login = input.nextLine(); // username
           String password = input.nextLine();
           availableNext = true; // delete once signup is finished
           //availableNext = jobSystem.signup(login, password);
       } else {
           System.out.println("Please select a valid number\n");
       }
       System.out.print("\033[H\033[2J");
       System.out.flush();
       // get user verification for current user
       int verification = jobSystem.getVerify();

       // NOTE lookup how to do a switch case to make this better
       if (verification == 1) {
           doProf();
       }else if (verification == 2) {
          doStudent();
       } else if (verification == 3) {
           doEmpl();
       } else if (verification == 4) {
           doAdmin();
       } 
       System.out.print("\033[H\033[2J");
       System.out.flush();
       System.out.print("Thank you for using Neurotic Job Search!");
   }
    }
    /**
     * student
     * 
     * View Job Listings -1
     * View Job Applications -2
     * Edit/Create Resume -3 
     * Check Review  -4
     */
    private void doStudent() {
        boolean keepLooping = true;
        Scanner input = new Scanner (System.in);
        int option = 0;
        System.out.println("Welcome Student " + jobSystem.getCurrentUser().username);
        while (keepLooping) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("(1) Enter Resume Editing Mode\n");
            System.out.println("(2) Enter Job Search Mode\n");
            System.out.println("(3) View Reviews\n");
            System.out.println("(0) Exit Neurotic Job Search");
            option = input.nextInt();
            if (option == 0) {
                keepLooping = false;
            } else if(option == 1) {
                // show current resume if availiable, ask which part to edit
                resumeMenu();
                keepLooping = false;
            } else if (option == 2) {
                // show all jobs, ask for specific job name 
                jobMenu();
                keepLooping = false;
            } else if (option == 3) {
                // show all reviews attatched to self
                jobSystem.getSelfReviews();
                keepLooping = false;
            } else {
                System.out.println("ERROR: The number you typed is not an option!\n");
            }
        }
    }
    private void resumeMenu() {
        jobSystem.createResume();
        // ask what the user would like to change (number options)
    }
    private void jobMenu() {
        jobSystem.showAllJobs();
    }
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
    private void doEmpl() {
        boolean keepLooping = true;
        Scanner input = new Scanner (System.in);
        int option = 0;
        System.out.println("Welcome Employer " + jobSystem.getCurrentUser().username);
        while (keepLooping) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("(1) Enter Job Edit Mode\n");
            System.out.println("(2) Enter Student Review Mode\n");
            System.out.println("(0) Exit Neurotic Job Search");
            option = input.nextInt();
            if (option == 0) {
                keepLooping = false;
            } else if(option == 1) {
                // show all self jobs, ask which to edit
                keepLooping = false;
            } else if (option == 2) {
                reviewMenuStudent();
                keepLooping = false;
            } else {
                System.out.println("ERROR: The number you typed is not an option!\n");
            }
        }
    }
    /**
     * Prof
     * 
     * Review Student
     * Edit Review
     * View Reviews (of self to all stu)
     */

    private void doProf() {
        boolean keepLooping = true;
        Scanner input = new Scanner (System.in);
        int option = 0;
        System.out.println("Welcome Professor " + jobSystem.getCurrentUser().username);
        while (keepLooping) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("(1) Enter Student Review Mode\n");
            System.out.println("(0) Exit Neurotic Job Search");
            option = input.nextInt();
            if (option == 0) {
                keepLooping = false;
            } else if(option == 1) {
                reviewMenuStudent();
                keepLooping = false;
            } else {
                System.out.println("ERROR: The number you typed is not an option!\n");
            }
        }
    }
    private void reviewMenuStudent() {
        // same for professors AND Employers
        jobSystem.showAllStudent();
        //ask for specific student
        System.out.println("Please select a student's first name"); // or something similar
        boolean studentNotFound = false;
        
        Scanner studentSelect = new Scanner(System.in);
        String chosenStudent = studentSelect.nextLine();
        jobSystem.rateStudent(chosenStudent); // chosen student
        // loop until 0 chosen or student selected and rated successfully
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
    private void doAdmin() {
        boolean keepLooping = true;
        Scanner input = new Scanner (System.in);
        int option = 0;
        System.out.println("Welcome Admin " + jobSystem.getCurrentUser().username);
        while (keepLooping) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("(1) --ACT AS ANOTHER CLASS--\n"); // how would this be stated? go to user X and edit as if they're that person
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
                keepLooping = false;   
            } else {
                System.out.println("ERROR: The number you typed is not an option!\n");
            }
        }
    }
    private void disguise() {
        // ask for what type of user you want to be as

        // show all users of that type, ask which one you want to access
        // set current user to that, run that verification type
    }
    private void approvalMenu() {
        // show all users not approved OR everyone
        // select a user, ask for either delete or approve
        // confirm deletion
    }
    public static void main(String[] args) {
        UserInterface neurotic = new UserInterface();
    }
}
