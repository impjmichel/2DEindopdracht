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
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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

public class L1M36 extends GameLevel implements ActionListener
{
	private World world2D;
	private Timer timer;
	private Body hero2D, roof, floor;
	private GameHero hero;
	private int deadCounter,imgX,imgY;
	private boolean gettingUp;
	private Image portal,portalBG,portalBG2;
	
	public L1M36(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		deadCounter = 1;
		imgX = 0;
		imgY = 0;
		gettingUp = false;
		
		roof = new StaticBody("", new Box(1200f,40f));
		roof.setPosition(500f, 200);
		floor = new StaticBody("", new Box(1200f,40f));
		floor.setPosition(500f, 400);
		
		world2D.add(floor);
		world2D.add(roof);
		
		hero = world.getHero();
		hero2D = hero.getHeroBody();
		hero2D.setPosition(position.x, position.y);
		world2D.add(hero2D);		
		
		try
		{
			portal = ImageIO.read(new File("src/view/img/portal160x165.png"));
			portalBG = ImageIO.read(new File("src/view/img/portal bg.png"));
			portalBG2 = ImageIO.read(new File("src/view/img/portal bg2.png"));
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

		drawBox(g2,floor);
		drawBox(g2,roof);
		
		
		AffineTransform tr = new AffineTransform();
		tr.translate(400, 249);
		tr.scale(.5, .8);
		
		if(world.getSaveSpot()==4)
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
		if(world.getX() > 380 && world.getX() < 500)
			world.setSaveSpot(4);
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
			if(deadCounter%13 == 0)
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
			if(deadCounter%35 == 34)
				imgX++;
			if(imgX == 2)
				world.setDead(false);
			if(imgX == 4)
				gettingUp = false;
		}
		
		repaint();
		
		if(hero2D.getPosition().getX()<0)
			frame.loadMap(new L1M35(world,frame,new Vector2f(890,world.getY())));
		else if(hero2D.getPosition().getX()>900)
			frame.loadMap(new L1M37(world,frame, new Vector2f(10f,world.getY())));
		
	}
}
