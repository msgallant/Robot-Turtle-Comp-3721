public class GameBoardModel
{
  private int numPlayers;
  private String message;
  private ArrayList<RobotTurtle> listOfTurtles = new ArrayList<RobotTurtle>();
  public GameBoardModel(int players)
  {
    numPlayers = players;
    
  }
  public static void createRobotTurtles()
  {
    if (numPlayers == 1)
    {
      listOfTurtles.add(
    }
    else if (numPlayers == 2)
    {
      listOfTurtles.add(
    }
    else if (numPlayers == 3)
    {
      listOfTurtles.add(
    }
    else
    {
      listOfTurtles.add(
    }
  }
}
