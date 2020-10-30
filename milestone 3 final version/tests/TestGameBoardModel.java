package tests;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import model.GameBoard;
import model.GameBoardModel;
import model.Tile;
import model.RobotTurtle;
import model.Card;

class TestGameBoardModel {

	  private static int[] JEWEL_ONE_POSITION = {4, 3};
	  private static int[] JEWEL_TWO_POSITION = {3, 4};
	  private static int[] JEWEL_THREE_POSITION = {3, 3};
	  private static int[] JEWEL_FOUR_POSITION = {4, 4};
	  private static String JEWEL_ONE_COLOUR = "Blue";
	  private static String JEWEL_TWO_COLOUR = "Green";
	  private static String JEWEL_THREE_COLOUR = "Purple";
	  private static String JEWEL_FOUR_COLOUR = "Red";
	  
	  private static int[] TURTLE_ONE_POSITION = {0,0,3};
	  private static int[] TURTLE_TWO_POSITION = {0,7,4};
	  private static int[] TURTLE_THREE_POSITION = {7,0,2};
	  private static int[] TURTLE_FOUR_POSITION = {7,7,1};
	  
	  private static String TURTLE_ONE_NAME = "Beep";
	  private static String TURTLE_TWO_NAME = "Pangle";
	  private static String TURTLE_THREE_NAME = "Dot";
	  private static String TURTLE_FOUR_NAME = "Pi";
	  
	  private static String TURTLE_ONE_COLOUR = "Blue";
	  private static String TURTLE_TWO_COLOUR = "Green";
	  private static String TURTLE_THREE_COLOUR = "Purple";
	  private static String TURTLE_FOUR_COLOUR = "Red";
	
	@Test
	void createCardDeckMethod() {
		GameBoardModel gm = new GameBoardModel(1);
		
		System.out.println(gm.getDeckOfCards());
		Card[] cd = gm.getDeckOfCards();
		
		
		assertThat(cd[1].getCardColour()).isEqualTo("Blue");  
		assertThat(cd[1].getCardType()).isEqualTo("Step Forward");
		
		assertThat(cd[0].getCardColour()).isEqualTo("Yellow");  
		assertThat(cd[0].getCardType()).isEqualTo("Turn Left");
		
		assertThat(cd[2].getCardColour()).isEqualTo("Purple");  
		assertThat(cd[2].getCardType()).isEqualTo("Turn Right");
		
		assertThat(cd[3].getCardColour()).isEqualTo("Brown");  
		assertThat(cd[3].getCardType()).isEqualTo("Bug");
		

		
	}

	@Test
	void testMoveTurtleMethod() {
		GameBoardModel gm = new GameBoardModel(4);
		//gm.createRobotTurtles();
		//gm.createCardDeck();
		Card[] cd = new Card[40];
		cd = gm.getDeckOfCards(); //cd[0] turn left, cd[1] move forward, cd[2] turn right, cd[3] bug
		
		RobotTurtle r1 = new RobotTurtle(1,1,2,"a", "Blue"); //facing east, +1 to x
		RobotTurtle r2 = new RobotTurtle(4,5,3,"b", "Red"); //facing south
		GameBoard board = gm.getGameBoard();
		Tile[][] tileList = board.getTileList();

		gm.moveTurtle(r1, cd[1]); //move forward 1 to the east
		
		assertThat(r1.getXPos()).isEqualTo(2);
		assertThat(r1.getYPos()).isEqualTo(1);
		
		gm.moveTurtle(r1, cd[0]); //turn left, so face the south which is d=1
		
		assertThat(r1.getDirection()).isEqualTo(1);
		
		gm.moveTurtle(r1, cd[3]); //bug card direction should be back to original value
		assertThat(r1.getDirection()).isEqualTo(2);
		
		gm.moveTurtle(r2, cd[2]);//turn right
		assertThat(r2.getDirection()).isEqualTo(4);
		gm.moveTurtle(r2, cd[3]); //bug
		assertThat(r2.getDirection()).isEqualTo(3);
		
		gm.moveTurtle(r2, cd[2]); //turn right when originally facing south, so facing west which is d=4
		assertThat(r2.getDirection()).isEqualTo(4);
		
		gm.moveTurtle(r2, cd[1]); //moving forward when facing west now, x-1
		assertThat(r2.getXPos()).isEqualTo(3);
		assertThat(r2.getYPos()).isEqualTo(5);
		
		
		
		gm.moveTurtle(r2, cd[3]); //bug card
		assertThat(r2.getXPos()).isEqualTo(4);
		assertThat(r2.getYPos()).isEqualTo(5);
	}
	
	
	
	
}


