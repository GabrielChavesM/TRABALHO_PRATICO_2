package main;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * A classe Client contém o método principal que executa testes e imprime as pontuações dos alunos.
 */

public class Client {

    /**
     * O método principal que executa testes e imprime as pontuações dos alunos.
     *
     * @param args Os argumentos da linha de comando (não utilizados neste caso).
     * @throws IOException Se ocorrer um erro de E/S durante a execução dos testes.
     * @param scanner O objeto Scanner usado para entrada.
     * @param americanTest Instância do teste americano.
     * @param trueOrFalseTest Instância do teste Verdadeiro ou Falso.
     * @param multipleQuestionsTest Instância do teste de Escolha Múltipla.
     */
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

    /**
     * Executa testes específicos e imprime as pontuações dos alunos.
     *
     * @param scoresAmerican Pontuações do teste americano.
     * @param scoresTrueFalse Pontuações do teste Verdadeiro ou Falso.
     * @param scoresMult Pontuações do teste de Escolha Múltipla.
     */
    private static void printAllStudentScores(
            Map<String, Double> scoresAmerican,
            Map<String, Double> scoresTrueFalse,
            Map<String, Double> scoresMult) {

        clearConsole();
        printScores("Teste Americano", scoresAmerican);
        printScores("Teste Verdadeiro/Falso", scoresTrueFalse);
        printScores("Teste Escolha Múltipla", scoresMult);
    }

    /**
     * Imprime as pontuações de um teste específico.
     *
     * @param testName Nome do teste.
     * @param scores Pontuações dos alunos no teste.
     */
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

    /**
     * Calcula a média das pontuações.
     *
     * @param scores Pontuações dos alunos.
     * @return A média das pontuações.
     */
    private static double calculateAverageScore(Map<String, Double> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }
        double totalScore = scores.values().stream().mapToDouble(Double::doubleValue).sum();
        return totalScore / scores.size();
    }

    /**
     * Limpa a tela da consola.
     */
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
