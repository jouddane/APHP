package choco;

import dev.Probleme;

import org.chocosolver.solver.ResolutionPolicy;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.solution.Solution;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;

public class Resolution {
	
    /**
     * Le probleme a resoudre
     */
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

	/** 
	 * @return la solution du probleme contraint
	 */
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
		System.out.println("Nombre de patients: "+aResoudre.getnPatients());
		int nombreDeSoins = 0;
		for(int i=0; i< this.aResoudre.getnPatients(); i++){
			System.out.println("i = "+i+" : "+this.aResoudre.getnG_i()[aResoudre.getP_i()[i]]);
			X[i] = new IntVar[this.aResoudre.getnG_i()[aResoudre.getP_i()[i]]][];
			solInt[i] = new Integer[this.aResoudre.getnG_i()[aResoudre.getP_i()[i]]][];			
			
			for (int j = 0; j < this.aResoudre.getnG_i()[aResoudre.getP_i()[i]]; j++) {
				X[i][j] = new IntVar[this.aResoudre.getnS_ij()[aResoudre.getP_i()[i]][j]];
				solInt[i][j] = new Integer[this.aResoudre.getnS_ij()[aResoudre.getP_i()[i]][j]];
				
				
				for (int k = 0; k < this.aResoudre.getnS_ij()[aResoudre.getP_i()[i]][j]; k++) {
					X[i][j][k]= VF.enumerated("X"+i+","+j+","+k, 0, this.aResoudre.getnPeriodes(),solver);
					nombreDeSoins++;
				}
			}
		}
		
		IntVar[] XFlattened = new IntVar[nombreDeSoins];
		int indiceTemp = 0;
		for(int i=0; i< this.aResoudre.getnPatients(); i++){
            for (int j = 0; j < this.aResoudre.getnG_i()[aResoudre.getP_i()[i]]; j++) {
                for (int k = 0; k < this.aResoudre.getnS_ij()[aResoudre.getP_i()[i]][j]; k++) {
                    XFlattened[indiceTemp] = X[i][j][k];
                    indiceTemp++;
                }
            }
        }
		
		// 3. Creation et post des contraintes 
		Contraintes contraintes = new Contraintes(this.aResoudre,solver, X);
		contraintes.contrainteHeureOuverture();
		contraintes.contrainteHeureFermeture();
		contraintes.contraintePrecedenceGroupe();
		contraintes.contrainteCapaciteRessources();
		contraintes.contrainteAutomate(false);
		
        // 4. Definition de la strategie de resolution
        solver.set(IntStrategyFactory.lexico_LB(XFlattened));
        
        // 5. Definition de la fonction objectif	
		FonctionObjectif fonctionObjectif = new FonctionObjectif(aResoudre, solver, X);
        IntVar objective = fonctionObjectif.minimiserTemps();
        
        // 6. Lancement de la resolution
        solver.findOptimalSolution(ResolutionPolicy.MINIMIZE, objective);
        
        // 7.  Affichage des statistiques de la resolution
        Chatterbox.printStatistics(solver);
        
        //solver.findAllSolutions();
		//System.out.println("Solution ? "+solver.findSolution());
		Solution solution = solver.getSolutionRecorder().getSolutions().get(0);
	
		
		for(int i=0; i< this.aResoudre.getnPatients(); i++){
			for (int j = 0; j < this.aResoudre.getnG_i()[aResoudre.getP_i()[i]]; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[aResoudre.getP_i()[i]][j]; k++) {
					solInt[i][j][k] = solution.getIntVal(X[i][j][k]);
				}
			}
		}
        return solInt;
	}
}
