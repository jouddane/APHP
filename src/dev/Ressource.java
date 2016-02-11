package dev;

public class Ressource {

	private int[] capaciteMaxPeriodeP;
	private int[] quantiteUtilisee;
	private String nom;
	
	public Ressource(int[] capaciteMaxPeriodeP, String nom){
		this.capaciteMaxPeriodeP = capaciteMaxPeriodeP;
		this.quantiteUtilisee = new int[capaciteMaxPeriodeP.length];
		this.nom=nom;
	}
	
	public Ressource(int nPeriodes, String nom){
		this(new int[nPeriodes], nom);
	}
	
	public static Ressource RessourceConstante(int nPeriodes, String nom, int capaciteConstante){
		int[] capaciteMaxPeriodeP= new int[nPeriodes];
		for (int i = 0; i < capaciteMaxPeriodeP.length; i++) {
			capaciteMaxPeriodeP[i]=capaciteConstante;
		}
		return new Ressource(capaciteMaxPeriodeP,nom);
	}
	
	public void diminuerDisponibilite(int periode, int quantite){
		this.quantiteUtilisee[periode] += quantite;
	}
	
	public void augmenterDisponibilite(int periode, int quantite){
		this.quantiteUtilisee[periode] -= quantite;
	}
	
	public void setCapaciteMaxPeriodeP(int[] capaciteMaxPeriodeP) {
		this.capaciteMaxPeriodeP = capaciteMaxPeriodeP;
	}
	
	public int[] getCapaciteMaxPeriodeP() {
		return capaciteMaxPeriodeP;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
}
