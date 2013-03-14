package view.world.level1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import view.GameFrame;
import view.MenuPanel;
import view.world.GameHero;
import view.world.GameLevel;
import view.world.GameWorld;

public class L1M1 extends GameLevel implements ActionListener
{
	private World world2D;
	private GameFrame frame;
	private Timer timer;
	private Body floor,wall1,wall2,roof1,roof2,hero2D;
	private GameHero hero;
	private ArrayList<Body> floors;
	
	public L1M1(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world);
		this.frame = frame;
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
		wall1 = new StaticBody("wall", new Box(40f,340f));
		wall1.setPosition(210f, 300);
		
		wall2 = new StaticBody("", new Box(40f,140f));
		wall2.setPosition(610f, 200);
		
		roof1 = new StaticBody("", new Box(440f,40f));
		roof1.setPosition(410f, 150);
		
		roof2 = new StaticBody("", new Box(320f,40f));
		roof2.setPosition(750f, 250);
		
		floor = new StaticBody("", new Box(720f,40f));
		floor.setPosition(550f, 450);
		
		floors = new ArrayList<Body>();
		floors.add(roof1);
		floors.add(roof2);
		floors.add(floor);
		
		world2D.add(floor);
		world2D.add(roof1);
		world2D.add(roof2);
		world2D.add(wall1);
		world2D.add(wall2);
		
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
		drawBox(g2, wall1);
		drawBox(g2, wall2);
		g2.setColor(Color.RED);
		drawBox(g2, floor);
		drawBox(g2, roof1);
		drawBox(g2, roof2);
		g2.setColor(Color.BLUE);
		drawBox(g2, hero2D);
	}

	

	@Override
	public void left()
	{
		Vector2f left = new Vector2f(-10000f,0);
		hero.move(left);
	}

	@Override
	public void right()
	{
		Vector2f right = new Vector2f(10000f,0);
		hero.move(right);
	}

	@Override
	public void enter()
	{
	}

	@Override
	public void escape()
	{
		frame.loadMap(new MenuPanel(frame,world));
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

		
		BodyList list = hero2D.getConnected();
		if(list.size() > 0)
		{
			for(Body ground : floors)
			{
				if(list.contains(ground))
				{
					world.setGrounded(true);
					break;
				}
				else
					world.setGrounded(false);
			}
		}
		if(hero2D.getPosition().getX()>900)
		{
			frame.loadMap(new L1M2(world,frame, new Vector2f(10f,world.getY())));
//			setHeroPosition(new Vector2f(10f,world.getY()+80));
		}
	}
}
