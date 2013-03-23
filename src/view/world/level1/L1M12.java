package view.world.level1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import view.GameFrame;
import view.world.GameHero;
import view.world.GameLevel;
import view.world.GameWorld;

public class L1M12 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private World world2D;
	private Timer timer;
	private Body hero2D,roof,floor,
				 wall,wall2,wall3,wall4;
	private Body pk;
	private GameHero hero;
	
	public L1M12(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
		roof = new StaticBody("", new Box(480,40f));
		roof.adjustRotation((float)Math.PI/4);
		roof.setPosition(704f, 363);
		
		floor = new StaticBody("", new Box(638f,40f));
		floor.adjustRotation((float)Math.PI/4);
		floor.setPosition(501f, 314);
		
		wall = new StaticBody("", new Box(40f,150f));
		wall.setPosition(270f, 0);
		wall2 = new StaticBody("", new Box(40f,300));
		wall2.setPosition(540, 57);
		wall3 = new StaticBody("", new Box(40f,120));
		wall3.setPosition(720f, 586);
		wall4 = new StaticBody("", new Box(40f,79f));
		wall4.setPosition(880, 586);
		
		//progrss killer
	//-----------------------------------------------------------------------------
		pk = new StaticBody("", new Box(1200f,40f));
		pk.setPosition(450, 580);
		world2D.add(pk);
	//-----------------------------------------------------------------------------
		//progress killer
		
		world2D.add(roof);
		world2D.add(floor);
		world2D.add(wall);
		world2D.add(wall2);
		world2D.add(wall3);
		world2D.add(wall4);
		
		hero = world.getHero();
		hero2D = hero.getHeroBody();
		hero2D.setPosition(position.x, position.y);
		world2D.add(hero2D);		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setStroke(new BasicStroke(2));
		g2.setColor(new Color(40,40,40));
		drawBox(g2, roof);
		drawBox(g2, floor);
		drawBox(g2, wall);
		drawBox(g2, wall2);
		drawBox(g2, wall3);
		drawBox(g2, wall4);
		hero.drawHero(g2);
		
		//progrss killer
	//-----------------------------------------------------------------------------
			drawBox(g2, pk);
	//-----------------------------------------------------------------------------
		//progress killer
	}

	@Override
	public void enter()
	{
	}

	@Override
	public void start()
	{
		timer.start();
	}

	@Override
	public void stop()
	{
		timer.stop();	
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		for(int i=0; i<5; i++) 
		{
			world2D.step();
			validate();
		}
		repaint();
//		if(hero2D.getPosition().getY()>600)
//			frame.loadMap(new L1M13(world,frame, new Vector2f(world.getX(),10)));
		if(hero2D.getPosition().getY()<0)
			frame.loadMap(new L1M11(world,frame, new Vector2f(world.getX(),580)));
	}
}