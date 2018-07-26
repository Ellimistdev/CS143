/*
Programmer: Nick Rodriguez
Description: This program implements a basic linked list and manages
   a game of assassin.
Time spent on assignment 3: 6 hours, 53 minutes
*/
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

      // adds the passed node to the front of the list upon which the method was invoked.
      public AssassinNode addFirst(AssassinNode node) {
         node.next = this;
         return node;
      }

      public void setKiller(String player) {
         AssassinNode prev = getPrevNode(player);
         AssassinNode node = (prev.next == null) ? this : prev.next;

         node.killer = prev.player;
      }

      private AssassinNode getPrevNode(String player) {
         if (!this.contains(player)) {
            throw new IllegalArgumentException("Attempted to find non-existant node");
         }
         AssassinNode node = this;
         AssassinNode prev = null;

         while(node.equals(player) != 0){
            prev = node;
            node = node.next;
         }
         
         if (prev == null) {
            prev = node;
            do{
               prev = prev.next;
            }while(prev.next != null);
         }
         return prev;
      }

      // removes and returns the targeted node from the list
      // TODO: This should also remove nodes if they are at index 0
      // currently it returns the whole list in that case
      public AssassinNode extractNode(String player) {
         AssassinNode prev = getPrevNode(player);
         AssassinNode node = (prev.next == null) ? this : prev.next;

         // if node == this, setting node.next mutates this.next unintentionally
         if (!(node == this)) {
            prev.next = node.next;
            node.next = null;
         }
         return node;
      }

      public boolean contains(String player) {
         if (this == null) {
            return false;
         }
         AssassinNode node = this;
         while (node != null) {
            if (node.equals(player) == 0) {
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
      killRing.setKiller(name);
      AssassinNode dead = killRing.extractNode(name);

      // TODO: This should not be necessary
      // removes head node from killRing if target was the first node
      if (dead.next != null) {
         killRing = killRing.next; 
         dead.next = null;
      }

      inter(dead);
   }

   private void inter(AssassinNode dead) {
      graveYard = (graveYard == null) ? dead : graveYard.addFirst(dead);
   }
}
