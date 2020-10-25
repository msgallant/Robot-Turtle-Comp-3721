
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameBoardView extends JFrame
{
	JButton T[][] = new JButton[8][8];

	public GameBoardView(String title)
	{

		super (title);
		setLayout(new GridLayout(8, 8, 1 ,1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);  
		int a=1;
		for(int i=0; i<8; i++)
		{
			for( int j=0; j<8; j++)
			{	
				T[i][j] = new JButton();
				T[i][j].setIcon(new ImageIcon("src/Images/GameBoard"+ a +".jpg"));
				a++;
				add(T[i][j]);
			}
		}
		//T[5][4].setIcon(new ImageIcon("src/Images/crate.jpg"));
		//T[4][7].setIcon(new ImageIcon("src/Images/stoneWall.jpg"));
		//T[3][3].setIcon(new ImageIcon("src/Images/jewel1.jpg"));
		//T[3][4].setIcon(new ImageIcon("src/Images/jewel2.jpg"));
		//T[4][3].setIcon(new ImageIcon("src/Images/jewel3.jpg"));
		//T[4][4].setIcon(new ImageIcon("src/Images/jewel4.jpg"));
		//T[0][0].setIcon(new ImageIcon("src/Images/robotTurtle1.3.jpg"));
		//T[0][7].setIcon(new ImageIcon("src/Images/robotTurtle2.4.jpg"));
		//T[7][0].setIcon(new ImageIcon("src/Images/robotTurtle3.2.jpg"));
		//T[7][7].setIcon(new ImageIcon("src/Images/robotTurtle4.1.jpg"));

	}

	public static void main(String[] args)
	{
		GameBoardView rt = new GameBoardView("Robot Turtles");
		rt.setVisible(true);
		rt.setSize(600, 600); 

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

}
