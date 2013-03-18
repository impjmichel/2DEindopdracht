package view.world;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.ConvexPolygon;
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
		spike = new StaticBody("spike", new ConvexPolygon(vertices));
		spike.setPosition(position.x, position.y);
	}

	public Body getBody()
	{
		return spike;
	}

	public void setBody(Body spike)
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
		double rotation = spike.getRotation();
		GeneralPath path = new GeneralPath();
		path.append(new Line2D.Double(p1.getX(),p1.getY(),p2.getX(),p2.getY()), true);
		path.append(new Line2D.Double(p2.getX(),p2.getY(),p3.getX(),p3.getY()),true);
		path.append(new Line2D.Double(p3.getX(),p3.getY(),p1.getX(),p1.getY()),true);
		AffineTransform tr = new AffineTransform();
		tr.rotate(rotation,0,0);
		g2.draw(tr.createTransformedShape(path));
	}
}
