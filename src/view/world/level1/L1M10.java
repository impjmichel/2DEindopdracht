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
import view.world.GameWorld;

public class L1M10 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private World world2D;
	private Timer timer;
	private Body hero2D,roof,roof2,roof3,roof4,roof5,roof6,roof7,roof8,
				 floor,floor2,floor3,floor4,floor5,floor6,floor7,floor8,
				 wall,wall2,wall3,wall4,wall5,wall6,wall7,wall8,wall9,wall10,wall11,wall12,wall13,wall14;
	private GameHero hero;
	
	public L1M10(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
		roof = new StaticBody("", new Box(120f,40f));
		roof.setPosition(720f, 290);
		roof2 = new StaticBody("", new Box(140f,40f));
		roof2.setPosition(480, 290);
		roof3 = new StaticBody("", new Box(270f,40f));
		roof3.setPosition(315f, 370);
		roof4 = new StaticBody("", new Box(470,20f));
		roof4.setPosition(465f, 140);
		roof5 = new StaticBody("", new Box(220,40f));
		roof5.setPosition(160f, 170);
		roof6 = new StaticBody("", new Box(180,40f));
		roof6.setPosition(0f, 450);
		roof7 = new StaticBody("", new Box(460,40f));
		roof7.setPosition(0f, 0);
		roof8 = new StaticBody("", new Box(680,40f));
		roof8.setPosition(570f, 20);
		
		floor = new StaticBody("", new Box(120f,40f));
		floor.setPosition(380f, 510);
		floor2 = new StaticBody("", new Box(400f,40f));
		floor2.setPosition(160f, 570);
		floor3 = new StaticBody("", new Box(200f,40f));
		floor3.setPosition(280f, 290);
		floor4 = new StaticBody("", new Box(80f,40f));
		floor4.setPosition(660f, 420);
		floor5 = new StaticBody("", new Box(250,40f));
		floor5.setPosition(785f, 540);
		floor6 = new StaticBody("", new Box(120f,40f));
		floor6.setPosition(720f, 210);
		floor7 = new StaticBody("", new Box(300f,40f));
		floor7.setPosition(0, 130);
		floor8 = new StaticBody("", new Box(210f,40f));
		floor8.setPosition(445, 250);
		
		wall = new StaticBody("", new Box(40f,260f));
		wall.setPosition(640f, 530);
		wall2 = new StaticBody("", new Box(40f,179));
		wall2.setPosition(680, 220);
		wall3 = new StaticBody("", new Box(40f,79f));
		wall3.setPosition(360f, 270);
		wall4 = new StaticBody("", new Box(40f,119f));
		wall4.setPosition(430, 330);
		wall5 = new StaticBody("", new Box(40f,79f));
		wall5.setPosition(530, 270);
		wall6 = new StaticBody("", new Box(40f,160f));
		wall6.setPosition(420f, 570);
		wall7 = new StaticBody("", new Box(40f,320f));
		wall7.setPosition(200f, 430);
		wall8 = new StaticBody("", new Box(40f,99f));
		wall8.setPosition(340f, 540);
		wall9 = new StaticBody("", new Box(40f,59f));
		wall9.setPosition(250f, 160);
		wall10 = new StaticBody("", new Box(40f,79f));
		wall10.setPosition(130f, 150);
		wall11 = new StaticBody("", new Box(40f,319f));
		wall11.setPosition(70f, 310);
		wall12 = new StaticBody("", new Box(40f,159f));
		wall12.setPosition(680, 480);
		wall13 = new StaticBody("", new Box(40f,520f));
		wall13.setPosition(890f, 300);
		wall14 = new StaticBody("", new Box(40f,119f));
		wall14.setPosition(760, 250);
		
		world2D.add(roof);
		world2D.add(roof2);
		world2D.add(roof3);
		world2D.add(roof4);
		world2D.add(roof5);
		world2D.add(roof6);
		world2D.add(roof7);
		world2D.add(roof8);
		world2D.add(floor);
		world2D.add(floor2);
		world2D.add(floor3);
		world2D.add(floor4);
		world2D.add(floor5);
		world2D.add(floor6);
		world2D.add(floor7);
		world2D.add(floor8);
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
		world2D.add(wall11);
		world2D.add(wall12);
		world2D.add(wall13);
		world2D.add(wall14);
		
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
		drawBox(g2, roof);
		drawBox(g2, roof2);
		drawBox(g2, roof3);
		drawBox(g2, roof4);
		drawBox(g2, roof5);
		drawBox(g2, roof6);
		drawBox(g2, roof7);
		drawBox(g2, roof8);
		drawBox(g2, floor);
		drawBox(g2, floor2);
		drawBox(g2, floor3);
		drawBox(g2, floor4);
		drawBox(g2, floor5);
		drawBox(g2, floor6);
		drawBox(g2, floor7);
		drawBox(g2, floor8);
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
		drawBox(g2, wall11);
		drawBox(g2, wall12);
		drawBox(g2, wall13);
		drawBox(g2, wall14);
		hero.drawHero(g2);
		
		for(int i = 0; i < 3; i++)
		{
			g2.drawLine(230, 50+i*20, 920, 50+i*20);
		}
		for(int i = 0; i < 9; i++)
		{
			g2.drawLine(0, 110+i*20, 920, 110+i*20);
		}
		for(int i = 0; i < 9; i++)
		{
			g2.drawLine(0, 290+i*20, 410, 290+i*20);
		}
		for(int i = 0; i < 12; i++)
		{
			g2.drawLine(700, 290+i*20, 920, 290+i*20);
		}
		g2.drawLine(180, 470, 410, 470);
		for(int i = 0; i < 6; i++)
		{
			g2.drawLine(180, 490+i*20, 400, 490+i*20);
		}
		g2.drawLine(230, 0, 230, 110);
		g2.drawLine(410, 390, 410, 490);
		g2.drawLine(700, 300, 700, 400);
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
		repaint();
		if(hero2D.getPosition().getX()<0)
			frame.loadMap(new L1M11(world,frame, new Vector2f(890,world.getY())));
		if(hero2D.getPosition().getY()>600)
			frame.loadMap(new L1M9(world,frame, new Vector2f(world.getX(),10)));
	}
}