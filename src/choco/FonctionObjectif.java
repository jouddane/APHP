package choco;

import dev.Probleme;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;

public class FonctionObjectif {

	/* Xijk avec
		i nombre de parcours
		j nombre de groupes de soins dans le parcours
		k nombre de soins dans le groupe de soins */
	
	public static IntVar minimiserTemps(IntVar[][][] X, Probleme aResoudre, Solver solver){
		IntVar OBJ = VF.enumerated("OBJ", 0, aResoudre.getHFermeture()-aResoudre.getHOuverture(), solver);
		IntVar MAX = VF.enumerated("MAX", aResoudre.getHOuverture(), aResoudre.getHFermeture(), solver);
		IntVar MIN = VF.enumerated("MIN", aResoudre.getHOuverture(), aResoudre.getHFermeture(), solver);
		for(int i=0; i<aResoudre.getnPatients(); i++){
			for(int j=0; j<aResoudre.getnG_i()[i]; j++){
				for(int k=0; i<aResoudre.getnS_ij()[i][j]; i++){
					//MAX = VF.enumerated("MAX", X[i][j][k], MAX, solver); --> Cela construit un domaine de variable, pas bon
					
				}
			}
				
		}
		return null;
	}
}
