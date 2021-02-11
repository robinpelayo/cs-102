



//Robin Pelayo
//CS - 102
//Assignment 2
//Description: TennisDatabase class to hold methods that manipulate the database.

package TennisDatabase;

import java.util.regex.PatternSyntaxException;
import java.util.Iterator;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.NumberFormatException;

//TennisDatabase class used to control menu options and update containers
//TODO: Make package private when finished testing
public class TennisDatabase implements TennisDatabaseInterface{

   //Fields
   private TennisPlayersContainer tpc; // Reference to container for tennis players
   private TennisMatchesContainer tmc; // Reference to container for tennis matches
  
   // Constructor.
   public TennisDatabase() {
      tpc = new TennisPlayersContainer(); // Create new tennisplayerscontainer
      tmc = new TennisMatchesContainer(); // Create new tennismatchescontainer
   }
   
   // Desc.: Loads data from file following the format described in the specifications.
   // Output: Throws an unchecked (non-critical) exception if the loading is not fully successfull.
   public void importFromFile( String fileName ) throws TennisDatabaseRuntimeException {
      // Local variables
      String line = null; // This will reference one line at a time.
      String delimiter = "/";
      String[] tokens;
      try{
         FileReader fileReader = new FileReader(fileName); // FileReader reads text files in the default encoding.
         BufferedReader bufferedReader = new BufferedReader(fileReader); // Always wrap FileReader in BufferedReader.
         // Loop to read the file line by line, terminates when the FileReader reaches the end of the file aka when line is null
         while( ( line = bufferedReader.readLine() ) != null ) {
            tokens = line.split( delimiter ); 
            // Check if the first element of tokens is MATCH of PLAYER, get the data respectivley, try inseerting the player
            try { 
               if( tokens[0].equals( "PLAYER" ) ) {
                  //If the first word in the line is player get the necessary info for a tennis player
                  String playerId = tokens[1];
                  String playerFirstName = tokens[2];
                  String playerLastName = tokens[3];
                  int playerBirthYear = Integer.parseInt( tokens[4] );
                  String playerCountry = tokens[5];
                  //Insert the player into the db.
                  insertPlayer( playerId, playerFirstName, playerLastName, playerBirthYear, playerCountry );
               }
               else if( tokens[0].equals( "MATCH" ) ) {
                  //If the first word in the line is match get the necessary info for a tennis match
                  String player1id = tokens[1];
                  String player2id = tokens[2];
                  int date = Integer.parseInt( tokens[3] );
                  //Split the date into year, month, day
                  int year = date/10000;
                  int month = (date%10000)/100;
                  int day = date%100;
                  //Finish getting the match data
                  String tournament = tokens[4];
                  String score = tokens[5];
                                  
                  //Insert the match into the database
                  insertMatch( player1id, player2id, year, month, day, tournament, score );
               }
               else { 
                     throw new TennisDatabaseRuntimeException ( "Error in TDB loadFromFile: invalid line read from file." ); 
               }
            }
            catch( NumberFormatException e ) { System.out.println( "NumberFormatException thrown in TennisDB loadFromFile" ); }
            catch( TennisDatabaseException e ) { System.out.println( "TennisDatabseExcetion thrown in TennisDB loadFromFile" ); }            
         }
         bufferedReader.close(); // Always close files.
      }
      catch( FileNotFoundException e ) { System.out.println( "FileNotFoundException thrown in TDB loadFromFile" ); }
      catch( IOException e ) { System.out.println( "IOException thrown in TDB loadFromFile" ); }
      catch( PatternSyntaxException e )  { System.out.println( "PatternSyntaxException thrown in TDB loadFromFile" ); }
   }
   
