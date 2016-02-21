package choco;

import dev.Donnees;
import dev.Probleme;

import java.io.IOException;
import java.util.ArrayList;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.ICF;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.constraints.LogicalConstraintFactory;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;
import org.chocosolver.solver.variables.Task;
import org.chocosolver.solver.variables.VF;

/**
 *Classe permettant la definition et l'ajout de l'ensemble des contraintes de notre probleme. 
 */
public class Contraintes {

	/**
	 * Le probleme d'optimisation que l'on cherche a resoudre, pour lequel on souhaite definir des contraintes
	 */
	private Probleme aResoudre;
	/**
	 * Le solver choco utilise pour la resolution
	 */
	private Solver solver;
	/**
	 * Les variables choco X[i][j][k] correspondant aux periodes de debut de realisation des soins
	 */
	private IntVar[][][]  X;


	/**
	 * Construit un objet de type Contraintes qui associe les variables choco X a un solver choco solver et un probleme d'optimisation aResoudre. Cet object sera utile par la suite pour definir l'ensemble des contraintes du probleme.
	 * @param aResoudre probleme d'optimisation que l'on cherche a resoudre, pour lequel on souhaite definir des contraintes
	 * @param solver solver choco utilise pour la resolution
	 * @param X variables choco X[i][j][k] correspondant aux periodes de debut de realisation des soins
	 */
	public Contraintes(Probleme aResoudre, Solver solver, IntVar[][][]  X) {
		this.aResoudre = aResoudre;
		this.solver = solver;
		this.X=X;
	}


	/**
	 * getter de la variable d'instance aResoudre
	 * @return 	Le probleme d'optimisation que l'on cherche a resoudre, pour lequel on souhaite definir des contraintes
	 */
	public Probleme getaResoudre() {
		return aResoudre;
	}

	/**
	 * setter de la variable d'instance aResoudre
	 * @param aResoudre probleme d'optimisation que l'on cherche a resoudre, pour lequel on souhaite definir des contraintes
	 */
	public void setaResoudre(Probleme aResoudre) {
		this.aResoudre = aResoudre;
	}

	/**
	 * setter de la variable d'instance solver
	 * @param solver solver choco utilise pour la resolution
	 */
	public void setSolver(Solver solver) {
		this.solver = solver;
	}
	
	/**
	 * getter de la variable d'instance solver
	 * @return Le solver choco utilise pour la resolution
	 */
	public Solver getSolver() {
		return solver;
	}
	
	/**
	 * setter de la variable d'instance X
	 * @param x variables choco X[i][j][k] correspondant aux periodes de debut de realisation des soins
	 */
	public void setX(IntVar[][][] x) {
		X = x;
	}

	/**
	 * getter de la variable d'instance X
	 * @return x variables choco X[i][j][k] correspondant aux periodes de debut de realisation des soins
	 */
	public IntVar[][][] getX() {
		return X;
	}
	
