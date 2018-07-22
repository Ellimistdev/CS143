import java.util.*;
import java.io.*;

class AssassinManager{
   private AssassinNode killRing;
   private AssassinNode graveYard;
   private static class AssassinNode {
      private String player;
      private String killer;
      private AssassinNode next;

      public AssassinNode(String name) {
         this.player = name;
         this.killer = null;
         this.next = null;
      }
      
      // finds and returns a node from the linked list
      // if the target node is not the first entry, removes the node
      public AssassinNode extract(String name) {
         AssassinNode node = this;
         AssassinNode prev = null;
         AssassinNode dead;         
         boolean firstNode = false;

         while(node.equals(name) != 0){
            prev = node;
            node = node.next;
         }
         dead = new AssassinNode(node.player);
         // if prev has not been set, the first node has been selected
         // we must set prev to the final node in the list
         if (prev == null) {  
            firstNode = true;       
            prev = node;
            do{
               prev = prev.next;
            }while(prev.next != null);
         }
            
         dead.killer = prev.player;
         if (!firstNode){
            prev.next = node.next;
         }
         return dead;
      }

      public boolean contains(String name) {
         if (this == null) {
            return false;
         }
         AssassinNode node = this;
         while (node != null) {
            if (node.equals(name) == 0) {
               return true;
            }
            node = node.next;
         }
         return false;
      }

      public int equals(String name) {
         return player.toLowerCase().compareTo(name.toLowerCase());
      }
   };

   /* Constructors */
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

   /* Accessors */   
   public void printKillRing() {
      AssassinNode node = killRing;
      String target;
      String fill = "is stalking";
      if (node != null) {
         while (node.next != null) {
            target = getString(node.player, fill, node.next.player);
            System.out.println(target);
            node = node.next;
         }
         target = getString(node.player, fill, killRing.player);
         System.out.println(target);
      }
   }
   
   private static String getString(String s1, String s2, String s3) {
      return String.format("\t %s %s %s", s1, s2, s3);
   }

   public void printGraveyard() {
      AssassinNode node = graveYard;
      String fill = "was killed by";
      while (node != null) {
            System.out.println(getString(node.player, fill, node.killer));
            node = node.next;
      }
   }

   public boolean killRingContains(String name) {
      try {
         return killRing.contains(name);
      }
      catch (Exception e){ // ignore NullPointerException
         return false;
       }
   }

   public boolean graveYardContains(String name) {
      try {
         return graveYard.contains(name);
      }
      catch (Exception e){ // ignore NullPointerException
         return false;
      }
   }

   public boolean gameOver() {
      return killRing.next == null;
   }

   public String winner() {
      return gameOver() ? killRing.player : "";
   }

   public void kill(String name) {
      if (!killRing.contains(name) || this.gameOver()) {
         throw new IllegalArgumentException(String.format("%s is not in the killRing", name));
      }
      AssassinNode dead = killRing.extract(name);
      if(killRing.equals(name) == 0) {
         killRing = killRing.next;
      }
      inter(dead);    
   }
   
   
   private void inter(AssassinNode dead) {
      if(graveYard == null) {
         graveYard = dead;
      }
      else {
         AssassinNode target = graveYard;
         while(target.next != null) {
            target = target.next;
         }
         target.next = dead;         
      }   
   }
}
