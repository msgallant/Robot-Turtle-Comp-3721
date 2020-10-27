import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestGameBoardDisplay {

	public static void main(String[] args)
	{
		GameBoardDisplay view = new GameBoardDisplay("a"); 
	}
	/*@ParameterizedTest   
	@CsvSource({       
		"a", // first set of arguments     
		"b", // second set of arguments      
		"c" // third set of arguments   
		})  
	void GameBoardDisplayConstructor(String x) {
		GameBoardDisplay view = new GameBoardDisplay(x); 

	}*/

}
