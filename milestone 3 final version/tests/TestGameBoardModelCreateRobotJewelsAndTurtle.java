package tests;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import model.GameBoardModel;
import model.RobotTurtle;
import model.RobotJewel;
import model.GameBoard;
import model.Tile;
class TestGameBoardModelCreateRobotJewelsAndTurtle {

	  private static int[] JEWEL_ONE_POSITION = {3, 3};
	  private static int[] JEWEL_TWO_POSITION = {4, 4};
	  private static int[] JEWEL_THREE_POSITION = {4, 3};
	  private static int[] JEWEL_FOUR_POSITION = {3, 4};
	  
	  private static String JEWEL_ONE_COLOUR = "Blue";
	  private static String JEWEL_TWO_COLOUR = "Green";
	  private static String JEWEL_THREE_COLOUR = "Purple";
	  private static String JEWEL_FOUR_COLOUR = "Red"; 
	  
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
	@ParameterizedTest   //must run one case at a time
	@CsvSource({       
		//"1", // first set of arguments      
		"2", // second set of arguments   
		//"3", // third set of arguments
		//"4", // fourth set of arguments
 
		})  
	void createRobotJewelsMethod(int numPlayers) {
		GameBoardModel ab = new GameBoardModel(numPlayers);
		//ab.createRobotJewels();
		GameBoard board = ab.getGameBoard();
		Tile[][] tileList = board.getTileList();

		for (int i =0; i < 8; i++)
		{
			for (int j =0; j< 8; j++)
			{
				System.out.print(" x pos: " + i + " y pos: " + j);
				System.out.println(tileList[i][j].getTileType());
			}
		}
		assertThat(tileList[JEWEL_ONE_POSITION[0]][JEWEL_ONE_POSITION[1]].getTileType()).isEqualTo("Robot Jewel");
		if (numPlayers < 2)
		{
			assertThat(tileList[JEWEL_TWO_POSITION[0]][JEWEL_TWO_POSITION[1]].getTileType()).isEqualTo("Normal");
		}
		else
		{

			assertThat(tileList[JEWEL_TWO_POSITION[0]][JEWEL_TWO_POSITION[1]].getTileType()).isEqualTo("Robot Jewel");
		}
		
System.out.println("");
		
	}
	@ParameterizedTest   
	@CsvSource({       //HAVE TO DO ARGUMENTS ONE AT A TIME - OTHERWISE ARRAYLIST MESSES UP AND ADDS TURTLES CONTINOUSLY, SO FIRST TIME WOULD BE 2 TURTLES, 2ND TIME WOULD BE 2+3 TURTLES
		//"2", // first set of arguments      
		//"3", // second set of arguments  
		"4", // third set of arguments      
		//"1", // fourth set of arguments
 
		})  
	void createRobotTurtlesMethod(int numPlayers) {

		GameBoardModel gm = new GameBoardModel(numPlayers);
		//gm.createRobotTurtles();
		GameBoard board = gm.getGameBoard();
		Tile[][] tileList = board.getTileList();
		
		ArrayList<RobotTurtle> rt = gm.getListOfRobotTurtles();
		for (int i =0; i < rt.size(); i++)
		{
			System.out.println("turtle " + i + " is" + rt.get(i).getXPos() + " " + rt.get(i).getYPos());
		}
		assertThat(rt.size()).isEqualByComparingTo(numPlayers);
		assertThat(rt.get(0).getXPos()).isEqualTo(TURTLE_ONE_POSITION[0]);  //testing turtle 1 and 2 given 2 or 3 players
		assertThat(rt.get(0).getYPos()).isEqualTo(TURTLE_ONE_POSITION[1]);
		if (numPlayers != 1)
		{
			assertThat(rt.get(1).getXPos()).isEqualTo(TURTLE_TWO_POSITION[0]);
			assertThat(rt.get(1).getYPos()).isEqualTo(TURTLE_TWO_POSITION[1]);
		}
		
		System.out.println("num play: " + numPlayers);
		for (int i =0; i < 8; i++)
		{
			for (int j =0; j< 8; j++)
			{
				System.out.print(" x pos: " + i + " y pos: " + j);
				System.out.println(tileList[i][j].getOccupied());
			}
		}
		System.out.println("");
		
	}

}
