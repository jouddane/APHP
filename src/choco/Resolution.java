package choco;

import java.Probleme;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
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

	public void resout(){
		
		//Initialisation du solver
		Solver solver = new Solver();
		
		
		
		
		
				
		//Initialisation des variables
		//Xi,j,k : modele a modifier pour coherence avec le code
		IntVar[][][]  X= new IntVar[this.aResoudre.getnPatients()][][];
		for(int i=0; i< this.aResoudre.getnPatients() ;i++){
			X[i] = new IntVar[this.aResoudre.getnG_i()[i]][];
			for (int j = 0; j < this.aResoudre.getnG_i()[i]; j++) {
				X[i][j] = new IntVar[this.aResoudre.getnS_ij()[i][j]];
				for (int k = 0; k < this.aResoudre.getnS_ij()[i][j]; k++) {
					X[i][j][k]= VF.enumerated("Xi,j,k", 0, this.aResoudre.getnPeriodes(),solver);
				}
			}
		}
		
		// 3. Create and post constraints by using constraint factories
		Contraintes contraintes = new Contraintes(this.aResoudre);
		Constraint[][][] contrainteHeureFermeture = contraintes.contrainteHeureFermeture(solver, X);
		Constraint[][][] contrainteHeureOuverture = contraintes.contrainteHeureOuverture(solver, X);
		
		for(int i=0; i< this.aResoudre.getnPatients() ;i++){
			for (int j = 0; j < this.aResoudre.getnG_i()[i]; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[i][j]; k++) {
					solver.post(contrainteHeureFermeture[i][j][k]);
					solver.post(contrainteHeureOuverture[i][j][k]);
				}
			}
		}
        // 4. Define the search strategy
        solver.set(IntStrategyFactory.lexico_LB(x, y));
        // 5. Launch the resolution process
        solver.findSolution();
        //6. Print search statistics
        Chatterbox.printStatistics(solver);
	}
}
