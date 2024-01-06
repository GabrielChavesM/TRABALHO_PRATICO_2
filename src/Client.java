import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        // TODO Parte I - Criação das perguntas.
        Scanner scanner = new Scanner(System.in);
        int nrQuest = scanner.nextInt();
        scanner.nextLine();

        // * HashMaps usados para armazenar as respostas corretas e a pontuação das
        // * questões, facilitando o cálculo das notas dos alunos.
        Map<Integer, String[]> respostasCorretas = new HashMap<>();
        Map<Integer, Double[]> pontuacaoQuestoes = new HashMap<>();

        // Criação de um arquivo para armazenar as respostas das perguntas.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
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
                    Double[] pontuacaoQuestao = new Double[nrOpcoes];

                    for (int j = 0; j < nrOpcoes; j++) {
                        String optionInput = scanner.nextLine();
                        String[] parts = optionInput.split("\\s+");

                        respostas[j] = parts[0];
                        pontuacaoQuestao[j] = Double.parseDouble(parts[2]);

                        // Armazena as letras e pontuações das respostas
                        writer.write(respostas[j] + "\n");
                        writer.write(pontuacaoQuestao[j] + "\n");
                    }
                    // Passa para os hashmaps os seguintes valores
                    respostasCorretas.put(QuestNr, respostas);
                    pontuacaoQuestoes.put(QuestNr, pontuacaoQuestao);
                }

                if (QuestType.equals("A")) {
                    int pontuacaoQuestaoA = scanner.nextInt();
                    writer.write(Integer.toString(pontuacaoQuestaoA) + "\n");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // TODO Parte II - Leitura dos dados dos Alunos.
        int nrStudents = scanner.nextInt();
        scanner.nextLine();
        Map<String, Double> studentScores = new HashMap<>();

        for (int i = 0; i < nrStudents; i++) {
            String studentName = scanner.next();
            double pontuacaoAluno = 0.0;
            // * Para avaliar os alunos, é lido o arquivo para comparar as respostas
            // * e aplicar a cotação correta.
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));

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
                            pontuacaoAluno += 0;
                            break;
                        }

                        // * Compara a resposta do aluno com a resposta armazenada do
                        // * teste e lê a linha imediatamente a seguir aonde está
                        // * a sua cotação e a soma á nota final do aluno.
                        if (currentLine.trim().equals(studentQuestLetter)) {
                            double pontuacaoQuestao = Double.parseDouble(reader.readLine());
                            pontuacaoAluno += pontuacaoQuestao;
                            break;
                        }
                    }
                }
                
                // 1 resposta aberta
                else if (studentQuestNr == 4 && (studentQuestLetter.equals("1") || studentQuestLetter.equals("2"))) {
                    pontuacaoAluno += 2.0;
                }
            }
            studentScores.put(studentName, pontuacaoAluno);
            reader.close();
        }

        // Imprime a nota de todos os alunos. 
        System.out.print("\033\143"); // Limpa a consola
        for (Map.Entry<String, Double> entry : studentScores.entrySet()) {
            System.out.println("Nota de " + entry.getKey() + ": " + entry.getValue());
        }

        
    // TODO Parte III - Armazenar respostas das perguntas no arquivo "data.txt"
    BufferedWriter writer2 = new BufferedWriter(new FileWriter("data.txt", true)); // Usar o append true para acrescentar ao arquivo existente

    /*
     * Ao contrário das perguntas acima, o formato de input das perguntas V/F é linha
     * a linha, por exemplo:
     * 1
     * "2+2 é 4?"
     * A
     * V
     * 2
     * B
     * F
     * -1
     */
    int nrQuest3 = scanner.nextInt();
    scanner.nextLine();

        for (int i = 0; i < nrQuest3; i++) {
            int QuestNr = scanner.nextInt();
            writer2.write(QuestNr + "\n");
            scanner.nextLine(); // Limpar o buffer

            String question = scanner.nextLine().trim();
            writer2.write(question + "\n");
            
            int numOptions = 2;

            for (int j = 0; j < numOptions; j++) {
                String option = scanner.nextLine();
                writer2.write(option + "\n");
                String trueFalse = scanner.nextLine();
                writer2.write(trueFalse + "\n");
                Double score = Double.parseDouble(scanner.nextLine());
                writer2.write(score + "\n");
            }
        }
    writer2.close(); // Fechar o arquivo
    }   
}

