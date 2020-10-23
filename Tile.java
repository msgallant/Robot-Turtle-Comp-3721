public class Tile
{
	private int xPos, yPos;
	private Tile nTile, eTile, wTile, sTile;
	private String tileType = "normal";
	private boolean nMovable, eMovable, wMovable, sMovable;
	private boolean occupied; //movable;
	public Tile(int x, int y)
	{
		xPos = x;
		yPos = y;
		nTile = null; //north neighbour Tile
		eTile = null; //east neighbour Tile
		wTile = null; //west neighbour Tile
		sTile = null; //south neighbour Tile
		
		nMovable = true; //if you can move to this tile from the north, south, west, east
		sMovable = true;
		wMovable = true;
		eMovable = true;
		
		//movable = true; //can this tile be moved on to by a turtle?
		
		
		occupied = false; // Is a turtle on this Tile?
		
	}
	public void setNTile(Tile n)
	{
		nTile = n;
	}
	public void setETile(Tile e)
	{
		eTile = e;
	}
	public void setWTile(Tile w)
	{
		wTile = w;
	}
	public void setSTile(Tile s)
	{
		sTile = s;
	}
	/*public void setMovable(boolean m)
	{
		movable = m;
	}*/
	
	public void setNMovable(boolean b)
	{
		nMovable = b;
	}
	public void setWMovable(boolean b)
	{
		wMovable = b;
	}
	public void setEMovable(boolean b)
	{
		eMovable = b;
	}
	public void setSMovable(boolean b)
	{
		sMovable = b;
	}
	
	public void setPosition(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	public void setOccupied(boolean o)
	{
		occupied = o;
	}
	public void setTileType(String t)
	{
		tileType = t;
	}
	public int[] getPosition()
	{
		int[] position = new int[2];
		position[0] = xPos;
		position[1] = yPos;
		return position;
	}
	public boolean getOccupied()
	{
		return occupied;
	}
	/*public boolean getMovable()
	{
		return movable;
	}*/
	public String getTileType()
	{
		return tileType;
	}
	
	
	public Tile getNTile()
	{
		return nTile;
	}
	public Tile getETile()
	{
		return eTile;
	}
	public Tile getWTile()
	{
		return wTile;
	}
	public Tile getSTile()
	{
		return sTile;
	}
	
	public boolean getNMovable()
	{
		return nMovable;
	}
	public boolean getWMovable()
	{
		return wMovable;
	}
	public boolean getEMovable()
	{
		return eMovable;
	}
	public boolean getSMovable()
	{
		return sMovable;
	}
}

