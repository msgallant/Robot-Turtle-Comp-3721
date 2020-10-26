import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;




public class GameBoardController
{
	private GameBoardModel model;
	private GameBoardView view;
	private Scanner in;
	private ArrayList<JButton> buttonList;
	private ArrayList<JButton> tileList;
	private int numberPlayers;
	private int mouseClickWall=0;
	private int mouseClickCrate=0;
	private int tileNumber;
	private int m;
	private JButton oneP, twoP, threeP, fourP;



	public GameBoardController()
	{
		in = new Scanner(System.in);
		//model = new GameBoardModel(numberPlayers);
		view = new GameBoardView("Robot Turtles");

	}

	/**
	 * Prompting the beginning of the game, number of players.
	 */
	public void initGame()
	{
		view.numPlayers();
		buttonList=view.getButtonList();
		oneP = buttonList.get(0);
		oneP.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = 1;
				model = new GameBoardModel(numberPlayers);
				view.displayBoard(model.getTileList());
			}
		});
		twoP = buttonList.get(1);
		twoP.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = 2;
				model = new GameBoardModel(numberPlayers);
				view.displayBoard(model.getTileList());
			}
		});
		threeP = buttonList.get(2);
		threeP.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = 3;
				model = new GameBoardModel(numberPlayers);
				view.displayBoard(model.getTileList());
			}
		});
		fourP = buttonList.get(3);
		fourP.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = 4;
				model = new GameBoardModel(numberPlayers);
				view.displayBoard(model.getTileList());
			}
		});	
		in.nextInt();
		//model = new GameBoardModel(numberPlayers);
		//view.displayBoard(model.getTileList());
		
	}

	private void setUpGame() 
	{
		view.addObsticles();
		tileList=view.getTileList();
		// tiles {T1(0,0), T8(0,7), T57(7,0), T64(7,7), T29(3,4), T28(3,3), T36(4,3), T37(4,4)} are invalid.
		int[] invalidArr = {0,7,27,28,35,36,56,63};
		//in.nextInt();
		for(int i=0; i<64; i++)
		{
			tileNumber=i;
			tileList.get(i).addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) 
				{
					for(int j=0; j<invalidArr.length; j++)
					{
						if( tileNumber != invalidArr[j] )
						{
							if(mouseClickWall<19)
							{
								model.addStoneWall((tileList.get(tileNumber)).getX(), (tileList.get(tileNumber)).getY());
								mouseClickWall++;
							}
							else if(mouseClickCrate<7)
							{
								model.addCrate((tileList.get(tileNumber)).getX(), (tileList.get(tileNumber)).getY());
								mouseClickCrate++;
							}
						}
						else
						{
							view.invalidSelection();
						}
					}
				}
			});
		}
		view.displayBoard(model.getTileList());
	}

	public void promptMove()
	{
		Player currentPlayer = model.getCurrentPlayer();
		Card[] c = model.getDeckOfCards();
		if(currentPlayer == Player.PLAYER_ONE)
		{
			view.pickACard();
			view.validate(m);
			while(view.m == 3)
			{
				view.pickACard();
				view.validate(m);
			}
			in.nextInt();
			model.moveTurtle(model.getListOfRobotTurtles().get(0), c[m]);
		}
		
		if(currentPlayer == Player.PLAYER_TWO)
		{
			view.pickACard();
			view.validate(m);
			while(view.m == 3)
			{
				view.pickACard();
				view.validate(m);
			}
			in.nextInt();
			model.moveTurtle(model.getListOfRobotTurtles().get(1), c[m]);
		}
		if(currentPlayer == Player.PLAYER_THREE)
		{
			view.pickACard();
			view.validate(m);
			while(view.m == 3)
			{
				view.pickACard();
				view.validate(m);
			}
			in.nextInt();
			model.moveTurtle(model.getListOfRobotTurtles().get(2), c[m]);
		}
		if(currentPlayer == Player.PLAYER_FOUR)
		{
			view.pickACard();
			view.validate(m);
			while(view.m == 3)
			{
				view.pickACard();
				view.validate(m);
			}
			in.nextInt();
			model.moveTurtle(model.getListOfRobotTurtles().get(3), c[m]);
		}
	}


	public void playGame()
	{
		initGame();
		setUpGame();
		State currentState = model.getCurrentState();
		do 
		{		
			promptMove();
			currentState = model.getCurrentState();
			view.displayBoard(model.getTileList());
		}
		while(currentState != State.ALL_PLAYERS_FIN);

	}


	public static void main(String[] args)
	{
		GameBoardController RT = new GameBoardController();
		RT.playGame();
	}
}




