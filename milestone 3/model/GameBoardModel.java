package model;
import java.util.ArrayList;

/* 
 * COMP 3721 - Object Oriented Design and Methodology
 * Term Project - Milestone 3
 * Code Version 3
 * Marcia Gallant & Loryn Losier
 * October 30th, 2020
 */



//Marcia Gallant, this initializes the game, puts jewels and turtles on it itself, asks foruser to put stone walls and crates, keeps track of whose 
//turn and keeps track of who finishes the game and when the game is completely finished.
public class GameBoardModel
{
  private static int numPlayers = 1; //default
  private static int numCards = 4;
  private int GAME_BOARD_DIMENSION = 8;
  private static int[] JEWEL_ONE_POSITION = {4, 3};
  private static int[] JEWEL_TWO_POSITION = {3, 3};
  private static int[] JEWEL_THREE_POSITION = {3, 4};
  private static int[] JEWEL_FOUR_POSITION = {4, 4};
  
  private static String JEWEL_ONE_COLOUR = "Blue";
  private static String JEWEL_TWO_COLOUR = "Green";
  private static String JEWEL_THREE_COLOUR = "Purple";
  private static String JEWEL_FOUR_COLOUR = "Red";
   
  private static int[] TURTLE_ONE_POSITION = {7,0,4};
  private static int[] TURTLE_TWO_POSITION = {0,0,3};
  private static int[] TURTLE_THREE_POSITION = {0,7,2};
  private static int[] TURTLE_FOUR_POSITION = {7,7,1};
  
  private static String TURTLE_ONE_NAME = "Beep";
  private static String TURTLE_TWO_NAME = "Pangle";
  private static String TURTLE_THREE_NAME = "Dot";
  private static String TURTLE_FOUR_NAME = "Pi";
  
  private static String TURTLE_ONE_COLOUR = "Blue";
  private static String TURTLE_TWO_COLOUR = "Green";
  private static String TURTLE_THREE_COLOUR = "Purple";
  private static String TURTLE_FOUR_COLOUR = "Red";

  private static ArrayList<RobotTurtle> listOfTurtles = new ArrayList<RobotTurtle>();
  private static RobotTurtle[] RTList = {null, null, null, null};
  private static Card[] cardDeck = new Card[numCards];
  
  private int[] previousPosition;
  private Card previousCard;
  private static GameBoard gameBoard = new GameBoard();
  private int MAX_NUM_STONEWALLS = 20;
  private int MAX_NUM_CRATES = 8;
  private static Player currentPlayer;
  private State currentState;
  private static boolean[] playersPlaying = {false, false, false, false}; 
  
