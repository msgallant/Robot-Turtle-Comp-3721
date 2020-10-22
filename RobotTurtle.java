/*Comp 3721 - Object-Oriented Design and Methodology
* Milestone 3
* Due October 27th, 2020
* Marcia Gallant and Loryn Losier
*/
//for direction 1 represents north, 2 represents east, 3 represents south, 4 represents west
public class RobotTurtle
{
  private int yPos, xPos, direction; 
  private String name, colour;
  private boolean north, east, south, west;

  public RobotTurtle(int x, int y, int d, String n, String c)
  {
    xPos = x;
    yPos = y;
    direction = d;
    name = n;
    colour = c;
    
  
  }
 /* public setMovableNorth(boolean m)
  {
    north = m;
  }
  public setMovableEast(boolean m)
  {
    east = m;
  }
  public setMovableSouth(boolean m)
  {
    south = m;
  }
  public setMovableWest(boolean m)
  {
    west = m;
  }
  public getMovableNorth()
  {
    return north; 
  }
  public getMovableEast()
  {
    return east; 
  }
  public getMovableSouth()
  {
    return south;
  }
  public getMovableWest()
  {
    return west;
  }*/
}
