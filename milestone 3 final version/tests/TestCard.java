package tests;
import model.Card;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestCard {

	@ParameterizedTest   
	@CsvSource({       
		"Step Forward, Blue", // first set of arguments      
		"Turn Left, Yellow", // second set of arguments      
		"Turn Right, Purple" // third set of arguments  
		})  
	void cardConstructor(String t, String c ) {
		Card card = new Card(t, c);
		
		assertThat(card.getCardType()).isEqualTo(t);
		assertThat(card.getCardColour()).isEqualTo(c);
		
		
	}

}
