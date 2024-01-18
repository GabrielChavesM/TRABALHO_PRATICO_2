import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class AmericanTest {
    private Map<String, Double> studentScores = new HashMap<>();

    public void processAmericanTest(Scanner scanner) throws IOException {
        String questionLine = scanner.nextLine();
        int nrQuest = Integer.parseInt(questionLine.split("\\s")[0]);
        // * HashMaps usados para armazenar as respostas corretas e a pontuação das
        // * questões, facilitando o cálculo das notas dos alunos.
        Map<Integer, String[]> respostasCorretas = new HashMap<>();
        Map<Integer, Double[]> pontuacaoQuestoes = new HashMap<>();

        // Criação de um arquivo para armazenar as respostas das perguntas.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/data.txt"))) {
            for (int i = 0; i < nrQuest; i++) {
                String userInput = scanner.nextLine();
                Scanner stringScanner = new Scanner(userInput);
                int QuestNr = stringScanner.nextInt();
                writer.write(QuestNr + " "); // Armazena o número da questão
                String QuestType = stringScanner.next();
                writer.write(QuestType + "\n"); // Armazena o tipo de questão
                stringScanner.next();
                stringScanner.close();

                if (QuestType.equals("F")) {
                    int nrOpcoes = 4;
                    String[] respostas = new String[nrOpcoes];
                    Double[] QuestScore = new Double[nrOpcoes];

                    for (int j = 0; j < nrOpcoes; j++) {
                        String optionInput = scanner.nextLine();
                        String[] parts = optionInput.split("\\s+");

                        respostas[j] = parts[0];
                        QuestScore[j] = Double.parseDouble(parts[2]);

                        // Armazena as letras e pontuações das respostas
                        writer.write(respostas[j] + "\n");
                        writer.write(QuestScore[j] + "\n");
                    }
                    // Passa para os hashmaps os seguintes valores
                    respostasCorretas.put(QuestNr, respostas);
                    pontuacaoQuestoes.put(QuestNr, QuestScore);
                }

                if (QuestType.equals("A")) {
                    int QuestScoreA = scanner.nextInt();
                    writer.write(Integer.toString(QuestScoreA) + "\n");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void readAmericanTest(Scanner scanner) throws IOException {
        try {
            // Giving students their scores
            int nrStudents = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < nrStudents; i++) {
                String studentName = scanner.next();
                double studentScore = 0.0;
                // * Para avaliar os alunos, é lido o arquivo para comparar as respostas
                // * e aplicar a cotação correta.
                BufferedReader reader = new BufferedReader(new FileReader("data/data.txt"));

                // São 4 alíneas para cada pergunta
                for (int j = 0; j < 4; j++) {
                    int studentQuestNr = scanner.nextInt();
                    String studentQuestLetter = scanner.next();

                    // 3 respostas fechadas
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

    public Map<String, Double> getStudentScores() {
        return Collections.unmodifiableMap(studentScores);
    }
}
