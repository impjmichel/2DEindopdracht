package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import view.ControlPanel;
import view.GameFrame;
import view.MenuPanel;
import view.world.GameLevel;
import view.world.GameWorld;
import view.world.level1.L1M1;
import view.world.level1.L1M2;

public class GameSimulator implements ActionListener
{
	private GameWorld world;
	private GameFrame frame;
	private Timer timer;
	
	public GameSimulator()
	{
		world = new GameWorld();
		frame = new GameFrame(world);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		timer = new Timer((int) (5*Math.PI), this);
		timer.start();
	}

	public static void main(String[] args)
	{
		new GameSimulator();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		timer.stop();
		frame.loadMap(new MenuPanel(frame,world));
	}
}
