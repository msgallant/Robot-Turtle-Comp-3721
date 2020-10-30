package model;
//Marcia Gallant, Loryn Losier
//This class adds stone walls, crates, jewels and turtles to the game board and 
//updates turtles and crates positions when they move.
public class GameBoard
{
	private Tile[][] tileList;
	private int GAME_BOARD_DIMENSION;
	
	public GameBoard(int x)
	{
		GAME_BOARD_DIMENSION = x;
		tileList = new Tile[GAME_BOARD_DIMENSION][GAME_BOARD_DIMENSION];
		createEmptyGameBoard();
		setAllNeighbours();
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
				Tile t = new Tile(col, row);
				tileList[col][row] = t;
			}
			
		}
		
		
		return tileList;
	}
	protected void setAllNeighbours()
	{
		for (int row =0; row < GAME_BOARD_DIMENSION; row++ )
		{
			for (int col =0; col < GAME_BOARD_DIMENSION; col++)
			{
				setNeighbours(col, row);
			}
			
		}
	}
	
	//this method sets all neighbours for the tile with coordinates x, y or col, row
	protected void setNeighbours(int col, int row)
	{
		if (row > 0) //not on the top row of the gameboard means the tile has a north neighbour
		{
			tileList[col][row].setNTile(tileList[col][(row-1)]);
		}

		if (col < (GAME_BOARD_DIMENSION -1)) //not a tile on the right most column, it has a east neighbour
		{
			tileList[col][row].setETile(tileList[(col+1)][row]);
		}
		if ( row < (GAME_BOARD_DIMENSION - 1)) //not tile on bottom row then it has a south neighbour
		{
			tileList[col][row].setSTile(tileList[col][(row+1)]);
		}
		if (col > 0) //not a tile on left most column, then it has a west neighbour
		{
			tileList[col][row].setETile(tileList[(col-1)][row]);
		}
		
		
	}
	

	
	//this method doesn't set neighbours for tile in x,y position but sets neighbours for all tiles
	//surrounding tile x,y; it only sets neighbours if they exist
	protected void updateAllNeighbours(int x, int y)
	{
		if ( x+1 < GAME_BOARD_DIMENSION)
		{
			setNeighbours(x+1, y);
		}
		if (x-1 > 0)
		{
			setNeighbours(x-1, y);
		}
		if (y+1 < GAME_BOARD_DIMENSION)
		{
			setNeighbours(x, y+1);
		}
		if (y - 1 > 0)
		{
			setNeighbours(x, y-1);
		}
	}
	protected void findAndSetMovableDirectionsForCrates(Tile t)
	{
		
		if (t.getNTile()!=null)
		{
			
			if (t.getNTile().getTileType().equals("Normal") || t.getNTile().getOccupied() == false)
			{
				t.setSMovable(true);
			}

		}
		if (t.getSTile() != null)
		{
			if ((t.getSTile().getTileType().equals("Normal")  || t.getSTile().getOccupied() == false))
			{
				t.setNMovable(true);
			}
			

		}
		if (t.getWTile() != null)
		{
			if (t.getWTile().getTileType().equals("Normal")  || t.getWTile().getOccupied() == false)
			{
				t.setEMovable(true);
			}

		}
		if (t.getETile() != null)
		{
			if (t.getETile().getTileType().equals("Normal") || t.getETile().getOccupied() == false)
			{
				t.setWMovable(true);
			}

		}
		
		
	}
	public boolean checkIfTurtleCanMove(RobotTurtle rt)
	{
		int rtDirection = rt.getDirection();
		int[] futurePos = rt.calculateNewPosition();
		boolean b = false;
		
		setAllNeighbours();
		if (tileList[futurePos[0]][futurePos[1]].getTileType() == "Crate")
		{
			//since crates need to know about the tile behind them to tell if
			//turtles can move on a crate tile
			findAndSetMovableDirectionsForCrates(tileList[futurePos[0]][futurePos[1]]);
		}
		
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
		
		//update the surrounding tiles of the tile that just moved positions and the tile that was
		//just created, since these 2 tiles surround each other, they both will be updated as well
		updateAllNeighbours(x,y);
		updateAllNeighbours(oldPos[0], oldPos[1]);
		
		
		
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