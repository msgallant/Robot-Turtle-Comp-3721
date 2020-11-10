package model_gameboard;

import model_tiles.Tile;
//this class responsibility is to update the crate's position and variables such as which directions
//turtles can move onto the crate from
public class CratePositionUpdater {
	private TileAttacher tileAttacher;
	private Tile[][] tileList;
	public CratePositionUpdater(TileAttacher attacher, Tile[][] t)
	{
		tileAttacher = attacher;
		tileList = t;
	}
	public CratePositionUpdater() //just updating the movable directions you don't need a Tile Attacher or tile list
	{
		
	}
	public void updateCratePositionOnBoard(int[] oldPos, int[] newPos)
	{
		
		
		Tile t = new Tile(oldPos[0], oldPos[1]); 
		tileList[newPos[0]][newPos[1]] = tileList[oldPos[0]][oldPos[1]]; //new position crate is in
		tileList[oldPos[0]][oldPos[1]] = t; //replace old location crate was in with a normal tile
		
		tileAttacher.setTileList(tileList);
		//update the surrounding tiles of the tile that just moved positions and the tile that was
		//just created, since these 2 tiles surround each other, they both will be updated as well
		tileAttacher.updateAllNeighbours(newPos[0],newPos[1]);
		tileAttacher.updateAllNeighbours(oldPos[0], oldPos[1]);
		
		
		
	}
	//update crate position in its own class
	public void updateCratePosition(int[] oldPos, int[] newPos)
	{
		tileList[oldPos[0]][oldPos[1]].setXPos(newPos[0]);
		tileList[oldPos[0]][oldPos[1]].setYPos(newPos[1]);
	}
	public void setMovableDirectionsForCrates(boolean[] dirMovable, Tile t)
	{
		t.setNMovable(dirMovable[0]);
		t.setEMovable(dirMovable[1]);
		t.setSMovable(dirMovable[2]);
		t.setWMovable(dirMovable[3]);
	}
}
