package model_gameboard;

import model_tiles.Tile;

public class IceWallMelter {

	public void meltIceWallsAhead(int direction, int x, int y, GameBoard gb)
	{
		Tile[][] t = gb.getTileList();
		int dim = gb.getGameBoardDimension();
		
		if (direction == 1) //facing north
		{
			for (int i = y; i > -1; i--)
			{
				if (t[x][i].getTileType() == "Ice Wall")
				{
					t[x][i].setTileType("Puddle");
					setAbleToMoveHere(t[x][i]);
					i = 0; //break out of loop since laser card only does 1 ice wall at a time
				}
				else if (t[x][i].getTileType() != "Normal") //if an obstacle is block the path for the laser to the ice wall, stop looking
				{
					i = 0;
				}
			}
			
		}
		else if (direction == 2) //facing east
		{
			for (int i = x; i < dim; i++)
			{
				if (t[i][y].getTileType() == "Ice Wall")
				{
					t[i][y].setTileType("Puddle");
					setAbleToMoveHere(t[i][y]);
					i = dim; //break out of loop since laser card only does 1 ice wall at a time
				}
				else if (t[i][y].getTileType() != "Normal") //if an obstacle is block the path for the laser to the ice wall, stop looking
				{
					i = dim;
				}
			}
		}
		else if (direction == 3) //facing south
		{

			for (int i = y; i < dim; i++)
			{
				if (t[x][i].getTileType() == "Ice Wall")
				{
					t[x][i].setTileType("Puddle");
					setAbleToMoveHere(t[x][i]);
					i = dim; //break out of loop since laser card only does 1 ice wall at a time
				}
				else if (t[x][i].getTileType() != "Normal") //if an obstacle is block the path for the laser to the ice wall, stop looking
				{
					i = dim;
				}
			}
			
		}
		else if (direction == 4) //facing west
		{
			System.out.println("x: " + x + " y: " + y);
			for (int i = x; i > -1; i--)
			{
				if (t[i][y].getTileType() == "Ice Wall")
				{
					t[i][y].setTileType("Puddle");
					setAbleToMoveHere(t[i][y]);
					i = 0; //break out of loop since laser card only does 1 ice wall at a time
				}
				else if (t[i][y].getTileType() != "Normal") //if an obstacle is block the path for the laser to the ice wall, stop looking
				{
					i = 0;
				}
			}

		}
	}

	protected void setAbleToMoveHere(Tile t)
	{
		t.setEMovable(true);
		t.setWMovable(true);
		t.setSMovable(true);
		t.setNMovable(true);
	}

}
