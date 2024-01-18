// AmericanTest.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AmericanTest implements Test {
    private Map<String, Double> studentScores = new HashMap<>();

    @Override
    public void processTest(Scanner scanner) throws IOException {
        String questionLine = scanner.nextLine();
        int nrQuest = Integer.parseInt(questionLine.split("\\s")[0]);
        Map<Integer, String[]> respostasCorretas = new HashMap<>();
        Map<Integer, Double[]> pontuacaoQuestoes = new HashMap<>();

        // Process American Test
        try (FileWriter writer = new FileWriter("data/data.txt")) {
            for (int i = 0; i < nrQuest; i++) {
                String userInput = scanner.nextLine();
                Scanner stringScanner = new Scanner(userInput);
                int questNr = stringScanner.nextInt();
                writer.write(questNr + " ");
                String questType = stringScanner.next();
                writer.write(questType + "\n");
                stringScanner.next();
                stringScanner.close();

                if (questType.equals("F")) {
                    int nrOpcoes = 4;
                    String[] respostas = new String[nrOpcoes];
                    Double[] questScore = new Double[nrOpcoes];

                    for (int j = 0; j < nrOpcoes; j++) {
                        String optionInput = scanner.nextLine();
                        String[] parts = optionInput.split("\\s+");

                        respostas[j] = parts[0];
                        questScore[j] = Double.parseDouble(parts[2]);

                        writer.write(respostas[j] + "\n");
                        writer.write(questScore[j] + "\n");
                    }

                    respostasCorretas.put(questNr, respostas);
                    pontuacaoQuestoes.put(questNr, questScore);
                }

                if (questType.equals("A")) {
                    int questScoreA = scanner.nextInt();
                    writer.write(Integer.toString(questScoreA) + "\n");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Call the readAmericanTest method
        readAmericanTest(scanner);
    }

    public void readAmericanTest(Scanner scanner) throws IOException {
        try {
            int nrStudents = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < nrStudents; i++) {
                String studentName = scanner.next();
                double studentScore = 0.0;
                BufferedReader reader = new BufferedReader(new FileReader("data/data.txt"));

                for (int j = 0; j < 4; j++) {
                    int studentQuestNr = scanner.nextInt();
                    String studentQuestLetter = scanner.next();

                    if (studentQuestNr >= 1 && studentQuestNr <= 3) {
                    
                        // * Organização das respostas do teste, de 8 em 8 linhas 
                        // * estão armazenadas as respostas e suas cotações, começando
                        // * pela linha 2.
                        int startLine = (studentQuestNr - 1) * 8 + 2;
                        int endLine = (studentQuestNr - 1) * 8 + 9;
    
                        for (int n = startLine; n <= endLine; n++) {
                            
                            String currentLine = reader.readLine();
    
                            // Se o valor for null, "-", a pontuação não se altera
                            if (currentLine == null) {
                                studentScore += 0;
                                break;
                            }
    
                            // * Compara a resposta do aluno com a resposta armazenada do
                            // * teste e lê a linha imediatamente a seguir aonde está
                            // * a sua cotação e a soma á nota final do aluno.
                            if (currentLine.trim().equals(studentQuestLetter)) {
                                double QuestScore = Double.parseDouble(reader.readLine());
                                studentScore += QuestScore;
                                break;
                            }
                        }
                    }
                        // 1 resposta aberta
                        else if (studentQuestNr == 4 && (studentQuestLetter.equals("1") || studentQuestLetter.equals("2"))) {
                        studentScore += 2.0;
                    }
                }
                studentScores.put(studentName, studentScore);
                reader.close();
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
