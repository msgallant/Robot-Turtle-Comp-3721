package model_gameboard;

import model_tiles.Crate;
import model_tiles.RobotJewel;
import model_tiles.StoneWall;
import model_tiles.Tile;
import model_turtle.RobotTurtle;

//Marcia Gallant
//This class is responsible for creating the board and the objects on the board
//This class adds stone walls, crates, jewels and turtles to the game board 
//it also creates a Tile Attacher object which 'attaches' all the tiles together
//it also makes sure no jewel can be claimed more than once.
public class GameBoard
{
	private Tile[][] tileList;
	private int GAME_BOARD_DIMENSION;
	private TileAttacher tileAttacher;
	public GameBoard(int x)
	{
		GAME_BOARD_DIMENSION = x;
		tileList = new Tile[GAME_BOARD_DIMENSION][GAME_BOARD_DIMENSION];
		createEmptyGameBoard();
		//this attaches all the tiles together, meaning all tiles store all their neighbours
		tileAttacher = new TileAttacher(GAME_BOARD_DIMENSION, tileList);
		tileAttacher.setAllNeighbours();
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
	public boolean checkIfObjectPresent(int x, int y) //looks to see if an object is already on tile, 
	{
		if (!tileList[x][y].getTileType().equals("Normal"))
		{
			return true;
		}
		return false;
	}
	public Tile[][] createEmptyGameBoard()
	{
		for (int row =0; row < GAME_BOARD_DIMENSION; row++ )
		{
			for (int col =0; col < GAME_BOARD_DIMENSION; col++)
			{
				Tile t = new Tile(col, row);
				tileList[col][row] = t;
			}
			
		}
		
		
		return tileList;
	}
	
	//if robot turtle moved onto robot jewel, then no other robot turtles can move onto this jewel
	public void robotJewelClaimed(int x, int y) 
	{
		tileList[x][y].setSMovable(false);
		tileList[x][y].setWMovable(false);
		tileList[x][y].setEMovable(false);
		tileList[x][y].setNMovable(false);
	}
	
	public Tile[][] getTileList()
	{
		return tileList;
	}
	public TileAttacher getTileAttacher()
	{
		tileAttacher.setTileList(tileList);
		return tileAttacher;
	}

}