import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            AmericanTest americanTest = new AmericanTest();
            americanTest.processAmericanTest(scanner);
            americanTest.readAmericanTest(scanner);
            TrueOrFalseTest trueOrFalseTest = new TrueOrFalseTest();
            trueOrFalseTest.processTrueOrFalseTest(scanner);
            MultipleQuestionsTest multipleQuestionsTest = new MultipleQuestionsTest();
            Map<String, Double> scoresMult = multipleQuestionsTest.processMultipleQuestionsTest(scanner);

            // Print scores for all students
            printAllStudentScores(americanTest.getStudentScores(), trueOrFalseTest.getStudentScores(), scoresMult);
        }
    }

    private static void printAllStudentScores(
      Map<String, Double> scoresAmerican,
      Map<String, Double> scoresTrueFalse,
      Map<String, Double> scoresMult) {

        clearConsole();
        // Print scores for the American Test
        printScores("Teste Americano", scoresAmerican);

        // Print scores for the True/False Test
        printScores("Teste Verdadeiro/Falso", scoresTrueFalse);

        // Print scores for the Multiple Questions Test
        printScores("Teste Escolha Múltipla", scoresMult);
    }

    private static void printScores(String testName, Map<String, Double> scores) {
        System.out.println("Notas de: " + testName);
        if (scores.isEmpty()) {
            System.out.println("Sem notas disponíveis");
        } 
        else {
          for (Map.Entry<String, Double> entry : scores.entrySet()) {
              System.out.println("Nota de " + entry.getKey() + ": " + entry.getValue());
          }
          double averageScore = calculateAverageScore(scores);
          System.out.println("Média de " + testName + ": " + averageScore);
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
