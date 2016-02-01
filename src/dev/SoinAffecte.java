package dev;

import java.util.Random;

public class SoinAffecte extends Soin{

	private int debut;
	private boolean realise;
	private boolean enCours;
	private int [] RessourcesUtiliseesH;
	private int [] RessourcesUtiliseesM;
	public SoinAffecte(Soin s, int debut, Ressources R){
		
		
		super(s.getDuree());
		this.debut = debut;
		this.realise = false;
		this.enCours = false;
		this.RessourcesUtiliseesH= new int[R.RHlength()];
		this.RessourcesUtiliseesM = new int[R.RMlength()];
		Random r = new Random();
		if( r.nextInt(100)<50){
		this.RessourcesUtiliseesH[r.nextInt(R.RHlength())]= 1;
		this.RessourcesUtiliseesM[r.nextInt(R.RMlength())]= 1;
		}
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
	/**
	 * Retourne [i][j] où i= ressource utilisée et [j] nombre de ressources utilisées
	 * @return
	 */
	public int[][] getRessourcesUtiliseesH(){
		int compteur=0;
		for(int i=0; i<RessourcesUtiliseesH.length;i++){
			if(RessourcesUtiliseesH[i]!=0)
				compteur++;
		}
		int[][] aretourner =new int[compteur][2];
		int j=0;
		for(int i=0; i<RessourcesUtiliseesH.length;i++){
			if (RessourcesUtiliseesH[i]!=0){
				aretourner[j][0]=i;
				aretourner[j][1]=RessourcesUtiliseesH[i];
				j++;
			}
		}
		
		return aretourner;
	}
	
	
	/**
	 * Retourne [i][j] où i= ressource utilisée et [j] nombre de ressources utilisées
	 * @return
	 */
	public int[][] getRessourceUtiliseeM(){
		int compteur=0;
		for(int i=0; i<RessourcesUtiliseesM.length;i++){
			if(RessourcesUtiliseesM[i]!=0)
				compteur++;
		}
		int[][] aretourner =new int[compteur][2];
		int j=0;
		for(int i=0; i<RessourcesUtiliseesM.length;i++){
			if (RessourcesUtiliseesM[i]!=0){
				aretourner[j][0]=i;
				aretourner[j][1]=RessourcesUtiliseesM[i];
				j++;
			}
		}
		
		return aretourner;
	}

	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}
}
