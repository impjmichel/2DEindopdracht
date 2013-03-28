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

public class L1M37 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private World world2D;
	private Timer timer;
	private Body floor,roof,door1,door2,door3,door4,hero2D;
	private GameHero hero;
	private int door1Y;
	private int door2Y;
	private int door3Y;
	private int door4Y;
	
	public L1M37(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
		door1Y = 280;
		door1 = new StaticBody("", new Box(30,200));
		door1.setPosition(500, door1Y);
		
		door2Y = 280;
		door2 = new StaticBody("", new Box(30,200));
		door2.setPosition(580, door2Y);
		
		door3Y = 280;
		door3 = new StaticBody("", new Box(30,200));
		door3.setPosition(660, door3Y);
		
		door4Y = 280;
		door4 = new StaticBody("", new Box(30,200));
		door4.setPosition(740, door3Y);
		
		roof = new StaticBody("", new Box(1200f,40f));
		roof.setPosition(550f, 200);
		
		floor = new StaticBody("", new Box(1200f,40f));
		floor.setPosition(550f, 400);
		
		world2D.add(floor);
		world2D.add(roof);
		world2D.add(door1);
		world2D.add(door2);
		world2D.add(door3);
		world2D.add(door4);
		
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
		drawBox(g2, floor);
		drawBox(g2, roof);
		fillBox(g2, door1);
		fillBox(g2, door2);
		fillBox(g2, door3);
		fillBox(g2, door4);
		
		hero.drawHero(g2);
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
		if(!world.isClosedL1M2())
		{
			if(door1Y > 119)
				door1Y --;
			if(door1Y > 119)
				door1Y --;
			if(door1Y > 119)
				door1Y --;
			if(door1Y > 119)
				door1Y --;
		}
		else
		{
			if(door1Y < 280)
				door1Y ++;
			if(door1Y < 280)
				door1Y ++;
			if(door1Y < 280)
				door1Y ++;
			if(door1Y < 280)
				door1Y ++;
		}
		if(!world.isClosedL1M3())
		{
			if(door2Y > 119)
				door2Y --;
			if(door2Y > 119)
				door2Y --;
			if(door2Y > 119)
				door2Y --;
		}
		else
		{
			if(door2Y < 280)
				door2Y ++;
			if(door2Y < 280)
				door2Y ++;
			if(door2Y < 280)
				door2Y ++;
		}
		if(!world.isClosedL1M20())
		{
			if(door3Y > 119)
				door3Y --;
			if(door3Y > 119)
				door3Y --;
		}
		else
		{
			if(door3Y < 280)
				door3Y ++;
			if(door3Y < 280)
				door3Y ++;
		}
		if(!world.isClosedL1M29())
		{
			if(door4Y > 119)
				door4Y--;
		}
		else
		{
			if(door4Y < 280)
				door4Y++;
		}
		door1.setPosition(500, door1Y);
		door2.setPosition(580, door2Y);
		door3.setPosition(660, door3Y);
		door4.setPosition(740, door4Y);
		for(int i=0; i<5; i++) 
		{
			world2D.step();
			validate();
		}
		repaint();
		if(hero2D.getPosition().getX()<0)
			frame.loadMap(new L1M36(world,frame,new Vector2f(870f,world.getY())));
		else if(hero2D.getPosition().getX()>900)
			frame.loadMap(new L1M38(world,frame, new Vector2f(10f,world.getY())));
	}
}
