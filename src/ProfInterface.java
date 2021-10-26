import java.util.Scanner;

public class ProfInterface {
    private JobSystem jobSystem;

    public ProfInterface (JobSystem jobSystem) {
        this.jobSystem = jobSystem;
    }
    /**
     * Prof
     * 
     * Review Student
     * Edit Review
     * View Reviews (of self to all stu)
     */
    public void doProf() {
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
        input.close();
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
}
