
package ga;

import java.util.ArrayList;


public class Tab {
	//Attr
	private int W, H;
	private int tablero[][];
        private int tableroInicial[][];
        private float fit;
        private ArrayList plantas;
        private Zombie zombies[];
	
	//Methods
	public Tab (int H, int W, Zombie[] zombies)
	{
                plantas = new ArrayList();
                Planta p;
		this.W=W;
		this.H=H;
                fit = 0;
		tablero = new int[H][W];
                tableroInicial = new int [H][W];
		int rand;
                Zombie z;
                this.zombies = new Zombie[zombies.length];
                for (int i=0; i<zombies.length; i++){
                    z = new Zombie(zombies[i].x(), zombies[i].y());
                    this.zombies[i]=z;
                }
                
		//Initialize with random values
		for (int i=0; i<H; i++){
			for (int j=0; j<Math.min(4, W); j++){
				rand = (int)(Math.random()*4);
				tablero[i][j]=rand;
                                tableroInicial[i][j]=rand;
                                if (rand!=0){
                                    p = new Planta(rand, i, j);
                                    plantas.add(p);
                                }
                                    
                               
			}
		}
		for (int i=0; i<H; i++){
			for (int j=4; j<W; j++){
				tablero[i][j]=0;
                                tableroInicial[i][j]=0;
			}
		}
	}
	
	public void setValue (int x, int y, int val)
	{
		//0 empty | 1 peashooter | 2 Repeater | 3 Potato mine
		tablero[x][y]=val;
	}
	
	public int getValue (int x, int y)	
	{
		return tablero[x][y];
	}
 float fitness ()
        {
            int i,j, max;
            boolean arrived=false;
            int numZombies=zombies.length;
            max=0;
            Planta pget;
            while( numZombies>0 && !arrived){
                if (max<zombies.length)
                            max++;
                    else
                        max=numZombies;
                for (i=0; i<max; i++){ //Para cada zombie
                    if (tablero[zombies[i].x()][zombies[i].y()]==1 || tablero[zombies[i].x()][zombies[i].y()]==2){
                        //Comerse la planta
                        for (int k=0; k<plantas.size(); k++){
                            pget = (Planta) plantas.get(k);
                            if (pget.x()==zombies[i].x() && pget.y()==zombies[i].y){
                                tablero[zombies[i].x()][zombies[i].y()] = 0;
                                plantas.remove(k);
                                break;
                            }
                        }
                    }else{
                        if (tablero[zombies[i].x()][zombies[i].y()]==3){
                            for (int k=0; k<plantas.size(); k++){
                            pget = (Planta) plantas.get(k);
                                if (pget.x()==zombies[i].x() && pget.y()==zombies[i].y){
                                    tablero[zombies[i].x()][zombies[i].y()] = 0;
                                    plantas.remove(k);
                                    zombies[i].damage();
                                    zombies[i].damage();
                                    zombies[i].damage();
                                    zombies[i].damage();
                                    numZombies--;
                                    break;
                                }
                            }
                        }
                    }
                }

                //Disparar
                for (i=0; i<plantas.size(); i++){
                    pget=(Planta) plantas.get(i);
                        if (pget.tipo()==1 || pget.tipo()==2){
                            for (j=0; j<max; j++){
                                if (pget.x()==zombies[j].x() && zombies[j].getLives()>0){
                                    zombies[j].damage();
                                    if (pget.tipo()==2)
                                        zombies[j].damage();
                                    if (zombies[j].getLives()<=0)
                                        numZombies--;
                                    break;
                                }
                            }
                        }
                }

                //Mover
                for (i=0; i<max; i++){

                    if (zombies[i].getLives()>0)
                        zombies[i].y(zombies[i].y()-1); 
                    if (zombies[i].y()==0)
                        arrived = true;
                }
            }
            //Calcular el centro de masa
            int masaTotal=0;
            for (i=0; i<zombies.length; i++){
                masaTotal = masaTotal+ (W-zombies[i].y());
            }
            float centroX, centroY; //Los flotantes que van a representar el centro de masa de la nube de puntos
            centroX=centroY=0; 
            for (i=0; i<zombies.length; i++){
                centroY+=(W-zombies[i].y())*zombies[i].y(); //*zombies[i].y()+1 es otra opcion
            }
            centroY = centroY/masaTotal;
           // System.out.println(centroY);
            fit = centroY;
            /*
            Estamos calculando el centro de masa solo en 
            el punto y, debemos trazar una linea que nos diga que tan bueno 
            es ese conjunto de puntos que representa
            las posiciones donde han muerto los zombies.
            */
            return fit;   
        }
 
    float getFitness(){
        return fit;
    }
    
    int[][] getTablero(){
        return tableroInicial;
    }
    
}














