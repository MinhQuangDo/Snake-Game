import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gameplay_2 extends JPanel implements KeyListener, ActionListener
{
	private int[] snake__x__length = new int[1000] ;
	private int[] snake__y__length = new int[1000] ;
	
	private int moves = 0;    //start of the game
	
	private boolean left = false;
	private boolean right = true;
	private boolean up = false;
	private boolean down = false;
	
	private int snake__length = 2;
	
	
	private ImageIcon snake__right__mouth;
	private ImageIcon snake__left__mouth;
	private ImageIcon snake__up__mouth;
	private ImageIcon snake__down__mouth;
	
	private ImageIcon snake__right__tail;
	private ImageIcon snake__left__tail;
	private ImageIcon snake__up__tail;
	private ImageIcon snake__down__tail;
	
	private Timer timer;
	private boolean start = false;
	private boolean end = false;
	private int delay = 100;
	private ImageIcon snake__body;
	private ImageIcon object;
	
	private int [] object__x__pos = {50, 75, 100, 125, 150, 175, 200, 225, 
			250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 
			575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850, 875, 
			900, 925, 950};
	
	private int [] object__y__pos = {50, 75, 100, 125, 150, 175, 200, 225, 
			250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 
			575, 600, 625, 650, 675};
	
	
	private Random random = new Random();

	private int obj__x = random.nextInt(37);
	private int obj__y = random.nextInt(26);
	
	
	private int obs__randomNum;
	private ArrayList<Integer> obj__obs__x;
	private ArrayList<Integer> obj__obs__y;	
	
	private int score = 0;
	private int highest__score = 0;
	
	
	private void create__bush()
	{
		if (Main.easy == true)
			obs__randomNum = ThreadLocalRandom.current().nextInt(20, 30 + 1);
		else if (Main.medium == true)
			obs__randomNum = ThreadLocalRandom.current().nextInt(40, 50 + 1);
		else
			obs__randomNum = ThreadLocalRandom.current().nextInt(60, 70 + 1);
		obj__obs__x = new ArrayList<Integer> (obs__randomNum);
		obj__obs__y = new ArrayList<Integer> (obs__randomNum);
		
		for (int o = 0; o <= obs__randomNum; o++)
		{
			int obs__x = random.nextInt(37);
			int obs__y = random.nextInt(26);
			while ((object__x__pos[obs__x] == 250 && object__y__pos[obs__y] == 250) ||
					(object__x__pos[obs__x] == 225 && object__x__pos[obs__y] == 250))
			{
				obs__x = random.nextInt(37);
				obs__y = random.nextInt(26);
			}
			obj__obs__x.add(obs__x);
			obj__obs__y.add(obs__y);
		}
	}

	
	
	public Gameplay_2()
	{
		create__bush();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
			
	}
	
	public void paint (Graphics g)
	{
		
		//this is the beginning of gameplay
		if (moves == 0)
		{
			snake__x__length[0] = 250;
			snake__y__length[0] = 250;
			
			snake__x__length[1] = 225;
			snake__y__length[1] = 250;
			
			
		}
		
		// this is the scoreboard
		g.setColor(Color.WHITE); 	//white border
		g.drawRect(999, 0, 100, 750);
		Color sb = new Color(74, 117, 44);
		g.setColor(sb);
		g.fillRect(1000, 0, 100, 750);
		
		
		//this is the game background
		Color game_nhat = new Color(170, 215, 81); 
		Color game_dam = new Color(162, 209, 73); 



		for (int x = 0; x <= 975; x += 25 )
		{
			for (int y = 0; y <= 700; y += 25)
			{
				if (x == 0 || x == 975)
				{
					ImageIcon obs = new ImageIcon("bush.png");
					obs.paintIcon(this, g, x, y);
				}
				else 
				{
					if (y == 0 || y == 700)
					{
						ImageIcon obs = new ImageIcon("bush.png");
						obs.paintIcon(this, g, x, y);
					}
		
					else if (y % 2 == 0)
					{
						if (x % 2 == 0)
						{
							g.setColor(game_nhat);
							g.fillRect(x, y, 25, 25);
						}
						else
						{
							g.setColor(game_dam);
							g.fillRect(x, y, 25, 25);
						}
					}
					else
					{
						if (x % 2 == 0)
						{
							g.setColor(game_dam);
							g.fillRect(x, y, 25, 25);
						}
						else
						{
							g.setColor(game_nhat);
							g.fillRect(x, y, 25, 25);
						}
					}
				}
			}
		}
		
		for (int o = 0; o < obs__randomNum; o++)
		{		
			ImageIcon obs = new ImageIcon("bush.png");
			obs.paintIcon(this, g, object__x__pos[obj__obs__x.get(o)], object__y__pos[obj__obs__y.get(o)]);

		
		}
		
		//this is score content
		ImageIcon score__obj = new ImageIcon(new ImageIcon("enemy.png")
				.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
		score__obj.paintIcon(this, g, 1020, 20);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 20));
		g.drawString(""+score, 1060, 40);
		
		ImageIcon high__score__obj = new ImageIcon(new ImageIcon("trophy.png")
				.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
		high__score__obj.paintIcon(this, g, 1018, 80);
		if (highest__score < score)
			highest__score = score;
		g.drawString(""+highest__score, 1060, 100);
		
		//this is escape instruction
		g.setFont(new Font("arial", Font.PLAIN, 18));
		g.drawString("Press Esc", 1005, 650);
		g.drawString("to exit", 1023, 670);
		
		
		snake__right__mouth = new ImageIcon(new ImageIcon("snake_right_mouth.png")
				.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)); 
		snake__right__mouth.paintIcon(this, g, snake__x__length[0], snake__y__length[0]);
		
		
		if (snake__y__length[snake__length - 2] < snake__y__length[snake__length - 1])
		{
			if (snake__y__length[snake__length - 2] == 0 &&
					snake__y__length[snake__length - 1] == 700)
			{
				snake__up__tail = new ImageIcon(new ImageIcon("snake_up_tail.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)); 
				snake__up__tail.paintIcon(this, g, snake__x__length[snake__length-1], snake__y__length[snake__length-1]);	
			}
			else
			{
				snake__down__tail = new ImageIcon(new ImageIcon("snake_down_tail.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)); 
				snake__down__tail.paintIcon(this, g, snake__x__length[snake__length-1], snake__y__length[snake__length-1]);
			}
				
		}
		
		else if (snake__y__length[snake__length - 2] > snake__y__length[snake__length - 1])
		{
			if (snake__y__length[snake__length - 2] == 725 &&
					snake__y__length[snake__length - 1] == 0)
			{
				snake__down__tail = new ImageIcon(new ImageIcon("snake_down_tail.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)); 
				snake__down__tail.paintIcon(this, g, snake__x__length[snake__length-1], snake__y__length[snake__length-1]);	
			}
			else
			{
				snake__up__tail = new ImageIcon(new ImageIcon("snake_up_tail.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)); 
				snake__up__tail.paintIcon(this, g, snake__x__length[snake__length-1], snake__y__length[snake__length-1]);
			}
		}
		
		else if (snake__x__length[snake__length - 2] < snake__x__length[snake__length - 1])
		{
			if (snake__x__length[snake__length - 2] == 0 &&
					snake__x__length[snake__length - 1] == 975)
			{
				snake__left__tail = new ImageIcon(new ImageIcon("snake_left_tail.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)); 
				snake__left__tail.paintIcon(this, g, snake__x__length[snake__length-1], snake__y__length[snake__length-1]);	
			}
			else
			{
				snake__right__tail = new ImageIcon(new ImageIcon("snake_right_tail.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)); 
				snake__right__tail.paintIcon(this, g, snake__x__length[snake__length-1], snake__y__length[snake__length-1]);
			}
		}
		
		else if (snake__x__length[snake__length - 2] > snake__x__length[snake__length - 1])
		{
			if (snake__x__length[snake__length - 2] == 975 &&
					snake__x__length[snake__length - 1] == 0)
			{
				snake__right__tail = new ImageIcon(new ImageIcon("snake_right_tail.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)); 
				snake__right__tail.paintIcon(this, g, snake__x__length[snake__length-1], snake__y__length[snake__length-1]);	
			}
			else
			{
				snake__left__tail = new ImageIcon(new ImageIcon("snake_left_tail.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)); 
				snake__left__tail.paintIcon(this, g, snake__x__length[snake__length-1], snake__y__length[snake__length-1]);
			}
		}
		
		for (int a = 0; a < snake__length - 1; a++)
		{
			if (a == 0 && right)
			{
				snake__right__mouth = new ImageIcon(new ImageIcon("snake_right_mouth.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
				snake__right__mouth.paintIcon(this, g, snake__x__length[a], snake__y__length[a]);
			}
			
			if (a == 0 && left)
			{
				snake__left__mouth = new ImageIcon(new ImageIcon("snake_left_mouth.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
				snake__left__mouth.paintIcon(this, g, snake__x__length[a], snake__y__length[a]);
			}
			
			if (a == 0 && up)
			{
				snake__up__mouth = new ImageIcon(new ImageIcon("snake_up_mouth.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
				snake__up__mouth.paintIcon(this, g, snake__x__length[a], snake__y__length[a]);
			}
			
			if (a == 0 && down)
			{
				snake__down__mouth = new ImageIcon(new ImageIcon("snake_down_mouth.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
				snake__down__mouth.paintIcon(this, g, snake__x__length[a], snake__y__length[a]);
			}
			
			if (a != 0)
			{
				snake__body = new ImageIcon(new ImageIcon("snake_body.png")
						.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
				snake__body.paintIcon(this, g, snake__x__length[a], snake__y__length[a]);
			}
		}
		
		object = new ImageIcon(new ImageIcon("enemy.png")
				.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)); 
		if (snake__x__length[0] == object__x__pos[obj__x] && 
			snake__y__length[0] == object__y__pos[obj__y])
		{
			snake__length++;
			score++;
			obj__x = random.nextInt(37);
			obj__y = random.nextInt(26);
			int c = 0;
			int d = 0;
			while (c < snake__length)
			{
				while (d < obs__randomNum)
				{
					if (object__x__pos[obj__obs__x.get(d)] == object__x__pos[obj__x] &&
							object__y__pos[obj__obs__y.get(d)] == object__y__pos[obj__y])
					{
						obj__x = random.nextInt(37);
						obj__y = random.nextInt(26);
						d = 0;
					}
					else d++;			
				}
				if ((snake__x__length[c] == object__x__pos[obj__x] && 	//object cannot be in the snake
						snake__y__length[c] == object__y__pos[obj__y]))
				{
					obj__x = random.nextInt(37);
					obj__y = random.nextInt(26);
					d = 0;
					c = 0;
				}
				else c++;
			}				
		}
		object.paintIcon(this, g, object__x__pos[obj__x], object__y__pos[obj__y]);
		
		for (int i = 1; i < snake__length; i++ )
		{
			if (snake__x__length[0] == snake__x__length[i]
					&& snake__y__length[0] == snake__y__length[i])
			{
				gameOver(g);				
			}
		}
		
		if (snake__x__length[0] == 0 || snake__x__length[0] == 975 ||
				snake__y__length[0] == 0 || snake__y__length[0] == 700)
		{
			gameOver(g);
		}
		
		for (int j = 0; j < obs__randomNum; j++)
		{
			if (snake__x__length[0] == object__x__pos[obj__obs__x.get(j)]
					&& snake__y__length[0] == object__y__pos[obj__obs__y.get(j)])
			{
				gameOver(g);
			}		
		}
		
		g.dispose();
		
	}
	
	private void gameOver(Graphics g)
	{
		up = right = left = down = false;
		end = true;

		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 50));
		g.drawString("Game Over", 400, 300);
		g.setFont(new Font("arial", Font.PLAIN, 30));
		g.drawString("Score: "+score, 475, 350);
		g.setFont(new Font("arial", Font.PLAIN, 30));
		g.drawString("Press Space to replay", 390, 390);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if (right)
		{
			for (int l = snake__length-1; l >= 0; l--)		//snake goes right
				snake__y__length[l+1] = snake__y__length[l]; 
			
			for (int i = snake__length; i >= 0; i--)
			{
				if (i == 0)		//head moves 25px to the right
					snake__x__length[i] += 25;
				else				//copy the rest of the body
					snake__x__length[i] = snake__x__length[i-1];

			}
			repaint();			
		}
		
		if (left)
		{
			for (int l = snake__length-1; l >= 0; l--)		
				snake__y__length[l+1] = snake__y__length[l]; 
			
			for (int i = snake__length; i >= 0; i--)
			{
				if (i == 0)		
					snake__x__length[i] -= 25;
				else				
					snake__x__length[i] = snake__x__length[i-1];
			}
			repaint();
		}
		
		if (up)
		{
			for (int l = snake__length-1; l >= 0; l--)		
				snake__x__length[l+1] = snake__x__length[l]; 
			
			for (int i = snake__length; i >= 0; i--)
			{
				if (i == 0)		
					snake__y__length[i] -= 25;
				else				
					snake__y__length[i] = snake__y__length[i-1];
			}
			repaint();
		}
		
		if (down)
		{
			for (int l = snake__length-1; l >= 0; l--)		
				snake__x__length[l+1] = snake__x__length[l]; 
			
			for (int i = snake__length; i >= 0; i--)
			{
				if (i == 0)		
					snake__y__length[i] += 25;
				else				//copy the rest of the body
					snake__y__length[i] = snake__y__length[i-1];
			}
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if (end == true)
			{
				moves = 0;
				score = 0;
				snake__length = 2;
				end = false;
				start = false;
				repaint();
				create__bush();
			}
			
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if (end == true)
				return;
			start = true;
			if (start == true)
			{
				moves++;
				if (left)			//if going left cannot go right
					right = false;
				else
					right = true;
				up = false;
				down = false;	
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if (end == true)
				return;
			if (start == true)
			{
				moves++;
				if (right)
				{
					left = false;
				}
				else
					left = true;
				up = false;
				down = false;
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			if (end == true)
				return;
			start = true;
			if (start == true)
			{
				moves++;
				if (down)
					up = false;
				else
					up = true;
				left = false;
				right = false;	
				}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if (end == true)
				return;
			start = true;
			if (start == true)
			{
				moves++;
				if (up)
					down = false;
				else
					down = true;
				left = false;
				right = false;	
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			JFrame parent = (JFrame) this.getTopLevelAncestor();
		    parent.dispose();
			Main frame = new Main();
			frame.setVisible(true);
			frame.setTitle("Snake It");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
