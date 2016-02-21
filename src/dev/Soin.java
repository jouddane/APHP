package dev;

/**
 * Classe representant un soin d'un parcours.
 * 
 */

public class Soin {

    /**
     * La duree du soin
     */
	protected int duree;
	
	/**
	 * Le tableau des quantites necessaires en chaque ressource
	 */
	protected int[] capaciteRessourcesNecessaires;
	
	/**
	 * Le nom du soin
	 */
	protected String nom;
	
	/**
	 * Constructeur d'un soin
	 * @param duree la duree du soin
	 * @param capaciteRessourcesNecessaires ses ressources necessaires (tableau de taille le nombre de ressources du probleme)
	 * @param nom le nom du soin
	 */
	public Soin(int duree, int[] capaciteRessourcesNecessaires, String nom){
		this.duree = duree;
		this.nom =nom;
		this.capaciteRessourcesNecessaires = capaciteRessourcesNecessaires;
	}
	
	/**
	 * Constructeur par defaut
	 * Initialise a un soin d'une duree nulle, ne consommant pas de ressource et n'ayant pas de nom
	 */
    public Soin(){
        this(0, null,"");
    }
	
	public int getDuree(){
		return this.duree;
	}
	
	public int[] getRessourcesNecessaires(){
		return this.capaciteRessourcesNecessaires;
	}
}
