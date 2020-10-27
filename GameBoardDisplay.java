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

@SuppressWarnings("serial")
public class GameBoardDisplay extends JFrame
{
	JButton[][] T = new JButton[8][8];
	JButton[] cardButtons = new JButton[4];
	private String[] colours = {"Blue", "Green", "Purple", "Red"};
	private String[] cardTypes = {"Turn Left", "Step Forward", "Turn Right", "Bug"};
	private String[] tileTypes = {"Robot Jewel", "Stone Wall", "Crate"};
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
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
		//p1.setSize(500, 500);
		BoxLayout Box = new BoxLayout(p1, BoxLayout.Y_AXIS);
		p1.setLayout(Box);

		//p3.setSize(400,400);
		p2.setLayout(new GridLayout(8, 8,1,1));

		int a=1;
		for(int row=0; row<8; row++) //setings up a blank gameboard
		{
			for( int col=0; col<8; col++) 
			{	
				T[col][row] = new JButton();
				T[col][row].setIcon(new ImageIcon("src/Images/GameBoard"+a+".JPG"));
				a++;
				//T[col][row].setSize(40,40);
				p2.add(T[col][row]);
			}
		}
		//p3.setSize(500, 100);
		p3.setLayout(new GridLayout(1, 4, 10, 1));
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
		//JFrame f = new JFrame();
		//f.setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);   
		p1.add(p2);
		p1.add(p3);
		this.add(p1);

