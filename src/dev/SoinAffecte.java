package dev;

/**
 * Classe representant un soin affecte a un patient
 * 
 */

public class SoinAffecte extends Soin{

    /**
     * L'heure de debut du soin en nombre de periodes
     */
	private int debut;
	
	/**
	 * Booleen permettant de savoir si le soin est deja realise
	 */
	private boolean realise;
	
	/**
	 * Booleen permetant de savoir si le soin est en cours de realisation
	 */
	private boolean enCours;
	
	/**
	 * Constructeur d'un soin affecte a partir d'un soin existant
	 * @param s le soin existant a affecter a un patient
	 * @param debut l'heure de debut de ce soin en nombre de periodes
	 */
	public SoinAffecte(Soin s, int debut){
		super(s.getDuree(), s.getRessourcesNecessaires(), s.nom);
		this.debut = debut;
		this.realise = false;
		this.enCours = false;
	}
	
	public int getDebut(){
		return this.debut;
	}

	public boolean estRealise() {
		return realise;
	}

	public boolean estEnCours() {
		return enCours;
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}

	public void setRealise(boolean realise) {
		this.realise = realise;
	}

	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}
}
