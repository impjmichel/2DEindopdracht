package view.world;

import java.awt.Graphics2D;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Box;

public class GameSpike
{
	private Vector2f position;
	private Body spike;
	private GameWorld world;

	public GameSpike(Vector2f position, GameWorld world)
	{
		this.position = position;
		this.world = world;
		
		spike = new StaticBody("spike", new Box(14,14));
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
		Box box = (Box) spike.getShape();
		Vector2f[] pts = box.getPoints(spike.getPosition(), spike.getRotation());

		Vector2f p1 = pts[0];
		Vector2f p2 = pts[1];
		Vector2f p3 = pts[2];
		
		if(spike.getRotation() == (float) ((Math.PI/2)*3))
		{
			g2.drawLine((int) p1.x,(int) p1.y+3,(int) p2.x,(int) p2.y-3);
			g2.drawLine((int) p2.x,(int) p2.y-3,(int) p3.x+6,(int) (p1.y+p2.y)/2);
			g2.drawLine((int) p3.x+6,(int) (p1.y+p2.y)/2,(int) p1.x,(int) p1.y+3);
		}
		else if(spike.getRotation() == (float) (Math.PI))
		{
			g2.drawLine((int) p1.x+3,(int) p1.y,(int) p2.x-3,(int) p2.y);
			g2.drawLine((int) p2.x-3,(int) p2.y,(int) (p2.x+p1.x)/2,(int) p3.y-6);
			g2.drawLine((int) (p2.x+p1.x)/2,(int) p3.y-6,(int) p1.x+3,(int) p1.y);
		}
		else if(spike.getRotation() == (float) (Math.PI/2))
		{
			g2.drawLine((int) p1.x,(int) p1.y-3,(int) p2.x,(int) p2.y+3);
			g2.drawLine((int) p2.x,(int) p2.y+3,(int) p3.x-6,(int) (p1.y+p2.y)/2);
			g2.drawLine((int) p3.x-6,(int) (p1.y+p2.y)/2,(int) p1.x,(int) p1.y-3);
		}
		else if(spike.getRotation() == 0)
		{
			g2.drawLine((int) p1.x-3,(int) p1.y,(int) p2.x+3,(int) p2.y);
			g2.drawLine((int) p2.x+3,(int) p2.y,(int) (p2.x+p1.x)/2,(int) p3.y+6);
			g2.drawLine((int) (p2.x+p1.x)/2,(int) p3.y+6,(int) p1.x-3,(int) p1.y);
		}
		
		BodyList list = spike.getTouching();
		if(list.contains(world.getHero().getHeroBody()))
		{
			try
			{
				Thread.sleep(600);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			world.killHero();
		}
	}
}
