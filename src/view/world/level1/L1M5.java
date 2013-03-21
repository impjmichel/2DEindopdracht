package view.world.level1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;

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

public class L1M5 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private World world2D;
	private Timer timer;
	private Body floor,floor2,floor3,floor4,wall,wall2,wall3,wall4,wall5,wall6,wall7,roof,roof2,roof3,roof4,hero2D;
	private GameHero hero;
	private final String[] s = {"Here's another fun fact : excessive use of the","suit might cause disorientation and headaches."};
	private int hintY;
	
	public L1M5(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		hintY = 0;
		
		roof = new StaticBody("", new Box(300f,40f));
		roof.setPosition(140f, 250);
		roof2 = new StaticBody("", new Box(410f,40f));
		roof2.setPosition(455f, 0);
		roof3 = new StaticBody("", new Box(300f,40f));
		roof3.setPosition(770f, 40);
		roof4 = new StaticBody("", new Box(160f,40f));
		roof4.setPosition(700f, 360);
		
		floor = new StaticBody("", new Box(500f,40f));
		floor.setPosition(180f, 450);
		floor2 = new StaticBody("", new Box(320f,40f));
		floor2.setPosition(780f, 160);
		floor3 = new StaticBody("", new Box(120f,40f));
		floor3.setPosition(450f, 120);
		floor4 = new StaticBody("", new Box(190f,40f));
		floor4.setPosition(565f, 490);
		
		wall = new StaticBody("", new Box(40f,299f));
		wall.setPosition(270f, 120);
		wall2 = new StaticBody("", new Box(40f,79f));
		wall2.setPosition(640f, 20);
		wall3 = new StaticBody("", new Box(40f,369f));
		wall3.setPosition(410f, 285);
		wall4 = new StaticBody("", new Box(40f,239f));
		wall4.setPosition(640f, 260);
		wall5 = new StaticBody("", new Box(40f,409f));
		wall5.setPosition(490f, 305);
		wall6 = new StaticBody("", new Box(40f,239f));
		wall6.setPosition(640f, 590);
		wall7 = new StaticBody("", new Box(40f,319f));
		wall7.setPosition(760f, 500);
		
		world2D.add(roof);
		world2D.add(roof2);
		world2D.add(roof3);
		world2D.add(roof4);
		world2D.add(floor2);
		world2D.add(floor3);
		world2D.add(floor4);
		world2D.add(floor);
		world2D.add(wall);
		world2D.add(wall2);
		world2D.add(wall3);
		world2D.add(wall4);
		world2D.add(wall5);
		world2D.add(wall6);
		world2D.add(wall7);
		
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
		drawBox(g2, floor);
		drawBox(g2, floor2);
		drawBox(g2, floor3);
		drawBox(g2, floor4);
		drawBox(g2, roof);
		drawBox(g2, roof2);
		drawBox(g2, roof3);
		drawBox(g2, roof4);
		drawBox(g2, wall);
		drawBox(g2, wall2);
		drawBox(g2, wall3);
		drawBox(g2, wall4);
		drawBox(g2, wall5);
		drawBox(g2, wall6);
		drawBox(g2, wall7);
		hero.drawHero(g2);

		if(world.getGameHints() == 5)
		{
			g2.setStroke(new BasicStroke(1));
			g2.setColor(new Color(0,0,0,180));
			g2.fill(new Rectangle2D.Double(0,hintY,920,85));
			g2.setColor(new Color(200,200,200,255));
			Font font = new Font("Monospaced", Font.BOLD, 30);
			g2.setFont(font);
			FontRenderContext frc = g2.getFontRenderContext();
			GlyphVector gv = font.createGlyphVector(frc, s[0]);
			GlyphVector	gv2 = font.createGlyphVector(frc, s[1]);
			Shape glyph = gv.getOutline(30,hintY+35);
			Shape glyph2 = gv2.getOutline(30,hintY+65);
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
		if(hero2D.getPosition().getY() > 280)
			hintY = 0;
		else
			hintY = 525;
		for(int i=0; i<5; i++) 
		{
			world2D.step();
			validate();
		}
		repaint();
		if(hero2D.getPosition().getX()<0)
		{
			if(world.getGameHints() == 5)
				world.setGameHints(6);
			frame.loadMap(new L1M4(world,frame,new Vector2f(870f,world.getY())));
		}
		else if(hero2D.getPosition().getX()>900)
		{
			if(world.getGameHints() == 5)
				world.setGameHints(6);
			frame.loadMap(new L1M6(world,frame, new Vector2f(10f,world.getY())));
		}
		else if(hero2D.getPosition().getY()>600)
		{
			if(world.getGameHints() == 5)
				world.setGameHints(6);
			frame.loadMap(new L1M21(world,frame, new Vector2f(world.getX(),10)));
		}
	}
}
