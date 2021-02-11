


// Robin Pelayo
// CS - 102, Summer 2018
// Assignment 1



package TennisDatabase;

//Desc: A dynamically allocated array-based list to store TennisMatches
class TennisMatchesContainer implements TennisMatchesContainerInterface {

   //Feilds 
   private TennisMatch matches[];    //Array of TennisMatches
   private int maxSize;              //Physical size of the container
   private int numItems;             //Logical size of array (number of matches in the array)
   
   //Constructor
   public TennisMatchesContainer(){
      this.numItems = 0;
      this.maxSize = 2;
      this.matches = new TennisMatch[this.maxSize];
   }
   
   // Desc.: Insert a tennis match into this container.
   // Input: A tennis match.
   // Output: Throws a checked (critical) exception if the container is full.
   public void insertMatch( TennisMatch m ) throws TennisDatabaseException {
      // Check if array is full, if so resize it.
      if ( this.numItems >= this.maxSize ) {
         // Create new array with double the size
         int newSize = this.maxSize * 2;
         TennisMatch[] newArray = new TennisMatch[newSize];
         // Copy the contents of the old array into the new array
         for (int i = 0; i < this.numItems; i++){
            newArray[i] = this.matches[i];
         }
         this.matches = newArray;   //Set the matches reference to point to the new array
         this.maxSize = newSize;    //Update the maxSize
      }
      // Now array is for sure not full, perform insertion by value.
      int insertionIndex = 0;
      int index = 0;
      //Find the index where the match should be inserted
      while( ( index < numItems ) && 
             ( m.compareTo( this.matches[index] ) < 0 ) ) {      
         index++;
      }
      insertionIndex = index;
      // If the index is at the logical size, do not shift the array
      if ( insertionIndex < this.numItems ){
         //Shift the array right         
         for ( int i = numItems; i > insertionIndex; i--){
             matches[i] = matches[i-1];
         }
      }
      //Insert the match
      matches[insertionIndex] = m;
      this.numItems++;
   }
   
   // Desc.: Prints all tennis matches to the console.
   // Output: Throws an exception if there are no matches in this container. 
   public void printAllMatches() throws TennisDatabaseRuntimeException{
      // if the container is empty throw excpetion
      if ( numItems == 0 ){
        throw new TennisDatabaseRuntimeException( "Error array empty" );  
      }
      // else run through the array and print each match on the console
      else{
         for (int i = 0; i < numItems; i++){
               matches[i].print();
         }
      }
   }
}


