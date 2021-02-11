



//Robin Pelayo
//CS - 102, Summer 2018
//Assignment 1
//Desc: Container for tennis players, used to manipulate and print the list of tennis players

package TennisDatabase;

//Class to hold the tennis players, methods are used to manipulate the list and
//print the list 
class TennisPlayersContainer implements TennisPlayersContainerInterface {

   //Fields
   private TennisPlayerNode head;   //Head reference to the first node in the list  
   private int numPlayers;          //Number of players ( needed because this is a doubly circular list )
   
   //Constructor
   public TennisPlayersContainer(){
      this.head = null;
      this.numPlayers = 0;
   }
   
   // Returns the tennis player node with input id, if it exists null otherwise
   private TennisPlayerNode getPlayerNode( String id ) {
      //If the list is empty return null
      if( this.head == null ) { return null; }
      //Else search the list for a player with the id argument 
      else {
         TennisPlayerNode currNode = this.head;    //Reference to the current node
         int currNodeIndex = 0;                    //holds the current node index
         while( ( currNodeIndex < this.numPlayers ) &&
                ( currNode.player.getID().compareTo( id ) < 0 ) ) {
            currNode = currNode.next;
            currNodeIndex++;
         }
         if( currNode.player.getID().equals( id ) ) { return currNode; }
         else { return null; }
      }
   }
   
   // Method getPlayer 
   // Returns the tennis player with input id, if it exists null otherwise
   public TennisPlayer getPlayer( String id ) {
      TennisPlayerNode pn = getPlayerNode( id );
      if( pn != null ) { return pn.player; }
      else { return null; }
   }
   
   // Desc.: Insert a tennis player into this container.
   // Input: A tennis player.
   // Output: Throws a checked (critical) exception if player id is already in this container.
   //         Throws a checked (critical) exception if the container is full.
   public void insertPlayer( TennisPlayer p ) throws TennisDatabaseException {
      TennisPlayer playerFound = getPlayer( p.getID() );
      if (playerFound == null ) {
         // Input player id not found, preform insertion
         TennisPlayerNode newNode = new TennisPlayerNode( p ); //Wrap player in a new node
         //Case if the list is empty 
         if ( this.head == null ){
            this.head = newNode;       //Set head to newNode
            newNode.next = newNode;    //Since the list is doubly linked and circluar next must point to the newNode for now
            newNode.prev = newNode;    //Since the list is doubly linked and circluar previuos must point to the last node
            this.numPlayers++;         //Increase the number of players
         }
         else{
            //If the insertion is in the middle
            TennisPlayerNode currNode = this.head;
            int currNodeIndex = 0;
            //Loop used to find the point of insertion
            while(  ( currNodeIndex < this.numPlayers ) && (p.compareTo( currNode.player ) > 0) ){
               currNode = currNode.next;
               currNodeIndex++;
            }
            //Preform insertion
            newNode.next = currNode;      //Set the next reference of newNode to point to the current node
            newNode.prev = currNode.prev; //Set the previous reference of newNode to point to the previous node referenced by current node
            currNode.prev.next = newNode; //Set the previous node's next reference to point to the new node
            currNode.prev = newNode;      //Set the current node's previous reference to point to the new node
            this.numPlayers++;            //Increase the number of players
            //Check if insertion is at front, and update head.
            if( currNodeIndex == 0 ) {
               this.head = newNode;       //Set head to point to the new node
            }
         }
      }
      else {
         // Input player id found, throw exeption for duplicate id
         throw new TennisDatabaseException( "Error in tpc insert player: Duplicate player id found");
      }
   }
         
   // Desc.: Insert a tennis match into the lists of both tennis players of the input match.
   // Input: A tennis match.
   // Output: Throws a checked (critical) exception if the insertion is not fully successful.
   public void insertMatch( TennisMatch m ) throws TennisDatabaseException {
      // Get the node of player 1.
      TennisPlayerNode nodePlayer1 = getPlayerNode( m.getPlayer1().getID() );
      if( nodePlayer1 != null ) { nodePlayer1.insertMatch(m); }
      // Get the node of player 2.
      TennisPlayerNode nodePlayer2 = getPlayerNode( m.getPlayer2().getID() );
      if( nodePlayer2 != null ) { nodePlayer2.insertMatch(m); }
   }
   
   // Desc.: Prints all tennis players to the console.
   // Output: Throws an unchecked (non-critical) exception if there are no players in this container.
   public void printAllPlayers() throws TennisDatabaseRuntimeException {
      TennisPlayerNode currNode = this.head;
      int currNodeIndex = 0;
      //Check if the head points to null, if so the list is empty, if not print the list
      if ( currNode != null ){
      //print the player and move to next node
      //Must have index because the list is doubly circular
         while ( currNodeIndex < this.numPlayers ) {
            currNode.player.print();
            System.out.println();
            currNode = currNode.next;
            currNodeIndex++;
         }
      }
      //Throw exception if the list is empty
      else {
         throw new TennisDatabaseRuntimeException( "Error: No players in the databse!");
      }
   }
   
   // Desc.: Prints all tennis matches of input tennis player (id).
   // Input: The id of the tennis player.
   // Output: Throws a checked (critical) exception if the tennis player (id) does not exist.
   //         Throws an unchecked (non-critical) exception if there are no tennis matches in the list (but the player id exists).
   public void printMatchesOfPlayer( String playerId ) throws TennisDatabaseException {
      // Get player node with input id 
      TennisPlayerNode pn = getPlayerNode( playerId );
      //if node is not null print the list of matches, if list is empty throw exception
      //else throw exception because the player was not found 
      if ( pn != null ){
         try{
            pn.list.print();
         }
         catch( RuntimeException e ) {
            throw new TennisDatabaseRuntimeException( "Error: Player has not played any matches");
         }
      }
      else{
         throw new TennisDatabaseException( "Error: Player id not found");
      }
   }
}