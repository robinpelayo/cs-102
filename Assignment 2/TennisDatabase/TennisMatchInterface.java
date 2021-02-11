



//Robin Pelayo
//CS - 102
//Assignment 1

package TennisDatabase;


//Interface providing the specifications for the TennisMatch class.
interface TennisMatchInterface extends Comparable<TennisMatch> {

   //Accessors (getters).
   public TennisPlayer getPlayer1();
   public TennisPlayer getPlayer2();
   public int getDateYear();
   public int getDateMonth();
   public int getDateDay();
   public String getTournament();
   public String getScore();   
   public int getWinner();
   
   // Desc: Prints this tennis match on the console 
   public  void print();
}
