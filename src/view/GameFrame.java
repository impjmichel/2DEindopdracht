package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.world.GameLevel;

public class GameFrame extends JFrame implements KeyListener
{
	private GameLevel content;
	private LoadScreen loading;
	private ArrayList<ArrayList<GameLevel>> levels;
	private int level, map;
	private int defaultKeys;

	public GameFrame()
	{
		super("Gravity Control");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		defaultKeys = 1;
		level = 0;
		map = 0;
		loading = new LoadScreen(this);
		content = loading;
		setFocusable(true);
		addKeyListener(this);
		requestFocus();
		setContentPane(content);
	}

	@Override
	public void keyPressed(KeyEvent ke)
	{
		if(defaultKeys==1)
		{
			if(ke.getKeyCode() == KeyEvent.VK_DOWN)
				content.down();
			if(ke.getKeyCode() == KeyEvent.VK_UP)
				content.up();
			if(ke.getKeyCode() == KeyEvent.VK_LEFT)
				content.left();
			if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
				content.right();
		}
		else
		{
			if(ke.getKeyCode() == KeyEvent.VK_S)
				content.down();
			if(ke.getKeyCode() == KeyEvent.VK_W)
				content.up();
			if(ke.getKeyCode() == KeyEvent.VK_A)
				content.left();
			if(ke.getKeyCode() == KeyEvent.VK_D)
				content.right();	
		}
	}

	@Override
	public void keyReleased(KeyEvent ke)
	{
		if(defaultKeys==1)
		{
			if(ke.getKeyCode() == KeyEvent.VK_SPACE)
			{
				content.enter();
			}
		}
		else
		{
			if(ke.getKeyCode() == KeyEvent.VK_ENTER)
			{
				content.enter();
			}
		}
		if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			content.escape();
		}
	}

	@Override
	public void keyTyped(KeyEvent ke)
	{
		
	}

	public void setLevels(ArrayList<ArrayList<GameLevel>> levels)
	{
		this.levels = levels;
		loading.stop();
		setContentPane(new JPanel());
		content = levels.get(0).get(0);
		setContentPane(content);
		content.start();
		validate();
		repaint();
	}
	
	public void nextMap()
	{
		map++;
		content.stop();
		content = levels.get(level).get(map);
		setContentPane(content);
		content.start();
		validate();
		repaint();
	}
	public void previousMap()
	{
		map--;
		content.stop();
		content = levels.get(level).get(map);
		setContentPane(content);
		content.start();
		validate();
		repaint();
	}
	public void nextLevel()
	{
		level++;
		content.stop();
		content = levels.get(level).get(map);
		setContentPane(content);
		content.start();
		validate();
		repaint();
	}
	public void previousLevel()
	{
		level--;
		content.stop();
		content = levels.get(level).get(map);
		setContentPane(content);
		content.start();
		validate();
		repaint();
	}
	
	public void setDefaultKeys(int defaultKeys)
	{
		this.defaultKeys = defaultKeys;
	}

	public int isDefaultKeys()
	{
		return defaultKeys;
	}

	private class LoadScreen extends GameLevel implements ActionListener
	{
		private double direction;
		private Timer timer;
		
		public LoadScreen(GameFrame gframe)
		{
			setPreferredSize(new Dimension(900,600));
			direction = 0;
			timer = new Timer(1000/60, this);
			this.start();
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.translate(450, 250);
			Ellipse2D circle = new Ellipse2D.Double(-25, -25, 50, 50);
			Ellipse2D ball = new Ellipse2D.Double(-15, -15, 10, 10);
			AffineTransform tr = new AffineTransform();
			tr.rotate(direction,0,0);
			g2.draw(circle);
			g2.draw(tr.createTransformedShape(ball));
		}
		@Override
		public void up()
		{
		}

		@Override
		public void down()
		{
		}

		@Override
		public void left()
		{
		}

		@Override
		public void right()
		{
		}

		@Override
		public void enter()
		{
		}
		public void escape()
		{
			System.exit(0);
		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			update();
		}
		
		public void stop()
		{
			timer.stop();
		}
		public void start()
		{
			timer.start();
		}
		public void update()
		{
			direction = (direction+Math.PI/180)%(Math.PI*2);
			repaint();
		}
	}
}
