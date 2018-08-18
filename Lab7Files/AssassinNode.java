/*
Programmer: Nick Rodriguez
Description: This class describes a node to be used 
             in a LinkedList for a game of Assassin
*/
class AssassinNode {
   private String player;
   private String killer;

   public AssassinNode(String name) {
      this.player = name;
      this.killer = null;
   }

   public boolean equals(Object other) {
      String name;
      if (other instanceof AssassinNode) {
         AssassinNode node = (AssassinNode) other;
         name = node.player;
      } else {
         name = (String) other;
      }
      return this.player.equalsIgnoreCase(name);
   }

   /* Accessors */
   public String getPlayer() {
      return player;
   }

   public String getKiller() {
      return killer;
   }

   /* Mutators */
   public void setPlayer(String player) {
      if (player == null || player.isEmpty()) {
         throw new IllegalArgumentException("String cannot be null or empty");
      }
      this.player = player;
   }

   public void setKiller(String killer) {
      if (player == null || player.isEmpty()) {
         throw new IllegalArgumentException("String cannot be null or empty");
      }
      this.killer = killer;
   }
}
