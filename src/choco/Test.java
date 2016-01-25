package choco;

import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.IntConstraintFactory;

import dev.Donnees;
import dev.Probleme;
import dev.Ressource;

public class Test {

	public static void main(String[] args) {
		
		
		//1. Initialisation des donnees a disposition des clients
		Donnees donnees = new Donnees();
		donnees.ajoutRessource(new Ressource(nPeriodes));
		
		//2. Creation du probleme mathematique associee
		Probleme aResoudre = new Probleme(donnees);
		
		//3. Creation de l'outil de resolution du probleme
		Resolution resolution = new Resolution(aResoudre);
		
		//4. Lancement de la resolution d probleme
		resolution.resout();
		
		//5. Affichage de la solution (a implementer)
	}
	
}
