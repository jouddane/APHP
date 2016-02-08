package choco;

import dev.Probleme;

import org.chocosolver.solver.ResolutionPolicy;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.search.solution.ISolutionRecorder;
import org.chocosolver.solver.search.solution.LastSolutionRecorder;
import org.chocosolver.solver.search.solution.Solution;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;
import org.chocosolver.solver.variables.Variable;

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


	public Integer[][][] resout(){
		
		//1. Initialisation du solver
		Solver solver = new Solver();
				
		//2. Initialisation des variables
		//Xi,j,k : modele a modifier pour coherence avec le code
		//X[i].lenght : nombre de groupes de soins du parcours i
		//X[i][j].length : nombre de soins du groupe de soins j du parcours i
		//X[i][j][k] : debut du soin k du groupe de soins j du parcours i
		IntVar[][][] X = new IntVar[this.aResoudre.getnPatients()][][];
		Integer[][][] solInt = new Integer[this.aResoudre.getnPatients()][][];
		for(int i=0; i< this.aResoudre.getnPatients(); i++){
			
			X[i] = new IntVar[this.aResoudre.getnG_i()[aResoudre.getP_i()[i]-1]][];
			solInt[i] = new Integer[this.aResoudre.getnG_i()[aResoudre.getP_i()[i]-1]][];
			
			
			for (int j = 0; j < this.aResoudre.getnG_i()[aResoudre.getP_i()[i]-1]; j++) {
				X[i][j] = new IntVar[this.aResoudre.getnS_ij()[aResoudre.getP_i()[i]-1][j]];
				solInt[i][j] = new Integer[this.aResoudre.getnS_ij()[aResoudre.getP_i()[i]-1][j]];
				
				
				for (int k = 0; k < this.aResoudre.getnS_ij()[aResoudre.getP_i()[i]-1][j]; k++) {
					X[i][j][k]= VF.enumerated("X"+i+","+j+","+k, 0, this.aResoudre.getnPeriodes(),solver);
				}
			}
		}
		
		// 3. Creation et post des contraintes 
		Contraintes contraintes = new Contraintes(this.aResoudre,solver, X);
		Constraint[][][] contrainteHeureOuverture = contraintes.contrainteHeureOuverture();
		Constraint[][][] contrainteHeureFermeture = contraintes.contrainteHeureFermeture();
		Constraint[][][] contraintePrecedenceGroupe = contraintes.contraintePrecedenceGroupe();
		//Constraint[] contrainteCapaciteRessources = contraintes.contrainteCapaciteRessources();
		
		for(int i=0; i< this.aResoudre.getnPatients() ;i++){
			for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]-1]; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]-1][j]; k++) {
					solver.post(contrainteHeureFermeture[i][j][k]);
					solver.post(contrainteHeureOuverture[i][j][k]);
					if(j != this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]-1]-1) {
						//System.out.println("valeur : "+(this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]-1]-1));
						//System.out.println("i = "+i+", j = "+j+", k = "+k);
						solver.post(contraintePrecedenceGroupe[i][j][k]);
					}
				}
			}
		}
		
		/*for (int i=0; i<this.aResoudre.getnRessources(); i++){
			solver.post(contrainteCapaciteRessources[i]);
		}*/
		
        // 4. Definition de la strategie de resolution
        //solver.set(IntStrategyFactory.lexico_LB(x, y));
        
        // 5. Definition de la fonction objectif	
        //IntVar objective = FonctionObjectif.minimiserTemps(X, aResoudre, solver);
        
        // 6. Lancement de la resolution
        //solver.findOptimalSolution(ResolutionPolicy.MINIMIZE, objective);
		System.out.println("Solution ? "+solver.findSolution());
		Solution solution = solver.getSolutionRecorder().getLastSolution();
		for(int i=0; i< this.aResoudre.getnPatients(); i++){
			for (int j = 0; j < this.aResoudre.getnG_i()[aResoudre.getP_i()[i]-1]; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[aResoudre.getP_i()[i]-1][j]; k++) {
					solInt[i][j][k] = solution.getIntVal(X[i][j][k]);
				}
			}
		}
		// 7.  Affichage des statistiques de la resolution
        Chatterbox.printStatistics(solver);
        
        return solInt;
	}
}
