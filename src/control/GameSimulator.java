package control;

import javax.swing.JFrame;

import view.GameFrame;

public class GameSimulator
{
	private JFrame frame;
	
	public GameSimulator()
	{
		frame = new GameFrame();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		new GameSimulator();
	}
}
