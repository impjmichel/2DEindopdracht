package view.world;

public abstract class GameLevel extends GameWorld
{

	public GameLevel()
	{
		// TODO Auto-generated constructor stub
	}
	
	public abstract void up();
	public abstract void down();
	public abstract void left();
	public abstract void right();
	public abstract void enter();
}
