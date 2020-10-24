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
  // direction: 1=north, 2=east, 3=south, 4 =west

  public RobotTurtle(int x, int y, int d, String n, String c)
  {
    xPos = x;
    yPos = y;
    direction = d;
    name = n;
    colour = c;
    
  
  }
  
  public void setXPos(int x)
  {
	  xPos = x;
  }
  public void setYPos(int y)
  {
	  yPos = y;
  }
  public void setDirection(int d)
  {
	  direction = d;
  }

  public int getXPos()
  {
	  return xPos;
  }
  public int getYPos()
  {
	  return yPos;
  }
  public int getDirection()
  {
	  return direction;
  }
  public String getName()
  {
	  return name;
  }
  public String getColour()
  {
	  return colour;
  }
  
  //corects x and y position coordinates after moving forward, this method is only called if turtle moves forward!
  public void updateTurtlePosition()
  {
	  if (direction == 1)
	  {
		  yPos -= 1;
	  }
	  else if (direction == 2)
	  {
		  xPos += 1;
	  }
	  else if (direction == 3)
	  {
		  yPos += 1;
	  }
	  else if (direction == 4)
	  {
		  xPos -= 1;
	  }
  }

  
  //corects direction value
  public void directionShiftRight()
  {
	  if (direction == 1)
	  {
		  direction = 2;
	  }
	  else if (direction == 2)
	  {
		  direction = 3;
	  }
	  else if (direction == 3)
	  {
		  direction = 4;
	  }
	  else if (direction == 4)
	  {
		  direction = 1;
	  }
  }
  
  //corects direction value
  public void directionShiftLeft()
  {
	  if (direction == 1)
	  {
		  direction = 4;
	  }
	  else if (direction == 2)
	  {
		  direction = 1;
	  }
	  else if (direction == 3)
	  {
		  direction = 2;
	  }
	  else if (direction == 4)
	  {
		  direction = 3;
	  }
  }
  
}
