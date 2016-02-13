package choco;

import dev.Probleme;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.ICF;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;
import org.chocosolver.solver.variables.Task;

public class Contraintes {

	private Probleme aResoudre;
	private Solver solver;
	private IntVar[][][]  X;


	public Contraintes(Probleme aResoudre, Solver solver, IntVar[][][]  X) {
		this.aResoudre = aResoudre;
		this.solver = solver;
		this.X=X;

	}


	public Probleme getaResoudre() {
		return aResoudre;
	}

	public void setaResoudre(Probleme aResoudre) {
		this.aResoudre = aResoudre;
	}

	public void setSolver(Solver solver) {
		this.solver = solver;
	}

	public Solver getSolver() {
		return solver;
	}

	public void setX(IntVar[][][] x) {
		X = x;
	}

	public IntVar[][][] getX() {
		return X;
	}
	
	/**
	 * Contrainte assurant que tous les soins se font avant l'heure de fermeture
	 * @return
	 */
	public Constraint[][][] contrainteHeureFermeture(){
		Constraint[][][] C2 = new Constraint[this.aResoudre.getnPatients()][][];
		for(int i=0;i<this.aResoudre.getnPatients();i++){
			C2[i] = new Constraint[this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]][];
			for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++) {
				C2[i][j] = new Constraint[this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]];
				for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					C2[i][j][k]= IntConstraintFactory.arithm(this.X[i][j][k], "<=", this.aResoudre.getHFermeture()-this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]);
				}
			}
		}
		return C2;
	}

	/**
	 * COntrainte assurant que tous les soins se font après l'heure d'ouverture
	 * @return
	 */
	public Constraint[][][] contrainteHeureOuverture(){
		Constraint[][][] C3 = new Constraint[this.aResoudre.getnPatients()][][];
		for(int i=0;i<this.aResoudre.getnPatients();i++){
			C3[i] = new Constraint[this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]][];
			for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++) {
				C3[i][j] = new Constraint[this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]];
				for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					C3[i][j][k]= IntConstraintFactory.arithm(this.X[i][j][k], ">=", this.aResoudre.getHOuverture());
				}
			}
		}
		return C3;
	}
	
	
	/**
	 * Contrainte s'assurant que la précédence des groupes est respectée
	 * @return
	 */
	public Constraint[][][] contraintePrecedenceGroupe(){
		Constraint[][][] C4 = new Constraint[this.aResoudre.getnPatients()][][];

		for(int i=0; i<this.aResoudre.getnPatients(); i++){
			C4[i] = new Constraint[this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]-1][];
			for(int j=0; j<this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]-1; j++){
				C4[i][j] = new Constraint[this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]*this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+1]];
				for (int k=0; k<this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					for(int u=0; u<this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+1]; u++){
						int duree = this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k];
						C4[i][j][k*this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+1]+u] = ICF.arithm(this.X[i][j][k], "<=", X[i][j+1][u], "-", duree);
					}
				}
			}
		}
		return C4;
	}



	/**
	 * COntrainte assurant que le patient n'est pas à plusieurs endroits en même temps
	 * @return
	 */
	public Constraint [] contrainteEmpilement(){
		//pour chaque patient je crée une contrainte all different avec tous les soins dans des tableaux IntVar
		Constraint[] C41= new Constraint[aResoudre.getnPatients()];
		for(int i=0; i<this.aResoudre.getnPatients(); i++){
			for(int j=0; j<this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++){
				IntVar [] Soins = new IntVar[this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]-1][j]];
				for (int k=0; k<this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]-1][j]; k++) {
				}
			}
		}
		return C41;
	}

	/* 
	 * Il faut que je fasse un Soin[], Hauteur[], Capacite pour chaque ressource
	 * puis rï¿½soudre solver.post(ICF.cumulative(Soin[], Hauteur[], Capacite, true)) pour chaque ressource;
	 * ï¿½a fait NbRessources Contraintes.
	 * Il faut lire tous les soins et voir quelles ressources ils utilisent. Si ils utilisent R[0] l'ajouter au Soin[0], Hauteur[0] et capacite_0
	 * 
	 */
	
	/**
	 * Contrainte assurant que la capacité des ressources n'est jamais dépassée
	 * @return
	 */
	public Constraint[] contrainteCapaciteRessources(){
		Constraint[] C5 = new Constraint[this.aResoudre.getnRessources()];
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

		// Il faut que je rajoute les contraintes 
		int compteur2=0;
		for (int a=0; a<this.aResoudre.getnRessources(); a++){
			Task[] Soins = new Task[compteurtemp[a]];
			IntVar[] Hauteur = new  IntVar[compteurtemp[a]];
			int compteur1=0;
			for(int i=0;i<this.aResoudre.getnPatients();i++){
				for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++) {
					for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
						// getRessourceUtilisee me renvoie un tableau il faut donc une mï¿½thode qui me dise si la case de a est nulle ou non
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
				C5[compteur2]=IntConstraintFactory.cumulative(Soins, Hauteur, Capacite);
				compteur2++;
			}
			else{
				compteur2++;
			}

		}
		return C5;	
	}

}
