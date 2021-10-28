
import java.util.ArrayList;
import java.util.Scanner;

import dataTypes.Education;
import dataTypes.Employment;
import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.Student;
import dataTypes.User;
import database.JobPostingDatabase;
import database.ReviewDatabase;
import database.UserDatabase;
import enums.Major;

public class StudentUI {
    private Student student;
    public StudentUI(Student student) {
        this.student = student;
    }

    /**
     * student
     * 
     * View Job Listings -1 View Job Applications -2 Edit/Create Resume -3 Check
     * Review -4
     */
    public void doMainMenu() {
        boolean keepLooping = true;
        Scanner input = new Scanner(System.in);
        int option = 0;
        System.out.println("Welcome Student " + student.getFirstName() + " " + student.getLastName());
        while (keepLooping) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("1) View Job Listings\n");
            System.out.println("2) View Job Applications\n");
            System.out.println("3) View Resume\n");
            System.out.println("4) Edit Resume\n");
            System.out.println("5) View Reviews\n");
            System.out.println("0) Exit Neurotic Job Search");
            option = input.nextInt();
            switch (option) {
                case 1:
                    doViewJobListings();
                    break;
                case 2:
                    doViewJobApplications();
                    break;
                case 3:
                    doViewResume();
                    break;
                case 4:
                    doEditResume();
                    break;
                case 5:
                    doViewReviews();
                    break;
                case 0:
                    keepLooping = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

        }
        input.close();
    }

    private void doViewReviews() {
        ArrayList<Review> reviews = ReviewDatabase.getInstance().getReviewsByReviewee(student);
        for (Review review : reviews) {
            System.out.println(review.toString());
        }
    }

    private void doViewResume() {
        System.out.println(student.getPrintableResume());
    }

    private void doViewJobApplications() {
        ArrayList<JobPosting> postings = JobPostingDatabase.getInstance().getPostingsByStudent(student);
        for (JobPosting posting : postings) {
            System.out.println(posting);
        }
    }

    private void doEditResume() {
        Scanner input = new Scanner(System.in);
        boolean keepEditing = true;
        while(keepEditing) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("1) Edit Major\n");
            System.out.println("2) Edit Educations\n");
            System.out.println("3) Edit Skills\n");
            System.out.println("4) Edit Employment\n");
            System.out.println("0) Stop Editing\n");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    doEditMajor();
                    break;
                case 2:
                    doEditEducations();
                    break;
                case 3:
                    doEditSkills();
                    break;
                case 4:
                    doEditEmployment();
                    break;
                case 0:
                    keepEditing = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        input.close();
        student.setCreated(true);
    }


    private void doEditEmployment() {
        ArrayList<Employment> employments = student.getEmployments();
        for (int i = 0; i < employments.size(); i++) {
            System.out.println(i+"\n"+employments.get(i));
        }
        Scanner input = new Scanner(System.in);
        boolean keepEditing = true;
        while(keepEditing) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("1) Add Employment\n");
            System.out.println("2) Remove Employment\n");
            //TODO: Add an edit. In the meantime removed and add works
            System.out.println("0) Stop Editing\n");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    doAddEmployment();
                    break;
                case 2:
                    doRemoveEmployment();
                    break;
                case 0:
                    keepEditing = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        input.close();
    }

    private void doRemoveEmployment() {
        ArrayList<Employment> employments = student.getEmployments();
        for (int i = 0; i < employments.size(); i++) {
            System.out.println(i+"\n"+employments.get(i));
        }
        System.out.print("Please enter the index of the employment you would like to remove: ");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        try {
            employments.remove(option);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid employment");
        }
        input.close();
    }

    private void doAddEmployment() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the company name: ");
        String companyName = input.nextLine();
        System.out.print("Please enter the job title: ");
        String jobTitle = input.nextLine();
        System.out.print("Please enter the dates: ");
        String dates = input.nextLine();

