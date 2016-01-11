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
		int n= this.soins.length;
		Soin[] soinsNew = new Soin[n+1];
		for (int i = 0; i < n; i++) {
			soinsNew[i]=this.soins[i];
		}
		soinsNew[n]=soin;
		this.soins = soinsNew;
	}
}
