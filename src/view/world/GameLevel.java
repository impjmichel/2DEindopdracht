package view.world;

import java.awt.Graphics;

public abstract class GameLevel extends GameWorld
{

	public GameLevel()
	{
		// TODO Auto-generated constructor stub
	}
	
	public abstract void up();
	public abstract void down();
	public abstract void left();
	public abstract void right();
	public abstract void enter();
	public abstract void escape();
	public abstract void start();
	public abstract void stop();
	public void update()
	{
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
	}
}
