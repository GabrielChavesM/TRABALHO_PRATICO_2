// Client.java
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        // Calls all the test methods
        try (Scanner scanner = new Scanner(System.in)) {
            Test americanTest = new AmericanTest();
            americanTest.processTest(scanner);

            Test trueOrFalseTest = new TrueOrFalseTest();
            trueOrFalseTest.processTest(scanner);

            Test multipleQuestionsTest = new MultipleQuestionsTest();
            multipleQuestionsTest.processTest(scanner);

            // Print scores for all students
            printAllStudentScores(
                    americanTest.getStudentScores(),
                    trueOrFalseTest.getStudentScores(),
                    multipleQuestionsTest.getStudentScores()
            );
        }
    }

    private static void printAllStudentScores(
            Map<String, Double> scoresAmerican,
            Map<String, Double> scoresTrueFalse,
            Map<String, Double> scoresMult) {

        clearConsole();
        printScores("Teste Americano", scoresAmerican);
        printScores("Teste Verdadeiro/Falso", scoresTrueFalse);
        printScores("Teste Escolha Múltipla", scoresMult);
    }

    // Print all the scores for all students
    private static void printScores(String testName, Map<String, Double> scores) {
        System.out.println("Notas de: " + testName);
        if (scores.isEmpty()) {
            System.out.println("Sem notas disponíveis");
        } else {
            for (Map.Entry<String, Double> entry : scores.entrySet()) {
                System.out.println("Nota de " + entry.getKey() + ": " + entry.getValue());
            }
            double averageScore = calculateAverageScore(scores);
            System.out.println("Média do " + testName + ": " + averageScore);
            System.out.println("\n-------------------------------------\n");
        }
    }

    private static double calculateAverageScore(Map<String, Double> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }
        double totalScore = scores.values().stream().mapToDouble(Double::doubleValue).sum();
        return totalScore / scores.size();
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");  // Clear console
        System.out.flush();
    }
}
