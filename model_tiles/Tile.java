package model_tiles;

import model_turtle.RobotTurtle;

//This class allows you to make Tile objects or subclass objects such as robot jewels, crates, stonewalls
//It keeps track of location, its north, west, south, east neighbours
//what directions a turtle can move onto the tile from
//whether or not a robot turtle is already on the tile and which one is
//color is set to default which is white, the color is changed if it is a robot jewel.
//it is necessary to have this method in it because when we put all the tiles into Tile[][] tileList
//we cannot use getJewelColour to figure out which jewel it is
//and then we will not be able to draw it in our display.
public class Tile
{
	private int xPos, yPos;
	private Tile nTile, eTile, wTile, sTile;
	private String tileType = "Normal";
	private boolean nMovable, eMovable, wMovable, sMovable;
	private boolean occupied; //movable;
	private String colour = "White";
	private RobotTurtle rt;
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
		
		//findDirectionsWithNoNeighbours();
		
	}
	public void deepCopy(Tile t)
	{
		this.setXPos(t.getXPos());
		this.setYPos(t.getYPos());
		
		this.setNMovable(t.getNMovable());
		this.setSMovable(t.getSMovable());
		this.setWMovable(t.getWMovable());
		this.setEMovable(t.getEMovable());
		
		this.setETile(t.getETile());
		this.setWTile(t.getWTile());
		this.setSTile(t.getSTile());
		this.setNTile(t.getNTile());
		
		this.setOccupied(t.getOccupied());
		
		this.setTileType(t.getTileType());
		if (this.getOccupied() == true)
		{
			RobotTurtle temp = new RobotTurtle(1,1,1,"a","b");
			temp.deepCopy(t.getRobotTurtle());
			this.setRobotTurtle(temp);
		}
		else
		{
			this.setRobotTurtle(null);
		}
		this.setColour(t.getColour());
	}
	public void findDirectionsWithNoNeighbours()
	{
		
		if (wTile == null)
		{
			wMovable = false; // no west neighbours
		}
		if (eTile == null)
		{
			
			eMovable = false; // no east neighbours
		}
		if (nTile == null)
		{
			nMovable = false; // no north neighbours
		}
		if (sTile == null)
		{
			sMovable = false; // no south neighbours
		}
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
	public void setXPos(int x)
	{
		xPos = x;
	}
	public void setYPos(int y)
	{
		yPos = y;
	}
	public void setColour(String c)
	{
		colour = c;
	}
	public void setRobotTurtle(RobotTurtle r)
	{
		rt = r;
	}
	public void setOccupied(boolean o)
	{
		occupied = o;
	}
	public void setTileType(String t)
	{
		tileType = t;
	}
	public RobotTurtle getRobotTurtle()
	{
		return rt;
	}
	public int getXPos()
	{
		return xPos;
	}
	public int getYPos()
	{
		return yPos;
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
	public String getColour()
	{
		return colour;
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
