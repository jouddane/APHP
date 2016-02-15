package dev;

/**
 * Classe representant un groupe de soins d'un parcours.
 * Cela permet de definir les precedences devant exister entre les soins d'un meme groupe de soins.
 * 
 */

public class GroupeSoins {

	protected Soin[] soins;
	protected int nombreDeSoins;
	
	/**
	 * Constructeur a partir d'un tableau de soins
	 * @param soins le tableau de soins
	 */
	public GroupeSoins(Soin[] soins){
		this.nombreDeSoins = soins.length;
		this.soins = soins;
	}
	
	/**
	 * @param indice l'indice d'un soin
	 * @return le soin a l'indice demande
	 */
	public Soin getSoin(int indice){
		return soins[indice];
	}
	
	
	/**
	 * @param indice l'indice du soin a changer
	 * @param soin le soin a mettre a l'indice indice
	 */
	public void setSoin(int indice, Soin soin){
		this.soins[indice] = soin;
	}

	/**
	 * @return le tableau de soins du groupe de soins
	 */
	public Soin[] getSoins() {
		return soins;
	}
	
	/**
	 * @param soins le nouveau tableau de soins
	 */
	public void setSoins(Soin[] soins) {
		this.soins = soins;
	}
	
	/**
	 * @return le nombre de soins du groupe de soins
	 */
	public int getNombreDeSoins(){
		return this.nombreDeSoins;
	}
	
	/**
	 * Ajoute un soin a la fin du tableau du groupe de soins
	 * @param soin le soin a  ajouter
	 */
	public void ajoutSoin(Soin soin){
		int n= this.soins.length;
		Soin[] soinsNew = new Soin[n+1];
		for (int i = 0; i < n; i++) {
			soinsNew[i]=this.soins[i];
		}
		soinsNew[n]=soin;
		this.soins = soinsNew;
	}
}
