import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class MultipleQuestionsTest {

    public Map<String, Double> processMultipleQuestionsTest(Scanner scanner) throws IOException {
        int nrQuestMult = scanner.nextInt();
        scanner.nextLine();
        Map<Integer, String[]> respostasMultCorretasMult = new HashMap<>();
        Map<Integer, Double[]> pontuacaoQuestoesMult = new HashMap<>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/data.txt"))) {
            for (int i = 0; i < nrQuestMult; i++) {
                String userInputMult = scanner.nextLine();
                Scanner stringScannerMult = new Scanner(userInputMult);
                int QuestNrMult = stringScannerMult.nextInt();
                writer.write(QuestNrMult + " ");
                String QuestTypeMult = stringScannerMult.next();
                writer.write(QuestTypeMult + "\n");
                stringScannerMult.next();
                stringScannerMult.close();

                if (QuestTypeMult.equals("F")) {
                    int nrOpcoesMult = 5;
                    String[] respostasMult = new String[nrOpcoesMult];
                    Double[] QuestScoreMult = new Double[nrOpcoesMult];

                    for (int j = 0; j < nrOpcoesMult; j++) {
                        String optionInputMult = scanner.nextLine();
                        String[] partsMult = optionInputMult.split("\\s+");

                        respostasMult[j] = partsMult[0];
                        QuestScoreMult[j] = Double.parseDouble(partsMult[2]);

                        writer.write(respostasMult[j] + "\n");
                        writer.write(QuestScoreMult[j] + "\n");
                    }

                    respostasMultCorretasMult.put(QuestNrMult, respostasMult);
                    pontuacaoQuestoesMult.put(QuestNrMult, QuestScoreMult);
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
                        double QuestScoreMult = Double.parseDouble(readerMult.readLine());
                        studentScoreMult += QuestScoreMult;
                        break;
                    }
                }
            }
            studentScoreMults.put(studentNameMult, studentScoreMult);
            readerMult.close();
        }

        // Output student scores
        return Collections.unmodifiableMap(studentScoreMults);
    }
}
