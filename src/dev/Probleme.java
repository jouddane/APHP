package dev;

/**
 * Classe reprensentant le probleme a resoudre. Il correspond au modele cree.
 * 
 */
public class Probleme {

    /**
     * Le nombre de parcours existants
     */
	private int nParcours;
	
	/**
	 * Le nombre de patients a traiter dans la journee
	 */
	private int nPatients;
	
	/**
	 * Le nombre de ressources existantes
	 */
	private int nRessources;
	
	/**
	 * Le nombre de periodes dont est composee la journee
	 */
	private int nPeriodes;
	
	/**
	 * L'heure d'ouverture en nombre de periodes
	 */
	private int HOuverture;
	
	/*
	 * L'heure de fermeture en nombre de periodes
	 */
	private int HFermeture;
	
	/**
	 * Le temps d'attente maximum entre deux soins pour un patient en nombre de periodes
	 */
	private int A_MAX;
	
	/**
	 * Le temps d'attente minimum entre deux soins pour un patient en nombre de periodes
     */
	private int A_MIN;
	
	/**
	 * Le nombre de groupe de soins composant les parcours
	 * L'indice i correspond au parcours i
	 */
	private int[] nG_i;
	
	/**
	 * Le nombre de soins composant les groupes de soins des differents parcours
	 * L'indice i correspond au parcours i
	 * L'indice j correspond au groupe de soins j du parcours i
	 */
	private int[][] nS_ij;
	
	/**
	 * La duree de chaque soin.
     * L'indice i correspond au parcours i
     * L'indice j correspond au groupe de soins j du parcours i
     * L'indice k correspond au soins k du groupe de soins j du parcours i
     */
	private int[][][] l_ijk;
	
	/**
	 * La quantite de ressources necessaires pour chaque soin
	 * L'indice i correspond au parcours i
     * L'indice j correspond au groupe de soins j du parcours i
     * L'indice k correspond au soins k du groupe de soins j du parcours i
     * L'indice r correspond au numero de la ressource
     */
	private int[][][][] q_ijkr;
	
	/**
	 * Tableau stockant les indices des parcours associes aux patients
	 */
	private int[] p_i;

    /**
     * La capacite d'une ressource i a la periode j
     */
    private int[][] cp_ij;
    
    /**
     * Tableau contenant les maximums des quantites en ressource i utilisées sur l'ensemble des periodes j 
     */
	private int[] Cp_ijMax;

	/**
	 * Constructeur de l'objet Probleme
	 * @param donnees un objet de type Donnees a partir duquel on cree le probleme
	 */
	public Probleme(Donnees donnees){
		this.nParcours = donnees.getParcours().length;
		this.nPatients = donnees.getPatients().length;
		this.nRessources = donnees.getRessources().length;
		this.nPeriodes = donnees.getnPeriodes();
		this.HOuverture = donnees.getHOuverture();
		this.HFermeture = donnees.getHFermeture();
		this.A_MAX = donnees.getA_MAX();
		this.A_MIN = donnees.getA_MIN();
		this.nG_i = new int[this.nParcours];
		for (int i = 0; i < this.nParcours; i++) {
			this.nG_i[i]=donnees.getParcours()[i].getNombreDeGroupes();
		}	

		this.nS_ij = new int[nParcours][];
		for (int i = 0; i < nParcours; i++) {
			this.nS_ij[i] = new int[this.nG_i[i]];
			for (int j = 0; j < nG_i[i]; j++) {
				this.nS_ij[i][j]=donnees.getParcours()[i].getGroupeSoins()[j].nombreDeSoins;
				//System.out.println("i="+i+", j="+j+", n="+this.nS_ij[i][j]);
			}
		}

		this.cp_ij = new int[nRessources][nPeriodes];
		for (int i = 0; i < nRessources; i++) {
			this.cp_ij[i]=donnees.getRessources()[i].getCapaciteMaxPeriodeP();
		}

		this.Cp_ijMax= new int[nRessources];
		for(int i=0; i<this.cp_ij.length;i++){
			int max=0;
			for(int j=0;j<this.cp_ij[i].length;j++){
				if(this.cp_ij[i][j]>max){
					max=this.cp_ij[i][j];
				}
			}
			this.Cp_ijMax[i]=max;
		}


		this.l_ijk = new int[nParcours][][];
		for (int i = 0; i < nParcours; i++) {
			this.l_ijk[i] = new int[this.nG_i[i]][];
			for (int j = 0; j < nG_i[i]; j++) {
				this.l_ijk[i][j] = new int[this.nS_ij[i][j]];
				for (int k = 0; k <this.nS_ij[i][j]; k++) {
					this.l_ijk[i][j][k]=donnees.getParcours()[i].getGroupeSoins()[j].getSoins()[k].getDuree();
				}	
			}
		}



		this.q_ijkr = new int[nParcours][][][];
		for (int i = 0; i < nParcours; i++) {
			this.q_ijkr[i] = new int[this.nG_i[i]][][];
			for (int j = 0; j < nG_i[i]; j++) {
				this.q_ijkr[i][j] = new int[this.nS_ij[i][j]][];
				for (int k = 0; k <this.nS_ij[i][j]; k++) {
					this.q_ijkr[i][j][k]=new int[nRessources];
					for (int r = 0; r < nRessources; r++) {
						this.q_ijkr[i][j][k][r]=donnees.getParcours()[i].getGroupeSoins()[j].getSoins()[k].getRessourcesNecessaires()[r];
					}
				}	
			}
		}

		this.p_i = new int[nPatients];
		for (int i = 0; i < nPatients; i++) {
			this.p_i[i]=donnees.getParcours(donnees.getPatients()[i].getParcours().getNumeroParcours());
		}

	}



