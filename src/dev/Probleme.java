package dev;

public class Probleme {

	private int nParcours;
	private int nPatients;
	private int nRessources;
	private int nPeriodes;
	private int HOuverture;
	private int HFermeture;
	private int A_MAX;
	private int A_MIN;
	private int[] nG_i;
	private int[][] nS_ij;
	private int[][] cp_ij;
	private int[][][] l_ijk;
	private int[][][][] q_ijkr;
	private int[] p_i;
	private int[] Cp_ijMax;
	
	
	public Probleme(int nParcours, int nPatients, int nRessources, int nPeriodes, int hOuverture, int hFermeture,
			int a_MAX, int a_MIN, int[] nG_i, int[][] nS_ij, int[][] cp_ij, int[][][] l_ijk, int[][][][] q_ijkr,
			int[] p_i, int[][][] X_ijk) {
		this.nParcours = nParcours;
		this.nPatients = nPatients;
		this.nRessources = nRessources;
		this.nPeriodes = nPeriodes;
		HOuverture = hOuverture;
		HFermeture = hFermeture;
		A_MAX = a_MAX;
		A_MIN = a_MIN;
		this.nG_i = nG_i;
		this.nS_ij = nS_ij;
		this.cp_ij = cp_ij;
		
		this.Cp_ijMax= new int[cp_ij.length];
		for(int i=0; i<cp_ij.length;i++){
			int max=0;
			for(int j=0;j<cp_ij[i].length;j++){
				if(cp_ij[i][j]>max){
					max=cp_ij[i][j];
				}
			}
			this.Cp_ijMax[i]=max;
		}
		this.l_ijk = l_ijk;
		this.q_ijkr = q_ijkr;
		this.p_i = p_i;
	}
	
	public Probleme(Donnees donnees){
		this.nParcours = donnees.getParcours().length;
		this.nPatients = donnees.getPatients().length;
		this.nRessources = donnees.getRessources().length;
		this.nPeriodes = donnees.getnPeriodes();
		this.HOuverture = donnees.getHOuverture();
		this.HFermeture = donnees.getHFermeture();
		this.A_MAX = donnees.getA_MAX();
		this.A_MIN = donnees.getA_MIN();
		this.nG_i = new int[nPatients];
		for (int i = 0; i < nPatients; i++) {
			this.nG_i[i]=donnees.getPatients()[i].getParcours().getNombreDeGroupes();
		}
		
		//A modifier sur le modele, indices inverses
		this.nS_ij = new int[nPatients][];
		for (int i = 0; i < nPatients; i++) {
			this.nS_ij[i] = new int[this.nG_i[i]];
			for (int j = 0; j < nG_i[i]; j++) {
				this.nS_ij[i][j]=donnees.getPatients()[i].getParcours().getGroupeSoins()[j].nombreDeSoins;
			}
		}
		
		//A modifier sur le modele, indices inverses
		this.cp_ij = new int[nRessources][nPeriodes];
		for (int i = 0; i < nRessources; i++) {
			this.cp_ij[i]=donnees.getRessources()[i].getCapaciteMaxPeriodeP();
		}
		
		//A modifier sur le modele, indices inverses
		this.l_ijk = new int[nPatients][][];
		for (int i = 0; i < nPatients; i++) {
			this.l_ijk[i] = new int[this.nG_i[i]][];
			for (int j = 0; j < nG_i[i]; j++) {
				this.l_ijk[i][j] = new int[this.nS_ij[i][j]];
				for (int k = 0; k <this.nS_ij[i][j]; k++) {
					this.l_ijk[i][j][k]=donnees.getPatients()[i].getParcours().getGroupeSoins()[j].getSoins()[k].getDuree();
				}	
			}
		}
		
		
		
		this.q_ijkr = new int[nPatients][][][];
		for (int i = 0; i < nPatients; i++) {
			this.q_ijkr[i] = new int[this.nG_i[i]][][];
			for (int j = 0; j < nG_i[i]; j++) {
				this.q_ijkr[i][j] = new int[this.nS_ij[i][j]][];
				for (int k = 0; k <this.nS_ij[i][j]; k++) {
					this.q_ijkr[i][j][k]=new int[nRessources];
					for (int r = 0; r < nRessources; r++) {
						this.q_ijkr[i][j][k][r]=donnees.getPatients()[i].getParcours().getGroupeSoins()[j].getSoins()[k].getRessourcesNecessaires()[r];
					}
				}	
			}
		}
		
		this.p_i = new int[nPatients];
		for (int i = 0; i < nPatients; i++) {
			this.p_i[i]=donnees.getPatients()[i].getParcours().getIndiceParcours();
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
		
}
