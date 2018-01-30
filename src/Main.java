import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Main extends JFrame
{
	private JButton create_button_1()
	{
		button__1 = new JButton("Normal Mode");
		button__1.setFont(new Font("arial", Font.PLAIN, 40));
		button__1.setForeground(new Color(162, 209, 73));
		button__1.setBounds(400, 510, 300, 40);
		button__1.setBorderPainted(false);
		button__1.addActionListener(new Action1());
		return button__1;
				
	};
	
	private JButton create_button_2()
	{
		button__2 = new JButton("Obstacles Mode");
		button__2.setFont(new Font("arial", Font.PLAIN, 40));
		button__2.setForeground(new Color(162, 209, 73));
		button__2.setBounds(373, 565, 350, 40);
		button__2.setBorderPainted(false);
		button__2.addActionListener(new Action2());
		return button__2;			
	};
	
	private JButton create_button_easy()
	{
		button__easy = new JButton("Easy");
		button__easy.setFont(new Font("arial", Font.PLAIN, 30));
		button__easy.setBounds(150, 630, 120, 30);
		button__easy.setBorderPainted(false);
		button__easy.setForeground(new Color(74, 117, 44));
		button__easy.addActionListener(new Action__Easy());
		return button__easy;			
	};
	
	private JButton create_button_medium()
	{
		button__medium = new JButton("Medium");
		button__medium.setFont(new Font("arial", Font.PLAIN, 30));
		button__medium.setBounds(440, 630, 200, 30);
		button__medium.setBorderPainted(false);
		button__medium.setForeground(new Color(162, 209, 73));
		button__medium.addActionListener(new Action__Medium());
		return button__medium;			
	};
	
	private JButton create_button_hard()
	{
		button__hard = new JButton("Hard");
		button__hard.setFont(new Font("arial", Font.PLAIN, 30));
		button__hard.setBounds(820, 630, 120, 30);
		button__hard.setBorderPainted(false);
		button__hard.setForeground(new Color(162, 209, 73));
		button__hard.addActionListener(new Action__Hard());
		return button__hard;			
	};
	
	static boolean easy = true;
	static boolean medium = false;
	static boolean hard = false;
	
	private JPanel panel;
	Gameplay_1 gameplay_1;
	Gameplay_2 gameplay_2;
	private JButton button__1, button__2, button__easy, button__medium, button__hard;
	public static void main(String[] args)  
	{
		Main frame = new Main();
		frame.setVisible(true);
		frame.setTitle("Snake It");
	}
	
	public Main()
	{
		panel = new JPanel();
		panel.setLayout(null);
		JButton button__1 = create_button_1();
		JButton button__2 = create_button_2();
		JButton button__easy = create_button_easy();
		JButton button__medium = create_button_medium();
		JButton button__hard = create_button_hard();

		panel.add(button__1);
		panel.add(button__2);
		panel.add(button__easy);
		panel.add(button__medium);
		panel.add(button__hard);
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 10, 1100, 750);
		setResizable(false);
		
	}
	
	public void paint (Graphics g)
	{
		ImageIcon first__bg = new ImageIcon(new ImageIcon("asset/cta.png")
				.getImage().getScaledInstance(1100, 400, java.awt.Image.SCALE_SMOOTH));
		first__bg.paintIcon(this, g, 0, 120);
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.PLAIN, 40));
		g.drawString("Play Snake", 50, 80);
		
		g.dispose();
	}
	
	public class Action1 implements ActionListener {        
		  public void actionPerformed (ActionEvent e)
		  {     
			JFrame frame2 = new JFrame("Normal Mode");
			frame2.setVisible(true);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.setBounds(300, 10, 1100, 750);
			frame2.setResizable(false);
		    dispose();
		    gameplay_1 = new Gameplay_1();
		    frame2.add(gameplay_1);
		  }	  
		}   
	
	public class Action2 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     
			JFrame frame3 = new JFrame("Obstacles Mode");
			frame3.setVisible(true);
			frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame3.setBounds(300, 10, 1100, 750);
			frame3.setResizable(false);
		    dispose();
		    gameplay_2 = new Gameplay_2();
		    frame3.add(gameplay_2);   
		  }
		}
	
	public class Action__Easy implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     
			easy = true;
			medium = false;
			hard = false;
			button__easy.setForeground(new Color(74, 117, 44));
			button__medium.setForeground(new Color(162, 209, 73));
			button__hard.setForeground(new Color(162, 209, 73));
		  }
		}
	
	public class Action__Medium implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     
			  	easy = false;
				medium = true;
				hard = false;  
				button__medium.setForeground(new Color(74, 117, 44));
				button__easy.setForeground(new Color(162, 209, 73));
				button__hard.setForeground(new Color(162, 209, 73));
		  }
		}
	
	public class Action__Hard implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     
				easy = false;
				medium = false;
				hard = true;  
				button__hard.setForeground(new Color(74, 117, 44));
				button__easy.setForeground(new Color(162, 209, 73));
				button__medium.setForeground(new Color(162, 209, 73));
		  }
		}
}
