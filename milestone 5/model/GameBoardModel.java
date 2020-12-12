package model;
import java.util.ArrayList;
//Marcia Gallant
//this initializes the game, puts jewels and turtles on it itself, 
//can take user input to put stone walls and crates, keeps track of the robot turtles, keeps track of whose 
//turn and keeps track of who finishes the game and when the game is completely finished.
//It also has the moveTurtle method which moves the given robot turtle depending on what card it is also given
//it updates the robot turtle x,y coordinates and gameboard accordingly if the turtle was able to move.

import controller.ControllerModelDataInterface;
import model_gameboard.GameBoard;
import model_tiles.Tile;
import model_turtle.RobotTurtle;
public class GameBoardModel implements ControllerModelDataInterface
{
  private static int numPlayers = 1; //default
  private static int numCards = 6;
  private int GAME_BOARD_DIMENSION = 8;
  private ArrayList<Card> functionFrog = new ArrayList<Card>();
  private ArrayList<Card> program = new ArrayList<Card>();
  private static String[] cardTypes;

  
  int[] invalidXPos;// = {-1, -1, -1, -1, -1, -1, -1, -1};
  int[] invalidYPos; // = {-1, -1, -1, -1, -1, -1, -1, -1};
  //private int maxStoneWalls = 1;
  //private int maxCrates = 1;
	
  private static ArrayList<RobotTurtle> listOfTurtles = new ArrayList<RobotTurtle>();
  private static Card[] cardDeck = new Card[numCards];
  
  private static GameBoard gameBoard;
  private int MAX_NUM_STONEWALLS = 2;
  private int MAX_NUM_CRATES = 2;
  private int MAX_NUM_ICEWALLS = 2;
  private int MAX_NUM_PORTALS = 2;
  private static Player currentPlayer;
  private State currentState;
  private static boolean[] playersPlaying = {false, false, false, false}; 
  private GameBoard oldGameBoard = null;
  private boolean playerWon = false;
  
  public GameBoardModel(int players)
  {
    numPlayers = players;
    currentState = State.PLAYING;
    currentPlayer = Player.PLAYER_ONE;
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
  public boolean addIceWall(int x, int y)
  {
	  if (gameBoard.checkIfObjectPresent(x,y)) //if there's an object already on this tile
	  {
		  return false;
	  }
	  
	  gameBoard.addIceWall(x, y);
	  return true; //successfully placed a crate
  }
  public boolean addPortal(int x, int y)
  {
	  if (gameBoard.checkIfObjectPresent(x,y)) //if there's an object already on this tile
	  {
		  return false;
	  }
	  
	  gameBoard.addPortal(x, y);
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
  public int getMaxIceWalls()
  {
	  return MAX_NUM_ICEWALLS;
  }
  public int getMaxPortals()
  {
	  return MAX_NUM_PORTALS;
  }
  public String[] getCardTypes()
  {
	  return cardTypes;
  }
  public String getCardType(Card c)
  {
	  return c.getCardType();
  }
  public ArrayList<Card> getProgram()
  {
	  return program;
  }
  public ArrayList<Card> getFunctionFrog()
  {
	  return functionFrog;
  }

  /*public String[] getColours()
  {
	  return colours;
  }*/
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
	  
	  new PlayerSwitcher(currentPlayer, numPlayers, playersPlaying);
	  currentPlayer = PlayerSwitcher.switchPlayer();
  }
  
  protected static void createCardDeck()
  {
	CardDeckCreator cdc = new CardDeckCreator();
	cardDeck = cdc.createCardDeck();
	cardTypes = cdc.getCardTypes();

  }
  protected static void createRobotJewels()
  {
    new RobotJewelCreator(numPlayers, gameBoard);
    RobotJewelCreator.createRobotJewels();
    
  }
  
  public boolean moveTurtle(RobotTurtle rt, Card c)
  {
	  ObjectMover mover = new ObjectMover(cardDeck, gameBoard);
	  boolean turtleMoved = mover.moveTurtle(rt, c); //returns if turtle has been successfully moved or not
	  return turtleMoved;
	  
  }
  public void addCardToFunctionFrog(Card c)
  {
	  functionFrog.add(c);
  }
  public void clearFunctionFrog()
  {
	  functionFrog.clear();
  }
  public void addCardToProgram(Card c)
  {
	  program.add(c);
  }
  public void clearProgram()
  {
	  program.clear();
  }
  public boolean writeProgram(RobotTurtle rt)
  {
	  oldGameBoard = new GameBoard(gameBoard); //deep copy of gameboard before moving turtle
	  boolean turtleMoved = false;
	  boolean temp;

	  for (int i =0; i < program.size(); i++)
	  {
		  if (program.get(i).getCardType() == "Function Frog")
		  {
			  for (int j = 0; j < functionFrog.size(); j++)
			  {
				  temp = moveTurtle(rt, functionFrog.get(j));
				  if (turtleMoved == false)
				  {
					  turtleMoved = checkIfTurtleMoved(temp);
				  }
			  }
		  }
		  else
		  {
			  temp = moveTurtle(rt, program.get(i));
			  if (turtleMoved == false)
			  {
				  turtleMoved = checkIfTurtleMoved(temp);
			  }
			  
		  }
		  
	  }
	  updatePlayersStatusInGame(rt); //check if won, then checks if all players one, then if not all players fin, switch turn
	  return turtleMoved;
  }
  public void reverseProgram()
  {
	  gameBoard.deepCopy(oldGameBoard);
	  listOfTurtles = gameBoard.getListOfRobotTurtles();
	  switchPlayer();

  }
  protected boolean checkIfTurtleMoved(boolean temp)
  {
	  if (temp == true)
	  {
		  return true;
	  }
	  return false;
  }
  
  public boolean checkIfPlayerWon(RobotTurtle rt)
  {
	  return playerWon;
  }
  //updates which players are currently in the game
  protected void updatePlayersStatusInGame(RobotTurtle rt)
  {
	  //checks if this robot
	  WinChecker wc = new WinChecker(gameBoard, playersPlaying, currentPlayer);
	  //it returns updated boolean[] containing true values in indexes where the player is still playing
	  playersPlaying = wc.updateWhoWon(rt); 
	  playerWon = wc.checkIfWon(rt);
	  //if current player won, update oldGameBoard to the current gameboard
	  if (playerWon == true)
	  {
		  oldGameBoard.deepCopy(gameBoard);
	  
	  
	  //it returns the current state which is either playing if not all players are finished
	  //or all players fin which means they have all won.
		  currentState = wc.checkIfAllPlayersFinished(numPlayers); 
	  
		  if (currentState == State.PLAYING) //game is still going on
		  {
			  switchPlayer();
		  }
	  }
	 /* else //game finished
	  {
		  currentPlayer = currentPlayer.NULL;
	  }*/
  }


  
}
