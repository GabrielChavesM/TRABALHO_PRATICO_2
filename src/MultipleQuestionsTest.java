// MultipleQuestionsTest.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MultipleQuestionsTest implements Test {

    private Map<String, Double> studentScores = new HashMap<>();

    @Override
    public void processTest(Scanner scanner) throws IOException {
        int nrQuestMult = scanner.nextInt();
        scanner.nextLine();
        Map<Integer, String[]> respostasMultCorretasMult = new HashMap<>();
        Map<Integer, Double[]> pontuacaoQuestoesMult = new HashMap<>();

        try (FileWriter writer = new FileWriter("data/data.txt")) {
            for (int i = 0; i < nrQuestMult; i++) {
                String userInputMult = scanner.nextLine();
                Scanner stringScannerMult = new Scanner(userInputMult);
                int questNrMult = stringScannerMult.nextInt();
                writer.write(questNrMult + " ");
                String questTypeMult = stringScannerMult.next();
                writer.write(questTypeMult + "\n");
                stringScannerMult.next();
                stringScannerMult.close();

                if (questTypeMult.equals("F")) {
                    int nrOpcoesMult = 5;
                    String[] respostasMult = new String[nrOpcoesMult];
                    Double[] questScoreMult = new Double[nrOpcoesMult];

                    for (int j = 0; j < nrOpcoesMult; j++) {
                        String optionInputMult = scanner.nextLine();
                        String[] partsMult = optionInputMult.split("\\s+");

                        respostasMult[j] = partsMult[0];
                        questScoreMult[j] = Double.parseDouble(partsMult[2]);

                        writer.write(respostasMult[j] + "\n");
                        writer.write(questScoreMult[j] + "\n");
                    }

                    respostasMultCorretasMult.put(questNrMult, respostasMult);
                    pontuacaoQuestoesMult.put(questNrMult, questScoreMult);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        int nrStudentsMult = scanner.nextInt();
        scanner.nextLine();
        Map<String, Double> studentScoreMults = new HashMap<>();

        for (int i = 0; i < nrStudentsMult; i++) {
            String studentNameMult = scanner.next();
            double studentScoreMult = 0.0;
            BufferedReader readerMult = new BufferedReader(new FileReader("data/data.txt"));

            for (int j = 0; j < 5; j++) {
                int studentQuestNrMult = scanner.nextInt();
                String studentQuestLetterMult = scanner.next();

                int startLine = (studentQuestNrMult - 1) * 10 + 2;
                int endLine = (studentQuestNrMult - 1) * 10 + 11;

                for (int n = startLine; n <= endLine; n++) {
                    String currentLine = readerMult.readLine();

                    if (currentLine == null) {
                        studentScoreMult += 0;
                        break;
                    }

                    if (currentLine.trim().equals(studentQuestLetterMult)) {
                        double questScoreMult = Double.parseDouble(readerMult.readLine().trim());
                        studentScoreMult += questScoreMult;
                        break;
                    }
                }
            }
            studentScoreMults.put(studentNameMult, studentScoreMult);
            readerMult.close();
        }

        // Set the studentScores field
        studentScores = studentScoreMults;
    }

    @Override
    public Map<String, Double> getStudentScores() {
        return Collections.unmodifiableMap(studentScores);
    }
}
