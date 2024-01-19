package tests;

import org.junit.jupiter.api.Test;

import main.AmericanTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class AmericanTestTest {

    @Test
    public void testProcessTestWithCorrectAnswers() throws IOException {
        AmericanTest test = new AmericanTest();
        Scanner scanner = new Scanner("4\n" +
                "1 F \"Quanto é 10+10?\"\n" +
                "A 10 -0.25\n" +
                "B 20 1\n" +
                "C 30 -0.25\n" +
                "D 40 -0.25\n" +
                "2 F \"Quanto vale 10-5?\"\n" +
                "A 1 -0.25\n" +
                "B 5 1\n" +
                "C 10 -0.25\n" +
                "D 15 -0.25\n" +
                "3 F \"Quanto vale 5-10?\"\n" +
                "A 1 -0.25\n" +
                "B -5 1\n" +
                "C 10 -0.25\n" +
                "D 15 -0.25\n" +
                "4 A \"Prove que 15*3 = 15+15+15\"\n" +
                "2\n" +
                "5\n" +
                "Amilcar\n" +
                "1 B\n" +
                "2 C\n" +
                "3 D\n" +
                "4 2\n" +
                "Beatriz \\\n" +
                "1 A \\\n" +
                "2 B \\\n" +
                "3 C \\\n" +
                "4 - \\\n" +
                "Carina\n" +
                "1 -\n" +
                "2 C\n" +
                "3 D\n" +
                "4 1\n" +
                "Dinis\n" +
                "1 D\n" +
                "2 B\n" +
                "3 A\n" +
                "4 2\n" +
                "Eurico\n" +
                "1 C\n" +
                "2 A\n" +
                "3 -\n" +
                "4 -");
        test.processTest(scanner);

        Map<String, Double> studentScores = test.getStudentScores();
        assertEquals(-0.5, studentScores.get("Eurico"));
        assertEquals(1.5, studentScores.get("Carina"));
        assertEquals(2.5, studentScores.get("Amilcar"));
        assertEquals(2.5, studentScores.get("Dinis"));
        assertEquals(0.5, studentScores.get("Beatriz"));

        double averageScore = studentScores.values().stream().mapToDouble(Double::doubleValue).sum() / studentScores.size();
        assertEquals(1.3, averageScore);
    }

    @Test
    public void testProcessTestWithIncorrectAnswer() throws IOException {
        AmericanTest test = new AmericanTest();
        Scanner scanner = new Scanner("4\n" +
                "1 F \"Quanto é 10+10?\"\n" +
                "A 10 1\n" + // Incorrect answer
                "B 20 1\n" +
                "C 30 -0.25\n" +
                "D 40 -0.25\n" +
                "2 F \"Quanto vale 10-5?\"\n" +
                "A 1 -0.25\n" +
                "B 5 1\n" +
                "C 10 -0.25\n" +
                "D 15 -0.25\n" +
                "3 F \"Quanto vale 5-10?\"\n" +
                "A 1 -0.25\n" +
                "B -5 1\n" + // Incorrect answer
                "C 10 -0.25\n" +
                "D 15 -0.25\n" +
                "4 A \"Prove que 15*3 = 15+15+15\"\n" +
                "2\n" +
                "5\n" +
                "Amilcar\n" +
                "1 B\n" +
                "2 C\n" +
                "3 D\n" +
                "4 2\n" +
                "Beatriz\n" +
                "1 A\n" +
                "2 B\n" +
                "3 C\n" +
                "4 -\n" +
                "Carina\n" +
                "1 -\n" +
                "2 C\n" +
                "3 D\n" +
                "4 1\n" +
                "Dinis\n" +
                "1 D\n" +
                "2 B\n" +
                "3 A\n" +
                "4 2\n" +
                "Eurico\n" +
                "1 C\n" +
                "2 A\n" +
                "3 -\n" +
                "4 -");

        test.processTest(scanner);

        Map<String, Double> studentScores = test.getStudentScores();
        assertEquals(0.0, studentScores.get("Amilcar")); // Amilcar got an incorrect answer in question 1
        assertEquals(1.5, studentScores.get("Carina"));
        assertEquals(2.5, studentScores.get("Dinis"));
        assertEquals(0.5, studentScores.get("Beatriz"));
        assertEquals(0.0, studentScores.get("Eurico")); // Eurico got an incorrect answer in question 3

        double averageScore = studentScores.values().stream().mapToDouble(Double::doubleValue).sum() / studentScores.size();
        assertEquals(1.0, averageScore); // Average score considering incorrect answers
    }
}
