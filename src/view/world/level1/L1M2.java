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

public class L1M2 extends GameLevel implements ActionListener
{
	private World world2D;
	private Timer timer;
	private Body floor,roof,door,hero2D;
	private GameHero hero;
	private int doorY;
	private boolean tutorial = false;
	private boolean tutorialEnd = false;
	private boolean tutorialMoved = false;
	private int tutorialX = 920;
	private final String[] s = {"Interact with objects by pressing Space","Interact with objects by pressing Enter"};
	private Image pcImage;
	private int pcX;
	private int pcCounter;
	
	public L1M2(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world, frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		pcX = 0;
		pcCounter = 0;
		
		doorY = 330;
		door = new StaticBody("", new Box(30,200));
		door.setPosition(800, doorY);
		
		roof = new StaticBody("", new Box(1200f,40f));
		roof.setPosition(550f, 250);
		
		floor = new StaticBody("", new Box(1200f,40f));
		floor.setPosition(550f, 450);
		
		world2D.add(floor);
		world2D.add(roof);
		world2D.add(door);
		
		hero = world.getHero();
		hero2D = hero.getHeroBody();
		hero2D.setPosition(position.x, position.y);
		world2D.add(hero2D);
		
		try
		{
			pcImage = ImageIO.read(new File("src/view/img/pc93.png"));
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
		drawBox(g2, roof);
		fillBox(g2, door);
		
		if(pcImage != null)
		{
			BufferedImage subImg = ((BufferedImage) pcImage).getSubimage(pcX*93, 0, 93, 94);
			g2.drawImage(subImg, 553, 336, 93, 94, null);
		}
		hero.drawHero(g2);
		
		if(tutorial)
		{
			g2.setStroke(new BasicStroke(1));
			g2.setColor(new Color(0,0,0,180));
			g2.fill(new Rectangle2D.Double(tutorialX,200,920,200));
			g2.setColor(new Color(200,200,200,255));
			Font font = new Font("Serif", Font.BOLD, 50);
			g2.setFont(font);
			FontRenderContext frc = g2.getFontRenderContext();
			GlyphVector gv = null;
			if(frame.isDefaultKeys() == 1)
				gv = font.createGlyphVector(frc, s[0]);
			else
				gv = font.createGlyphVector(frc, s[1]);
			Shape glyph = gv.getOutline(tutorialX+30,310);
			g2.setColor(new Color(220,220,220,255));
			
			g2.fill(glyph);
			g2.setColor(new Color(0,0,0,255));
			g2.draw(glyph);
		}
	}

	@Override
	public void enter()
	{
		if(tutorialEnd)
		{
			tutorialMoved = true;
		}
		int x = (int) hero2D.getPosition().getX();
		int y = (int) hero2D.getPosition().getY();
		if(x > 550 && x < 650)
		{
			if(y > 330 && y < 440)
			{
				if(world.isClosedL1M2())
					world.setClosedL1M2(false);
				else
					world.setClosedL1M2(true);
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
		pcCounter = (pcCounter+1)%5;
		if(pcCounter == 4 && world.isClosedL1M2())
			pcX = (pcX+1)%4;
		
		if(!world.isClosedL1M2())
		{
			if(doorY > 169)
			{
				doorY--;
				door.setPosition(800, doorY);
			}
		}
		else
		{
			if(doorY < 330)
			{
				doorY++;
				door.setPosition(800, doorY);
			}
		}
		if(world.getGameHints() == 1)
		{
			tutorial = true;
			hero.setPaused(true);
			if(tutorialX > 0)
			{
				tutorialX -= 15;
				tutorialEnd = true;
			}
			if(tutorialMoved)
			{
				tutorialX-= 15;
				if(tutorialX < -930)
				{
					world.setGameHints(2);
					tutorial = false;
					hero.setPaused(false);
				}
			}
		}
		if(!tutorial)
		{
			for(int i=0; i<5; i++) 
			{
				world2D.step();
				validate();
			}
		}
		repaint();
		if(hero2D.getPosition().getX()<0)
			frame.loadMap(new L1M1(world,frame,new Vector2f(870f,world.getY())));
		else if(hero2D.getPosition().getX()>900)
			frame.loadMap(new L1M3(world,frame, new Vector2f(10f,world.getY())));
	}
	
}
