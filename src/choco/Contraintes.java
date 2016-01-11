package choco;

import java.Probleme;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;

public class Contraintes {

	private Probleme aResoudre;


	public Contraintes(Probleme aResoudre) {
		super();
		this.aResoudre = aResoudre;
	}

	public Probleme getaResoudre() {
		return aResoudre;
	}

	public void setaResoudre(Probleme aResoudre) {
		this.aResoudre = aResoudre;
	}

	public Constraint[][][] contrainteHeureFermeture(Solver solver, IntVar[][][] X){
		Constraint[][][] C2 = new Constraint[this.aResoudre.getnPatients()][][];
		for(int i=0;i<this.aResoudre.getnPatients();i++){
			C2[i] = new Constraint[this.aResoudre.getnG_i()[i]][];
			for (int j = 0; j < this.aResoudre.getnG_i()[i]; j++) {
				C2[i][j] = new Constraint[this.aResoudre.getnS_ij()[i][j]];
				for (int k = 0; k < this.aResoudre.getnS_ij()[i][j]; k++) {
					C2[i][j][k]= IntConstraintFactory.arithm(X[i][j][k], "<=", this.aResoudre.getHFermeture()-this.aResoudre.getL_ijk()[i][j][k]);
				}
			}
		}
		return C2;
	}

	public Constraint[][][] contrainteHeureOuverture(Solver solver, IntVar[][][] X){
		Constraint[][][] C3 = new Constraint[this.aResoudre.getnPatients()][][];
		for(int i=0;i<this.aResoudre.getnPatients();i++){
			C3[i] = new Constraint[this.aResoudre.getnG_i()[i]][];
			for (int j = 0; j < this.aResoudre.getnG_i()[i]; j++) {
				C3[i][j] = new Constraint[this.aResoudre.getnS_ij()[i][j]];
				for (int k = 0; k < this.aResoudre.getnS_ij()[i][j]; k++) {
					C3[i][j][k]= IntConstraintFactory.arithm(X[i][j][k], ">=", this.aResoudre.getHOuverture());
				}
			}
		}
		return C3;
	}

	public Constraint[][][] contraintePrecedenceGroupe(Solver solver, IntVar[][][] X){
		Constraint[][][] C4 = new Constraint[this.aResoudre.getnPatients()][][];
		for(int i=0; i<this.aResoudre.getnPatients(); i++){
			C4[i] = new Constraint[this.aResoudre.getnG_i()[i]-1][];
			for(int j=0; j<this.aResoudre.getnG_i()[i]; j++){
				C4[i][j] = new Constraint[this.aResoudre.getnS_ij()[i][j]];
				for (int k=0; k<this.aResoudre.getnS_ij()[i][j]; k++) {
					for(int u=0; u<this.aResoudre.getnS_ij()[i][j+1]; u++){
						C4[i][j][k] = IntConstraintFactory.arithm(X[i][j][k], "<=", X[i][j+1][u], "-", this.aResoudre.getL_ijk()[i][j][i]);
					}
				}
			}
		}
		return null;
	}
}