	public int getnParcours() {
		return nParcours;
	}

	public void setnParcours(int nParcours) {
		this.nParcours = nParcours;
	}

	public int getnPatients() {
		return nPatients;
	}

	public void setnPatients(int nPatients) {
		this.nPatients = nPatients;
	}

	public int getnRessources() {
		return nRessources;
	}

	public void setnRessources(int nRessources) {
		this.nRessources = nRessources;
	}

	public int getnPeriodes() {
		return nPeriodes;
	}

	public void setnPeriodes(int nPeriodes) {
		this.nPeriodes = nPeriodes;
	}

	public int getHOuverture() {
		return HOuverture;
	}

	public void setHOuverture(int hOuverture) {
		HOuverture = hOuverture;
	}

	public int getHFermeture() {
		return HFermeture;
	}

	public void setHFermeture(int hFermeture) {
		HFermeture = hFermeture;
	}

	public int getA_MAX() {
		return A_MAX;
	}

	public void setA_MAX(int a_MAX) {
		A_MAX = a_MAX;
	}

	public int getA_MIN() {
		return A_MIN;
	}

	public void setA_MIN(int a_MIN) {
		A_MIN = a_MIN;
	}

	public int[] getnG_i() {
		return nG_i;
	}

	public void setnG_i(int[] nG_i) {
		this.nG_i = nG_i;
	}

	public int[][] getnS_ij() {
		return nS_ij;
	}

	public void setnS_ij(int[][] nS_ij) {
		this.nS_ij = nS_ij;
	}

	public int[][] getCp_ij() {
		return cp_ij;
	}

	public void setCp_ij(int[][] cp_ij) {
		this.cp_ij = cp_ij;
	}

	public int[][][] getL_ijk() {
		return l_ijk;
	}

	public void setL_ijk(int[][][] l_ijk) {
		this.l_ijk = l_ijk;
	}

	public int[][][][] getQ_ijkr() {
		return q_ijkr;
	}

	public void setQ_ijkr(int[][][][] q_ijkr) {
		this.q_ijkr = q_ijkr;
	}

	public int[] getP_i() {
		return p_i;
	}

	public void setP_i(int[] p_i) {
		this.p_i = p_i;
	}

	public int[] getCpij_max() {
		return this.Cp_ijMax;
	}

	/**
	 * 
	 * @param i l'indice du parcours voulu
	 * @param j l'indice du groupe de soins voulu du parcours i
	 * @param k l'indice du soin voulu du groupe de soins j du parcours i
	 * @return le tableau des ressources utilisees par le soins k
	 */
	public int[] getRessourcesUtilisees(int i, int j, int k) {
		int[] tableauRessources = new int[this.getnRessources()];
		for(int r = 0; r<this.getnRessources(); r++) {
			tableauRessources[r] = this.getQ_ijkr()[i][j][k][r];
		}
		return tableauRessources;
	}
	
	/**
	 * Met a jour le tableau des ressources utilisees par l'ensemble du probleme
	 * @param i l'indice du parcours voulu
     * @param j l'indice du groupe de soins voulu du parcours i
     * @param k l'indice du soin voulu du groupe de soins j du parcours i
     * @param ressourcesUtilisees les ressources utilisees par les autres soins que le soin k
	 * @return le tableau des ressources utilisees par tous autres soint plus le k
	 */
	public int[] updateRessourcesAvecSoin(int i, int j, int k, int[] ressourcesUtilisees) {
		if(ressourcesUtilisees.length != this.getnRessources()) {
			System.out.println("Pas la bonne taille de tableau !");
		} else {
			for(int r=0; r<this.getnRessources(); r++) {
				ressourcesUtilisees[r] += this.getQ_ijkr()[i][j][k][r];
			}
		}
		return ressourcesUtilisees;
	}
}
