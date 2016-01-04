
public class Ressource {

	private int quantiteMax;
	private int quantiteUtilisee;
	
	public Ressource(int quantiteMax){
		this.quantiteMax = quantiteMax;
		this.quantiteUtilisee = 0;
	}
	
	public Ressource(){
		this(0);
	}
	
	public void diminuerDe(int quantite){
		this.quantiteUtilisee += quantite;
	}
	
	public int quantiteDisponible(){
		return quantiteMax - quantiteUtilisee;
	}
	
	public int getQuantiteMax(){
		return quantiteMax;
	}
}
