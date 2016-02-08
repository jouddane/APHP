package dev;

import java.util.Random;

public class Ressources {
	
	Ressource [] R;
	
	
	public Ressources(){
		this.R= new Ressource[500];
		
		Random r = new Random();
		
		for (int i=0; i<this.R.length; i++){
			this.R[i] = new Ressource(r.nextInt(9)+1,"A");
		}
		
	}
	
	public Ressource [] getR(){
		return this.R;
	}
	

	
	public int Rlength(){
		return this.R.length;
	}
	

}
