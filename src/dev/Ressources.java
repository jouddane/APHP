package dev;

import java.util.Random;

public class Ressources {
	
	Ressource [] RH;
	Ressource [] RM;
	
	public Ressources(){
		this.RH= new Ressource[30];
		this.RM= new Ressource[20];
		
		Random r = new Random();
		
		for (int i=0; i<this.RH.length; i++){
			this.RH[i] = new Ressource(r.nextInt(9)+1);
		}
		for (int i=0; i<this.RM.length; i++){
			this.RM[i] = new Ressource(r.nextInt(7)+1);
		}
	}
	
	public Ressource [] getRH(){
		return this.RH;
	}
	
	public Ressource [] getRM(){
		return this.RM;
	}
	
	public int RHlength(){
		return this.RH.length;
	}
	
	public int RMlength(){
		return this.RM.length;
	}
}
