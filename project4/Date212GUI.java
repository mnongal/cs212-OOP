import javax.swing.*;
import javax.swing.JMenuBar;

public class Date212GUI extends JFrame {
   
   static String date;
   static JFrame myFrame;
   static SortedDate212List sortedList;
   static UnsortedDate212List unsortedList;
   static JMenuBar menuBar;
   static JMenu fileMenu;
   static FileMenuHandler fmh;
   
   public Date212GUI() {
      //Variables related to the date file and linked lists
      date = "";
      sortedList = new SortedDate212List();
      unsortedList = new UnsortedDate212List();
      //Variables related to jFrames
      myFrame = new JFrame();
      myFrame.setSize(400, 200);
      myFrame.setLocation(100, 100);
      myFrame.setTitle("Dates");
      createMenu();
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   public static void storeDate(Date212 d) 
   {
       //Check that the date is valid
       if (isValid(d))
       {
           //Call 'add' function from Sorted and Unsorted classes to create different types of lists
           sortedList.add(d);
           unsortedList.add(d);
       }
   }
   
   public static boolean isValid(Date212 date) {
      //Runs if the input from the file is not in the expected format needed for the program to work
      if(date.getOriginal().length() != 8) throw new IllegalDate212Exception("The date must be in yyyymmdd format.");
      //If the if statement doesn't run that means the date is valid and true is returned
      return true;
   }
   
   public void createMenu() {
      //Create a file menu using the file menu handler class
      JMenuBar menuBar = new JMenuBar();
      JMenu fileMenu = new JMenu("File");
      JMenuItem item;
      FileMenuHandler fmh = new FileMenuHandler(this);

      //Create an item with an action listener and add to the file menu
      item = new JMenuItem("Open");
      item.addActionListener(fmh);
      fileMenu.add(item);

      fileMenu.addSeparator();

      //Create another item with action listener and add again
      item = new JMenuItem("Quit");
      item.addActionListener(fmh);
      fileMenu.add(item);
      
      //Create an edit menu using the file menu handler class and adding a new item with action listener
      JMenu editMenu = new JMenu("Edit");
      EditMenuHandler emh = new EditMenuHandler(this);
      item = new JMenuItem("Search");
      item.addActionListener(emh);
      editMenu.add(item);
      

      //Add a JMenuBar to the current jFrame and add the file menu to it
      this.setJMenuBar(menuBar);
      menuBar.add(fileMenu);
      menuBar.add(editMenu);
      
      //Run the menu so that the user can pick a file from it
      this.setSize(400, 200);
      this.setLocation(100, 100);
      this.setTitle("Dates");
      setVisible(true);
   }
   
   public static String getValue(Date212Node n) {
      //Prints the dates in the appropriate format
      return n.data.toString(n.data.getYear(), n.data.getMonth(), n.data.getDay());
   }
   
}