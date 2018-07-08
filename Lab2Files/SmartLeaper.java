/*
Programmer: Nick Rodriguez
Description: This class describes a child of LeapingDog that will
   not dig in the same location twice.
*/
public class SmartLeaper extends LeapingDog {
   public SmartLeaper() {
      super();
   }

   public SmartLeaper(int numRow, int numCols, String name) {
      super(numRow, numCols, name);
   }
   
   public int digHoles() {
      return super.digHoles();  
   }   
   
   public int processLocation(int row, int column) {
      if (boneFound(row, column)) {
         this.setElement(row,column, 'H');
         return 0;
      } else if (elementAt(row, column) != '.') {
         this.setElement(row, column, '.');
         return 1;
      }
      return -1;
   }
}