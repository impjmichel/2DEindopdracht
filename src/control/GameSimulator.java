package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import view.GameFrame;
import view.MenuPanel;
import view.world.GameWorld;

public class GameSimulator implements ActionListener
{
	private GameWorld world;
	private GameFrame frame;
	private Timer timer;
	private int waitTime, counter;
	
	public GameSimulator()
	{
		world = new GameWorld();
		frame = new GameFrame(world);
		world.setFrame(frame);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		waitTime = (int) (70*Math.random());
		timer = new Timer(1000/60, this);
		timer.start();
	}

	public static void main(String[] args)
	{
		new GameSimulator();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		counter++;
		if(counter == waitTime)
		{	
			timer.stop();
			frame.loadMap(new MenuPanel(world,frame));
		}
	}
}
