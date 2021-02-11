







//Robin Pelayo
//CS - 102
//Assignment 1
//Description: TennisDatabase interface hold specs for TennisDatabase class.


package TennisDatabase;


import java.lang.String;

//Interface to describe what methods will be in the TennisDatabase class
interface TennisDatabaseInterface{
   
   
   // Desc.: Loads data from file following the format described in the specifications.
   // Output: Throws an unchecked (non-critical) exception if the loading is not fully successfull.
   public void importFromFile( String fileName ) throws TennisDatabaseRuntimeException;
   //not sure how to do this.
   
   
   
   // Desc.: Prints all tennis players in the database to the console (sorted by id, alphabetically).
   // Output: Throws an unchecked (non-critical) exception if there are no players in the database.
   public void printAllPlayers() throws TennisDatabaseRuntimeException;
   //Check if there are players in the playerscontainer
   //If no players 
   //throw exception
   //Else
   //Call TennisPlayersContainer.printAllPlayers.
   
   // Desc.: Prints all tennis matches of input tennis player (id) to the console (sorted by date, most recent first).
   // Input: The id of the tennis player.
   // Output: Throws a checked (critical) exception if the tennis player (id) does not exists.
   //         Throws an uncheckedor (non-critical) exception if there are no tennis matches (but the player id exists).
   public void printMatchesOfPlayer( String playerId ) throws TennisDatabaseException, TennisDatabaseRuntimeException;
   //Checks that the id exists in the container
   //Throws a non-critical exception if there is a player but there are no tennis matches acociated with that player
   //Calls TennisPlayersContainer.printMatchesOfPlayer
   
   
   
   // Desc.: Prints all tennis matches in the database to the console (sorted by date, most recent first).
   // Output: Throws an unchecked (non-critical) exception if there are no tennis matches in the database.
   public void printAllMatches() throws TennisDatabaseRuntimeException;
   //Checks if there are tennis matches in the database, if not throw non-critical exception
   //Calls TennisMatchesContainer.printAllMatches.
   
   
   
   // Desc.: Insert a tennis player into the database.
   // Input: All the data required for a tennis player.
   // Output: Throws a checked (critical) exception if player id is already in this container.
   public void insertPlayer( String id, String firstName, String lastName, int year, String country ) throws TennisDatabaseException;
   //Check if the player is already in the container.
   //Create a new TennisPlayer with input data
   //Call TennisPlayersContainer.insertPlayer.
   
   
   // Desc.: Insert a tennis match into the database.
   // Input: All the data required for a tennis match.
   // Output: Throws a checked (critical) exception if a player does not exist in the database.
   //         Throws a checked (critical) exception if the match score is not valid.
   public void insertMatch( String player1ID , String player2ID , int year, int month, int day, String tournament, String score ) throws TennisDatabaseException;
   //Check if the players exist in the database
   //Check if the match score is valid
   //Create TennisMatch 
   //Call TennisMatchesContainer.insertMatch.
   
   //Methods:
   //Load 
   //Insert player
      //Check the data passed to this method 
      //Create new TennisPlayer
      //Store TennisPlayer in TennisPlayersContainer
   //Insert match
      //Ask user for id numbers
      //Ask user for date (will have to split this by year, month, and day)
      //Ask user for tournament name 
      //Ask user for score
      //Create new TennisMatch
      //store TennisMatch into TennisMatchesContainer
   //print all players (sorted alphabetically by ids)
      //Sort the players by date
      //TennisPlayer1.print
      //TennisPlayer2.print
      //3.print...
      //...
   //print all matches (sorted by date - most recent first)
       //Sort the matches by date 
       //TennisMatch1.print
       //TennisMatch2.print
       //3.print...
       //...
   //print all matches of a player (sorted by date - most recent first)
       //Sort the matches of a player by date
        
   //This class needs to edit the database records 
}