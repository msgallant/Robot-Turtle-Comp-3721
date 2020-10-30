package tests;
import model.Tile;
import model.RobotTurtle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestTile {

	@ParameterizedTest   
	@CsvSource({       
		"0, 1", // first set of arguments      
		"4, 4", // second set of arguments      
		"6, 7" // third set of arguments  
		})  
	void tileConstructor(int x, int y) {
		Tile t = new Tile(x,y);
		RobotTurtle rt = new RobotTurtle(1,1,1,"a","Purple" );
		t.setRobotTurtle(rt);
		t.findDirectionsWithNoNeighbours(); 
		assertThat(t.getXPos()).isEqualTo(x);
		assertThat(t.getYPos()).isEqualTo(y);
		assertThat(t.getNMovable()).isEqualTo(false); //all neighbours are set to null as default
		assertThat(t.getSMovable()).isEqualTo(false);
		assertThat(t.getWMovable()).isEqualTo(false);
		assertThat(t.getEMovable()).isEqualTo(false);
		
		assertThat(t.getRobotTurtle()).isEqualTo(rt);

		
	}

}
