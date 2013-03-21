package view.world.level1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private Timer timer;
	private World world2D;
	private Body hero2D, roof,roof2,roof3,roof4,roof5,roof6,floor,floor2,floor3,floor4,
				 wall,wall2,wall3,wall4,wall5,wall6,wall7,wall8,wall9,wall10;
	private GameMovingEnemy enemy1,enemy2,enemy3;
	private GameHero hero;
	
	public L1M6(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world, frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
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
		hero.drawHero(g2);
		enemy1.drawEnemy(g2);
		enemy2.drawEnemy(g2);
		enemy3.drawEnemy(g2);
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
		for(int i=0; i<5; i++) 
		{
			world2D.step();
			validate();
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
