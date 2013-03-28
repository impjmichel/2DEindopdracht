package view.world.level1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
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
	private final String[] s = {">>You hear the faint sound of a door opening<<","Or that's what I would like to say,",
								"but all our doors open and close behind you", "without any sound.",""};
	private boolean tutorial = false;
	private boolean tutorialEnd = false;
	private int tutorialMoved = 0;
	private int tutorialX = 920;
	
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
		if(tutorial)
		{
			g2.setStroke(new BasicStroke(1));
			g2.setColor(new Color(0,0,0,180));
			g2.fill(new Rectangle2D.Double(tutorialX,200,920,200));
			g2.setColor(new Color(200,200,200,255));
			Font font = new Font("Monospaced", Font.BOLD, 30);
			g2.setFont(font);
			FontRenderContext frc = g2.getFontRenderContext();
			GlyphVector gv = font.createGlyphVector(frc, "");
			GlyphVector gv2 = font.createGlyphVector(frc, "");
			if(tutorialMoved == 1)
				gv = font.createGlyphVector(frc, s[0]);
			else if(tutorialMoved == 2)
				gv = font.createGlyphVector(frc, s[1]);
			else if(tutorialMoved == 3)
			{
				gv = font.createGlyphVector(frc, s[2]);
				gv2 = font.createGlyphVector(frc, s[3]);
			}
			else if(tutorialMoved == 4)
				gv = font.createGlyphVector(frc, s[4]);
			Shape glyph = gv.getOutline(35,310);
			Shape glyph2 = gv2.getOutline(35,345);
			g2.setColor(new Color(220,220,220,255));
			g2.fill(glyph);
			g2.fill(glyph2);
			g2.setColor(new Color(0,0,0,255));
			g2.draw(glyph);
			g2.draw(glyph2);
		}
	}

	@Override
	public void enter()
	{
		if(tutorialEnd && hero.isPaused())
		{
			if(tutorialX > -10)
				tutorialMoved ++;
		}
		int x = (int) hero2D.getPosition().getX();
		int y = (int) hero2D.getPosition().getY();
		if(x > 650 && x < 750)
		{
			if(y > 450 && y < 580)
			{
				world.setClosedL1M20(false);
				if(world.getGameHints() == 8)
					world.setGameHints(9);
			}
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
		if(world.getGameHints() == 9)
		{
			tutorial = true;
			hero.setPaused(true);
			if(tutorialX  > 0)
			{
				tutorialX -= 25;
				tutorialEnd = true;
			}
			if(tutorialMoved > 0)
				tutorialEnd = true;
			if(tutorialMoved == 5)
			{
				tutorialX-= 25;
				if(tutorialX < -930)
				{
					world.setGameHints(10);
					tutorial = false;
					hero.setPaused(false);
				}
			}
		}
		pcCounter = (pcCounter+1)%5;
		if(pcCounter == 4 && world.isClosedL1M20())
			pcX = (pcX+1)%4;
		if(!tutorial)
		{
			for(int i=0; i<5; i++) 
			{
				world2D.step();
				validate();
			}
		}
		repaint();
		if(hero2D.getPosition().getX()>900)
			frame.loadMap(new L1M10(world,frame, new Vector2f(10,world.getY())));
		if(hero2D.getPosition().getY()>600)
			frame.loadMap(new L1M12(world,frame, new Vector2f(world.getX(),10)));
	}
}