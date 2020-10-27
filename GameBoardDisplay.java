import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
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
	public GameBoardDisplay(String title)
	{
		//super(title); 
		Panel p1 = new Panel(); //creating a panel consisting of 2 panels: p2 and p3
		Panel p2 = new Panel(); //consists of gameboard
		Panel p3 = new Panel(); //consists of cards
		p1.setSize(600, 600);
		p1.setLayout(new GridLayout(2, 1, 1, 1));
		
		p2.setSize(600,600);
		p2.setLayout(new GridLayout(8, 8, 1, 1));
		p3.setSize(100, 100);
		setLayout(new GridLayout(1, 4, 1, 1));
		Frame f = new Frame();
		f.setSize(1000, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);   
		int a=1;
		for(int row=0; row<8; row++) //setings up a blank gameboard
		{
			for( int col=0; col<8; col++) 
			{	
				T[col][row] = new JButton();
				T[col][row].setIcon(new ImageIcon("src/imgs/GameBoard1.JPG"));
				a++;
				p2.add(T[col][row]);
			}
		}
		for (int i=0; i<4; i++)
		{
			cardButtons[i] = new JButton();
			p3.add(cardButtons[i]);
		}

		p1.add(p2);
		p1.add(p3);
		f.add(p1);
		
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
		f.show();
		
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
			Icon i=new ImageIcon("src/imgs/robotTurtle1." + d + ".JPG");
			T[x][y].setIcon(i);
				
		}
		else if (colour == colours[1])
		{
			Icon i=new ImageIcon("src/imgs/robotTurtle2." + d + ".JPG");
			T[x][y].setIcon(i);
		}
		else if (colour == colours[2])
		{
			Icon i=new ImageIcon("src/imgs/robotTurtle3." + d + ".JPG");
			T[x][y].setIcon(i);
		}
		else if (colour == colours[3])
		{
			Icon i=new ImageIcon("src/imgs/robotTurtle4." + d + ".JPG");
			T[x][y].setIcon(i);
		}
	}
	
	public void drawJewel(int x, int y, String colour)
	{
		//colours = {"Blue", "Green", "Purple", "Red"};
		if (colour == colours[0])
		{
			Icon i=new ImageIcon("src/imgs/jewel1.JPG"); //blue
			T[x][y].setIcon(i);
			
		}
		else if (colour == colours[1])
		{
			Icon i=new ImageIcon("src/imgs/jewel2.JPG");
			T[x][y].setIcon(i);
		}
		else if (colour == colours[2])
		{
			Icon i=new ImageIcon("src/imgs/jewel3.JPG");
			T[x][y].setIcon(i);
		}
		else if (colour == colours[3])
		{
			Icon i=new ImageIcon("src/imgs/jewel4.JPG");
			T[x][y].setIcon(i);
		}
	}
	public void drawCrate(int x, int y)
	{
		Icon i=new ImageIcon("src/imgs/crate.JPG");
		T[x][y].setIcon(i);
	}
	public void drawStoneWall(int x, int y)
	{
		Icon i=new ImageIcon("src/imgs/stoneWall.JPG");
		T[x][y].setIcon(i);
	}
	public void drawCard(String cardType) //cardTypes = {"Turn Left", "Step Forward", "Turn Right", "Bug"};
	{
		if (cardType == cardTypes[0])
		{
			Icon i=new ImageIcon("src/imgs/card.0.JPG"); 
			cardButtons[0].setIcon(i);
		}
		else if (cardType == cardTypes[1])
		{
			Icon i=new ImageIcon("src/imgs/card.1.JPG");
			cardButtons[1].setIcon(i);
		}
		else if (cardType == cardTypes[2])
		{
			Icon i=new ImageIcon("src/imgs/card.2.JPG");
			cardButtons[2].setIcon(i);
		}
		else if (cardType == cardTypes[3])
		{
			Icon i=new ImageIcon("src/imgs/card.3.JPG");
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
		textArea.setText("Select up to 20 tiles to be replaced with stone walls.\n"
				+ "And up to 8 to be replaced with crates.\n"
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
	public void invalidSelection() 
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
		
	}
}