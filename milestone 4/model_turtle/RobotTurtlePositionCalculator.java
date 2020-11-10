package model_turtle;

//Marcia Gallant
//this class has the responsibility of finding out what the next position or direction is for the turtle
public class RobotTurtlePositionCalculator implements DirectionCalculator, PositionCalculator
{
	private int xPos, yPos, direction;
	
	public RobotTurtlePositionCalculator(int x, int y, int d)
	{
		xPos = x;
		yPos = y;
		direction = d;
	}
	public RobotTurtlePositionCalculator(int d)
	{
		direction = d;
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
	//corrects direction value
	  public int directionShiftRight()
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
		  return direction;
		  //System.out.println("shift right");
	  }
	  
	  //corrects direction value
	  public int directionShiftLeft()
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
		  return direction;
	  }

}