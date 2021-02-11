


//Robin Pelayo
//CS-102, Summer 2018
//Assignment 1
//Desc: SortedLinkedListNode generic class,
//                   Implementing the SortedLinkedList class


package TennisDatabase;


//Supporting class for the SortedLinkedList class, this class represents a node in the list
class SortedLinkedListNode<T>{
    //Feilds
    T item;                         // Item in the list 
    SortedLinkedListNode<T> next;   // Reference to the next node in the list
    
     
    //Constructor 
    public SortedLinkedListNode ( T i ) {
      this.item = i;
      this.next = null;
    }   

}