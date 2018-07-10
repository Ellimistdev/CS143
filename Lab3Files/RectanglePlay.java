/*
   This file demonstrates the following:
      1) "inner" Rectangle class "nested" inside "outer" RectanglePlay class
         Convenient to not need multiple files, one per class.
      2) Usually make all inner class fields public so no need for getters or
         setters (accessors or mutators)
      3) class (static) count field shared by all instances of Rectangle class
         versus
         instance fields where each object has its own copy of the field 
         
   You will use this file to practice concepts pertaining to ArrayList objects,
   Collections and Arrays objects, Comparable and Comparator interfaces
   and implementations.
      
*/
import java.util.*;
class RectanglePlay {

   public static void main(String[] args) {
      System.out.printf("Before any Rectangle objects created,\n" +
                        "Rectangle.count reports %d Rectangle objects exist.\n",
                        Rectangle.count);                         
      Rectangle[] rs = { new Rectangle(5,1), new Rectangle(3, 4), 
                         new Rectangle(6,2), new Rectangle(7,10) };
      System.out.printf("After Rectangle[] rs = {. . .}; \n" +
                        "Rectangle.count reports %d Rectangle objects exist.\n",
                        Rectangle.count);                         
      print("rs array before sort:", rs);
      /*                
         Next statement won't work until Rectangle class 
         implements Compararable interface
         by defining compareTo() method appropriately.  
      */   
      Arrays.sort(rs);  
      print("\nAfter Arrays.sort(rs):", rs);
      
      ArrayList<Rectangle> recList = new ArrayList<Rectangle>();
      for(int i=0; i<rs.length; i++)
         recList.add(rs[i]);  //add Rectangle objects from rs array to ArrayList
      Collections.shuffle(recList); //unsort the sorted ArrayList
      print("\nrecList after shuffle:", recList);
      /*                
         Next statement won't work until a class is created 
         that implements Comparator interface for Rectangle
         objects by defining compare() method appropriately.  
      */   
      Collections.sort(recList,new RectangleComparator());
      print("\nAfter Collections.sort():", recList);
    
   }
   
   //Display message followed by contents of array parameter r
   public static void print(String msg, Rectangle[] r) {
      System.out.println(msg);  
      for(int i=0; i<r.length; i++)
         System.out.printf("Rectangle with %s has area %d\n", 
                           r[i], r[i].getArea());
                           //r[i] is r[i].toString() to fit in %s field
   }

   //Display message followed by contents of ArrayList parameter r
   public static void print(String msg, ArrayList<Rectangle> r) {
      System.out.println(msg);              
      for(int i=0; i<r.size(); i++)
         System.out.printf("Rectangle with %s has area %d\n", 
                           r.get(i), r.get(i).getArea());
                           //r.get(i) is r.get(i).toString() to fit in %s field
   }
   
   public static class RectangleComparator implements Comparator<Rectangle> {
     public int compare(Rectangle r1, Rectangle r2) { 
       if (r1.height != r2.height) { 
         return r1.height - r2.height;
       }
       return r1.width - r2.width;
     }
   }
   
   public static class Rectangle implements Comparable<Rectangle> {
      public static int count = 0;   // class (static) field
      public int width, height;      // instance fields
      
      //Default constructor 
      public Rectangle() {
         this(1,1); //height x width = 1 x 1
      } 
      
      //Alternate constructor
      public Rectangle(int height, int width) {
         this.height = height;
         this.width = width;
         count++; // Count the Rectangle being constructed
      } 
      
      //return text representation of Rectangle object
      public String toString() {
         return "h x w = " + height + " x " + width;
      }
      
      public int compareTo(Rectangle r) {
        if (width != r.width) {
          return width - r.width;
        }
        return height - r.height;
      }
      
      public int getArea() {
         return height * width;
      }
      
   }//end Rectangle class
   
}//RectanglePlay class
