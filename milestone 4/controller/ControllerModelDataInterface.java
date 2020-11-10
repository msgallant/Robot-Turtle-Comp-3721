package controller;

import java.util.ArrayList;

import model.Card;
import model.Player;
import model.State;
import model_turtle.RobotTurtle;
import model.ModelDataInterface;

public interface ControllerModelDataInterface extends ModelDataInterface {

	public int[] getInvalidXPositions();
	public int[] getInvalidYPositions();
	public int getMaxCrates();
	public int getMaxStoneWalls();
	public Player getCurrentPlayer();
	public State getCurrentState();
	public ArrayList<RobotTurtle> getListOfRobotTurtles();
	public Card[] getDeckOfCards();
	
}
