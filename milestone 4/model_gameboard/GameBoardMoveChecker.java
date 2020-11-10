package model_gameboard;
//This class makes sure that turtles can move,
//if the turtle can move then it then it returns true
//it also makes sure to move the crate along with the turtle if
//the turtle pushed a crate
import model_tiles.CratePositionCalculator;
import model_tiles.Tile;

public class GameBoardMoveChecker {
	private Tile[][] tileList;
	private TileAttacher tileAttacher;
	public GameBoardMoveChecker(Tile[][] t, TileAttacher a)
	{
		tileList = t;
		tileAttacher = a;
	}
	public boolean checkIfTurtleCanMove(int[] newPos, int dir)
	{
		int rtDirection = dir;
		int[] futurePos = newPos;
		boolean b = false;
		
		tileAttacher.setAllNeighbours();
		if (tileList[futurePos[0]][futurePos[1]].getTileType() == "Crate")
		{
			//since crates need to know about the tile behind them to tell if
			//turtles can move on a crate tile
			CratePositionCalculator cpc = new CratePositionCalculator();
			boolean[] dirMovable = cpc.findMovableDirectionsForCrates(tileList[futurePos[0]][futurePos[1]]);
			CratePositionUpdater crateUpdater = new CratePositionUpdater();
			crateUpdater.setMovableDirectionsForCrates(dirMovable, tileList[futurePos[0]][futurePos[1]]);
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
	
}
