package view.world;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	private float speed;
	private double widthScale,heightScale;
	private Image enemyImage;
//	private double randImg;

	public GameMovingEnemy(Vector2f startPosition, Vector2f endPosition, int width, int height, float speed, GameWorld world)
	{
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.speed = speed;
		this.world = world;
		currentPosition = startPosition;
		goalPosition = endPosition;
		enemy = new StaticBody("enemy", new Box(width,height));
		enemy.setPosition(currentPosition.x, currentPosition.y);

		enemy.setGravityEffected(false);
		enemy.setMaxVelocity(20, 20);
		
		//TODO implement once multiple images exist
//		randImg = Math.random()*4;
		try
		{
			enemyImage = ImageIO.read(GameMovingEnemy.class.getResource("/moving enemy.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public GameMovingEnemy(Vector2f startPosition, Vector2f endPosition, int width, int height, GameWorld world)
	{
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		speed = 1;
		this.world = world;
		currentPosition = startPosition;
		goalPosition = endPosition;
		enemy = new StaticBody("enemy", new Box(width,height));
		enemy.setPosition(currentPosition.x, currentPosition.y);

		enemy.setGravityEffected(false);
		enemy.setMaxVelocity(20, 20);
		
		//TODO implement once multiple images exist
//		randImg = Math.random()*4;
		try
		{
			enemyImage = ImageIO.read(GameMovingEnemy.class.getResource("/moving enemy.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
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
		float x = (int) (goalPosition.x-currentPosition.x);
		float y = (int) (goalPosition.y-currentPosition.y);
		if(x < 0)
			x = -speed;
		else if(x > 0)
			x = speed;
		if(y < 0)
			y = -speed;
		else if(y > 0)
			y = speed;	
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
		
		if(enemyImage != null)
		{
			widthScale = (p2.x-p1.x)/200;
			heightScale = (p3.y-p1.y)/200;
			AffineTransform tr = new AffineTransform();
			tr.translate(p1.x, p1.y);
			tr.scale(widthScale, heightScale);
			g2.drawImage(enemyImage, tr, null);
		}
		
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
