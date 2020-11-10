package model;
import java.util.ArrayList;
//Marcia Gallant
//this initializes the game, puts jewels and turtles on it itself, 
//can take user input to put stone walls and crates, keeps track of the robot turtles, keeps track of whose 
//turn and keeps track of who finishes the game and when the game is completely finished.
//It also has the moveTurtle method which moves the given robot turtle depending on what card it is also given
//it updates the robot turtle x,y coordinates and gameboard accordingly if the turtle was able to move.

import model_gameboard.CratePositionUpdater;
import model_gameboard.GameBoard;
import model_gameboard.GameBoardMoveChecker;
import model_gameboard.RobotTurtlePositionUpdater;
import model_tiles.CratePositionCalculator;
import model_tiles.Tile;
import model_turtle.RobotTurtle;
import model_turtle.RobotTurtlePositionCalculator;
public class GameBoardModel
{
  private static int numPlayers = 1; //default
  private static int numCards = 4;
  private int GAME_BOARD_DIMENSION = 8;
  private static int[] JEWEL_ONE_POSITION = {3, 3};
  private static int[] JEWEL_TWO_POSITION = {4, 4};
  private static int[] JEWEL_THREE_POSITION = {4, 3};
  private static int[] JEWEL_FOUR_POSITION = {3, 4};
  
  private static String JEWEL_ONE_COLOUR = "Blue";
  private static String JEWEL_TWO_COLOUR = "Green";
  private static String JEWEL_THREE_COLOUR = "Purple";
  private static String JEWEL_FOUR_COLOUR = "Red"; 
  
  private static int[] TURTLE_ONE_POSITION = {0,0,3};
  private static int[] TURTLE_TWO_POSITION = {7,7,1};
  private static int[] TURTLE_THREE_POSITION = {7,0,4};
  private static int[] TURTLE_FOUR_POSITION = {0,7,2};
  
  private static String TURTLE_ONE_NAME = "Beep";
  private static String TURTLE_TWO_NAME = "Pangle";
  private static String TURTLE_THREE_NAME = "Dot";
  private static String TURTLE_FOUR_NAME = "Pi";
  
  private static String TURTLE_ONE_COLOUR = "Blue";
  private static String TURTLE_TWO_COLOUR = "Green";
  private static String TURTLE_THREE_COLOUR = "Purple";
  private static String TURTLE_FOUR_COLOUR = "Red";
  
  //consists of all turtle colours
  private String[] colours = {TURTLE_ONE_COLOUR, TURTLE_TWO_COLOUR, TURTLE_THREE_COLOUR, TURTLE_FOUR_COLOUR};
  
  private static String[] cardTypes = {"Turn Left", "Step Forward", "Turn Right", "Bug"};
  private static String[] cardColours = {"Yellow", "Blue", "Purple", "Brown"};
  
  //things the view class might have to draw. Order cannot be changed.
  private static String[] tilePossibilities = {"Robot Jewel", "Stone Wall", "Crate", "Robot Turtle"};
  
  int[] invalidXPos;// = {-1, -1, -1, -1, -1, -1, -1, -1};
  int[] invalidYPos; // = {-1, -1, -1, -1, -1, -1, -1, -1};
  //private int maxStoneWalls = 1;
  //private int maxCrates = 1;
	
  private static ArrayList<RobotTurtle> listOfTurtles = new ArrayList<RobotTurtle>();
  //private static RobotTurtle[] RTList = {null, null, null, null};
  private static Card[] cardDeck = new Card[numCards];
  
  private static GameBoard gameBoard;
  private int MAX_NUM_STONEWALLS = 2;
  private int MAX_NUM_CRATES = 2;
  private static Player currentPlayer;
  private State currentState;
  private static boolean[] playersPlaying = {false, false, false, false}; 
  
