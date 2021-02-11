


//Robin Pelayo
//CS - 102
//Assignment 2

package TennisDatabase;

//Iterator class used to export to file
class TennisPlayersContainerIterator {
   
   
   private QueueArrayBased<TennisPlayer> queue;  //Queue to hold the data from tree
   private TennisPlayerNode root;   //entry point of the iterator into the BST
   
   
   //Constructor
   public TennisPlayersContainerIterator ( TennisPlayerNode root ) {
      //...
      this.queue = new QueueArrayBased<TennisPlayer>();
      this.root = root;
   }
   
   //returns true if the next iteration has more elements
   public boolean hasNext() { return ! this.queue.isEmpty(); }
   
   //Returns the next element in the iteration
   public TennisPlayer next() {
      return queue.dequeue();
   }
   
   //...
   public void setPreorder() {
      this.queue.clear();
      setPreorderRec( this.root );
   }
   
   //....
   private void setPreorderRec( TennisPlayerNode currRoot ){
      if (currRoot == null ) {}
      else {
         this.queue.enqueue( currRoot.player );  //Preorder traversal: Start with the current root, then go left
         setPreorderRec( currRoot.leftNode );    //Go left
         setPreorderRec( currRoot.rightNode );   //Finally, go right
      }
   }
   
   //...
   public void setPostorder() {  
      this.queue.clear();
      setPostorderRec( this.root );
   }
   
   //...
   private void setPostorderRec( TennisPlayerNode currRoot ){
      if ( currRoot == null ) {}
      else{
         setPostorderRec( currRoot.leftNode );
         setPostorderRec( currRoot.rightNode );
         this.queue.enqueue( currRoot.player );
      }
   }
   
   //...
   public void setInorder() {  
      this.queue.clear();
      setInorderRec( this.root );
   }
   
   //...
   private void setInorderRec( TennisPlayerNode currRoot ){
      if ( currRoot == null ) {}
      else{
         setInorderRec( currRoot.leftNode );
         this.queue.enqueue( currRoot.player );
         setInorderRec( currRoot.rightNode );
      }
   }
}