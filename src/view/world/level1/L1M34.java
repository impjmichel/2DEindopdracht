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
import net.phys2d.raw.World;
import view.GameFrame;
import view.MenuPanel;
import view.world.GameHero;
import view.world.GameLevel;
import view.world.GameSpike;
import view.world.GameWorld;

public class L1M34 extends GameLevel implements ActionListener
{
	private World world2D;
	private GameFrame frame;
	private Timer timer;
	private Body hero2D;
	private GameSpike spike;
	private ArrayList<GameSpike> spikes;
	private GameHero hero;
	
	public L1M34(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world);
		this.frame = frame;
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);

		spikes = new ArrayList<GameSpike>();
		for(int i = 0; i < 46; i++)
		{
			spike = new GameSpike(new Vector2f(i*20,30), world);
			Body spikey = spike.getBody();
			spikes.add(spike);
			world2D.add(spikey);
		}
		for(int i = 0; i < 46; i++)
		{
			spike = new GameSpike(new Vector2f(i*20,570), world);
			Body spikey = spike.getBody();
			spikey.adjustRotation((float) Math.PI);
			spikes.add(spike);
			world2D.add(spikey);
		}
		
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
			frame.loadMap(new L1M21(world,frame,new Vector2f(890,world.getY())));
		else if(hero2D.getPosition().getX()>900)
			frame.loadMap(new L1M35(world,frame, new Vector2f(10f,world.getY())));
	}
}
