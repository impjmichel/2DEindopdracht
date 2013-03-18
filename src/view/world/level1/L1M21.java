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
import view.MenuPanel;
import view.world.GameHero;
import view.world.GameLevel;
import view.world.GameSpike;
import view.world.GameWorld;

public class L1M21 extends GameLevel implements ActionListener
{
	private World world2D;
	private GameFrame frame;
	private Timer timer;
	private Body floor,floor2,floor3,floor4,floor5,wall,wall2,wall3,wall4,wall5,wall6,wall7,roof,roof2,roof3,hero2D;
	private GameSpike spike;
	private ArrayList<GameSpike> spikes;
	private GameHero hero;
	
	public L1M21(GameWorld world, GameFrame frame, Vector2f position)
	{
		super(world);
		this.frame = frame;
		world2D = world.getWorld2D();
		world2D.clear();
		timer = new Timer(1000/60,this);
		
		roof = new StaticBody("", new Box(300f,40f));
		roof.setPosition(510f, 0);
		roof2 = new StaticBody("", new Box(120f,40f));
		roof2.setPosition(800f, 40);
		roof3 = new StaticBody("", new Box(120f,40f));
		roof3.setPosition(880f, 0);
		
		floor = new StaticBody("", new Box(210f,40f));
		floor.setPosition(100f, 530);

		floor3 = new StaticBody("", new Box(344f,40f));
		floor3.setPosition(478f, 490);
		floor2 = new StaticBody("", new Box(113.137f,40f));
		floor2.setPosition(200f, 485);
		
		floor4 = new StaticBody("", new Box(380f,40f));
		floor4.setPosition(670f, 220);
		floor5 = new StaticBody("", new Box(120f,40f));
		floor5.setPosition(880f, 600);
		
		wall = new StaticBody("", new Box(40f,79f));
		wall.setPosition(630f, 470);
		wall2 = new StaticBody("", new Box(40f,79f));
		wall2.setPosition(326f, 470);
		
		wall3 = new StaticBody("", new Box(40f,299f));
		wall3.setPosition(500f, 290);
		wall4 = new StaticBody("", new Box(40f,439f));
		wall4.setPosition(840f, 420);
		wall5 = new StaticBody("", new Box(40f,79f));
		wall5.setPosition(840f, 20);
		wall6 = new StaticBody("", new Box(40f,119f));
		wall6.setPosition(640f, 0);
		wall7 = new StaticBody("", new Box(40f,119f));
		wall7.setPosition(760f, 0);
		
		spikes = new ArrayList<GameSpike>();
		spike = new GameSpike(new Vector2f(870,30), world);
		Body spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(890,30), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(910,30), world);
		spikey = spike.getBody();
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(870,570), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(890,570), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(910,570), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(870,50), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) (Math.PI/2)*3);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(530,150), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) (Math.PI/2)*3);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(530,170), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) (Math.PI/2)*3);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(530,190), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) (Math.PI/2)*3);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(490,130), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(510,130), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) Math.PI);
		spikes.add(spike);
		world2D.add(spikey);
		for(int i = 0; i < 18; i++)
		{
			spike = new GameSpike(new Vector2f(870,212+20*i), world);
			spikey = spike.getBody();
			spikey.adjustRotation((float) (Math.PI/2)*3);
			spikes.add(spike);
			world2D.add(spikey);
		}
		
		//-------PROGRESS-KILLER----------------------------
		spike = new GameSpike(new Vector2f(530,130), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) (Math.PI/2)*3);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(530,110), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) (Math.PI/2)*3);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(530,90), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) (Math.PI/2)*3);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(530,70), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) (Math.PI/2)*3);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(530,50), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) (Math.PI/2)*3);
		spikes.add(spike);
		world2D.add(spikey);
		spike = new GameSpike(new Vector2f(530,30), world);
		spikey = spike.getBody();
		spikey.adjustRotation((float) (Math.PI/2)*3);
		spikes.add(spike);
		world2D.add(spikey);
		//-------PROGRESS-KILLER----------------------------
		
		world2D.add(roof);
		world2D.add(roof2);
		world2D.add(roof3);
		world2D.add(floor2);
		world2D.add(floor3);
		world2D.add(floor4);
		world2D.add(floor5);
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
		drawBox(g2, floor5);
		drawBox(g2, roof);
		drawBox(g2, roof2);
		drawBox(g2, roof3);
		drawBox(g2, wall);
		drawBox(g2, wall2);
		drawBox(g2, wall3);
		drawBox(g2, wall4);
		drawBox(g2, wall5);
		drawBox(g2, wall6);
		drawBox(g2, wall7);
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
		if(hero2D.getPosition().getY()<0)
			frame.loadMap(new L1M5(world,frame,new Vector2f(world.getX(),590)));
		else if(hero2D.getPosition().getX()>900)
			frame.loadMap(new L1M34(world,frame, new Vector2f(10f,world.getY())));
	}
}
