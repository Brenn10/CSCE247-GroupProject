package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import dataTypes.Admin;
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

import java.util.ArrayList;
import java.util.UUID;

public class DatabaseTest {

    static ArrayList<User> users;
    static ArrayList<Review> reviews;
    static ArrayList<JobPosting> postings;
    static JsonDataWriter writer = new JsonDataWriter("data/Administrators.json", "data/Students.json",
            "data/Employers.json", "data/Professors.json", "data/Reviews.json", "data/JobPostings.json");
    static JsonDataReader reader = new JsonDataReader("data/Administrators.json", "data/Students.json",
            "data/Employers.json", "data/Professors.json", "data/Reviews.json", "data/JobPostings.json");

    @BeforeAll
    public static void setUp() {
        Database.getInstance().setDataReader(reader);
        Database.getInstance().setDataWriter(writer);
        Database.getInstance().loadFromFile(); // get what is currently stored in
        users = Database.getInstance().getUsers();
        reviews = Database.getInstance().getReviews();
        postings = Database.getInstance().getPostings();
    }

    @AfterAll
    public static void tearDown() {
        writer.write(users, reviews, postings);
        // gets rid of anything I may have added to the database
    }

    @BeforeEach
    public void individualSetUp() {
        Database.getInstance().loadFromFile();
    }

    @AfterEach
    public void individualTearDown() {
    }

    @Test
    void getInstanceIfInstanceIsNull() {
        Database database = Database.getInstance();
        assertNotNull(database);
    }

    @Test
    void getInstanceIfInstanceIsNotNull() {
        Database.getInstance();
        Database database = Database.getInstance();
        assertNotNull(database);
    }

    /**
     * I did not test load from file/write to file because these methods are used in
     * my set up/ tear down methods and testing them would be pointless (they have
     * to work for the test BeforeAll/BeforeEach/AfterAll/AfterEach to work)
     */

    // Testing add methods
    @Test
    void addPostingNotNull() {
        JobPosting posting = new JobPosting(UUID.randomUUID(), null, "Test", "Test description", null, 10.25,
                JobPostingStatus.CLOSED, null, false);
        Database.getInstance().addPosting(posting);
        ArrayList<JobPosting> postings = Database.getInstance().getPostings();
        boolean found = false;
        for (JobPosting post : postings) {
            if (post.equals(posting))
                found = true;
        }
        assertTrue(found);
    }

    @Test
    void addPostingNull() {
        JobPosting posting = null;
        Database.getInstance().addPosting(posting);
        ArrayList<JobPosting> postings = Database.getInstance().getPostings();
        boolean noNull = true;
        for (JobPosting post : postings) {
            if (post == null)
                noNull = false;
        }
        assertTrue(noNull);
    } // FAILS because addPosting does not check for null value

    @Test
    void addReviewNotNull() {
        Review review = new Review(UUID.randomUUID(), null, null, 0, "", false);
        Database.getInstance().addReview(review);

        ArrayList<Review> reviews = Database.getInstance().getReviews();
        boolean found = false;
        for (Review rev : reviews) {
            if (rev.equals(review))
                found = true;
        }
        assertTrue(found);
    }

    @Test
    void addReviewNull() {
        Review review = null;
        Database.getInstance().getReviews().add(review);

        ArrayList<Review> reviews = Database.getInstance().getReviews();
        boolean noNull = true;
        for (Review rev : reviews) {
            if (rev == null)
                noNull = false;
        }
        assertTrue(noNull);
    }

    @Test
    void addUserNotNull() {
        User user = new Student(UUID.randomUUID(), "", "", "", "", "", true, Major.NA, false, null, null, null, 0,
                false);
        Database.getInstance().addUser(user);
        ArrayList<User> users = Database.getInstance().getUsers();
        boolean found = false;
        for (User us : users) {
            if (us.equals(user))
                found = true;
        }
        assertTrue(found);
    }

    @Test
    void addUserNull() {
        User user = null;
        Database.getInstance().addUser(user);
        ArrayList<User> users = Database.getInstance().getUsers();
        boolean noNull = true;
        for (User us : users) {
            if (us == null)
                noNull = false;
        }
        assertTrue(noNull);
    } // FAILS because addUser does not check for null value
      // end testing add methods

    // testing remove methods
    @Test
    void removePostingThatExists() {
        JobPosting posting = new JobPosting(UUID.randomUUID(), null, "Test", "Test description", null, 10.25,
                JobPostingStatus.CLOSED, null, false);
        Database.getInstance().getPostings().add(posting);
        Database.getInstance().removePosting(posting);
        ArrayList<JobPosting> postings = Database.getInstance().getRemovedPostings();
        boolean found = false;
        for (JobPosting post : postings) {
            if (post.equals(posting))
                found = true;
        }
        assertFalse(found);
    }

