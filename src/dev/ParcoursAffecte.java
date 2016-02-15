package dev;

/**
 * Classe representant un paurcours affecte a un patient
 * 
 */

public class ParcoursAffecte extends Parcours{

    /**
     * Constructeur effectuant une copie d'un parcours existant mais non affecte a un patient
     * @param parcours le parcours a affecter
     */
	public ParcoursAffecte(Parcours parcours){
		super(parcours.groupeSoins, parcours.numeroParcours);
		GroupeSoins[] temp = parcours.groupeSoins;
		this.groupeSoins = new GroupeSoinsAffecte[temp.length];
		for(int i=0; i<temp.length; i++) {
			this.groupeSoins[i] = new GroupeSoinsAffecte(temp[i]);
		}
	}
	
	/**
	 * @return les groupes de soins composant le parcours affecte a un patient
	 */
	public GroupeSoinsAffecte[] getGroupesSoinsAffectes(){
		return (GroupeSoinsAffecte[])(this.groupeSoins);
	}
	public void setDebut(int debut){
		this.getGroupesSoinsAffectes()[0].setDebut(debut);
	}
}
