package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A classe TrueOrFalseTest implementa a interface Test e fornece métodos
 * para processar e avaliar um teste de Verdadeiro ou Falso.
 *
 * A classe oferece suporte à leitura e gravação de dados de teste em um arquivo
 * e ao cálculo das pontuações para os alunos com base em suas respostas.
 */

public class TrueOrFalseTest implements Test {

    /**
     * Mapa para armazenar as pontuações de cada aluno.
     */
    private Map<String, Double> studentScores = new HashMap<>();

    /**
     * Processa o teste de Verdadeiro ou Falso com base no Scanner fornecido.
     * Lê as perguntas, opções e respostas dos alunos, calcula as pontuações
     * e armazena os resultados em um arquivo.
     *
     * @param scanner O objeto Scanner usado para entrada.
     * @throws IOException Se ocorrer um erro de E/S durante o processamento do teste.
     * @param nrQuestTrueOrFalse Quantidade de perguntas do teste de Verdadeiro ou Falso.
     * @param question Pergunta do teste.
     * @param trueFalse Opções Verdadeiro ou Falso.
     * @param score Pontuação de cada opção.
     * @param nrStudentsTrueOrFalse Quantidade de alunos.
     * @param studentName Nome do aluno.
     * @param studentScore Pontuação do aluno.
     * @param studentQuestNr Número da questão respondida pelo aluno.
     * @param studentAnswer Resposta do aluno.
     */
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
