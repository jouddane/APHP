package dev;

public class ParcoursAffecte extends Parcours{

	public ParcoursAffecte(Parcours parcours){
		super(parcours.groupeSoins);
		this.groupeSoins = (GroupeSoinsAffecte[])(parcours.groupeSoins);
	}
	
	public GroupeSoinsAffecte[] getGroupesSoinsAffectes(){
		return (GroupeSoinsAffecte[])(this.groupeSoins);
	}
	public void setDebut(int debut){
		this.getGroupesSoinsAffectes()[0].setDebut(debut);
	}
}
