/* 
 * Nongal Maibam
 * CSCI 212 11E
 * Spring 2023
 */

import javax.swing.JOptionPane;
/** swing package has inbuilt classes like JOptionPane which 
 * will provide input dialog box to display information of the user
 */
public class Project0{
	public static String Input() { //create string method named ReadInput
		String Line;
		Line = JOptionPane.showInputDialog( null,"Enter a sentence:");
		/**setting sentence equal to JOptionPane to store user input
		 * (any Message in a sentence) in a dialog box to display.
		 * here showInputDialog is a method which aimed for input and 
		 * null a parameter component that determines the frame in which 
		 * the dialog will display which does not belong other dialog
		 */


		return Line;//return user input. 
	}

	public static void main(String[] args){

		int uppercase_E = 0;
		int	lowercase_e = 0;
		String sentence;// string (data type) name
		

		while(true){
			/**utilize a while loop to check for user input error
		 	while loop will repeat this process.
			 */

			uppercase_E = 0; lowercase_e = 0;
			
			sentence= Input();
			/**setting string data type equal to ReadInput(method), 
			if something is returned from the method it will now be stored in sentence(user input).
			 */

			if (sentence.equalsIgnoreCase("STOP")){
				/**creating a condition and using .equalsIgnoreCase method 
				 * to avoid case consideration/ errors when user types "STOP"
				 */

				System.exit(0);
				//using System.exit(0) to indicate the termination.It does not return any value.
			}


			int length = sentence.length();		// 2 E
			/**created a method length() in order to read each element of the user input
			 * length method will return the number of character written in the user input 
			 */

			//create a for loop to keep count specific element in a string sentence 
			for(int i=0; i< length; i++){
				//using condition where .charAt(int) to return specific element of the user which equals to char'E'
				if(sentence.charAt(i) == 'E'){
					//if an upper case E is found, add 1 to uppercaseE to keep count until for loop ends
					uppercase_E += 1;		// 4 --> 5 --> 6	
				}
				if(sentence.charAt(i) == 'e'){
					//if an lower case e is found, add 1 to lowercase_e to keep count until for loop ends
					lowercase_e += 1;
				}
			}

			JOptionPane.showMessageDialog( null, "There are " + uppercase_E + " upper case E's."+ "\n"+"There are " + lowercase_e + " lowercase e's.");
			//this output will display in the dialog box, number of result of E's and e's that the user entered in a sentence.

		}
	}
}



