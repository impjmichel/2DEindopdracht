package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Timer;

import net.phys2d.math.Vector2f;
import view.world.GameLevel;
import view.world.GameWorld;
import view.world.level1.L1M34;

public class MenuPanel extends GameLevel implements ActionListener
{
	private ArrayList<Shape> shapes;
	private static String[] s = {"Start","Controls","Exit"};
	private GameFrame frame;
	private int select, maxSelect;
	private Timer timer;
	
	public MenuPanel(GameFrame frame, GameWorld world)
	{
		super(world);
		this.frame = frame;
		setPreferredSize(new Dimension(900,600));
		select = 0;
		maxSelect = 3;
		shapes = new ArrayList<Shape>();
		for(int i = 0; i < 3; i++)
		{
			Rectangle2D option = new Rectangle2D.Double(0, 0, 300, 60);
			shapes.add(option);
		}
		
		timer = new Timer(1000/60, this);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.translate(300, 150);
		Font font = new Font("Serif", Font.BOLD, 60);
		g2.setFont(font);
		FontRenderContext frc = g2.getFontRenderContext();
		for(int id = 0; id < s.length; id++)
		{
			if(select == id)
			{
				GradientPaint p = new GradientPaint(0,0,Color.GREEN,300,0,Color.BLUE);
				g2.setPaint(p);
			}
			else
			{
				GradientPaint p = new GradientPaint(0,0,Color.LIGHT_GRAY,300,0,Color.DARK_GRAY);
				g2.setPaint(p);
			}
			g2.fill(shapes.get(id));
			g2.setColor(Color.BLACK);
			g2.draw(shapes.get(id));
			g2.translate(20, 50);
			GlyphVector gvN = font.createGlyphVector(frc, s[id]);
			Shape glyphN = gvN.getOutline(0,0);
			
			g2.draw(glyphN);
			g2.translate(-20, 30);
		}
	}

	public void up()
	{
		select = (select+maxSelect-1)%maxSelect;
	}
	public void down()
	{
		select = (select+1)%maxSelect;
	}
	public void enter()
	{
		switch(select)
		{
			case 0:
				frame.loadMap(new L1M34(world,frame, new Vector2f(300f,320)));
				break;
			case 1:
				frame.loadMap(new ControlPanel(frame,world));
				break;
			case 2:
				System.exit(0);
				break;
		}
	}
	public void escape()
	{
		System.exit(0);
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		update();
	}
	
	public void update()
	{
		repaint();
	}

	@Override
	public void left()
	{
	}

	@Override
	public void right()
	{
	}
	
	public void start()
	{
		timer.start();
	}
	public void stop()
	{
		timer.stop();
	}
}
