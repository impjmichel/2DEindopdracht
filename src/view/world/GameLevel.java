package view.world;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

public abstract class GameLevel extends JPanel
{
	protected GameWorld world;
	
	public GameLevel(GameWorld world)
	{
		this.world = world;
	}
	
	public void up()
	{
		world.flip();
		world.setGrounded(false);
	}
	public void down()
	{
		world.flip();
		world.setGrounded(false);
	}
	public abstract void left();
	public abstract void right();
	public abstract void enter();
	public abstract void escape();
	public abstract void start();
	public abstract void stop();
	public void setHeroPosition(Vector2f position)
	{
		world.getHero().getHeroBody().setPosition(position.x, position.y);
	}
	public void update()
	{
		repaint();
	}
	
	public void drawBox(Graphics2D g2, Body b)
	{
		Box box = (Box) b.getShape();
		Vector2f[] pts = box.getPoints(b.getPosition(), b.getRotation());

		Vector2f p1 = pts[0];
		Vector2f p2 = pts[1];
		Vector2f p3 = pts[2];
		Vector2f p4 = pts[3];
		
		g2.drawLine((int) p1.x,(int) p1.y,(int) p2.x,(int) p2.y);
		g2.drawLine((int) p2.x,(int) p2.y,(int) p3.x,(int) p3.y);
		g2.drawLine((int) p3.x,(int) p3.y,(int) p4.x,(int) p4.y);
		g2.drawLine((int) p4.x,(int) p4.y,(int) p1.x,(int) p1.y);

	}
	public void fillBox(Graphics2D g2, Body b)
	{
		Box box = (Box) b.getShape();
		Vector2f[] pts = box.getPoints(b.getPosition(), b.getRotation());

		Vector2f p1 = pts[0];
		Vector2f p2 = pts[1];
		Vector2f p3 = pts[2];
		Vector2f p4 = pts[3];
		
		GeneralPath path = new GeneralPath();
		path.append(new Line2D.Double((int) p1.x,(int) p1.y,(int) p2.x,(int) p2.y),true);
		path.append(new Line2D.Double((int) p2.x,(int) p2.y,(int) p3.x,(int) p3.y),true);
		path.append(new Line2D.Double((int) p3.x,(int) p3.y,(int) p4.x,(int) p4.y),true);
		path.append(new Line2D.Double((int) p4.x,(int) p4.y,(int) p1.x,(int) p1.y),true);
		g2.fill(path);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}
