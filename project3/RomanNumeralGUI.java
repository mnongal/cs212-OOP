import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.*;

// The main class is called RomanNumeralGUI, which extends the JFrame class to create a window.
public class RomanNumeralGUI extends JFrame {
    /*
     * The class has a nested IllegalRomanNumeralException class which extends
     * IllegalArgumentException.
     * The IllegalRomanNumeralException class extends IllegalArgumentException and
     * provides a constructor to set a custom error message.
     */

    public class IllegalRomanNumeralException extends IllegalArgumentException {
        public IllegalRomanNumeralException(String message) {
            super(message);
        }
    }

    /*
     * The class has instance variables to represent the UI components: textArea,
     * menuBar,
     * fileMenu, openMenuItem, quitMenuItem, convertMenu, and romanToArabicMenuItem.
     */
    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openMenuItem;
    private JMenuItem quitMenuItem;
    private JMenu convertMenu;
    private JMenuItem romanToArabicMenuItem;

    // The constructor initializes the UI components and sets the window properties.
    public RomanNumeralGUI() {
        super("Roman Numeral GUI");

        // Create TextArea
        textArea = new JTextArea();
        textArea.setEditable(false);
        // Add text area to scroll pane and add to frame
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create File menu and menu items
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(new FileMenuHandler());
        fileMenu.add(openMenuItem);

        quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(new FileMenuHandler());
        fileMenu.add(quitMenuItem);

        // Create Convert menu and menu items
        convertMenu = new JMenu("Convert");
        menuBar.add(convertMenu);

        romanToArabicMenuItem = new JMenuItem("Roman to Arabic");
        romanToArabicMenuItem.addActionListener(new ConvertMenuHandler());
        convertMenu.add(romanToArabicMenuItem);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    // The main method creates an instance of RomanNumeralGUI to start the
    // application.
    public static void main(String args[]) {
        new RomanNumeralGUI();
    }

    /*
     * The class has two nested classes that implement the ActionListener interface
     * to handle menu events:
     * // FileMenuHandler and ConvertMenuHandler.
     * The FileMenuHandler class handles the events of the "Open" and "Quit" menu
     * items in the "File" menu.
     */
    private class FileMenuHandler implements ActionListener {
        String str = "";

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == openMenuItem) {
                // Create file chooser
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose a file");
                int result = fileChooser.showOpenDialog(RomanNumeralGUI.this);

                // Handle file selection
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            str += line + "\n";
                        }
                        textArea.setText(str);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (e.getSource() == quitMenuItem) {
                System.exit(0);
            }
        }
    }

    // The ConvertMenuHandler class handles the event of the "Roman to Arabic" menu
    // item in the "Convert" menu.
    private class ConvertMenuHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == romanToArabicMenuItem) {
                String input = JOptionPane.showInputDialog("Enter a Roman numeral");
                try {
                    int result = convertRomanToArabic(input);
                    if (result == 0) {
                        throw new IllegalRomanNumeralException("demo");
                    }
                    if (result == -1) {
                    }

                    else
                        JOptionPane.showMessageDialog(RomanNumeralGUI.this, "Arabic value: " + result);
                } catch (IllegalRomanNumeralException ex) {
                    // System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(RomanNumeralGUI.this, "Invalid Roman numeral");
                }
            }
        }

        /*
         * The convertRomanToArabic method takes a Roman numeral as input and returns
         * its Arabic
         * equivalent. It also checks if the input is a valid Roman numeral and throws
         * an
         * IllegalRomanNumeralException if it is not
         */
        private int convertRomanToArabic(String romanNumeral) throws IllegalRomanNumeralException {
            Map<Character, Integer> romanValues = new HashMap<Character, Integer>();
            romanValues.put('I', 1);
            romanValues.put('V', 5);
            romanValues.put('X', 10);
            romanValues.put('L', 50);
            romanValues.put('C', 100);
            romanValues.put('D', 500);
            romanValues.put('M', 1000);

            int arabicNumeral = 0;
            int prevValue = 0;
            int prevCount = 0;
            romanNumeral = romanNumeral.toUpperCase();
            for (int i = romanNumeral.length() - 1; i >= 0; i--) {
                int currentValue = romanValues.getOrDefault(romanNumeral.charAt(i), 0);

                if (currentValue == 0) {
                    throw new IllegalRomanNumeralException(
                            "Invalid character in Roman numeral: " + romanNumeral.charAt(i));
                }

                if (currentValue < prevValue) {
                    if (prevCount > 1) {
                        throw new IllegalRomanNumeralException("Invalid Roman numeral: " + romanNumeral + ". "
                                + romanNumeral.charAt(i) + " cannot appear more than once before " + prevValue);
                    }
                    arabicNumeral -= currentValue;
                    prevCount = 1;
                } else {
                    if (prevCount >= 3 && prevValue == currentValue) {
                        throw new IllegalRomanNumeralException("Invalid Roman numeral: " + romanNumeral + ". "
                                + romanNumeral.charAt(i) + " cannot appear more than three times consecutively");
                    }
                    arabicNumeral += currentValue;
                    prevCount = (prevValue == currentValue) ? prevCount + 1 : 1;
                }

                prevValue = currentValue;
            }

            return arabicNumeral;
        }

    }

}