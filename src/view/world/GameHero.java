package view.world;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

public class GameHero
{
	private GameWorld world;
	private Body body;
	private Image stickman;
	private int counter;
	private int walking;
	private int running;
	private boolean paused = false;
	
	public GameHero(GameWorld world)
	{
		this.world = world;
		counter = 0;
		walking = 0;
		running = 0;
		switchBody();
		
		try
		{
			stickman = ImageIO.read(new File("src/view/img/man50x80.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Body getHeroBody()
	{
		return body;
	}
	
	public void switchBody()
	{
		float posX = 0;
		float posY = 0;
		if(body != null)
		{
			posX = body.getPosition().getX();
			posY = body.getPosition().getY();
		}
		if(world.isGravitySuit())
		{
			body = new Body("hero", new Box(70f,70f), 100);
			body.setPosition(posX, posY);
			body.setRestitution(0.6f);
			body.setFriction(0.5f);
			body.setRotatable(true);
			body.setMaxVelocity(40, 100);
		}
		else
		{
			body = new Body("hero", new Box(30f,80f), 100);
			body.setPosition(posX, posY);
			body.setRestitution(0.7f);
			body.setFriction(0.6f);
			body.setRotatable(false);
			body.setMaxVelocity(50, 100);
		}
	}
	
	public void move(Vector2f direction)
	{
		body.setForce(direction.x, direction.y);
	}
	
	public Shape getRectangle2D()
	{
		Rectangle2D r = null;
		double posX = body.getPosition().getX();
		double posY = body.getPosition().getY();
		double rotation = body.getRotation();
		r = new Rectangle2D.Double(posX-35,posY-35,70,70);
		AffineTransform tr = new AffineTransform();
		tr.rotate(rotation, posY, posY);
		
		return tr.createTransformedShape(r);
	}
	
	public void drawHero(Graphics2D g2)
	{
		counter = (counter+1)%20;
		if(counter == 19)
			walking = (walking+1)%3;
		if(counter == 19 || counter == 9)
			running = (running+1)%4;
		if(world.isGravitySuit())
		{
			Box box = (Box) body.getShape();
			Vector2f[] pts = box.getPoints(body.getPosition(), body.getRotation());
	
			Vector2f p1 = pts[0];
			Vector2f p2 = pts[1];
			Vector2f p3 = pts[2];
			Vector2f p4 = pts[3];
			
			g2.drawLine((int) p1.x,(int) p1.y,(int) p2.x,(int) p2.y);
			g2.drawLine((int) p2.x,(int) p2.y,(int) p3.x,(int) p3.y);
			g2.drawLine((int) p3.x,(int) p3.y,(int) p4.x,(int) p4.y);
			g2.drawLine((int) p4.x,(int) p4.y,(int) p1.x,(int) p1.y);
		}
		else
		{
			Box box = (Box) body.getShape();
			Vector2f[] pts = box.getPoints(body.getPosition(), body.getRotation());
			Vector2f p1 = pts[0];
			float v = body.getVelocity().getX();
			if(stickman != null)
			{
				if((v > -5 && v < 5) || paused)
				{
					if(stickman != null)
					{
						Image subImg = ((BufferedImage) stickman).getSubimage(0, 0, 50, 80);
						g2.drawImage(subImg, (int) p1.x-10, (int) p1.y+1, 50, 80, null);
					}
				}
				if((v > 5 && v < 20) && !paused)
				{
					if(stickman != null)
					{
						BufferedImage subImg = ((BufferedImage) stickman).getSubimage(50+50*walking, 0, 50, 80);
						g2.drawImage(subImg, (int) p1.x-10, (int) p1.y+1, 50, 80, null);
					}
				}
				if(v > 20 && !paused)
				{
					if(stickman != null)
					{
						BufferedImage subImg = ((BufferedImage) stickman).getSubimage(50*running, 80, 50, 80);
						g2.drawImage(subImg, (int) p1.x-10, (int) p1.y+2, 50, 80, null);
					}
				}
				if((v < -5 && v > -20) && !paused)
				{
						BufferedImage subImg = ((BufferedImage) stickman).getSubimage(50+50*walking, 160, 50, 80);
						g2.drawImage(subImg, (int) p1.x-10, (int) p1.y+1, 50, 80, null);
				}
				if(v < -20 && !paused)
				{
						BufferedImage subImg = ((BufferedImage) stickman).getSubimage(50*running, 240, 50, 80);
						g2.drawImage(subImg, (int)(p1.x)-10, (int) p1.y+2, 50, 80, null);
				}
			}
		}
	}

	public boolean isPaused()
	{
		return paused;
	}

	public void setPaused(boolean paused)
	{
		this.paused = paused;
	}
}
