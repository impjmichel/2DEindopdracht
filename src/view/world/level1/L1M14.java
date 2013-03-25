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
import view.world.GameMovingEnemy;
import view.world.GameWorld;

public class L1M14 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private World world2D;
	private Timer timer;
	private Body floor,floor2,floor3,wall,wall2,roof,hero2D;
	private GameHero hero;
	private GameMovingEnemy enemy1;

	public L1M14(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
		roof = new StaticBody("", new Box(1200f,40f));
		roof.setPosition(550f, 240);
		
		floor = new StaticBody("", new Box(440f,40f));
		floor.setPosition(810f, 460);
		floor2 = new StaticBody("", new Box(160f,40f));
		floor2.setPosition(550f, 540);
		floor3 = new StaticBody("", new Box(620f,40f));
		floor3.setPosition(200, 460);
		
		wall = new StaticBody("", new Box(40f,119f));
		wall.setPosition(610f, 500);
		wall2 = new StaticBody("", new Box(40f,119f));
		wall2.setPosition(490f, 500);
		
		world2D.add(roof);
		world2D.add(floor2);
		world2D.add(floor3);
		world2D.add(floor);
		world2D.add(wall);
		world2D.add(wall2);
		
		hero = world.getHero();
		hero2D = hero.getHeroBody();
		hero2D.setPosition(position.x, position.y);
		world2D.add(hero2D);	
		
		enemy1 = new GameMovingEnemy(new Vector2f(200,350), new Vector2f(12000,350),200,160,1.5f, world);
		world2D.add(enemy1.getBody());
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setStroke(new BasicStroke(2));
		g2.setColor(new Color(40,40,40));
		drawBox(g2, floor);
		drawBox(g2, floor2);
		drawBox(g2, floor3);
		drawBox(g2, roof);
		drawBox(g2, wall);
		drawBox(g2, wall2);
		enemy1.drawEnemy(g2);
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
		for(int i=0; i<5; i++) 
		{
			world2D.step();
			validate();
		}
		enemy1.update();
		repaint();
		if(hero2D.getPosition().getX()<0)
			frame.loadMap(new L1M15(world,frame,new Vector2f(870f,world.getY())));
		if(hero2D.getPosition().getX()>900)
			frame.loadMap(new L1M13(world,frame, new Vector2f(10,world.getY())));
	}
}

