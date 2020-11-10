package model_gameboard;
//this class is responsible for setting all of the appropriate neighbour tiles for each tile
//this is useful because if it's a crate we can look at the crates neighbours and use
//that information to determine if the crate can be pushed.
import model_tiles.Tile;

public class TileAttacher {
	private int GAME_BOARD_DIMENSION;
	private Tile[][] tileList;
	public TileAttacher(int gbd, Tile[][] t)
	{
		GAME_BOARD_DIMENSION = gbd;
		tileList = t;
	}
	public void setTileList(Tile[][] t)
	{
		tileList = t;
	}
	public void setAllNeighbours()
	{
		for (int row =0; row < GAME_BOARD_DIMENSION; row++ )
		{
			for (int col =0; col < GAME_BOARD_DIMENSION; col++)
			{
				setNeighbours(col, row);
			}
			
		}
	}
	
	//this method sets all neighbours for the tile with coordinates x, y or col, row
	protected void setNeighbours(int col, int row)
	{
		if (row > 0) //not on the top row of the gameboard means the tile has a north neighbour
		{
			tileList[col][row].setNTile(tileList[col][(row-1)]);
		}
		else
		{
			tileList[col][row].setNTile(null);
		}

		if (col < (GAME_BOARD_DIMENSION -1)) //not a tile on the right most column, it has a east neighbour
		{
			tileList[col][row].setETile(tileList[(col+1)][row]);
		}
		else
		{
			tileList[col][row].setETile(null);
		}
		if ( row < (GAME_BOARD_DIMENSION - 1)) //not tile on bottom row then it has a south neighbour
		{
			tileList[col][row].setSTile(tileList[col][(row+1)]);
		}
		else
		{
			tileList[col][row].setSTile(null);
		}
		if (col > 0) //not a tile on left most column, then it has a west neighbour
		{
			tileList[col][row].setWTile(tileList[(col-1)][row]);
		}
		else
		{
			tileList[col][row].setWTile(null);
		}
		
		
	}
	

	
	//this method doesn't set neighbours for tile in x,y position but sets neighbours for all tiles
	//surrounding tile x,y; it only sets neighbours if they exist
	public void updateAllNeighbours(int x, int y)
	{
		if ( x+1 < GAME_BOARD_DIMENSION)
		{
			setNeighbours(x+1, y);
		}
		if (x-1 > 0)
		{
			setNeighbours(x-1, y);
		}
		if (y+1 < GAME_BOARD_DIMENSION)
		{
			setNeighbours(x, y+1);
		}
		if (y - 1 > 0)
		{
			setNeighbours(x, y-1);
		}
	}
}
