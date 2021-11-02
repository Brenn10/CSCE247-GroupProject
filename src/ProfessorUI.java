
import java.util.Scanner;
import dataTypes.Professor;
import dataTypes.Review;
import database.ReviewDatabase;
import database.UserDatabase;
import dataTypes.Student;
import java.util.ArrayList;
import dataTypes.User;


/**
 * The User Interface for the Professor 
 * @author Ian McDevitt
 */
public class ProfessorUI {
/** 
 * @param professor creating private varible for the current user (professor)
 */
    private Professor professor;
/**
 * create Constructor for professor 
 * @param professor the User object professor
 */
    public ProfessorUI (Professor professor) {
        this.professor = professor;
    }
    public ProfessorUI() {
        
        this.professor = null;
    }
/**
 * taking in the current professor displaying the menu 
 * to see if the user wants to advance into Review Mode
 * @param professor
 */
    public void doMainMenu(Professor professor) {
        boolean keepLooping = true;
        Scanner input = new Scanner (System.in);
        int option = 0;
        System.out.println("Welcome Professor " + professor.getFirstName() + " " + professor.getLastName());
        while (keepLooping) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("(1) Enter Professor Review Mode\n");
            System.out.println("(0) Exit Neurotic Job Search");
            option = input.nextInt();
            if (option == 0) {
                keepLooping = false;
            } else if(option == 1) {
                reviewMenuProfessor(professor);
                keepLooping = false;
            } else {
                System.out.println("ERROR: The number you typed is not an option!\n");
            }
        }
        input.close();
}
/**
 * @param professor takes in the current professor to display the options available 
 * to the professor for reviewing students
 */
private void reviewMenuProfessor(Professor professor) {
    boolean looking = true;
    Scanner input = new Scanner (System.in);
    String studentname = "";
    int choice = 0;
    while(looking) {
        System.out.println("To review a student enter: 1");
        System.out.println("To edit review enter: 2");
        System.out.println("To delete review enter: 3");
        System.out.println("To quit enter: 0");
        choice = input.nextInt();
        switch (choice) {
            case 1:
                ReviewStudent(professor);
                break;
            case 2:
                editReviewStudent(professor);
                break;
            case 3:
                ReviewStudentRemove(professor);
            case 0:
                looking = false;
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
}
/**
 * takes in current professor and adds the a new review to an existing student.
 * @param professor
 */
public void ReviewStudent(Professor professor) {
    User student;
    Review review = null;
    displayAllStudents();
    student = selectStudent();
    review  = addingreview(student, professor);
    ReviewDatabase.getInstance().addReview(review);
}
/**
 * takes in the current professor to select a previously reviewed student and make changes to the sutdent
 * @param professor
 */
public void editReviewStudent(Professor professor) { 
    User student;
    Review review = null;
    displayStudents(professor);
    student = selectStudent();
    review = removeReview(student, professor);
    ReviewDatabase.getInstance().removeReveiw(review);
    review = addingreview(student, professor);
    ReviewDatabase.getInstance().addReview(review);
}
   
/**
 * takes in a student and a professor to remove a student review given by the current professor
 * @param student the student selected by the professor
 * @param professor the current professor
 * @return a review that been removed
 */
public Review removeReview(User student, Professor professor) {
    Review review = null;
    String prof = "";
    String stu = "";
    stu = student.getUsername();
    prof = professor.getUsername();
    review = ReviewDatabase.getInstance().getReviewByRevieweeAndReviewer(prof,stu);
    return review;
}
/**
 * Takes in current professor to remove a student review made by the professor
 * @param professor
 */
public void ReviewStudentRemove(Professor professor) {
    User student;
    Review review = null;
    displayStudents(professor);
    student = selectStudent();
    review = removeReview(student, professor);
    ReviewDatabase.getInstance().removeReveiw(review);

}
/**
 * Takes in the current professor to display Students reviewed by them
 * @param professor
 */
public void displayStudents(Professor professor) {
    for (Review i: professor.getStudentReviewed(professor)) {
        System.out.println("Student Name: ");
        System.out.println(i.getReviewee().getFullName());
    }
}
/**
 * Displays all students available
 */
public void displayAllStudents() {
    ArrayList<Student> students = UserDatabase.getInstance().getStudents();
        for (Student student : students)
            System.out.println(student);
}
/**
 * selecting a student and returning the student selected.
 * @return
 */
public User selectStudent() { 
    String studentname = "";
    User student;
    Scanner input = new Scanner (System.in);
    System.out.println("Please select a student's first name"); // or something similar
    studentname = input.next();
    studentname += " ";
    System.out.println("Please select a student's last name");
    studentname += input.next();
    student = UserDatabase.getInstance().findByName(studentname);
    return student;
}
/**
 * takes in the current professor and the student selected and
 * prompt the current professor to add a rating and comment for a review.
 * @param student
 * @param professor
 * @return
 */
public Review addingreview(User student, Professor professor) {
    String comment = "";
    int rating = 0;
    Review review = null;
    Scanner input = new Scanner (System.in);
    System.out.println("What rating do you give to " + student.getFullName());
    comment = input.next();
    System.out.println("What comment do you have for " + student.getFullName());
    rating = input.nextInt();
    review = new Review.Builder().reviewer(professor).reviewee(student).rating(rating).comment(comment).build();
    return review;

}
/**
 * Creating a new User Professor  by prompting the user for info
 * @param email email address to be confirmed
 * @param username username the professor will identified as
 * @param password password for safety
 * @param firstName First Name
 * @param lastName Last Name
 * 
 */
public void doSignup() {
    Scanner input = new Scanner (System.in);

    System.out.println("Enter email: ");
    String email = input.nextLine();
    if(!User.isEmailValid(email)) {
        System.out.println("This email is invalid");
        return;
    }
    if (UserDatabase.getInstance().getUserByEmail(email) != null) {
        System.out.println("Email already in use");
        return;
    }

    System.out.print("Enter in Username:");
    String username = input.nextLine();
    if (UserDatabase.getInstance().findByUsername(username) != null) {
        System.out.println("Username already exists");
        return;
    }

    System.out.print("Enter Password:");
    String password = input.nextLine();
    System.out.print("Enter First Name:");
    String firstName = input.nextLine();
    System.out.print("Enter Last Name:");
    String lastName = input.nextLine();
    while (User.isEmailValid(email) == false) {
        System.out.print("Invalid email. Please enter a valid email: ");
        email = input.nextLine();
    }
    professor = new Professor.Builder().username(username).password(password).firstName(firstName).lastName(lastName).email(email).approved(true).removed(false).build();
    UserDatabase.getInstance().addUser(professor);
    
}
}
