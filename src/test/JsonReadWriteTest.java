package test;

import dataTypes.DataBlob;
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
