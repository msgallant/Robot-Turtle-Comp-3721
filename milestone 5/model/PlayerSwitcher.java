package model;
//This class responsibility is to figure out whose turn it is
public class PlayerSwitcher {
	private static Player currentPlayer;
	private static int numPlayers;
	private static boolean[] playersPlaying;
	private static Player[] playerPossibilities = {Player.PLAYER_ONE, Player.PLAYER_TWO, Player.PLAYER_THREE, Player.PLAYER_FOUR};

	public PlayerSwitcher(Player p, int n, boolean[] players)
	{
		currentPlayer = p;
		numPlayers = n;
		playersPlaying = players;
	}
	public static Player switchPlayer()
	{
		int next = -1;
		if (numPlayers > 1) //player doesn't switch if only 1 player
		{
			for (int i =0; i < numPlayers; i++)
			{
				if (currentPlayer == playerPossibilities[i])
				{
					next = findNextPlayer(i);

				}
			}
			if (next == -1)
			{
				return Player.NULL;
			}
			return playerPossibilities[next];
		}
		return Player.PLAYER_ONE;
	}
	
	protected static int findNextPlayer(int curPlayerIndex)
	  {
		  //System.out.println(playersPlaying[0] + " " + playersPlaying[1] + playersPlaying[2] + playersPlaying[3]);
		  
		  int nextPlayerIndex = curPlayerIndex + 1;
		  if (curPlayerIndex == 3)
		  {
			  nextPlayerIndex = 0;
		  }

		  for (int i =0; i< 4; i++)
		  {
			  
			  if(playersPlaying[nextPlayerIndex] == true) //if the player with this index is still playing
			  {
				  return nextPlayerIndex;
			  }
			  //if player with nextPlayerIndex already won game, check if next player is still playing
			  if (nextPlayerIndex == 3) //next player after player 4 is player 1
			  {
				  nextPlayerIndex = 0;
			  }
			  else
			  {
				  nextPlayerIndex = nextPlayerIndex + 1;
			  }
			  
			  //System.out.println("next: " +nextPlayerIndex + " " + playersPlaying[nextPlayerIndex] + " cur: " + curPlayerIndex);
		  }
		  return -1; //if no next player, all finished
	  }
}