    @Test
    void removePostingThatDoesNotExists() { // idk if I need this
        JobPosting posting = new JobPosting(UUID.randomUUID(), null, "Test", "Test description", null, 10.25,
                JobPostingStatus.CLOSED, null, false);
        Database.getInstance().removePosting(posting);
        ArrayList<JobPosting> postings = Database.getInstance().getRemovedPostings();
        boolean found = false;
        for (JobPosting post : postings) {
            if (post.equals(posting))
                found = true;
        }
        assertFalse(found);
    }

    @Test
    void removeReviewThatExist() {
        Review review = new Review(UUID.randomUUID(), null, null, 0, "", false);
        Database.getInstance().getReviews().add(review);
        Database.getInstance().removeReveiw(review);
        ArrayList<Review> reviews = Database.getInstance().getRemovedReviews();
        boolean found = false;
        for (Review rev : reviews) {
            if (rev.equals(review))
                found = true;
        }
        assertFalse(found);
    }

    @Test
    void removeReviewThatDoesNotExist() { // idk if I need this
        Review review = new Review(UUID.randomUUID(), null, null, 0, "", false);
        Database.getInstance().removeReveiw(review);
        ArrayList<Review> reviews = Database.getInstance().getRemovedReviews();
        boolean found = false;
        for (Review rev : reviews) {
            if (rev.equals(review))
                found = true;
        }
        assertFalse(found);
    }

    @Test
    void removeUserThatExists() {
        User user = new Student(UUID.randomUUID(), "", "", "", "", "", true, Major.NA, false, null, null, null, 0,
                false);
        Database.getInstance().getUsers().add(user);
        Database.getInstance().removeUser(user);
        ArrayList<User> users = Database.getInstance().getUsers();
        boolean removed = false;
        for (User us : users) {
            if (user.equals(us))
                if (user.isRemoved())
                    removed = true;
        }
        assertTrue(removed);
    }

    @Test
    void removeUserThatDoesNotExist() { // idk if i need this
        User user = new Student(UUID.randomUUID(), "", "", "", "", "", true, Major.NA, false, null, null, null, 0,
                false);

        Database.getInstance().removeUser(user);
        ArrayList<User> users = Database.getInstance().getUsers();
        boolean found = false;
        for (User us : users) {
            if (user.equals(us))
                found = true;
        }
        assertFalse(found);
    }
    // end testing remove methods

    // testing get posting methods
    @Test
    void getPostingByEmployerAndTitleThatExist() {
        JobPosting post = Database.getInstance().getPostings().get(0);
        String employer = post.getEmployer().getUsername();
        String title = post.getJobTitle();
        assertNotNull(Database.getInstance().getPostingByEmployerAndTitle(employer, title));
    }

    @Test
    void getPostingByEmployerAndTitleThatDoesNoyExist() {
        assertNull(Database.getInstance().getPostingByEmployerAndTitle("", ""));
    }

    @Test
    void getPostingByStudentThatExists() {
        JobPosting post = Database.getInstance().getPostings().get(0);
        Student student = post.getApplicants().get(0);
        ArrayList<JobPosting> postings = Database.getInstance().getPostingsByStudent(student);
        assertNotEquals(postings.size(), 0);
    }

    @Test
    void getPostingByStudentThatDoesNotExists() {
        Student student = new Student(UUID.randomUUID(), "", "", "", "", "", false, Major.NA, false, null, null, null,
                0, true);
        ArrayList<JobPosting> postings = Database.getInstance().getPostingsByStudent(student);
        assertEquals(postings.size(), 0);
    }

    @Test
    void getPostingByStudentThatHasNotAppliedAnywhere() {
        Student student = new Student(UUID.randomUUID(), "", "", "", "", "", false, Major.NA, false, null, null, null,
                0, true);
        Database.getInstance().getUsers().add(student);
        ArrayList<JobPosting> postings = Database.getInstance().getPostingsByStudent(student);
        assertEquals(postings.size(), 0);
    }

    @Test
    void getPostingByEmployerThatExists() {
        JobPosting posting = Database.getInstance().getJobPostings().get(0);
        Employer employer = posting.getEmployer();
        ArrayList<JobPosting> postings = Database.getInstance().getPostingsByEmployer(employer);
        assertNotNull(postings);
    }

