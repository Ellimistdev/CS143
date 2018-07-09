/*
Programmer: Nick Rodriguez
Description: This class describes a child of YardDog that digs in 
   a random fashion and has the additional leap() method.
*/
import java.util.*;
import java.io.*;

public class LeapingDog extends YardDog {

   public LeapingDog() {
      super();
   }

   public LeapingDog(int numRow, int numCols, String name) {
      super(numRow, numCols, name);
   }

   // dig holes at randomly generated locations
   public int digHoles() {
      int holes = 1;
      Random r = new Random();
      int row;
      int column;
      int status;
      boolean digging = true;

      do {
         row = r.nextInt(getNumRows() - 2) + 1;
         column = r.nextInt(getNumColumns() - 2) + 1;
         status = processLocation(row, column);
         if (status == 0) {
            digging = false;
         } else if (status == 1) {
            holes++;
         }       
      } while (digging);

      return holes;
   }

   public int processLocation(int row, int column) {
      if (boneFound(row, column)) {
         this.setElement(row,column, 'H');
         return 0;
      }
      this.setElement(row, column, '.');
      return 1;       
   }

   public void leap() {
      int[] target = findBorder();
      this.setElement(target[0], target[1], '@');
   }
   
   // randomly generates a position on the border of a 2d array
   private int[] findBorder() {
      Random r = new Random();
      int fence = r.nextInt(4);
      int row;
      int col;

      if (fence < 2) { 
         if (fence == 0) { // north
            row = fence;
         } else {          // south
            row = getNumRows() - 1;
         }
         // 0 to yard[0].length - 1
         // includes corners
         col = r.nextInt(getNumColumns());
      } else { 
         if (fence == 2) { // west
            col = 0;
         } else {          // east
            col = getNumColumns() - 1;
         }
         // 1 to yard.length - 2 
         // excludes corners
         row = r.nextInt(getNumRows() - 1) + 1;
      }

      int[] location = { row, col };
      return location;
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
   // Tests whether an int is between two others
   // duplicated from YardDog as we have not been introduced to protected methods.
   private boolean isOutOfRange(int test, int min, int max) {
      if (test < min || test > max) {
         return true;
      }
      return false;
   }
}