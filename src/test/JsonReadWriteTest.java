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
import database.JsonDataWriter;

public class JsonReadWriteTest {
    public static void main(String[] args) {
        JsonDataReader reader = new JsonDataReader(
            "data/Administrators.json",
            "data/Students.json", 
            "data/Employers.json",
            "data/Professors.json",
            "data/Reviews.json",
            "data/JobPostings.json");
        DataBlob data = reader.read();

        JsonDataWriter writer = new JsonDataWriter(
            "data/Administrators.json",
            "data/Students.json", 
            "data/Employers.json",
            "data/Professors.json",
            "data/Reviews.json",
            "data/JobPostings.json");
        writer.write(data);
    }
}
