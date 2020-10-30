package controller;

import model.Tile;
import model.GameBoardModel;
import model.RobotTurtle;

public class GameBoardConverter
{
	private GameBoardModel model;
	private Tile[][] t;
	public GameBoardConverter()
	{
		
	}
	public void setGameBoardModel(GameBoardModel m)
	{
		model =m;
	}
	public void setTileList(Tile[][] tileList)
	{
		t = tileList;
	}
	public GameBoardModel getGameBoardModel()
	{
		return model;
	}
	public Tile[][] getTileList()
	{
		return t;
	}
	
	public String[][] convertTiles()
	{
		
		int n = model.getGameBoardDimension();
		String[][] curGameBoard = new String[n][n];
		for (int i =0; i< n; i++)
		{
			for (int j =0; j<n; j++)
			{
				curGameBoard[j][i] = t[j][i].getTileType();
				if (t[j][i].getOccupied() == true) //if tile contains a robot turtle
				{
					curGameBoard[j][i] = "Robot Turtle";
				}
			}
		}
		return curGameBoard;
		
	}
	public String getColourOfTile(int x, int y)
	{
		Tile[][] t = model.getTileList();
		return t[x][y].getColour();
	}
	public String getColourOfRobotTurtle(int x, int y)
	{
		Tile[][] t = model.getTileList();
		RobotTurtle rt = t[x][y].getRobotTurtle();
		return rt.getColour();
	}
	public int getDirectionOfRobotTurtle(int x, int y)
	{
		Tile[][] t = model.getTileList();
		return t[x][y].getRobotTurtle().getDirection();
	}
	
}