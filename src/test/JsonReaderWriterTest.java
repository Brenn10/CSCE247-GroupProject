package test;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import dataTypes.DataBlob;
import dataTypes.Employer;
import dataTypes.JobPosting;
import dataTypes.Professor;
import dataTypes.Review;
import dataTypes.Student;
import dataTypes.User;
import database.Database;
import database.JsonDataReader;
import database.JsonDataWriter;
import enums.JobPostingStatus;
import enums.Major;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

// Trey - JSON Data reader and writer

class JsonReaderWriterTest {
    // bases: datablob, strings for what the paths are, admin student prof emplo
    // check: write student, prof, empl, admin
    // check: read student, prof emplo, admin
    // write null, read a null, read a different file
    static JsonDataWriter writer = new JsonDataWriter("data/Administrators.json", "data/Students.json", "data/Employers.json",
    "data/Professors.json", "data/Reviews.json", "data/JobPostings.json");
    static JsonDataReader reader = new JsonDataReader("data/Administrators.json", "data/Students.json", "data/Employers.json",
    "data/Professors.json", "data/Reviews.json", "data/JobPostings.json");
    static DataBlob allPrevItems;
    ArrayList<User> allUsers;
    ArrayList<JobPosting> allJobPost;
    ArrayList<Review> allReview;

