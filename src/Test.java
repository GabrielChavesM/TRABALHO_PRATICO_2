// Test.java
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

interface Test {
    void processTest(Scanner scanner) throws IOException;
    Map<String, Double> getStudentScores();
}
