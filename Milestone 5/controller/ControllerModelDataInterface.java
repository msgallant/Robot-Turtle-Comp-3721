package controller;

import java.util.ArrayList;

import model.Card;
import model.Player;
import model.State;
import model_tiles.Tile;
import model_turtle.RobotTurtle;
import model.ModelDataInterface;

public interface ControllerModelDataInterface extends ModelDataInterface {

	public int[] getInvalidXPositions();
	public int[] getInvalidYPositions();
	public int getMaxCrates();
	public int getMaxStoneWalls();
	public int getMaxIceWalls();
	public int getMaxPortals();
	public Player getCurrentPlayer();
	public State getCurrentState();
	public ArrayList<RobotTurtle> getListOfRobotTurtles();
	public Card[] getDeckOfCards();
	
	public boolean addStoneWall(int x, int y);
	public boolean addCrate(int x, int y);
	public boolean addIceWall(int x, int y);
	public boolean addPortal(int x, int y);
	public void reverseProgram();
	
	public void addCardToFunctionFrog(Card c);
	public void clearFunctionFrog();
	public void addCardToProgram(Card c);
	public void clearProgram();
	
	public boolean writeProgram(RobotTurtle rt);
	public boolean checkIfPlayerWon(RobotTurtle rt);
	
	public int getGameBoardDimension();
	public Tile[][] getTileList();
	
	public ArrayList<Card> getProgram();
	public ArrayList<Card> getFunctionFrog();
	public String getCardType(Card c);
	
}
