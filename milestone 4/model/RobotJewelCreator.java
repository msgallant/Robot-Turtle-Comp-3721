package model;
//This class is responsible for creating all the robot jewels according to the number of players.
import model_gameboard.GameBoard;

public class RobotJewelCreator {
	private static int numPlayers;
	private static GameBoard gameBoard;
	
	private static int[] JEWEL_ONE_POSITION = {3, 3};
	private static int[] JEWEL_TWO_POSITION = {4, 4};
	private static int[] JEWEL_THREE_POSITION = {4, 3};
	private static int[] JEWEL_FOUR_POSITION = {3, 4};
	
	private static int[][] JEWEL_POSITIONS = {JEWEL_ONE_POSITION, JEWEL_TWO_POSITION, JEWEL_THREE_POSITION, JEWEL_FOUR_POSITION};
	
	private static String JEWEL_ONE_COLOUR = "Blue";
	private static String JEWEL_TWO_COLOUR = "Green";
	private static String JEWEL_THREE_COLOUR = "Purple";
	private static String JEWEL_FOUR_COLOUR = "Red"; 
	
	private static String[] JEWEL_COLOURS = {JEWEL_ONE_COLOUR, JEWEL_TWO_COLOUR, JEWEL_THREE_COLOUR, JEWEL_FOUR_COLOUR};
	  
	
	public RobotJewelCreator(int n, GameBoard g)
	{
		numPlayers = n;
		gameBoard = g;
	}
	public static void createRobotJewels()
	{
	    for (int i =0; i < numPlayers; i++)
	    {
	    	gameBoard.addRobotJewel(JEWEL_POSITIONS[i], JEWEL_COLOURS[i]);
	    }

	    
	}
}
