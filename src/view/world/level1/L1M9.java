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
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import view.GameFrame;
import view.world.GameHero;
import view.world.GameLevel;
import view.world.GameSpike;
import view.world.GameWorld;

public class L1M9 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private World world2D;
	private Timer timer;
	private Body hero2D,roof,roof2,roof3,roof4,floor,floor2,
				 wall,wall2,wall3,wall4,wall5,wall6,wall7,wall8;
	private GameSpike spike;
	private ArrayList<GameSpike> spikes;
	private GameHero hero;
	
	public L1M9(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
		roof = new StaticBody("", new Box(80f,40f));
		roof.setPosition(700f, 370);
		roof2 = new StaticBody("", new Box(80f,40f));
		roof2.setPosition(550, 260);
		roof3 = new StaticBody("", new Box(80f,40f));
		roof3.setPosition(400f, 110);
		roof4 = new StaticBody("", new Box(80,40f));
		roof4.setPosition(660f, 70);
		
		floor = new StaticBody("", new Box(220f,40f));
		floor.setPosition(470f, 520);
		floor2 = new StaticBody("", new Box(80f,40f));
		floor2.setPosition(550f, 220);
		
		wall = new StaticBody("", new Box(40f,300f));
		wall.setPosition(720, 500);
		wall2 = new StaticBody("", new Box(40f,340));
		wall2.setPosition(680, 220);
		wall3 = new StaticBody("", new Box(40f,100f));
		wall3.setPosition(640f, 40);
		wall4 = new StaticBody("", new Box(40f,79f));
		wall4.setPosition(570, 240);
		wall5 = new StaticBody("", new Box(40f,79f));
		wall5.setPosition(530, 240);
		wall6 = new StaticBody("", new Box(40f,120f));
		wall6.setPosition(560f, 560);
		wall7 = new StaticBody("", new Box(40f,260f));
		wall7.setPosition(420f, 0);
		wall8 = new StaticBody("", new Box(40f,449f));
		wall8.setPosition(380f, 315);
		
		spikes = new ArrayList<GameSpike>();
		for(int i = 0; i < 9; i++)
		{
			spike = new GameSpike(new Vector2f(410+i*20,493), world);
			Body spikey = spike.getBody();
			spikey.adjustRotation((float) Math.PI);
			spikes.add(spike);
			world2D.add(spikey);
		}
		for(int i = 0; i < 4; i++)
		{
			spike = new GameSpike(new Vector2f(520+i*20,193), world);
			Body spikey = spike.getBody();
			spikey.adjustRotation((float) Math.PI);
			spikes.add(spike);
			world2D.add(spikey);
		}
		for(int i = 0; i < 4; i++)
		{
			spike = new GameSpike(new Vector2f(520+i*20,287), world);
			Body spikey = spike.getBody();
			spikes.add(spike);
			world2D.add(spikey);
		}
		spike = new GameSpike(new Vector2f(670,398), world);
		Body spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(690,398), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(630,98), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(650,98), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(410,138), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(430,138), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		
		world2D.add(roof);
		world2D.add(roof2);
		world2D.add(roof3);
		world2D.add(roof4);
		world2D.add(floor);
		world2D.add(floor2);
		world2D.add(wall);
		world2D.add(wall2);
		world2D.add(wall3);
		world2D.add(wall4);
		world2D.add(wall5);
		world2D.add(wall6);
		world2D.add(wall7);
		world2D.add(wall8);
		
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
		drawBox(g2, roof2);
		drawBox(g2, roof3);
		drawBox(g2, roof4);
		drawBox(g2, floor);
		drawBox(g2, floor2);
		drawBox(g2, wall);
		drawBox(g2, wall2);
		drawBox(g2, wall3);
		drawBox(g2, wall4);
		drawBox(g2, wall5);
		drawBox(g2, wall6);
		drawBox(g2, wall7);
		drawBox(g2, wall8);
		if(spikes.size() > 0)
		{
			for(GameSpike spikey : spikes)
				spikey.drawSpike(g2);
		}
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
		repaint();
		if(hero2D.getPosition().getY()<0)
			frame.loadMap(new L1M10(world,frame, new Vector2f(world.getX(),580)));
		if(hero2D.getPosition().getY()>600)
			frame.loadMap(new L1M8(world,frame, new Vector2f(world.getX(),10)));
	}
}