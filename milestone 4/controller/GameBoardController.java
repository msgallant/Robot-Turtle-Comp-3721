package controller;
//Marcia Gallant
//This class gets all user input such as the number of players and which cards the players picked.
//It gives this information to the model which translates the information
//and makes a model of the current state of the game in Tile[][] tileList
//the controller takes this and passes it into GameBoardConverter which translates the model
//into String[][], String and int and then passes the converter with this translated
//information to GameBoardDisplay which can use it to draw the current Game Board.
import view.GameBoardDisplay;
import view.NumPlayersScreen;

import model.GameBoardModel;
import model.State;
import model.Player;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

public class GameBoardController
{
	private int numberPlayers;
	private int maxNumPlayers = 4;
	private ArrayList<JButton> buttonList;
	private GameBoardModel model;
	private GameBoardDisplay view;
	private int mouseClickStoneWall = 0;
	private int mouseClickCrate =0;
	private JButton[][] tileButtons;
	private JButton[] cardButtons;
	private JButton confirm;
	private int cardChoiceIndex;
	boolean validPos = true;
	boolean validatedCard = false;
	int[] invalidXPos; //= possibilities = {0, 0, 7, 7, 3, 3, 4, 4};
	int[] invalidYPos; //= possibilities = {0, 7, 0, 7, 4, 3, 3, 4};
	private int maxStoneWalls;
	private int maxCrates; 
	private boolean gameStarted = false;
	private GameBoardConverter converter;