	/**
	 * Ajoute les contraintes permettant de garantir que les soins sont realises avant l'heure de fermeture de l'hopital de jour au solver choco.
	 */
	public void contrainteHeureFermeture(){
		for(int i=0;i<this.aResoudre.getnPatients();i++){
			for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					Constraint C2 = IntConstraintFactory.arithm(this.X[i][j][k], "<=", this.aResoudre.getHFermeture()-this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]);
					solver.post(C2);
				}
			}
		}
	}

	/**
	 * Ajoute les contraintes permettant de garantir que les soins sont realises apres l'heure d'ouverture de l'hopital de jour au solver choco.
	 */
	public void contrainteHeureOuverture(){
		for(int i=0;i<this.aResoudre.getnPatients();i++){
			for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					Constraint C3= IntConstraintFactory.arithm(this.X[i][j][k], ">=", this.aResoudre.getHOuverture());
					solver.post(C3);
				}
			}
		}
	}
	
	
	/**
	 * Ajoute les contraintes permettant de s'assurer que les relations de precedence entre les groupes sont respectees
	 */
	public void contraintePrecedenceGroupe(){
		for(int i=0; i<this.aResoudre.getnPatients(); i++){
			for(int j=0; j<this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]-1; j++){
				for (int k=0; k<this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					for(int u=0; u<this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+1]; u++){
						int duree = this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k];
						Constraint C4 =  ICF.arithm(this.X[i][j][k], "<=", X[i][j+1][u], "-", duree);
						solver.post(C4);
					}
				}
			}
		}
	}

	
	/**
	 * Contrainte assurant que la capacite des ressources n'est jamais depasse malgre l'affectation de soins a des periodes
	 */
	public void contrainteCapaciteRessources(){
		int[] compteurtemp=new int[this.aResoudre.getnRessources()];

		//Premiere boucle pour trouver la taille de Soins[] et leur Hauteur[] 
		for(int i=0;i<this.aResoudre.getnPatients();i++){
			for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					//compteurtemp possede toutes les infos sur le nb de soins par ressource
					compteurtemp= this.aResoudre.updateRessourcesAvecSoin(this.aResoudre.getP_i()[i], j, k, compteurtemp);
					for(int p=0; p<compteurtemp.length; p++) {
					}
				}
			}
		}

		
		for (int a=0; a<this.aResoudre.getnRessources(); a++){
			Task[] Soins = new Task[compteurtemp[a]];
			IntVar[] Hauteur = new  IntVar[compteurtemp[a]];
			int compteur1=0;
			for(int i=0;i<this.aResoudre.getnPatients();i++){
				for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++) {
					for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
						// getRessourceUtilisee me renvoie un tableau il faut donc une m�thode qui me dise si la case de a est nulle ou non
						if(this.aResoudre.getQ_ijkr()[aResoudre.getP_i()[i]][j][k][a]!=0){
							//task(Start,Duration,End)
							Soins[compteur1]=VariableFactory.task(this.X[i][j][k],VariableFactory.fixed(this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k],this.solver),VariableFactory.offset(X[i][j][k],this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]));
							Hauteur[compteur1]= VariableFactory.fixed(this.aResoudre.getQ_ijkr()[aResoudre.getP_i()[i]][j][k][a], solver);
							compteur1++;
						}
					}
				}
			}
			IntVar Capacite = VariableFactory.fixed(this.aResoudre.getCpij_max()[a],solver)	;
			if(Soins.length>0){
				Constraint C1 =IntConstraintFactory.cumulative(Soins, Hauteur, Capacite);
				solver.post(C1);
			}
		}	
	}
	
	/**
	 * Ajoute les contraintes definies par les automates permettant d'ajouter les contraintes sur : les relations de precedence entre les groupes de soins, la duree des soins et la duree des pauses
	 */
	public void contrainteAutomate(){
		//Creation des automates, 1 par parcours
		Automate[] automates = new Automate[aResoudre.getnParcours()];
		
		long t0 = System.currentTimeMillis();
		ArrayList<Integer> automatesCrees = new ArrayList<>();
		for (int i = 0; i < aResoudre.getnPatients(); i++) {
			if(!automatesCrees.contains(aResoudre.getP_i()[i])){
				automates[aResoudre.getP_i()[i]] =  new Automate (aResoudre,aResoudre.getP_i()[i] );
				automatesCrees.add(aResoudre.getP_i()[i]);
			}
		}
		long t1 = System.currentTimeMillis();
		long duree =t1-t0;
		System.out.println("Duree creation des automates : "+duree);
		
	
		
		for (int i = 0; i < aResoudre.getnPatients(); i++) {
			int indiceSoinsMax = automates[aResoudre.getP_i()[i]].getIndicesSoins()[automates[aResoudre.getP_i()[i]].getIndicesSoins().length-1][automates[aResoudre.getP_i()[i]].getIndicesSoins()[automates[aResoudre.getP_i()[i]].getIndicesSoins().length-1].length-1];
			IntVar[] Ai = VF.enumeratedArray("A"+i, aResoudre.getnPeriodes(), 0,indiceSoinsMax , solver);
			
			int sumLijk = 0;
			for (int j = 0; j < aResoudre.getnG_i()[aResoudre.getP_i()[i]]; j++) {
				for (int k = 0; k < aResoudre.getnS_ij()[aResoudre.getP_i()[i]][j]; k++) {
					sumLijk = sumLijk+aResoudre.getL_ijk()[aResoudre.getP_i()[i]][j][k];
				}
			}
			
	        IntVar limitRien = VF.bounded("LIMITRIEN"+i, 0, aResoudre.getnPeriodes()-sumLijk-1, solver);
	        
	        Constraint regular  = ICF.regular(Ai, automates[aResoudre.getP_i()[i]].getFiniteAutomaton());
	        solver.post(regular);
	        solver.post(ICF.count(Automate.RIEN, Ai, limitRien));
			
			

	        
			for (int j = 0; j < aResoudre.getnG_i()[aResoudre.getP_i()[i]]; j++) {
				for (int k = 0; k < aResoudre.getnS_ij()[aResoudre.getP_i()[i]][j]; k++) {
					
				IntVar Vijk = VF.enumerated("V"+i+","+j+","+k, automates[aResoudre.getP_i()[i]].getIndicesSoins()[j][k], automates[aResoudre.getP_i()[i]].getIndicesSoins()[j][k], solver);

					
			    Constraint element1 = ICF.element(Vijk, Ai, X[i][j][k], 0);
			    Constraint element2 = ICF.element(Vijk, Ai, X[i][j][k], -aResoudre.getL_ijk()[aResoudre.getP_i()[i]][j][k]+1);
			     
			    solver.post(element1);
			    solver.post(element2);
				}
			}
		}
	}
	
	/**
	 * Ajoute les contraintes C5 et C6 definies dans le modele au solveur. Permet d'assurer que le temps d'attente entre deux soins pour un patient est superieur a Amin et inferieur a Amax
	 */
	public void contraiteTempsAttente() {
		
	    for (int i = 0; i < aResoudre.getnPatients(); i++) {
			for (int j = 0; j < aResoudre.getnG_i()[aResoudre.getP_i()[i]]-1; j++) {
				for (int k = 0; k <  aResoudre.getnS_ij()[aResoudre.getP_i()[i]][j]; k++) {
					IntVar[][]Y = new IntVar[2][];
					IntVar[][]gY = new IntVar[2][];
					for (int u = 0; u < 2 ; u++) {
						Y[u] = new IntVar[aResoudre.getnS_ij()[aResoudre.getP_i()[i]][u+j]];
						gY[u] = new IntVar[aResoudre.getnS_ij()[aResoudre.getP_i()[i]][u+j]];
						for (int m = 0; m < aResoudre.getnS_ij()[aResoudre.getP_i()[i]][u+j] ; m++) {
							//Initialisation variable de la variable Y correspondant a X[i][u+j][m]-(X[i][j][k]+aResoudre.getL_ijk()[aResoudre.getP_i()[i]][j][k]))
							Y[u][m] = VF.enumerated("Y"+u+","+m, -(aResoudre.getnPeriodes()+aResoudre.getL_ijk()[aResoudre.getP_i()[i]][j][k]), aResoudre.getnPeriodes(), solver);
							
							//Initialisation des variables formant la somme composant Y[u][m–
							IntVar[] somme = new IntVar[2];
					
							somme[0] = X[i][u+j][m];
							IntVar tempVar = VF.enumerated("temp", 0, aResoudre.getnPeriodes()+aResoudre.getL_ijk()[aResoudre.getP_i()[i]][j][k], solver);
							Constraint contrainteSomme = ICF.arithm(tempVar, "=", X[i][j][k], "+", aResoudre.getL_ijk()[aResoudre.getP_i()[i]][j][k]);
							
							somme[1] =  VariableFactory.minus(tempVar);
							
							
							Constraint contrainteInitialisationY =ICF.sum(somme, Y[u][m]);
							
							gY[u][m] =  VF.enumerated("gY"+u+","+m,0, aResoudre.getnPeriodes(), solver);
							
							//Contrainte sur G: est postee automatiquement
							LogicalConstraintFactory.ifThenElse(ICF.arithm(Y[u][m], ">=", 0), ICF.arithm(gY[u][m], "=", Y[u][m]), ICF.arithm(gY[u][m], "=", aResoudre.getnPeriodes()));

							//On poste les contraintes
							solver.post(contrainteSomme);
							solver.post(contrainteInitialisationY);
						}
					}
					
							
					int nbgY =0;
					for (int u = 0; u < gY.length; u++) {
						for (int m = 0; m < gY[u].length; m++) {
							nbgY++;
						}
					}
					
					IntVar[] gYflattened = new IntVar[nbgY];
					int indice = 0;
					for (int u = 0; u < gY.length; u++) {
						for (int m = 0; m < gY[u].length; m++) {
							gYflattened[indice] = gY[u][m];
							indice++;
						}
					}
					
					IntVar Min = VF.enumerated("Min"+i+","+j+","+k, 0, aResoudre.getnPeriodes(), solver);
					Constraint contrainteMin = ICF.minimum(Min, gYflattened);
					
					solver.post(contrainteMin);
					
					Constraint contrainteAmax = ICF.arithm(Min, "<=", aResoudre.getA_MAX());
					Constraint contrainteAmin = ICF.arithm(Min, ">=", aResoudre.getA_MIN());
					
					solver.post(contrainteAmax);
					solver.post(contrainteAmin);
				}
			}
		}
	}
}
