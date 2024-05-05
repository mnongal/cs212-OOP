import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

public class FileMenuHandler implements ActionListener {
   
   static JFrame jframe;
   static String date;
   static TextFileInput inFile;
   static Date212 obj;
   public static StringTokenizer myTokens;
   static Container myContentPane;
   static TextArea unsorted;
   static TextArea sorted;
   
   
   public FileMenuHandler (JFrame jf) {
      
      jframe = jf;
      obj = null;
      
      myContentPane = jframe.getContentPane();
      jframe.setLayout(new GridLayout(1, 3));
      unsorted = new TextArea();
      sorted = new TextArea();
   }
   
   public void actionPerformed(ActionEvent event) {
      String menuName = event.getActionCommand();
      if (menuName.equals("Open")) openFile();
      else if (menuName.equals("Quit")) System.exit(0);
   } //actionPerformed
   
   private void openFile() {
       //open a File Chooser and send the file to readSource method to read the contents
       JFileChooser chooser;
       int status;
       chooser = new JFileChooser();
       status = chooser.showOpenDialog(null);
       if (status == JFileChooser.APPROVE_OPTION)
           readSource(chooser.getSelectedFile());
       else
           JOptionPane.showMessageDialog(null, "Open File dialog canceled");
   }

   private void readSource(File chosenFile) {
       //get path of chosen file and use as input for TextFileInput
       String chosenFileName = chosenFile.getPath();
       TextFileInput inFile = new TextFileInput(chosenFileName);
       String date;

       //read a line of the file into 'date'
       date = inFile.readLine();

       while (date != null) 
       {
           //tokenize the line using a comma as a delimiter, and place each token into a dateObject
           myTokens = new StringTokenizer(date, ",");

           while (myTokens.hasMoreTokens()) {

               date = myTokens.nextToken();

               obj = new Date212(date);

               if (!Date212GUI.isValid(obj)) 
               {
                   throw new IllegalDate212Exception("Invalid date");
               }

               Date212GUI.storeDate(obj);

           }
           date = inFile.readLine();

       }
       
       //Refreshes the container so that it can show new information
       myContentPane = jframe.getContentPane();
       unsorted.setText("");
       sorted.setText("");
       myContentPane.add(unsorted);
       myContentPane.add(sorted);
       
       //create dateNodes containing the date info from the file, using the linked lists created by StoreDate method
       Date212Node dN2 = Date212GUI.sortedList.getFirst();
       Date212Node dN1 = Date212GUI.unsortedList.getFirst();

       while (dN1 != null && dN2 != null) {
           unsorted.append(Date212GUI.getValue(dN1) + "\n");
           sorted.append(Date212GUI.getValue(dN2) + "\n");

           //call next nodes
           dN1 = dN1.next;
           dN2 = dN2.next;
       }

       jframe.pack();
       jframe.setVisible(true);
   }
   
   public static void displayDatesByYear(String year) {
      
    //Refreshes the container so that it can show new information
      myContentPane = jframe.getContentPane();
      unsorted.setText("");
      sorted.setText("");
      myContentPane.add(unsorted);
      myContentPane.add(sorted);

      //create dateNodes containing the date info from the file, using the linked lists created by StoreDate method
      Date212Node dN2 = Date212GUI.sortedList.getFirst();
      Date212Node dN1 = Date212GUI.unsortedList.getFirst();

      while (dN1 != null && dN2 != null) {
         //Check and print only if the last four digits of the string match with the year being searched
         if(dN1.data.getYear() == Integer.parseInt(year)) unsorted.append(Date212GUI.getValue(dN1) + "\n");
         if(dN2.data.getYear() == Integer.parseInt(year)) sorted.append(Date212GUI.getValue(dN2) + "\n");

         //call next nodes
         dN1 = dN1.next;
         dN2 = dN2.next;
      }

      jframe.pack();
      jframe.setVisible(true);
   }
}