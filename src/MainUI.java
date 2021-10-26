
import java.util.Scanner;

import dataTypes.Admin;
import dataTypes.Employer;
import dataTypes.Professor;
import dataTypes.Student;
import dataTypes.User;

public class MainUI {
    private JobSystem jobSystem;

    public void doMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Neurotic Job Search!");
        System.out.println("(1) Login\n(2) Sign Up\n(0)Exit");

        boolean doLoop = true;
        while (doLoop) {
            int userSelection = scanner.nextInt();
            switch (userSelection) {
                case 1:
                    doLogin();
                    break;
                case 2:
                    doSignup();
                    break;
                case 0:
                    System.out.println("Thank you for using the Neurotic Job Search!");
                    doLoop = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    public void doLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        User user = jobSystem.login(username, password);
        if (user == null) {
            System.out.println("Invalid username or password. Please try again.");
        } else if (user instanceof Student) {
            new StudentUI().doMainMenu((Student) user);
        } else if (user instanceof Employer) {
            new EmployerUI().doMainMenu((Employer) user);
        } else if (user instanceof Admin) {
            new AdminUI().doMainMenu((Admin) user);
        } else if (user instanceof Professor) {
            new ProfessorUI().doMainMenu((Professor) user);
        } else {
            System.out.println("Invalid user type.");
        }
        scanner.close();
    }

    public void doSignup() {} // TODO: implement
}
