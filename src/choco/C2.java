package choco;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;

public class C2 {
	
	public static Constraint[][][] C2(Solver solver){
		
		//A remplacer avec donnees probleme provenant d'une autre classe
		int nPatients =0;
		int[] nG = new int[0];
		int[][] nS = new int[0][0];
		int nPeriodes = 0;
		int CSTE = 0;
		
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
		Constraint[][][] C2 = new Constraint[nPatients][][];
		for(int i=0;i<nPatients;i++){
			C2[i] = new Constraint[nG[i]][];
			for (int j = 0; j < nG[i]; j++) {
				C2[i][j] = new Constraint[nS[i][j]];
				for (int k = 0; k < nS[i][j]; k++) {
					C2[i][j][k]= IntConstraintFactory.arithm(X[i][j][k], "<", CSTE);
				}
			}
		}
		return C2;
	}
	
}
