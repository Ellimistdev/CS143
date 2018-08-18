/*
Programmer: Nick Rodriguez
Description: This class describes a node to be used 
             in a LinkedList for a game of Assassin
*/
import java.util.*;

class KillList extends LinkedList<AssassinNode> {

   public KillList() {
      super();
   }
   
   public int indexOf(Object o) {
      if (o instanceof AssassinNode) {
         return super.indexOf(o);
      }
      if (o instanceof String) {
         return super.indexOf(new AssassinNode((String) o));
      }  
      return -1;
   }
   
   public String toString() {
      String result = "";
      ListIterator<AssassinNode> node = super.listIterator();
      while(node.hasNext()) {
         result += String.format("%s#%s\n",node.next().getPlayer(), node.next().getPlayer());
      }
      return result;
   }
}
