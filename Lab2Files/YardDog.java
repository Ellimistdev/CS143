/*
Programmer: Nick Rodriguez
Description: This class provides the base for a dog burying and 
   digging up bones in a yard via a 2d array
*/
import java.util.*;
import java.io.*;

public abstract class YardDog implements Saveable{

   public static final int DEFAULT_ROWS = 15;
   public static final int DEFAULT_COLS = 20;
   public static final int MIN_ROWS = 5;
   public static final int MIN_COLS = 5;
   public static final int MAX_COLS = 75;
   public static final int NUM_BONES = 3;
   private char[][] yard;
   private String name;

   /* Constructors */
   public YardDog() {
      this(DEFAULT_ROWS,DEFAULT_COLS,"Dog");
   }

   public YardDog(int numRows, int numCols, String name) {
      if (numRows < MIN_ROWS || isOutOfRange(numCols, MIN_COLS, MAX_COLS)){
         throw new IllegalArgumentException("Argument out of range");
      }

      yard = new char[numRows][numCols];
      buildYard();
      this.name = name;
   }

   /* Mutators */
   public void buildYard() {
      buildFence();
      buryBones();
   }

   // Fills the array border
   private void buildFence() {
      char fill;
      for (int i = 0; i < this.getNumRows(); i++) {
         for (int j = 0; j < this.getNumColumns(); j++) {
            fill = getChar(i, j);
            setElement(i, j, fill);
         }
      }
   }

   // Places three "bones" somewhere in the array
   private void buryBones() {
      Random r = new Random();
      int row;
      int col;

      for (char bone = 'A'; bone < 'D'; bone++){
         do {
            row = r.nextInt(this.getNumRows() - 1) + 1;
            col = r.nextInt(this.getNumColumns() - 1) + 1;
         } while (yard[row][col] != ' ');

         yard[row][col] = bone;
      }
   }

   // Assigns the passed char to yard[row][col]
   public void setElement(int row, int col, char ch) {
      if(isOutOfBounds(row, col)) {
         throw new IllegalArgumentException("Argument out of range");
      }
      yard[row][col] = ch;
   }

   // Tests the targeted location and immediate neighbors for a "bone"
   public boolean boneFound(int row, int col) {
      if (isWithinFence(row, col)) {
         return (isBone(this.elementAt(row - 1, col)) || 
            isBone(this.elementAt(row + 1,col)) || 
            isBone(this.elementAt(row, col)) || 
            isBone(this.elementAt(row, col - 1)) || 
            isBone(this.elementAt(row, col + 1)));
      }
      return false;
   }

   public abstract int digHoles();

   public void save(PrintStream output) throws IOException {
      output.printf("%d %d\n%s", yard.length, yard[0].length, this.toString());
   }
   
   public void retrieve(Scanner input) {      
      int rows = input.nextInt();
      int columns = input.nextInt(); 
      this.yard = new char[rows][columns];
      String line;      
      
      input.nextLine();      
      for (int i = 0; i < rows; i++) {
         line = input.nextLine();         
         for (int j = 0; j < columns; j++) {
            yard[i][j] = line.charAt(j);
         }
      }
      this.name = input.next();
   }

   /* Accessors */
   public void print() {
      this.print(this.toString());
   }

   public void print(String str) {
      System.out.printf(str);
   }

   public String getName() {
      return name;
   }

   public int getNumRows() {
      return yard.length;
   }

   public int getNumColumns() {
      return yard[0].length;
   }

   public char elementAt(int row, int column) {
      if(isOutOfBounds(row, column)) {
         throw new ArrayIndexOutOfBoundsException("Argument out of range");
      }
      return yard[row][column];
   }

   // Converts this.yard to a string for printing
   public String toString() {
      String dog = "";
      for (int i = 0; i < this.getNumRows(); i++) {
         for (int j = 0; j < this.getNumColumns(); j++) {
            dog += yard[i][j];
         }
         dog += "\n";
      }
      dog += name + "\n";
      return dog;
   }

   /* Helpers */
   private boolean isBone(char c) {
      if (c == 'A' || c == 'B' || c == 'C'){
         return true;
      }
      return false;
   }

   // Returns an appropriate char based on the targeted location of the array.
   private char getChar(int row, int col){
      if (row == 0 || row == this.getNumRows() - 1) {
         if (col == 0 || col == this.getNumColumns() - 1) {
            return '+';
         }
         return '-';
      }
      if (col == 0 || col == this.getNumColumns() - 1) {
         return '|';
      }
      return ' ';
   }

   // Tests if a targeted location is outside the array.
   private boolean isOutOfBounds(int row, int col) {
      if ((isOutOfRange(row, 0, this.getNumRows() - 1)) || 
            (isOutOfRange(col, 0, this.getNumColumns() - 1))){
         return true;
      }
      return false;
   }

   // Tests if a targeted location is inside the borders of the array
   private boolean isWithinFence(int row, int col) {
      if ((isOutOfRange(row, 1, this.getNumRows() - 2)) || 
            (isOutOfRange(col, 1, this.getNumColumns() - 2))) {
         return false;
      }
      return true;
   }

   // Tests whether an int is between two others
   private boolean isOutOfRange(int test, int min, int max) {
      if (test < min || test > max) {
         return true;
      }
      return false;
   }
}