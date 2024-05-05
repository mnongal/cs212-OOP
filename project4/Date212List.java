
public abstract class Date212List {
   
   protected Date212Node first;
   protected Date212Node last;
   protected int length;
   
   public Date212List() {
      //A null first and last node are set during the initialization of a new list
      first = new Date212Node(null);
      last = first;
      length = 0;
   }

   public void append(Date212 d) {
       Date212Node AppendedNode = new Date212Node(d);
       
       //If the list is empty then the node holding the appended Date212 object is set as the next node from the head node, else it is set as the node pointed to by the last added node
       if (length == 0) first.next = AppendedNode;
       else last.next = AppendedNode;
       
       //The node holding the appended Date212 object is set as the new last node of the list and the length of the list accounts for this addition
       last = AppendedNode;
       length++;
   }
   
}
