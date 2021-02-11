

//Robin Pelayo
//CS - 102
//Assignment 2
//Description: QueueArrayBased generic class,
//             implementing a queue using a dynamic circular array to be used to store tennis players.

package TennisDatabase;

//Generic Class to hold objects in  a queue array based
class QueueArrayBased< T > /*implements QueueArrayBasedInterface*/ {
   
   private T[] array;         // Circular array.
   private int sizePhysical;  // Max number of items the array can store
   private int sizeLogical;   // Crurrent number of items in the array.
   private int front;         //Index of the item at the front of the queue
   private int back;          //Index of the item at the back of the queue
   
   // Constructor.
   public QueueArrayBased() {
      clear(); 
   }
   
   // Enqueue an item into the queue
   public void enqueue( T item ) {
      // Check if current circular array is full.
      if( this.sizeLogical == this.sizePhysical ) {
         // ...
         int newFrontIndex = 0;
         int oldFrontIndex = this.front;
         int newSizePhysical = this.sizePhysical * 2;
         T[] newArray = (T[]) new Object[ newSizePhysical ];
         int currIndexOldArray = this.front;
         int currIndexNewArray = newFrontIndex;
         // ...
         for( int i = 1; i <= this.sizeLogical; i++ ) {
            newArray[ currIndexNewArray ] = this.array[ currIndexOldArray ];
            currIndexOldArray = ( currIndexOldArray + 1 ) % this.sizePhysical;
            currIndexNewArray = ( currIndexNewArray + 1 ) % newSizePhysical;
         }
         // ...
         this.array = newArray;
         this.sizePhysical = newSizePhysical;
         this.front = newFrontIndex;
         this.back = ( newFrontIndex + this.sizeLogical - 1 ) % newSizePhysical;
      }
      // ...
      this.back = ( this.back + 1 ) % this.sizePhysical;
      this.array[ this.back ] = item;
      this.sizeLogical++;
   }
   
   //...
   public T dequeue(){
      if( sizeLogical == 0 ){
         return null;
      }
      T frontItem = this.array[ this.front ];                  //Store the front item in a local variable
      this.array[ this.front ] = null;       //Unnecessary
      this.front = ( this.front + 1 ) % this.sizePhysical;     //Update the front, set the index to the previous index plus 1
      this.sizeLogical--;                                      //Update the logical size
      return frontItem;                                        //Return the front item
   }
   
   
   //
   public boolean isEmpty(){
      if ( sizeLogical == 0 ) { return true; }
      return false;
   }
   
   //clear the queue
   public void clear(){
      //Option1
      /*
      while ( !this.isEmpty() ){
         this.dequeue();
      }*/
      //Option2 - Better preformance( no loop )
      this.sizePhysical = 2;
      this.sizeLogical = 0;
      this.front = 0;
      this.back = -1;
      this.array = (T[]) new Object[ this.sizePhysical ];
   }
}