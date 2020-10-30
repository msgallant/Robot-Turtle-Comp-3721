package model;
import java.util.ArrayList;
//Marcia Gallant, Loryn Losier,
//this initializes the game, puts jewels and turtles on it itself, 
//can take user input to put stone walls and crates, keeps track of the robot turtles, keeps track of whose 
//turn and keeps track of who finishes the game and when the game is completely finished.
//It also has the moveTurtle method which moves the given robot turtle depending on what card it is also given
//it updates the robot turtle x,y coordinates and gameboard accordingly if the turtle was able to move.

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
  
  private int[] previousPosition;
  private Card previousCard;
  private static GameBoard gameBoard;
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
    gameBoard = new GameBoard(GAME_BOARD_DIMENSION);
    initPlayers(); //initializing all players
    initGame(); //initializing board game
    
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
	  return MAX_NUM_CRATES = 8;
  }
  public int getMaxStoneWalls()
  {
	  return MAX_NUM_STONEWALLS = 20;
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
	  findInvalidPositions(); //finds tiles with jewels and turtles on them
	  //then stores their coordinates in invalidXpos and invalidYpos
	  
  }
  
  //finds positions with jewels and turtles because crates and stone walls cannot be put here
  protected void findInvalidPositions()
  {
	  int index = 0;
	  
	  invalidXPos = new int[numPlayers*2]; //since each player needs a turtle and a jewel
	  invalidYPos = new int[numPlayers*2];
	  
	  if (numPlayers >= 1)
	  {
		  invalidXPos[index] = TURTLE_ONE_POSITION[0];
		  invalidYPos[index] = TURTLE_ONE_POSITION[1];
		  invalidXPos[index+1] = JEWEL_ONE_POSITION[0];
		  invalidYPos[index+1] = JEWEL_ONE_POSITION[1];
		  index = index + 2;
	  }
	  
	  if (numPlayers >= 2)
	  {
		  invalidXPos[index] = TURTLE_TWO_POSITION[0];
		  invalidYPos[index] = TURTLE_TWO_POSITION[1];
		  invalidXPos[index+1] = JEWEL_TWO_POSITION[0];
		  invalidYPos[index+1] = JEWEL_TWO_POSITION[1];
		  index = index + 2;
	  }
	  
	  if (numPlayers >= 3)
	  {
		  invalidXPos[index] = TURTLE_THREE_POSITION[0];
		  invalidYPos[index] = TURTLE_THREE_POSITION[1];
		  invalidXPos[index+1] = JEWEL_THREE_POSITION[0];
		  invalidYPos[index+1] = JEWEL_THREE_POSITION[1];
		  index = index + 2;
	  }
	  
	  if (numPlayers >= 4)
	  {
		  invalidXPos[index] = TURTLE_FOUR_POSITION[0];
		  invalidYPos[index] = TURTLE_FOUR_POSITION[1];
		  invalidXPos[index+1] = JEWEL_FOUR_POSITION[0];
		  invalidYPos[index+1] = JEWEL_FOUR_POSITION[1];
		  //index = index + 2;
	  }
	  
	  
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
  protected static void switchPlayer()
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
  protected static int findNextPlayer(int curPlayerIndex)
  {
	  //System.out.println(playersPlaying[0] + " " + playersPlaying[1] + playersPlaying[2] + playersPlaying[3]);
	  
	  int nextPlayerIndex = curPlayerIndex + 1;
	  if (curPlayerIndex == 3)
	  {
		  nextPlayerIndex = 0;
	  }

	  for (int i =0; i< 4; i++)
	  {
		  
		  if(playersPlaying[nextPlayerIndex] == true) //if the player with this index is still playing
		  {
			  return nextPlayerIndex;
		  }
		  //if player with nextPlayerIndex already won game, check if next player is still playing
		  if (nextPlayerIndex == 3) //next player after player 4 is player 1
		  {
			  nextPlayerIndex = 0;
		  }
		  else
		  {
			  nextPlayerIndex = nextPlayerIndex + 1;
		  }
		  
		  //System.out.println("next: " +nextPlayerIndex + " " + playersPlaying[nextPlayerIndex] + " cur: " + curPlayerIndex);
	  }
	  return -1; //if no next player, all finished
  }
  protected static void createCardDeck()
  {
	Card turnLeft = new Card(cardTypes[0], cardColours[0]);  
    Card stepForward = new Card(cardTypes[1], cardColours[1]);
    Card turnRight = new Card(cardTypes[2], cardColours[2]);
    Card bug = new Card(cardTypes[3], cardColours[3]);
    cardDeck[0] = turnLeft;
    cardDeck[1] = stepForward;
    cardDeck[2] = turnRight;
    cardDeck[3] = bug;
  }
  protected static void createRobotJewels()
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
  protected void checkIfWon(RobotTurtle rt)
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
  
  protected boolean checkIfAllPlayersFinished()
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


  
}
