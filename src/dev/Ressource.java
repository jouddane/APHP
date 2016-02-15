package dev;

/**
 * Classe representant une ressource
 *
 */

public class Ressource {

    /**
     * Tableau representant les capacites maximum en ressource a chaque periode
     */
	private int[] capaciteMaxPeriodeP;
	
	/**
	 * Tableau representant la quantite en ressource utilisee a chaque periode
	 */
	private int[] quantiteUtilisee;
	
	/**
	 * Le nom de la ressource
	 */
	private String nom;
	
	/**
	 * @param capaciteMaxPeriodeP les capacites maximales a chaque periode de la journee
	 * @param nom le nom de la ressource
	 */
	public Ressource(int[] capaciteMaxPeriodeP, String nom){
		this.capaciteMaxPeriodeP = capaciteMaxPeriodeP;
		this.quantiteUtilisee = new int[capaciteMaxPeriodeP.length];
		this.nom=nom;
	}
	
	/**
	 * @param nPeriodes le nombre de periodes dans la journee
	 * @param nom le nom de la ressource
	 */
	public Ressource(int nPeriodes, String nom){
		this(new int[nPeriodes], nom);
	}
	
	/**
	 * Methode renvoyant une nouvelle ressource initialisee a une capacite maximale constante sur chaque periode de la journee
	 * @param nPeriodes le nombre de periodes de la journee
	 * @param nom le nom de la ressource
	 * @param capaciteConstante la capacite maximale constante sur toute la journee
	 * @return la ressource ainsi creee
	 */
	public static Ressource RessourceConstante(int nPeriodes, String nom, int capaciteConstante){
		int[] capaciteMaxPeriodeP= new int[nPeriodes];
		for (int i = 0; i < capaciteMaxPeriodeP.length; i++) {
			capaciteMaxPeriodeP[i]=capaciteConstante;
		}
		return new Ressource(capaciteMaxPeriodeP,nom);
	}
	
	/**
	 * Diminue la quantite disponible 
	 * @param periode la periode a laquelle on diminue la quantite disponible
	 * @param quantite la quantite a soustraire
	 */
	public void diminuerDisponibilite(int periode, int quantite){
		this.quantiteUtilisee[periode] += quantite;
	}
	
	/**
	 * Augmente la quantite disponible 
     * @param periode la periode a laquelle on augmente la quantite disponible
     * @param quantite la quantite a ajouter
     */
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