    @BeforeAll
    public static void setupJson() {

    }
    @AfterAll
    public static void breakdown() {

    }
    @BeforeEach
    public void singleSetup() {
        allPrevItems = reader.read();
        // also make an empty file
        try {
            File file = new File("data/EmptyFile.json");
            file.createNewFile();
         } catch(Exception e) {
            e.printStackTrace();
         }
    }
    @AfterEach 
    public void singleBreakdown() {
        // fix everything
        writer.write(allPrevItems);
        // delete the empty file (no witnesses)
        File file = new File("data/EmptyFile.json");
        file.delete();
    }
    @Test
    public void grabFullAll() {
        DataBlob all = reader.read();
        assertNotNull(all);
    }
    @Test
    public void grabFullStudent() {
        // the get user methods are very similar, so I may just not add the rest (depends on time)
        DataBlob all = reader.read();
        int numStudents = 0;
        for(User i: all.getUsers()) {
            if (i instanceof Student) {
                numStudents++;
            }
        }
        int numStudentsPrev = 0;
        for(User i: allPrevItems.getUsers()) {
            if (i instanceof Student) {
                numStudentsPrev++;
            }
        }
        assertEquals(numStudents, numStudentsPrev);
    }
    @Test
    public void grabFullUsersFromOnlyStudent() {
        // what happens when the wrong file is inputted? any errors?
        JsonDataReader readerAllStudent = new JsonDataReader("data/Students.json", "data/Students.json", "data/Students.json",
        "data/Students.json", "data/Reviews.json", "data/JobPostings.json");
        DataBlob onlyStudentFilesBlob = readerAllStudent.read();
        onlyStudentFilesBlob.getUsers();
    }
    @Test
    public void grabFullReview() {
        assertNotNull(allPrevItems.getReviews());
    }
    @Test
    public void grabFullJobPost() {
        assertNotNull(allPrevItems.getJobPostings());
    }
    // nulls will use the empty file for the path
    @Test
    public void grabNullAll() {
        JsonDataReader nullReader = new JsonDataReader("data/EmptyFile.json", "data/EmptyFile.json", "data/EmptyFile.json",
        "data/EmptyFile.json", "data/EmptyFile.json", "data/EmptyFile.json");
        DataBlob nulBlob = nullReader.read();
        // check if there are any items in DataBlob
        boolean anything = false;
        if(!nulBlob.getUsers().isEmpty()) 
            anything = true;
        if(!nulBlob.getReviews().isEmpty()) 
            anything = true;
        if(!nulBlob.getJobPostings().isEmpty()) 
            anything = true;
        assertFalse(anything);
    }
    @Test
    public void grabNullStudent() {
        JsonDataReader nullReader = new JsonDataReader("data/Administrators.json", "data/EmptyFile.json", "data/Employers.json",
        "data/Professors.json", "data/Reviews.json", "data/JobPostings.json");
        DataBlob nulBlob = nullReader.read();
        int numStudents = 0;
        for(User i: nulBlob.getUsers()) {
            if (i instanceof Student) {
                numStudents++;
            }
        }
        assertEquals(0, numStudents);
    }
    @Test
    public void grabNullReview() {
        JsonDataReader nullReader = new JsonDataReader("data/Administrators.json", "data/Students.json", "data/Employers.json",
        "data/Professors.json", "data/EmptyFile.json", "data/JobPostings.json");
        DataBlob nulBlob = nullReader.read();
        int numReview = 0;
        for(Review i: nulBlob.getReviews()) {
            if (i instanceof Review) {
                numReview++;
            }
        }
        assertEquals(0, numReview);
    }
    @Test
    public void grabNullJobPost() {
        JsonDataReader nullReader = new JsonDataReader("data/Administrators.json", "data/Students.json", "data/Employers.json",
        "data/Professors.json", "data/Reviews.json", "data/EmptyFile.json");
        DataBlob nulBlob = nullReader.read();
        int numPost = 0;
        for(JobPosting i: nulBlob.getJobPostings()) {
            if (i instanceof JobPosting) {
                numPost++;
            }
        }
        assertEquals(0, numPost);
    }
    @Test
    public void writeFullAll() {
        writer.write(allPrevItems);
        DataBlob writeCheck = reader.read();
        assertNotNull(writeCheck);
    }
    @Test
    public void writeFullStudent() {
        int numStudents = 0;
        for(User i: allPrevItems.getUsers()) {
            if (i instanceof Student) {
                numStudents++;
            }
        }
        JsonDataWriter studentToEmpty = new JsonDataWriter("data/Administrators.json", "data/EmptyFile.json", "data/Employers.json",
        "data/Professors.json", "data/Reviews.json", "data/JobPostings.json");
        studentToEmpty.write(allPrevItems);
        JsonDataReader studentFromEmpty = new JsonDataReader("data/Administrators.json", "data/EmptyFile.json", "data/Employers.json",
        "data/Professors.json", "data/Reviews.json", "data/JobPostings.json");
        DataBlob allfromEmpt = studentFromEmpty.read();
        int numStudentsNew = 0;
        for(User i: allfromEmpt.getUsers()) {
            if (i instanceof Student) {
                numStudentsNew++;
            }
        }
        assertEquals(numStudents, numStudentsNew);
    }
    @Test
    public void writeFullReview() {
        int numStudents = 0;
        for(Review i: allPrevItems.getReviews()) {
            if (i instanceof Review) {
                numStudents++;
            }
        }
        JsonDataWriter studentToEmpty = new JsonDataWriter("data/Administrators.json", "data/Students.json", "data/Employers.json",
        "data/Professors.json", "data/EmptyFile.json", "data/JobPostings.json");
        studentToEmpty.write(allPrevItems);
        JsonDataReader studentFromEmpty = new JsonDataReader("data/Administrators.json", "data/Students.json", "data/Employers.json",
        "data/Professors.json", "data/EmptyFile.json", "data/JobPostings.json");
        DataBlob allfromEmpt = studentFromEmpty.read();
        int numReviewNew = 0;
        for(Review i: allfromEmpt.getReviews()) {
            if (i instanceof Review) {
                numReviewNew++;
            }
        }
        assertEquals(numStudents, numReviewNew);
    }
    @Test
    public void writeFullJobPost() {
        int numjob = 0;
        for(JobPosting i: allPrevItems.getJobPostings()) {
            if (i instanceof JobPosting) {
                numjob++;
            }
        }
        JsonDataWriter studentToEmpty = new JsonDataWriter("data/Administrators.json", "data/Students.json", "data/Employers.json",
        "data/Professors.json", "data/Reviews.json", "data/EmptyFile.json");
        studentToEmpty.write(allPrevItems);
        JsonDataReader studentFromEmpty = new JsonDataReader("data/Administrators.json", "data/Students.json", "data/Employers.json",
        "data/Professors.json", "data/Reviews.json", "data/EmptyFile.json");
        DataBlob allfromEmpt = studentFromEmpty.read();
        int numjobNew = 0;
        for(JobPosting i: allfromEmpt.getJobPostings()) {
            if (i instanceof JobPosting) {
                numjobNew++;
            }
        }
        assertEquals(numjob, numjobNew);
    }
    // these will use the empty file to write
    @Test
    public void writeNullAll() {
        DataBlob beforeBlob = new DataBlob();
        JsonDataWriter nullWriter = new JsonDataWriter("data/EmptyFile.json", "data/EmptyFile.json", "data/EmptyFile.json",
        "data/EmptyFile.json", "data/EmptyFile.json", "data/EmptyFile.json");
        JsonDataReader nullReader = new JsonDataReader("data/EmptyFile.json", "data/EmptyFile.json", "data/EmptyFile.json",
        "data/EmptyFile.json", "data/EmptyFile.json", "data/EmptyFile.json");
        nullWriter.write(beforeBlob);
        DataBlob nulBlob = nullReader.read();
        boolean anything = false;
        if(!nulBlob.getUsers().isEmpty()) 
            anything = true;
        if(!nulBlob.getReviews().isEmpty()) 
            anything = true;
        if(!nulBlob.getJobPostings().isEmpty()) 
            anything = true;
        assertFalse(anything);
    }
    @Test
    public void writeNullStudent() {
        DataBlob beforeBlob = new DataBlob();
        JsonDataWriter nullWriter = new JsonDataWriter("data/Administrators.json", "data/EmptyFile.json", "data/Employers.json",
        "data/Professors.json", "data/Reviews.json", "data/JobPostings.json");
        nullWriter.write(beforeBlob);
        JsonDataReader nullReader = new JsonDataReader("data/Administrators.json", "data/EmptyFile.json", "data/Employers.json",
        "data/Professors.json", "data/Reviews.json", "data/JobPostings.json");
        DataBlob nulBlob = nullReader.read();
        boolean anything = false;
        if(!nulBlob.getUsers().isEmpty()) 
            anything = true;
        assertFalse(anything);
    }
    @Test
    public void writeNullReview() {
        DataBlob beforeBlob = new DataBlob();
        JsonDataWriter nullWriter = new JsonDataWriter("data/Administrators.json", "data/Students.json", "data/Employers.json",
        "data/Professors.json", "data/EmptyFile.json", "data/JobPostings.json");
        nullWriter.write(beforeBlob);
        JsonDataReader nullReader = new JsonDataReader("data/Administrators.json", "data/Students.json", "data/Employers.json",
        "data/Professors.json", "data/EmptyFile.json", "data/JobPostings.json");
        DataBlob nulBlob = nullReader.read();
        boolean anything = false;
        if(!nulBlob.getUsers().isEmpty()) 
            anything = true;
        assertFalse(anything);
    }
    @Test
    public void writeNullJobPost() {
        DataBlob beforeBlob = new DataBlob();
        JsonDataWriter nullWriter = new JsonDataWriter("data/Administrators.json", "data/Students.json", "data/Employers.json",
        "data/Professors.json", "data/Reviews.json", "data/EmptyFile.json");
        nullWriter.write(beforeBlob);
        JsonDataReader nullReader = new JsonDataReader("data/Administrators.json", "data/Students.json", "data/Employers.json",
        "data/Professors.json", "data/Reviews.json", "data/EmptyFile.json");
        DataBlob nulBlob = nullReader.read();
        boolean anything = false;
        if(!nulBlob.getUsers().isEmpty()) 
            anything = true;
        assertFalse(anything);
    }
}
