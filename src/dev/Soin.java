package dev;

import java.util.ArrayList;

public class Soin {

	protected int duree;
	protected int[] capaciteRessourcesNecessaires;
	protected String nom;
	
	public Soin(int duree, int[] capaciteRessourcesNecessaires, String nom){
		this.duree = duree;
		this.nom =nom;
		this.capaciteRessourcesNecessaires = capaciteRessourcesNecessaires;
	}
	
	public static Soin creerSoin(Donnees donnees, ArrayList<CoupleStringInt> listeRessourcesCapacite, String nom , int duree ){
		int[] capaciteRessourcesNecessaires = new int[donnees.getRessources().length];
		for (int i = 0; i < capaciteRessourcesNecessaires.length; i++) {
			capaciteRessourcesNecessaires[i] = 0;
		}
		for (CoupleStringInt ressourceCapacite : listeRessourcesCapacite) {
			capaciteRessourcesNecessaires[donnees.getRessourceNom(ressourceCapacite.getNom())]=ressourceCapacite.getCapacite();
		}
		return new Soin(duree, capaciteRessourcesNecessaires, nom);
	}
	
	public Soin(){
		this(0, null,"");
	}
	
	public int getDuree(){
		return this.duree;
	}
	
	public int[] getRessourcesNecessaires(){
		return this.capaciteRessourcesNecessaires;
	}
}
