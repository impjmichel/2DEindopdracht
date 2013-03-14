package view.world;

import java.awt.Graphics;

import javax.swing.JPanel;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public abstract class GameWorld extends JPanel
{
	private World world;
	private Vec2 gravity;

	public GameWorld()
	{
		gravity = new Vec2(0.0f, -10.0f);
		boolean doSleep = true;
		world = new World(gravity, doSleep);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
	}
}
