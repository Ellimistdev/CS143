/*
Programmer: Nick Rodriguez
Description: This program implements a basic linked list and manages
             a game of assassin.
Time spent on assignment 7: 
*/
import java.util.*;

class AssassinManager {
   private KillList killRing = new KillList();
   private LinkedList<AssassinNode> graveYard = new LinkedList<AssassinNode>();
   private final String STALKING = " is stalking ";
   private final String KILLED_BY = "was killed by";
   
   /* Constructors */
   public AssassinManager(ArrayList<String> playerNamesList) {
      if (playerNamesList == null || playerNamesList.isEmpty()) {
         throw new IllegalArgumentException("Received empty list");
      }

      for (String name : playerNamesList){
         killRing.add(new AssassinNode(name));
      }
   }

   public void printKillRing() {
      ListIterator<AssassinNode> node = killRing.listIterator();
      String out = killRing.toString();
      out = out.replaceAll("#", STALKING);
      out = out.replaceAll("\n", "\n\t ");
      System.out.printf("\t %s", out);
   }

   public void printGraveyard() {
      ListIterator<AssassinNode> node = graveYard.listIterator();
      while (node.hasNext()) {
         AssassinNode dead = node.next();
         System.out.println(String.format("\t %s %s %s", dead.getPlayer(), KILLED_BY, dead.getKiller()));
      }
   }

   public boolean gameOver() {
      return killRing.size() == 1;
   }

   public String winner() {
      if (gameOver()) {
         return killRing.peekFirst().getPlayer();
      }
      return "";
   }

   public boolean graveYardContains(String name) {
      return listContains(name, graveYard);
   }
   
   public boolean killRingContains(String name) {
      return listContains(name, killRing);
   }
   
   private boolean listContains(String name, LinkedList<AssassinNode> list) {
      ListIterator<AssassinNode> node = list.listIterator();
      while (node.hasNext()) {
         if (node.next().getPlayer().equals(name)) {
            return true;
         }
      }
      return false;
   }

   public void kill(String name) {
      if (!killRing.contains(name) || this.gameOver()) {
         throw new IllegalArgumentException(String.format("%s is not in the killRing", name));
      }
      ListIterator<AssassinNode> node = killRing.listIterator();
      boolean targetFound = false;
      AssassinNode target;

      do {
         target = node.next();
         if (target.getPlayer().equals(name)) {
            AssassinNode killer = target.equals(killRing.getFirst()) ?
                                  killRing.getLast() : 
                                  killRing.get(killRing.indexOf(target) - 1);
            target.setKiller(killer.getPlayer());
            targetFound = true;
         }
      } while(!targetFound) ;

      System.out.printf("TargetFound, name input %s removing %s\n", name, target.getPlayer());
      killRing.remove(target);
      graveYard.addFirst(target);
   }
}