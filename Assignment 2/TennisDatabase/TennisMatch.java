



//Robin Pelayo
//CS - 102, Summer 2018
//Assignment 1
//Description: TennisMatch class storing information about a tennis match.

package TennisDatabase;

import java.util.regex.PatternSyntaxException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;

//Class to represent a tennis match.
public class TennisMatch implements TennisMatchInterface {

    //Feilds
    private TennisPlayer player1;     //Player 1
    private TennisPlayer player2;     //Player 2     
    private int dateYear;             //Year of the match
    private int dateMonth;            //Month of the match
    private int dateDay;              //Day of the match
    private String tournament;        //Tournament name
    private String score;             //Score of the match
    private int winner;               //{1,2}: 1 means player1 won, 2 means player2 won.
    
    //Constructors
    public TennisMatch( TennisPlayer player1, TennisPlayer player2, 
                        int dateYear, int dateMonth, int dateDay,
                        String tournament, String score, int winner ) {
     this.player1 = player1;      //Java knows the difference between the feilds of this class and the 
     this.player2 = player2;      //arguments "this.name = name;" is different because "this" is used to
     this.dateYear = dateYear;    //access the current object.
     this.dateMonth = dateMonth;
     this.dateDay = dateDay;
     this.tournament = tournament;
     this.score = score;
     this.winner = winner;
     //While creating this match update the win loss record of each player
     if( this.winner == 1 ) {
        this.player1.incrementWinsByOne();
        this.player2.incrementLossesByOne();
     }
     else if( this.winner == 2 ) {
        this.player2.incrementWinsByOne();
        this.player1.incrementLossesByOne();
     }
   }
   
   //Getter/Accessor Methods
   public TennisPlayer getPlayer1(){ return this.player1; }
   public TennisPlayer getPlayer2(){ return this.player2; }
   public int getDateYear(){ return dateYear; }
   public int getDateMonth(){ return dateMonth; }
   public int getDateDay(){ return dateDay; }
   public String getTournament(){ return tournament; }
   public String getScore(){ return score; }
   public int getWinner(){ return winner; }
   
   //Getters for GUI
   public String getPlayer1LastName(){ return this.player1.getLastName(); }
   public String getPlayer2LastName(){ return this.player2.getLastName(); }
   public String getCombinedDate(){ return String.valueOf( this.dateYear ) + "/" + String.valueOf( this.dateMonth ) + "/" + String.valueOf( this.dateDay ); }
   
   //returns 1 if other has an older date than this, -1 if other has a more recent date.
   //returns 0 if the dates are the same.
   public int compareTo( TennisMatch other ) throws NullPointerException { 
      if( other == null ) { throw new NullPointerException("Error: Null input argument in TM.compareTo"); }
      else if ( other.dateYear < this.dateYear ){ return 1; }
      else if ( other.dateYear > this.dateYear ) { return -1; }
      else if ( other.dateMonth < this.dateMonth ) { return 1; }
      else if ( other.dateMonth > this.dateMonth ) { return -1; }
      else if ( other.dateDay < this.dateDay ) { return 1; }
      else if ( other.dateDay > this.dateDay ) { return -1; }
      return 0;
   }
   
   
   //match score --> sets won --> winner
   // 6-3,6-7,6-2 --> 2-1 --> 1
   //Return type: only 1 or 2, meaning player 1 won or player 2 won respectivly.
   public static int getMatchWinner( String matchScore ) {
      //STEP 1: Convert match score (6-3,6-7,6-2) into sets won (2-1).
      TennisMatchSetScore setScore = processMatchScoreRec( matchScore );
      //STEP 2: Compare the sets won by player 1 vs the sets won by player 2, and return the winner.
      if ( setScore.setsPlayer1 > setScore.setsPlayer2 ) { return 1; }
      else if (setScore.setsPlayer2 > setScore.setsPlayer1 ) {return 2;}
      else { return -1; }
   }   
   
   //Method to preocess the match score recursivley
   private static TennisMatchSetScore processMatchScoreRec( String matchScoreString ){
      try { 
         // STEP A: Remove set 1 score from the match score.
         String[] setScoreStrings = matchScoreString.split( "," );   //Splits the match score into an array of set scores
         String set1ScoreString = setScoreStrings[0];                //Sets ste1ScoreString to the first set score in the array
         // STEP B: Process set 1 score.
         String[] set1Games = set1ScoreString.split( "-" );          //Splits set1ScoreString into game scores using '-'
         int gamesWonP1 = Integer.parseInt( set1Games[0] );          //parses the set1 games as integers 
         int gamesWonP2 = Integer.parseInt( set1Games[1] );          //    "        "
         TennisMatchSetScore set1Score = new TennisMatchSetScore();  //Create new set score
         //Compare the game scores
         if( gamesWonP1 > gamesWonP2 ) {
            set1Score.setsPlayer1 = 1;
            set1Score.setsPlayer2 = 0;
         }
         else if( gamesWonP1 < gamesWonP2 ) {
            set1Score.setsPlayer1 = 0;
            set1Score.setsPlayer2 = 1;
         }
         else {
            set1Score.setsPlayer1 = 0;
            set1Score.setsPlayer2 = 0;
         }
         // Check if match score includes only 1 set or more.
         if( setScoreStrings.length > 1 ) {
            // STEP C: Process other sets score recursively.
            //Get the rest of the set's scores, stored in otherSetsScoreString
            String otherSetsScoreString = matchScoreString.substring( set1ScoreString.length() + 1 );
            //New set score holds result of recurrsive call
            TennisMatchSetScore otherSetsScore = processMatchScoreRec( otherSetsScoreString );
            // STEP D: Merge result of set 1 with result of other sets.
            TennisMatchSetScore matchScore = new TennisMatchSetScore();                   //new final match score
            //Add the first set score with the result of the recurrsive call
            matchScore.setsPlayer1 = set1Score.setsPlayer1 + otherSetsScore.setsPlayer1;  
            matchScore.setsPlayer2 = set1Score.setsPlayer2 + otherSetsScore.setsPlayer2;
            return matchScore;
         }
         else { return set1Score; }
      }
      catch( PatternSyntaxException e ) {System.out.println("PatternSyntaxException thrown in TennisMatch processMatchScoreRec");}
      catch( ArrayIndexOutOfBoundsException e ) {System.out.println("ArrayIndexOutOfBoundsException thrown in TennisMatch processMatchScoreRec");}
      catch( IndexOutOfBoundsException e ) {System.out.println("IndexOutOfBoundsException thrown in TennisMatch processMatchScoreRec");}
      // STEP E: Something went wrong during the parsing, return a 0-0 score.
      TennisMatchSetScore matchScore = new TennisMatchSetScore();
      matchScore.setsPlayer1 = 0;
      matchScore.setsPlayer2 = 0;
      return matchScore;
   }
   
   //Returns true if this match has the player with input id
   public boolean hasPlayer( String playerId ){
      return ( player1.getID().equals( playerId ) || player2.getID().equals( playerId ) );
   }
   
   //toString method overides inherited toString method
   public String toString () {
      String outString = this.player1.getLastName() + "-" + this.player2.getLastName() 
                          + "/" + this.dateYear +"/"+ this.dateMonth + "/" + this.dateDay
                          + "/" + this.tournament + "/" + this.score;
      return outString;
   }
   
   //Prints this match on the console 
   public void print(){
      System.out.println( this );
   }
}