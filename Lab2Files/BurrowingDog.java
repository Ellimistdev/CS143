/*
Programmer: Nick Rodriguez
Description: This class describes a child of YardDog that digs in 
   a continuous fashion and has the additional burrow() method.
*/
import java.util.*;
import java.io.*;

public class BurrowingDog extends YardDog {

   public BurrowingDog() {
      super();
   }

   public BurrowingDog(int numRow, int numCols, String name) {
      super(numRow, numCols, name);
   }

   // Digs holes in a continuous fashion until a bone is found
   public int digHoles() {
      Random r = new Random();
      int row = getNumRows() / 2;
      int column = getNumColumns() / 2;
      int holes = 1;
      int rowMax = getNumRows();
      int colMax = getNumColumns();
      int direction;
      int[] pendingPos = new int[2];

      while (!boneFound(row, column)) {
         this.setElement(row, column, '.');
         do {
            direction = r.nextInt(4);
            pendingPos = getNewPos(row, column, direction);
         } while(!isWithinFence(pendingPos[0], pendingPos[1], rowMax, colMax));
         row = pendingPos[0];
         column = pendingPos[1];
         holes++;
      }

      this.setElement(row,column, 'H');
      return holes;  
   }
   
   // Given a position and a direction, return a new position 1 unit in the given direction
   public int[] getNewPos(int row, int column, int direction) {
      int[] position = {row, column};
      
      if (direction == 0) {        // North
         position[0] -= 1;
      } else if (direction == 1) { // South
         position[0] += 1;
      } else if (direction == 2) { // West
         position[1] -= 1;
      } else {                     // East
         position[1] += 1;
      }      

      return position;
   }

   public void burrow() {
      for (int i = 1; i < this.getNumRows() - 1; i++) {
         for (int j = 1; j < this.getNumColumns() - 1; j++) {
            if (elementAt(i, j) == '.') {
               setElement(i, j, '@');
            }
         }
      }
   }
   
   public void save(String filename) throws IOException {
      PrintStream out = new PrintStream(new File(filename));
      super.save(out);
      out.printf("TBD");
   }
   
   public void retrieve(String filename) throws IOException {
      Scanner input = new Scanner(new File(filename));
      super.retrieve(input);      
   }
   
   
   /* Helpers */
   // Tests if a targeted location is inside the borders of the array
   // duplicated from YardDog as we have not been introduced to protected methods.
   private boolean isWithinFence(int row, int col, int rowMax, int colMax) {
      if ((isOutOfRange(row, 1, rowMax - 2)) || 
            (isOutOfRange(col, 1, colMax - 2))) {
         return false;
      }
      return true;
   }

   // Tests whether an int is between two others
   // duplicated from YardDog as we have not been introduced to protected methods.
   private boolean isOutOfRange(int test, int min, int max) {
      if (test < min || test > max) {
         return true;
      }
      return false;
   }
   
}