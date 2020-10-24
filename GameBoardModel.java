import java.util.ArrayList;

public class GameBoardModel
{
  private static int numPlayers;
  private static int numCards = 4;
  private static int[] JEWEL_ONE_POSITION = {4, 3};
  private static int[] JEWEL_TWO_POSITION = {3, 4};
  private static int[] JEWEL_THREE_POSITION = {3, 3};
  private static int[] JEWEL_FOUR_POSITION = {4, 4};
  private static String JEWEL_ONE_COLOUR = "Blue";
  private static String JEWEL_TWO_COLOUR = "Green";
  private static String JEWEL_THREE_COLOUR = "Purple";
  private static String JEWEL_FOUR_COLOUR = "Red";
  
  private static int[] TURTLE_ONE_POSITION = {0,0,3};
  private static int[] TURTLE_TWO_POSITION = {0,7,4};
  private static int[] TURTLE_THREE_POSITION = {7,0,2};
  private static int[] TURTLE_FOUR_POSITION = {7,7,1};
  
  private static String TURTLE_ONE_NAME = "Beep";
  private static String TURTLE_TWO_NAME = "Pangle";
  private static String TURTLE_THREE_NAME = "Dot";
  private static String TURTLE_FOUR_NAME = "Pi";
  
  private static String TURTLE_ONE_COLOUR = "Blue";
  private static String TURTLE_TWO_COLOUR = "Green";
  private static String TURTLE_THREE_COLOUR = "Purple";
  private static String TURTLE_FOUR_COLOUR = "Red";
  

  private String message;
  private static ArrayList<RobotTurtle> listOfTurtles = new ArrayList<RobotTurtle>();
  private static Card[] cardDeck = new Card[numCards];
  
  private int[] previousPosition;
  private Card previousCard;
  private static GameBoard gameBoard = new GameBoard();
  
  public GameBoardModel(int players)
  {
    numPlayers = players;
    
  }
  public static void createRobotTurtles()
  { 
    if (numPlayers >= 1)
    {
      //int x, int y, int d, String n, String c
      RobotTurtle t = new RobotTurtle(TURTLE_ONE_POSITION[0],TURTLE_ONE_POSITION[1],TURTLE_ONE_POSITION[2],TURTLE_ONE_NAME, TURTLE_ONE_COLOUR);
      listOfTurtles.add(t);
      gameBoard.addTurtle(TURTLE_ONE_POSITION);
    }
    if (numPlayers >= 2)
    {
    	RobotTurtle t = new RobotTurtle(TURTLE_TWO_POSITION[0],TURTLE_TWO_POSITION[1],TURTLE_TWO_POSITION[2],TURTLE_TWO_NAME, TURTLE_TWO_COLOUR);
        listOfTurtles.add(t);
        gameBoard.addTurtle(TURTLE_TWO_POSITION);
    }
    if (numPlayers >= 3)
    {
    	RobotTurtle t = new RobotTurtle(TURTLE_THREE_POSITION[0],TURTLE_THREE_POSITION[1],TURTLE_THREE_POSITION[2],TURTLE_THREE_NAME, TURTLE_THREE_COLOUR);
        listOfTurtles.add(t);
        gameBoard.addTurtle(TURTLE_THREE_POSITION);
    }
    if (numPlayers >= 4)
    {
    	RobotTurtle t = new RobotTurtle(TURTLE_FOUR_POSITION[0],TURTLE_FOUR_POSITION[1],TURTLE_FOUR_POSITION[2],TURTLE_FOUR_NAME, TURTLE_FOUR_COLOUR);
        listOfTurtles.add(t);
        gameBoard.addTurtle(TURTLE_FOUR_POSITION);
    }

  }
  public static void createCardDeck()
  {
    Card stepForward = new Card("Step Forward", "Blue");
    Card turnLeft = new Card("Turn Left", "Yellow");
    Card turnRight = new Card("Turn Right", "Purple");
    Card bug = new Card("Bug", "Brown");
    cardDeck[0] = stepForward;
    cardDeck[1] = turnLeft;
    cardDeck[2] = turnRight;
    cardDeck[3] = bug;
  }
  public static void createRobotJewels()
  {
    
     if (numPlayers >= 1)
    {
      //int x, int y, String c
      gameBoard.addJewel(JEWEL_ONE_POSITION, JEWEL_ONE_COLOUR);

    }
    if (numPlayers >= 2)
    {
    	gameBoard.addJewel(JEWEL_TWO_POSITION, JEWEL_TWO_COLOUR);
    }
    if (numPlayers >= 3)
    {
    	gameBoard.addJewel(JEWEL_THREE_POSITION, JEWEL_THREE_COLOUR);
    }
    if (numPlayers >= 4)
    {
    	gameBoard.addJewel(JEWEL_FOUR_POSITION, JEWEL_FOUR_COLOUR);
    }
    
  }
  
  public void moveTurtle(RobotTurtle rt, Card c)
  {
	  //need to pass to moveTurtle in turtle class and gameboard
	  //need to check if able to move
	  int[] oldPos = {rt.getXPos(), rt.getYPos()};
	  previousPosition = oldPos; //stores position in case of bug card
	  previousCard = c;
	  //updates the turtles position in the object itself
	  if (c.equals(cardDeck[0])) //if card = step forward
	  {
		  rt.updateTurtlePosition(); //update turtle x, y coordinates depending on what direction the turtle is facing
		  int[] newPos = {rt.getXPos(), rt.getYPos()}; 
		  gameBoard.updateTurtlePositionOnBoard(newPos, oldPos); //update gameboard that new position is occupied with a turtle and old position is not
	  }
	  else if (c.equals(cardDeck[1]))// turn left card
	  {
		  rt.directionShiftLeft();
	  }
	  else if (c.equals(cardDeck[2])) //turn right card
	  {
		  rt.directionShiftRight();
	  }
	  else // the bug card, //need to update turtle position in object + update gameboard
	  {
		  if (previousCard.equals(cardDeck[0])) //moves forward card
		  {
			  rt.setXPos(previousPosition[0]);
			  rt.setYPos(previousPosition[1]);
			  
			  gameBoard.updateTurtlePositionOnBoard(previousPosition, oldPos);
		  }
		  else if (previousCard.equals(cardDeck[1]))// turn left card
		  {
			  rt.directionShiftRight();//turn right into original position
		  }
		  else if (previousCard.equals(cardDeck[2])) //turn right card
		  {
			  rt.directionShiftLeft(); //turn left into original position
		  }
	  }
	  
	  
	  
	  
  }
  
}

