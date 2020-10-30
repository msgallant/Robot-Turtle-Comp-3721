package controller;
import view.GameBoardDisplay;
import view.NumPlayersScreen;

import model.GameBoardModel;
import model.State;
import model.Player;
import model.Card;
import model.RobotTurtle;
import model.Tile;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

/* 
 * COMP 3721 - Object Oriented Design and Methodology
 * Term Project - Milestone 3
 * Code Version 3
 * Marcia Gallant & Loryn Losier
 * October 30th, 2020
 */

public class GameBoardController
{
	private int numberPlayers;
	private ArrayList<JButton> buttonList;
	private JButton oneP, twoP, threeP, fourP;
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
	int[] invalidXPos = {0, 0, 7, 7, 3, 3, 4, 4};
	int[] invalidYPos = {0, 7, 0, 7, 4, 3, 3, 4};
	private int maxStoneWalls = 20;
	private int maxCrates = 8;
	private boolean gameStarted = false;
	private GameBoardConverter converter;

	
	public void findNumPlayers()
	{
		NumPlayersScreen s1 = new NumPlayersScreen();
		s1.findNumPlayers();
		buttonList= s1.getButtonList();
		oneP = buttonList.get(0);
		oneP.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = 1;
				setUpGame();
			}
		});
		twoP = buttonList.get(1);
		twoP.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = 2;
				setUpGame();
			}
		});
		threeP = buttonList.get(2);
		threeP.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = 3;
				setUpGame();
			}
		});
		fourP = buttonList.get(3);
		fourP.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = 4;
				setUpGame();
			}
		});	
	}
	
	public void displayCurrentGameBoard()
	{
		converter = new GameBoardConverter();
		converter.setGameBoardModel(model);
		converter.setTileList(model.getTileList());
		view.drawCurrentGame(converter);
	}
	public void setUpGame()
	{
		System.out.println(numberPlayers);
		model = new GameBoardModel(numberPlayers);
		view = new GameBoardDisplay("Robot Turtles");

		displayCurrentGameBoard();
		
		view.addObsticles();
		tileButtons=view.getTileButtons();
		
		// tiles {T1(0,0), T8(0,7), T57(7,0), T64(7,7), T29(3,4), T28(3,3), T36(4,3), T37(4,4)} are invalid.
		int[] invalidArr = {0,7,27,28,35,36,56,63};
		
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
	public void getBoardSetUpFromUser(int col, int row)
	{
		
		tileButtons[col][row].addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				for (int i =0; i < invalidXPos.length; i++)
				{
					if (col == invalidXPos[i] && row == invalidYPos[i])
					{
						validPos = false;
					}
				}
				if (validPos) //if not a tile that may have a turtle or robot jewel it's a valid tile to put a stone wall or crate
				{
					if(mouseClickStoneWall<maxStoneWalls)
					{
						mouseClickStoneWall++;
						model.addStoneWall(col, row);
						displayCurrentGameBoard();
					}
					else if(mouseClickCrate< maxCrates)
					{
						mouseClickCrate++;
						model.addCrate(col, row);
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
	public void startGame()
	{
		displayCurrentGameBoard();
		//cardButtons = view.getCardButtons();
		setUpCardsToBePicked();
			
	}
	
		
	//allows the cards that are displayed to the user to be picked
	public void setUpCardsToBePicked()
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
	public void addMouseListenerToACard(int i) 
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
	public void addMouseListenerToConfirmButton() //user must choose either confirm or bug card
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
	public void validateCardChoice()
	{
		//turnsPlayed += 1;
		validatedCard = true; //player didnt pick bug card
		boolean turtleMoved = false;
		Player currentPlayer = model.getCurrentPlayer(); //need to know which player turtle should move
		Card[] c = model.getDeckOfCards(); //getting cards player could choose from
		//player's card choice is stored in variable cardChoiceIndex
		
		//System.out.println(model.getListOfRobotTurtles().get(0).getColour() + " " + c[i].getCardType());
		if(currentPlayer == Player.PLAYER_ONE)
		{
			//System.out.println("PLAYER 1 turn!");
			//System.out.println(model.getListOfRobotTurtles().get(0).getColour() + " " + c[cardChoiceIndex].getCardType());
			//System.out.println("direction is: " + model.getListOfRobotTurtles().get(0).getDirection());
			turtleMoved = model.moveTurtle(model.getListOfRobotTurtles().get(0), c[cardChoiceIndex]);
			/*System.out.println(model.getListOfRobotTurtles().get(0).getColour() + " " + c[cardChoiceIndex].getCardType());
			System.out.println("direction is: " + model.getListOfRobotTurtles().get(0).getDirection());
			System.out.println("turn ended!");
			System.out.println("turns played: " + turnsPlayed);
			System.out.println("");*/
		}
		if(currentPlayer == Player.PLAYER_TWO)
		{
			//System.out.println("PLAYER 2 turn!");
			//System.out.println(model.getListOfRobotTurtles().get(1).getColour() + " " + c[cardChoiceIndex].getCardType());
			//System.out.println("direction is: " + model.getListOfRobotTurtles().get(1).getDirection());
			turtleMoved = model.moveTurtle(model.getListOfRobotTurtles().get(1), c[cardChoiceIndex]);
			/*System.out.println(model.getListOfRobotTurtles().get(1).getColour() + " " + c[cardChoiceIndex].getCardType());
			System.out.println("direction is: " + model.getListOfRobotTurtles().get(1).getDirection());
			System.out.println("turn ended!");
			System.out.println("turns played: " + turnsPlayed);
			System.out.println("");*/
		}
		if(currentPlayer == Player.PLAYER_THREE)
		{
			System.out.println("player 3 turn!");
			turtleMoved = model.moveTurtle(model.getListOfRobotTurtles().get(2), c[cardChoiceIndex]);
		}
		if(currentPlayer == Player.PLAYER_FOUR)
		{
			System.out.println("player 4 turn!");
			turtleMoved = model.moveTurtle(model.getListOfRobotTurtles().get(3), c[cardChoiceIndex]);
		}
		
		if (turtleMoved == true) //draw current gameboard
		{
			displayCurrentGameBoard();
			
			
		}
		else //turtle couldn't move
		{
			System.out.println("turtle couldnt move");
			view.turtleUnableToMove();
			//get message saying turtle couldn't move
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
	
	public void displayNotificationOfWhoseTurnItIs()
	{
		Player currentPlayer = model.getCurrentPlayer();
		if(currentPlayer == Player.PLAYER_ONE)
		{
			view.changePlayersTurnIndicatorLabel(1);
		}
		else if(currentPlayer == Player.PLAYER_TWO)
		{
			view.changePlayersTurnIndicatorLabel(2);
		}
		if(currentPlayer == Player.PLAYER_THREE)
		{
			view.changePlayersTurnIndicatorLabel(3);
		}
		if(currentPlayer == Player.PLAYER_FOUR)
		{
			view.changePlayersTurnIndicatorLabel(4);
		}
	}
		
	
	public int getNumPlayers()
	{
		return numberPlayers;
	}
	//this method is just for testing to make sure tile list is correct, not actually for the game
		public void printTileList()
		{
			for (int i =0; i< 8; i++)
			{
				for (int j =0; j < 8; j++)
				{
					System.out.println(model.getTileList()[i][j].getTileType() + " " + model.getTileList()[j][i].getOccupied() + " " + i + " " + j);
				}
				
			}
		}
	public static void main(String[] args)
	{
		GameBoardController gbc = new GameBoardController();
		gbc.findNumPlayers();
	
		
	}
}