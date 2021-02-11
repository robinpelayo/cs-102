



//Robin Pelayo
//CS - 102
//Assignment 1
//Description: SortedLinkedList class to represent a generic linked list of objects.

package TennisDatabase;

import java.lang.Comparable;
import java.lang.Exception;
import java.lang.RuntimeException;

//Class to represent a sorted LinkedList of objects, uses the compateTo method from Comparable.
public class SortedLinkedList< T extends Comparable<T>  > implements SortedLinkedListInterface<T> {

   private SortedLinkedListNode<T> head; // Reference to the first node in the linked list
   private int numItems;                 // Holds the number of items in the linked list
   
   // Constructor.
   public SortedLinkedList() {
      this.head = null;
      this.numItems = 0;
   }
   
   // Desc.: Insert an object of type T this container.
   // Input: An object (reference) of type T.
   // Output: Throws a checked (critical) exception if the insertion is not successful.
   public void insert( T m ) throws Exception{
      //Wrap input item in new node.
      SortedLinkedListNode<T> newNode = new SortedLinkedListNode<T>( m );
      SortedLinkedListNode<T> prevNode = null;
      SortedLinkedListNode<T> currNode = this.head;
      // Search for the proper print of insertion.
      while(( currNode != null ) && (m.compareTo( currNode.item ) < 0) ){
         prevNode = currNode;
         currNode = currNode.next;
      }
      //If the insertion is at the start of the list 
      if( currNode == this.head ) { 
         newNode.next = this.head;  //Set newNode's next reference to what head points to
         this.head = newNode;       //Set head to point to newNode
         this.numItems++;           //Increase the number of items
      }
      //If the insertion is in the middle
      else {
         newNode.next = currNode;   //Set newNode's next reference to point to the current node
         prevNode.next = newNode;   //Set prevNode's next reference to point to the newNode
         this.numItems++;           //Increase number of items
      }
   }
   
   // Desc.: Prints all the items in this container to the console.
   // Output: Throws an unchecked (non-critical) exception if there are no items in this container.
   public void print() throws RuntimeException{
      //Check if the list is empty
      if ( this.head == null ) { throw new RuntimeException( "Error: List empty" ); }
      //If not print the list
      else {
         SortedLinkedListNode<T> currNode = this.head;
         for ( int i = 0; i < this.numItems; i++ ) {
            System.out.println( currNode.item );
            currNode = currNode.next;
         }
      }
   }
}