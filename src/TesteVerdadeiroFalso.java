import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TesteVerdadeiroFalso {
    public static void main(String[] args) throws IOException {
      Scanner scanner = new Scanner(System.in);

    // TODO Parte III - Armazenar respostas das perguntas de V/F no arquivo "data_2.txt"
    try {
        BufferedWriter writerTrueOrFalse = new BufferedWriter(new FileWriter("data/data_2.txt")); // Append true para acrescentar ao arquivo existente

        int nrQuestTrueOrFalse = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < nrQuestTrueOrFalse; i++) {
            int QuestNr = scanner.nextInt();
            writerTrueOrFalse.write("P" + QuestNr + "\n");
            scanner.nextLine(); // Limpar o buffer

            String question = scanner.nextLine().trim();

            int numOptions = 2;

            for (int j = 0; j < numOptions; j++) {
                String trueFalse = scanner.nextLine();
                writerTrueOrFalse.write(trueFalse + "\n");
                Double score = Double.parseDouble(scanner.nextLine());
                writerTrueOrFalse.write(score + "\n");
            }
        }
        writerTrueOrFalse.close(); // Fechar o arquivo
        
        int nrStudentsTrueOrFalse = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        Map<String, Double> studentsScoresTrueOrFalse = new HashMap<>();

        for (int i = 0; i < nrStudentsTrueOrFalse; i++) {
            String studentName = scanner.next();
            double studentScore = 0.0;

            int nrQuestions = nrQuestTrueOrFalse;

            for (int j = 0; j < nrQuestions; j++) {
                String studentQuestNr = scanner.next();
                String studentAnswer = scanner.next();

                // TODO Parte IV - Atribuir notas aos alunos comparando com as respostas no data_2.txt
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("data/data_2.txt"));
                    String line;

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
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            studentsScoresTrueOrFalse.put(studentName, studentScore);
        }

        System.out.print("\033\143"); // Limpar a consola

        // Exibir notas dos alunos para as questões V/F
        System.out.println("\nNotas dos alunos V/F:");
        for (Map.Entry<String, Double> entry : studentsScoresTrueOrFalse.entrySet()) {
            System.out.println("Nota de " + entry.getKey() + ": " + entry.getValue());
        }
    } catch(NumberFormatException e) {
        e.printStackTrace();
    }
    }
}