
package ga;

import java.util.ArrayList;
import java.util.Scanner;

public class GA {
        //Attr
	int W, H, Z;
	Zombie zombies[];
	Tab poblacion[];
	
	float PC; //Probabilidad de cruce
	float PM; //Probabilidad de mutacion
	int tamPoblacion; //Tamano de la poblacion
	private Scanner in;
	
	public GA(){
		tamPoblacion = 10;
		int Zi;
		in = new Scanner(System.in);
                W=in.nextInt();
                H=in.nextInt();
                Z=in.nextInt();
                zombies = new Zombie[Z];
		for (int i=0; i<Z; i++){
                    Zi=in.nextInt();
                    zombies[i] = new Zombie(Zi, W-1);
		}
	}
	
	public void init()
	{
		int i;
                float fitnessTotal=0;
		//Generate random population
                poblacion = new Tab[tamPoblacion];
		for (i=0; i<tamPoblacion; i++){
			poblacion[i]=new Tab(H, W, zombies);
			
			
                       /*
                       // Esto imprime todos los tableros de la poblacion
                        for (int j=0; j<H; j++){
				for (int k=0; k<W; k++){
					System.out.print(poblacion[i].getValue(j, k));
					System.out.print(" ");

				}
				System.out.print(" \n");
			}
                               */
                        
		}
		//Fitness
                for (i=0; i<tamPoblacion; i++){
                    fitnessTotal+=poblacion[i].fitness();
                }
                //Cada tablero ahora tiene su fitness
                //Aqui va el muestreo
                
		
	}
	public static void main(String[] args)
	{
		GA algoritmo = new GA();                
		algoritmo.init();
                int t=0;
	}
}
