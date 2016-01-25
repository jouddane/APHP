package choco;

import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.IntConstraintFactory;

import dev.Donnees;
import dev.GroupeSoins;
import dev.Parcours;
import dev.Probleme;
import dev.Ressource;
import dev.Soin;

public class Test {

	public static void main(String[] args) {
		
		
		//1. Initialisation des donnees a disposition des clients
		Donnees donnees = new Donnees();
		donnees.ajoutRessource(new Ressource(nPeriodes));
		
		Soin S1G1P1 = new Soin
		GroupeSoins G1P1 = new GroupeSoins();
		Parcours P1 = new Parcours();
		
		//2. Creation du probleme mathematique associee
		Probleme aResoudre = new Probleme(donnees);
		
		//3. Creation de l'outil de resolution du probleme
		Resolution resolution = new Resolution(aResoudre);
		
		//4. Lancement de la resolution d probleme
		resolution.resout();
		
		//5. Affichage de la solution (a implementer)
	}
	
}
