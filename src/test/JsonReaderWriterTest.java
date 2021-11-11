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
        // take everything so that it can be re-fixed
        allPrevItems = reader.read();
        // also make an empty file
        try {
            File file = new File("data/EmptyFile.json");
            file.createNewFile();
         } catch(Exception e) {
            e.printStackTrace();
         }
    }
    @AfterAll
    public static void breakdown() {
        // fix everything
        writer.write(allPrevItems);
        // delete the empty file (no witnesses)
        File file = new File("data/EmptyFile.json");
        file.delete();
    }
    @BeforeEach
    public void singleSetup() {
    }
    @AfterEach 
    public void singleBreakdown() {

    }
    @Test
    public void grabFullAll() {
        
    }
    @Test
    public void grabFullStudent() {
        
    }
    @Test
    public void grabFullEmploy() {

    }
    @Test 
    public void grabFullProf() {

    }
    @Test
    public void grabFullAdmin() {

    }
    @Test
    public void grabFullReview() {

    }
    @Test
    public void grabFullJobPost() {

    }
    @Test
    public void grabNullAll() {

    }
    @Test
    public void grabNullStudent() {

    }
    @Test
    public void grabNullEmploy() {

    }
    @Test 
    public void grabNullProf() {

    }
    @Test
    public void grabNullAdmin() {

    }
    @Test
    public void grabNullReview() {

    }
    @Test
    public void grabNullJobPost() {
        
    }
    @Test
    public void writeFullAll() {

    }
    @Test
    public void writeFullStudent() {
        
    }
    @Test
    public void writeFullEmploy() {

    }
    @Test 
    public void writeFullProf() {

    }
    @Test
    public void writeFullAdmin() {

    }
    @Test
    public void writeFullReview() {

    }
    @Test
    public void writeFullJobPost() {
        
    }
    @Test
    public void writeNullAll() {

    }
    @Test
    public void writeNullStudent() {

    }
    @Test
    public void writeNullEmploy() {

    }
    @Test 
    public void writeNullProf() {

    }
    @Test
    public void writeNullAdmin() {

    }
    @Test
    public void writeNullReview() {

    }
    @Test
    public void writeNullJobPost() {
        
    }
}
