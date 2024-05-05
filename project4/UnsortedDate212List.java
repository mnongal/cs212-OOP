
public class UnsortedDate212List extends Date212List {
   
   public UnsortedDate212List() {
      //Initialize the linked list that will contain all the unsorted dates
      super();
   }
   
   public void add(Date212 d) {
      //Append the linked list without any sorting requirements
      super.append(d);
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
