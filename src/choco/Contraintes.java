package choco;

import java.Probleme;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;

public class Contraintes {
	
	private Probleme aResoudre;
	private Solver solver;
	private IntVar[][][]  X;
	
	
	public Contraintes(Probleme aResoudre, Solver solver, IntVar[][][]  X) {
		super();
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
			C2[i] = new Constraint[this.aResoudre.getnG_i()[i]][];
			for (int j = 0; j < this.aResoudre.getnG_i()[i]; j++) {
				C2[i][j] = new Constraint[this.aResoudre.getnS_ij()[i][j]];
				for (int k = 0; k < this.aResoudre.getnS_ij()[i][j]; k++) {
					C2[i][j][k]= IntConstraintFactory.arithm(this.X[i][j][k], "<=", this.aResoudre.getHFermeture()-this.aResoudre.getL_ijk()[i][j][k]);
				}
			}
		}
		return C2;
	}
	
	public Constraint[][][] contrainteHeureOuverture(){
		Constraint[][][] C3 = new Constraint[this.aResoudre.getnPatients()][][];
		for(int i=0;i<this.aResoudre.getnPatients();i++){
			C3[i] = new Constraint[this.aResoudre.getnG_i()[i]][];
			for (int j = 0; j < this.aResoudre.getnG_i()[i]; j++) {
				C3[i][j] = new Constraint[this.aResoudre.getnS_ij()[i][j]];
				for (int k = 0; k < this.aResoudre.getnS_ij()[i][j]; k++) {
					C3[i][j][k]= IntConstraintFactory.arithm(this.X[i][j][k], ">=", this.aResoudre.getHOuverture());
				}
			}
		}
		return C3;
	}
}
