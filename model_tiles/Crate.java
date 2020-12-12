package model_tiles;

//import java.util.ArrayList;
//This allows you to create Crate tiles, they are set to not be able to move to them from all directions intitally
//but when checking if a turtle can move on the gameboard in the GameBoard class
//findAndSetMovableDirectionsForCrates(tileList[futurePos[0]][futurePos[1]]); corrects which
//directions they can be moved onto from
public class Crate extends Tile
{
	
	public Crate(int x, int y) 
	{
		super(x, y);
		
		this.setTileType("Crate");
		this.setSMovable(false);
		this.setNMovable(false);
		this.setWMovable(false);
		this.setEMovable(false);

	}
	

	
}
