import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GameBoardView extends JFrame
{
	ArrayList<JButton> butList = new ArrayList<JButton>();
	ArrayList<JButton> cardList = new ArrayList<JButton>();
	int m;
	JButton T[][] = new JButton[8][8];
	ArrayList<JButton> tileList = new ArrayList<JButton>();

	public GameBoardView(String title)
	{
		super (title);
		setSize(600,600);
		setLayout(new GridLayout(8, 8, 1 ,1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);  
		int a=1;
		for(int i=0; i<8; i++)
		{
			for( int j=0; j<8; j++)
			{	
				T[i][j] = new JButton();
				T[i][j].setIcon(new ImageIcon("src/Images/GameBoard"+ a +".jpg"));
				tileList.add(T[i][j]);
				a++;
				add(T[i][j]);
			}
		}
		setVisible(true);
	}

	public void numPlayers()
	{
		JFrame frame = new JFrame("Robot Turtles");
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();  
		panel1.setLayout(new BorderLayout());  
		JLabel label = new JLabel("How many Players do we have?");  
		label.setVerticalTextPosition(SwingConstants.NORTH);
		panel1.add(label);

		JPanel panel2 = new JPanel();

		for (int i=1; i<5; i++)
		{
			JButton b = new JButton();
			butList.add(b);
			b.setIcon(new ImageIcon("src/Images/" + i + "Player.jpg"));
			b.setHorizontalTextPosition(SwingConstants.CENTER);
			b.setVerticalTextPosition(SwingConstants.BOTTOM);
			b.setText(i +" Player");
			panel2.add(b);
		}
		panel.add(panel1);  
		panel.add(panel2);
		frame.add(panel);  
		frame.setSize(650, 280);  
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true); 
	}

	public ArrayList<JButton> getButtonList()
	{
		return butList;
	}

	public ArrayList<JButton> getTileList()
	{
		return tileList;
	}


	public int pickACard()
	{
		JFrame frame = new JFrame("Robot Turtles");
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();  
		panel1.setLayout(new BorderLayout());  
		JLabel label = new JLabel("What would You like to do?");  
		label.setVerticalTextPosition(SwingConstants.NORTH);
		panel1.add(label);

		JPanel panel2 = new JPanel();


		JButton left = new JButton();
		left.setIcon(new ImageIcon("src/Images/Card.0.jpg"));
		left.setHorizontalTextPosition(SwingConstants.CENTER);
		left.setVerticalTextPosition(SwingConstants.BOTTOM);
		left.setText("Turn Left");
		panel2.add(left);

		JButton forw = new JButton();
		forw.setIcon(new ImageIcon("src/Images/Card.1.jpg"));
		forw.setHorizontalTextPosition(SwingConstants.CENTER);
		forw.setVerticalTextPosition(SwingConstants.BOTTOM);
		forw.setText("Move Forward");
		panel2.add(forw);

		JButton right = new JButton();
		right.setIcon(new ImageIcon("src/Images/Card.2.jpg"));
		right.setHorizontalTextPosition(SwingConstants.CENTER);
		right.setVerticalTextPosition(SwingConstants.BOTTOM);
		right.setText("Turn Right");
		panel2.add(right);

		panel.add(panel1);  
		panel.add(panel2);
		frame.add(panel);  
		frame.setSize(660, 360);  
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true); 

		left.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				m=0;
			}
		});	
		forw.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				m=1;
			}
		});	
		right.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				m=2;
			}
		});
		return m;
	}

	public void validate()
	{
		JFrame frame = new JFrame("Robot Turtles");
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();  
		panel1.setLayout(new BorderLayout());  
		JLabel label = new JLabel("Are you sure that you want to do this?");  
		label.setVerticalTextPosition(SwingConstants.NORTH);
		panel1.add(label);
		panel.add(panel1); 

		JPanel panel2 = new JPanel();

		JButton move = new JButton();
		move.setIcon(new ImageIcon("src/Images/Card."+ m +".jpg"));
		move.setHorizontalTextPosition(SwingConstants.CENTER);
		move.setVerticalTextPosition(SwingConstants.BOTTOM);
		move.setText("Confirm Move?");
		panel2.add(move);

		JButton bug = new JButton();
		bug.setIcon(new ImageIcon("src/Images/Card.3.jpg"));
		bug.setHorizontalTextPosition(SwingConstants.CENTER);
		bug.setVerticalTextPosition(SwingConstants.BOTTOM);
		bug.setText("BUG!!!");
		panel2.add(bug);

		panel.add(panel2);
		frame.add(panel);  
		frame.setSize(660, 360);  
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true); 

		move.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{

			}
		});	
		bug.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				m=3;
			}
		});	
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

	public void addStoneWalls(int t, int x, int y)
	{
		T[x][y].setIcon(new ImageIcon("src/Images/stoneWall.jpg")); 
	}

	public void addCrate(int x, int y)
	{
		T[x][y].setIcon(new ImageIcon("src/Images/crate.jpg"));
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true); 
	}


	public void displayBoard(Tile[][] tileList2) 
	{
		GameBoardView updated = new GameBoardView("Robot Turtles");
		int a=1;
		for(int i =0; i<8; i++)
		{
			for(int j=0;j<8;j++)
			{
				String type = new String();
				//types can be : Crate, Normal, Stone Wall, Robot Jewel

				Boolean occupied = new Boolean(false);
				//True or False

				type = tileList2[i][j].getTileType();
				occupied = tileList2[i][j].getOccupied();
				
				if(occupied == true)
				{
					RobotTurtle rt = tileList2[i][j].getRobotTurtle();
					if(type == "Normal")
					{
						if(rt.getColour == "Blue")
						{
							int d = rt.getDirection();
							T[i][j].setIcon(new ImageIcon("src/Images/robotTurtle1." + d + ".jpg"));
							updated.add(T[i][j]);
							a++;
						}
						if(rt.getColour == "Green")
						{
							int d = rt.getDirection();
							T[i][j].setIcon(new ImageIcon("src/Images/robotTurtle2." + d + ".jpg"));
							updated.add(T[i][j]);
							a++;
						}
						if(rt.getColour == "Purple")
						{
							int d = rt.getDirection();
							T[i][j].setIcon(new ImageIcon("src/Images/robotTurtle3." + d + ".jpg"));
							updated.add(T[i][j]);
							a++;
						}
						if(rt.getColour == "Red")
						{
							int d = rt.getDirection();
							T[i][j].setIcon(new ImageIcon("src/Images/robotTurtle4." + d + ".jpg"));
							updated.add(T[i][j]);
							a++;
						}
						
					}// If Normal
					
					if(type == "Robot Jewel")
					{
						if(rt.getColour == "Blue")
						{
							int d = rt.getDirection();
							T[i][j].setIcon(new ImageIcon("src/Images/robotTurtle1." + d + ".jpg"));
							updated.add(T[i][j]);
							a++;
						}
						if(rt.getColour == "Green")
						{
							int d = rt.getDirection();
							T[i][j].setIcon(new ImageIcon("src/Images/robotTurtle2." + d + ".jpg"));
							updated.add(T[i][j]);
							a++;
						}
						if(rt.getColour == "Purple")
						{
							int d = rt.getDirection();
							T[i][j].setIcon(new ImageIcon("src/Images/robotTurtle3." + d + ".jpg"));
							updated.add(T[i][j]);
							a++;
						}
						if(rt.getColour == "Red")
						{
							int d = rt.getDirection();
							T[i][j].setIcon(new ImageIcon("src/Images/robotTurtle4." + d + ".jpg"));
							updated.add(T[i][j]);
							a++;
						}	
					}// If Robot Jewel
					
				}// If of Occupied

				else
				{
					if(type == "Crate")
					{
						T[i][j].setIcon(new ImageIcon("src/Images/crate.jpg"));
						updated.add(T[i][j]);
						a++;
					}// If for Crate
					
					if(type == "Normal")
					{
						T[i][j].setIcon(new ImageIcon("src/Images/GameBoard" + a +".jpg"));
						updated.add(T[i][j]);
						a++;
					}// If for Normal
					
					if(type == "Stone Wall")
					{
						T[i][j].setIcon(new ImageIcon("src/Images/stoneWall.jpg"));
						updated.add(T[i][j]);
						a++;
					}// If for Stone Wall
					
					if(type == "Robot Jewel")
					{
						String colour = new String();
						//Blue(3,4), Green(3,3), Purple(4,3), Red(4,4)
						colour = tileList2[i][j].getJewelColour();
						if(colour == "Blue")
						{
							T[i][j].setIcon(new ImageIcon("src/Images/Jewel1.jpg"));
							updated.add(T[i][j]);
							a++;
						}
						if(colour == "Green")
						{	
							T[i][j].setIcon(new ImageIcon("src/Images/Jewel2.jpg"));
							updated.add(T[i][j]);
							a++;
						}
						if(colour == "Purple")
						{
							T[i][j].setIcon(new ImageIcon("src/Images/Jewel3.jpg"));
							updated.add(T[i][j]);
							a++;
						}
						if(colour == "Red")
						{
							T[i][j].setIcon(new ImageIcon("src/Images/Jewel3.jpg"));
							updated.add(T[i][j]);
							a++;
						}
					}// If for RObot Jewel
				}// Else of occupied

			}
		}
		updated.setVisible(true);
	}


}
