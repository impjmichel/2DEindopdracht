package view.world;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.World;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import view.GameFrame;
import view.world.level1.L1M3;
import view.world.level1.L1M36;

public class GameWorld implements ActionListener
{
	private World world2D;
	private Vector2f gravity;
	private GameHero hero;
	private GameFrame frame;
	private int saveSpot,timePlayed;
	public Timer time;
	private boolean gravitySuit;
	private boolean closedL1M2;
	private boolean closedL1M3;
	private boolean closedL1M20;
	private boolean closedL1M29;
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
		closedL1M20 = true;
		closedL1M29 = true;
		gameHints = 0;
		saveSpot = 0;
		dead = false;
		time = new Timer(1000/100,this);
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

	public boolean isClosedL1M20()
	{
		return closedL1M20;
	}

	public void setClosedL1M20(boolean closedL1M20)
	{
		this.closedL1M20 = closedL1M20;
	}

	public boolean isClosedL1M29()
	{
		return closedL1M29;
	}

	public void setClosedL1M29(boolean closedL1M29)
	{
		this.closedL1M29 = closedL1M29;
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

	public int getTimePlayed()
	{
		return timePlayed;
	}

	public void setTimePlayed(int timePlayed)
	{
		this.timePlayed = timePlayed;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		timePlayed++;
	}
	
	public String slashPlayed()
	{
		String s = "";
		int hundredth = timePlayed%100;
		int sec = (timePlayed/100)%60;
		int min = (timePlayed/100/60)%60;
		int hour = timePlayed/100/60/60;
		s += hour+":";
		if(min<10)
			s += "0"+min+":";
		else
			s += min+":";
		if(sec<10)
			s += "0"+sec+":";
		else
			s += sec+":";
		if(hundredth<10)
			s += "0"+hundredth;
		else
			s += hundredth;
		return s;
	}
}
