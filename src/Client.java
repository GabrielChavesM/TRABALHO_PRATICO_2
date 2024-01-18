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

        // TODO Parte II - Leitura dos dados dos Alunos.
        int nrStudents = scanner.nextInt();
        scanner.nextLine();
        Map<String, Double> studentScores = new HashMap<>();

        // Ciclo igual para todos os estudantes
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
        

        // TODO Parte III - Armazenar respostas das perguntas de V/F no arquivo "data.txt"
        try {
            BufferedWriter writerTrueOrFalse = new BufferedWriter(new FileWriter("data/data.txt"));
            int nrQuestTrueOrFalse = scanner.nextInt(); // Número de perguntas V/F
            scanner.nextLine();

            // Ciclo igual para todas as perguntas
            for (int i = 0; i < nrQuestTrueOrFalse; i++) {
                int QuestNr = scanner.nextInt();
                writerTrueOrFalse.write("P" + QuestNr + "\n");
                scanner.nextLine(); // Limpar o buffer
                scanner.nextLine(); // Question

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
            scanner.nextLine(); // Consumir a quebra de linha pendente

            Map<String, Double> studentsScoresTrueOrFalse = new HashMap<>();

            // Ciclo igual para todos os estudantes
            for (int i = 0; i < nrStudentsTrueOrFalse; i++) {
                String studentName = scanner.next();
                double studentScore = 0.0;

                int nrQuestions = nrQuestTrueOrFalse;

                for (int j = 0; j < nrQuestions; j++) {
                    String studentQuestNr = scanner.next();
                    String studentAnswer = scanner.next();
                    
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("data/data.txt"));
                        String line;

                        // * Mesma lógica para o armazenamento, as opções marcadas
                        // * pelos alunos são comparadas ás opções armazenadas e é
                        // * atribúida á pontuação do aluno o valor de cada pergunta
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
            
            // TODO Parte V
            int nrQuestMult = scanner.nextInt();
            scanner.nextLine();

            // * HashMaps usados para armazenar as respostasMult corretas e a pontuação das
            // * questões, facilitando o cálculo das notas dos alunos.
            Map<Integer, String[]> respostasMultCorretasMult = new HashMap<>();
            Map<Integer, Double[]> pontuacaoQuestoesMult = new HashMap<>();

            // Criação de um arquivo para armazenar as respostasMult das perguntas.
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/data.txt"))) {
                for (int i = 0; i < nrQuestMult; i++) {
                    String userInputMult = scanner.nextLine(); 
                    Scanner stringScannerMult = new Scanner(userInputMult);
                    int QuestNrMult = stringScannerMult.nextInt();
                    writer.write(QuestNrMult + " "); // Armazena o número da questão
                    String QuestTypeMult = stringScannerMult.next();
                    writer.write(QuestTypeMult + "\n"); // Armazena o tipo de questão
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

                            // Armazena as letras e pontuações das respostasMult
                            writer.write(respostasMult[j] + "\n");
                            writer.write(QuestScoreMult[j] + "\n");
                        }
                        // Passa para os hashmaps os seguintes valores
                        respostasMultCorretasMult.put(QuestNrMult, respostasMult);
                        pontuacaoQuestoesMult.put(QuestNrMult, QuestScoreMult);
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        // TODO Parte VI - Leitura dos dados dos alunos de escolha múltipla.
            int nrStudentsMult = scanner.nextInt();
            scanner.nextLine();
            Map<String, Double> studentScoreMults = new HashMap<>();

            // Ciclo igual para todos os estudantes
            for (int i = 0; i < nrStudentsMult; i++) {
                String studentNameMult = scanner.next();
                double studentScoreMult = 0.0;
                // * Para avaliar os alunos, é lido o arquivo para comparar as respostasMult
                // * e aplicar a cotação correta.
                BufferedReader readerMult = new BufferedReader(new FileReader("data/data.txt"));

                // São 5 alíneas para cada pergunta
                for (int j = 0; j < 5; j++) {
                    int studentQuestNrMult = scanner.nextInt();
                    String studentQuestLetterMult = scanner.next();
  
                    // * Organização das respostasMult do teste, de 10 em 10 linhas 
                    // * estão armazenadas as respostasMult e suas cotações, começando
                    // * pela linha 2.
                    int startLine = (studentQuestNrMult - 1) * 10 + 2;
                    int endLine = (studentQuestNrMult - 1) * 10 + 11;

                    for (int n = startLine; n <= endLine; n++) {
                        
                        String currentLine = readerMult.readLine();

                        // Se o valor for null, "-", a pontuação não se altera
                        if (currentLine == null) {
                            studentScoreMult += 0;
                            break;
                        }

                        // * Compara a resposta do aluno com a resposta armazenada do
                        // * teste e lê a linha imediatamente a seguir aonde está
                        // * a sua cotação e a soma á nota final do aluno.
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

        // Imprime a nota de todos os alunos.
        System.out.print("\033\143"); // Limpar a consola
        for (Map.Entry<String, Double> entry : studentScores.entrySet()) {
            System.out.println("Nota de " + entry.getKey() + ": " + entry.getValue());
        }
        // Exibir notas dos alunos para as questões V/F
        System.out.println("\nNotas dos alunos V/F:");
        for (Map.Entry<String, Double> entry : studentsScoresTrueOrFalse.entrySet()) {
            System.out.println("Nota de " + entry.getKey() + ": " + entry.getValue());
        }
        // Imprime a nota dos alunos Escolha Múltipla.
        System.out.println("\nNotas dos alunos Escolha Múltipla:");
        for (Map.Entry<String, Double> entry : studentScoreMults.entrySet()) {
            System.out.println("Nota de " + entry.getKey() + ": " + entry.getValue());
        }
    } catch(NumberFormatException e) {
        e.printStackTrace();
    }
    scanner.close();
    }
}