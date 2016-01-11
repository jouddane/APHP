package choco;

import java.Probleme;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;

public class Contraintes {
	
	public static Constraint[][][] ContrainteHeureFermeture(Solver solver, Probleme p, IntVar[][][] X){
		
		int nPatients =p.getnPatients();
		int[] nG = p.getnG_i();
		int[][] nS = p.getnS_ij();
		int nPeriodes = p.getnPeriodes();
		
		
		Constraint[][][] C2 = new Constraint[nPatients][][];
		for(int i=0;i<nPatients;i++){
			C2[i] = new Constraint[nG[i]][];
			for (int j = 0; j < nG[i]; j++) {
				C2[i][j] = new Constraint[nS[i][j]];
				for (int k = 0; k < nS[i][j]; k++) {
					C2[i][j][k]= IntConstraintFactory.arithm(X[i][j][k], "<=", p.getHFermeture()-p.getL_ijk()[i][j][k]);
				}
			}
		}
		return C2;
	}
	
}