        ArrayList<String> details = new ArrayList<String>();
        System.out.print("Please enter the descriptions (empty line to end): ");
        String detail = input.nextLine();
        while (!detail.isEmpty()) {
            details.add(detail);
            detail = input.nextLine();
        }
        student.addEmployment(new Employment.Builder().company(companyName).title(jobTitle).dates(dates).details(details).build());
        input.close();
    }

    private void doEditSkills() {
        ArrayList<String> skills = student.getTechnicalSkills();
        for (int i = 0; i < skills.size(); i++) {
            System.out.println(i+": "+skills.get(i));
        }
        Scanner input = new Scanner(System.in);
        boolean keepEditing = true;
        while(keepEditing) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("1) Add Skill\n");
            System.out.println("2) Remove Skill\n");
            System.out.println("0) Stop Editing\n");
            switch (input.nextInt()) {
                case 1:
                    doAddSkill();
                    break;
                case 2:
                    doRemoveSkill();
                    break;
                case 0:
                    keepEditing = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        input.close();
    }

    private void doRemoveSkill() {
        ArrayList<String> skills = student.getTechnicalSkills();
        for (int i = 0; i < skills.size(); i++) {
            System.out.println(i+"\n"+skills.get(i));
        }
        System.out.print("Please enter the index of the skill you would like to remove: ");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        try {
            skills.remove(option);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid skill");
        }
        input.close();
    }

    private void doAddSkill() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the skill: ");
        String skill = input.nextLine();
        student.addTechincalSkill(skill);
        input.close();
    }

    private void doEditEducations() {
        ArrayList<Education> educations = student.getEducations();
        for (int i = 0; i < educations.size(); i++) {
            System.out.println(i+"\n"+educations.get(i));
        }
        Scanner input = new Scanner(System.in);
        boolean keepEditing = true;
        while(keepEditing) {
            System.out.println("Please select a valid option: \n\n");
            System.out.println("1) Add Education\n");
            System.out.println("2) Remove Education\n");
            //TODO: Add an edit. In the meantime removed and add works
            System.out.println("0) Stop Editing\n");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    doAddEducation();
                    break;
                case 2:
                    doRemoveEducation();
                    break;
                case 0:
                    keepEditing = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        input.close();
    }

    private void doRemoveEducation() {
        ArrayList<Education> educations = student.getEducations();
        for (int i = 0; i < educations.size(); i++) {
            System.out.println(i+"\n"+educations.get(i));
        }
        System.out.print("Please enter the index of the education you would like to remove: ");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        try {
            educations.remove(option);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid education");
        }
        input.close();
    }

    private void doAddEducation() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the name of the school: ");
        String schoolName = input.nextLine();
        System.out.print("Please enter your gpa: ");
        double gpa = input.nextDouble();
        System.out.print("Please enter the graduation date: ");
        String gradDate = input.nextLine();
        student.addEducation(new Education.Builder().place(schoolName).gpa(gpa).gradDate(gradDate).build());
        input.close();
    }

    private void doEditMajor() {
        boolean majorChanged = false;
        Scanner input = new Scanner(System.in);
        String major = input.nextLine();
        while (!majorChanged) {
            System.out.print("Input your new major: ");
            if(major.equalsIgnoreCase(Major.COMPUTER_ENGINEERING.toString())) {
                student.setMajor(Major.COMPUTER_ENGINEERING);
                majorChanged = true;
            } else if(major.equalsIgnoreCase(Major.COMPUTER_SCIENCE.toString())) {
                student.setMajor(Major.COMPUTER_SCIENCE);
                majorChanged = true;
            } else if(major.equalsIgnoreCase(Major.INTEGRATED_INFORMATION_TECHNOLOGY.toString())) {
                student.setMajor(Major.INTEGRATED_INFORMATION_TECHNOLOGY);
                majorChanged = true;
            } else if(major.equalsIgnoreCase(Major.COMPUTER_INFORMATION_SYSTEMS.toString())) {
                student.setMajor(Major.COMPUTER_INFORMATION_SYSTEMS);
                majorChanged = true;
            } else {
                System.out.println("Invalid major");
            }
        }
        input.close();
    }

    private void doViewJobListings() {
        ArrayList<JobPosting> postings = JobPostingDatabase.getInstance().getOpenPostings();
        for (JobPosting posting : postings) {
            System.out.println(posting);
        }
    }

    public static void doSignup() {
        Scanner input = new Scanner(System.in);
        System.out.print("Username:");
        String username = input.nextLine();
        System.out.print("Password:");
        String password = input.nextLine();
        System.out.print("First Name:");
        String firstName = input.nextLine();
        System.out.print("Last Name:");
        String lastName = input.nextLine();
        System.out.print("Email:");
        String email = input.nextLine();
        while (User.isEmailValid(email) == false) {
            System.out.print("Invalid email. Please enter a valid email: ");
            email = input.nextLine();
        }

        System.out.print("Would you like to create a resume now [yes/no]:");
        String doResume = input.nextLine();
        while (!doResume.equalsIgnoreCase("yes") && !doResume.equalsIgnoreCase("no")) {
            System.out.print("Invalid input. Would you like to create a resume now [yes/no]:");
            doResume = input.nextLine();
        }

        Student newStudent = new Student.Builder().username(username)
                                                   .password(password)
                                                   .firstName(firstName)
                                                   .lastName(lastName)
                                                   .email(email)
                                                   .createdResume(false)
                                                   .build();
        if (doResume.equalsIgnoreCase("yes")) {
            new StudentUI(newStudent).doEditResume();
        }
        UserDatabase.getInstance().addUser(newStudent);
        input.close();
    }
}
