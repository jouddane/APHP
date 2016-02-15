package choco;

import dev.Probleme;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.ICF;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;
import org.chocosolver.solver.variables.VariableFactory;

/**
 * 
 * Classe definissant les fonctions objectif utilisees pour la resolution du probleme
 */
public class FonctionObjectif {


	//Le probleme d'optimisation que l'on cherche a resoudre, pour lequel on souhaite definir une fonction Objectif
	private Probleme aResoudre;
	//Le solver choco utilise pour la resolution
	private Solver solver;
	//Les variables choco X[i][j][k] correspondant aux periodes de debut de realisation des soins
	private IntVar[][][]  X;
	
	/**
	 * Construit un objet de type FonctionObjectif qui associe les variables choco X a un solver choco solver et un probleme d'optimisation aResoudre. Cet object sera utile par la suite pour definir une fonction objectif au probleme.
	 * @param aResoudre probleme d'optimisation que l'on cherche a resoudre, pour lequel on souhaite definir une fonction objectif
	 * @param solver solver choco utilise pour la resolution
	 * @param X variables choco X[i][j][k] correspondant aux periodes de debut de realisation des soins
	 */
	public FonctionObjectif(Probleme aResoudre, Solver solver, IntVar[][][]  X) {
		this.aResoudre = aResoudre;
		this.solver = solver;
		this.X=X;
	}


	/**
	 * getter de la variable d'instance aResoudre
	 * @return 	Le probleme d'optimisation que l'on cherche a resoudre, pour lequel on souhaite definir une fonction objectif
	 */
	public Probleme getaResoudre() {
		return aResoudre;
	}

	/**
	 * setter de la variable d'instance aResoudre
	 * @param aResoudre probleme d'optimisation que l'on cherche a resoudre, pour lequel on souhaite definir  une fonction objectif
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
	 * 
	 * Creation de la fonction objectif permettant de minimiser le sejour des patients dans les hopitaux
	 * @return OBJ variable correspondant a la somme des durees des sejours des patients en hopital de jour que l'on va chercher a minimiser
	 */
	public IntVar minimiserTemps(){
		IntVar OBJ = VF.enumerated("OBJ", 0, (aResoudre.getnPeriodes()-1)*aResoudre.getnPatients(), solver);

		//On calcule le Min et Max pour chaque patient
		IntVar[] MAX_Patient = VariableFactory.enumeratedArray("MAX_Patient", aResoudre.getnPatients(), 
				0, aResoudre.getnPeriodes()-1, solver);
		IntVar[] MIN_Patient = VariableFactory.enumeratedArray("MIN_Patient", aResoudre.getnPatients(), 
				0, aResoudre.getnPeriodes()-1, solver);
		
		for(int i=0; i<aResoudre.getnPatients(); i++){
			
			//On calcule le Min et Max pour chaque groupe de soins du patient i
			IntVar[] MAX_PatientI_Groupe = VariableFactory.enumeratedArray("MAX_PatientI_Groupe", aResoudre.getnG_i()[aResoudre.getP_i()[i]], 
					0, aResoudre.getnPeriodes()-1, solver);
			IntVar[] MIN_PatientI_Groupe = VariableFactory.enumeratedArray("MIN_PatientI_Groupe", aResoudre.getnG_i()[aResoudre.getP_i()[i]], 
					0, aResoudre.getnPeriodes()-1, solver);
			
			for(int j=0; j<aResoudre.getnG_i()[aResoudre.getP_i()[i]]; j++){
				solver.post(ICF.maximum(MAX_PatientI_Groupe[j], X[i][j]));
				solver.post(ICF.minimum(MIN_PatientI_Groupe[j], X[i][j]));
			}
			solver.post(ICF.maximum(MAX_Patient[i], MAX_PatientI_Groupe));
			solver.post(ICF.minimum(MIN_Patient[i], MIN_PatientI_Groupe));
		}
		
		//TODO : vérifier que c'est juste --> possible source de problème
		//Ici on veut faire une différence donc on prend l'opposé de chaque somme
		IntVar[] MINUS_MIN_Patient = new IntVar[MIN_Patient.length];
		for(int i=0; i<MIN_Patient.length; i++){
			MINUS_MIN_Patient[i] = VariableFactory.minus(MIN_Patient[i]);
		}
		
		//On calcule la somme des max et des min
		IntVar[] SUM = new IntVar[2];
		SUM[0] = VF.enumerated("SUM_MAX", 0, (aResoudre.getnPeriodes()-1)*aResoudre.getnPatients(), solver);
		SUM[1] = VF.enumerated("SUM_MIN", -(aResoudre.getnPeriodes()-1)*aResoudre.getnPatients(), 0, solver);
		
		solver.post(ICF.sum(MAX_Patient, SUM[0]));
		solver.post(ICF.sum(MINUS_MIN_Patient, SUM[1]));
		solver.post(ICF.sum(SUM, OBJ));
		return OBJ;
	}
}
