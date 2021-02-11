



//Robin Pelayo
//CS - 102
//Assignment 1

package TennisDatabase;


//Supporting class to the TennisPlayersContainer class
class TennisPlayerNode{

   TennisPlayer player;//Holds this tennis player's data
   SortedLinkedList<TennisMatch> list; //This tennis player's list of matches 
   
   TennisPlayerNode prev; //Prevoius node
   TennisPlayerNode next; //Next node
   
   //Constructor
   public TennisPlayerNode( TennisPlayer p ){
      this.player = p;
      this.list = new SortedLinkedList<TennisMatch>();
      this.prev = null;
      this.next = null;   
   }
   
   // Inserts a new tennis match into this player's list of matches
   public void insertMatch( TennisMatch m ) {
      try { list.insert(m); }
      catch( Exception e ) {}
   }
   
}   