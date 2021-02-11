



//Robin Pelayo
//CS - 102, Summer 2018
//Assignment 2

import java.lang.ArrayIndexOutOfBoundsException;
import java.util.InputMismatchException;
import java.util.Scanner;

import TennisDatabase.TennisDatabaseRuntimeException;
import TennisDatabase.TennisDatabaseException;
import TennisDatabase.TennisDatabase;
import TennisDatabase.TennisPlayer;
import TennisDatabase.TennisMatch;

//GUI Stuff
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

//Class to hold the menu method and main method.
// Note: main class for a JavaFX app extends the javafx.application.Application class
public class Assignment2 extends Application{ 
   // The start method is the main entry point for all JavaFX applications.
   // Stage: The A JavaFX app defines the UI container by means of a stage and a scene.
   // The JavaFX Stage class is the top-level JavaFX container.
   @Override
   public void start( Stage stage ) {
   
      TennisDatabase tdb = new TennisDatabase();   //Create a new tennis database
      Scanner input = new Scanner(System.in);      //New scanner for user and file input
      
      //Introduction
      System.out.println("CS-102 Tennis Manager");

      //Load file data
      try {
         //String fileName = args[0];
         String fileName = getParameters().getRaw().get(0);
         
         tdb.importFromFile( fileName );
      }
      catch ( ArrayIndexOutOfBoundsException e ) { System.out.println( "No input file name..." ); }
      System.out.println("File load success!");
      
      
      //Create new scene
      Scene scene = new Scene( new Group() );
      stage.setTitle( "Tennis Database" );
      stage.setWidth( 1150 ); stage.setHeight( 550 );
      final Label playerLabel = new Label( "Tennis Players" );
      playerLabel.setFont( new Font( "Arial", 14 ) );
      final Label matchesLabel = new Label( "Tennis Matches" );
      matchesLabel.setFont( new Font( "Arial", 14 ) );
      
      // SETTINGS FOR PLAYERS TABLE VIEW
      TableView<TennisPlayer> playersTable = new TableView<>(); 
      playersTable.setEditable( false );
      // TABLE VIEW - COLUMN 1
      TableColumn< TennisPlayer, String > colPlayerId = new TableColumn<>( "Player Id" );
      colPlayerId.setMinWidth( 100 );
      colPlayerId.setCellValueFactory( new PropertyValueFactory<>( "ID" ) );
      //colPlayerId.setCellFactory( TextFieldTableCell.<TennisPlayer>forTableColumn() );
      // TABLE VIEW - COLUMN 2
      TableColumn< TennisPlayer, String > colPlayerFirstName = new TableColumn<>( "First Name" );
      colPlayerFirstName.setMinWidth( 100 );
      colPlayerFirstName.setCellValueFactory( new PropertyValueFactory<>( "firstName" ) );
      // TABLE VIEW - COLUMN 3
      TableColumn< TennisPlayer, String > colPlayerLastName = new TableColumn<>( "Last Name" );
      colPlayerLastName.setMinWidth( 100 );
      colPlayerLastName.setCellValueFactory( new PropertyValueFactory<>( "lastName" ) );
      // TABLE VIEW - COLUMN 4
      TableColumn< TennisPlayer, Integer > colPlayerBirthYear = new TableColumn<>( "Birth Year" );
      colPlayerBirthYear.setMinWidth( 100 );
      colPlayerBirthYear.setCellValueFactory( new PropertyValueFactory<>( "birthYear" ) );
      // TABLE VIEW - COLUMN 5
      TableColumn< TennisPlayer, String > colPlayerCounrty = new TableColumn<>( "Born in" );
      colPlayerCounrty.setMinWidth( 100 );
      colPlayerCounrty.setCellValueFactory( new PropertyValueFactory<>( "country" ) );
      
      // SETTINGS FOR MATCHES TABLE VIEW
      TableView<TennisMatch> matchesTable = new TableView<>(); 
      matchesTable.setEditable( false );
      // TABLE VIEW - COLUMN 1
      TableColumn< TennisMatch, String > colPlayer1LastName = new TableColumn<>( "Player 1" );
      colPlayer1LastName.setMinWidth( 100 );
      colPlayer1LastName.setCellValueFactory( new PropertyValueFactory<>( "player1LastName" ) );
      // TABLE VIEW - COLUMN 2
      TableColumn< TennisMatch, String > colPlayer2LastName = new TableColumn<>( "Player 2" );
      colPlayer2LastName.setMinWidth( 100 );
      colPlayer2LastName.setCellValueFactory( new PropertyValueFactory<>( "player2LastName" ) );
      // TABLE VIEW - COLUMN 3
      TableColumn< TennisMatch, String > colDate = new TableColumn<>( "Date" );
      colDate.setMinWidth( 100 );
      colDate.setCellValueFactory( new PropertyValueFactory<>( "combinedDate" ) );
      // TABLE VIEW - COLUMN 4
      TableColumn< TennisMatch, String > colTournament = new TableColumn<>( "Tournament" );
      colTournament.setMinWidth( 100 );
      colTournament.setCellValueFactory( new PropertyValueFactory<>( "tournament" ) );
      // TABLE VIEW - COLUMN 5
      TableColumn< TennisMatch, String > colScore = new TableColumn<>( "Score" );
      colScore.setMinWidth( 100 );
      colScore.setCellValueFactory( new PropertyValueFactory<>( "score" ) );
      
      //Adds the columns to the player table
      playersTable.setItems( tdb.getObservableListPlayers() );
      playersTable.getColumns().addAll( colPlayerId, colPlayerFirstName, colPlayerLastName, 
                                 colPlayerBirthYear, colPlayerCounrty );
      //Adds the columns to the matches table
      matchesTable.setItems( tdb.getObservableListMatches() );
      matchesTable.getColumns().addAll( colPlayer1LastName, colPlayer2LastName, colDate, colTournament, colScore );
      
      //INSERT PLAYER TEXT FEILDS
      //ID TEXT FEILD
      final TextField addId = new TextField();
      addId.setPromptText( "Player Id" );
      addId.setMaxWidth( 100 );
      //FIRST NAME TEXT FEILD
      final TextField addFirstName = new TextField();
      addFirstName.setPromptText( "First Name" );
      addFirstName.setMaxWidth( 100 );
      //LAST NAME TEXT FEILD
      final TextField addLastName = new TextField();
      addLastName.setMaxWidth( 100 );
      addLastName.setPromptText( "Last Name" );
      //BIRTH YEAR TEXT FEILD
      final TextField addBirthYear = new TextField();
      addBirthYear.setMaxWidth( 100 );
      addBirthYear.setPromptText( "Birth Year" );
      //COUNTRY TEXT FEILD
      final TextField addBirthCountry = new TextField();
      addBirthCountry.setMaxWidth( 100 );
      addBirthCountry.setPromptText( "Birth Country" );
      
      //ADD PLAYER BUTTON
      final Button addPlayerButton = new Button( "Add" );
      addPlayerButton.setOnAction(
         ( ActionEvent e ) -> {
            //Local variables 
            String insertId = addId.getText().toUpperCase();
            String insertFirstName = addFirstName.getText().toUpperCase();
            String insertLastName = addLastName.getText().toUpperCase();
            int insertBirthYear = Integer.parseInt( addBirthYear.getText() );
            String insertCountry = addBirthCountry.getText().toUpperCase();
            
            //Insert player into tdb
            try{
               tdb.insertPlayer( insertId, insertFirstName, insertLastName, insertBirthYear, insertCountry );
            }
            catch( TennisDatabaseException exception ){ System.out.println( "Error: Player id already in database!" ); }
         }
      );
      
      //REMOVE PLAYER TEXT FEILD
      final TextField removePlayer = new TextField();
      removePlayer.setMaxWidth( 100 );
      removePlayer.setPromptText( "Player Id" );
      
      //REMOVE PLAYER BUTTON
      final Button removePlayerButton = new Button( "Remove" );
      removePlayerButton.setOnAction(
         ( ActionEvent e ) -> {
            String removeId = removePlayer.getText().toUpperCase();
            try{
               tdb.deletePlayer( removeId );
            }
            catch( TennisDatabaseException removalException ){ System.out.println("Player not in database."); }
      } );
      
      //INSERT MATCH TEXT FEILDS
      //FIRST PLAYER ID TEXT FEILD
      final TextField addPlayer1Id = new TextField();
      addPlayer1Id.setPromptText( "Id Player 1" );
      addPlayer1Id.setMaxWidth( 100 );
      //SECOND PLAYER ID TEXT FEILD
      final TextField addPlayer2Id = new TextField();
      addPlayer2Id.setPromptText( "Id Player 2" );
      addPlayer2Id.setMaxWidth( 100 );
      //DATE TEXT FEILD
      final TextField addDate = new TextField();
      addDate.setMaxWidth( 100 );
      addDate.setPromptText( "Match Date(YYYYMMDD)" );
      //TOURNAMENT TEXT FEILD
      final TextField addTournament = new TextField();
      addTournament.setMaxWidth( 100 );
      addTournament.setPromptText( "Tournament Name" );
      //SCORE TEXT FEILD
      final TextField addScore = new TextField();
      addScore.setMaxWidth( 100 );
      addScore.setPromptText( "Match Score" );
      
      //ADD MATCH BUTTON
      final Button addMatchButton = new Button( "Add" );
      addMatchButton.setOnAction(
         ( ActionEvent e ) -> {
            //Local variables
            String p1Id = addPlayer1Id.getText().toUpperCase();
            String p2Id = addPlayer2Id.getText().toUpperCase();
            int matchDate = Integer.parseInt( addDate.getText() );
            String tournamnentName = addTournament.getText().toUpperCase();
            String matchScore = addScore.getText().toUpperCase();
            
            //Split up the date
            int dateYear = matchDate/10000;        //20151012/10000 = 2015
            int dateMonth = (matchDate%10000)/100; //20151012%10000 = 1012/100 = 10
            int dateDay = matchDate%100;           //20151012%100 = 12
            
            //Insert tennis match into tdb
            try{
               tdb.insertMatch( p1Id, p2Id, dateYear, dateMonth, dateDay, tournamnentName, matchScore);
            }
            catch( TennisDatabaseException matchException ){ 
               System.out.print("Could not insert match.\nMake sure both player's are in the database and the match score is valid.");
            }
      } );
      
      //EXPORT TO FILE BUTTON
      final Button exportButton = new Button( "Export To File" );
      exportButton.setOnAction(
         ( ActionEvent e ) -> { 
            tdb.exportToFile( "export.txt" );
      });
      
      //RESET DB BUTTON
      final Button btnReset = new Button( "Reset Database" );
      btnReset.setOnAction(
         ( ActionEvent e ) -> {
            tdb.reset();
       }
       );
       
      //IMPORT FILE BUTTON
      final Button btnImport = new Button( "Import From File" );
      btnImport.setOnAction(
         ( ActionEvent e ) -> {
            tdb.importFromFile("export.txt");
       }
       );
      
      //INSERT PLAYER HBOX SETTINGS
      HBox insertPlayerHB = new HBox();
      insertPlayerHB.getChildren().addAll(addId, addFirstName, addLastName, addBirthYear, addBirthCountry, addPlayerButton );
      insertPlayerHB.setSpacing( 3 );
      //REMOVE PLAYER HBOX SETTINGS
      HBox removePlayerHB = new HBox();
      removePlayerHB.getChildren().addAll(removePlayer, removePlayerButton, btnReset, btnImport);
      removePlayerHB.setSpacing( 3 );
      //INSERT MATCH HBOX SETTINGS 
      HBox insertMatchHB = new HBox();
      insertMatchHB.getChildren().addAll(addPlayer1Id, addPlayer2Id, addDate, addTournament, addScore, addMatchButton );
      insertMatchHB.setSpacing( 3 );
      
      //VERTICAL BOX FOR THE PLAYERS 
      final VBox playersVBox = new VBox();
      playersVBox.setSpacing( 5 ); 
      playersVBox.getChildren().addAll( playerLabel, playersTable, insertPlayerHB, removePlayerHB );
      //VERTICAL BOX FOR THE MATCHES
      final VBox matchesVBox = new VBox();
      matchesVBox.setSpacing( 5 ); 
      matchesVBox.getChildren().addAll( matchesLabel, matchesTable, insertMatchHB, exportButton );
      //HORRIZONTAL BOX FOR THE SCENE 
      HBox sceneHB = new HBox(); // ...
      sceneHB.setPadding( new Insets( 10, 0, 0, 10 ) );
      sceneHB.getChildren().addAll( playersVBox, matchesVBox );
      sceneHB.setSpacing( 10 );
      //SHOW SCENE 
      ( (Group) scene.getRoot() ).getChildren().add( sceneHB );
      stage.setScene( scene );
      stage.show();
   }
   
   // main method uses do while loop and switch statement to control menu options.
   public static void main(String [] args) {  
      launch( args ); 
   }  
}