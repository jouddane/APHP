package choco;

import java.util.ArrayList;

import org.chocosolver.solver.constraints.nary.automata.FA.FiniteAutomaton;

import dev.CoupleStringInt;
import dev.Donnees;
import dev.GroupeSoins;
import dev.Parcours;
import dev.Ressource;
import dev.Soin;

/*
 * Classe representant les automates utilisees pour representer la succession de soins (relation de precedence, duree, pauses, etc) que les patients doivent suivre en fonction de leur parcours
 */

public class Automate {

	private Parcours p;
	
	public Automate(Parcours p){
		int RIEN = -1;
		int PAUSE = 0;
		int[][] indiceSoins = new int[p.getGroupeSoins().length][];
		int i=0;
		int indiceSoin =0;
		while ( i < indiceSoins.length) {
			indiceSoins[i]=new int[p.getGroupeSoins()[i].getSoins().length];
			int j=0;
			while (j < indiceSoins[i].length) {
				indiceSoins[i][j]=indiceSoin;
				j++;
				indiceSoin++;
			}
			i++;
		}
		

        FiniteAutomaton auto = new FiniteAutomaton();
        int debut = auto.addState();
        auto.setInitialState(debut);
        int fin = auto.addState();
        auto.setFinal(debut);
        auto.setFinal(fin);

        auto.addTransition(debut, debut, RIEN);
        auto.addTransition(debut, fin, RIEN);
        auto.addTransition(fin, fin, RIEN);
        
        /*
        int[][][][] E = new int[p.getNombreDeGroupes()][][][];
        int[] EInt = new int [p.getNombreDeGroupes()-1];
        
        for (int j = 0; j < E.length; j++) {
        	E[j] = new int[p.getGroupeSoins()[j].getSoins().length][][];
        	for (int j2 = 0; j2 < E[0].length; j2++) {
        		E[j][j2] = new int [factoriel(E[j].length)/factoriel(E[j].length-j2)][];
        		for (int k = 0; k < E[0][0].length; k++) {
					E[j][j2][k] = new int[p.getGroupeSoins()[j].getSoins()[].]
				}
			}	
		}
        
        for (int j = 0; j < E01.length; j++) {
			auto.addTransition(debut, E01[j][0], indiceSoins[0][j]);
		}
        for (int j = 0; j < E01.length; j++) {
			for (int j2 = 0; j2 < E01[0].length-1; j2++) {
				auto.addTransition(E01[j][j2], E01[j][j2+1], indiceSoins[0][j]);
			}
		}
        
        int[][] E02 = new int[p.getGroupeSoins()[0].getSoins().length][];
        
        */
        
        ArrayList<CoupleIndiceSoinDuree> listPossibilitesGroupe1 = new ArrayList();
        for (int j = 0; j < indiceSoins[0].length; j++) {
			listPossibilitesGroupe1.add(new CoupleIndiceSoinDuree(indiceSoins[0][j],p.getGroupeSoins()[0].getSoin(j).getDuree()));
		}
        
        Etat etatInitialGroupe1 = new Etat(listPossibilitesGroupe1);
       
        Etat[][] etatsNiveau = new Etat[indiceSoins[0].length][];
        
        for (int j = 0; j < etatsNiveau.length; j++) {
        	etatsNiveau[j] = new Etat[factoriel(indiceSoins[j].length)/factoriel(indiceSoins[j].length-j-1)];
            
		}
        
        
        if(!etatInitialGroupe1.estUnEtatFinal()){
        	int j=0;
            for (CoupleIndiceSoinDuree possibiliteDuree : etatInitialGroupe1.getListPossibilite()) {
            	etatsNiveau[0][j] = etatInitialGroupe1.etatSuivant(possibiliteDuree.getIndiceSoin());
            	int E[] = new int[possibiliteDuree.getDuree()];
            	for (int k = 0; k < E.length; k++) {
					E[k] = auto.addState();
				}
            	auto.addTransition(debut, E[0], possibiliteDuree.getIndiceSoin());
            	for (int k = 0; k < E.length-1; k++) {
					auto.addTransition(E[k], E[k]+1, possibiliteDuree.getIndiceSoin());
				}
    			j++;
    		}
        }
        
        Etat etatCourant;
        
        for (int j = 0; j < etatsNiveau.length; j++) {
        	int nb=0;
        	for (int j2 = 0; j2 < etatsNiveau[j].length; j2++) {
        		etatCourant = etatsNiveau[j][j2];
        		for (CoupleIndiceSoinDuree possibiliteDuree : etatCourant.getListPossibilite()) {
        			etatsNiveau[j+1][nb] = etatCourant.etatSuivant(possibiliteDuree.getIndiceSoin());
               		int E[] = new int[possibiliteDuree.getDuree()];
               		for (int k = 0; k < E.length; k++) {
               			E[k] = auto.addState();
               		}
               		auto.addTransition(debut, E[0], possibiliteDuree.getIndiceSoin());
               		for (int k = 0; k < E.length-1; k++) {
               			auto.addTransition(E[k], E[k]+1, possibiliteDuree.getIndiceSoin());
               		}
               		nb++;
       			}	
    	   }
       }
        
        
        System.out.printf("%s\n", auto.toDot());

	}
	
	public static int factoriel(int a){
		if(a==0){
			return 1;
		}
		else{
			return a*factoriel(a-1);
		}
	}

	public Parcours getP() {
		return p;
	}

	public void setP(Parcours p) {
		this.p = p;
	}
	
	public static void main(String[] args) {
	
		//new Automate(P1);
	}
	
}
