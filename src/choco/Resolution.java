package choco;

import java.Probleme;

import org.chocosolver.solver.ResolutionPolicy;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;

public class Resolution {
	
	private Probleme aResoudre;

	public Resolution(Probleme aResoudre) {
		super();
		this.aResoudre = aResoudre;
	}

	public void resout(){
		
		//1. Initialisation du solver
		Solver solver = new Solver();
				
		//2. Initialisation des variables
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
		
		// 3. Creation et post des contraintes 
		Contraintes contraintes = new Contraintes(this.aResoudre,solver, X);
		Constraint[][][] contrainteHeureFermeture = contraintes.contrainteHeureFermeture();
		Constraint[][][] contrainteHeureOuverture = contraintes.contrainteHeureOuverture();
		
		for(int i=0; i< this.aResoudre.getnPatients() ;i++){
			for (int j = 0; j < this.aResoudre.getnG_i()[i]; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[i][j]; k++) {
					solver.post(contrainteHeureFermeture[i][j][k]);
					solver.post(contrainteHeureOuverture[i][j][k]);
				}
			}
		}
		
        // 4. Definition de la strategie de resolution
        //solver.set(IntStrategyFactory.lexico_LB(x, y));
        
        // 5. Definition de la fonction objective
        ResolutionPolicy objectif ;
        
        // 6. Lancement de la resolution
        //solver.findOptimalSolution(policy);
        // 7.  Affichage des statistiques de la resolution
        Chatterbox.printStatistics(solver);
	}
}
