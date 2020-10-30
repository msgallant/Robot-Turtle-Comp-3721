package model;

/* 
 * COMP 3721 - Object Oriented Design and Methodology
 * Term Project - Milestone 3
 * Code Version 3
 * Marcia Gallant & Loryn Losier
 * October 30th, 2020
 */

public class RobotJewel extends Tile
{
  private int xPos, yPos;
  private String colour;
  
  public RobotJewel(int x, int y, String c)
  {
	super(x, y);
    this.setColour(c);
    this.setTileType("Robot Jewel");
  }
  public String getJewelColour()
  {
	  return colour;
  }

}