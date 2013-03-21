package view.world.level1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

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

public class L1M7 extends GameLevel implements ActionListener
{
	private static final long	serialVersionUID	= 1L;
	private World world2D;
	private Timer timer;
	private Body hero2D,roof,roof2,roof3,roof4,roof5,roof6,floor,floor2,floor3,floor4,
				 wall,wall2,wall3,wall4,wall5,wall6,wall7,wall8,wall9;
	private GameSpike spike;
	private ArrayList<GameSpike> spikes;
	private GameHero hero;
	
	public L1M7(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world,frame);
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
		roof = new StaticBody("", new Box(80f,40f));
		roof.setPosition(820f, 300);
		roof2 = new StaticBody("", new Box(470f,40f));
		roof2.setPosition(585f, 50);
		roof3 = new StaticBody("", new Box(80f,40f));
		roof3.setPosition(510f, 230);
		roof4 = new StaticBody("", new Box(410,40f));
		roof4.setPosition(185f, 450);
		roof5 = new StaticBody("", new Box(300f,40f));
		roof5.setPosition(0f, 280);
		roof6 = new StaticBody("", new Box(600f,40f));
		roof6.setPosition(10f, 90);
		
		floor = new StaticBody("", new Box(80f,40f));
		floor.setPosition(680f, 490);
		floor2 = new StaticBody("", new Box(210f,40f));
		floor2.setPosition(575f, 190);
		floor3 = new StaticBody("", new Box(560f,40f));
		floor3.setPosition(270f, 570);
		floor4 = new StaticBody("", new Box(300,40f));
		floor4.setPosition(0, 240);
		
		wall = new StaticBody("", new Box(40f,420f));
		wall.setPosition(840f, 490);
		wall2 = new StaticBody("", new Box(40f,290f));
		wall2.setPosition(800f, 175);
		wall3 = new StaticBody("", new Box(40f,240f));
		wall3.setPosition(700f, 590);
		wall4 = new StaticBody("", new Box(40f,340f));
		wall4.setPosition(660, 340);
		wall5 = new StaticBody("", new Box(40f,380f));
		wall5.setPosition(530, 400);
		wall6 = new StaticBody("", new Box(40f,79f));
		wall6.setPosition(490f, 210);
		wall7 = new StaticBody("", new Box(40f,439f));
		wall7.setPosition(370f, 250);
		wall8 = new StaticBody("", new Box(40f,399f));
		wall8.setPosition(290f, 270);
		wall9 = new StaticBody("", new Box(40f,79f));
		wall9.setPosition(130, 260);
		
		spikes = new ArrayList<GameSpike>();
		for(int i = 0; i < 6; i++)
		{
			spike = new GameSpike(new Vector2f(398,340+20*i), world);
			Body spikey = spike.getBody();
			spikey.adjustRotation((float) Math.PI/2*3);
			spikes.add(spike);
			world2D.add(spikey);
		}
		spike = new GameSpike(new Vector2f(500,543), world);
		Body spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(480,543), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(690,463), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(710,463), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(770,78), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(750,78), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(810,328), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(790,328), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		
		world2D.add(roof);
		world2D.add(roof2);
		world2D.add(roof3);
		world2D.add(roof4);
		world2D.add(roof5);
		world2D.add(roof6);
		world2D.add(floor);
		world2D.add(floor2);
		world2D.add(floor3);
		world2D.add(floor4);
		world2D.add(wall);
		world2D.add(wall2);
		world2D.add(wall3);
		world2D.add(wall4);
		world2D.add(wall5);
		world2D.add(wall6);
		world2D.add(wall7);
		world2D.add(wall8);
		world2D.add(wall9);
		
		hero = world.getHero();
		hero2D = hero.getHeroBody();
		hero2D.setPosition(position.x, position.y);
		world2D.add(hero2D);		
		
		//-------PROGRESS-KILLER----------------------------
		for(int i = 0; i < 5; i++)
		{
			spike = new GameSpike(new Vector2f(530,80+20*i), world);
			spikey = spike.getBody();
			spikey.adjustRotation((float) (Math.PI/2)*3);
			spikes.add(spike);
			world2D.add(spikey);
		}
		//-------PROGRESS-KILLER----------------------------
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
		drawBox(g2, floor);
		drawBox(g2, floor2);
		drawBox(g2, floor3);
		drawBox(g2, floor4);
		drawBox(g2, wall);
		drawBox(g2, wall2);
		drawBox(g2, wall3);
		drawBox(g2, wall4);
		drawBox(g2, wall5);
		drawBox(g2, wall6);
		drawBox(g2, wall7);
		drawBox(g2, wall8);
		drawBox(g2, wall9);
		if(spikes.size() > 0)
		{
			for(GameSpike spikey : spikes)
				spikey.drawSpike(g2);
		}
		hero.drawHero(g2);
		
		//-------PROGRESS-KILLER----------------------------
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
//		if(hero2D.getPosition().getX()<0)
//			frame.loadMap(new L1M8(world,frame,new Vector2f(870f,world.getY())));
		if(hero2D.getPosition().getY()>600)
			frame.loadMap(new L1M6(world,frame, new Vector2f(world.getX(),10)));
	}
}