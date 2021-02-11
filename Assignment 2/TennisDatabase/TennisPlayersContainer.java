



//Robin Pelayo
//CS - 102, Summer 2018
//Assignment 2
//Desc: Container for tennis players, used to manipulate and print the list of tennis players
//       Binary Search Tree

package TennisDatabase;

//import java.lang.Math; //for exercises

// Class to hold the tennis player's, Structured in a Binary Search Tree
class TennisPlayersContainer implements TennisPlayersContainerInterface {

   //Fields
   private TennisPlayerNode root;   //Root reference to the first node in the tree  
   private int numPlayers;          //Number of players ( needed because this is a doubly circular list )
   
   //Constructor
   public TennisPlayersContainer(){
      this.root = null;
      this.numPlayers = 0;
   }
   
   //Iterator object setup
   public TennisPlayersContainerIterator iterator() {
      return new TennisPlayersContainerIterator( this.root );
   }
   
   // Returns the tennis player node with input id, if it exists null otherwise
   // UPDATE for BST
   private TennisPlayerNode getPlayerNodeRec( TennisPlayerNode currRoot, String id ) {
      //If the list is empty return null
      if( currRoot == null ) { return null; }
      //Else search the list for a player with the id argument 
      else {
         if( currRoot.player.getID().equals( id ) ) { return currRoot; }
         //Else if check if the player is in the right subtree
         else if ( currRoot.player.getID().compareTo( id ) < 0 ){ 
            //The current root's player is less than the input id, so move right.
            return getPlayerNodeRec( currRoot.rightNode, id );
         }
         //Else the current root's player is bigger than the input id so move left.
         else {
            //Move left
            return getPlayerNodeRec( currRoot.leftNode, id );            
         }
      }
   }
   
   // Method getPlayer 
   // Returns the tennis player with input id, if it exists null otherwise
   public TennisPlayer getPlayer( String id ) {
      TennisPlayerNode pn = getPlayerNodeRec( this.root, id );
      //If the node returned by the recursive call is not null 
      //return pn's player
      if( pn != null ) { return pn.player; }
      //Else return null, the player could'nt be found
      else { return null; }
   }
   
   // Desc.: Insert a tennis player into this container.
   // Input: A tennis player.
   // Output: Throws a checked (critical) exception if player id is already in this container.
   public void insertPlayer( TennisPlayer p ) throws TennisDatabaseException {
      try{
         this.root = insertPlayerRec( this.root, p ); //Call to the recursive implementation. 
      }
      catch( TennisDatabaseException e ){
         throw e;
      }
   }
      
   // Desc.: Insert a tennis player into this BST.
   // Input: The current root node of this BST, and a tennis player.
   // Output: The new root node of "this BST", after the insertion.
   private TennisPlayerNode insertPlayerRec( TennisPlayerNode currRoot, TennisPlayer p ) throws TennisDatabaseException{
      //If the current root is null insert the player ( current BST is empty ).
      if ( currRoot == null ) {  
         TennisPlayerNode newNode = new TennisPlayerNode(p); //Wrap the input player in a node
         this.numPlayers++;                                  //Increase the number of players by one if the insertion was successful
         return newNode;                                     //return the new root
      }
      else {
         //Compare input player (p) id versus the player id stored in currRoot ( part A).
         if ( currRoot.player.getID().equals( p.getID() ) ) {
            //Input player (p) id is equal to player id stored in currRoot, abort insertion
            throw new TennisDatabaseException( "Error: Player already exists in database.");
         }
         else {
            //Compare input player (p) id versus the player id stored in currRoot(Part B).
            if ( currRoot.player.getID().compareTo( p.getID() ) < 0 ) {
               // Player id stored in current root is less than input player (p) id,
               // so, forward the insertion to the right subtree.
               currRoot.rightNode = insertPlayerRec(currRoot.rightNode, p );
               return currRoot;         
            }
            else {
               // Player id stored in current root is greater than input player (p) id,
               // so, forward the insertion to the left subtree.
               currRoot.leftNode = insertPlayerRec(currRoot.leftNode, p );
               return currRoot;
            }
         }
      }
   }
       
   // Desc.: Insert a tennis match into the lists of both tennis players of the input match.
   // Input: A tennis match.
   // Output: Throws a checked (critical) exception if the insertion is not fully successful.
   public void insertMatch( TennisMatch m ) throws TennisDatabaseException {
      //Get the first player's node
      TennisPlayerNode player1 = getPlayerNodeRec( this.root, m.getPlayer1().getID() );
      //Check if the player was found 
      if ( player1 != null ) {
         //the player was found, preform insertion
         player1.insertMatch(m);
      }
      else{
         throw new TennisDatabaseRuntimeException( "Error: Player 1 not found in database.");
      }
      //Get the second player's node
      TennisPlayerNode player2 = getPlayerNodeRec( this.root, m.getPlayer2().getID() );
      //Check if the player was found 
      if ( player2 != null ) {
         //the player was found, preform insertion
         player2.insertMatch(m);
      }
      else{
         throw new TennisDatabaseRuntimeException( "Error: Player 2 not found in database.");      }
   }
   
   // Desc.: Prints all tennis players to the console.
   // Output: Throws an unchecked (non-critical) exception if there are no players in this container.
   public void printAllPlayers() throws TennisDatabaseRuntimeException {     
      //Check if this container is empty
      if ( this.root == null ) { 
         throw new TennisDatabaseRuntimeException( "Error: No players in the databse!"); 
      } 
      //Call to the recursive implementation
      else { printAllPlayersRec( this.root ); }
   }
   
   //Private recursive method used to print all players in this container
   private void printAllPlayersRec(TennisPlayerNode currRoot){
      if( currRoot == null ) { return; }
      else {
         printAllPlayersRec( currRoot.leftNode );
         currRoot.player.print();
         System.out.println();
         printAllPlayersRec( currRoot.rightNode );
      }
   }
   
