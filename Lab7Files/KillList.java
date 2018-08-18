/*
Programmer: Nick Rodriguez
Description: This class extends LinkedList for a game of Assassin
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
         String player = node.next().getPlayer();
         String target;
         if (node.hasNext()){
            target = node.next().getPlayer();
            node.previous();
         } else {
            target = getFirst().getPlayer();
         }
         result += String.format("%s#%s\n", player, target); 
      }
      return result;
   }
}
