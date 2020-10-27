import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

public class GBController
{
	private int numberPlayers;
	private ArrayList<JButton> buttonList;
	private JButton oneP, twoP, threeP, fourP;
	private GameBoardModel model;
	private GameBoardDisplay view;
	private int mouseClickStoneWall, mouseClickCrate;
	private JButton[][] tileButtons;
	boolean validPos = true;
	int[] invalidXPos = {0, 0, 7, 7, 3, 3, 4, 4};
	int[] invalidYPos = {0, 7, 0, 7, 4, 3, 3, 4};
	
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
	public void setUpGame()
	{
		System.out.println(numberPlayers);
		model = new GameBoardModel(numberPlayers);
		view = new GameBoardDisplay("Robot Turtles"); 
		view.drawCurrentGame(model.getTileList());
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
				if (validPos)
				{
					if(mouseClickStoneWall<19)
					{
						System.out.println("hi" + "c: " + col + " r " + row);
						model.addStoneWall(col, row);
						mouseClickStoneWall++;
						view.drawCurrentGame(model.getTileList());
						System.out.println(model.getTileList()[col][row].getTileType());
					}
					else if(mouseClickCrate<7)
					{
						view.drawCurrentGame(model.getTileList());
						model.addCrate(col, row);
						mouseClickCrate++;
					}
				}
				else
				{
					//view.invalidSelection();
				}
			}
		});
		System.out.println("redraw");
		
		printTileList();
		
		
		if (mouseClickStoneWall == 20 && mouseClickCrate == 7)
		{
			startGame();
		}
	}
	public void printTileList()
	{
		for (int i =0; i< 7; i++)
		{
			for (int j =0; j < 7; j++)
			{
				System.out.println(model.getTileList()[j][i].getTileType() + " " + model.getTileList()[j][i].getOccupied() + " " + i + " " + j);
			}
			
		}
	}
		
		
	public void startGame()
	{
		view.drawCurrentGame(model.getTileList());
	}
	public int getNumPlayers()
	{
		return numberPlayers;
	}
	public static void main(String[] args)
	{
		GBController gbc = new GBController();
		gbc.findNumPlayers();
	
		
	}
}