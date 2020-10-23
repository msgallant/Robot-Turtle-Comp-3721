import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DisplayGameBoard extends JFrame
{
 JButton T[][] = new JButton[8][8];
 
 public DisplayGameBoard(String title)
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
 }
 
 public static void main(String[] args)
 {
	 DisplayGameBoard rt = new DisplayGameBoard("Robot Turtles");
	 rt.setVisible(true);
	 rt.setSize(500, 500);
 }
 
}
