package dev;

public class GroupeSoinsAffecte extends GroupeSoins{

	private int debut;
	private boolean realise;
	private boolean enCours;
	
	
	/**
	 * Constructeur ou les soins deviennent des soins affectes
	 * @param groupeSoins
	 */
	public GroupeSoinsAffecte(GroupeSoins groupeSoins) {
		super(groupeSoins.soins);
		Soin[] temp = groupeSoins.soins;
		this.soins = new SoinAffecte[temp.length];
		this.debut = Integer.MAX_VALUE;
		for(int i=0; i<temp.length; i++) {
			this.soins[i] = new SoinAffecte(temp[i], this.debut);
		}
		this.realise = false;
		this.enCours = false;
	}	
	
	/**
	 * Constructeur avec un début pour le groupe de soins affectés
	 * @param soinsAffectes
	 * @param debut
	 */
	public GroupeSoinsAffecte(GroupeSoins soinsAffectes, int debut) {
		super(soinsAffectes.getSoins());
		this.debut = debut;
	}
	
	/**
	 * Retourne true si le groupe de soins affecte est realisé
	 * @return
	 */
	public boolean estRealise() {
		return realise;
	}
	
	/**
	 * Mets à jour l'état de réalisation : true si les groupe est réalisé
	 * @param realise
	 */
	public void setRealise(boolean realise) {
		this.realise = realise;
	}
	
	/**
	 * Retourne true si le groupe de soins affectes est en cours de réalisation
	 * @return
	 */
	public boolean estEnCours() {
		return enCours;
	}

	/**
	 * Mets à jours l'état de réalisation du groupe de soins affectes : true si ils sont en cours de réalisation
	 * @param enCours
	 */
	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}
	
	/**
	 * Retourne le tableau de soins affectés
	 * @return
	 */
	public SoinAffecte[] getSoinsAffectes() {
		return (SoinAffecte[])(this.getSoins());
	}

	/**
	 * Mets à jour le tableau de soins affectes
	 * @param soins
	 */
	public void setSoinsAffectes(SoinAffecte[] soins) {
		this.setSoins(soins);
	}
	
	/**
	 * Retourne le début du premier soin affecte : en min
	 * @return
	 */
	public int getDebut(){
		if(this.debut == Integer.MAX_VALUE){
			int temp = Integer.MAX_VALUE;
			for(SoinAffecte s : this.getSoinsAffectes()){
				if(s.getDebut() < temp)
					temp = s.getDebut();
			}
			this.debut = temp;
		}
		return this.debut;
	}
	
	/**
	 * Mets à jour le début des soins affectés
	 * @param debut
	 */
	public void setDebut(int debut){
		this.debut = debut;
	}
	
	/*
	public int debut(){
		int debut = Integer.MAX_VALUE;
		for(SoinAffecte s : this.getSoinsAffectes()){
			if(s.getDebut() < debut)
				debut = s.getDebut();
		}
		return debut;
	}
	*/
	
	/**
	 * Retourne la valeur en min du dernier soin affecte
	 * @return
	 */
	public int fin(){
		int fin = Integer.MIN_VALUE;
		for(SoinAffecte s : this.getSoinsAffectes()){
			if(s.getDebut() + s.getDuree() > fin)
				fin = s.getDebut() + s.getDuree();
		}
		return fin;
	}
	
}
