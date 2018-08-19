/*
Programmer: Nick Rodriguez
Description: This program implements a basic linked list and manages
             a game of assassin.
Time spent on assignment 7: 4 Hours, 36 minutes
Note: The Kill() method operates in O(n) time, as aside from the initial 
      indexOf() call (which runs at worst in n time), all the operations
      run in constant time.
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
   
   /* Accessors */
   public void printKillRing() {
      ListIterator<AssassinNode> node = killRing.listIterator();
      String out = killRing.toString();
      out = out.replaceAll("#", STALKING);
      out = out.replaceAll("\n", "\n\t ");
      System.out.printf("\t %s", out);
   }

   public void printGraveYard() {
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
         if (node.next().getPlayer().equalsIgnoreCase(name)) {
            return true;
         }
      }
      return false;
   }

   /* Mutators */
   public void kill(String name) {
      int target = killRing.indexOf(name);
      if (target < 0 || this.gameOver()) {
         throw new IllegalArgumentException(String.format("%s is not in the killRing", name));
      }
      AssassinNode victim = killRing.get(target);
      String killer = (target == 0) ? killRing.getLast().getPlayer() : killRing.get(target - 1).getPlayer();
      victim.setKiller(killer);
      killRing.remove(target);
      graveYard.addFirst(victim);
   }
}