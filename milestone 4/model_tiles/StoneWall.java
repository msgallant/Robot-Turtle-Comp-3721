package model_tiles;

//All directions to be moved onto from are set to false, because a turtle can never move onto
//a tile with a stone wall no matter which direction the turtle is trying to move onto the
//tile from
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