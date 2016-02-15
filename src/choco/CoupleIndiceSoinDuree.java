package choco;

/**
 * Classe representant un couple de deux entiers, correspondant respectivement a l'indice d'un soin (entier representant un soin) et la duree du meme soin
 */
public class CoupleIndiceSoinDuree {

	//L'indice du soin
	private int indiceSoin;
	//La duree du soin
	private int duree;
	
	/**
	 * Construit un couple regroupant deux valeurs entieres correspondant respectivement a l'indice d'un soin et sa duree
	 * @param indiceSoin valeur entiere representant le soin
	 * @param duree duree du soin
	 */
	public CoupleIndiceSoinDuree(int indiceSoin, int duree) {
		super();
		this.indiceSoin = indiceSoin;
		this.duree = duree;
	}
	
	/**
	 * getter de la variable d'instance indiceSoin
	 * @return indiceSoin valeur entiere representant le soin
	 */
	public int getIndiceSoin() {
		return indiceSoin;
	}
	
	/**
	 * setter de la variable d'instance indiceSoin
	 * @param indiceSoin le nouvel indice du soin
	 */
	public void setIndiceSoin(int indiceSoin) {
		this.indiceSoin = indiceSoin;
	}
	
	/**
	 * getter de la variable d'instance duree
	 * @return duree duree du soin
	 */
	public int getDuree() {
		return duree;
	}
	
	/**
	 * setter la variable d'instance duree
	 * @param duree duree du soin
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}

	
}
