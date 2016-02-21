package choco;

import dev.Probleme;
import visu.VisuCheckeur;
import visu.VisuSolution;

import org.chocosolver.solver.ResolutionPolicy;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.solution.Solution;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;
import org.jfree.ui.RefineryUtilities;

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
	public void resout(){
		
		//1. Initialisation du solver
		Solver solver = new Solver();
				
		//2. Initialisation des variables
		//Xi,j,k : modele a modifier pour coherence avec le code
		//X[i].lenght : nombre de groupes de soins du parcours i
		//X[i][j].length : nombre de soins du groupe de soins j du parcours i
		//X[i][j][k] : debut du soin k du groupe de soins j du parcours i
		IntVar[][][] X = new IntVar[this.aResoudre.getnPatients()][][];
		Integer[][][] solInt = new Integer[this.aResoudre.getnPatients()][][];
		int nombreDeSoins = 0;
		
		
		for(int i=0; i< this.aResoudre.getnPatients(); i++){
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
		contraintes.contraiteTempsAttente();
		//contraintes.contrainteAutomate();
		//contraintes.contrainteTempsEntreSoin();
		
        // 4. Definition de la strategie de resolution
         // solver.set(IntStrategyFactory.lexico_LB(XFlattened));        
        solver.set(IntStrategyFactory.activity(XFlattened,0));
        //solver.set(IntStrategyFactory.impact(XFlattened,0));
		//solver.set(IntStrategyFactory.(XFlattened));
        
		
        // 5. Definition de la fonction objectif	
		FonctionObjectif fonctionObjectif = new FonctionObjectif(aResoudre, solver, X);
        IntVar objective = fonctionObjectif.minimiserTemps();
        
        
        System.out.println("Lancement de la resolution");
        long t0 = System.currentTimeMillis();
		
        // 6. Lancement de la resolution
        //solver.findOptimalSolution(ResolutionPolicy.MINIMIZE, objective);
        solver.findSolution();
        
       
        
        // 7.  Affichage des statistiques de la resolution
        Chatterbox.printStatistics(solver);
        
       
		Solution solution = solver.getSolutionRecorder().getSolutions().get(0);
	
		
		for(int i=0; i< this.aResoudre.getnPatients(); i++){
			for (int j = 0; j < this.aResoudre.getnG_i()[aResoudre.getP_i()[i]]; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[aResoudre.getP_i()[i]][j]; k++) {
					solInt[i][j][k] = solution.getIntVal(X[i][j][k]);
					System.out.println("X["+i+"]["+j+"]["+k+"] = "+solInt[i][j][k]);
				}
			}
		}
		
		int taillePeriode =24*60/aResoudre.getnPeriodes();
		for (int i=0; i<aResoudre.getnRessources();i++){
			final VisuCheckeur Checkeur = new VisuCheckeur("Checkeur ressource "+i,solInt, aResoudre, i, taillePeriode);
			Checkeur.pack();
			RefineryUtilities.centerFrameOnScreen(Checkeur);
			Checkeur.setVisible(true);
		}
		VisuSolution Gantt = new VisuSolution("Journee", solInt, aResoudre,taillePeriode);
		Gantt.pack();
		RefineryUtilities.centerFrameOnScreen(Gantt);
		Gantt.setVisible(true);
		
		System.out.println("Checker des solutions :");
		
		maths.Solution verifierSol = new maths.Solution(solInt, aResoudre);
		System.out.println("Ouverture? "+verifierSol.verifieContrainteHeureOuverture());
		System.out.println("Fermeture? "+verifierSol.verifieContrainteHeureFermeture());
		System.out.println("Precedence? "+verifierSol.verifieContraintePrecedenceGroupe());
		System.out.println("Capacite max? "+verifierSol.verifieContrainteRessources());
        System.out.println("Temps Attente Min? "+verifierSol.verifieContrainteAttentePatientsMin());
        System.out.println("Temps Attente Max? "+verifierSol.verifieContrainteAttentePatientsMax());
		System.out.println("Toutes les contraintes sont verifiees ?"+verifierSol.verifieContraintes());
	}
}
