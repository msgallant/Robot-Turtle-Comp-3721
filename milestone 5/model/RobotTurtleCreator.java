package model;

import java.util.ArrayList;

import model_gameboard.GameBoard;
import model_turtle.RobotTurtle;

public class RobotTurtleCreator {
	private int numPlayers;
	private static ArrayList<RobotTurtle> listOfTurtles = new ArrayList<RobotTurtle>();
	private GameBoard gameBoard;
	
	private static int[] TURTLE_ONE_POSITION = {0,0,3};
	private static int[] TURTLE_TWO_POSITION = {7,7,1};
	private static int[] TURTLE_THREE_POSITION = {7,0,4};
	private static int[] TURTLE_FOUR_POSITION = {0,7,2};
	  
	private static String TURTLE_ONE_NAME = "Beep";
	private static String TURTLE_TWO_NAME = "Pangle";
	private static String TURTLE_THREE_NAME = "Dot";
	private static String TURTLE_FOUR_NAME = "Pi";
	  
	private static String TURTLE_ONE_COLOUR = "Blue";
	private static String TURTLE_TWO_COLOUR = "Green";
	private static String TURTLE_THREE_COLOUR = "Purple";
	private static String TURTLE_FOUR_COLOUR = "Red";
	
	private static int[][] TURTLE_POSITIONS = {TURTLE_ONE_POSITION, TURTLE_TWO_POSITION, TURTLE_THREE_POSITION, TURTLE_FOUR_POSITION};
	private static String[] TURTLE_COLOURS = {TURTLE_ONE_COLOUR, TURTLE_TWO_COLOUR, TURTLE_THREE_COLOUR, TURTLE_FOUR_COLOUR};
	private static String[] TURTLE_NAMES = {TURTLE_ONE_NAME, TURTLE_TWO_NAME, TURTLE_THREE_NAME, TURTLE_FOUR_NAME};  
	public RobotTurtleCreator(int n, GameBoard gb)
	{
		numPlayers = n;
		gameBoard = gb;
	}
	public ArrayList<RobotTurtle> createRobotTurtles()
	{
		for (int i =0; i < numPlayers; i++)
		{
			//int x, int y, int d, String n, String c
			RobotTurtle t = new RobotTurtle(TURTLE_POSITIONS[i][0],TURTLE_POSITIONS[i][1],TURTLE_POSITIONS[i][2],TURTLE_NAMES[i], TURTLE_COLOURS[i]);
			listOfTurtles.add(t);
			gameBoard.addRobotTurtle(TURTLE_POSITIONS[i], t);
		}
		return listOfTurtles;
	}
}
