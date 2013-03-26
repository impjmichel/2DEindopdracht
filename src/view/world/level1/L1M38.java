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
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

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
import view.world.GameWorld;

public class L1M38 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private World world2D;
	private Timer timer;
	private Body floor,wall1,wall2,roof1,roof2,hero2D,cakeBox;
	private GameHero hero;
	private boolean levelEnd;
	private boolean endingEnded = false;
	private int endingMoved = 0;
	private int endingX = 920;
	private String[] s;
	private Image cake;
	
	
	public L1M38(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
		wall1 = new StaticBody("wall", new Box(40f,340f));
		wall1.setPosition(690f, 250);
		
		wall2 = new StaticBody("", new Box(40f,139f));
		wall2.setPosition(290f, 150);
		
		roof1 = new StaticBody("", new Box(440f,40f));
		roof1.setPosition(490f, 100);
		
		roof2 = new StaticBody("", new Box(320f,40f));
		roof2.setPosition(150f, 200);
		
		floor = new StaticBody("", new Box(720f,40f));
		floor.setPosition(350f, 400);
		
		cakeBox = new Body("cake", new Box(80,59), 10);
		cakeBox.setPosition(500, 250);
		cakeBox.adjustRotation((float)Math.PI/6);
		world2D.add(cakeBox);
		
		world2D.add(floor);
		world2D.add(roof1);
		world2D.add(roof2);
		world2D.add(wall1);
		world2D.add(wall2);
		
		hero = world.getHero();
		hero2D = hero.getHeroBody();
		hero2D.setPosition(position.x, position.y);
		world2D.add(hero2D);
		try
		{
			cake = ImageIO.read(L1M38.class.getResource("/cake.png"));
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
		drawBox(g2, wall1);
		drawBox(g2, wall2);
		drawBox(g2, floor);
		drawBox(g2, roof1);
		drawBox(g2, roof2);
		
		Box box = (Box) cakeBox.getShape();
		Vector2f[] pts = box.getPoints(cakeBox.getPosition(), cakeBox.getRotation());

		Vector2f p1 = pts[0];

		if(cake != null)
		{
			AffineTransform tr = new AffineTransform();
			tr.translate(p1.x, p1.y);
			tr.rotate(cakeBox.getRotation(), 0, 0);
			g2.drawImage(cake, tr, null);
		}
		
		hero.drawHero(g2);
		
		if(levelEnd)
		{
			g2.setStroke(new BasicStroke(1));
			g2.setColor(new Color(0,0,0,180));
			g2.fill(new Rectangle2D.Double(endingX,200,920,200));
			g2.setColor(new Color(200,200,200,255));
			Font font = new Font("Monospaced", Font.BOLD, 30);
			g2.setFont(font);
			FontRenderContext frc = g2.getFontRenderContext();
			GlyphVector gv = font.createGlyphVector(frc, "");
			if(endingMoved == 1)
				gv = font.createGlyphVector(frc, s[0]);
			else if(endingMoved == 2)
				gv = font.createGlyphVector(frc, s[1]);
			else if(endingMoved == 3)
				gv = font.createGlyphVector(frc, s[2]);
			else if(endingMoved == 4)
				gv = font.createGlyphVector(frc, s[3]);
			else if(endingMoved == 5)
				gv = font.createGlyphVector(frc, s[4]);
			else if(endingMoved == 6)
				gv = font.createGlyphVector(frc, s[5]);
			Shape glyph = gv.getOutline(35,310);
			g2.setColor(new Color(220,220,220,255));
			g2.fill(glyph);
			g2.setColor(new Color(0,0,0,255));
			g2.draw(glyph);
		} 
	}

	@Override
	public void enter()
	{
		if(endingEnded && hero.isPaused())
		{
			if(endingX > -10)
				endingMoved ++;
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
		if(!levelEnd)
		{
			for(int i=0; i<5; i++) 
			{
				world2D.step();
				validate();
			}
		}
		repaint();
		if(hero2D.getPosition().getX() < 0)
			frame.loadMap(new L1M37(world,frame, new Vector2f(890f,world.getY())));
		else if(hero2D.getPosition().getX() > 300)
		{
			if(s == null)
			{
				String s2[] = {"   Congratulations","You have completed the first test.",
						"It took you only "+world.slashPlayed()+".",
						"And you only hit about "+world.getDeathCount()+" objects.",
						"To save you the trouble, I will dispose you now.","Goodbye."};
				s = s2;
			}
			levelEnd = true;
			hero.setPaused(true);
			if(endingX  > 0)
			{
				endingX -= 25;
				endingEnded = true;
			}
			if(endingMoved > 0)
				endingEnded = true;
			if(endingMoved == 6)
			{
				endingX-= 25;
				if(endingX < -930)
					System.exit(0);
			}
		}
	}
}
