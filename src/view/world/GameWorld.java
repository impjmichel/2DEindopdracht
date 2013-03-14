package view.world;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.World;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

public class GameWorld
{
	private World world2D;
	private Vector2f gravity;
	private boolean grounded;
	private GameHero hero;
	private float x,y;
	private boolean gravitySuit;

	public GameWorld()
	{
		grounded = true;
		gravitySuit = true;
		gravity = new Vector2f(.0f,30.0f);
		world2D = new World(gravity,10,new QuadSpaceStrategy(20,5));
		hero = new GameHero();
	}
	
	public void flip()
	{
		System.out.println(""+grounded);
		if(grounded && gravitySuit)
		{
			float y = gravity.y*-1;
			world2D.setGravity(0, y);
			grounded = false;
			System.out.println(""+grounded);
		}
	}
	
	public World getWorld2D()
	{
		return world2D;
	}

	public void setWorld2D(World world2d)
	{
		world2D = world2d;
	}

	public Vector2f getGravity()
	{
		return gravity;
	}

	public void setGravity(Vector2f gravity)
	{
		this.gravity = gravity;
	}

	public boolean isGrounded()
	{
		return grounded;
	}

	public void setGrounded(boolean grounded)
	{
		this.grounded = grounded;
	}

	public GameHero getHero()
	{
		return hero;
	}

	public float getX()
	{
		return hero.getHeroBody().getPosition().getX();
	}

	public float getY()
	{
		return hero.getHeroBody().getPosition().getY();
	}
	
}
