package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.swing.plaf.TreeUI;

import dataTypes.DataBlob;
import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Professor;
import dataTypes.Review;
import dataTypes.Student;
import dataTypes.User;
import dataTypes.*;

public class DataBlobTest {


    @Test 
    public void addUser_Test_Null() {
        DataBlob dataBlob = new DataBlob();
        User user = null;
        dataBlob.addUser(user);
        assertNull(dataBlob.getUsers().contains(user));

    }
    @Test 
    public void addUser_Test_Student() {
        DataBlob dataBlob = new DataBlob();
        UUID UUID;
        Student user = new Student.Builder().username("johnb").firstName("john").lastName("bryan").email("johnb@email.sc.edu").build();
        dataBlob.addUser(user);
        assertTrue(dataBlob.getUsers().contains(user));
    }
    @Test 
    public void addUser_Test_Professor() {
        DataBlob dataBlob = new DataBlob();
        UUID UUID;
        Professor user = new Professor.Builder().username("johnb").firstName("john").lastName("bryan").email("johnb@email.sc.edu").build();
        dataBlob.addUser(user);
        assertTrue(dataBlob.getUsers().contains(user));
    }
    @Test 
    public void addUser_Test_Employer() {
        DataBlob dataBlob = new DataBlob();
        UUID UUID;
        Employer user = new Employer.Builder().username("johnb").firstName("john").lastName("bryan").email("johnb@email.sc.edu").build();
        dataBlob.addUser(user);
        assertTrue(dataBlob.getUsers().contains(user));
    }
    @Test 
    public void addUser_Test_Admin() {
        DataBlob dataBlob = new DataBlob();
        UUID UUID;
        Admin user = new Admin.Builder().username("johnb").firstName("john").lastName("bryan").email("johnb@email.sc.edu").build();
        dataBlob.addUser(user);
        assertTrue(dataBlob.getUsers().contains(user));
    }
    @Test
    public void addReview_Test() {
        DataBlob dataBlob = new DataBlob();
        Professor user = new Professor.Builder().username("johnb").firstName("john").lastName("bryan").email("johnb@email.sc.edu").build();
        Student user1Student = new Student.Builder().username("johnb").firstName("john").lastName("bryan").email("johnb@email.sc.edu").build();
        Review review = new Review.Builder().id(UUID.randomUUID()).rating(4).removed(false).reviewee(user1Student).reviewer(user).comment("Good Soup").build();
        dataBlob.addReview(review);
        assertTrue(dataBlob.getReviews().contains(review));
    }
    @Test 
    public void addJobPosting_Test() {
        DataBlob dataBlob = new DataBlob();
        Employer employer;
        JobPosting  jobPosting = new JobPosting.Builder().id(UUID.randomUUID()).employer(employer = mock(Employer.class)).jobTitle("JOB1").description("New Job").requirements(new ArrayList<String>()).hourlyWage(32).build();
        dataBlob.addJobPosting(jobPosting);
        assertTrue(dataBlob.getJobPostings().contains(jobPosting));
    }
}
