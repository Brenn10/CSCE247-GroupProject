package test;

import dataTypes.Admin;
import dataTypes.DataBlob;
import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Professor;
import dataTypes.Review;
import dataTypes.Student;
import dataTypes.User;
import database.JsonDataReader;

public class JsonReaderTest {
    public static void main(String[] args) {
        JsonDataReader reader = new JsonDataReader(
            "data/Administrators.json",
            "data/Students.json", 
            "data/Employers.json",
            "data/Professors.json",
            "data/Reviews.json",
            "data/JobPostings.json");
        DataBlob data = reader.read();

        for (User user : data.getUsers()) {
            if (user instanceof Student) {
                Student student = (Student) user;
                System.out.println(student.toString());
            } else if (user instanceof Admin) {
                Admin admin = (Admin) user;
                System.out.println(admin.toString());
            } else if (user instanceof Professor) {
                Professor professor = (Professor) user;
                System.out.println(professor.toString());
            } else if (user instanceof Employer) {
                Employer employer = (Employer) user;
                System.out.println(employer.toString());
            }
        }

        for (Review review : data.getReviews()) {
            System.out.println(review);
        }

        for (JobPosting jobPosting : data.getJobPostings()) {
            System.out.println(jobPosting);
        }
    }
}
