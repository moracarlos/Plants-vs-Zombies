
package ga;


public class Zombie {
	//Attr
	int x;
	int y;
	int lives;
	
	
	//Methods
	public Zombie(int x, int y)
	{
		this.x=x;
		this.y=y;
		lives = 4;
	}
	
	public int x(){return x;}
	public int y(){return y;}
        
        public void x(int v){x=v;}
	public void y(int v){y=v;}
	public void damage()
	{
		lives--;
	}
	public int getLives()
	{
		return lives;
	}
}