  public GameBoardModel(int players)
  {
    numPlayers = players;
    currentState = currentState.PLAYING;
    currentPlayer = currentPlayer.PLAYER_ONE;
    initPlayers(); //initializing all players
    initGame(); //initializing board game
    
  }
  public void initPlayers()
  {
	  for (int i =0; i < numPlayers; i++)
	  {
		  playersPlaying[i] = true;
		 
	  }

  }
  public void initGame()
  {
	  createRobotTurtles();
	  createRobotJewels();
	  createCardDeck();
	  
  }
  public static void createRobotTurtles()
  { 

    if (numPlayers >= 1)
    {
      //int x, int y, int d, String n, String c
      RobotTurtle t = new RobotTurtle(TURTLE_ONE_POSITION[0],TURTLE_ONE_POSITION[1],TURTLE_ONE_POSITION[2],TURTLE_ONE_NAME, TURTLE_ONE_COLOUR);
      listOfTurtles.add(t);
      gameBoard.addRobotTurtle(TURTLE_ONE_POSITION, t);
    }
    
    if (numPlayers >= 2)
    {
    	RobotTurtle t = new RobotTurtle(TURTLE_TWO_POSITION[0],TURTLE_TWO_POSITION[1],TURTLE_TWO_POSITION[2],TURTLE_TWO_NAME, TURTLE_TWO_COLOUR);
    	listOfTurtles.add(t);
        gameBoard.addRobotTurtle(TURTLE_TWO_POSITION, t);
    }
    
    if (numPlayers >= 3)
    {
    	RobotTurtle t = new RobotTurtle(TURTLE_THREE_POSITION[0],TURTLE_THREE_POSITION[1],TURTLE_THREE_POSITION[2],TURTLE_THREE_NAME, TURTLE_THREE_COLOUR);
    	listOfTurtles.add(t);
        gameBoard.addRobotTurtle(TURTLE_THREE_POSITION, t);
    }
    
    if (numPlayers >= 4)
    {
    	RobotTurtle t = new RobotTurtle(TURTLE_FOUR_POSITION[0],TURTLE_FOUR_POSITION[1],TURTLE_FOUR_POSITION[2],TURTLE_FOUR_NAME, TURTLE_FOUR_COLOUR);
    	listOfTurtles.add(t);
        gameBoard.addRobotTurtle(TURTLE_FOUR_POSITION, t);
    }
    

  }
  public static void switchPlayer()
  {
	  
	  if (numPlayers > 1)
	  {
		  if (currentPlayer == currentPlayer.PLAYER_ONE)
		  {
			  int next = findNextPlayer(0);
			  if (next == 1) //player 2
			  {
				  currentPlayer = currentPlayer.PLAYER_TWO;
			  }
			  else if (next == 2) //player 3
			  {
				  currentPlayer = currentPlayer.PLAYER_THREE;
			  }
			  else if (next == 3) //player 4
			  {
				  currentPlayer = currentPlayer.PLAYER_FOUR;
			  }
			  //else current player doesn't change
		  }
		  else if (currentPlayer == currentPlayer.PLAYER_TWO)
		  {
			  
			  int next = findNextPlayer(1);
			  if (next == 0) 
			  {		
				  currentPlayer = currentPlayer.PLAYER_ONE;
			  }
			  else if (next == 2) 
			  {
				  currentPlayer = currentPlayer.PLAYER_THREE;
			  }
			  else if (next == 3)
			  {
				  currentPlayer = currentPlayer.PLAYER_FOUR;
			  }
		  }
		  else if (currentPlayer == currentPlayer.PLAYER_THREE)
		  {
			  int next = findNextPlayer(2);
			  if (next == 1)
			  {
				  currentPlayer = currentPlayer.PLAYER_TWO;
			  }
			  else if (next == 0) 
			  {
				  currentPlayer = currentPlayer.PLAYER_ONE;
			  }
			  else if (next == 3)
			  {
				  currentPlayer = currentPlayer.PLAYER_FOUR;
			  }
		  }
		  else if (currentPlayer == currentPlayer.PLAYER_FOUR)
		  {
			  int next = findNextPlayer(3);
			  if (next == 1) 
			  {
				  currentPlayer = currentPlayer.PLAYER_TWO;
			  }
			  else if (next == 2) 
			  {
				  currentPlayer = currentPlayer.PLAYER_THREE;
			  }
			  else if (next == 0)
			  {
				  currentPlayer = currentPlayer.PLAYER_ONE;
			  }
		  }
	  }
  }
  public static int findNextPlayer(int curPlayerIndex)
  {
	  //System.out.println(playersPlaying[0] + " " + playersPlaying[1] + playersPlaying[2] + playersPlaying[3]);
	  int nextPlayerIndex = curPlayerIndex + 1;
	  for (int i =0; i< 4; i++)
	  {
		  
		  
		  if (nextPlayerIndex == 3)
		  {
			  nextPlayerIndex = 0;
		  }
		  if(playersPlaying[nextPlayerIndex] == true)
		  {
			  return nextPlayerIndex;
		  }
		  nextPlayerIndex = nextPlayerIndex + 1;
		  
		  //System.out.println("next: " +nextPlayerIndex + " " + playersPlaying[nextPlayerIndex] + " cur: " + curPlayerIndex);
	  }
	  return -1; //if no next player, all finished
  }
  public static void createCardDeck()
  {
    Card stepForward = new Card("Step Forward", "Blue");
    Card turnLeft = new Card("Turn Left", "Yellow");
    Card turnRight = new Card("Turn Right", "Purple");
    Card bug = new Card("Bug", "Brown");
    cardDeck[0] = turnLeft;
    cardDeck[1] = stepForward;
    cardDeck[2] = turnRight;
    cardDeck[3] = bug;
  }
  public static void createRobotJewels()
  {
    
     if (numPlayers >= 1)
    {
      //int x, int y, String c
      gameBoard.addRobotJewel(JEWEL_ONE_POSITION, JEWEL_ONE_COLOUR);

    }
    if (numPlayers >= 2)
    {
    	gameBoard.addRobotJewel(JEWEL_TWO_POSITION, JEWEL_TWO_COLOUR);
    }
    if (numPlayers >= 3)
    {
    	gameBoard.addRobotJewel(JEWEL_THREE_POSITION, JEWEL_THREE_COLOUR);
    }
    if (numPlayers >= 4)
    {
    	gameBoard.addRobotJewel(JEWEL_FOUR_POSITION, JEWEL_FOUR_COLOUR);
    }
    
  }
  
