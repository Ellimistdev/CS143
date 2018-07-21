/*
    This code uses a sentinel-controlled loop to let the user 
    create a linked list of nodes containing string values
    and node IDs. The list is then displayed to the user.
*/
import java.util.*;
//Use to demo jGRASP debugger for linked lists
public class InnerListNodeProcessing {

   public static void main(String[] args) {
      ListNode list = null;    //will point to front of list
      ListNode current = null; //will point to most recent node added to end of list
      Scanner kbd = new Scanner(System.in);
      System.out.print("Enter string values; QUIT to quit: ");
      String data = kbd.next(); //priming input
      while(!data.equals("QUIT")) {
         if(list == null) {     //first data item to be added to list
            list = new ListNode(data);
            current = list;
         }
         else {
            current.next = new ListNode(data);
            current = current.next;
         }
         data = kbd.next(); //updating input
      }
      //list->1->2->3 becomes list->3->2->1
      ListNode tmp = list.next.next;
      ListNode tmp2 = list.next;
      ListNode tmp3 = list;
      list = tmp;
      list.next = tmp2;
      list.next.next = tmp3;
      list.next.next.next = null;
        
      //Display list
      System.out.println("Here is your linked list:");
      current = list;
      while(current != null) {
         System.out.print(current + "->");
         current = current.next;
      }
      System.out.println("X");
   }
   //Inner ListNode class
   private static class ListNode {
      private static int nodeID = 0;
      private int id;       // data stored in this node
      private String data;
      private ListNode next;  // link to next node in the list
   
       // post: constructs a node with given data and null link
      private ListNode(String data) {
         this.id = ++nodeID;
         this.data = data;
      }
       
      public String toString() {
         return id + " " + data;
      }
   }
}