package tests;
import model.Crate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestCrate {

	@ParameterizedTest   
	@CsvSource({       
		"0, 1", // first set of arguments     
		"4, 4", // second set of arguments      
		"6, 7" // third set of arguments   
		})  
	void crateConstructor(int x, int y) {
		Crate t = new Crate(x,y);
		t.findDirectionsWithNoNeighbours(); 
		
		
		assertThat(t.getXPos()).isEqualTo(x);
		assertThat(t.getYPos()).isEqualTo(y);
		assertThat(t.getNMovable()).isEqualTo(false);
		assertThat(t.getSMovable()).isEqualTo(false);
		assertThat(t.getWMovable()).isEqualTo(false);
		assertThat(t.getEMovable()).isEqualTo(false);

		
	}
	

}