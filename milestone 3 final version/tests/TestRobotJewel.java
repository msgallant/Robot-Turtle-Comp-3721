package tests;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import model.Tile;
import model.RobotJewel;


class TestRobotJewel {

	@ParameterizedTest   
	@CsvSource({       
		"0, 1, Red", // first set of arguments      
		"4, 4, Green", // second set of arguments      
		"6, 7, Blue" // third set of arguments  
		})  
	void robotJewelConstructor(int x, int y, String c) {
		RobotJewel j = new RobotJewel(x,y,c);
		
		assertThat(j.getXPos()).isEqualTo(x);
		assertThat(j.getYPos()).isEqualTo(y);

		assertThat(j.getJewelColour()).isEqualTo(c);
		assertThat(j.getColour()).isEqualTo(c);
		assertThat(j.getTileType()).isEqualTo("Robot Jewel");
		
		
	}

}
