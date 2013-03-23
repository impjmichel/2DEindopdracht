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

import javax.imageio.ImageIO;
import javax.swing.Timer;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import view.AdvancedControlPanel;
import view.GameFrame;
import view.world.GameHero;
import view.world.GameLevel;
import view.world.GameWorld;

public class L1M3 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private World world2D;
	private Timer timer;
	private Body floor,floor2,floor3,
				 wall,wall2,wall3,wall4,wall5,wall6,wall7,wall8,
				 roof,roof2,roof3,roof4,roof5,roof6,roof7,
				 door,hero2D;
	private GameHero hero;
	private int doorY;
	private boolean tutorial = false;
	private boolean tutorialEnd = false;
	private int tutorialMoved = 0;
	private int tutorialX = 920;
	private Image gSuit, gChange, gCatch;
	private int gsX, gsY, gcX, gcY, catchX, catchY, frameCounter;
	private boolean flying, changing;
	private final String[] s = {"Congratulations","Congratulations...","You have obtained a gravity suit.",
			"","",
			"...","Please move on so we can start the testing.","At the end of the course there will be cake.",
			"...","I just heard one of the testsubjects escaped...","with the cake.",
			"I will see to it that you will be equivalently","rewarded in pies."};
	private String str,str2;

	
	public L1M3(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
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
		
		try
		{
			gSuit = ImageIO.read(L1M3.class.getResource("/gravity suit200x200.png"));
			gChange = ImageIO.read(L1M3.class.getResource("/change sprite300x200.png"));
			gCatch = ImageIO.read(L1M3.class.getResource("/catching200x200.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		AdvancedControlPanel p = new AdvancedControlPanel(world, frame);
		str = p.getKeyString(frame.getSwitchKey());
		str = "Press "+str+" to use its functionality.";
		str2 = "Or UP, or DOWN, those three do fine.";
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(new Color(40,40,40));
		
		drawBox(g2, wall);
		drawBox(g2, wall2);
		drawBox(g2, wall3);
		drawBox(g2, wall4);
		drawBox(g2, wall5);
		drawBox(g2, wall6);
		drawBox(g2, wall7);
		drawBox(g2, wall8);
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
		fillBox(g2, door);
		
		if(!flying && !changing && !world.isGravitySuit())
		{
			BufferedImage subImg = ((BufferedImage) gSuit).getSubimage(gsX, gsY, 200, 200);
			g2.drawImage(subImg, 350, 310, 200, 200, null);
		}
		hero.drawHero(g2);
		if(flying && !changing && !world.isGravitySuit())
		{
			BufferedImage subImg = ((BufferedImage) gChange).getSubimage(gcX, gcY, 300, 200);
			g2.drawImage(subImg, (int)hero2D.getPosition().getX()-150, 310, 300, 200, null);
		}
		if(changing && !world.isGravitySuit())
		{
			BufferedImage subImg = ((BufferedImage) gCatch).getSubimage(catchX, catchY, 200, 200);
			g2.drawImage(subImg, (int)hero2D.getPosition().getX()-100, 320, 200, 200, null);
		}
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
				gv = font.createGlyphVector(frc, s[2]);
			else if(tutorialMoved == 4)
			{
				gv = font.createGlyphVector(frc, str);
				gv2 = font.createGlyphVector(frc, str2);
			}
			else if(tutorialMoved == 5)
				gv = font.createGlyphVector(frc, s[5]);
			else if(tutorialMoved == 6)
				gv = font.createGlyphVector(frc, s[6]);
			else if(tutorialMoved == 7)
				gv = font.createGlyphVector(frc, s[7]);
			else if(tutorialMoved == 8)
				gv = font.createGlyphVector(frc, s[8]);
			else if(tutorialMoved == 9)
				gv = font.createGlyphVector(frc, s[9]);
			else if(tutorialMoved == 10)
			{
				gv = font.createGlyphVector(frc, s[9]);
				gv2 = font.createGlyphVector(frc, s[10]);
			}
			else if(tutorialMoved == 11)
			{
				gv = font.createGlyphVector(frc, s[11]);
				gv2 = font.createGlyphVector(frc, s[12]);
				world.setGravitySuit(true);
				world2D.remove(hero2D);
				hero.switchBody();
				hero2D = hero.getHeroBody();
				world2D.add(hero2D);
			}
			Shape glyph = gv.getOutline(35,310);
			Shape glyph2 = gv2.getOutline(35,340);
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
		if(x > 400 && x < 500)
		{
			if(y > 410 && y < 510)
			{
				if(world.getGameHints() == 2)
				{
					world.setGameHints(3);
				}
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
		if(!world.isClosedL1M3())
		{
			if(doorY > 169)
			{
				doorY--;
				door.setPosition(800, doorY);
			}
		}
		if(world.getGameHints() == 3)
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
			if(tutorialMoved == 12)
			{
				tutorialX-= 25;
				if(tutorialX < -930)
				{
					world.setGameHints(4);
					tutorial = false;
					world.setClosedL1M3(false);
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
		if(tutorial && !world.isGravitySuit())
			frameCounter++;
		
		if(gsX == 800 && gsY == 200)
		{
			flying = true;
			frameCounter = 0;
			gsX = 0;
		}
		
		if(catchX == 800 && catchY == 200)
			catchX = 0;
		
		if(frameCounter%15 == 14)
		{
			if(!flying && !changing)
			{
				frameCounter += 2;
				gsX = (gsX+200)%1000;
				if(gsX == 0)
					gsY = 200;
			}
			else if(flying && !changing)
			{
				frameCounter += 9;
				gcY = (gcY+200)%1000;
				if(gcY == 0)
					gcX = (gcX+300)%900;
			}
			else if(changing)
			{
				catchX = (catchX+200)%1000;
				if(catchX == 0)
					catchY = 200;
			}
		}
		
		if(tutorialMoved == 8)
		{
			changing = true;
			frameCounter = 0;
		}
		repaint();
		if(hero2D.getPosition().getX()<0)
			frame.loadMap(new L1M2(world,frame,new Vector2f(870f,world.getY())));
		else if(hero2D.getPosition().getX()>900)
			frame.loadMap(new L1M4(world,frame,new Vector2f(10f,world.getY())));
	}
}
