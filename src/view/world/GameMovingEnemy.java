package view.world;

import java.awt.Graphics2D;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Box;

public class GameMovingEnemy
{
	private Vector2f startPosition,endPosition, currentPosition, goalPosition;
	private Body enemy;
	private GameWorld world;
	private Vector2f direction;
	private double randImg;

	public GameMovingEnemy(Vector2f startPosition, Vector2f endPosition, int width, int height, GameWorld world)
	{
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.world = world;
		currentPosition = startPosition;
		goalPosition = endPosition;
		enemy = new StaticBody("enemy", new Box(width,height));
		enemy.setPosition(currentPosition.x, currentPosition.y);

		enemy.setGravityEffected(false);
		enemy.setMaxVelocity(20, 20);
		
		direction = getDir();
		randImg = Math.random()*4;
	}

	public Body getBody()
	{
		return enemy;
	}

	public void setBody(Body enemy)
	{
		this.enemy = enemy;
	}

	public Vector2f getPosition()
	{
		return currentPosition;
	}

	public void setPosition(Vector2f position)
	{
		this.currentPosition = position;
	}
	
	public Vector2f getDir()
	{
		int x = (int) (goalPosition.x-currentPosition.x);
		int y = (int) (goalPosition.y-currentPosition.y);
		if(x < 0)
			x = -1;
		else if(x > 0)
			x = 1;
		if(y < 0)
			y = -1;
		else if(y > 0)
			y = 1;	
		if(x == 0 && y == 0)
		{
			if(goalPosition == startPosition)
				goalPosition = endPosition;
			else if(goalPosition == endPosition)
				goalPosition = startPosition;
		}			
		return new Vector2f(x,y);
	}
	
	public void update()
	{
		Vector2f v = getDir();
		currentPosition = new Vector2f(currentPosition.x+v.x,currentPosition.y+v.y);
		enemy.move(currentPosition.x,currentPosition.y);	
	}
	
	public void drawEnemy(Graphics2D g2)
	{
		Box box = (Box) enemy.getShape();
		Vector2f[] pts = box.getPoints(enemy.getPosition(), enemy.getRotation());
		Vector2f p1 = pts[0];
		Vector2f p2 = pts[1];
		Vector2f p3 = pts[2];
		Vector2f p4 = pts[3];
		
		g2.drawLine((int) p1.x,(int) p1.y,(int) p2.x,(int) p2.y);
		g2.drawLine((int) p2.x,(int) p2.y,(int) p3.x,(int) p3.y);
		g2.drawLine((int) p3.x,(int) p3.y,(int) p4.x,(int) p4.y);
		g2.drawLine((int) p4.x,(int) p4.y,(int) p1.x,(int) p1.y);
		
		BodyList list = enemy.getTouching();
		if(list.contains(world.getHero().getHeroBody()))
		{
			try
			{
				Thread.sleep(1200);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			world.killHero();
		}
	}
}
