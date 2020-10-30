package model;
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
		//findAndSetMovableDirections(); //need to do it in gameboard class not here?
	}
	
	public void findAndSetMovableDirections()
	{
		if (this.getNTile()!=null)
		{
			
			if (!this.getNTile().getTileType().equals("Normal") || this.getNTile().getOccupied() == true)
			{
				this.setNMovable(false);
			}

		}
		if (this.getSTile() != null)
		{
			if (!this.getSTile().getTileType().equals("Normal")  || this.getSTile().getOccupied() == true)
			{
				this.setSMovable(false);
			}
			

		}
		if (this.getWTile() != null)
		{
			if (!this.getWTile().getTileType().equals("Normal")  || this.getWTile().getOccupied() == true)
			{
				this.setWMovable(false);
			}

		}
		if (this.getETile() != null)
		{
			if (!this.getETile().getTileType().equals("Normal") || this.getETile().getOccupied() == true)
			{
				this.setEMovable(false);
			}

		}
		
		
		
	}
	
	
}