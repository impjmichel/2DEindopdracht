package view.world.level1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
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

public class L1M13 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private Timer timer;
	private World world2D;
	private Body hero2D, roof,roof2,roof3,floor,floor2,
				 wall,wall2,wall3,wall4,wall5,wall6;
	private GameSpike spike;
	private ArrayList<GameSpike> spikes;
	private GameHero hero;
	private int deadCounter,imgX,imgY;
	private boolean gettingUp;
	private Image portal,portalBG,portalBG2;
	
	public L1M13(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world, frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		deadCounter = 1;
		imgX = 0;
		imgY = 0;
		gettingUp = false;
		
		roof = new StaticBody("", new Box(480f,40f));
		roof.setPosition(460, 10);
		roof2 = new StaticBody("", new Box(300,40f));
		roof2.setPosition(110f, 240);
		roof3 = new StaticBody("", new Box(80,40f));
		roof3.setPosition(700f, 240);
		 
		floor = new StaticBody("", new Box(1000f,40f));
		floor.setPosition(400f, 460);
		floor2 = new StaticBody("", new Box(80f,40f));
		floor2.setPosition(460f, 240);
		
		wall = new StaticBody("", new Box(40f,499f));
		wall.setPosition(880, 230);
		wall2 = new StaticBody("", new Box(40f,279f));
		wall2.setPosition(720f, 120);
		wall3 = new StaticBody("", new Box(40f,259f));
		wall3.setPosition(480, 350);
		wall4 = new StaticBody("", new Box(40f,259f));
		wall4.setPosition(440f, 350);
		wall5 = new StaticBody("", new Box(40f,279f));
		wall5.setPosition(240f, 120);
		wall6 = new StaticBody("", new Box(40f,279f));
		wall6.setPosition(680f, 120);
		
		spikes = new ArrayList<GameSpike>();
		for(int i = 0; i < 18; i++)
		{
			spike = new GameSpike(new Vector2f(510+20*i,433), world);
			Body spikey = spike.getBody();
			spikey.adjustRotation((float) Math.PI);
			spikes.add(spike);
			world2D.add(spikey);
		}
		for(int i = 0; i < 9; i++)
		{
			spike = new GameSpike(new Vector2f(410-20*i,433), world);
			Body spikey = spike.getBody();
			spikey.adjustRotation((float) Math.PI);
			spikes.add(spike);
			world2D.add(spikey);
		}
		for(int i = 0; i < 20; i++)
		{
			spike = new GameSpike(new Vector2f(270+20*i,37), world);
			Body spikey = spike.getBody();
			spikes.add(spike);
			world2D.add(spikey);
		}
		
		world2D.add(roof);
		world2D.add(roof2);
		world2D.add(roof3);
		world2D.add(floor2);
		world2D.add(floor);
		world2D.add(wall);
		world2D.add(wall2);
		world2D.add(wall3);
		world2D.add(wall4);
		world2D.add(wall5);
		world2D.add(wall6);
		
		hero = world.getHero();
		hero2D = hero.getHeroBody();
		hero2D.setPosition(position.x, position.y);
		world2D.add(hero2D);	
		
		try
		{
			portal = ImageIO.read(L1M6.class.getResource("/portal160x165.png"));
			portalBG = ImageIO.read(L1M6.class.getResource("/portal bg.png"));
			portalBG2 = ImageIO.read(L1M6.class.getResource("/portal bg2.png"));
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
		drawBox(g2, floor);
		drawBox(g2, floor2);
		drawBox(g2, roof);
		drawBox(g2, roof2);
		drawBox(g2, roof3);
		drawBox(g2, wall);
		drawBox(g2, wall2);
		drawBox(g2, wall3);
		drawBox(g2, wall4);
		drawBox(g2, wall5);
		drawBox(g2, wall6);
		
		if(spikes.size() > 0)
		{
			for(GameSpike spikey : spikes)
				spikey.drawSpike(g2);
		}
		
		AffineTransform tr = new AffineTransform();
		tr.translate(651.5, 388);
		tr.scale(.6, -.8);
		
		if(world.getSaveSpot()==3)
			g2.drawImage(portalBG, tr, null);
		else
			g2.drawImage(portalBG2, tr, null);

		if(world.isDead())
		{
			Image subImg = ((BufferedImage) portal).getSubimage(160*imgX, 165*imgY, 160, 165);
			g2.drawImage(subImg, tr, null);
		}
		if(gettingUp)
		{
			Image subImg = ((BufferedImage) portal).getSubimage(160*imgX, 165*imgY, 160, 165);
			g2.drawImage(subImg, tr, null);
		}
		if(!world.isDead())
			hero.drawHero(g2);
	}
	
	@Override
	public void enter()
	{
		if(world.getX() > 650 && world.getX() < 750)
		{
			if(world.getY() > 260 && world.getY() < 420)
				world.setSaveSpot(3);
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
		if(world.isDead() || gettingUp)
			deadCounter++;
		if(!world.isDead())
		{
			for(int i=0; i<5; i++) 
			{
				world2D.step();
				validate();
			}
		}
		else
		{
			if(deadCounter%4 == 0)
			{
				imgX = (imgX+1)%5;
				if(imgX == 0)
					imgY = (imgY+1)%3;
			}
			if(imgY == 2 && imgX == 4)
			{
				deadCounter = 0;
				gettingUp = true;
				imgX = 0;
			}
		}
		if(gettingUp)
		{
			imgY = 3;
			if(deadCounter%20 == 19)
				imgX++;
			if(imgX == 2)
				world.setDead(false);
			if(imgX == 4)
				gettingUp = false;
		}
		repaint();
		if(hero2D.getPosition().getX() < 0)
			frame.loadMap(new L1M14(world,frame,new Vector2f(870f,world.getY())));
		if(hero2D.getPosition().getY() < 0)
			frame.loadMap(new L1M12(world,frame, new Vector2f(world.getX(),590)));
	}
}
