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
import view.world.GameHero;
import view.world.GameLevel;
import view.world.GameMovingEnemy;
import view.world.GameWorld;

public class L1M6 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private Timer timer;
	private World world2D;
	private Body hero2D, roof,roof2,roof3,roof4,roof5,roof6,floor,floor2,floor3,floor4,
				 wall,wall2,wall3,wall4,wall5,wall6,wall7,wall8,wall9,wall10;
	private GameMovingEnemy enemy1,enemy2,enemy3;
	private GameHero hero;
	private int deadCounter,imgX,imgY;
	private boolean gettingUp;
	private Image portal,portalBG,portalBG2;
	private final String[] s = {"","You just activated a so called...","\"Organic Matrix Reproduction Unit\".","Most likely you wouldn't understand it.",""};
	private boolean tutorial = false;
	private boolean tutorialEnd = false;
	private int tutorialMoved = 0;
	private int tutorialX = 920;
	
	public L1M6(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world, frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		deadCounter = 1;
		imgX = 0;
		imgY = 0;
		gettingUp = false;
		
		roof = new StaticBody("", new Box(240f,40f));
		roof.setPosition(100f, 40);
		roof2 = new StaticBody("", new Box(80f,40f));
		roof2.setPosition(220f, 260);
		roof3 = new StaticBody("", new Box(180f,40f));
		roof3.setPosition(310f, 220);
		roof4 = new StaticBody("", new Box(180f,40f));
		roof4.setPosition(450f, 260);
		roof5 = new StaticBody("", new Box(180f,40f));
		roof5.setPosition(590f, 220);
		roof6 = new StaticBody("", new Box(80f,40f));
		roof6.setPosition(680f, 260);
		 
		floor = new StaticBody("", new Box(160f,40f));
		floor.setPosition(0f, 160);
		floor2 = new StaticBody("", new Box(360f,40f));
		floor2.setPosition(220f, 460);
		floor3 = new StaticBody("", new Box(180f,40f));
		floor3.setPosition(450f, 500);
		floor4 = new StaticBody("", new Box(360f,40f));
		floor4.setPosition(680f, 460);
		
		wall = new StaticBody("", new Box(40f,259f));
		wall.setPosition(200f, 150);
		wall2 = new StaticBody("", new Box(40f,79f));
		wall2.setPosition(240f, 240);
		wall3 = new StaticBody("", new Box(40f,79f));
		wall3.setPosition(380f, 240);
		wall4 = new StaticBody("", new Box(40f,79f));
		wall4.setPosition(520f, 240);
		wall5 = new StaticBody("", new Box(40f,79f));
		wall5.setPosition(660f, 240);
		wall6 = new StaticBody("", new Box(40f,299f));
		wall6.setPosition(700f, 130);
		wall7 = new StaticBody("", new Box(40f,339f));
		wall7.setPosition(60f, 310);
		wall8 = new StaticBody("", new Box(40f,79f));
		wall8.setPosition(380f, 480);
		wall9 = new StaticBody("", new Box(40f,79f));
		wall9.setPosition(520f, 480);
		wall10 = new StaticBody("", new Box(40f,539f));
		wall10.setPosition(840f, 210);
		
		enemy1 = new GameMovingEnemy(new Vector2f(310,275), new Vector2f(310,405),40,70, world);
		enemy2 = new GameMovingEnemy(new Vector2f(450,315), new Vector2f(450,445),40,70, world);
		enemy3 = new GameMovingEnemy(new Vector2f(590,275), new Vector2f(590,405),40,70, world);
		world2D.add(enemy1.getBody());
		world2D.add(enemy2.getBody());
		world2D.add(enemy3.getBody());
		
		world2D.add(roof);
		world2D.add(roof2);
		world2D.add(roof3);
		world2D.add(roof4);
		world2D.add(roof5);
		world2D.add(roof6);
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
		world2D.add(wall8);
		world2D.add(wall9);
		world2D.add(wall10);
		
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
		drawBox(g2, floor);
		drawBox(g2, floor2);
		drawBox(g2, floor3);
		drawBox(g2, floor4);
		drawBox(g2, roof);
		drawBox(g2, roof2);
		drawBox(g2, roof3);
		drawBox(g2, roof4);
		drawBox(g2, roof5);
		drawBox(g2, roof6);
		drawBox(g2, wall);
		drawBox(g2, wall2);
		drawBox(g2, wall3);
		drawBox(g2, wall4);
		drawBox(g2, wall5);
		drawBox(g2, wall6);
		drawBox(g2, wall7);
		drawBox(g2, wall8);
		drawBox(g2, wall9);
		drawBox(g2, wall10);
		enemy1.drawEnemy(g2);
		enemy2.drawEnemy(g2);
		enemy3.drawEnemy(g2);
		
		AffineTransform tr = new AffineTransform();
		tr.translate(690, 309);
		tr.scale(.8, .8);
		
		if(world.getSaveSpot()==1)
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
			if(tutorialMoved == 1)
				gv = font.createGlyphVector(frc, s[0]);
			else if(tutorialMoved == 2)
				gv = font.createGlyphVector(frc, s[1]);
			else if(tutorialMoved == 3)
				gv = font.createGlyphVector(frc, s[2]);
			else if(tutorialMoved == 4)
				gv = font.createGlyphVector(frc, s[3]);
			else if(tutorialMoved == 5)
				gv = font.createGlyphVector(frc, s[4]);

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
		if(tutorialEnd && hero.isPaused())
		{
			if(tutorialX > -10)
				tutorialMoved ++;
		}
		if(world.getX() > 670 && world.getX() < 840)
		{
			if(world.getY() > 310)
			{
				world.setSaveSpot(1);
				if(world.getGameHints() == 6)
				{
					world.setGameHints(7);
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
		if(world.getGameHints() == 7)
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
					world.setGameHints(8);
					tutorial = false;
					hero.setPaused(false);
				}
			}
		}
		
		if(world.isDead() || gettingUp)
			deadCounter++;
		if(!world.isDead() && !tutorial)
		{
			for(int i=0; i<5; i++) 
			{
				world2D.step();
				validate();
			}
		}
		else
		{
			if(deadCounter%8 == 0)
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
			if(deadCounter%25 == 24)
				imgX++;
			if(imgX == 2)
				world.setDead(false);
			if(imgX == 4)
				gettingUp = false;
		}
		enemy1.update();
		enemy2.update();
		enemy3.update();
		repaint();
		if(hero2D.getPosition().getX() < 0)
		{
			if(world.getGameHints() == 5)
				world.setGameHints(6);
			frame.loadMap(new L1M5(world,frame,new Vector2f(870f,world.getY())));
		}
		else if(hero2D.getPosition().getY() < 0)
		{
			if(world.getGameHints() == 5)
				world.setGameHints(6);
			frame.loadMap(new L1M7(world,frame, new Vector2f(world.getX(),590)));
		}
	}
}
