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

public class GameFallingEnemy
{
	private Body enemy;
	private GameWorld world;
	private double widthScale,heightScale;
	private Image enemyImage;
//	private double randImg;
	
	public GameFallingEnemy(Vector2f startPosition, Vector2f maxVelocity, int width, int height, boolean rotate, GameWorld world)
	{
		this.world = world;
		enemy = new StaticBody("enemy", new Box(width,height));
		enemy.setPosition(startPosition.x, startPosition.y);
		enemy.setRotatable(rotate);
		enemy.setGravityEffected(true);
		enemy.setMaxVelocity(maxVelocity.x, maxVelocity.y);
		
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
			tr.rotate(enemy.getRotation(), 0, 0);
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
