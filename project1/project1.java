import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class project1 {

    public static void main(String[] args) {
        List<String> romanNumerals = readRomanNumeralsFromFile("input.txt");
        String[] romanNumeralArray = romanNumerals.toArray(new String[0]);
        RomanNumeralGUI gui = new RomanNumeralGUI(romanNumeralArray);
        gui.setVisible(true);
    }

    private static List<String> readRomanNumeralsFromFile(String fileName) {
        List<String> romanNumerals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    romanNumerals.add(part.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return romanNumerals;
    }
}
