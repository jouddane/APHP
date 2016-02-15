package dev;

public class Date {

    /**
     * Le numero du jour de la date.
     */
	private int jour;
	
	/** 
	 * Le numero du mois de la date.
	 */
	private int mois;
	  
	/**
	 * Le numero de l'annee de la date.
	 */
	private int annee;
	
	/**
	 * Constructeur permettant de creer une date.
	 * @param jour le jour de la date
	 * @param mois le mois de la date
	 * @param annee l'annee de la date
	 */
	public Date(int jour, int mois, int annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}
	
	/**
	 * Constructeur par defaut de Date. Initialise au premier janier 2000.
	 */
	public Date(){
		this(1,1,2000);
	}
	
	/**
	 * @return le jour de Date
	 */
	public int getJour() {
		return jour;
	}
	
	/**
	 * @param jour le nouveau jour de Date
	 */
	public void setJour(int jour) {
		this.jour = jour;
	}
	
	/**
	 * @return le mois de Date 
	 */
	public int getMois() {
		return mois;
	}
	
	/**
	 * @param mois le nouveau mois de Date
	 */
	public void setMois(int mois) {
		this.mois = mois;
	}
	
	/**
	 * @return l'annee de Date 
	 */
	public int getAnnee() {
		return annee;
	}
	
	/**
	 * @param annee la nouvelle annee de Date
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}	
}
