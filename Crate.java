//import java.util.ArrayList;

public class Crate extends Tile
{
	//private ArrayList<String> movableTileTypes = new ArrayList<String>();
	//private String tileType1 = "Normal";
	//need to check for all tile types that crate can be pushed onto
	
	public Crate(int x, int y) 
	{
		super(x, y);
		
		this.setTileType("Crate");
		findAndSetMovableDirections();
	}
	
	public void findAndSetMovableDirections()
	{
		if (this.getNTile() != null)
		{
			if (this.getNTile().getTileType() != "Normal" || this.getNTile().getOccupied() == true)
			{
				this.setNMovable(false);
			}
			else
			{
				this.setNMovable(true);
			}
		}
		if (this.getSTile() != null)
		{
			if (this.getSTile().getTileType() != "Normal"  || this.getSTile().getOccupied() == true)
			{
				this.setSMovable(false);
			}
			else
			{
				this.setSMovable(true);
			}
		}
		if (this.getWTile() != null)
		{
			if (this.getWTile().getTileType() != "Normal"  || this.getWTile().getOccupied() == true)
			{
				this.setWMovable(false);
			}
			else
			{
				this.setWMovable(true);
			}
		}
		if (this.getETile() != null)
		{
			if (this.getETile().getTileType() != "Normal"  || this.getETile().getOccupied() == true)
			{
				this.setEMovable(false);
			}
			else
			{
				this.setEMovable(true);
			}
		}
		
		
		
	}
	
	
}
