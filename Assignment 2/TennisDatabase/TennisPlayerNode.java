



//Robin Pelayo
//CS - 102
//Assignment 2
package TennisDatabase;


//Supporting class to the TennisPlayersContainer class
class TennisPlayerNode {
   
   
   TennisPlayer player;                //Holds this tennis player's data
   SortedLinkedList<TennisMatch> list; //This tennis player's list of matches 
   
   TennisPlayerNode leftNode;          //Reference to the left subtree
   TennisPlayerNode rightNode;         //Reference to the right subtree
   
   //Constructor
   public TennisPlayerNode( TennisPlayer p ){
      this.player = p;
      this.list = new SortedLinkedList<TennisMatch>();
      this.leftNode = null;
      this.rightNode = null;
   }
   
   // Inserts a new tennis match into this player's list of matches
   public void insertMatch( TennisMatch m ) {
      try { list.insert(m); }
      catch( Exception e ) {  
         System.out.println("Insertion failed.");
      }
   }
   
   //Deletes matches of player with input id
   public void deleteMatchesOfPlayer( String playerId ){
      for ( int i = 0; i < list.getNumItems(); ){
         try{
            TennisMatch currMatch = list.get(i);
            if ( currMatch.hasPlayer( playerId ) ) {
               list.remove(i);
            }
            else{
               i++;
            }
         }
         catch( Exception e) { System.out.println( "Index invalid." ); }
      }
   }
}   