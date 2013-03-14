package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements KeyListener
{
	private JPanel content;
	private int select, maxSelect;

	public GameFrame()
	{
		super("Gravity Control");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		select = 0;
		maxSelect = 3;
		
		content = new MenuPanel(this);
		setFocusable(true);
		addKeyListener(this);
		requestFocus();
		setContentPane(content);
	}

	@Override
	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_DOWN)
			select = (select+1)%maxSelect;
		if(ke.getKeyCode() == KeyEvent.VK_UP)
			select = (select+maxSelect-1)%maxSelect;
		
	}

	@Override
	public void keyReleased(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_ENTER)
		{
			switch(select)
			{
				case 0:
					System.out.println("start game!!");
					break;
				case 1:
					System.out.println("controls are easy, arrow keys + enter");
					break;
				case 2:
					System.exit(0);
					break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent ke)
	{
		
	}
	
	public int getSelect()
	{
		return select;
	}
}
