package dev;

public class ParcoursAffecte extends Parcours{

	public ParcoursAffecte(Parcours parcours){
		super(parcours.groupeSoins, parcours.numeroParcours);
		GroupeSoins[] temp = parcours.groupeSoins;
		this.groupeSoins = new GroupeSoinsAffecte[temp.length];
		for(int i=0; i<temp.length; i++) {
			this.groupeSoins[i] = new GroupeSoinsAffecte(temp[i]);
		}
	}
	
	public GroupeSoinsAffecte[] getGroupesSoinsAffectes(){
		return (GroupeSoinsAffecte[])(this.groupeSoins);
	}
	public void setDebut(int debut){
		this.getGroupesSoinsAffectes()[0].setDebut(debut);
	}
}
