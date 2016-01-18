package visu;

import java.util.Random;

import dev.Soin;
import dev.SoinAffecte;

public class Donnees {
	// [Patient][Temps] = Soin
	public SoinAffecte[][] donnees;

	public Donnees(){
		
		this.donnees = new SoinAffecte[20][30];
		
		Random r = new Random();

		for (int i=0; i<this.donnees.length;i++){
			
			for (int j=0; j<this.donnees[0].length; j++){
				
				if(r.nextInt(10)<3){
					int duree =(r.nextInt(3)+2)*15;
					Soin s= new Soin(duree);

					if(j==0){
						this.donnees[i][j]= new SoinAffecte(s,8*60);
					}
					else{
						this.donnees[i][j]= new SoinAffecte(s, donnees[i][j-1].getDebut()+donnees[i][j-1].getDuree());
					}
				}

				else {
					Soin s= new Soin(15);

				if(j==0){
					this.donnees[i][j]= new SoinAffecte(s,8*60);
				}
				else{
					this.donnees[i][j]= new SoinAffecte(s, donnees[i][j-1].getDebut()+donnees[i][j-1].getDuree());
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
		return this.donnees[0].length-1;
	}
	  public static void main(final String[] args) {
		  
		  Donnees donn = new Donnees();
		  
		  for (int i=0; i<29; i++){
				//System.out.println(donn.getSoin(0, i).getDebut());
				System.out.println("Soin  "+i+" "+donn.getDebutH(0, i)+"h "+donn.getDebutM(0, i)+"min");
			}
	  }
}
