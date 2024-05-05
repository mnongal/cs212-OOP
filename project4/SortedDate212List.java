
public class SortedDate212List extends Date212List {
   
   private Date212Node current;
   
   public SortedDate212List() {
      //Initialize the linked list that will contain all the sorted dates
      super();
   }
   
   public void add(Date212 d) {
      Date212Node n = new Date212Node(d);
      current = first;
      
      //Checks if the date from the passed parameter is more recent than any of the dates that are already added to the list, keeps checking forward if true
      while (current.next != null && current.next.data.compareTo(d) == true) current = current.next;
      
      //if false, the while loop ends and the new date is added before the date it is earlier than
      n.next = current.next;
      current.next = n;
   }
   
   public int getLength() 
   {
       return length;
   }

   public Date212Node getFirst() 
   {
       return first.next;
   }
   
}
