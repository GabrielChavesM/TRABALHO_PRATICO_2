package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A classe MultipleQuestionsTest implementa a interface Test e fornece métodos
 * para processar e avaliar um teste com múltiplas questões. O teste inclui
 * perguntas de múltipla escolha.
 *
 * A classe oferece suporte à leitura e gravação de dados de teste em um arquivo
 * e ao cálculo das pontuações para os alunos com base em suas respostas.
 */
public class MultipleQuestionsTest implements Test {

    /**
     * Mapa para armazenar as pontuações de cada aluno.
     */
    private Map<String, Double> studentScores = new HashMap<>();

    /**
     * Processa o teste de múltiplas questões com base no Scanner fornecido.
     * Lê as perguntas, opções e respostas dos alunos, calcula as pontuações
     * e armazena os resultados em um arquivo.
     *
     * @param scanner O objeto Scanner usado para entrada.
     * @throws IOException Se ocorrer um erro de E/S.
     * @param nrQuestMult Quantidade de perguntas do teste de múltipla escolha.
     * @param respostasMultCorretasMult Mapa para armazenar as respostas corretas das questões de múltipla escolha.
     * @param pontuacaoQuestoesMult Mapa para armazenar as pontuações das questões de múltipla escolha.
     */
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

        studentScores = studentScoreMults;
    }

    /**
     * Recupera um mapa não modificável contendo as pontuações de cada aluno.
     *
     * @return Um mapa não modificável de nomes de alunos e suas respectivas pontuações.
     */
    @Override
    public Map<String, Double> getStudentScores() {
        return Collections.unmodifiableMap(studentScores);
    }
}
