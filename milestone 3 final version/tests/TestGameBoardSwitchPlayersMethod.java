package tests;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import model.GameBoardModel;
import model.State;
import model.Player;
import model.RobotJewel;
import model.RobotTurtle;
import model.Card;
import model.GameBoard;
class TestGameBoardSwitchPlayersMethod {

	@ParameterizedTest   
	@CsvSource({       
		"2", // first set of arguments      
		//"1", // second set of arguments  
		//"3", // third set of arguments
 
		})  
	void testSwitchPlayersMethod(int numPlayers) {
		System.out.println("entered method");
		GameBoardModel gm = new GameBoardModel(numPlayers);
		Player p = gm.getCurrentPlayer(); //starts off with current player = player one = 0
		State s = gm.getCurrentState(); //starts off PLAYING
		GameBoard board = gm.getGameBoard();

		Card[] c = gm.getDeckOfCards();
		ArrayList<RobotTurtle> rt = gm.getListOfRobotTurtles();
		assertThat(s).isEqualTo(State.PLAYING);  
		assertThat(p).isEqualTo(Player.PLAYER_ONE);
		
		gm.moveTurtle(rt.get(0), c[1]);
		System.out.println(rt.get(0).getXPos());
		p = gm.getCurrentPlayer(); 
		s = gm.getCurrentState();
		if (numPlayers > 1) //if more than 1 player
		{
			assertThat(s).isEqualTo(State.PLAYING);  
			assertThat(p).isEqualTo(Player.PLAYER_TWO);
			rt.get(1).setXPos(4); //put turtle on jewel
			rt.get(1).setYPos(4);
			gm.moveTurtle(rt.get(1), c[0]); //make turtle turn to pretend turtle just got on jewel after a move
			
			p = gm.getCurrentPlayer(); 
			s = gm.getCurrentState();
			assertThat(s).isEqualTo(State.PLAYING);  
			assertThat(p).isEqualTo(Player.PLAYER_ONE);
			rt.get(0).setXPos(3); //put turtle on jewel
			rt.get(0).setYPos(3);
			gm.moveTurtle(rt.get(0), c[0]); //make turtle turn to pretend turtle just got on jewel after a move
			
			p = gm.getCurrentPlayer(); 
			s = gm.getCurrentState();
			assertThat(s).isEqualTo(State.ALL_PLAYERS_FIN);  
			assertThat(p).isEqualTo(Player.NULL);
			
		}
		else //if theres only 1 player, then it doesnt switch to a different player
		{
			assertThat(s).isEqualTo(State.PLAYING);  
			assertThat(p).isEqualTo(Player.PLAYER_ONE);
		}
		
		
		
	

		
	}
	

}
