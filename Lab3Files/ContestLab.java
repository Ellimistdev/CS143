/*
Programmer: Nick Rodriguez
Description: This program defines a ContestantRecord object and
   creates an ArrayList<ContestantRecord> from the file.
Time spent on assignment 3: 3 hours, 17 minutes
*/
import java.util.*;
import java.io.*;

class ContestLab{

   public static void main(String[] args) throws IOException{
      String fileName = "contest2016.dat";
      ArrayList<ContestantRecord> data = getData(fileName);
      
      System.out.println(data);
   }
   
   private static ArrayList<ContestantRecord> getData(String fileName) throws IOException{
   
      Scanner file = new Scanner(new File(fileName));
      ArrayList<ContestantRecord> data = new ArrayList<ContestantRecord>();
      
      while(file.hasNext()) {
         ContestantRecord record = new ContestantRecord();
         record.setName(file.nextLine());       //or: record.contestant = ...
         record.setDistance(file.nextDouble()); //or: record.distance = ...
         record.setTime(file.nextDouble());     //or: record.time = ...
         data.add(record);
         file.nextLine();
      }
      
      return data;
   }
      
   public static class ContestantRecord implements Comparable<ContestantRecord>{
      private String contestant;
      private double distance;
      private double time;
      
      /* Constructors */
      public ContestantRecord() {
         this("", 0.0, 0.0);
      }  
      
      public ContestantRecord( String contestant, double distance, double time) {
         setName(contestant);
         setDistance(distance);
         setTime(time);
      }
      
      /* Mutators */
      public void setName(String contestant) {
         this.contestant = contestant;
      }
      
      public void setDistance(double distance) {
         this.distance = distance;
      }
      
      public void setTime(double time) {
         this.time = time;
      }
      
      /* Accessors */
      public String toString() {
         return String.format("%s %.2f %.2f\n", this.contestant, this.distance, this.time);
      }
      
      /* Overrides */
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