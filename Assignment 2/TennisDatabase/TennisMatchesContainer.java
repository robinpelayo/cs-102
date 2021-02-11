


// Robin Pelayo
// CS - 102, Summer 2018
// Assignment 2

package TennisDatabase;

import java.util.ArrayList;
import java.util.Iterator;

//Desc: A java ArrayList of tennis matches
public class TennisMatchesContainer implements TennisMatchesContainerInterface {

   //Fields 
   ArrayList<TennisMatch> matchList;  //List of TennisMatch objects.
   
   //Constructor
   public TennisMatchesContainer(){
      this.matchList = new ArrayList<TennisMatch>();
   }
   
   // Getter for the matches iterator
   public Iterator<TennisMatch> iterator() { return this.matchList.iterator(); }
   
   // Desc.: Insert a tennis match into this container.
   // Input: A tennis match.
   // Output: Throws a checked (critical) exception if the container is full.
   public void insertMatch( TennisMatch m ) throws TennisDatabaseException {
      int insertionIndex = 0;
      //Check if the match is already in the container 
      if ( this.matchList.contains(m) ){ //TODO: Error match is already in the list 
      }
      else {
         //Search for the point of insertion 
         while ( insertionIndex < matchList.size() &&
                  m.compareTo(this.matchList.get(insertionIndex) ) < 0 ){
            insertionIndex++;
         }
         //Inset the match into the list 
         this.matchList.add( insertionIndex, m);
      }
   }
   
   // Desc.: Prints all tennis matches to the console.
   // Output: Throws an exception if there are no matches in this container. 
   public void printAllMatches() throws TennisDatabaseRuntimeException{
      // if the container is empty throw excpetion
      if ( this.matchList.isEmpty() ){ throw new TennisDatabaseRuntimeException( "Error array empty" ); }
      else {
         //Print the list of matches 
         for (int i = 0; i < this.matchList.size(); i++){
            System.out.println(this.matchList.get(i));
         }
      }
   }
   
   //Desc: Deletes the matches of the plaeyr with input id
   public void deleteMatchesOfPlayer( String playerId ){
      for ( int i = 0; i < matchList.size();){
         if ( matchList.get(i).hasPlayer( playerId ) ){
            matchList.remove(i);
         }
         else{
             i++;
         }
      }
   }
   
   //Desc: Removes all tennis matches in this container
   public void removeAll(){
      //this.list.clear(); 
      this.matchList = new ArrayList<TennisMatch>();
   }
}


