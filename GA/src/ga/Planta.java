
package ga;


public class Planta {
    private final int x;
    private final int y;
    private final int tipo;

    public Planta(int tipo, int x, int y)
    {
    this.tipo=tipo;
    this.x=x;
    this.y=y;
    }
    
    public int x()
    {
        return x;
    }
    
    public int y()
    {
        return y;
    }
    
    public int tipo()
    {
        return tipo;
    }
}