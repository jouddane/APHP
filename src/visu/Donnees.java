package visu;

import java.util.Random;

import dev.Ressources;
import dev.Soin;
import dev.SoinAffecte;

public class Donnees {
	// [Patient][Temps] = Soin
	public SoinAffecte[][] donnees;
	public Ressources R;


	/**
	 * Crée un tableau de soins affectés à des heures qui se suivent
	 */
	public Donnees(){

		this.donnees = new SoinAffecte[20][30];
		this.R = new Ressources();

		Random r = new Random();

		for (int i=0; i<this.donnees.length;i++){

			for (int j=0; j<this.donnees[0].length; j++){

				if(r.nextInt(10)<3){
					int duree =(r.nextInt(3)+2)*15;
					Soin s= new Soin(duree);

					if(j==0){
						this.donnees[i][j]= new SoinAffecte(s,8*60, R);
					}
					else{
						this.donnees[i][j]= new SoinAffecte(s, donnees[i][j-1].getDebut()+donnees[i][j-1].getDuree(), R);
					}
				}

				else {
					Soin s= new Soin(15);

					if(j==0){
						this.donnees[i][j]= new SoinAffecte(s,8*60,R);
					}
					else{
						this.donnees[i][j]= new SoinAffecte(s, donnees[i][j-1].getDebut()+donnees[i][j-1].getDuree(),R);
					}
				}
			}
		}
	}

	public SoinAffecte getSoin(int i, int j){
		return this.donnees[i][j];
	}

	public int getDebutH(int i, int j){
		SoinAffecte s= this.getSoin(i, j);
		int debut = s.getDebut()/60;
		return debut;

	}

	public int getDebutM(int i, int j){
		SoinAffecte s= this.getSoin(i, j);
		int debut = s.getDebut()%60;
		return debut;
	}

	public int nbPatients(){
		return this.donnees.length;
	}

	public int nbSoins(){
		return this.donnees[0].length;
	}

	/**
	 * Retourne 0 si le soinaffecte(i,j) n'utilise pas la ressourceH(k) et le nombre de ressources si il en utilise
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	public int getRH(int i, int j, int k){
		int aretourner=0;
		int [][] ressources= this.donnees[i][j].getRessourcesUtiliseesH();
		if (ressources.length>0){
			for (int h =0; h<ressources.length; h++){
				if(ressources[h][0]==k)
					aretourner =ressources[h][1];
			}
		}
		return aretourner;
	}

	/**
	 * Retourne 0 si le soinaffecte(i,j) n'utilise pas la ressourceM(k) et le nombre de ressources si il en utilise
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	public int getRM(int i, int j, int k){
		int aretourner=0;
		int [][] ressources= this.donnees[i][j].getRessourceUtiliseeM();
		if( ressources.length>0){
			for (int h =0; h<ressources.length; h++){
				if(ressources[h][0]==k)
					aretourner =ressources[h][1];
			}
		}
		return aretourner;
	}


	public int[][] TableauCapacitesRH(){
		int taille=24*60;
		int [][] tableau = new int [R.RHlength()][taille];
		for( int a =0; a< R.RHlength(); a++){
			for (int i=0; i<nbPatients(); i++){
				for (int j=0; j<nbSoins(); j++){
					if(getRH(i, j, a)!=0){
						int longeur = donnees[i][j].getDuree();
						int lieu = getDebutM(i, j)+getDebutH(i, j)*60;

						for (int k=0; k<longeur; k++){
							tableau[a][lieu+k]+=getRH(i,j,a);
						}
					}
				}
			}

		}




		return tableau;
	}

	public int[][] TableauCapacitesRM(){
		int taille=24*60;
		int [][] tableau = new int [R.RMlength()][taille];
		for( int a =0; a< R.RMlength(); a++){
			for (int i=0; i<nbPatients(); i++){
				for (int j=0; j<nbSoins(); j++){
					if(getRM(i, j, a)!=0){
						int longeur = donnees[i][j].getDuree();
						int lieu = getDebutM(i, j)+getDebutH(i, j)*60;

						for (int k=0; k<longeur; k++){
							tableau[a][lieu+k]+=getRM(i,j,a);
						}
					}
				}
			}

		}


		return tableau;
	}
	
	public static void main(final String[] args) {

		Donnees donn = new Donnees();

		for (int i=0; i<29; i++){
			//System.out.println(donn.getSoin(0, i).getDebut());
			System.out.println("Soin  "+i+" "+donn.getDebutH(0, i)+"h "+donn.getDebutM(0, i)+"min  Ressources RH :"+donn.getRH(0, i, 1));

		}
	}


}
