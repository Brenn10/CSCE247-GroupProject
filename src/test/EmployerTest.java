package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.MockedStatic;

import dataTypes.Admin;
import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.Student;
import database.Database;
import enums.JobPostingStatus;

public class EmployerTest {
    @Test
    public void testMakePosting() {
        JobPosting jobPosting = mock(JobPosting.class);
        Employer employer = new Employer.Builder()
                .username("test")
                .password("test")
                .email("email")
                .firstName("first")
                .lastName("last")
                .build();
        Database database = mock(Database.class);
        try(MockedStatic<Database> staticDatabase = mockStatic(Database.class)) {
            staticDatabase.when(Database::getInstance).thenReturn(database);
            employer.makePosting(jobPosting);
            verify(database, times(1)).addPosting(jobPosting);
        }
    }

    @Test
    public void testEditDescription() {
        Employer employer = new Employer.Builder()
                .username("test")
                .password("test")
                .email("email")
                .firstName("first")
                .lastName("last")
                .build();
        JobPosting jobPosting = mock(JobPosting.class);
        String description = "description";
        employer.editDescription(jobPosting, description);
    }

    @Test
    public void testAddPostingRequirement() {
        Employer employer = new Employer.Builder()
                .username("test")
                .password("test")
                .email("email")
                .firstName("first")
                .lastName("last")
                .build();
        JobPosting jobPosting = mock(JobPosting.class);
        String requirement = "requirement";
        employer.addPostingRequirement(jobPosting, requirement);
        verify(jobPosting, times(1)).addRequirement(requirement);
    }
    
    @Test
    public void removePostingRequirement() {
        Employer employer = new Employer.Builder()
                .username("test")
                .password("test")
                .email("email")
                .firstName("first")
                .lastName("last")
                .build();
        JobPosting jobPosting = mock(JobPosting.class);
        String requirement = "requirement";
        employer.removePostingRequirement(jobPosting, requirement);
        verify(jobPosting, times(1)).removeRequriement(requirement);
    }

    @Test
    public void testEditPostingWage() {
        Employer employer = new Employer.Builder()
                .username("test")
                .password("test")
                .email("email")
                .firstName("first")
                .lastName("last")
                .build();
        JobPosting jobPosting = mock(JobPosting.class);
        int wage = 100;
        employer.editPostingWage(jobPosting, wage);
        verify(jobPosting, times(1)).setWage(wage);
    }

    @Test
    public void testEditPostingStatus() {
        Employer employer = new Employer.Builder()
                .username("test")
                .password("test")
                .email("email")
                .firstName("first")
                .lastName("last")
                .build();
        JobPosting jobPosting = mock(JobPosting.class);
        JobPostingStatus status = JobPostingStatus.OPEN;
        employer.editPostingStatus(jobPosting, status);
        verify(jobPosting, times(1)).setStatus(status);
    }

    @Test
    public void testRemovePosting() {
        Employer employer = new Employer.Builder()
                .username("test")
                .password("test")
                .email("email")
                .firstName("first")
                .lastName("last")
                .build();
        JobPosting jobPosting = mock(JobPosting.class);
        Database database = mock(Database.class);
        ArrayList<JobPosting> jobPostings = mock(ArrayList.class);
        try(MockedStatic<Database> staticDatabase = mockStatic(Database.class)) {
            staticDatabase.when(Database::getInstance).thenReturn(database);
            when(database.getPostings()).thenReturn(jobPostings);
            when(jobPostings.remove(jobPosting)).thenReturn(true);
            employer.removePosting(jobPosting);
            verify(jobPostings, times(1)).remove(jobPosting);
        }
    }

    
}
