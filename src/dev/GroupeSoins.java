package dev;

public class GroupeSoins {

	protected Soin[] soins;
	protected int nombreDeSoins;
	
	public GroupeSoins(Soin[] soins){
		this.nombreDeSoins = soins.length;
		this.soins = soins;
	}
	
	public Soin getSoin(int indice){
		return soins[indice];
	}
	
	public void setSoin(int indice, Soin soin){
		this.soins[indice] = soin;
	}

	public Soin[] getSoins() {
		return soins;
	}

	public void setSoins(Soin[] soins) {
		this.soins = soins;
	}
	
	public int getNombreDeSoins(){
		return this.nombreDeSoins;
	}
	
	public void ajoutSoin(Soin soin){
		
	}
}
