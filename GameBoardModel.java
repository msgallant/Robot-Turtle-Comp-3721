public class GameBoardModel
{
  private int numPlayers;
  private int numCards = 4;
  private String message;
  private ArrayList<RobotTurtle> listOfTurtles = new ArrayList<RobotTurtle>();
  private ArrayList<RobotJewel> listOfJewels = new ArrayList<RobotJewel>();
  private Card[] cardDeck = new Card[numCards];
  public GameBoardModel(int players)
  {
    numPlayers = players;
    
  }
  public static void createRobotTurtles()
  { 
    if (numPlayers >= 1)
    {
      //int x, int y, int d, String n, String c
      RobotTurtle beep = new RobotTurtle(0,0,3,"Beep", "Blue");
      listOfTurtles.add(beep);
    }
    if (numPlayers >= 2)
    {
      RobotTurtle pangle = new RobotTurtle(0,7,4,"Pangle", "Green");
      listOfTurtles.add(pangle);
    }
    if (numPlayers >= 3)
    {
      RobotTurtle dot = new RobotTurtle(7,0,2,"Dot", "Purple");
      listOfTurtles.add(dot);
    }
    if (numPlayers >= 4)
    {
      RobotTurtle pi = new RobotTurtle(7,7,1,"Pi", "Red");
      listOfTurtles.add(pi);
    }

  }
  public static void createCardDeck()
  {
    Card stepForward = new Card("Step Forward", "Blue");
    Card turnLeft = new Card("Turn Left", "Yellow");
    Card turnRight = new Card("Turn Right", "Purple");
    card bug = new Card("Bug", "Brown");
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
      RobotJewel r1 = new RobotJewel(5, 4, "Blue");
      listOfJewels.add(r1);
    }
    if (numPlayers >= 2)
    {
      RobotJewel r2 = new RobotJewel(4, 5, "Green");
      listOfJewels.add(r2);
    }
    if (numPlayers >= 3)
    {
      RobotJewel r3 = new RobotJewel(4, 4, "Purple");
      listOfJewels.add(r3);
    }
    if (numPlayers >= 4)
    {
      RobotJewel r4 = new RobotJewel(5, 5, "Red");
      listOfJewels.add(r4);
    }
    
  }
  
}

