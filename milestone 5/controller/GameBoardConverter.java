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
	
	public String[] convertCards()
	{
		String p1 = "";
		String p2 = "";
		
		
		String[] programs = new String[2];
		for (int i =0; i < model.getProgram().size(); i++)
		{
			//get the card from model, then get card type from model
			p1 = p1 + model.getCardType(model.getProgram().get(i));
			p1 = addSeparator(p1, i, model.getProgram().size() -1);
			
		}
		for (int j =0; j< model.getFunctionFrog().size(); j++)
		{
			p2 = p2 + model.getCardType(model.getFunctionFrog().get(j));
			p2 = addSeparator(p2, j, model.getProgram().size() -1);
		}
		programs[0] = p1;
		programs[1] = p2;
		return programs;
	}
	protected String addSeparator(String p, int i, int end)
	{
		String p1 = p;
		String separator = ", \n";
		if (i < end) //if not the last card, add a separtor
		{
			p1 = p + separator;
		}
		return p1;
	}
}