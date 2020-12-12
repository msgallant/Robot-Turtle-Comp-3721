package model_turtle;
//this allows you to create a robot turtle object which is the players character.
//its responsibility is to keeps track of location, direction and colour
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
  public void deepCopy(RobotTurtle r)
  {
	  this.setDirection(r.getDirection());
	  this.setXPos(r.getXPos());
	  this.setYPos(r.getYPos());
	  this.setName(r.getName());
	  this.setColour(r.getColour());
  }
  public void setName(String n)
  {
	  name = n;
  }
  public void setColour(String c)
  {
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
  
  
}