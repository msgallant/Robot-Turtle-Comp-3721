package model;

/* 
 * COMP 3721 - Object Oriented Design and Methodology
 * Term Project - Milestone 3
 * Code Version 3
 * Marcia Gallant & Loryn Losier
 * October 30th, 2020
 */

//This class adds stone walls, crates, jewels and turtles to the game board and updates turtles position when they move.
public class GameBoard
{
	private Tile[][] tileList;
	private int GAME_BOARD_DIMENSION = 8;
	public GameBoard()
	{
		tileList = new Tile[GAME_BOARD_DIMENSION][GAME_BOARD_DIMENSION];
		createEmptyGameBoard();
	}
	
	//x and y are position on gameboard, valid coordinates are 0-7
	public Tile[][] addStoneWall(int x, int y)
	{
		StoneWall t = new StoneWall(x, y);
		tileList[x][y] = t;
		return tileList;
	}
	
	public Tile[][] addCrate(int x, int y)
	{
		Crate t = new Crate(x, y);
		tileList[x][y] =t;
		return tileList;
	}
	public Tile[][] addRobotJewel(int[] pos, String color)
	{

		RobotJewel r = new RobotJewel(pos[0], pos[1], color);
		tileList[pos[0]][pos[1]] = r;
		return tileList;
	}
	public Tile[][] addRobotTurtle(int[] pos, RobotTurtle rt)
	{
		tileList[pos[0]][pos[1]].setOccupied(true);
		tileList[pos[0]][pos[1]].setRobotTurtle(rt);
		return tileList;
	}
	public Tile[][] createEmptyGameBoard()
	{
		for (int row =0; row < GAME_BOARD_DIMENSION; row++ )
		{
			for (int col =0; col < GAME_BOARD_DIMENSION; col++)
			{
				Tile t = new Tile(row, col);
				tileList[row][col] = t;
			}
			
		}
		return tileList;
	}
	public boolean checkIfTurtleCanMove(RobotTurtle rt)
	{
		int rtDirection = rt.getDirection();
		int[] futurePos = rt.calculateNewPosition();
		boolean b = false;
		if (rtDirection == 1) //if robot facing north
		{
			//the robot is trying to move into a tile to its north
			//so check if turtles are able to move into that tile from that tile's south neighbour tile 
			//which would be the tile the turtle is currently on
			
			b = tileList[futurePos[0]][futurePos[1]].getSMovable();
		}
		else if (rtDirection == 2) //if robot facing east, check wMovable
		{	
			b = tileList[futurePos[0]][futurePos[1]].getWMovable();
		}
		else if (rtDirection == 3) //if robot facing south, check nMovable
		{			
			b = tileList[futurePos[0]][futurePos[1]].getNMovable();
		}
		else if (rtDirection == 4) //if robot facing west, check eMovable
		{
			b = tileList[futurePos[0]][futurePos[1]].getEMovable();
		}
		return b;
	}
	public boolean checkIfTurtlePushedACrate(int x, int y) //takes in tile that the turtle just moved into x and y coordinates
	{
		if (tileList[x][y].getTileType() == "Crate")
		{
			return true;
		}
		return false;
	}
	public void updateCratePositionOnBoard(int[] oldPos, int directionPushed)
	{
		int x = oldPos[0];
		int y = oldPos[1];
		
		if (directionPushed == 1)
		{
			y -= 1;
		}
		else if (directionPushed == 2)
		{
			x += 1;
		}
		else if (directionPushed == 3)
		{
			y += 1;
		}
		else if (directionPushed == 4)
		{
			x -= 1;
		}
		
		Tile t = new Tile(oldPos[0], oldPos[1]); 
		tileList[x][y] = tileList[oldPos[0]][oldPos[1]]; //new position crate is in
		tileList[oldPos[0]][oldPos[1]] = t; //replace old location crate was in with a normal tile
		
	}
	// need to update occupied status when turtle is moved.
	public Tile[][] updateTurtlePositionOnBoard(int[] newPos, int[] oldPos, RobotTurtle rt)
	{
		tileList[oldPos[0]][oldPos[1]].setOccupied(false);
		tileList[oldPos[0]][oldPos[1]].setRobotTurtle(null);
		tileList[newPos[0]][newPos[1]].setOccupied(true);
		tileList[newPos[0]][newPos[1]].setRobotTurtle(rt);
		return tileList;
	}
	public Tile[][] getTileList()
	{
		return tileList;
	}
	

}