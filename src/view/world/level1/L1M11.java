package view.world.level1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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

public class L1M11 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private World world2D;
	private Timer timer;
	private Body hero2D,roof,roof2,roof3,roof4,floor,floor2,
				 wall,wall2,wall3;
	private GameSpike spike;
	private ArrayList<GameSpike> spikes;
	private GameHero hero;
	private Image pcImage;
	private int pcX;
	private int pcCounter;
	
	public L1M11(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		pcX = 0;
		pcCounter = 0;
		
		roof = new StaticBody("", new Box(400f,40f));
		roof.setPosition(840f, 450);
		roof2 = new StaticBody("", new Box(700f,40f));
		roof2.setPosition(600, 0);
		roof3 = new StaticBody("", new Box(80f,40f));
		roof3.setPosition(520f, 350);
		roof4 = new StaticBody("", new Box(80,40f));
		roof4.setPosition(290f, 350);
		
		floor = new StaticBody("", new Box(400,40f));
		floor.setPosition(840, 570);
		floor2 = new StaticBody("", new Box(400,40f));
		floor2.setPosition(720, 130);
		
		wall = new StaticBody("", new Box(40f,160));
		wall.setPosition(660, 510);
		wall2 = new StaticBody("", new Box(40f,520));
		wall2.setPosition(540, 370);
		wall3 = new StaticBody("", new Box(40f,650f));
		wall3.setPosition(270f, 300);
		
		spikes = new ArrayList<GameSpike>();
		for(int i = 0; i < 10; i++)
		{
			spike = new GameSpike(new Vector2f(513,120+20*i), world);
			Body spikey = spike.getBody();
			spikey.adjustRotation((float) Math.PI/2);
			spikes.add(spike);
			world2D.add(spikey);
		}
		for(int i = 0; i < 14; i++)
		{
			spike = new GameSpike(new Vector2f(297,40+20*i), world);
			Body spikey = spike.getBody();
			spikey.adjustRotation((float) Math.PI/2*3);
			spikes.add(spike);
			world2D.add(spikey);
		}
		for(int i = 0; i < 7; i++)
		{
			spike = new GameSpike(new Vector2f(310+i*20,27), world);
			Body spikey = spike.getBody();
			spikes.add(spike);
			world2D.add(spikey);
		}
		spike = new GameSpike(new Vector2f(300,377), world);
		Body spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(320,377), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(490,377), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(510,377), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(300,323), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(320,323), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(490,323), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(510,323), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
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
		
		hero = world.getHero();
		hero2D = hero.getHeroBody();
		hero2D.setPosition(position.x, position.y);
		world2D.add(hero2D);	
		
		try
		{
			pcImage = ImageIO.read(L1M2.class.getResource("/pc93.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
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
		if(spikes.size() > 0)
		{
			for(GameSpike spikey : spikes)
				spikey.drawSpike(g2);
		}
		if(pcImage != null)
		{
			BufferedImage subImg = ((BufferedImage) pcImage).getSubimage(pcX*93, 0, 93, 94);
			g2.drawImage(subImg, 680, 470, 80, 80, null);
		}
		hero.drawHero(g2);
	}

	@Override
	public void enter()
	{
		int x = (int) hero2D.getPosition().getX();
		int y = (int) hero2D.getPosition().getY();
		if(x > 650 && x < 750)
		{
			if(y > 450 && y < 580)
				world.setClosedL1M20(false);
		}
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
		pcCounter = (pcCounter+1)%5;
		if(pcCounter == 4 && world.isClosedL1M20())
			pcX = (pcX+1)%4;
		for(int i=0; i<5; i++) 
		{
			world2D.step();
			validate();
		}
		repaint();
		if(hero2D.getPosition().getX()>900)
			frame.loadMap(new L1M10(world,frame, new Vector2f(10,world.getY())));
		if(hero2D.getPosition().getY()>600)
			frame.loadMap(new L1M12(world,frame, new Vector2f(world.getX(),10)));
	}
}