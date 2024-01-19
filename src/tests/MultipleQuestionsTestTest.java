package tests;

import main.MultipleQuestionsTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class MultipleQuestionsTestTest {

    @Test
    public void testProcessTestWithCorrectAnswers() throws IOException {
        MultipleQuestionsTest test = new MultipleQuestionsTest();
        Scanner scanner = new Scanner("2\n" +
                "1\n" +
                "“1+1 é 2?”\n" +
                "V\n" +
                "2\n" +
                "F\n" +
                "-2\n" +
                "2\n" +
                "“2+2 é 5?”\n" +
                "V\n" +
                "-2\n" +
                "F\n" +
                "2\n" +
                "2\n" +
                "João\n" +
                "P1 V\n" +
                "P2 F\n" +
                "Ana\n" +
                "P1 F\n" +
                "P2 V");
        test.processTest(scanner);

        Map<String, Double> studentScores = test.getStudentScores();
        assertEquals(-2.5, studentScores.get("João"));
        assertEquals(5.0, studentScores.get("Ana"));

        double averageScore = studentScores.values().stream().mapToDouble(Double::doubleValue).sum() / studentScores.size();
        assertEquals(1.25, averageScore);
    }

    @Test
    public void testProcessTestWithIncorrectAnswer() throws IOException {
        MultipleQuestionsTest test = new MultipleQuestionsTest();
        Scanner scanner = new Scanner("2\n" +
                "1\n" +
                "“1+1 é 2?”\n" +
                "V\n" +
                "2\n" +
                "F\n" +
                "-2\n" +
                "2\n" +
                "“2+2 é 5?”\n" +
                "F\n" +
                "-2\n" +
                "V\n" +
                "2\n" +
                "2\n" +
                "João\n" +
                "P1 V\n" +
                "P2 F\n" +
                "Ana\n" +
                "P1 F\n" +
                "P2 V");
        test.processTest(scanner);

        Map<String, Double> studentScores = test.getStudentScores();
        assertEquals(-5.0, studentScores.get("João"));
        assertEquals(0.0, studentScores.get("Ana"));

        double averageScore = studentScores.values().stream().mapToDouble(Double::doubleValue).sum() / studentScores.size();
        assertEquals(-1.875, averageScore);
    }
}
