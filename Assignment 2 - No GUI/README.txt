Robin Pelayo
CS-102 Project - Tennis Record Database

PURPOSE:
   The purpose of this program is storing the records of various tennis players and tennis matches into a database. The custom data type
"TennisPlayer" is used to represent a tennis player and the custom data type "TennisMatch" is used to represent a tennis match. This
program will read in preset tennis players and matches from a data file called "example_data_file.txt". The players and matches are internally
stored in different ways. Players are stored in a sorted binary search tree data structure, and matches are stored as the java ArrayList data type.

INSTRUCTIONS FOR RUNNING:
STEP 1:
Open files in your favorite IDE. RECOMMENDED: jGrasp (Free open source IDE)
STEP 2:
Open the "Assignment2.java" file.
Enter in the "Run Arguments" field at the top, "example_data_file.txt". This will run the program with the given data file containing predefined
tennis players and tennis matches. 
STEP 3:
The program will have loaded all the input data from the 
file. This means that at this point the database should not be empty.

MENU OPTIONS:
Menu option 1:
	Print all players in the database. This is done by
pressing 1 on the keyboard and pressing return.
This will print off all of the players loaded from the file initially in
alphabeticl order.
Menu option 2:
	Print all matches in the database. This will print off all
matches loaded from the file in order of date (most recent to least recent).
Menu Option 3:
	Print all matches of a specific tennis player. This will first ask for the 
player's id. 
Menu option 4:
	Insert new tennis player into the database. This will ask you for a UNIQUE
id for the new player and all the necessary data that a tennis player needs.
Menu option 5:
	Insert a new match into the database. This will ask you for the ids of two 
players that are already in the database. Then you will need to provide all the necessary information
for a tennis match. (The SCORE field should be input as a comma separated list with no spaces Ex: "7-6,7-5,2-6,3-6,6-2").
Menu option 6:
   Quit the program.

