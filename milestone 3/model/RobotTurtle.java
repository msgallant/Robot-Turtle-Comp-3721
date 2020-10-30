package model;

/* 
 * COMP 3721 - Object Oriented Design and Methodology
 * Term Project - Milestone 3
 * Code Version 3
 * Marcia Gallant & Loryn Losier
 * October 30th, 2020
 */

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
  public boolean checkIfTileAheadExists()
  {
	  if (direction == 1 && yPos != 0) //if the turtle is not on an upper edge tile of the gameboard
	  {                                //the turtle can move north
		  return true;
	  }
	  else if (direction == 2 && xPos != 7) //if turtle not on right edge of gameboard, can move east
	  {
		  return true;
	  }
	  else if (direction == 3 && yPos != 7) // not on bottom edge tile
	  {
		  return true;
	  }
	  else if (direction == 4 && xPos != 0) //not on left edge tile
	  {
		  return true;
	  }
	  return false; //if none of the if statements are true then, turtle trying to move off gameboard
  }
  public int[] calculateNewPosition()
  {
	  int x = xPos;
	  int y = yPos;
	  if (direction == 1)
	  {
		  y -= 1;
	  }
	  else if (direction == 2)
	  {
		  x += 1;
	  }
	  else if (direction == 3)
	  {
		  y += 1;
	  }
	  else if (direction == 4)
	  {
		  x -= 1;
	  }
	  int[] newPos = {x, y};
	  return newPos;
  }
  //corects x and y position coordinates after moving forward, this method is only called if turtle moves forward!
  public void updateTurtlePosition()
  {
	  int[] pos = calculateNewPosition();
	  xPos = pos[0];
	  yPos = pos[1];
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
	  //System.out.println("shift right");
  }
  
  //corects direction value
  public void directionShiftLeft()
  {
	  //System.out.println("shift left");
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