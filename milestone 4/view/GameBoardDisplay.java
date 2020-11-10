package view;
import controller.GameBoardController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.*;

import controller.GameBoardConverter;

/* 
 * COMP 3721 - Object Oriented Design and Methodology
 * Term Project - Milestone 3
 * Code Version 3
 * Marcia Gallant & Loryn Losier
 * October 30th, 2020
 */

@SuppressWarnings("serial")
public class GameBoardDisplay extends JFrame
{
	JButton[][] T = new JButton[8][8];
	JButton[] cardButtons = new JButton[4];
	private String[] colours;// = {"Blue", "Green", "Purple", "Red"};
	private String[] cardTypes; // = {"Turn Left", "Step Forward", "Turn Right", "Bug"};
	private String[] tilePossibilities; // = {"Robot Jewel", "Stone Wall", "Crate", "Robot Turtle"};
	private JButton confirm = new JButton();
	private Frame f = new Frame(); 
	private JLabel playersTurnLabel, space;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private String act;
	private String dir;
	
	public GameBoardDisplay(String title)
	{
		super(title); 
		setSize(600,800);
		
		p1 = new JPanel(); //creating a panel consisting of 2 panels: p2 and p3
		p1.setPreferredSize(new Dimension(600, 800));// hardCoded sizing
		p1.setMaximumSize(new Dimension(600, 800));  // hardCoded sizing
		p1.setMinimumSize(new Dimension(600, 800));  // hardCoded sizing
		p2 = new JPanel(); //consists of gameboard
		p2.setPreferredSize(new Dimension(500, 500));// hardCoded sizing
		p2.setMaximumSize(new Dimension(500, 500));  // hardCoded sizing
		p2.setMinimumSize(new Dimension(500, 500));  // hardCoded sizing
		p3 = new JPanel(); //consists of cards
		p3.setPreferredSize(new Dimension(500, 150));// hardCoded sizing
		p3.setMaximumSize(new Dimension(500, 150));  // hardCoded sizing
		p3.setMinimumSize(new Dimension(500, 150));  // hardCoded sizing
		p4 = new JPanel(); //consists of players turn
		p4.setPreferredSize(new Dimension(500, 150));// hardCoded sizing
		p4.setMaximumSize(new Dimension(500, 150));  // hardCoded sizing
		p4.setMinimumSize(new Dimension(500, 150));  // hardCoded sizing
		BoxLayout Box = new BoxLayout(p1, BoxLayout.Y_AXIS);
		p1.setLayout(Box);
		//Panel p1 = new Panel();//creating a panel consisting of 2 panels: p2 and p3
		//Panel p2 = new Panel(); //consists of gameboard
		//Panel p3 = new Panel(); //consists of cards
		//Panel p4 = new Panel(); //consists of label informing users whose turn it is
		//p1.setSize(600, 600);

		p2.setLayout(new GridLayout(8, 8, 1, 1));
		
		int a=1;
		for(int row=0; row<8; row++) //setings up a blank gameboard
		{
			for( int col=0; col<8; col++) 
			{	
				T[col][row] = new JButton();
				T[col][row].setIcon(new ImageIcon("imgs/GameBoard"+ a +".JPG"));
				a++;
				p2.add(T[col][row]);
			}
		}
		
		p3.setLayout(new GridLayout(1, 5, 10, 1));
		int j=0;
		for (int i=0; i<4; i++)
		{
			if(j==0) 
			{
				act = new String("Turn ");
				dir = new String("Left");
			}
			if(j==1)
			{
				act = new String("Move ");
				dir = new String("Forward");
			}
			if(j==2) 
			{
				act = new String("Turn ");
				dir = new String("Right");
			}
			if(j==3)
			{
				act = new String("BUG!!!");
				dir = new String("");
			}
			j++;
			cardButtons[i] = new JButton();
			cardButtons[i].setText(act + dir);
			cardButtons[i].setVerticalTextPosition(SwingConstants.BOTTOM);
			cardButtons[i].setHorizontalTextPosition(SwingConstants.CENTER);
			p3.add(cardButtons[i]);
		}
		//p4.setLayout(new BorderLayout());
		playersTurnLabel = new JLabel();
		playersTurnLabel.setText(" It is Player 1's turn");
		space = new JLabel();
		space.setText("                              ");
		//playersTurnLabel.setHorizontalTextPosition(SwingConstants.WEST);
		confirm.setText("Confirm Move");
		confirm.setPreferredSize(new Dimension(150,30));
		confirm.setMaximumSize(new Dimension(150,30));
		confirm.setMinimumSize(new Dimension(150,30));
		confirm.setHorizontalTextPosition(SwingConstants.CENTER);
		disableConfirmButton();
		setDefaultCloseOperation(EXIT_ON_CLOSE);   
		p4.add(playersTurnLabel);
		p4.add(space);
		p4.add(confirm);
		
		p1.add(p2);
		p1.add(p3);
		p1.add(p4);
		this.add(p1);

		cardButtons[3].setEnabled(false); //bug card cannot be picked before another card.
		setVisible(true);
	}
	//gives this class information about what colours it may need draw, what cards it needs to draw,
		//what types of objects/tiles it may need to draw
	public void setGameInformation(String[] c, String[] ct, String[] tp)
	{
		colours = c;
		cardTypes = ct;
		tilePossibilities = tp;
	}
	public void drawCards()
	{
		for (int j = 0; j < cardTypes.length; j++)
		{
			Icon i = new ImageIcon("imgs/Card." + j + ".jpg");
			cardButtons[j].setIcon(i);
		}


	}
	public void changePlayersTurnIndicatorLabel(int i) //i is whose turn it is
	{
		playersTurnLabel.setText("It is Player " + i + "'s turn.");
	}
	public JButton getConfirmButton()
	{
		return confirm;
	}
	public JButton[][] getTileButtons()
	{
		return T;
	}
	public JButton[] getCardButtons()
	{
		return cardButtons;
	}
	public void enableAllCardButtons() 
	{
		for (int i =0; i < cardButtons.length; i++)
		{
			cardButtons[i].setEnabled(true);
			
		}
	}
	public void disableConfirmButton()
	{
		confirm.setEnabled(false);
	}
	public void enableConfirmButton()
	{
		confirm.setEnabled(true);
	}
	public void disableAllCardButtons() 
	{
		for (int i =0; i < cardButtons.length; i++)
		{
			cardButtons[i].setEnabled(false);
		}
	}
	public void disableCardButton(int i) //the card button index in the card button array
	{
		cardButtons[i].setEnabled(false);
	}
	public void enableCardButton(int i)
	{
		cardButtons[i].setEnabled(true);
	}
	
