package model_gameboard;

import java.util.ArrayList;

import model_tiles.Crate;
import model_tiles.IceWall;
import model_tiles.Portal;
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
	private Portal prevPortal = null;
	public GameBoard(int x)
	{
		GAME_BOARD_DIMENSION = x;
		tileList = new Tile[GAME_BOARD_DIMENSION][GAME_BOARD_DIMENSION];
		createEmptyGameBoard();
		//this attaches all the tiles together, meaning all tiles store all their neighbours
		tileAttacher = new TileAttacher(GAME_BOARD_DIMENSION, tileList);
		tileAttacher.setAllNeighbours();
	}
	
	public GameBoard(GameBoard gb)
	{
		deepCopy(gb);
	}
	public void deepCopy(GameBoard gb)
	{
		
		GAME_BOARD_DIMENSION = gb.getGameBoardDimension();
		tileList = new Tile[GAME_BOARD_DIMENSION][GAME_BOARD_DIMENSION];
		
		for (int row =0; row < GAME_BOARD_DIMENSION; row++ )
		{
			for (int col =0; col < GAME_BOARD_DIMENSION; col++)
			{
				if (gb.getTileList()[col][row].getTileType() == "Portal")
				{
					Portal temp = new Portal(0,0);
					temp.deepCopy(gb.getTileList()[col][row]);
					tileList[col][row] = temp;
				}
				else
				{
					Tile temp = new Tile(0, 0);
					temp.deepCopy(gb.getTileList()[col][row]);
					tileList[col][row] = temp;
				}
				
				
			}
			
		}
		tileAttacher = new TileAttacher(GAME_BOARD_DIMENSION, tileList);
		tileAttacher.setAllNeighbours();
	}
	
	public ArrayList<RobotTurtle> getListOfRobotTurtles()
	{
		ArrayList<RobotTurtle> l = new ArrayList<RobotTurtle>();
		String[] colours = {"Blue", "Green", "Purple", "Red"};
		int index = 0;
		for (int i = 0; i < colours.length; i++)
		{
			for (int row =0; row < GAME_BOARD_DIMENSION; row++ )
			{
				for (int col =0; col < GAME_BOARD_DIMENSION; col++)
				{
					if (tileList[col][row].getOccupied() == true)
					{
						if (tileList[col][row].getRobotTurtle().getColour().equals(colours[index]))
						{
							l.add(tileList[col][row].getRobotTurtle());
							index++;
						}
					}
				}
			
			}
		}
		return l;
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
	public Tile[][] addPortal(int x, int y)
	{
		Portal t = new Portal(x, y);
		tileList[x][y] =t;
		//since portals need to be attached to each other,
		//the odd number portal is stored in prevPortal and
		//the next portal will become attached to it
		if (prevPortal == null) 
		{
			prevPortal = t;
		}
		else //will cast work like thiS??
		{
			t.setAttachedPortal(prevPortal);
			prevPortal.setAttachedPortal(t);
			prevPortal = null;
		}
		return tileList;
	}
	public Tile[][] addIceWall(int x, int y)
	{
		IceWall t = new IceWall(x, y);
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

	public int getGameBoardDimension()
	{
		return GAME_BOARD_DIMENSION;
	}
}