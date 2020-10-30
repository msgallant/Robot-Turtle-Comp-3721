package model;
//This class allows you to make a tile with a robot jewel on it and set its colour

public class RobotJewel extends Tile
{
  private int xPos, yPos;
  private String colour;
  
  public RobotJewel(int x, int y, String c)
  {
	super(x, y);
    this.setColour(c);
    colour = c;
    this.setTileType("Robot Jewel");
  }
  public String getJewelColour()
  {
	  return colour;
  }

}