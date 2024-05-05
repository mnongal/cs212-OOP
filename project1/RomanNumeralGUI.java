import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RomanNumeralGUI extends JFrame {

    private static final Map<Character, Integer> ROMAN_TO_DECIMAL = new HashMap<>();
    static {
        ROMAN_TO_DECIMAL.put('I', 1);
        ROMAN_TO_DECIMAL.put('V', 5);
        ROMAN_TO_DECIMAL.put('X', 10);
        ROMAN_TO_DECIMAL.put('L', 50);
        ROMAN_TO_DECIMAL.put('C', 100);
        ROMAN_TO_DECIMAL.put('D', 500);
        ROMAN_TO_DECIMAL.put('M', 1000);
    }

    public RomanNumeralGUI(String[] numerals) {
        super("Roman Numerals");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        List<JLabel> romanLabels = new ArrayList<>();
        List<JLabel> decimalLabels = new ArrayList<>();
        for (String numeral : numerals) {
            String romanNumeral = numeral.trim();
            int decimalValue = valueOf(romanNumeral);
            JLabel romanLabel = new JLabel(romanNumeral);
            JLabel decimalLabel = new JLabel(Integer.toString(decimalValue));
            romanLabels.add(romanLabel);
            decimalLabels.add(decimalLabel);
        }

        JPanel panel = new JPanel(new GridLayout(0, 2));
        for (int i = 0; i < romanLabels.size(); i++) {
            panel.add(romanLabels.get(i));
            panel.add(decimalLabels.get(i));
        }
        add(panel);
    }
    public static int valueOf(String romanNumeral) {
        int decimalValue = 0;
        int lastDigit = 0;
        for (int i = romanNumeral.length() - 1; i >= 0; i--) {
            int digit = ROMAN_TO_DECIMAL.get(romanNumeral.charAt(i));
            if (digit < lastDigit) {
                decimalValue -= digit;
            } else {
                decimalValue += digit;
            }
            lastDigit = digit;
        }
        return decimalValue;
    }
}






