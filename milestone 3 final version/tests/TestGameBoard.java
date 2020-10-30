package tests;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import model.GameBoard;
import model.Tile;
import model.RobotTurtle;
class TestGameBoard {

	@ParameterizedTest   
	@CsvSource({       
		"0, 1", // first set of arguments      
		"4, 4", // second set of arguments      
		"6, 7", // third set of arguments  
		"2, 4" // fourth set of arguments  
		})  
	void gameBoardAddStoneWallMethod(int x, int y) {
		GameBoard g = new GameBoard(8);
		Tile[][] tileList = g.addStoneWall(x, y);

		assertThat(tileList[x][y].getXPos()).isEqualTo(x);
		assertThat(tileList[x][y].getYPos()).isEqualTo(y);
		assertThat(tileList[x][y].getTileType()).isEqualTo("Stone Wall");

		
	}
	
	@ParameterizedTest   
	@CsvSource({       
		"0, 1", // first set of arguments      
		"4, 4", // second set of arguments      
		"6, 7", // third set of arguments  
		"2, 4" // fourth set of arguments  
		})  
	void gameBoardAddCrateMethod(int x, int y) {
		GameBoard g = new GameBoard(8);
		Tile[][] tileList = g.addCrate(x, y);

		assertThat(tileList[x][y].getXPos()).isEqualTo(x);
		assertThat(tileList[x][y].getYPos()).isEqualTo(y);
		assertThat(tileList[x][y].getTileType()).isEqualTo("Crate");

		
	}
	@ParameterizedTest   
	@CsvSource({       
		"0, 1, Blue", // first set of arguments      
		"4, 4, Pink", // second set of arguments      
		"6, 7, Red", // third set of arguments  
		"2, 4, Green" // fourth set of arguments  
		})  
	void gameBoardAddRobotJewelMethod(int x, int y, String colour) {
		GameBoard g = new GameBoard(8);
		int[] pos = {x, y};
		Tile[][] tileList = g.addRobotJewel(pos, colour);

		assertThat(tileList[x][y].getXPos()).isEqualTo(x);
		assertThat(tileList[x][y].getYPos()).isEqualTo(y);
		assertThat(tileList[x][y].getTileType()).isEqualTo("Robot Jewel");

		
	}
	@ParameterizedTest   
	@CsvSource({       
		"0, 1", // first set of arguments      
		"4, 4", // second set of arguments      
		"6, 7", // third set of arguments  
		"2, 4" // fourth set of arguments  
		})  
	void gameBoardAddRobotTurtleMethod(int x, int y) {
		GameBoard g = new GameBoard(8);
		RobotTurtle t = new RobotTurtle(1,4,3,"a", "pink");
		int[] pos = {x, y};
		Tile[][] tileList = g.addRobotTurtle(pos, t);

		assertThat(tileList[x][y].getXPos()).isEqualTo(x);
		assertThat(tileList[x][y].getYPos()).isEqualTo(y);
		assertThat(tileList[x][y].getOccupied()).isEqualTo(true);

		
	}
	
	
	@ParameterizedTest   
	@CsvSource({       
		"0, 1, 0, 0", // first set of arguments      
		"4, 4, 5, 4", // second set of arguments      
		"6, 7, 6, 6", // third set of arguments  
		"2, 4, 3, 4" // fourth set of arguments  
		})  
	void gameUpdateTurtlePositionOnBoardMethod(int x, int y, int x2, int y2) {
		GameBoard g = new GameBoard(8);
		int[] pos1 = {x, y};
		int[] pos2 = {x2, y2};
		RobotTurtle rt = new RobotTurtle(1,2,2,"a", "Red");

		Tile[][] tileList = g.updateTurtlePositionOnBoard(pos2, pos1, rt); //takes in new postion then old position

		assertThat(tileList[pos1[0]][pos1[1]].getOccupied()).isEqualTo(false);
		assertThat(tileList[pos1[0]][pos1[1]].getRobotTurtle()).isEqualTo(null);
		assertThat(tileList[pos2[0]][pos2[1]].getOccupied()).isEqualTo(true);
		assertThat(tileList[pos2[0]][pos2[1]].getRobotTurtle()).isEqualTo(rt);
		
	}
	
	@ParameterizedTest   
	@CsvSource({       
		"0, 1, 0", // first set of arguments      
		"4, 4, 1", // second set of arguments      
		//"6, 5, 2", // third set of arguments  
		"2, 4, 3" // fourth set of arguments  
		})  
	void gameCheckIfTurtleCanMoveMethod(int x, int y, int x2) {
		GameBoard g = new GameBoard(8);
		int[] pos1 = {x, y};
		if (y + 1 < 7)
		{
			g.addCrate(x, y+1); //add crate to your south
		}
		if (y + 2 < 7 )
		{
			g.addCrate(x, y+2);
		}
		if (x - 1 > 0)
		{
			g.addStoneWall(x-1, y); //add crate to your west
		}
		RobotTurtle rt;
		boolean expectedToMove = false;
		if (x2 == 0)
		{
			rt = new RobotTurtle(x,y,1,"a", "Red");
			expectedToMove = true;
		}
		else if (x2 == 1)
		{
			rt = new RobotTurtle(x,y,2,"a", "Red");
			expectedToMove = true;
		}
		else if (x2 == 2)
		{
			rt = new RobotTurtle(x,y,3,"a", "Red");
		}
		else
		{
			rt = new RobotTurtle(x,y,4,"a", "Red");
		}
		
		
		

		assertThat(g.checkIfTurtleCanMove(rt)).isEqualTo(expectedToMove);
		
		
		
		
	}
	
	
}