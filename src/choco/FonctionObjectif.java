package choco;

import java.Probleme;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;

public class FonctionObjectif {

	public static IntVar minimiserTemps(IntVar[][][] X, Probleme aResoudre, Solver solver){
		IntVar MAX;
		for(int i=0; i<aResoudre.getnPatients(); i++){
			for(int j=0; j<aResoudre.getnG_i()[i]; j++){
				for(int k=0; i<aResoudre.getnS_ij()[i][j]; i++){
					MAX = VF.enumerated("MAX", X[i][j][k], MAX, solver);
				}
			}
				
		}
		return null;
	}
}
