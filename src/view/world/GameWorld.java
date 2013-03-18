package view.world;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.World;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import view.GameFrame;
import view.world.level1.L1M3;
import view.world.level1.L1M36;

public class GameWorld
{
	private World world2D;
	private Vector2f gravity;
	private GameHero hero;
	private GameFrame frame;
	private int saveSpot;
	private boolean gravitySuit;
	private boolean closedL1M2;
	private boolean closedL1M3;
	private int gameHints;
	private boolean dead;

	public GameWorld()
	{
		gravitySuit = false;
		gravity = new Vector2f(.0f,30.0f);
		world2D = new World(gravity,10,new QuadSpaceStrategy(20,5));
		hero = new GameHero(this);
		closedL1M2 = true;
		closedL1M3 = true;
		gameHints = 0;
		saveSpot = 0;
		dead = false;
	}
	
	public void flip()
	{
		if(gravitySuit)
		{
			float y = gravity.y*-1;
			world2D.setGravity(0, y);
		}
	}
	
	public void setFrame(GameFrame frame)
	{
		this.frame = frame;
	}
	
	public void killHero()
	{
		setDead(true);
		
		switch(saveSpot)
		{
		case 0: frame.loadMap(new L1M3(this,frame,new Vector2f(450,400)));
			break;
		case 1: // L1 M8
			break;
		case 2: // L1 M18
			break;
		case 3: // L1 M23
			break;
		case 4: frame.loadMap(new L1M36(this,frame,new Vector2f(425,300)));
			break;
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

	public boolean isClosedL1M2()
	{
		return closedL1M2;
	}

	public void setClosedL1M2(boolean closedL1M2)
	{
		this.closedL1M2 = closedL1M2;
	}
	public boolean isClosedL1M3()
	{
		return closedL1M3;
	}

	public void setClosedL1M3(boolean closedL1M3)
	{
		this.closedL1M3 = closedL1M3;
	}

	public int getGameHints()
	{
		return gameHints;
	}

	public void setGameHints(int gameHints)
	{
		this.gameHints = gameHints;
	}

	public boolean isGravitySuit()
	{
		return gravitySuit;
	}

	public void setGravitySuit(boolean gravitySuit)
	{
		this.gravitySuit = gravitySuit;
	}

	public int getSaveSpot()
	{
		return saveSpot;
	}

	public void setSaveSpot(int saveSpot)
	{
		this.saveSpot = saveSpot;
	}

	public boolean isDead()
	{
		return dead;
	}

	public void setDead(boolean dead)
	{
		this.dead = dead;
	}
	
}
