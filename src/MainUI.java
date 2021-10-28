
import java.util.Scanner;

import dataTypes.Admin;
import dataTypes.Employer;
import dataTypes.Professor;
import dataTypes.Student;
import dataTypes.User;

public class MainUI {
    public void doMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Neurotic Job Search!");
        System.out.println("(1) Login\n(2) Sign Up\n(0)Exit");

        boolean doLoop = true;
        while (doLoop) {
            int userSelection = scanner.nextInt();
            scanner.nextLine();
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
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = JobSystem.getInstance().login(username, password);
        if (user == null) {
            System.out.println("Invalid username or password. Please try again.");
        } else if (user instanceof Student) {
            new StudentUI((Student) user).doMainMenu();
        } else if (user instanceof Employer) {
            new EmployerUI().doMainMenu((Employer) user); // TODO: inconsistant constructors
        } else if (user instanceof Admin) {
            new AdminUI().doMainMenu((Admin) user); // TODO: inconsistant constructor
        } else if (user instanceof Professor) {
            new ProfessorUI().doMainMenu((Professor) user);// TODO: inconsistant constructor
        } else {
            System.out.println("Invalid user type.");
        }
        scanner.close();
    }

    public void doSignup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you an...\n");
        System.out.println("1) Student");
        System.out.println("2) Employer");
        System.out.println("3) Professor");
        System.out.print("Your input: ");
        int input = scanner.nextInt();

        switch (input) {
        case 1:
            StudentUI.doSignup();
            break;
        case 2:
            EmployerUI.doSignup();
            break;
        case 3:
            ProfessorUI.doSignup();
            break;
        default:
            System.out.println("Invalid selection. Please try again.");
            break;
        }
        scanner.close();
    }

}
