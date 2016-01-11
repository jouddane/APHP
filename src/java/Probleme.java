package java;

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
	
	private int[][][] X_ijk;
	
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
		this.l_ijk = l_ijk;
		this.q_ijkr = q_ijkr;
		this.p_i = p_i;
		this.X_ijk = X_ijk;
	}

	public int[][][] getX_ijk() {
		return X_ijk;
	}

	public void setX_ijk(int[][][] x_ijk) {
		X_ijk = x_ijk;
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
