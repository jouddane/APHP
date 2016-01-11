package choco;

import java.Probleme;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;

public class Resolution {
	
	private Probleme aResoudre;

	public Resolution(Probleme aResoudre) {
		this.aResoudre = aResoudre;
	}

	public Probleme getAResoudre() {
		return aResoudre;
	}

	public void setAResoudre(Probleme aResoudre) {
		this.aResoudre = aResoudre;
	}

	public void resout(Probleme p){
		
		//Initialisation du solver
		Solver solver = new Solver();
		
		int nPatients =p.getnPatients();
		int[] nG = p.getnG_i();
		int[][] nS = p.getnS_ij();
		int nPeriodes = p.getnPeriodes();
				
		//Initialisation des variables
		//Xi,j,k : modele a modifier pour coherence avec le code
		IntVar[][][]  X= new IntVar[nPatients][][];
		for(int i=0;i<nPatients;i++){
			X[i] = new IntVar[nG[i]][];
			for (int j = 0; j < nG[i]; j++) {
				X[i][j] = new IntVar[nS[i][j]];
				for (int k = 0; k < nS[i][j]; k++) {
					X[i][j][k]= VF.enumerated("Xi,j,k", 0, nPeriodes,solver);
				}
			}
		}
	}
}
