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
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Polygon;
import view.GameFrame;
import view.MenuPanel;
import view.world.GameHero;
import view.world.GameLevel;
import view.world.GameSpike;
import view.world.GameWorld;

public class L1M4 extends GameLevel implements ActionListener
{
	private World world2D;
	private GameFrame frame;
	private Timer timer;
	private Body floor,floor2,floor3,floor4,floor5,roof,hero2D;
	private GameSpike spike;
	private BodyList spikes;
	private GameHero hero;
	private final String[] s = {"Fun fact :  the longer you stay in the suit,","the more likely it is your arms will degenerate."};
	
	public L1M4(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world);
		this.frame = frame;
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
		roof = new StaticBody("", new Box(1200f,40f));
		roof.setPosition(550f, 250);
		
		floor = new StaticBody("", new Box(1200f,40f));
		floor.setPosition(550f, 530);
		floor2 = new StaticBody("", new Box(300f,40f));
		floor2.setPosition(770f, 450);
		floor5 = new StaticBody("", new Box(100f,40f));
		floor5.setPosition(4000f, 450);
		floor3 = new StaticBody("", new Box(500f,40f));
		floor3.setPosition(550f, 490);
		floor4 = new StaticBody("", new Box(113.137f,40f));
		floor4.setPosition(200f, 490);
		floor4.adjustRotation((float) (-Math.PI/4));
		
		spikes = new BodyList();
		spike = new GameSpike(new Vector2f(400,300));
		Body spikey = spike.getBody();
		spikes.add(spikey);
		
		world2D.add(spikey);
		
		
		world2D.add(floor2);
		world2D.add(floor3);
		world2D.add(floor4);
		world2D.add(floor);
		world2D.add(roof);
		
		hero = world.getHero();
		hero2D = hero.getHeroBody();
		hero2D.setPosition(position.x, position.y);
		world2D.add(hero2D);		
		
		BodyList listTest = world2D.getBodies();
		for ( int z = 0; z < listTest.size(); z++)
		{
			System.out.println("to string : "+listTest.get(z));
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
		drawBox(g2, floor4);
		drawBox(g2, roof);
		spike.drawSpike(g2);

		hero.drawHero(g2);

		if(world.getGameHints() == 4)
		{
			g2.setStroke(new BasicStroke(1));
			g2.setColor(new Color(0,0,0,180));
			g2.fill(new Rectangle2D.Double(0,0,920,85));
			g2.setColor(new Color(200,200,200,255));
			Font font = new Font("Monospaced", Font.BOLD, 30);
			g2.setFont(font);
			FontRenderContext frc = g2.getFontRenderContext();
			GlyphVector gv = font.createGlyphVector(frc, s[0]);
			GlyphVector	gv2 = font.createGlyphVector(frc, s[1]);
			Shape glyph = gv.getOutline(30,35);
			Shape glyph2 = gv2.getOutline(30,65);
			g2.setColor(new Color(220,220,220,255));
			g2.fill(glyph);
			g2.fill(glyph2);
			g2.setColor(new Color(0,0,0,255));
			g2.draw(glyph);
			g2.draw(glyph2);
		}
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
		{
			if(world.getGameHints() == 4)
				world.setGameHints(5);
			frame.loadMap(new L1M3(world,frame,new Vector2f(870f,world.getY())));
		}
//		else if(hero2D.getPosition().getX()>900)
//		{
//			if(world.getGameHints() == 4)
//				world.setGameHints(5);
//			frame.loadMap(new L1M3(world,frame, new Vector2f(10f,world.getY())));
//		}
	}
	
}
