package main;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * A interface Test define métodos para processar e avaliar um teste,
 * assim como recuperar as pontuações dos alunos.
 */

public interface Test {
    /**
     * Processa o teste com base no Scanner fornecido.
     *
     * @param scanner O objeto Scanner usado para entrada.
     * @throws IOException Se ocorrer um erro de E/S durante o processamento do teste.
     */
    void processTest(Scanner scanner) throws IOException;
    /**
     * Recupera um mapa contendo as pontuações de cada aluno.
     *
     * @return Um mapa de nomes de alunos e suas respectivas pontuações.
     */
    Map<String, Double> getStudentScores();
}
