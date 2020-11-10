package controller;

import model.GameBoardModel;

public class GameBoardConverter
{
	private GameBoardModel model;
	public GameBoardConverter()
	{
		
	}
	public void setGameBoardModel(GameBoardModel m)
	{
		model =m;
	}

	public GameBoardModel getGameBoardModel()
	{
		return model;
	}

	
	public String[][] convertTiles()
	{
		
		int n = model.getGameBoardDimension();
		String[][] curGameBoard = new String[n][n];
		for (int i =0; i< n; i++)
		{
			for (int j =0; j<n; j++)
			{
				curGameBoard[j][i] = model.getTileList()[j][i].getTileType();
				if (model.getTileList()[j][i].getOccupied() == true) //if tile contains a robot turtle
				{
					curGameBoard[j][i] = "Robot Turtle";
				}
			}
		}
		return curGameBoard;
		
	}
	public boolean checkIfRobotTurtle(int x, int y)
	{
		if (model.getTileList()[x][y].getOccupied() == true)
		{
			return true;
		}
		return false;
	}
	public int getDirectionOfTile(int x, int y)
	{
		if (checkIfRobotTurtle(x, y))
		{
			return getDirectionOfRobotTurtle(x, y);
		}
		return 0; //direction only matters for robot turtles, so, the default direction is 0 for everything else
	}
	public String getColourOfTile(int x, int y)
	{
		if (checkIfRobotTurtle(x, y))
		{
			return getColourOfRobotTurtle(x, y);
		}
		String s = model.getTileList()[x][y].getColour();
		return s;
	}
	public String getColourOfRobotTurtle(int x, int y)
	{
		String s = model.getTileList()[x][y].getRobotTurtle().getColour();
		return s;
	}
	public int getDirectionOfRobotTurtle(int x, int y)
	{
		int d = model.getTileList()[x][y].getRobotTurtle().getDirection();
		return d;
	}
	
}