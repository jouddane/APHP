package dev;

public class GroupeSoins {

	protected Soin[] soins;
	protected int nombreDeSoins;
	
	/**
	 * Constructeur à partir d'un tableau de soins
	 * @param soins
	 */
	public GroupeSoins(Soin[] soins){
		this.nombreDeSoins = soins.length;
		this.soins = soins;
	}
	
	/**
	 * Retourne le soin à l'indice demandé
	 * @param indice
	 * @return
	 */
	public Soin getSoin(int indice){
		return soins[indice];
	}
	
	
	/**
	 * Mets à jour le soin à l'indice demandé
	 * @param indice
	 * @param soin
	 */
	public void setSoin(int indice, Soin soin){
		this.soins[indice] = soin;
	}

	/**
	 * Retourne le tableau de soins du groupe de soins
	 * @return
	 */
	public Soin[] getSoins() {
		return soins;
	}
	
	/**
	 * Mets à jour le tableau de soins à partir d'un tableau de soins existants
	 * @param soins
	 */
	public void setSoins(Soin[] soins) {
		this.soins = soins;
	}
	
	/**
	 * Retourne le nombre de soins du groupe de soins
	 * @return
	 */
	public int getNombreDeSoins(){
		return this.nombreDeSoins;
	}
	
	/**
	 * ajoute un soin à la fin du tableau du groupe de soins
	 * @param soin
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
