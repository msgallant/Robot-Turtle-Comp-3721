package model;

import java.util.ArrayList;

import model_tiles.Tile;
import model_turtle.RobotTurtle;

public interface ModelDataInterface {
	public int getGameBoardDimension();
	public Tile[][] getTileList();
	public String[] getCardTypes();
	public int[] getInvalidXPositions();
	public int[] getInvalidYPositions();
	public int getMaxCrates();
	public int getMaxStoneWalls();
	public Player getCurrentPlayer();
	public State getCurrentState();
	public ArrayList<RobotTurtle> getListOfRobotTurtles();
	public Card[] getDeckOfCards();
}
