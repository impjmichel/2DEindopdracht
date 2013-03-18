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
import view.MenuPanel;
import view.world.GameHero;
import view.world.GameLevel;
import view.world.GameSpike;
import view.world.GameWorld;

public class L1M35 extends GameLevel implements ActionListener
{
	private World world2D;
	private GameFrame frame;
	private Timer timer;
	private Body hero2D, roof, roof2, floor,floor2;
	private GameSpike spike;
	private ArrayList<GameSpike> spikes;
	private GameHero hero;
	
	public L1M35(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world);
		this.frame = frame;
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);

		spikes = new ArrayList<GameSpike>();
		spike = new GameSpike(new Vector2f(590,227), world);
		Body spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(610,227), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(820,227), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(800,227), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(695,373), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(715,373), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		for(int i = 0; i < 23; i++)
		{
			spike = new GameSpike(new Vector2f(i*20-2,30), world);
			spikey = spike.getBody();
			spikes.add(spike);
			world2D.add(spikey);
		}
		for(int i = 0; i < 23; i++)
		{
			spike = new GameSpike(new Vector2f(i*20-2,570), world);
			spikey = spike.getBody();
			spikey.adjustRotation((float) Math.PI);
			spikes.add(spike);
			world2D.add(spikey);
		}
		
		roof = new StaticBody("", new Box(500f,40f));
		roof.setPosition(770f, 200);
		roof2 = new StaticBody("", new Box(400f,40f));
		roof2.setPosition(470f, 25);
		roof2.adjustRotation((float) (Math.PI/18*7));
		floor = new StaticBody("", new Box(500f,40f));
		floor.setPosition(770f, 400);
		floor2 = new StaticBody("", new Box(400f,40f));
		floor2.setPosition(470f, 575);
		floor2.adjustRotation((float) -(Math.PI/18*7));
		
		world2D.add(floor);
		world2D.add(floor2);
		world2D.add(roof);
		world2D.add(roof2);
		
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

		drawBox(g2,floor);
		drawBox(g2,floor2);
		drawBox(g2,roof);
		drawBox(g2,roof2);
		if(spikes.size() > 0)
		{
			for(GameSpike spikey : spikes)
				spikey.drawSpike(g2);
		}
		hero.drawHero(g2);
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
		if(hero2D.getPosition().getX()<0)
			frame.loadMap(new L1M34(world,frame,new Vector2f(890,world.getY())));
		else if(hero2D.getPosition().getX()>900)
			frame.loadMap(new L1M36(world,frame, new Vector2f(10f,world.getY())));
	}
}
