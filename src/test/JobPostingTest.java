package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import enums.JobPostingStatus;

import dataTypes.DataBlob;
import dataTypes.JobPosting;
import dataTypes.Review;
import dataTypes.User;
import dataTypes.Employer;
import dataTypes.Student;
public class JobPostingTest {
    @Test
    public void addRequirement_Test() {
        UUID UUID = null;
        Employer employer;
        String requirment = "requirment";
        JobPosting  jobPosting = new JobPosting.Builder().id(UUID.randomUUID()).employer(employer = mock(Employer.class)).jobTitle("JOB1").description("New Job").requirements(new ArrayList<String>()).hourlyWage(32).build();
        jobPosting.addRequirement(requirment);
        assertTrue(jobPosting.getRequirements().contains(requirment));
    }
    @Test
    public void removeRequriement_Test() {
        UUID UUID = null;
        Employer employer;
        String requirment = "requirment";
        JobPosting  jobPosting = new JobPosting.Builder().id(UUID.randomUUID()).employer(employer = mock(Employer.class)).jobTitle("JOB1").description("New Job").requirements(new ArrayList<String>()).hourlyWage(32).build();
        jobPosting.addRequirement(requirment);
        jobPosting.removeRequriement(requirment);
        assertFalse(jobPosting.getRequirements().contains(requirment));
    }
    @Test
    public void addApplicant_Test() {
        UUID UUID = null;
        Employer employer;
        Student student = new Student.Builder().id(UUID.randomUUID()).username("username").firstName("first").lastName("last").build();
        String requirment = "requirment";
        JobPosting  jobPosting = new JobPosting.Builder().id(UUID.randomUUID()).employer(employer = mock(Employer.class)).jobTitle("JOB1").description("New Job").requirements(new ArrayList<String>()).hourlyWage(32).applicants(new ArrayList<Student>()).build();
        jobPosting.addApplicant(student);
        assertTrue(jobPosting.getApplicants().contains(student));
    }


}