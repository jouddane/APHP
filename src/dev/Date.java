package dev;

import java.util.Calendar;

public class Date {

	private int jour;
	private int mois;
	private int annee;
	
	/**
	 * Constructeur fixant une date pour résoudre le problème
	 * @param jour
	 * @param mois
	 * @param annee
	 */
	public Date(int jour, int mois, int annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}
	
	
	public Date(){
		this(1,1,2000);
	}
	
	/**
	 * Retourne le jour de la Date utilisée pour la résolution
	 * @return
	 */
	public int getJour() {
		return jour;
	}
	
	/**
	 * Change le jour de la Date utilisée pour la résolution
	 * @param jour
	 */
	public void setJour(int jour) {
		this.jour = jour;
	}
	
	/**
	 * Retourne le mois de la Date utilisée pour la résolution
	 * @return
	 */
	public int getMois() {
		return mois;
	}
	
	/**
	 * Change le mois de la Date utilisée pour la résolution
	 * @param mois
	 */
	public void setMois(int mois) {
		this.mois = mois;
	}
	
	/**
	 * Retourne l'année de la Date utilisée pour la résolution
	 * @return
	 */
	public int getAnnee() {
		return annee;
	}
	
	/**
	 * Change l'année de la Date utilisée pour la résolution
	 * @param annee
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}	
}
