package java;

public class Soin {

	protected int duree;
	protected int[] ressourcesNecessaires;
	
	public Soin(int duree){
		this.duree = duree;
	}
	
	public Soin(){
		this(0);
	}
	
	public int getDuree(){
		return this.duree;
	}
	
	public int[] getRessourcesNecessaires(){
		return this.ressourcesNecessaires;
	}
}
