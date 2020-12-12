package model;
//this finds what tiles are invalid positions that the user cannot put stone walls and crates
public class InvalidPositionCalculator {
	private static int[] TURTLE_ONE_POSITION = {0,0,3};
	  private static int[] TURTLE_TWO_POSITION = {7,7,1};
	  private static int[] TURTLE_THREE_POSITION = {7,0,4};
	  private static int[] TURTLE_FOUR_POSITION = {0,7,2};
	  
	  private static int[] JEWEL_ONE_POSITION = {3, 3};
	  private static int[] JEWEL_TWO_POSITION = {4, 4};
	  private static int[] JEWEL_THREE_POSITION = {4, 3};
	  private static int[] JEWEL_FOUR_POSITION = {3, 4};
	  
	  private int[][][] objs = {{TURTLE_ONE_POSITION, JEWEL_ONE_POSITION}, {TURTLE_TWO_POSITION, JEWEL_TWO_POSITION}, {TURTLE_THREE_POSITION, JEWEL_THREE_POSITION}, {TURTLE_FOUR_POSITION, JEWEL_FOUR_POSITION}};   
	
	private int numInvalidPositions, numPlayers;
	private int[] invalidXPos, invalidYPos;
	public InvalidPositionCalculator(int n)
	{
		numInvalidPositions= n*2;
		numPlayers = n;
	}
	//finds positions with jewels and turtles because crates and stone walls cannot be put here
	public int[][] findInvalidPositions()
	{
		int index = 0;
		  
		invalidXPos = new int[numInvalidPositions]; //since each player needs a turtle and a jewel
		invalidYPos = new int[numInvalidPositions];
		
		for (int i = 0; i < numPlayers; i++)
		{
			invalidXPos[index] = objs[i][0][0]; //turtle x pos
			invalidYPos[index] = objs[i][0][1]; //turtle y pos
			invalidXPos[index+1] = objs[i][1][0];//jewel x pos
			invalidYPos[index+1] = objs[i][1][1]; //jewel y pos
			index = index + 2;
		}
		int[][] arr = {invalidXPos, invalidYPos};
		return arr;
	}
	
	  
}
