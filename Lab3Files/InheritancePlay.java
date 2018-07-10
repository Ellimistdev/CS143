/*
   The purpose of this class is to demonstrate nesting inner classes
   within an outer class. It is much more convenient to
   declare one-time-only inner classes inside the same file as the 
   outer class that uses them, rather than create multiple files
   to hold the inner classes and the outer class.
   
   Note that since the inner classes tend to be for the convenience
   of the outer class, it can be common to make all the inner class
   fields public to avoid the need for setter/getter methods.
   
   This also demonstrates the use of a static (class) field versus an
   instance field in classes.
   
*/
public class InheritancePlay {

   public static void main(String[] args) {
      Pond[] ponds = {new Ocean(), new Pond(), new Lake(), new Bay()};

         for(int i=0; i<ponds.length; i++) {
            displayFields(ponds[i], i);
            ponds[i].method1();
            System.out.println();
            ponds[i].method2();
            System.out.println();
            ponds[i].method3();
            System.out.println("\n");
         }   
   }//end main() method

   public static void displayFields(Pond p, int i) {
      if(p instanceof Ocean) {
         Ocean o = (Ocean)p;
      System.out.println("ponds[" + i + "].name is " +
                         o.name);
      System.out.println("ponds[" + i + "].count is " +
                         o.count);
      } else if (p instanceof Bay) {
         Bay b = (Bay)p;
      System.out.println("ponds[" + i + "].name is " +
                         b.name);
      System.out.println("ponds[" + i + "].count is " +
                         b.count);
      } else if (p instanceof Lake) {
         Lake l = (Lake)p;
      System.out.println("ponds[" + i + "].name is " +
                         l.name);
      System.out.println("ponds[" + i + "].count is " +
                         l.count);
      } else { //p is Pond object; not need for casting
      System.out.println("ponds[" + i + "].name is " +
                         p.name);
      System.out.println("ponds[" + i + "].count is " +
                         p.count);
      }
   }

   public static class Pond {
      public String name = "Pond";  //instance field
      public static int count;      //class (static) field
      public Pond() {
         count++; //increment count whenever Pond (or subclasses) made
      }
      public void method1() {
         System.out.print("p1");
      }
      public void method2() {
         System.out.print("p2");
      }
      public void method3() {
         System.out.print("p3");
      }
   }

   public static class Bay extends Lake {
      public String name = "Bay";   //instance field
      public void method1() {
         System.out.print("b1");
         super.method2();
      }
      public void method2() {
         System.out.print("b2");
      }
   }
   
   public static class Ocean extends Bay {
      public String name = "Ocean";
      public void method2() {
         System.out.print("o2");
      }
   }
   
   public static class Lake extends Pond {
      public String name = "Lake";
      public void method3() {
         System.out.print("l3");
         method2();
      }
   }
   

}