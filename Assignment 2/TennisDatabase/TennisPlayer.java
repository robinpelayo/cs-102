



//Robin Pelayo
//CS - 102
//Assignment 2
//Description: TennisPlayer class storing information about a tennis player.

package TennisDatabase;

//Class to represent a tennis player
public class TennisPlayer implements TennisPlayerInterface {  
   
   //Fields
   private String id;         //Holds the player's id
   private String firstName;  //Holds the player's first name
   private String lastName;   //holds the player's last name 
   private int birthYear;     //holds the player's birth year
   private String country;    //holds the player's home country
   private int wins;          //holds the player's total wins
   private int losses;        //holds the player's total losses
   
   //Constructors - shpuld probably add more
   //public TennisPlayer(){}
   public TennisPlayer(String id, String firstName, 
                        String lastName, int birthYear, String country){
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.birthYear = birthYear;
      this.country = country;
      this.wins = 0;
      this.losses = 0;
   }
   
   //Getter Methods 
   public String getID(){return id;}
   public String getFirstName(){return firstName;}
   public String getLastName(){return lastName;}
   public Integer getBirthYear(){return birthYear;}
   public String getCountry(){return country;}
   
   //Prints this player on the console 
   public void print(){
      System.out.print(this.id + ": " + this.firstName + " " + 
                        this.lastName + ", " + this.birthYear + ", " + this.country + 
                        ", " + this.wins + "/" + this.losses + " (WINS/LOSSES)");
   }
   
   //Method compareTo overrides inherited method from comparable,
   //used to compare this tennisplayer with other to sort them by player id alphabetically
   public int compareTo( TennisPlayer other ) {
      return this.id.compareTo( other.id );
   }
   
   //Method incrementWinsByOne increments the wins of this player by one 
   public void incrementWinsByOne(){
      this.wins++;
   }
   
   //Method incrementLossesByOne increments the losses of this player by one
   public void incrementLossesByOne(){
      this.losses++;
   }
   
   //DEBUG
   public String toString(){
      return this.getID();
   }
}


