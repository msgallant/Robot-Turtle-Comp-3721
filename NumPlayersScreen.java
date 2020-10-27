import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NumPlayersScreen extends JFrame
{
	ArrayList<JButton> buttonList = new ArrayList<JButton>();

	public NumPlayersScreen()
	{
		
	}
	public void findNumPlayers()
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
			buttonList.add(b);
			b.setIcon(new ImageIcon("src/Images/" + i + "Player.jpg"));
			b.setHorizontalTextPosition(SwingConstants.CENTER);
			b.setVerticalTextPosition(SwingConstants.BOTTOM);
			b.setText(i +" Player");
			panel2.add(b);
			b.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent args0)
					{
						frame.dispose();
					}
				});
		}
		panel.add(panel1);  
		panel.add(panel2);
		frame.add(panel);  
		frame.setSize(650, 280);  
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true); 
		
	}
	public void destroyScreen()
	{
		
	}
	public ArrayList<JButton> getButtonList()
	{
		return buttonList;
	}
}