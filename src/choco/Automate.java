package choco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.chocosolver.solver.constraints.nary.automata.FA.FiniteAutomaton;

import dev.CoupleStringInt;
import dev.Date;
import dev.Donnees;
import dev.GroupeSoins;
import dev.Parcours;
import dev.Patient;
import dev.Probleme;
import dev.Ressource;
import dev.Soin;

/**
 * Classe representant les automates utilisees pour definir les contraintes sur : les relations de precedence entre les groupes de soins, la duree des soins et la duree des pauses. 
 * Il est necessaire de creer un automate pour chaque parcours.
 */

public class Automate {

	/**
	 * L'entier 0 est utilise pour representer la tache correspondant a RIEN dans l'automate
	 */
    public static final Integer RIEN = 0;
    
	/**
	 * Represente les valeures entieres utilisees pour representer les soins sous forme de transition dans l'automate
	 */
	private int[][] indicesSoins;
	/**
	 * Represente le FiniteAutomaton utilise pour definir les contraintes
	 */
	private FiniteAutomaton finiteAutomaton;
	
	/**
	 * Permet de creer l'automate associe au parcours d'indice i_parcours dans prob.getParcours()
	 * @param prob le probleme d'optimisation que l'on cherche a resoudre
	 * @param i_parcours indice du parcours pour lequel on souhaite associe un automate dans prob.getParcours()
	 * @param exporter true si on souhaite exporter l'automate
	 */
	public Automate(Probleme prob, int i_parcours){

		//Initialisation des tache (transitions) pause et rien
		int PAUSE = 1;
		
		//Association d'indices a chaque soins representant des transitions entre des etats
		//IndiceSoins[i][j] represent l'indice utilisee dans l'automate pour representer le soin j du groupe de soin i du parcours p 
		int[][] indiceSoins = new int[prob.getnG_i()[i_parcours]][];
		int j=0;
		int indiceSoin =2;
		while ( j < indiceSoins.length) {
			indiceSoins[j]=new int[prob.getnS_ij()[i_parcours][j]];
			int k=0;
			while (k < indiceSoins[j].length) {
				indiceSoins[j][k]=indiceSoin;
				k++;
				indiceSoin++;
			}
			j++;
		}
		this.indicesSoins = indiceSoins;
		
		//Creation de l'automate
        FiniteAutomaton auto = new FiniteAutomaton();
        
        //Initialisation des etats de debut et de fin de l'automate
        int debut = auto.addState();
        auto.setInitialState(debut);
        int fin = auto.addState();
        auto.setFinal(debut);
        auto.setFinal(fin);

        //Ajout des transitions entre les etats debut et fin
        auto.addTransition(debut, debut, RIEN);
        auto.addTransition(fin, fin, RIEN);
        
        //Initialisation de tous les etats possibles pris par l'automate. Chaque etat correspond a un ensemble de soins realisables pour un groupe de soins donne.
        Etat[][][] etatsNiveau = new Etat[prob.getnG_i()[i_parcours]][][];
        for (int indiceGroupe = 0; indiceGroupe < prob.getnG_i()[i_parcours]; indiceGroupe++) {
        	etatsNiveau[indiceGroupe] = new Etat[prob.getnS_ij()[i_parcours][indiceGroupe]+1][];
			for (int indiceNiveau = 0; indiceNiveau < etatsNiveau[indiceGroupe].length; indiceNiveau++) {
				int nbEtatsNiveauIndiceNiveau = factoriel(prob.getnS_ij()[i_parcours][indiceGroupe])/factoriel(prob.getnS_ij()[i_parcours][indiceGroupe]-indiceNiveau);
				etatsNiveau[indiceGroupe][indiceNiveau]= new Etat[nbEtatsNiveauIndiceNiveau];
				for (int k = 0; k < nbEtatsNiveauIndiceNiveau; k++) {
					etatsNiveau[indiceGroupe][indiceNiveau][k]= new Etat();
				}
			}
		}
        
        //Initialisation d un tableau repertoriant les derniers etats obtenus pour le groupe precedent
        int[] etatsFinauxAncienGroupeInt = new int[1];
        etatsFinauxAncienGroupeInt[0] = debut; 
        
        //On itere sur l'ensemble des groupes pour construire l'automate
        for(int indiceGroupe = 0; indiceGroupe<prob.getnG_i()[i_parcours]; indiceGroupe++){
        	
            //Creation d'une liste regroupant l'ensemble de transitions possibles (indices des soins realisables) dans le groupe de soins indiceGroupe
        	ArrayList<CoupleIndiceSoinDuree> listPossibilitesGroupe = new ArrayList<CoupleIndiceSoinDuree>();
        	for (int j2 = 0; j2 < indiceSoins[indiceGroupe].length; j2++) {
        		listPossibilitesGroupe.add(new CoupleIndiceSoinDuree(indiceSoins[indiceGroupe][j2],prob.getL_ijk()[i_parcours][indiceGroupe][j2]));
        	}

        	//Initialisation du premier etat composant l'ensemble des etats atteignables a partir des transitions associees aux soins realisable du groupe de soins indiceGroupe 
    		etatsNiveau[indiceGroupe][0][0] = new Etat(listPossibilitesGroupe,auto.addState());

    		//Creation de la transition entre l'etat initial debut et le premier etat de l'ensemble des etats atteignables a partir des transitions associees aux soins realisable du groupe de soins indiceGroupe 
        	if(indiceGroupe == 0){
            	auto.addTransition(etatsFinauxAncienGroupeInt[0],etatsNiveau[indiceGroupe][0][0].getEtatInt() , RIEN);
        	}
        	
        	//Creation transitions entre les derniers etats atteignables par les transitions associees aux soins du groupe de soins indiceGroupe-1 et le premier etat atteignable du groupe de soins indiceGroupe par une transition PAUSE
        	else{
        		for (int j2 = 0; j2 < etatsFinauxAncienGroupeInt.length; j2++) {
       				auto.addTransition(etatsFinauxAncienGroupeInt[j2],etatsNiveau[indiceGroupe][0][0].getEtatInt() , PAUSE);
       			}
        	}
        	
        	//Creation d'un etatCourant representant l'etat pour lequel on souhaite construire les etats successeurs
        	Etat etatCourant;
        	for (int indiceNiveau = 0; indiceNiveau < etatsNiveau[indiceGroupe].length-1; indiceNiveau++) {
        		int indiceEtatsNiveauSuivantIndiceNiveau=0;
        		for (int indiceNoeudNiveauIndiceNiveau = 0; indiceNoeudNiveauIndiceNiveau < etatsNiveau[indiceGroupe][indiceNiveau].length; indiceNoeudNiveauIndiceNiveau++) {
        			etatCourant =etatsNiveau[indiceGroupe][indiceNiveau][indiceNoeudNiveauIndiceNiveau];
        			ArrayList<CoupleIndiceSoinDuree> listeSoinsRealisables = etatCourant.getListPossibilite();
        			for (int j2 = 0; j2 < listeSoinsRealisables.size(); j2++) {
        				etatsNiveau[indiceGroupe][indiceNiveau+1][indiceEtatsNiveauSuivantIndiceNiveau] = etatCourant.etatSuivant(listeSoinsRealisables.get(j2).getIndiceSoin());
                		int E[];
                		if(indiceNiveau>0){
                			E = new int[listeSoinsRealisables.get(j2).getDuree()+1];
                     		for (int k = 0; k < E.length; k++) {
                     			E[k] = auto.addState();
                     		}
                			int[][] EPause = new int[prob.getA_MAX() - prob.getA_MIN() +1][];
                			int etatPostSoins = auto.addState();
                			for (int k = 0; k < EPause.length; k++) {
                				EPause[k] = new int[prob.getA_MIN() -1 +k];
               					EPause[k][0] = etatPostSoins;
               					for (int u = 1; u < EPause[k].length; u++) {
               						EPause[k][u]= auto.addState();
               						auto.addTransition(EPause[k][u-1],EPause[k][u], PAUSE);
               					}
               				}
                	
                			int[] etatsFinauxAncienNiveauInt = new int[EPause.length];
                			for (int k = 0; k < EPause.length; k++) {
                				etatsFinauxAncienNiveauInt[k] = EPause[k][EPause[k].length-1];
                			}
                		
               				auto.addTransition(etatCourant.getEtatInt(),etatPostSoins,PAUSE);
                		 		
                        
               				for (int k = 0; k < EPause.length; k++) {
               					auto.addTransition(etatsFinauxAncienNiveauInt[k], E[0], PAUSE);
               				}
                		}
                		else{
                			E = new int[listeSoinsRealisables.get(j2).getDuree()];
                     		for (int k = 0; k < E.length; k++) {
                     			E[k] = auto.addState();
                     		}
                     		auto.addTransition(etatCourant.getEtatInt(), E[0], listeSoinsRealisables.get(j2).getIndiceSoin());
                		}
                        for (int k = 0; k < E.length-1; k++) {
            				auto.addTransition(E[k], E[k+1], listeSoinsRealisables.get(j2).getIndiceSoin());
            			}
                        
                        etatsNiveau[indiceGroupe][indiceNiveau+1][indiceEtatsNiveauSuivantIndiceNiveau].setEtatInt(E[E.length-1]);
                        indiceEtatsNiveauSuivantIndiceNiveau++;
					}
        		}
			}
        	
        	int dernierNiveau = etatsNiveau[indiceGroupe].length-1;
    		int nbNoeudsDernierNiveau = etatsNiveau[indiceGroupe][dernierNiveau].length;
    		
        	if(prob.getA_MAX()==1||indiceGroupe==prob.getnG_i()[i_parcours]-1){
        		
        		etatsFinauxAncienGroupeInt = new int[nbNoeudsDernierNiveau];
        		for (int j2 = 0; j2 < nbNoeudsDernierNiveau; j2++) {
        			etatsFinauxAncienGroupeInt[j2] = etatsNiveau[indiceGroupe][dernierNiveau][j2].getEtatInt();
				}
        		
        	}
        	else{
        		int[][] EPause = new int[prob.getA_MAX() - prob.getA_MIN() +1][];
        		int etatPostSoins = auto.addState();
        		for (int j2 = 0; j2 < EPause.length; j2++) {
        			EPause[j2] = new int[prob.getA_MIN() -1 +j2];
        			EPause[j2][0] = etatPostSoins;
        			for (int u = 1; u < EPause[j2].length; u++) {
        				EPause[j2][u]= auto.addState();
        				auto.addTransition(EPause[j2][u-1],EPause[j2][u], PAUSE);
        			}
        		}
        	
        		for (int j2 = 0; j2 < nbNoeudsDernierNiveau; j2++) {
        			auto.addTransition(etatsNiveau[indiceGroupe][dernierNiveau][j2].getEtatInt(),etatPostSoins,PAUSE);
        		}
        		
        		etatsFinauxAncienGroupeInt = new int[EPause.length];
        		for (int j2 = 0; j2 < EPause.length; j2++) {
        			etatsFinauxAncienGroupeInt[j2] = EPause[j2][EPause[j2].length-1];
        		}
        	}
		}
       
        
        
        for (int j2 = 0; j2 < etatsFinauxAncienGroupeInt.length; j2++) {
			auto.addTransition(etatsFinauxAncienGroupeInt[j2],fin,RIEN);
		}
 
        this.finiteAutomaton =auto ;
   
	}
	
	
	/**
	 * Methode permettant le calcul de a! 
	 * @param a l'entier dont on veut la factorielle
	 * @return la factorielle de l'entier
	 */
	public static int factoriel(int a){
		if(a==0){
			return 1;
		}
		else{
			return a*factoriel(a-1);
		}
	}

	/**
	 * Exporte le graphe associe a l'automate dans un fichier .dot
	 * @param aExporter String correspondant a la representation du graphes de l'automate au format .dot
	 * @param i_parcours l'indice du parcours a exporter dans l'indice du tableau des parcours
	 * @throws IOException si il y a un probleme lors de la l'ecriture dans le fichier
	 */
	static void exportFichierDot(String aExporter, String nom_parcours) throws IOException {
		BufferedWriter writer = null;
        try {
            //create a temporary file
            //String timeLog = new SimpleDateFormat("Parcours_"+i_parcours+"_yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File logFile = new File("Parcours_"+nom_parcours+".dot");

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(aExporter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
	  }
	
	/**
	 * getter de la variable d'instance indicesSoins
	 * @return les valeurs entieres representant les soins dans l'automate. 
	 */
	public int[][] getIndicesSoins() {
		return this.indicesSoins;
	}
	
	/**
	 * getter de la variable d'instance finiteAutomaton
	 * @return le FiniteAutomaton utilise pour definir les contraintes
	 */
	public FiniteAutomaton getFiniteAutomaton() {
		return finiteAutomaton;
	}
	
}
