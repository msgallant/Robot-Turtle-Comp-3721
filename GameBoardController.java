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
	private Scanner in;
	private GameBoardModel model;
	private GameBoardView view;
	private ArrayList<JButton> buttonList;
	private ArrayList<JButton> tileList;
	private int numberPlayers;
	private int move;
	private JButton oneP, twoP, threeP, fourP;
	private JButton T1, T2, T3, T4, T5, T6, T7,T8,T9,T10,
					T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,
					T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,
					T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,
					T41,T42,T43,T44,T45,T46,T47,T48,T49,T50,
					T51,T52,T53,T54,T55,T56,T57,T58,T59,T60,
					T61,T62,T63,T64;


	public GameBoardController()
	{
		in = new Scanner(System.in);
		model = new GameBoardModel();
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
			}
		});
		twoP = buttonList.get(1);
		twoP.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = 2;
			}
		});
		threeP = buttonList.get(2);
		threeP.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = 3;
			}
		});
		fourP = buttonList.get(3);
		fourP.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				numberPlayers = 4;
			}
		});	
	}

	private void setUpGame() 
	{
		view.addObsticles();
		tileList=view.getTileList();
		// tiles {T1(0,0), T8(0,7), T57(7,0), T64(7,7), T29(3,4), T28(3,3), T36(4,3), T37(4,4)} are invalid.
		T1 = tileList.get(0);
		T1.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				view.invalidSelection();
			}
		});
		T2 = tileList.get(1);
		T2.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				view.addStoneWalls(((tileList.indexOf(T2))+1) ,T2.getX(),T2.getY());
			}
		});
		T3 = tileList.get(2);
		T3.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				view.addStoneWalls((tileList.indexOf(T3))+1),T3.getX(),T3.getY());
			}
		});
		T4 = tileList.get(3);
		T4.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				view.addStoneWalls((tileList.indexOf(T4))+1)T4.getX(),T4.getY());
			}
		});
		T5 = tileList.get(4);
		T5.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				view.addStoneWalls((tileList.indexOf(T5))+1)T5.getX(),T5.getY());
			}
		});
	}

	public void promptMove()
	{
		Player currentPlayer = model.getCurrentPlayer();
		if(currentPlayer !=Player.NULL)
		{
			view.pickACard();
			view.validate();
			if(view.m == 3)
			{
				view.pickACard();
				view.validate();
			}
			move = view.m;
		}
		
	}


	public void playGame();
	{
		State currentState = model.getCurrentState();
		do 
		{
			initGame();
			setUpGame();
			promptMove();
			currentState = model.getCurrentState();
			view.GameBoardView(model.getBoard());
		}
		while(currentState != State.ALL_PLAYER_FIN);

	}


	public static void main(String[] args)
	{
		GameBoardController RT = new GameBoardController();
		RT.playGame();
	}
}




