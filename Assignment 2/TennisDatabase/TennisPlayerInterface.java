


//Robin Pelayo
//CS - 102
//Assignment 2

package TennisDatabase;


//Interface to specify which methods need to be in the TennisPlayer class.
interface TennisPlayerInterface extends Comparable<TennisPlayer>{


  //Acessors/Getters
  public String getFirstName();
  public String getID();
  public String getLastName();
  public Integer getBirthYear();
  public String getCountry();
  
  //Method to print the current tennis player on the console 
  public void print();
  
}