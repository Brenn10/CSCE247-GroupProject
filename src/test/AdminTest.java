package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import dataTypes.Admin;
import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.Student;
import database.Database;

public class AdminTest {


    @Test
    public void testsetJobPosting() {
        Admin admin = new Admin.Builder()
        .username("admin")
        .password("password")
        .email("email")
        .firstName("First")
        .lastName("Last")
        .approved(true)
        .build();
        Employer employer = mock(Employer.class);
        JobPosting jobPosting = mock(JobPosting.class);
        admin.setJobPosting(employer, jobPosting);
        verify(employer, times(1)).makePosting(jobPosting);
    }

    @Test
    public void testRemoveUser() {
        Admin admin = new Admin.Builder()
                                .username("admin")
                                .password("password")
                                .email("email")
                                .firstName("First")
                                .lastName("Last")
                                .approved(true)
                                .build();
        Database database = mock(Database.class);
        try (MockedStatic<Database> staticDatabase = mockStatic(Database.class)) {
            staticDatabase.when(Database::getInstance).thenReturn(database);
            Employer employer = mock(Employer.class);
            admin.removeUser(employer);
            verify(database, times(1)).removeUser(employer);        
        }    
    }

    @Test
    public void removeReviews() {
        Admin admin = new Admin.Builder()
        .username("admin")
        .password("password")
        .email("email")
        .firstName("First")
        .lastName("Last")
        .approved(true)
        .build();
        Database database = mock(Database.class);
        when(Database.getInstance()).thenReturn(database);
        Review review = mock(Review.class);
        admin.removeReviews(review);
        verify(database, times(1)).removeReveiw(review);
    }

    @Test
    public void testRemoveResume() {
        Admin admin = new Admin.Builder()
        .username("admin")
        .password("password")
        .email("email")
        .firstName("First")
        .lastName("Last")
        .approved(true)
        .build();
        Database database = mock(Database.class);
        when(Database.getInstance()).thenReturn(database);
        Student student = mock(Student.class);
        admin.removeResume(student);
        verify(student, times(1)).setCreated(false);
    }

    @Test
    public void testRemoveJobPosting() {
        Admin admin = new Admin.Builder()
        .username("admin")
        .password("password")
        .email("email")
        .firstName("First")
        .lastName("Last")
        .approved(true)
        .build();
        Database database = mock(Database.class);
        when(Database.getInstance()).thenReturn(database);
        JobPosting jobPosting = mock(JobPosting.class);
        admin.removeJobPosting(jobPosting);
        verify(database, times(1)).removePosting(jobPosting);
    }

    @Test
    public void testAddUser() {
        Admin admin = new Admin.Builder()
        .username("admin")
        .password("password")
        .email("email")
        .firstName("First")
        .lastName("Last")
        .approved(true)
        .build();
        Database database = mock(Database.class);
        when(Database.getInstance()).thenReturn(database);
        Employer employer = mock(Employer.class);
        admin.addUser(employer);
        verify(database, times(1)).addUser(employer);
    }

    @Test
    public void testToString() {
        Admin admin = new Admin.Builder()
        .username("admin")
        .password("password")
        .email("email")
        .firstName("First")
        .lastName("Last")
        .approved(true)
        .build();
        assertEquals(admin.toString(), "First Last\nemail\nadmin\n");
    }
}
