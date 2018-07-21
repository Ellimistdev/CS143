/*
Programmer: Nick Rodriguez
Description: This program defines a ContestantRecord object and
   creates an ArrayList<ContestantRecord> from the file.
Time spent on assignment 3: 3 hours, 17 minutes
*/
import java.util.*;
import java.io.*;

class ContestLab{
   public static final int REPORT_WIDTH = 50;
   public static final int CONTESTANT_WIDTH = 29;
   public static final String REPORT_HEADING = String.format("\t%s\t\t\t\t\t\t\t  %s\t\t %s", "Contestants", "Ft", "Secs");

   public static void main(String[] args) throws IOException{
      Scanner console = new Scanner(System.in);
      String fileName = "contest2016.dat";
      ContestantRecord targetRec;
      ArrayList<ContestantRecord> data = getData(fileName);
      
      System.out.println(data);
      displayReport("Original Data", data);
      Collections.shuffle(data);
      displayReport("Shuffled Data", data);
      Collections.sort(data);
      displayReport("Contest Results Sorted by Name", data);
      Collections.sort(data, new RaceComparator());
      displayReport("Race Results", data);
      Collections.sort(data, new TossComparator());
      displayReport("Results by Toss Distance", data);
      
      do {
         System.out.print("Enter target name to search for (or QUIT): ");
         targetRec = new ContestantRecord();
         targetRec.contestant = console.nextLine();
         if (data.indexOf(targetRec) > 0){
            targetRec = data.get(data.indexOf(targetRec));
            System.out.printf("indexOf() shows %s had toss of %.1f ft and race time of %.1f secs\n", targetRec.contestant, targetRec.distance, targetRec.time);
         } else {
            System.out.printf("indexOf( says %s NOT in list.\n", targetRec.contestant);
         }            
      }while(!targetRec.contestant.equalsIgnoreCase("QUIT"));
   }
   
   private static void displayReport(String title, ArrayList<ContestantRecord> data) {
      printChar(System.out, ' ', (REPORT_WIDTH / 2) - (title.length() / 2));
      System.out.printf("%s\n", title);
      printChar(System.out, '=', REPORT_WIDTH);     
      System.out.printf("\n%s\n", REPORT_HEADING);
      printChar(System.out, '=', REPORT_WIDTH);  
      for (ContestantRecord record : data) {
         System.out.printf("\n\t%s", record.contestant);
         printChar(System.out, ' ', CONTESTANT_WIDTH - record.contestant.length() + 1);
         System.out.printf("%.2f\t\t %.2f", record.distance, record.time);         
      }
      System.out.println();
      
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
   
   // prints the passed char to the console 'count' times.
   private static void printChar(PrintStream output, char c, int count){
      for (int i = 1; i <= count; i++) {
         output.print(c);
      }
   }

   /* Internal Classes */         
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
      public boolean equals(Object obj) {
         if (obj instanceof ContestantRecord) {
            return (compareTo((ContestantRecord) obj) == 0);
         }
         return false;
      }
      
      public int compareTo(ContestantRecord c1) {
         return contestant.toLowerCase().compareTo(c1.contestant.toLowerCase());
      }
   }
   
   public static class TossComparator implements Comparator<ContestantRecord> {
      public int compare(ContestantRecord c1, ContestantRecord c2) {
         Double d1 = new Double(c1.distance);
         Double d2 = new Double(c2.distance);
         return d1.compareTo(d2) * -1;
      }
   }

   public static class RaceComparator implements Comparator<ContestantRecord> {
      public int compare(ContestantRecord c1, ContestantRecord c2) {
         Double t1 = new Double(c1.time);
         Double t2 = new Double(c2.time);
         return t1.compareTo(t2);
      }
   }

   
}