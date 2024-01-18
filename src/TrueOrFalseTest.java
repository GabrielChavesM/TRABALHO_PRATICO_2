import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TrueOrFalseTest {
    private Map<String, Double> studentScores = new HashMap<>();

    public void processTrueOrFalseTest(Scanner scanner) throws IOException {
        try {
            BufferedWriter writerTrueOrFalse = new BufferedWriter(new FileWriter("data/data.txt"));
            int nrQuestTrueOrFalse = scanner.nextInt(); // Número de perguntas V/F
            scanner.nextLine(); // Consume newline

            // Ciclo igual para todas as perguntas
            for (int i = 0; i < nrQuestTrueOrFalse; i++) {
                int QuestNr = scanner.nextInt();
                writerTrueOrFalse.write("P" + QuestNr + "\n");
                scanner.nextLine(); // Consume newline
                String question = scanner.nextLine(); // Read question
                writerTrueOrFalse.write(question + "\n");

                int numOptions = 2;
                // Armazenar valores das opções
                for (int j = 0; j < numOptions; j++) {
                    String trueFalse = scanner.nextLine();
                    writerTrueOrFalse.write(trueFalse + "\n");
                    Double score = Double.parseDouble(scanner.nextLine());
                    writerTrueOrFalse.write(score + "\n");
                }
            }
            writerTrueOrFalse.close(); // Fechar o arquivo

            // TODO Parte IV - Atribuir notas aos alunos comparando com as respostas no data.txt

            int nrStudentsTrueOrFalse = scanner.nextInt();
            scanner.nextLine(); // Consume the newline pending

            // Ciclo igual para todos os estudantes
            for (int i = 0; i < nrStudentsTrueOrFalse; i++) {
                String studentName = scanner.next();
                double studentScore = 0.0;

                int nrQuestions = nrQuestTrueOrFalse;

                for (int j = 0; j < nrQuestions; j++) {
                    String studentQuestNr = scanner.next();
                    String studentAnswer = scanner.next();

                    try (BufferedReader reader = new BufferedReader(new FileReader("data/data.txt"))) {
                        String line;

                        // * Mesma lógica para o armazenamento, as opções marcadas
                        // * pelos alunos são comparadas ás opções armazenadas e é
                        // * atribuída á pontuação do aluno o valor de cada pergunta
                        while ((line = reader.readLine()) != null) {
                            if (line.trim().equals(studentQuestNr)) {
                                int startLine = (Integer.parseInt(studentQuestNr.substring(1)) - 1) * 8 + 2;
                                int endLine = (Integer.parseInt(studentQuestNr.substring(1)) - 1) * 8 + 9;

                                for (int n = startLine; n <= endLine; n++) {
                                    String currentLine = reader.readLine();

                                    if (currentLine == null) {
                                        studentScore += 0;
                                        break;
                                    }

                                    if (currentLine.trim().equals(studentAnswer)) {
                                        double questScore = Double.parseDouble(reader.readLine().trim());
                                        studentScore += questScore;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                studentScores.put(studentName, studentScore);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Double> getStudentScores() {
        return Collections.unmodifiableMap(studentScores);
    }
}
