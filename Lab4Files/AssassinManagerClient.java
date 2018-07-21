/**
   Programmer: Hilda Halliday April 2018
   This client of the AssassinManager class manages
   a game of Assassin.
*/
import java.util.*;
import java.io.*;

public class AssassinManagerClient {

   public static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args) {
      //Create the AssassinManager object from an ArrayList filled
      //with names from a file specified by the user.  
      AssassinManager mgr = null;
      boolean mgrSuccessfullyCreated = false;
      do {
         try {
            ArrayList<String> playersList = new ArrayList<String>();
            fillArrayListFromFile(playersList);
            mgr = new AssassinManager(playersList);
            mgrSuccessfullyCreated = true;  
         }
         catch(Exception e) {
            System.out.println(e);
            System.out.println("Error in file. Try another file.");   
         }
      } while(!mgrSuccessfullyCreated);
      
      //Play a game of Assassin using the mgr AssassinManager object.
      while ( !mgr.gameOver() )  {
         System.out.println("\nCurrent kill ring:");
         mgr.printKillRing();
         System.out.println("Current gravyard:");
         mgr.printGraveyard();
         System.out.print("\nNext victim? ");
         String victim = keyboard.nextLine();
         try {
            mgr.kill(victim);
         }catch(Exception e) {
            System.out.println("Victim " + victim + " is not a current player in the kill ring.");
         }
      }//end while loop
      System.out.println("\nGame was won by " + mgr.winner());
      System.out.println("Final gravyard is as follows:");
      mgr.printGraveyard();
   }//end main() method
   
   //method: fillArrayList
   //purpose: fill List parameter with names from file specified by user
   //precondition: players List parameter is not null.
   //postcondition: players List has been filled with names from file
   public static void fillArrayListFromFile(List<String> players) {
      boolean fileProcessedSuccessfully = false;    
      do {
         try {//try to open and read from file specified by user
            System.out.print("Name of game players file? ");
            String filename = keyboard.nextLine();
            Scanner playersFile = new Scanner(new File(filename));
            while( playersFile.hasNext() ) {
               players.add(playersFile.nextLine());
            }
            fileProcessedSuccessfully = true;
         }catch(Exception e) {
            System.out.println("Invalid file. Please retry.");
         }
      } while ( !fileProcessedSuccessfully );
   }//end fillArrayList() method
   
}//end AssassinManagerClient class