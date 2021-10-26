import java.util.Scanner;

public class UserInterface {
    private JobSystem jobSystem;
    public UserInterface() {
    }

    public void run() {
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
       if (jobSystem.getCurrentUser() instanceof Professor) {
           ProfInterface profInterface = new ProfInterface(jobSystem);
           profInterface.doProf();
       }else if (jobSystem.getCurrentUser() instanceof Student) {
            StudentInterface studentInterface = new StudentInterface(jobSystem);
            studentInterface.doStudent();
       } else if (jobSystem.getCurrentUser() instanceof Employer) {
            EmployInterface employInterface = new EmployInterface(jobSystem);
            employInterface.doEmpl();
       } else if (jobSystem.getCurrentUser() instanceof Administrator) {
            AdminInterface adminInterface = new AdminInterface(jobSystem);
            adminInterface.doAdmin();
       } 
       System.out.print("\033[H\033[2J");
       System.out.flush();
       System.out.print("Thank you for using Neurotic Job Search!");
       input.close();
    }
    }


}