    @Test
    void getPostingByEmployerThatDoesNotExists() {
        Employer employer = new Employer(UUID.randomUUID(), "", "", "", "", "", false, "", 0, true);
        ArrayList<JobPosting> postings = Database.getInstance().getPostingsByEmployer(employer);
        assertTrue(postings.size() == 0);
    }

    @Test
    void getPostingByEmployerThatHasNoPostings() {
        Employer employer = new Employer(UUID.randomUUID(), "", "", "", "", "", false, "", 0, true);
        Database.getInstance().getUsers().add(employer);
        ArrayList<JobPosting> postings = Database.getInstance().getPostingsByEmployer(employer);
        assertEquals(postings.size(), 0);
    }

    @Test
    void getPostingByRequirementThatExists() {
        ArrayList<JobPosting> postings = Database.getInstance().getOpenPostingByRequirement("javascript");
        assertNotEquals(postings.size(), 0);
    }

    @Test
    void getPostingByRequirementThatDoesNotExists() {
        ArrayList<JobPosting> postings = Database.getInstance().getOpenPostingByRequirement("7390185972");
        assertEquals(postings.size(), 0);
    }

    @Test
    void getRemovedPostings() {
        JobPosting removedPosting = new JobPosting(UUID.randomUUID(), null, "", "", null, 0, JobPostingStatus.OPEN,
                null, true);
        Database.getInstance().getJobPostings().add(removedPosting);
        ArrayList<JobPosting> removedPostings = Database.getInstance().getRemovedPostings();
        boolean found = false;
        for (JobPosting post : removedPostings)
            if (post.equals(removedPosting))
                found = true;
        assertTrue(found);
    }

    @Test
    void getOpenPostings() {
        JobPosting openPosting = new JobPosting(UUID.randomUUID(), null, "", "", null, 0, JobPostingStatus.OPEN, null,
                false);
        Database.getInstance().getJobPostings().add(openPosting);
        ArrayList<JobPosting> openPostings = Database.getInstance().getOpenPostings();
        boolean found = false;
        for (JobPosting post : openPostings)
            if (post.equals(openPosting))
                found = true;
        assertTrue(found);
    }
    // end testing get posting methods

    /*
     * I opted not to test the getReviewByReviwer/byReviewee that only returns a
     * single Review I believe these methods should have been removed from the
     * original project as they are unused and kind of pointless (because they only
     * return one review, it just would return the first review made by/about the
     * specified user, which would never be necessary)
     * 
     * @author Stella Garcia
     */

    // testing get reviews methods
    @Test
    void getReviewByRevieweeAndReviewerThatExists() {
        Review review = Database.getInstance().getReviews().get(0);
        User reviewer = review.getReviewer();
        User reviewee = review.getReviewee();
        assertEquals(review,
                Database.getInstance().getReviewByRevieweeAndReviewer(reviewer.getUsername(), reviewee.getUsername()));
    }

    @Test
    void getReviewByRevieweeAndReviewerWithNoReviewer() {
        Review review = Database.getInstance().getReviews().get(0);
        User reviewee = review.getReviewee();
        assertNull(Database.getInstance().getReviewByRevieweeAndReviewer("", reviewee.getUsername()));
    }

    @Test
    void getReviewByRevieweeAndReviewerWithNoReviewee() {
        Review review = Database.getInstance().getReviews().get(0);
        User reviewer = review.getReviewer();
        assertNull(Database.getInstance().getReviewByRevieweeAndReviewer(reviewer.getUsername(), ""));
    }

    @Test
    void getReviewByRevieweeAndReviewerNull() {
        assertNull(Database.getInstance().getReviewByRevieweeAndReviewer(null, null));
    }

    @Test
    void getReviewByRevieweeThatExists() {
        Review review = Database.getInstance().getReviews().get(0);
        User reviewee = review.getReviewee();
        ArrayList<Review> reviews = Database.getInstance().getReviewsByReviewee(reviewee);
        boolean correct = true;
        for (Review rev : reviews)
            if (!rev.getReviewee().equals(reviewee))
                correct = false;
        assertTrue(correct);
    }

    @Test
    void getReviewByRevieweeThatDoesNotExist() {
        User reviewee = null;
        ArrayList<Review> reviews = Database.getInstance().getReviewsByReviewee(reviewee);
        assertEquals(reviews.size(), 0);
    }

