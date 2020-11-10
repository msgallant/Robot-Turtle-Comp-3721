package model_tiles;

public class CratePositionCalculator implements CratePositionCalculatorInterface {
	public CratePositionCalculator()
	{
		
	}
	public boolean[] findMovableDirectionsForCrates(Tile t)
	{//1=north, 2=east, 3=south, 4=west 
		//arrays start at 0, so the values are off by 1
		boolean[] dirMovable = {false, false, false, false}; 
		if (t.getNTile()!=null) 
		{
			//if the north tile isn't null or have a turtle or object on it, the crate can be pushed to this
			//north tile, therefore, you can move onto the crate tile from the south
			if (t.getNTile().getTileType().equals("Normal") && t.getNTile().getOccupied() == false)
			{
				dirMovable[2] = true;
			}

		}
		if (t.getSTile() != null)
		{
			if ((t.getSTile().getTileType().equals("Normal")  && t.getSTile().getOccupied() == false))
			{

				dirMovable[0] = true;
			}
			

		}
		if (t.getWTile() != null)
		{
			if (t.getWTile().getTileType().equals("Normal")  && t.getWTile().getOccupied() == false)
			{
				dirMovable[1] = true;
			}

		}

		if (t.getETile() != null)
		{
			if (t.getETile().getTileType().equals("Normal") && t.getETile().getOccupied() == false)
			{
				dirMovable[3] = true;
			}

		}
		return dirMovable;
	}
	public int[] findNewCratePosition(int[] oldPos, int directionPushed)
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
		int[] newPos = {x,y};
		return newPos;
	}
	
}
