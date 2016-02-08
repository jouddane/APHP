package dev;

import java.util.Calendar;

public class Date {

	private int jour;
	private int mois;
	private int annee;
	
	/**
	 * Constructeur fixant une date pour r�soudre le probl�me
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
	 * Retourne le jour de la Date utilis�e pour la r�solution
	 * @return
	 */
	public int getJour() {
		return jour;
	}
	
	/**
	 * Change le jour de la Date utilis�e pour la r�solution
	 * @param jour
	 */
	public void setJour(int jour) {
		this.jour = jour;
	}
	
	/**
	 * Retourne le mois de la Date utilis�e pour la r�solution
	 * @return
	 */
	public int getMois() {
		return mois;
	}
	
	/**
	 * Change le mois de la Date utilis�e pour la r�solution
	 * @param mois
	 */
	public void setMois(int mois) {
		this.mois = mois;
	}
	
	/**
	 * Retourne l'ann�e de la Date utilis�e pour la r�solution
	 * @return
	 */
	public int getAnnee() {
		return annee;
	}
	
	/**
	 * Change l'ann�e de la Date utilis�e pour la r�solution
	 * @param annee
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}	
}
