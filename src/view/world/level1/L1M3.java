package view.world.level1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import view.GameFrame;
import view.MenuPanel;
import view.world.GameHero;
import view.world.GameLevel;
import view.world.GameWorld;

public class L1M3 extends GameLevel implements ActionListener
{
	private World world2D;
	private GameFrame frame;
	private Timer timer;
	private Body floor,floor2,floor3,
				 wall,wall2,wall3,wall4,wall5,wall6,wall7,wall8,
				 roof,roof2,roof3,roof4,roof5,roof6,roof7,
				 door,hero2D;
	private GameHero hero;
	private int doorY;

	
	public L1M3(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world);
		this.frame = frame;
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
		doorY = 370;
		door = new StaticBody("", new Box(30,280));
		door.setPosition(800, doorY);
		
		roof = new StaticBody("", new Box(385f,40f));
		roof.setPosition(432.5f, 130);
		roof2 = new StaticBody("", new Box(250f,40f));
		roof2.setPosition(-5f, 250);
		roof3 = new StaticBody("", new Box(120f,40f));
		roof3.setPosition(140f, 210);
		roof4 = new StaticBody("", new Box(120f,40f));
		roof4.setPosition(220f, 170);
		
		roof5 = new StaticBody("", new Box(250f,40f));
		roof5.setPosition(870f, 250);
		roof6 = new StaticBody("", new Box(120f,40f));
		roof6.setPosition(725f, 210);
		roof7 = new StaticBody("", new Box(120f,40f));
		roof7.setPosition(645f, 170);
		
		floor = new StaticBody("", new Box(700f,40f));
		floor.setPosition(590f, 530);
		floor2 = new StaticBody("", new Box(200f,40f));
		floor2.setPosition(180f, 490);
		floor3 = new StaticBody("", new Box(250f,40f));
		floor3.setPosition(-5f, 450);
		
		wall = new StaticBody("", new Box(40f,79f));
		wall.setPosition(100f, 470);
		wall2 = new StaticBody("", new Box(40f,79f));
		wall2.setPosition(260f, 510);
		wall3 = new StaticBody("", new Box(40f,79f));
		wall3.setPosition(100f, 230);
		wall4 = new StaticBody("", new Box(40f,79f));
		wall4.setPosition(180f, 190);
		wall5 = new StaticBody("", new Box(40f,79f));
		wall5.setPosition(260f, 150);
		
		wall6 = new StaticBody("", new Box(40f,79f));
		wall6.setPosition(765f, 230);
		wall7 = new StaticBody("", new Box(40f,79f));
		wall7.setPosition(685f, 190);
		wall8 = new StaticBody("", new Box(40f,79f));
		wall8.setPosition(605f, 150);
		
		world2D.add(floor);
		world2D.add(floor2);
		world2D.add(floor3);
		world2D.add(wall);
		world2D.add(wall2);
		world2D.add(wall3);
		world2D.add(wall4);
		world2D.add(wall5);
		world2D.add(wall6);
		world2D.add(wall7);
		world2D.add(wall8);
		world2D.add(roof);
		world2D.add(roof2);
		world2D.add(roof3);
		world2D.add(roof4);
		world2D.add(roof5);
		world2D.add(roof6);
		world2D.add(roof7);
		world2D.add(door);
		
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
		drawBox(g2, wall);
		drawBox(g2, wall2);
		drawBox(g2, wall3);
		drawBox(g2, wall4);
		drawBox(g2, wall5);
		drawBox(g2, wall6);
		drawBox(g2, wall7);
		drawBox(g2, wall8);
		g2.setColor(Color.RED);
		drawBox(g2, floor);
		drawBox(g2, floor2);
		drawBox(g2, floor3);
		drawBox(g2, roof);
		drawBox(g2, roof2);
		drawBox(g2, roof3);
		drawBox(g2, roof4);
		drawBox(g2, roof5);
		drawBox(g2, roof6);
		drawBox(g2, roof7);
		
		g2.setColor(Color.BLUE);
		drawBox(g2, hero2D);
		g2.setColor(Color.BLACK);
		fillBox(g2, door);
		
		g2.setColor(new Color(255,255,255,90));
		Rectangle2D pc = new Rectangle2D.Double(400,400,100,100);
		g2.fill(pc);
	}

	

	@Override
	public void left()
	{
		Vector2f left = new Vector2f(-100000f,0);
		hero.move(left);
	}

	@Override
	public void right()
	{
		Vector2f right = new Vector2f(100000f,0);
		hero.move(right);
	}

	@Override
	public void enter()
	{
		int x = (int) hero2D.getPosition().getX();
		int y = (int) hero2D.getPosition().getY();
		if(x > 600 && x < 700)
		{
			if(y > 330 && y < 440)
			{
				world.setClosedL1M3(false);
			}
		}
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
		if(!world.isClosedL1M3())
		{
			if(doorY > 169)
			{
				doorY--;
				door.setPosition(800, doorY);
			}
		}
		for(int i=0; i<5; i++) 
		{
			world2D.step();
			validate();
		}
		repaint();
		if(hero2D.getPosition().getX()<0)
			frame.loadMap(new L1M2(world,frame,new Vector2f(870f,world.getY())));
	}
	
}