  public boolean moveTurtle(RobotTurtle rt, Card c)
  {
	  //need to pass to moveTurtle in turtle class and gameboard
	  //need to check if able to move
	  int[] turtleOldPos = {rt.getXPos(), rt.getYPos()};
	  boolean turtleMoved = true;
	  if (!c.equals(cardDeck[3]))//if it's not the bug card
	  {
		  previousPosition = turtleOldPos; //stores position in case of bug card
		  previousCard = c;
	  }
	  //updates the turtles position in the object itself
	  if (c.equals(cardDeck[1])) //if card = step forward
	  {

		  if (rt.checkIfTileAheadExists()) //making sure robot turtle isnt trying to go off the gameboard
		  {
			  if (gameBoard.checkIfTurtleCanMove(rt)) //checking if obstacle blocking turtle's path
			  {
				  int turtleNewPos[] = rt.calculateNewPosition();
				  int direction = rt.getDirection();
				  if (gameBoard.checkIfTurtlePushedACrate(turtleNewPos[0], turtleNewPos[1]))
				  {
					  	gameBoard.updateCratePositionOnBoard(turtleNewPos, direction); //crate's old position and direction pushes are the parameters.
				  }
				  rt.updateTurtlePosition(); //update turtle x, y coordinates depending on what direction the turtle is facing
				  gameBoard.updateTurtlePositionOnBoard(turtleNewPos, turtleOldPos, rt); 
				  
				  
				  //update gameboard that new position is occupied with a turtle and old position is not 
				  //and stores the robot turtle that's on the tile in a Tile private variable and 
				  //sets the old tile robot turtle var to false
			  }
			  else
			  {
				  turtleMoved = false;
			  }
			                                                        
		  }
		  else
		  {
			  turtleMoved = false;
		  }
	  }
	  else if (c.equals(cardDeck[0]))// turn left card
	  {
		  rt.directionShiftLeft();
	  }
	  else if (c.equals(cardDeck[2])) //turn right card
	  {
		  
		  rt.directionShiftRight();
	  }
	  else // the bug card, //need to update turtle position in object + update gameboard
	  {
		  if (previousCard.equals(cardDeck[1])) //moves forward card
		  {
			  rt.setXPos(previousPosition[0]);
			  rt.setYPos(previousPosition[1]);
			  
			  gameBoard.updateTurtlePositionOnBoard(previousPosition, turtleOldPos, rt);
		  }
		  else if (previousCard.equals(cardDeck[0]))// turn left card
		  {
			  rt.directionShiftRight();//turn right into original position
		  }
		  else if (previousCard.equals(cardDeck[2])) //turn right card
		  {
			  rt.directionShiftLeft(); //turn left into original position
		  }
	  }
	  
	  checkIfWon(rt); //check if won, then checks if all players one, then if not all players fin, switch turn
	  return turtleMoved;
	  
	  
  }
  
  //checking if this robot turtle is on a jewel and is finished.
  public void checkIfWon(RobotTurtle rt)
  {
	  int x = rt.getXPos();
	  int y = rt.getYPos();
	  boolean playersFin = false;
	  Tile[][] tl = gameBoard.getTileList();
	  if (tl[x][y].getTileType() == "Robot Jewel")
	  {
		  if (currentPlayer == Player.PLAYER_ONE)
		  {
			  playersPlaying[0] = false;

		  }
		  else if (currentPlayer == Player.PLAYER_TWO)
		  {
			  playersPlaying[1] = false;

		  }
		  else if (currentPlayer == Player.PLAYER_THREE)
		  {
			  playersPlaying[2] = false;

		  }
		  else if (currentPlayer == Player.PLAYER_FOUR)
		  {
			  playersPlaying[3] = false;

		  }
		  
		  playersFin = checkIfAllPlayersFinished(); //change state & player if true
	  }
	  if (playersFin == false) //if not all players finished, continue game and switch to the next players turn
	  {
		  switchPlayer();
	  }
  }
  
  public boolean checkIfAllPlayersFinished()
  {
	  for (int i =0; i < numPlayers; i++)
	  {
		  if (playersPlaying[i] == true)
		  {
			  return false;
		  }
	  }
	  currentPlayer = currentPlayer.NULL;
	  currentState = currentState.ALL_PLAYERS_FIN;
	  return true;
  }


  public void addStoneWall(int x, int y)
  {
	  gameBoard.addStoneWall(x, y);
  }
  public void addCrate(int x, int y)
  {
	  gameBoard.addCrate(x, y);
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
  
}