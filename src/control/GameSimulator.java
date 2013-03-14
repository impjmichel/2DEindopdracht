package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import view.ControlPanel;
import view.GameFrame;
import view.MenuPanel;
import view.world.GameLevel;

public class GameSimulator implements ActionListener
{
	private GameFrame frame;
	private ArrayList<GameLevel> menu;
	private ArrayList<ArrayList<GameLevel>> levels;
	private Timer timer;
	
	public GameSimulator()
	{
		frame = new GameFrame();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		levels = new ArrayList<ArrayList<GameLevel>>();
		menu = new ArrayList<GameLevel>();
		menu.add(new MenuPanel(frame));
		menu.add(new ControlPanel(frame));
		levels.add(menu);
		timer = new Timer(2000, this);
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
		frame.setLevels(levels);
	}
}
