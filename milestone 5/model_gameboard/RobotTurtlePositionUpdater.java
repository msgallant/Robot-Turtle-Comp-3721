package model_gameboard;

import model_tiles.Tile;
import model_turtle.RobotTurtle;

public class RobotTurtlePositionUpdater {
	private Tile[][] tileList;
	public RobotTurtlePositionUpdater(Tile[][] t)
	{
		tileList = t;
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
		
		//updates turtle's x, y position and direction appropriately 
		public void updateTurtlePosition(int x, int y, int d, RobotTurtle rt)
		{
			rt.setXPos(x);
			rt.setYPos(y);
			rt.setDirection(d);
		}	
		//updates the turtle position in the turtle itself and on the gameboard
		public void teleport(int x, int y, RobotTurtle rt)
		{
			tileList[rt.getXPos()][rt.getYPos()].setOccupied(false);
			tileList[rt.getXPos()][rt.getYPos()].setRobotTurtle(null);
			Tile t = tileList[x][y].getNTile(); //since it's a portal tile, doesnt matter what direction tile you get
			rt.setXPos(t.getXPos());
			rt.setYPos(t.getYPos());
			tileList[t.getXPos()][t.getYPos()].setOccupied(true);
			tileList[t.getXPos()][t.getYPos()].setRobotTurtle(rt);
		}
}
