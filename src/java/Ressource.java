package java;

public class Ressource {

	private int[] capaciteMaxPeriodeP;
	private int quantiteUtilisee;
	
	public Ressource(int[] capaciteMaxPeriodeP){
		this.capaciteMaxPeriodeP = capaciteMaxPeriodeP;
		this.quantiteUtilisee = 0;
	}
	
	public Ressource(int nPeriodes){
		this(new int[nPeriodes]);
	}
	
	public void diminuerDisponibilite(int quantite){
		this.quantiteUtilisee += quantite;
	}
	
	public void augmenterDisponibilite(int quantite){
		this.quantiteUtilisee -= quantite;
	}
	
	public void setCapaciteMaxPeriodeP(int[] capaciteMaxPeriodeP) {
		this.capaciteMaxPeriodeP = capaciteMaxPeriodeP;
	}
	
	public int[] getCapaciteMaxPeriodeP() {
		return capaciteMaxPeriodeP;
	}
}
