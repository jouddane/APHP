
public class Soin {

	private int debut;
	private int duree;
	
	public Soin(int debut, int duree){
		this.debut = debut;
		this.duree = duree;
	}
	
	public Soin(){
		this(0,0);
	}
	
	public int getDebut(){
		return this.debut;
	}
	
	public int getDuree(){
		return this.duree;
	}
}