    @Test
    void getReviewByReviewerThatExists() {
        Review review = Database.getInstance().getReviews().get(0);
        User reviewer = review.getReviewer();
        ArrayList<Review> reviews = Database.getInstance().getReviewsByReviewer(reviewer);
        boolean correct = true;
        for (Review rev : reviews)
            if (!rev.getReviewer().equals(reviewer))
                correct = false;
        assertTrue(correct);
    }

    @Test
    void getReviewByReviewerThatDoesNotExist() {
        User reviewer = null;
        ArrayList<Review> reviews = Database.getInstance().getReviewsByReviewer(reviewer);
        assertEquals(reviews.size(), 0);
    }

    @Test
    void getRemovedReviews() {
        Review removedReview = new Review(UUID.randomUUID(), null, null, 0, "", true);
        Database.getInstance().addReview(removedReview);
        ArrayList<Review> removedReviews = Database.getInstance().getRemovedReviews();
        boolean found = false;
        for (Review review : removedReviews)
            if (review.equals(removedReview))
                found = true;
        assertTrue(found);
    }
    // end testing get reviews method

    // testing get user methods
    @Test
    void getUnapprovedUsers() {
        User user = new Student(UUID.randomUUID(), "", "", "", "", "", false, Major.NA, false, null, null, null, 0,
                false);
        Database.getInstance().addUser(user);
        ArrayList<User> unapproved = Database.getInstance().getUnapprovedUsers();
        boolean found = false;
        for (User us : unapproved)
            if (us.equals(user))
                found = true;
        assertTrue(found);
    }

    @Test
    void getRemovedUsers() {
        User removedUser = new Student(UUID.randomUUID(), "", "", "", "", "", true, Major.NA, true, null, null, null, 0,
                true);
        Database.getInstance().addUser(removedUser);
        ArrayList<User> removedUsers = Database.getInstance().getRemovedUsers();
        boolean found = false;
        for (User us : removedUsers)
            if (us.equals(removedUser))
                found = true;
        assertTrue(found);
    } // FAILS does not return all removed users

    @Test
    void getAdmin() {
        Admin admin = new Admin(UUID.randomUUID(), "", "", "", "", "", true);
        Database.getInstance().addUser(admin);
        ArrayList<Admin> users = Database.getInstance().getAdmin();
        boolean found = false;
        for (Admin ad : users)
            if (ad.equals(admin))
                found = true;
        assertTrue(found);
    }

    @Test
    void getStudents() {
        Student student = new Student(UUID.randomUUID(), "", "", "", "", "", false, Major.NA, false, null, null, null,
                0, true);
        Database.getInstance().addUser(student);
        ArrayList<Student> users = Database.getInstance().getStudents();
        boolean found = false;
        for (Student stud : users)
            if (stud.equals(student))
                found = true;
        assertTrue(found);
    }

    @Test
    void getEmployers() {
        Employer employer = new Employer(UUID.randomUUID(), "", "", "", "", "", false, "", 0, true);
        Database.getInstance().addUser(employer);
        ArrayList<Employer> users = Database.getInstance().getEmployers();
        boolean found = false;
        for (Employer emp : users)
            if (emp.equals(employer))
                found = true;
        assertTrue(found);
    }

    @Test
    void getProfessors() {
        Professor professor = new Professor(UUID.randomUUID(), "", "", "", "", "", true, false);
        Database.getInstance().addUser(professor);
        ArrayList<Professor> users = Database.getInstance().getProfessor();
        boolean found = false;
        for (Professor prof : users)
            if (prof.equals(professor))
                found = true;
        assertTrue(found);
    }

    // end testing get user methods
    // testing find user methods
    @Test
    void findUserByUsernameThatExists() {
        User user = Database.getInstance().getUsers().get(0);
        String username = user.getUsername();
        assertEquals(user, Database.getInstance().findByUsername(username));
    }

    @Test
    void findUserByUsernameThatDoesNotExist() {
        assertNull(Database.getInstance().findByUsername(""));
    }

    @Test
    void findUserByNameThatExists() {
        User user = Database.getInstance().getUsers().get(0);
        String name = user.getFullName();
        assertEquals(user, Database.getInstance().findByName(name));
    }

    @Test
    void findUserByNameThatDoesNotExist() {
        assertNull(Database.getInstance().findByName(""));
    }

    @Test
    void findUserByEmailThatExists() {
        User user = Database.getInstance().getUsers().get(0);
        String email = user.getEmail();
        assertEquals(user, Database.getInstance().getUserByEmail(email));
    }

    @Test
    void findUserByEmailThatDoesNotExist() {
        assertNull(Database.getInstance().getUserByEmail(""));
    }
    // end testing find user methods

}