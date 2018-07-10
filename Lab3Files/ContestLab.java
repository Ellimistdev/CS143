import java.util.*;
import java.io.*;

public class ContestLab{

   public ContestLab(String filename) throws IOException {
      Scanner file = new Scanner(new File(filename));
      ArrayList<ContestantRecord> data = getData(file);
            
   }
   
   private ArrayList<ContestantRecord> getData(Scanner file) {
      ArrayList<ContestantRecord> data = new ArrayList<ContestantRecord>();

      return data;
   }
      
   public static class ContestantRecord implements Comparable<ContestantRecord>{
      private String contestant;
      private double distance;
      private double time;
      
      public boolean equals(ContestantRecord c1) {
         if (compareTo(c1) == 0) {
            return true;
         }
         return false;
      }
      
      public int compareTo(ContestantRecord c1) {
         if (distance != c1.distance) { 
            return (int) Math.round(distance - c1.distance);
         }
            return (int) Math.round(time - c1.time);
      }
   }
   /*
   public static class ContestantComparator implements Comparator<ContestantRecord> {
     public int compare(ContestantRecord c1, ContestantRecord c2) { 
       if (c1.distance != c2.distance) { 
         return (int) Math.round(c1.distance - c2.distance);
       }
       return (int) Math.round(c1.time - c2.time);
     }
   }
   */
}