package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Timer;

import view.world.GameLevel;

public class ControlPanel extends GameLevel implements ActionListener
{
	private GameFrame frame;
	private String[] s = {"Game controls:","Move with 'A' and 'D'","Switch with 'W' and 'S'","Use 'Enter' to interact.","Move with 'LEFT' and 'RIGHT'","Switch with 'UP' and 'DOWN'","Use 'Space' to interact.","BACK"};
	private int select, maxSelect;
	private ArrayList<Shape> shapes;
	private Timer timer;
	private int selected;
	
	public ControlPanel(GameFrame frame)
	{
		this.frame = frame;
		selected = frame.isDefaultKeys();
		select = 0;
		maxSelect = 3;
		shapes = new ArrayList<Shape>();
		Rectangle2D title = new Rectangle2D.Double(20, -30, 210, 40);
		shapes.add(title);
		Rectangle2D option1 = new Rectangle2D.Double(-60, -60, 440, 100);
		shapes.add(option1);
		Rectangle2D option2 = new Rectangle2D.Double(-60, -30, 440, 100);
		shapes.add(option2);
		Rectangle2D option3 = new Rectangle2D.Double(0, 0, 210, 40);
		shapes.add(option3);
		timer = new Timer(1000/60, this);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(300, 150);		
		
		for(int id = 0; id < shapes.size(); id++)
		{
			if(select+1 == id)
			{
				GradientPaint p = new GradientPaint(-60,0,Color.GREEN,500,0,Color.BLUE);
				g2.setPaint(p);
			}
			else
			{
				GradientPaint p = new GradientPaint(-60,0,Color.LIGHT_GRAY,440,0,Color.DARK_GRAY);
				g2.setPaint(p);
			}
			g2.fill(shapes.get(id));
			if(id > 0)
			{
				g2.setColor(Color.BLACK);
				g2.draw(shapes.get(id));
			}
			g2.translate(0, 80);
		}
		g2.translate(30, -80*shapes.size());
		Font font = new Font("Serif", Font.BOLD, 30);
		g2.setFont(font);
		FontRenderContext frc = g2.getFontRenderContext();
		for(String str : s)
		{
			GlyphVector gvN = font.createGlyphVector(frc, str);
			Shape glyphN = gvN.getOutline(0,0);
			if(str.equals(s[6]))
				g2.translate(80, 0);
			g2.draw(glyphN);
			if(str.equals(s[0]))
				g2.translate(-80, 0);
			if(str.equals(s[0]) || str.equals(s[3]) || str.equals(s[6]))
				g2.translate(0, 50);
			else
				g2.translate(0, 30);	
		}
		
		g2.translate(-130, -270);
		ArrayList<Shape> diamonds = new ArrayList<Shape>();
		for(int i = 0; i < 2; i++)
		{
			GeneralPath diamond = new GeneralPath();
			diamond.append(new Line2D.Double(0,0,10,25), true);
			diamond.append(new Line2D.Double(10,25,0,80), true);
			diamond.append(new Line2D.Double(0,80,-10,25), true);
			diamond.append(new Line2D.Double(-10,25,0,0), true);
			diamonds.add(diamond);
		}

		for(int i = 0; i < 2; i++)
		{
			if(selected == i)
			{
				GradientPaint p = new GradientPaint(5,10,Color.GREEN,5,50,Color.BLUE);
				g2.setPaint(p);
			}
			else
			{
				GradientPaint p = new GradientPaint(5,5,Color.LIGHT_GRAY,5,50,Color.DARK_GRAY);
				g2.setPaint(p);
			}
			
			g2.fill(diamonds.get(0));
			g2.setColor(Color.BLACK);
			g2.draw(diamonds.get(0));
			g2.translate(0, 105);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		update();
	}

	@Override
	public void up()
	{
		select = (select+maxSelect-1)%maxSelect;
	}

	@Override
	public void down()
	{
		select = (select+1)%maxSelect;
	}

	@Override
	public void left()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void right()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter()
	{
		switch(select)
		{
		case 0:
			frame.setDefaultKeys(0);
			break;
		case 1:
			frame.setDefaultKeys(1);
			break;
		case 2:
			frame.previousMap();
			break;
		}
	}
	public void escape()
	{
		frame.previousMap();
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

	public void update()
	{
		selected = frame.isDefaultKeys();
		repaint();
	}
}
