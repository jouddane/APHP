package dev;

public class Soin {

	protected int duree;
	protected int[] ressourcesNecessaires;
	
	public Soin(int duree, int[] ressourcesNecessaires){
		this.duree = duree;
		this.ressourcesNecessaires = ressourcesNecessaires;
	}
	
	public Soin(){
		this(0, null);
	}
	
	public int getDuree(){
		return this.duree;
	}
	
	public int[] getRessourcesNecessaires(){
		return this.ressourcesNecessaires;
	}
}
