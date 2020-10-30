package model;

/* 
 * COMP 3721 - Object Oriented Design and Methodology
 * Term Project - Milestone 3
 * Code Version 3
 * Marcia Gallant & Loryn Losier
 * October 30th, 2020
 */

public class StoneWall extends Tile
{

	public StoneWall(int x, int y) 
	{
		super(x, y);
		this.setSMovable(false);
		this.setNMovable(false);
		this.setWMovable(false);
		this.setEMovable(false);
		
		this.setTileType("Stone Wall");
	}
	
	
	
}