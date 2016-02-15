package dev;

/**
 * Classe representant un groupe de soins d'un parcours affecte a un patient
 * 
 */
public class GroupeSoinsAffecte extends GroupeSoins{

	private int debut;
	private boolean realise;
	private boolean enCours;	
	
	/**
	 * Constructeur ou les soins deviennent des soins affectes
	 * @param groupeSoins l'objet de type GroupeSoins a partir duquel on cree le groupe de soins affecte
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
	 * Constructeur avec un debut pour le groupe de soins affecte
	 * @param soinsAffectes le groupe de soins
	 * @param debut l'heure de debut du soin en nombre de periodes
	 */
	public GroupeSoinsAffecte(GroupeSoins soinsAffectes, int debut) {
		super(soinsAffectes.getSoins());
		this.debut = debut;
	}
	
	/**
	 * @return true si le groupe de soins affecte est realise (tous les soins du groupes ont ete faits)
	 */
	public boolean estRealise() {
		return realise;
	}
	
	/**
	 * Mets a jour l'etat de realisation : true si les groupe est realise
	 * @param realise le nouvel etat de realisation
	 */
	public void setRealise(boolean realise) {
		this.realise = realise;
	}
	
	/**
	 * 
	 * @return true si le groupe de soins affecte est en cours de realisation
	 */
	public boolean estEnCours() {
		return enCours;
	}

	/**
	 * Mets a jour l'etat de realisation du groupe de soins affectes
	 * @param enCours vaut true si ils sont en cours de realisation
	 */
	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}
	
	/**
	 * @return le tableau de soins affecte
	 */
	public SoinAffecte[] getSoinsAffectes() {
		return (SoinAffecte[])(this.getSoins());
	}

	/**
	 * Mets a jour le tableau de soins affecte
	 * @param soins le nouveau tableau de soins
	 */
	public void setSoinsAffectes(SoinAffecte[] soins) {
		this.setSoins(soins);
	}
	
	/**
	 * 
	 * @return le debut du premier soin affecte en nombre de periodes
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
	 * Mets a jour le debut du groupe de soins affecte
	 * @param debut l'heure de debut en periodes
	 */
	public void setDebut(int debut){
		this.debut = debut;
	}
		
	/**
	 * 
	 * @return la valeur en periodes du dernier soin affecte
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
