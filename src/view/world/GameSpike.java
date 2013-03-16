package view.world;

import java.awt.Graphics2D;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Polygon;

public class GameSpike
{
	private Vector2f position;
	private Body spike;

	public GameSpike(Vector2f position)
	{
		this.position = position;
		Vector2f p1 = new Vector2f(position.x,position.y);
		Vector2f p2 = new Vector2f(position.x+20,position.y);
		Vector2f p3 = new Vector2f(position.x+10,position.y+20);
		
		
		ROVector2f[] vertices = {p1,p2,p3};
		spike = new StaticBody("spike", new Polygon(vertices));
		spike.setPosition(position.x, position.y);
	}

	public Body getSpike()
	{
		return spike;
	}

	public void setSpike(Body spike)
	{
		this.spike = spike;
	}

	public Vector2f getPosition()
	{
		return position;
	}

	public void setPosition(Vector2f position)
	{
		this.position = position;
	}
	
	public void drawSpike(Graphics2D g2)
	{
		Polygon spikey = (Polygon) spike.getShape();
		ROVector2f[] pts = spikey.getVertices();

		ROVector2f p1 = pts[0];
		ROVector2f p2 = pts[1];
		ROVector2f p3 = pts[2];
		
		g2.drawLine((int) p1.getX(),(int) p1.getY(),(int) p2.getX(),(int) p2.getY());
		g2.drawLine((int) p2.getX(),(int) p2.getY(),(int) p3.getX(),(int) p3.getY());
		g2.drawLine((int) p3.getX(),(int) p3.getY(),(int) p1.getX(),(int) p1.getY());
	}
}
