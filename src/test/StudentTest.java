package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import dataTypes.Education;
import dataTypes.Employment;
import dataTypes.Student;

public class StudentTest {
    @Test
    public void addEmployment() {
        Student student = new Student.Builder()
                                     .educations(new ArrayList<Education>())
                                     .employments(new ArrayList<Employment>())
                                     .technicalSkills(new ArrayList<String>())
                                     .build();
        Employment employment = new Employment.Builder().build();
        student.addEmployment(employment);
        assertTrue(student.getEmployments().contains(employment));
    }

    @Test
    public void testAddTechnicalSkill() {
        Student student = new Student.Builder()
        .educations(new ArrayList<Education>())
        .employments(new ArrayList<Employment>())
        .technicalSkills(new ArrayList<String>())
        .build();
        student.addTechincalSkill("Java");
        assertTrue(student.getTechnicalSkills().contains("Java"));
    }

    @Test
    public void testRemoveTechnicalSkill() {
        Student student = new Student.Builder()
        .educations(new ArrayList<Education>())
        .employments(new ArrayList<Employment>())
        .technicalSkills(new ArrayList<String>())
        .build();
        student.addTechincalSkill("Java");
        student.removeTechincalSkill("Java");
        assertTrue(student.getTechnicalSkills().isEmpty());
    }

    @Test
    public void testAddEducation() {
        Student student = new Student.Builder()
        .educations(new ArrayList<Education>())
        .employments(new ArrayList<Employment>())
        .technicalSkills(new ArrayList<String>())
        .build();
        Education education = new Education.Builder().build();
        student.addEducation(education);
        assertTrue(student.getEducations().contains(education));
    }

    @Test
    public void testRemoveEducation() {
        Student student = new Student.Builder()
        .educations(new ArrayList<Education>())
        .employments(new ArrayList<Employment>())
        .technicalSkills(new ArrayList<String>())
        .build();
        Education education = new Education.Builder().build();
        student.addEducation(education);
        student.removeEducation(education);
        assertTrue(student.getEducations().isEmpty());
    }


}