	protected void findNumPlayers()
	{
		NumPlayersScreen s1 = new NumPlayersScreen();
		s1.findNumPlayers();
		buttonList= s1.getButtonList();
		for (int i =0; i < maxNumPlayers; i++)
		{
			addMouseListenerToAButton(i);
		}
	}
	protected void addMouseListenerToAButton(int i)
	{
		buttonList.get(i).addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = i + 1;
				setUpGame();
			}
		});
	}
	
	protected void giveViewGameInformation() //pass the view what colours, card types and 
	//what objects like robot turtle, jewel, stone walls, crates can be on a tile
	{
		view.setGameInformation(model.getColours(), model.getCardTypes(), model.getTilePosibilities());
	}
	protected void displayCurrentGameBoard()
	{
		converter = new GameBoardConverter();
		converter.setGameBoardModel(model);
		view.drawCurrentGame(converter);
	}
	protected void setUpGame()
	{
		//System.out.println(numberPlayers);
		model = new GameBoardModel(numberPlayers);
		view = new GameBoardDisplay("Robot Turtles");
		view.setGameInformation(model.getColours(), model.getCardTypes(), model.getTilePosibilities());
		view.drawCards();
		
		//get positions user cant put crates and stone walls
		invalidXPos = model.getInvalidXPositions(); 
		invalidYPos = model.getInvalidYPositions();
		
		maxStoneWalls = model.getMaxStoneWalls(); //get how many stone walls user needs to put on board
		maxCrates = model.getMaxCrates();
		
		
		displayCurrentGameBoard();
		
		view.addObsticles();
		tileButtons=view.getTileButtons();
		
		
		
		for (int row = 0; row < 8; row++)
		{
			for (int col =0; col < 8; col++)
			{
				getBoardSetUpFromUser(col, row); 
				//adds mouse listeners to all tile buttons. 
				//also stores col, row coordinates so can figure out which listener was clicked
				//and can add crate or stonewall to correct tile.
				
			}
			
		}
		
	}
	protected void getBoardSetUpFromUser(int col, int row)
	{
		
		tileButtons[col][row].addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				// tiles {T1(0,0), T8(0,7), T57(7,0), T64(7,7), T29(3,4), T28(3,3), T
				//36(4,3), T37(4,4)} are invalid.
				for (int i =0; i < invalidXPos.length; i++)
				{
					if (col == invalidXPos[i] && row == invalidYPos[i])
					{
						validPos = false;
					}
				}
				if (validPos) //if not a tile that may have a turtle or robot jewel it's a valid tile to put a stone wall or crate
				{
					boolean placed; //was an object successfully placed on the board? Or was there an object in the way
					if(mouseClickStoneWall<maxStoneWalls)
					{
						
						placed = model.addStoneWall(col, row);
						if (placed)
						{
							mouseClickStoneWall++;
						}
						displayCurrentGameBoard();
					}
					else if(mouseClickCrate< maxCrates)
					{
						
						placed = model.addCrate(col, row);
						if (placed)
						{
							mouseClickCrate++;
						}
						displayCurrentGameBoard();
						
					}
				}
				else
				{
					validPos = true; //resetting validPos, so, if next click is valid, it doesn't still think it's invalid
					view.invalidSelection();
				}
				if ((mouseClickStoneWall == maxStoneWalls)); //if all stone walls are placed
				{
					if (mouseClickCrate == maxCrates) //if all crates are placed
					{
						if (gameStarted == false) //if game has already started because they placed all crates and walls but continue trying to put more in
						{
							gameStarted = true; //these mouse listeners are useless now
							System.out.println("game starting!");
							startGame();
						}
					}
					
					
				}
			}
		});
		
		
		
		
	}
	protected void startGame()
	{
		displayCurrentGameBoard();
		//cardButtons = view.getCardButtons();
		setUpCardsToBePicked();
			
	}
	
		
	//allows the cards that are displayed to the user to be picked
	protected void setUpCardsToBePicked()
	{
		cardButtons = view.getCardButtons();//adds mouse listeners to all cards
		for (int i =0; i < cardButtons.length; i++) 
		{
			addMouseListenerToACard(i);
		}
		addMouseListenerToConfirmButton();
		
	}
	
	//i represents the index the card has if you get Card[] c = model.getDeckOfCards();
	//and is index of the JButton for the card in cardButtons 
	protected void addMouseListenerToACard(int i) 
	{
		
			cardButtons[i].addMouseListener(new MouseAdapter() //setting up a mouse listener for a card
			{
				public void mouseClicked(MouseEvent arg0) 
				{
					if (i != 3) //if not bug card
					{
						view.disableAllCardButtons();
						view.enableCardButton(3); //enable bug card
						view.enableConfirmButton();
						cardChoiceIndex = i; //store card choice, so, 
						//we know which card the player confirmed if player chooses to confirm
						
					}
					else //bug card
					{
						view.enableAllCardButtons();
						view.disableCardButton(3);
						view.disableConfirmButton();
					}
					
					
				}
			});
		
	}
	protected void addMouseListenerToConfirmButton() //user must choose either confirm or bug card
	//after picking a card that moves the turtle
	{
		confirm = view.getConfirmButton();
		confirm.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent arg0)
					{
						
						validateCardChoice();
						
					}
				});
	}
	//private int turnsPlayed = 0;
	protected void validateCardChoice()
	{
		Player[] pList = {Player.PLAYER_ONE, Player.PLAYER_TWO, Player.PLAYER_THREE, Player.PLAYER_FOUR};
		validatedCard = true; //player didnt pick bug card
		boolean turtleMoved = false;
		Player currentPlayer = model.getCurrentPlayer(); //need to know which player turtle should move

		//player's card choice is stored in variable cardChoiceIndex
		
		for (int i =0; i< pList.length; i++)
		{
			if (currentPlayer == pList[i])
			{
				turtleMoved = model.moveTurtle(model.getListOfRobotTurtles().get(i), model.getDeckOfCards()[cardChoiceIndex]);
			}
		}

		if (turtleMoved == true) //draw current gameboard
		{
			displayCurrentGameBoard();			
		}
		else 
		{
			view.turtleUnableToMove();
		}
		
		State currentState = model.getCurrentState();
		if (currentState == State.PLAYING) //game continues
		{
			view.enableAllCardButtons();
			view.disableCardButton(3);
			view.disableConfirmButton();
			displayNotificationOfWhoseTurnItIs();
		}
		else //game finishes
		{
			view.disableAllCardButtons();
			view.disableConfirmButton();
			view.closeGame();
			view.finish();
			
		}
	}
	
	protected void displayNotificationOfWhoseTurnItIs()
	{
		Player currentPlayer = model.getCurrentPlayer();
		Player[] pList = {Player.PLAYER_ONE, Player.PLAYER_TWO, Player.PLAYER_THREE, Player.PLAYER_FOUR};
		for (int i =0; i < pList.length; i++)
		{
			if (currentPlayer == pList[i])
			{
				view.changePlayersTurnIndicatorLabel(i + 1);
			}
		}

	}
		
	
	protected int getNumPlayers()
	{
		return numberPlayers;
	}
	//this method is just for testing to make sure tile list is correct, not actually for the game
		/*public void printTileList()
		{
			for (int i =0; i< 8; i++)
			{
				for (int j =0; j < 8; j++)
				{
					System.out.println(model.getTileList()[j][i].getTileType() + " " + model.getTileList()[j][i].getOccupied() + " " + i + " " + j);
				}
				
			}
		}*/
	
	
}