  public GameBoardModel(int players)
  {
    numPlayers = players;
    currentState = currentState.PLAYING;
    currentPlayer = currentPlayer.PLAYER_ONE;
    gameBoard = new GameBoard(GAME_BOARD_DIMENSION);
    initPlayers(); //initializing all players
    initGame(); //initializing board game
    
  }
  public boolean addStoneWall(int x, int y)
  {
	  if (gameBoard.checkIfObjectPresent(x,y)) //if there's an object already on this tile
	  {
		  return false;
	  }
	  gameBoard.addStoneWall(x, y);
	  return true; //successfully placed a stonewall
  }
  public boolean addCrate(int x, int y)
  {
	  if (gameBoard.checkIfObjectPresent(x,y)) //if there's an object already on this tile
	  {
		  return false;
	  }
	  
	  gameBoard.addCrate(x, y);
	  return true; //successfully placed a crate
  }
  
  
  public ArrayList<RobotTurtle> getListOfRobotTurtles()
  {
	  return listOfTurtles;
  }
  public Card[] getDeckOfCards()
  {
	  return cardDeck;
  }
  public GameBoard getGameBoard()
  {
	  return gameBoard;
  }
  public State getCurrentState()
  {
	  return currentState;
  }
  public Player getCurrentPlayer()
  {
	  return currentPlayer;
  }
  public Tile[][] getTileList()
 {
	 return gameBoard.getTileList();
 }
  public int getGameBoardDimension()
 {
 	return GAME_BOARD_DIMENSION;
 }
  public int[] getInvalidXPositions()
  {
	  return invalidXPos;
  }
  public int[] getInvalidYPositions()
  {
	  return invalidYPos;
  }
  public int getMaxCrates()
  {
	  return MAX_NUM_CRATES;
  }
  public int getMaxStoneWalls()
  {
	  return MAX_NUM_STONEWALLS;
  }
  public String[] getCardTypes()
  {
	  return cardTypes;
  }
  public String[] getTilePosibilities()
  {
	  return tilePossibilities;
  }
  public String[] getColours()
  {
	  return colours;
  }
  protected void initPlayers()
  {
	  for (int i =0; i < numPlayers; i++)
	  {
		  playersPlaying[i] = true;
		 
	  }

  }
  protected void initGame()
  {
	  createRobotTurtles();
	  createRobotJewels();
	  createCardDeck();
	  InvalidPositionCalculator invalidCalc = new InvalidPositionCalculator(numPlayers);
	  int[][] arr = invalidCalc.findInvalidPositions(); //finds tiles with jewels and turtles on them
	  //then stores their coordinates in invalidXpos and invalidYpos
	  invalidXPos = arr[0];
	  invalidYPos = arr[1];
	  
  }
  
  

  protected static void createRobotTurtles()
  { 
	  RobotTurtleCreator rtc = new RobotTurtleCreator(numPlayers, gameBoard);
	  listOfTurtles = rtc.createRobotTurtles();

  }
  protected static void switchPlayer()
  {
	  
	  PlayerSwitcher ps = new PlayerSwitcher(currentPlayer, numPlayers, playersPlaying);
	  currentPlayer = ps.switchPlayer();
  }
  
  protected static void createCardDeck()
  {
	CardDeckCreator cdc = new CardDeckCreator();
	cardDeck = cdc.createCardDeck();
	
  }
  protected static void createRobotJewels()
  {
    RobotJewelCreator jewelCreator = new RobotJewelCreator(numPlayers, gameBoard);
    jewelCreator.createRobotJewels();
    
  }
  
  public boolean moveTurtle(RobotTurtle rt, Card c)
  {
	  ObjectMover mover = new ObjectMover(cardDeck, gameBoard);
	  boolean turtleMoved = mover.moveTurtle(rt, c); //returns if turtle has been successfully moved or not
	  updatePlayersStatusInGame(rt); //check if won, then checks if all players one, then if not all players fin, switch turn
	  return turtleMoved;
	  
  }
  
  //updates which players are currently in the game
  protected void updatePlayersStatusInGame(RobotTurtle rt)
  {
	  //checks if this robot
	  WinChecker wc = new WinChecker(gameBoard, playersPlaying, currentPlayer);
	  playersPlaying = wc.checkIfWon(rt); //it returns updated boolean[] containing true values in indexes where the player is still playing
	  
	  //it returns the current state which is either playing if not all players are finished
	  //or all players fin which means they have all won.
	  currentState = wc.checkIfAllPlayersFinished(numPlayers); 
	  
	  if (currentState == State.PLAYING) //game is still going on
	  {
		  switchPlayer();
	  }
	 /* else //game finished
	  {
		  currentPlayer = currentPlayer.NULL;
	  }*/
  }


  
}
