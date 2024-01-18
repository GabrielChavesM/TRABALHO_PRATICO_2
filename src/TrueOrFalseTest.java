// TrueOrFalseTest.java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TrueOrFalseTest implements Test {
    private Map<String, Double> studentScores = new HashMap<>();

    @Override
    public void processTest(Scanner scanner) throws IOException {
        try {
            BufferedWriter writerTrueOrFalse = new BufferedWriter(new FileWriter("data/data.txt"));
            int nrQuestTrueOrFalse = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < nrQuestTrueOrFalse; i++) {
                int questNr = scanner.nextInt();
                writerTrueOrFalse.write("P" + questNr + "\n");
                scanner.nextLine();
                String question = scanner.nextLine();
                writerTrueOrFalse.write(question + "\n");

                int numOptions = 2;
                for (int j = 0; j < numOptions; j++) {
                    String trueFalse = scanner.nextLine();
                    writerTrueOrFalse.write(trueFalse + "\n");
                    Double score = Double.parseDouble(scanner.nextLine());
                    writerTrueOrFalse.write(score + "\n");
                }
            }
            writerTrueOrFalse.close();

            int nrStudentsTrueOrFalse = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < nrStudentsTrueOrFalse; i++) {
                String studentName = scanner.next();
                double studentScore = 0.0;

                int nrQuestions = nrQuestTrueOrFalse;

                for (int j = 0; j < nrQuestions; j++) {
                    String studentQuestNr = scanner.next();
                    String studentAnswer = scanner.next();

                    try (BufferedReader reader = new BufferedReader(new FileReader("data/data.txt"))) {
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

    @Override
    public Map<String, Double> getStudentScores() {
        return Collections.unmodifiableMap(studentScores);
    }
}
