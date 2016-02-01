package choco;

import dev.Probleme;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.variables.IntVar;

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

	public Constraint[][][] contraintePrecedenceGroupe(){
		Constraint[][][] C4 = new Constraint[this.aResoudre.getnPatients()][][];

		for(int i=0; i<this.aResoudre.getnPatients(); i++){
			C4[i] = new Constraint[this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]-1][];
		
			for(int j=0; j<this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]-1; j++){
				C4[i][j] = new Constraint[this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]];
			
				for (int k=0; k<this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
				
					for(int u=0; u<this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+1]; u++){
						C4[i][j][k] = IntConstraintFactory.arithm(X[i][j][k], "<=", X[i][j+1][u], "-", this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]);
						//C4[i][j][k] = IntConstraintFactory.arithm(X[i][j+1][u], ">=", X[i][j][k], "+", this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]);
					}
				}
			}
		}
		return C4;
	}
}