		/*drawTurtle(0,3, 1, "Blue");
				drawTurtle(2,6, 2, "Blue");
				drawJewel(4,4, "Blue");
				drawCrate(7,4);
				drawStoneWall(2,3);*/
		drawCard(cardTypes[0]);
		drawCard(cardTypes[1]);
		drawCard(cardTypes[2]);
		drawCard(cardTypes[3]);
		setVisible(true);

	}





	public JButton[][] getTileButtons()
	{
		return T;
	}
	public JButton[] getCardButtons()
	{
		return cardButtons;
	}
	//draws a robot turtle in the x column and y row 
	//also draws the right colour depending on what colour is passed
	//also draws turtle facing in right direction depending on what direction is passed in
	public void drawTurtle(int x, int y, int d, String colour)
	{
		if (colour == colours[0]) //colours = {"Blue", "Green", "Purple", "Red"};
		{
			Icon i=new ImageIcon("src/Images/robotTurtle1." + d + ".JPG");
			T[x][y].setIcon(i);

		}
		else if (colour == colours[1])
		{
			Icon i=new ImageIcon("src/Images/robotTurtle2." + d + ".JPG");
			T[x][y].setIcon(i);
		}
		else if (colour == colours[2])
		{
			Icon i=new ImageIcon("src/Images/robotTurtle3." + d + ".JPG");
			T[x][y].setIcon(i);
		}
		else if (colour == colours[3])
		{
			Icon i=new ImageIcon("src/Images/robotTurtle4." + d + ".JPG");
			T[x][y].setIcon(i);
		}
	}

	public void drawJewel(int x, int y, String colour)
	{
		//colours = {"Blue", "Green", "Purple", "Red"};
		if (colour == colours[0])
		{
			Icon i=new ImageIcon("src/Images/jewel1.JPG"); //blue
			T[x][y].setIcon(i);

		}
		else if (colour == colours[1])
		{
			Icon i=new ImageIcon("src/Images/jewel2.JPG");
			T[x][y].setIcon(i);
		}
		else if (colour == colours[2])
		{
			Icon i=new ImageIcon("src/Images/jewel3.JPG");
			T[x][y].setIcon(i);
		}
		else if (colour == colours[3])
		{
			Icon i=new ImageIcon("src/Images/jewel4.JPG");
			T[x][y].setIcon(i);
		}
	}
	
	public void drawCrate(int x, int y)
	{
		Icon i=new ImageIcon("src/Images/crate.JPG");
		T[x][y].setIcon(i);
	}
	public void drawStoneWall(int x, int y)
	{
		Icon i=new ImageIcon("src/Images/stoneWall.JPG");
		T[x][y].setIcon(i);
	}
	public void drawCard(String cardType) //cardTypes = {"Turn Left", "Step Forward", "Turn Right", "Bug"};
	{
		if (cardType == cardTypes[0])
		{
			Icon i=new ImageIcon("src/Images/card.0.JPG"); 
			cardButtons[0].setIcon(i);
		}
		else if (cardType == cardTypes[1])
		{
			Icon i=new ImageIcon("src/Images/card.1.JPG");
			cardButtons[1].setIcon(i);
		}
		else if (cardType == cardTypes[2])
		{
			Icon i=new ImageIcon("src/Images/card.2.JPG");
			cardButtons[2].setIcon(i);
		}
		else if (cardType == cardTypes[3])
		{
			Icon i=new ImageIcon("src/Images/card.3.JPG");
			cardButtons[3].setIcon(i);
		}
	}

	public void drawCurrentGame(Tile[][] t)
	{
		for (int col = 0; col < 8; col++)
		{
			for (int row =0; row < 8; row++)
			{
				if (t[col][row].getOccupied() != true) //tileTypes = {"Robot Jewel", "Stone Wall", "Crate"};
				{
					if (t[col][row].getTileType() == tileTypes[0]) //robot jewel
					{
						drawJewel(col, row, t[col][row].getColour());
					}
					else if (t[col][row].getTileType() == tileTypes[1]) //stone wall
					{
						drawStoneWall(col, row);
					}
					else if (t[col][row].getTileType() == tileTypes[2]) //crate
					{
						drawCrate(col, row);
					}
					//else normal tile which has already been drawn
				}
				else //there's a turtle on this tile
				{
					RobotTurtle rt = t[col][row].getRobotTurtle();
					int direction = rt.getDirection();
					String colour = rt.getColour();
					drawTurtle(col, row, direction, colour);
				}

			}
		}
	}

	public void addObsticles() 
	{
		JFrame frame = new JFrame("Robot Turtles");
		JPanel panel = new JPanel();  
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(null);
		textArea.setText("Select 20 tiles to be replaced with stone walls.\n"
				+ "And 8 to be replaced with crates.\n"
				+ "***Please be aware that the following tiles are invalid selections: \n"
				+ "{(0,0),(0,7),(7,0),(7,7),(3,4),(3,3),(4,3),(4,4)}");
		textArea.setCaretPosition(SwingConstants.NORTH);
		panel.add(textArea);
		JButton ok = new JButton();
		ok.setText("OK");
		panel.add(ok);
		frame.add(panel);  
		frame.setSize(500, 120);  
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
	
	public int pickACardPrompt(Player currentPlayer)
	{
		JFrame frame = new JFrame("Robot Turtles");
		JPanel panel = new JPanel();
		JLabel label = new JLabel(currentPlayer + " what would You like to do?");  
		label.setVerticalTextPosition(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		if(currentPlayer==Player.PLAYER_ONE)
		{
			
			label.setForeground(Color.WHITE);
			panel.setBackground(Color.BLUE);
		}
		if(currentPlayer==Player.PLAYER_TWO)
		{
			label.setForeground(Color.WHITE);
			panel.setBackground(Color.GREEN);
		}
		if(currentPlayer==Player.PLAYER_THREE)
		{
			label.setForeground(Color.WHITE);
			panel.setBackground(Color.MAGENTA);
		}
		if(currentPlayer==Player.PLAYER_FOUR)
		{
			label.setForeground(Color.WHITE);
			panel.setBackground(Color.RED);
		}
		panel.add(label); 
		frame.add(panel);  
		frame.setSize(300, 100);  
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true);
		return 0; 
	}
	
	public void invalidSelection(int col, int row) 
	{
		JFrame frame = new JFrame("Robot Turtles ***ERROR***");
		JPanel panel = new JPanel();  
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(null);
		textArea.setText("Invalid tile.\n" 
				+ "***Please be aware that the following tiles are invalid selections:\n"
				+ "{(0,0),(0,7),(7,0),(7,7),(3,4),(3,3),(4,3),(4,4)}");
		textArea.setCaretPosition(SwingConstants.NORTH);
		panel.add(textArea);		
		frame.add(panel);  
		frame.setSize(500, 100);  
		frame.setLocationRelativeTo(null);  
		//frame.setDefaultCloseOperation(JFrame.EXIT);  
		frame.setVisible(true); 
		
		GBController.setUpGame();

	}
}