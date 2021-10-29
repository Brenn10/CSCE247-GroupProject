
import java.util.Scanner;
import dataTypes.Professor;
import dataTypes.Review;
import database.ReviewDatabase;
import database.UserDatabase;
import dataTypes.Student;
import java.util.ArrayList;
import dataTypes.User;

public class ProfessorUI {
    private Professor professor;
    public ProfessorUI (Professor professor) {
        this.professor = professor;
    }
    /**
     * Prof
     * 
     * Review Student
     * Edit Review
     * View Reviews (of self to all stu)
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
public void ReviewStudent(Professor professor) {
    User student;
    Review review = null;
    displayStudents(professor);
    student = selectStudent();
    review  = addingreview(student, professor);
    ReviewDatabase.getInstance().addReview(review);
}
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
   

public Review removeReview(User student, Professor professor) {
    Review review = null;
    String prof = "";
    String stu = "";
    displayStudents(professor);
    student = selectStudent();
    stu = student.getUsername();
    prof = professor.getUsername();
    review = ReviewDatabase.getInstance().getReviewByRevieweeAndReviewer(prof,stu);
    return review;
}
public void ReviewStudentRemove(Professor professor) {
    User student;
    Review review = null;
    displayStudents(professor);
    student = selectStudent();
    review = removeReview(student, professor);
    ReviewDatabase.getInstance().removeReveiw(review);

}
public void displayStudents(Professor professor) {
    for (Review i: professor.getStudentReviewed(professor)) {
        System.out.println("Student Name: ");
        System.out.println(i.getReviewee().getFullName());
    }
}
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
public static void doSignup() {
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