	public void drawNormalTile(int x, int y, int a)
	{
		int b = a;
		Icon i = new ImageIcon("imgs/GameBoard" + b + ".JPG");
		T[x][y].setIcon(i);
	}
	
	//draws a robot turtle in the x column and y row 
		//also draws the right colour depending on what colour is passed
		//also draws turtle facing in right direction depending on what direction is passed in
	//also draws all other tile objects like crates, stonewalls and robot jewels
	//if direction doesn't matter then it is set to default of 0 which is set in the converter
	//all tiles have a default colour of white, so, if colour doesn't matter white will be passed
	public void drawTileObject(int x, int y, String type, String colour, int dir)
	{
		String s;
		s = "imgs/" + type + colour + dir + ".jpg";
		Icon i = new ImageIcon(s);
		T[x][y].setIcon(i);
		
	}
	public void drawCurrentGame(GameBoardConverter gbc)
	{
		String[][] t = gbc.convertTiles();
		int a =1;
		String normal = "Normal";
		for (int row = 0; row < 8; row++)
		{
			for (int col  =0; col < 8; col++)
			{

				if (t[col][row] == normal)
				{
					drawNormalTile(col, row, a);
				}
				else
				{
					//x, y position, what type of object, colour (default white if none), direction (default 0 if none)
					drawTileObject(col, row, t[col][row], gbc.getColourOfTile(col, row), gbc.getDirectionOfTile(col, row) );
				}
				a++;
				
			}
		}
	}

	public void addObsticles() //pop-up tell user to add crates and stone walls
	{
		JFrame frame = new JFrame("Robot Turtles");
		JPanel panel = new JPanel();  
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(null);
		textArea.setText("Select up to 20 tiles to be replaced with stone walls.\n"
				+ "And up to 8 to be replaced with crates.\n"
				+ "***Please be aware that the tiles with a turtle or a jewel are invalid selections: \n");
		textArea.setCaretPosition(SwingConstants.NORTH);
		panel.add(textArea);
		JButton ok = new JButton();
		ok.setText("OK");
		panel.add(ok);
		frame.add(panel);  
		frame.setSize(500, 150);  
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true); 
		ok.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				frame.dispose();
			}
		});
	}
	
	public void invalidSelection() //pop-up when player tries to put a crate, stonewall, etc in an invalid tile
	{
		JFrame frame = new JFrame("Robot Turtles ***ERROR***");
		JPanel panel = new JPanel();  
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(null);
		textArea.setText("Invalid tile.\n" 
				+ "***Please be aware that the tiles with a turtle or "
				+ "a jewel are invalid selections: \n");
		textArea.setCaretPosition(SwingConstants.NORTH);
		panel.add(textArea);
		JButton ok = new JButton();
		ok.setText("OK");
		panel.add(ok);
		frame.add(panel);  
		frame.setSize(500, 150);  
		frame.setLocationRelativeTo(null);  
		//frame.setDefaultCloseOperation(JFrame.EXIT);  
		frame.setVisible(true); 
		ok.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				frame.dispose();
			}
		});
		
	}
	public void turtleUnableToMove() //pop-up when turtle can't move
	{
		JFrame frame = new JFrame("Robot Turtles");
		JPanel panel = new JPanel();  
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(null);
		textArea.setText("Uh oh!.\n" 
				+ "Your turtle was unble to move");
		textArea.setCaretPosition(SwingConstants.NORTH);
		panel.add(textArea);	
		JButton ok = new JButton();
		ok.setText("OK");
		panel.add(ok);
		frame.add(panel);  
		frame.setSize(500, 100);  
		frame.setLocationRelativeTo(null);  
		//frame.setDefaultCloseOperation(JFrame.EXIT);  
		frame.setVisible(true); 
		ok.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				frame.dispose();
			}
		});
		
	}
	public void finish() //pop-up when all players have won
	{
		JFrame frame = new JFrame("Robot Turtles");
		JPanel panel = new JPanel();  
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(null);
		textArea.setText("Congratulations.\n" 
				+ "Everyone has won the game!");
		textArea.setCaretPosition(SwingConstants.NORTH);
		panel.add(textArea);	
		JButton ok = new JButton();
		ok.setText("OK");
		panel.add(ok);
		frame.add(panel);  
		frame.setSize(500, 100);  
		frame.setLocationRelativeTo(null);  
		//frame.setDefaultCloseOperation(JFrame.EXIT);  
		frame.setVisible(true); 
		ok.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				frame.dispose();
			}
		});
		
	}
	public void closeGame()
	{
		dispose(); //main frame exit
	}
}