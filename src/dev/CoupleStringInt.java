package dev;

/**
 * Classe permettant d'associer un nom de ressource et une capacite associee.
 * Cela permet notamment de creer un soin avec les informations sur ses ressources necessaires.
 * 
 */

public class CoupleStringInt {

    /*
     * Le nom de la ressource.
     */
	private String nom;
	
	/**
	 * Le capacite associee a la ressource.
	 */
	private int capacite;
	
	/**
	 * Constructeur de la classe.
	 * @param nom le nom de la ressource
	 * @param capacite la capacitee associee a la ressource
	 */
	public CoupleStringInt(String nom, int capacite) {
		super();
		this.nom = nom;
		this.capacite = capacite;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getCapacite() {
		return capacite;
	}
	
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}	
}
