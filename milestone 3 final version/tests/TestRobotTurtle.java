package tests;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import model.RobotTurtle;

class TestRobotTurtle {

	@ParameterizedTest   
	@CsvSource({       
		"0, 1, 2, a, Purple", // first set of arguments      
		"4, 4, 4, b, Blue", // second set of arguments      
		"6, 7, 1, c, Green" // third set of arguments  
		})  
	void robotTurtleConstructor(int x, int y, int d, String n, String c) {
		RobotTurtle t = new RobotTurtle(x,y, d, n, c);
		
		assertThat(t.getXPos()).isEqualTo(x);
		assertThat(t.getYPos()).isEqualTo(y);
		assertThat(t.getDirection()).isEqualTo(d);
		assertThat(t.getName()).isEqualTo(n);
		assertThat(t.getColour()).isEqualTo(c);
		
		
		
	}
	
	@ParameterizedTest   
	@CsvSource({       
		"0, 1, 2, a, Purple", // first set of arguments      
		"4, 4, 2, b, Blue", // second set of arguments      
		"6, 7, 2, c, Green" // third set of arguments  
		})  
	void updateTurtlePositionFacingEastTest(int x, int y, int d, String n, String c) {
		RobotTurtle t = new RobotTurtle(x,y, d, n, c);
		t.updateTurtlePosition(); //this method updates position assuming they have successfully moved forward by 1,
		//so all turtles d=2 meaning they all face east, so their x value should incr by 1 since they move the right
		
		assertThat(t.getXPos()).isEqualTo(x+1);
		assertThat(t.getYPos()).isEqualTo(y);
		assertThat(t.getDirection()).isEqualTo(d);
		assertThat(t.getName()).isEqualTo(n);
		assertThat(t.getColour()).isEqualTo(c);
		
		
		
	}
	@ParameterizedTest   
	@CsvSource({       
		"0, 1, 1, a, Purple", // first set of arguments      
		"4, 4, 1, b, Blue", // second set of arguments      
		"6, 7, 1, c, Green" // third set of arguments  
		})  
	void updateTurtlePositionFacingNorthTest(int x, int y, int d, String n, String c) {
		RobotTurtle t = new RobotTurtle(x,y, d, n, c);
		t.updateTurtlePosition(); //this method updates position assuming they have successfully moved forward by 1,
		//so all turtles d=1 meaning they all face north, so their y value should decease by 1 since they move up
		
		assertThat(t.getXPos()).isEqualTo(x);
		assertThat(t.getYPos()).isEqualTo(y-1);
		assertThat(t.getDirection()).isEqualTo(d);
		assertThat(t.getName()).isEqualTo(n);
		assertThat(t.getColour()).isEqualTo(c);
		
		
		
	}
	@ParameterizedTest   
	@CsvSource({       
		"0, 1, 4, a, Purple", // first set of arguments      
		"4, 4, 4, b, Blue", // second set of arguments      
		"6, 7, 4, c, Green" // third set of arguments  
		})  
	void updateTurtlePositionFacingWestTest(int x, int y, int d, String n, String c) {
		RobotTurtle t = new RobotTurtle(x,y, d, n, c);
		t.updateTurtlePosition(); //this method updates position assuming they have successfully moved forward by 1,
		//so all turtles d=4 meaning they all face west, so their x value should decease by 1 since they move left
		
		assertThat(t.getXPos()).isEqualTo(x-1);
		assertThat(t.getYPos()).isEqualTo(y);
		assertThat(t.getDirection()).isEqualTo(d);
		assertThat(t.getName()).isEqualTo(n);
		assertThat(t.getColour()).isEqualTo(c);
		
		
		
	}
	@ParameterizedTest   
	@CsvSource({       
		"0, 1, 3, a, Purple", // first set of arguments      
		"4, 4, 3, b, Blue", // second set of arguments      
		"6, 7, 3, c, Green" // third set of arguments  
		})  
	void updateTurtlePositionFacingSouthTest(int x, int y, int d, String n, String c) {
		RobotTurtle t = new RobotTurtle(x,y, d, n, c);
		t.updateTurtlePosition(); //this method updates position assuming they have successfully moved forward by 1,
		//so all turtles d=3 meaning they all face south, so their y value should increase by 1 since they move down
		
		assertThat(t.getXPos()).isEqualTo(x);
		assertThat(t.getYPos()).isEqualTo(y+1);
		assertThat(t.getDirection()).isEqualTo(d);
		assertThat(t.getName()).isEqualTo(n);
		assertThat(t.getColour()).isEqualTo(c);
		
		
		
	}
	@ParameterizedTest   
	@CsvSource({       
		"0, 1, 3, a, Purple", // first set of arguments      
		"4, 4, 2, b, Blue", // second set of arguments      
		"6, 7, 4, c, Green" // third set of arguments  
		})  
	void directionShiftRightTest(int x, int y, int d, String n, String c) {
		RobotTurtle t = new RobotTurtle(x,y, d, n, c);
		int oldDir = d;
		t.directionShiftRight(); //turtle rotating to right, so direction increasing by 1 unless direction is 4 and must loop around to 1
		
		assertThat(t.getXPos()).isEqualTo(x);
		assertThat(t.getYPos()).isEqualTo(y);
		if (oldDir < 4)
		{
			assertThat(t.getDirection()).isEqualTo(oldDir+1);
		}
		else
		{
			assertThat(t.getDirection()).isEqualTo(1);
		}
		assertThat(t.getName()).isEqualTo(n);
		assertThat(t.getColour()).isEqualTo(c);
		
		
		
	}
	@ParameterizedTest   
	@CsvSource({       
		"0, 1, 4, a, Purple", // first set of arguments      
		"4, 4, 1, b, Blue", // second set of arguments      
		"6, 7, 3, c, Green" // third set of arguments  
		})  
	void directionShiftLeftTest(int x, int y, int d, String n, String c) {
		RobotTurtle t = new RobotTurtle(x,y, d, n, c);
		int oldDir = d;
		t.directionShiftLeft(); //turtle rotating to left, so direction decreasing by 1 unless direction is 1 and must loop around to 4
		assertThat(t.getXPos()).isEqualTo(x);
		assertThat(t.getYPos()).isEqualTo(y);
		if (oldDir > 1)
		{
			assertThat(t.getDirection()).isEqualTo(oldDir-1);
		}
		else
		{
			assertThat(t.getDirection()).isEqualTo(4);
		}
		assertThat(t.getName()).isEqualTo(n);
		assertThat(t.getColour()).isEqualTo(c);
		
		
		
	}


}
