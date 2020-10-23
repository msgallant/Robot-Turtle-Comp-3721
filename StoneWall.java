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
