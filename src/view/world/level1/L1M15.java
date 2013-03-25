package view.world.level1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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

public class L1M15 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private World world2D;
	private Timer timer;
	private Body floor,floor2,floor3,wall,wall2,roof,hero2D;
	private GameSpike spike;
	private ArrayList<GameSpike> spikes;
	private GameHero hero;
	private Image pcImage;
	private int pcX;
	private int pcCounter;

	public L1M15(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		pcX = 0;
		pcCounter = 0;
		
		roof = new StaticBody("", new Box(1200f,40f));
		roof.setPosition(550f, 240);
		
		floor = new StaticBody("", new Box(440f,40f));
		floor.setPosition(710f, 460);
		floor2 = new StaticBody("", new Box(160f,40f));
		floor2.setPosition(450f, 540);
		floor3 = new StaticBody("", new Box(420f,40f));
		floor3.setPosition(200, 460);
		
		wall = new StaticBody("", new Box(40f,119f));
		wall.setPosition(510f, 500);
		wall2 = new StaticBody("", new Box(40f,119f));
		wall2.setPosition(390f, 500);
		
	//-----------------------------------------
		// Progress Killer
		spikes = new ArrayList<GameSpike>();
		for(int i = 0; i < 9; i++)
		{
			spike = new GameSpike(new Vector2f(510,430-20*i), world);
			Body spikey = spike.getBody();
			spikey.adjustRotation((float) Math.PI/2*3);
			spikes.add(spike);
			world2D.add(spikey);
		}
		// Progress Killer		
	//-----------------------------------------
		
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
		drawBox(g2, floor);
		drawBox(g2, floor2);
		drawBox(g2, floor3);
		drawBox(g2, roof);
		drawBox(g2, wall);
		drawBox(g2, wall2);
		
		hero.drawHero(g2);
		//-------PROGRESS-KILLER----------------------------
		if(spikes.size() > 0)
		{
			for(GameSpike spikey : spikes)
				spikey.drawSpike(g2);
		}
		Font font = new Font("Monospaced", Font.BOLD, 30);
		g2.setFont(font);
		String[] s = {"work in progress work in progress work","s work in progress work in progress wor",
				"ss work in progress work in progress wo","ess work in progress work in progress w",
				"ress work in progress work in progress ","gress work in progress work in progress",
				"ogress work in progress work in progres","rogress work in progress work in progre",
				"progress work in progress work in progr"," progress work in progress work in prog",
				"n progress work in progress work in pro","in progress work in progress work in pr",
				" in progress work in progress work in p","k in progress work in progress work in ",
				"rk in progress work in progress work in","ork in progress work in progress work i",
				"work in progress work in progress work "," work in progress work in progress work",
				"s work in progress work in progress wor","ss work in progress work in progress wo",};
		double rotate2 = -Math.PI/4;
		g2.setFont(g2.getFont().deriveFont(AffineTransform.getRotateInstance(rotate2)));
		for(int i = 0; i < 20; i++)
			g2.drawString(s[i], 0, 0+60*i);
		//-------PROGRESS-KILLER----------------------------
		
		if(pcImage != null)
		{
			BufferedImage subImg = ((BufferedImage) pcImage).getSubimage(pcX*93, 0, 93, 94);
			g2.drawImage(subImg, 680, 360, 80, 80, null);
		}
	}

	@Override
	public void enter()
	{
		int x = (int) hero2D.getPosition().getX();
		int y = (int) hero2D.getPosition().getY();
		if(x > 650 && x < 750)
		{
			if(y > 340 && y < 470)
				world.setClosedL1M29(false);
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
		if(pcCounter == 4 && world.isClosedL1M29())
			pcX = (pcX+1)%4;
		for(int i=0; i<5; i++) 
		{
			world2D.step();
			validate();
		}
		repaint();
//		if(hero2D.getPosition().getX()<0)
//			frame.loadMap(new L1M3(world,frame,new Vector2f(870f,world.getY())));
		if(hero2D.getPosition().getX()>900)
			frame.loadMap(new L1M14(world,frame, new Vector2f(10,world.getY())));
	}
}