   // Desc.: Prints all tennis matches of input tennis player (id).
   // Input: The id of the tennis player.
   // Output: Throws a checked (critical) exception if the tennis player (id) does not exist.
   //         Throws an unchecked (non-critical) exception if there are no tennis matches in the list (but the player id exists).
   public void printMatchesOfPlayer( String playerId ) throws TennisDatabaseException {
      //NEEDS TESTING
      //Step 1: Find the player node with the input argument id
      TennisPlayerNode pn = getPlayerNodeRec( this.root, playerId );
      //Step 2: Chceck if the node is not null
      if ( pn != null ) {
         //The player node is not null, print the list if it has any matches, if not throw exception
         try{
            pn.list.print();
         }
         catch ( RuntimeException e ) { 
            throw new TennisDatabaseRuntimeException( "Error: Player has not played any matches"); }
      }
      else {
         //The player node is null, throw exception
         throw new TennisDatabaseException( "Error: Player id not found");
      }  
   }
   
   // Desc.: Deletes the player with the specified id
   // Input: The id of the tennis player.
   // Output: Throws a checked (critical) exception if the player was not found in the database
   public void deletePlayer( String playerId ) throws TennisDatabaseException {
      try{ 
         this.root = deletePlayerRec( this.root, playerId );
      }
      catch( TennisDatabaseException e ){
         throw e;
      }
   }
   
   //This method finds which node to be deleted and forwards the deletion to deletePlayerNodeRec
   private TennisPlayerNode deletePlayerRec( TennisPlayerNode currRoot, String playerId ) throws TennisDatabaseException {
      TennisPlayerNode newSubtree;
      if( currRoot == null ) { throw new TennisDatabaseException( "Input id not found." ); }
      else {
         int compareToResult = playerId.compareTo( currRoot.player.getID() );
         if( compareToResult == 0 ) { // Item is in the root.
            newSubtree = deletePlayerNodeRec( currRoot );   //This is the call to the recursive function that deletes the node
            return newSubtree;
         }
         else if( compareToResult < 0 ) { // Search left tree.
            newSubtree = deletePlayerRec( currRoot.leftNode, playerId );   //Forward the search for the node to be deleted to the left subtree
            currRoot.leftNode = newSubtree;
            return currRoot;
         }
         else {
            newSubtree = deletePlayerRec( currRoot.rightNode, playerId );   //Forward the search for the node to be deleted to the right subtree
            currRoot.rightNode = newSubtree;
            return currRoot;
         } 
      }
   }
   
   //Method to delete the node passed as an argument
   private TennisPlayerNode deletePlayerNodeRec( TennisPlayerNode markedNode ) {
      // 4 cases to consider: markedNode is a leaf (1); markedNode has no left child (2);
      // markedNode has no right child (3); markedNode has 2 children (4).
      if( ( markedNode.leftNode == null ) && ( markedNode.rightNode == null ) ) { // Case (1).
         return null; 
      }
      else if( markedNode.leftNode == null ) { return markedNode.rightNode; } // Case (2).
      else if( markedNode.rightNode == null ) { return markedNode.leftNode; } // Case (3).
      else { // Case (4): retrieve and delete the inorder successor.
         TennisPlayerNode replacementNode;
         replacementNode = findLeftmostNodeRec( markedNode.rightNode );
         //Sawp the content of the nodes
         markedNode.player = replacementNode.player;
         markedNode.list = replacementNode.list;
         //Delete the leftmost Node of the right subtree of marked node
         markedNode.rightNode = deleteLeftmostNodeRec( markedNode.rightNode );
         return markedNode; //Now (after the swapping and the delete) marked node represents the new root of this subtree.
      }
   }
   
   //Finds the leftmost node of the subtree with root currRoot.
   private TennisPlayerNode findLeftmostNodeRec( TennisPlayerNode currRoot ) {
      if ( currRoot == null ) { return null; }  
      else if ( currRoot.leftNode == null ) { return currRoot; }
      else { return findLeftmostNodeRec( currRoot.leftNode ); }
   }
   
   //Deletes the leftmost node of the tree rooted at currRoot
   private TennisPlayerNode deleteLeftmostNodeRec( TennisPlayerNode currRoot ) {
      if ( currRoot == null ) { return null; }
      //If the node is the leftmost node
      else if ( currRoot.leftNode == null ) { return currRoot.rightNode; }
      //if the node is not the leftmost node
      else {
         currRoot.leftNode = deleteLeftmostNodeRec( currRoot.leftNode );
         return currRoot; 
      }
   }
   
   //Public wraper with for the deletion method
   public void deleteMatchesOfPlayer ( String playerId ){
      //Call to recursive implementaion
      deleteMatchesOfPlayerRec( this.root, playerId );
   }
   
   // Recursive implementation
   public void deleteMatchesOfPlayerRec( TennisPlayerNode currRoot, String playerId ){
      if ( currRoot == null ) { return; }
      else {
         currRoot.deleteMatchesOfPlayer( playerId );
         deleteMatchesOfPlayerRec( currRoot.leftNode, playerId );
         deleteMatchesOfPlayerRec( currRoot.rightNode, playerId );
      }
   }

   // Desc.: Remove all tennis players in this container
   public void removeAll() throws TennisDatabaseRuntimeException {
      //Set the reference to the root equal to null, causing a chain reaction.
      //The garbage collector will remove all node objects with no references to them.
      if ( this.root == null ){ throw new TennisDatabaseRuntimeException( "Error: Database empty." ); }
      else{ 
         this.root = null; 
         this.numPlayers = 0;
      }
   }
}