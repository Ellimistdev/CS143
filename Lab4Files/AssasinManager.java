import java.util.*;
import java.io.*;

class AssassinManager{
   private AssassinNode killRing;
   private AssassinNode graveYard;
   private static class AssassinNode {
      private String player;
      private String killer;
      private AssassinNode next;

      AssassinNode(String name) {
         this.player = name;
         this.killer = null;
         this.next = null;
      }
   };

   public AssassinManager(ArrayList<String> playerNamesList) {
      if (playerNamesList.isEmpty()) {
         throw new IllegalArgumentException("Received empty list");
      }

      AssassinNode current = null;
      for (String name : playerNamesList){
         if(killRing == null) {
            killRing = new AssassinNode(name);
            current = killRing;
         }
         else {
            current.next = new AssassinNode(name);
            current = current.next;
         }
      }
   }
}
