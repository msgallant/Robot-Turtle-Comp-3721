package model;
//This class responsibility is to check if a player has won the game and return a boolean[]
//containing true if they've won and false if still playing
//It also checks if all players have won and returns the state which is either playing
//or all players fin
import model_gameboard.GameBoard;
import model_tiles.Tile;
import model_turtle.RobotTurtle;

public class WinChecker {
	
	private GameBoard gameBoard;
	private boolean[] playersPlaying;
	Player currentPlayer;
	public WinChecker(GameBoard gb, boolean[] players, Player p)
	{
		gameBoard = gb;
		playersPlaying = players;
		currentPlayer = p;
	}
	//checking if this robot turtle is on a jewel and is finished.
	public boolean[] checkIfWon(RobotTurtle rt)
	{
		  int x = rt.getXPos();
		  int y = rt.getYPos();
		  Tile[][] tl = gameBoard.getTileList();
		  if (tl[x][y].getTileType() == "Robot Jewel")
		  {
			  if (currentPlayer == Player.PLAYER_ONE)
			  {
				  playersPlaying[0] = false;

			  }
			  else if (currentPlayer == Player.PLAYER_TWO)
			  {
				  playersPlaying[1] = false;

			  }
			  else if (currentPlayer == Player.PLAYER_THREE)
			  {
				  playersPlaying[2] = false;

			  }
			  else if (currentPlayer == Player.PLAYER_FOUR)
			  {
				  playersPlaying[3] = false;

			  }
			  
			  gameBoard.robotJewelClaimed(x, y); //make it so no other player can claim same jewel
		  }
		  return playersPlaying;
			  
			/*  playersFin = checkIfAllPlayersFinished(); //change state & player if true
		  }
		  if (playersFin == false) //if not all players finished, continue game and switch to the next players turn
		  {
			  switchPlayer();
		  }*/
	}
	  
	public State checkIfAllPlayersFinished(int numPlayers)
	{
		  for (int i =0; i < numPlayers; i++)
		  {
			  if (playersPlaying[i] == true)
			  {
				  return State.PLAYING;
			  }
		  }
		  //currentPlayer = currentPlayer.NULL;
		  return State.ALL_PLAYERS_FIN;
	}
}
