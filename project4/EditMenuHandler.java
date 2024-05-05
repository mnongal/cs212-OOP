import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class EditMenuHandler implements ActionListener {
   
   static JFrame jframe;
   static String date;
   static TextFileInput inFile;
   static Date212 obj;
   public static StringTokenizer myTokens;
   
   public EditMenuHandler (JFrame jf) {
      
      jframe = jf;
      obj = null;
   }
   
   public void actionPerformed(ActionEvent event) {
      // Prompt the user to enter a year using JOptionPane input dialog
      String input = JOptionPane.showInputDialog("Enter a year (yyyy):");
      
      while(input.length() != 4) input = JOptionPane.showInputDialog("Error! Must be 4 digits, enter a year (yyyy):");
      // Display dates with the entered year in the text area
      FileMenuHandler.displayDatesByYear(input);
   }
   
}