   // Desc.: Loads data from file following the format described in the specifications.
   // Output: Throws an unchecked (non-critical) exception if the loading is not fully successfull.
   public void exportToFile( String fileName ) throws TennisDatabaseRuntimeException {
      //...
      try{
         FileWriter fileWriter = new FileWriter(fileName,false);
         PrintWriter printWriter = new PrintWriter(fileWriter);
         //...
         TennisPlayersContainerIterator playerIter = tpc.iterator();
         playerIter.setPreorder();
         //...
         while( playerIter.hasNext() ){
            TennisPlayer currPlayer = playerIter.next();
            printWriter.print( "PLAYER/" );
            printWriter.print( currPlayer.getID() + "/" );
            printWriter.print( currPlayer.getFirstName() + "/" );
            printWriter.print( currPlayer.getLastName() + "/" );
            printWriter.print( currPlayer.getBirthYear() + "/" );
            printWriter.print( currPlayer.getCountry() );
            printWriter.println();
         }
         //PLAYER/DJO87/NOVAK/DJOKOVIC/1987/SERBIA
         //...
         Iterator<TennisMatch> matchIter = tmc.iterator();
         while( matchIter.hasNext() ) {
            TennisMatch currMatch = matchIter.next();
            printWriter.print( "MATCH/" );
            printWriter.print( currMatch.getPlayer1().getID()+"/" );
            printWriter.print( currMatch.getPlayer2().getID()+"/" );
            printWriter.print( currMatch.getDateYear()+currMatch.getDateMonth()+currMatch.getDateDay()+"/");
            printWriter.print( currMatch.getTournament() + "/" );
            printWriter.print( currMatch.getScore() );
            printWriter.println();
         }
         //MATCH/FED81/DJO87/20150223/DUBAI/6-3,7-5
         
         printWriter.close();
      }
      catch ( IOException e ) {}
   }
   
   
   
   // Desc.: Prints all tennis players in the database to the console (sorted by id, alphabetically).
   // Output: Throws an unchecked (non-critical) exception if there are no players in the database.
   public void printAllPlayers() throws TennisDatabaseRuntimeException {
      tpc.printAllPlayers();
   }
   
   // Desc.: Prints all tennis matches of input tennis player (id) to the console (sorted by date, most recent first).
   // Input: The id of the tennis player.
   // Output: Throws a checked (critical) exception if the tennis player (id) does not exists.
   //         Throws an uncheckedor (non-critical) exception if there are no tennis matches (but the player id exists).
   public void printMatchesOfPlayer( String playerId ) throws TennisDatabaseException, TennisDatabaseRuntimeException {
      tpc.printMatchesOfPlayer( playerId );
   }
   
   // Desc.: Prints all tennis matches in the database to the console (sorted by date, most recent first).
   // Output: Throws an unchecked (non-critical) exception if there are no tennis matches in the database.
   public void printAllMatches() throws TennisDatabaseRuntimeException {
      tmc.printAllMatches();
   }
   
   // Desc.: Insert a tennis player into the database.
   // Input: All the data required for a tennis player.
   // Output: Throws a checked (critical) exception if player id is already in this container.
   public void insertPlayer( String id, String firstName, String lastName, int year, String country ) throws TennisDatabaseException {
      TennisPlayer foundPlayer = tpc.getPlayer( id );
      if( foundPlayer == null ) {
         // Input id is valid (not found), perform insertion.
         TennisPlayer newPlayer = new TennisPlayer( id, firstName, lastName, year, country );
         tpc.insertPlayer( newPlayer );
      }
      else { throw new TennisDatabaseException( "Error: Player id already in database!" ); }
   }
   
   // Desc.: Insert a tennis match into the database.
   // Input: All the data required for a tennis match.
   // Output: Throws a checked (critical) exception if a player does not exist in the database.
   //         Throws a checked (critical) exception if the match score is not valid.
   public void insertMatch( String player1ID , String player2ID , int year, int month, int day, String tournament, String score ) throws TennisDatabaseException {
      // Check if the match score is valid.
      int matchWinner = TennisMatch.getMatchWinner( score );
      if( ( matchWinner == 1 ) || ( matchWinner == 2 ) ) {
         // Match score is valid.
         // Get both players, and check if they are both valid.
         TennisPlayer player1 = tpc.getPlayer( player1ID );
         TennisPlayer player2 = tpc.getPlayer( player2ID );
         if( ( player1 != null ) && ( player2 != null ) ) {
            // Both players are valid.
            TennisMatch newMatch = new TennisMatch( player1, player2, year, month, day, tournament, score, matchWinner );
            // Insert new match in both containers.
            tmc.insertMatch( newMatch );
            tpc.insertMatch( newMatch );
         }
         else { throw new TennisDatabaseException( "Error: A player in the match is not in database!" ); }
      }
      else { throw new TennisDatabaseException( "Error: Match score not valid!" ); }
   }
   
   // Desc.: Delete a player with the input id from the database.
   // Input: Player's unique id
   // Output: Throws a checked (critical) exception if a player does not exist in the database.
   public void deletePlayer( String playerId ) /*throws TennisDatabaseException*/ {
      tmc.deleteMatchesOfPlayer( playerId );
      tpc.deleteMatchesOfPlayer( playerId );
      tpc.deletePlayer( playerId );
   }
   
   // Desc.: Reset the database, remove all players and matches
   public void reset(){
      tpc.removeAll();  
      tmc.removeAll();
   } 
}


