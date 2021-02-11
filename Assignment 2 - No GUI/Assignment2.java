



//Robin Pelayo
//CS - 102, Summer 2018
//Assignment 2

import java.lang.ArrayIndexOutOfBoundsException;
import java.util.InputMismatchException;
import java.util.Scanner;

import TennisDatabase.TennisDatabaseRuntimeException;
import TennisDatabase.TennisDatabaseException;
import TennisDatabase.TennisDatabase;
import TennisDatabase.TennisPlayer;
import TennisDatabase.TennisMatch;


//Class to hold the menu method and main method.
public class Assignment2 {  
   
   // main method uses do while loop and switch statement to control menu options.
   public static void main(String [] args) {  
          
      //Local variables
      int choice;                                  //Holds the user's menu choice
      TennisDatabase tdb = new TennisDatabase();   //Create a new tennis database
      Scanner input = new Scanner(System.in);      //New scanner for user and file input
      
      //Introduction
      System.out.println("CS-102 Tennis Manager");

      //Load file data
      try {
         String fileName = args[0];
         tdb.importFromFile( fileName );
      }
      catch ( ArrayIndexOutOfBoundsException e ) { System.out.println( "No input file name..." ); }
      System.out.println("File load success!");
      
      
      //TESTIng
      tdb.exportToFile( "export.txt" );
      
      //Do while loop to control menu
      do{
         //print the menu
         printMenu();
         choice = input.nextInt();
         input.nextLine();
         System.out.println();
         
         //Switch statment to control the menu options
         switch(choice){
           case 1:
              //Print all tennis players
              try{
                 tdb.printAllPlayers();   //Throws exception if there are no players in the database  
                 System.out.println();   
              }
              catch (TennisDatabaseRuntimeException e ) { System.out.println("Error: No players in the database.\nPlease insert a tennis player and try again.\n"); }
              break;
           case 2:
              //Print all tennis matches
              try{ 
                 tdb.printAllMatches();  
                 System.out.println();
              }
              catch(TennisDatabaseRuntimeException e ) { System.out.println("Error: No matches in the database.\nPlease insert a tennis match and try again.\n"); }
              break;
           case 3:
              //Print all matches of a specific tennis player
              //Get the tennis player id from the user
              System.out.println("Enter tennis player id:");
              String id = input.nextLine();
              
              //Try printing the player's matches.
              try{
                 System.out.println();
                 tdb.printMatchesOfPlayer( id.toUpperCase() );
                 System.out.println();
              }
              catch (TennisDatabaseException e ) { System.out.println("Error: Cannot find player id in database."); }
              catch (TennisDatabaseRuntimeException e ) { System.out.println("Error: Player has not played any matches."); }
              break;
           case 4:
              //Insert a new tennis player 
              //Local variables to hold the entered player data
              String Id = "";
              String first = "";
              String last = "";
              int year = 0;
              String country = "";
              
              //Ask the user for data
              System.out.println("Enter the player's ID: ");
              Id = input.nextLine();
              System.out.println("Enter the player's first name: ");
              first = input.nextLine();
              System.out.println("Enter the player's last name: ");
              last = input.nextLine(); 
              System.out.println("Enter the player's birth year: ");
              
              //Get the player's birth year as an integer, catch exception if year is entered as a string.
              try{
                 year = input.nextInt();  //nextInt does not advance the scanner
                 input.nextLine();        //nextLine used to advance the scanner to the next line
              }
              catch(InputMismatchException e ) { 
                  System.out.println("Error: birth year must be entered as a number."); 
                  continue;
              }
              
              System.out.println("Enter the player's birth country: "); 
              country = input.nextLine();
              System.out.println();
              
              //Pass the data entered to the tdb
              try{
                  tdb.insertPlayer(Id.toUpperCase(), first.toUpperCase(), last.toUpperCase(), year, country.toUpperCase());
              }
              catch ( TennisDatabaseException e ) { System.out.println("\nInsertion aborted: Player already exists in database.\n"); }
              break;
           case 5:
              //Insert a new tennis match
              //Local variables to hold the entered match data
              String player1Id = "";
              String player2Id = "";
              int date = 0;
              String tournament = "";
              String score = "";
              
              //Get the match data from user
              System.out.println("Enter the first player's ID: ");
              player1Id = input.nextLine();
              System.out.println("Enter the second player's ID: ");
              player2Id = input.nextLine();
              System.out.println("Enter the match date (YYYYMMDD): ");
              
              //Get the match date as an integer, catch exception if date is entered as a string.
              try{
                 date = input.nextInt();
                 input.nextLine();
              }
              catch(InputMismatchException e ) { System.out.println("Error: match date must be entered as a number."); }
              
              System.out.println("Enter the tournament name: ");
              tournament = input.nextLine();
              System.out.println("Enter the match score: ");
              score = input.nextLine();
               
              //Split up the date
              int dateYear = date/10000;        //20151012/10000 = 2015
              int dateMonth = (date%10000)/100; //20151012%10000 = 1012/100 = 10
              int dateDay = date%100;           //20151012%100 = 12
              
              //Pass the user's data to the tdb
              try{
                  tdb.insertMatch( player1Id.toUpperCase(), player2Id.toUpperCase(), dateYear, dateMonth, dateDay, tournament.toUpperCase(), score);
              }
              catch ( TennisDatabaseException e ) { System.out.println("\nIntertion Failed: One or more player's do not exist in database.\n"); }
              break;
           case 6:
              //Delete a tennis player 
              //Get tennis player's id 
              //Remove
              String removalId;  //To hold the id of the player to be removed
              String proceed;    //To hold the user's choice to continue the deletion
              System.out.println("Enter player's id:");
              removalId = input.nextLine();
              System.out.println("Remove player?(y/n):");
              proceed = input.nextLine();
              try{
                 if ( proceed.toUpperCase().equals("Y") ){
                      //Remove player
                      tdb.deletePlayer( removalId );
                      System.out.println("Player removed.");
                      System.out.println();
                 }
              }
              catch ( TennisDatabaseRuntimeException e ) { System.out.println("Player not found in database."); }
              break;
           case 7:
              //Reset the database
              try{
                 String go;
                 System.out.println("Reset database?(y/n):");
                 go = input.nextLine();
                 if ( go.toUpperCase().equals("Y") ){
                   //Reset database
                   tdb.reset();
                   System.out.println("\nDatabase reset.\n");
                 }
              }
              catch( TennisDatabaseRuntimeException e ) { System.out.println("Error: Database empty.\n"); }
              break;
           case 8:
              //Exit
              System.exit(0);
              break;
           default:
              System.out.println("Please enter a valid menu option.\n");
              break;
         }
      }while(choice != 8); 
   }  
   
   //Method to print main menu
   public static void printMenu(){
      System.out.print("Current avalable commands:\n");
      System.out.println("1 ---> Print all tennis players");
      System.out.println("2 ---> Print all tennis matches");
      System.out.println("3 ---> Print matches of a player");
      System.out.println("4 ---> Insert a new tennis player");
      System.out.println("5 ---> Insert a new tennis match");
      System.out.println("6 ---> Delete a tennis player");
      System.out.println("7 ---> Reset database");
      System.out.println("8 ---> Exit");   
      System.out.println("Your choice?:");
   }
}