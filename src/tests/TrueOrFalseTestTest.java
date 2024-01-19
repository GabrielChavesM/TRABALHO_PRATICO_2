package tests;

import org.junit.jupiter.api.Test;

import main.TrueOrFalseTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrueOrFalseTestTest {

    @Test
    void testProcessTest() {
        // Prepare input for the test
        String input = "2\n1\nQuestion 1?\nTrue\n2.0\nFalse\n0.0\n2\nQuestion 2?\nTrue\n1.5\nFalse\n0.0\n"
                + "3\nStudent1 1 True\nStudent2 2 False";

        // Redirect System.in
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Create an instance of the TrueOrFalseTest class
        TrueOrFalseTest trueOrFalseTest = new TrueOrFalseTest();

        // Run the processTest method of the TrueOrFalseTest class
        try {
            trueOrFalseTest.processTest(new Scanner(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Restore original System.in
        System.setIn(originalSystemIn);

        // Assertions based on the expected output
        // Modify these assertions based on your specific output expectations

        // Expected student scores
        Map<String, Double> expectedScores = new HashMap<>();
        expectedScores.put("Student1", 2.0);
        expectedScores.put("Student2", 0.0);

        assertEquals(expectedScores, trueOrFalseTest.getStudentScores());
    }
    
    @Test
    void testProcessTestWithNoQuestions() {
        // Test when there are no questions in the input
        String input = "0\n";

        // Redirect System.in
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Create an instance of the TrueOrFalseTest class
        TrueOrFalseTest trueOrFalseTest = new TrueOrFalseTest();

        // Run the processTest method of the TrueOrFalseTest class
        try {
            trueOrFalseTest.processTest(new Scanner(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Restore original System.in
        System.setIn(originalSystemIn);

        // Assertions based on the expected output
        // Modify these assertions based on your specific output expectations

        // Expected student scores for no questions
        assertEquals(Map.of(), trueOrFalseTest.getStudentScores());
    }

    @Test
    void testProcessTestWithSingleQuestion() {
        // Test when there is a single question in the input
        String input = "1\n1\nQuestion 1?\nTrue\n2.0\nFalse\n0.0\n1\nStudent1 1 True";

        // Redirect System.in
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Create an instance of the TrueOrFalseTest class
        TrueOrFalseTest trueOrFalseTest = new TrueOrFalseTest();

        // Run the processTest method of the TrueOrFalseTest class
        try {
            trueOrFalseTest.processTest(new Scanner(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Restore original System.in
        System.setIn(originalSystemIn);

        // Assertions based on the expected output
        // Modify these assertions based on your specific output expectations

        // Expected student scores for a single question
        Map<String, Double> expectedScores = new HashMap<>();
        expectedScores.put("Student1", 2.0);

        assertEquals(expectedScores, trueOrFalseTest.getStudentScores());
    }
    
    @Test
    void testProcessTestWithNoQuestions1() {
        // Test when there are no questions in the input
        String input = "0\n";

        // Redirect System.in
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Create an instance of the TrueOrFalseTest class
        TrueOrFalseTest trueOrFalseTest = new TrueOrFalseTest();

        // Run the processTest method of the TrueOrFalseTest class
        try {
            trueOrFalseTest.processTest(new Scanner(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Restore original System.in
        System.setIn(originalSystemIn);

        // Assertions based on the expected output
        // Modify these assertions based on your specific output expectations

        // Expected student scores for no questions
        assertEquals(Map.of(), trueOrFalseTest.getStudentScores());
    }

    @Test
    void testProcessTestWithSingleQuestion1() {
        // Test when there is a single question in the input
        String input = "1\n1\nQuestion 1?\nTrue\n2.0\nFalse\n0.0\n1\nStudent1 1 True";

        // Redirect System.in
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Create an instance of the TrueOrFalseTest class
        TrueOrFalseTest trueOrFalseTest = new TrueOrFalseTest();

        // Run the processTest method of the TrueOrFalseTest class
        try {
            trueOrFalseTest.processTest(new Scanner(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Restore original System.in
        System.setIn(originalSystemIn);

        // Assertions based on the expected output
        // Modify these assertions based on your specific output expectations

        // Expected student scores for a single question
        Map<String, Double> expectedScores = new HashMap<>();
        expectedScores.put("Student1", 2.0);

        assertEquals(expectedScores, trueOrFalseTest.getStudentScores());
    }

    @Test
    void testProcessTestWithNoStudents() {
        // Test when there are no students in the input
        String input = "2\n1\nQuestion 1?\nTrue\n2.0\nFalse\n0.0\n0\n";

        // Redirect System.in
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Create an instance of the TrueOrFalseTest class
        TrueOrFalseTest trueOrFalseTest = new TrueOrFalseTest();

        // Run the processTest method of the TrueOrFalseTest class
        try {
            trueOrFalseTest.processTest(new Scanner(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Restore original System.in
        System.setIn(originalSystemIn);

        // Assertions based on the expected output
        // Modify these assertions based on your specific output expectations

        // Expected student scores for no students
        assertEquals(Map.of(), trueOrFalseTest.getStudentScores());
    }
    
    // Z
    
}
