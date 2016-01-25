package choco;

import dev.Probleme;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.ICF;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;
import org.chocosolver.solver.variables.VariableFactory;

public class FonctionObjectif {

	/* Xijk avec
		i nombre de parcours
		j nombre de groupes de soins dans le parcours
		k nombre de soins dans le groupe de soins */
	
	public static IntVar minimiserTemps(IntVar[][][] X, Probleme aResoudre, Solver solver){
		IntVar OBJ = VF.enumerated("OBJ", 0, aResoudre.getHFermeture()-aResoudre.getHOuverture(), solver);

		//On calcule le Min et Max pour chaque patient
		IntVar[] MAX_Patient = VariableFactory.enumeratedArray("MAX_Patient", aResoudre.getnPatients(), 
				aResoudre.getHOuverture(), aResoudre.getHFermeture(), solver);
		IntVar[] MIN_Patient = VariableFactory.enumeratedArray("MIN_Patient", aResoudre.getnPatients(), 
				aResoudre.getHOuverture(), aResoudre.getHFermeture(), solver);
		
		for(int i=0; i<aResoudre.getnPatients(); i++){
			
			//On calcule le Min et Max pour chaque groupe de soins du patient i
			IntVar[] MAX_PatientI_Groupe = VariableFactory.enumeratedArray("MAX_PatientI_Groupe", aResoudre.getnPatients(), 
					aResoudre.getHOuverture(), aResoudre.getHFermeture(), solver);
			IntVar[] MIN_PatientI_Groupe = VariableFactory.enumeratedArray("MIN_PatientI_Groupe", aResoudre.getnPatients(), 
					aResoudre.getHOuverture(), aResoudre.getHFermeture(), solver);
			
			for(int j=0; j<aResoudre.getnG_i()[i]; j++){
				solver.post(ICF.maximum(MAX_PatientI_Groupe[j], X[i][j]));
				solver.post(ICF.minimum(MIN_PatientI_Groupe[j], X[i][j]));
			}
			solver.post(ICF.maximum(MAX_Patient[i], MAX_PatientI_Groupe));
			solver.post(ICF.minimum(MIN_Patient[i], MIN_PatientI_Groupe));
		}
		
		//TODO : vérifier que c'est juste --> possible source de problème
		for(int i=0; i<MIN_Patient.length; i++){
			MIN_Patient[i] = VariableFactory.minus(MIN_Patient[i]);
		}
		
		//On calcule la somme des max et des min
		IntVar SUM[] = VF.enumeratedArray("SUM", 2, aResoudre.getHOuverture()*aResoudre.getnPatients(),
				aResoudre.getHFermeture()*aResoudre.getnPatients(), solver);
		solver.post(ICF.sum(MAX_Patient, SUM[0]));
		solver.post(ICF.sum(MIN_Patient, SUM[1]));
		solver.post(ICF.sum(SUM, OBJ));
		return null;
	}
}
