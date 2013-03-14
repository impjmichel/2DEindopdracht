package view.world;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

public class GameHero
{
	private Body body;
	
	public GameHero()
	{
		body = new Body("hero", new Box(40f,80f), 100);
		body.setRestitution(0.7f);
		body.setFriction(0.6f);
		body.setRotatable(false);
	}

	public Body getHeroBody()
	{
		return body;
	}
	
	public void move(Vector2f direction)
	{
		body.setForce(direction.x, direction.y);
	}
}
