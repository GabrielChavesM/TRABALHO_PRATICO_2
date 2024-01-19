package tests;

import org.junit.jupiter.api.Test;

import main.Client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    @Test
    public void testClient() throws IOException {
        // Set up input data
        String input = "4\n" +
                "1 F ”Quanto é 10+10?”\n" +
                "A 10 -0.25\n" +
                "B 20 1\n" +
                "C 30 -0.25\n" +
                "D 40 -0.25\n" +
                "2 F ”Quanto vale 10-5?”\n" +
                "A 1 -0.25\n" +
                "B 5 1\n" +
                "C 10 -0.25\n" +
                "D 15 -0.25\n" +
                "3 F ”Quanto vale 5-10?”\n" +
                "A 1 -0.25\n" +
                "B -5 1\n" +
                "C 10 -0.25\n" +
                "D 15 -0.25\n" +
                "4 A ”Prove que 15*3 = 15+15+15”\n" +
                "2\n" +
                "5\n" +
                "Amilcar\n" +
                "1 B\n" +
                "2 C\n" +
                "3 D\n" +
                "4 2\n" +
                "Beatriz\n" +
                "1 A\n" +
                "2 B\n" +
                "3 C\n" +
                "4 -\n" +
                "Carina\n" +
                "1 -\n" +
                "2 C\n" +
                "3 D\n" +
                "4 1\n" +
                "Dinis\n" +
                "1 D\n" +
                "2 B\n" +
                "3 A\n" +
                "4 2\n" +
                "Eurico\n" +
                "1 C\n" +
                "2 A\n" +
                "3 -\n" +
                "4 -\n" +
                "2\n" +
                "1\n" +
                "“1+1 é 2?”\n" +
                "V\n" +
                "2\n" +
                "F\n" +
                "-2\n" +
                "2\n" +
                "“2+2 é 5?”\n" +
                "V\n" +
                "-2\n" +
                "F\n" +
                "2\n" +
                "Joao\n" +
                "P1 V\n" +
                "P2 F\n" +
                "Ana\n" +
                "P1 F\n" +
                "P2 V\n" +
                "5\n" +
                "1 F ”Quanto é 10+10?”\n" +
                "A 10 -0.5\n" +
                "B 20 1\n" +
                "C 30 -0.5\n" +
                "D 40 -0.5\n" +
                "E 50 -0.5\n" +
                "2 F ”Quanto vale 10-5?”\n" +
                "A 1 -0.5\n" +
                "B 5 1\n" +
                "C 10 -0.5\n" +
                "D 15 -0.5\n" +
                "E 20 -0.5\n" +
                "3 F ”Quanto vale 5-10?”\n" +
                "A 1 -0.5\n" +
                // Add the rest of the input
                "B -5 1\n" +
                "C 10 -0.5\n" +
                "D 15 -0.5\n" +
                "E 20 -0.5\n" +
                "4 F ”Prove que 15*3 = 15+15+15”\n" +
                "A 1 -0.5\n" +
                "B -5 1\n" +
                "C 10 -0.5\n" +
                "D 15 -0.5\n" +
                "E 20 -0.5\n" +
                "5 F ”Prove que 15*3 = 15+15+15”\n" +
                "A 1 -0.5\n" +
                "B -5 1\n" +
                "C 10 -0.5\n" +
                "D 15 -0.5\n" +
                "E 20 -0.5\n" +
                "2\n" +
                "Alexandra\n" +
                "1 B\n" +
                "2 B\n" +
                "3 B\n" +
                "4 B\n" +
                "5 B\n" +
                "Bráulio\n" +
                "1 A\n" +
                "2 A\n" +
                "3 A\n" +
                "4 A\n" +
                "5 A\n";

        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Set up System.in to provide input
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Run the main method of Client
        Client.main(new String[]{});

        // Restore System.out and System.in
        System.setOut(System.out);
        System.setIn(System.in);

        // Expected output
        String expectedOutput = "Notas de: Teste Americano\n" +
                "Nota de Eurico: -0.5\n" +
                "Nota de Carina: 1.5\n" +
                "Nota de Amilcar: 2.5\n" +
                "Nota de Dinis: 2.5\n" +
                "Nota de Beatriz: 0.5\n" +
                "Média do Teste Americano: 1.3\n" +
                "\n" +
                "-------------------------------------\n" +
                "\n" +
                "Notas de: Teste Verdadeiro/Falso\n" +
                "Nota de Joao: 4.0\n" +
                "Nota de Ana: -4.0\n" +
                "Média do Teste Verdadeiro/Falso: 0.0\n" +
                "\n" +
                "-------------------------------------\n" +
                "\n" +
                "Notas de: Teste Escolha Múltipla\n" +
                "Nota de Bráulio: -2.5\n" +
                "Nota de Alexandra: 5.0\n" +
                "Média do Teste Escolha Múltipla: 1.25\n" +
                "\n" +
                "-------------------------------------\n";

        // Compare the actual and expected output
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }
}
