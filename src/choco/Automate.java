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
	
	
	//Rajouter les pauses entre les taches (toutes) et les mettre correctement entre les groupes
	//Rajouter le rien a la fin
	//TEnir compte que collation a une duree de 1
	public Automate(Parcours p){
		
		//Initialisation tache pause et rien
		int RIEN = 0;
		int PAUSE = 1;
		
		//Association d'indices a chaque soins, pause et rien
		//IndiceSoins[i][j] represent l'indice utilisee dans l'automate pour representer le soin j du groupe de soin i du parcours p 
		int[][] indiceSoins = new int[p.getGroupeSoins().length][];
		int i=0;
		int indiceSoin =2;
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
		
		//Creation de l'automate
        FiniteAutomaton auto = new FiniteAutomaton();
        int debut = auto.addState();
        auto.setInitialState(debut);
        int fin = auto.addState();
        auto.setFinal(debut);
        auto.setFinal(fin);

        //Ajout transition entre les etats debut et fin
        auto.addTransition(debut, debut, RIEN);
        auto.addTransition(fin, fin, RIEN);
        
        //Initialisation de tous les etats possibles (en terme de combinaisons de soins realisables apres l'Etat)
        Etat[][][] etatsNiveau = new Etat[p.getNombreDeGroupes()][][];
        for (int indiceGroupe = 0; indiceGroupe < p.getNombreDeGroupes(); indiceGroupe++) {
        	etatsNiveau[indiceGroupe] = new Etat[p.getGroupeSoins()[indiceGroupe].getNombreDeSoins()+1][];
			for (int indiceNiveau = 0; indiceNiveau < etatsNiveau[indiceGroupe].length; indiceNiveau++) {
				int nbEtatsNiveauIndiceNiveau = factoriel(p.getGroupeSoins()[indiceGroupe].getNombreDeSoins())/factoriel(p.getGroupeSoins()[indiceGroupe].getNombreDeSoins()-indiceNiveau);
				etatsNiveau[indiceGroupe][indiceNiveau]= new Etat[nbEtatsNiveauIndiceNiveau];
				for (int k = 0; k < nbEtatsNiveauIndiceNiveau; k++) {
					etatsNiveau[indiceGroupe][indiceNiveau][k]= new Etat();
				}
			}
		}
        
      //Creation des listes de possibilites (soins realisables) dans le groupe de soin 0 
        
        int etatFinalAncienGroupeInt = debut; 
        
        //A modif
        for(int indiceGroupe = 0; indiceGroupe<p.getNombreDeGroupes(); indiceGroupe++){
        	ArrayList<CoupleIndiceSoinDuree> listPossibilitesGroupe = new ArrayList();
        	for (int j = 0; j < indiceSoins[indiceGroupe].length; j++) {
        		listPossibilitesGroupe.add(new CoupleIndiceSoinDuree(indiceSoins[indiceGroupe][j],p.getGroupeSoins()[indiceGroupe].getSoin(j).getDuree()));
        	}

        	etatsNiveau[indiceGroupe][0][0] = new Etat(listPossibilitesGroupe,auto.addState());
       
        
        	
        	//Certainement a modifier par la suite (Pause voir meme pas de charactere du tout)
        	auto.addTransition(etatFinalAncienGroupeInt,etatsNiveau[indiceGroupe][0][0].getEtatInt() , RIEN);
        	
        	Etat etatCourant;
        	for (int indiceNiveau = 0; indiceNiveau < etatsNiveau[indiceGroupe].length-1; indiceNiveau++) {
        		int indiceEtatsNiveauSuivantIndiceNiveau=0;
        		for (int indiceNoeudNiveauIndiceNiveau = 0; indiceNoeudNiveauIndiceNiveau < etatsNiveau[indiceGroupe][indiceNiveau].length; indiceNoeudNiveauIndiceNiveau++) {
        			etatCourant =etatsNiveau[indiceGroupe][indiceNiveau][indiceNoeudNiveauIndiceNiveau];
        			ArrayList<CoupleIndiceSoinDuree> listeSoinsRealisables = etatCourant.getListPossibilite();
        			for (int j = 0; j < listeSoinsRealisables.size(); j++) {
        				etatsNiveau[indiceGroupe][indiceNiveau+1][indiceEtatsNiveauSuivantIndiceNiveau] = etatCourant.etatSuivant(listeSoinsRealisables.get(j).getIndiceSoin());
                		int E[] = new int[listeSoinsRealisables.get(j).getDuree()];
                		for (int k = 0; k < E.length; k++) {
                			E[k] = auto.addState();
                		}
                		auto.addTransition(etatCourant.getEtatInt(), E[0], listeSoinsRealisables.get(j).getIndiceSoin());
                        for (int k = 0; k < E.length-1; k++) {
            				auto.addTransition(E[k], E[k+1], listeSoinsRealisables.get(j).getIndiceSoin());
            			}
                        etatsNiveau[indiceGroupe][indiceNiveau+1][indiceEtatsNiveauSuivantIndiceNiveau].setEtatInt(E[E.length-1]);
                        indiceEtatsNiveauSuivantIndiceNiveau++;
					}
        		}
			}
        	
        	int etatPause = auto.addState();
        	int dernierNiveau = etatsNiveau[indiceGroupe].length-1;
        	int nbNoeudsDernierNiveau = etatsNiveau[indiceGroupe][dernierNiveau].length;
        	for (int j = 0; j < nbNoeudsDernierNiveau; j++) {
        		auto.addTransition(etatsNiveau[indiceGroupe][dernierNiveau][j].getEtatInt(),etatPause,PAUSE);
			}
			
        	
        	
        	
		}
       
        
        /*Penser a l'ajout de pause*/
        
       
        /*
        for (int j = 0; j < 1; j++) {
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
        */
        auto.addTransition(etatFinalAncienGroupeInt, fin, RIEN);
        System.out.printf("%s\n", auto.toDot());
        

	}
	
	public static Automate AutomateAvecPause(Parcours p, int Amin, int Amax){

		
		//Initialisation tache pause et rien
		int RIEN = 0;
		int PAUSE = 1;
		
		//Association d'indices a chaque soins, pause et rien
		//IndiceSoins[i][j] represent l'indice utilisee dans l'automate pour representer le soin j du groupe de soin i du parcours p 
		int[][] indiceSoins = new int[p.getGroupeSoins().length][];
		int i=0;
		int indiceSoin =2;
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
		
		//Creation de l'automate
        FiniteAutomaton auto = new FiniteAutomaton();
        int debut = auto.addState();
        auto.setInitialState(debut);
        int fin = auto.addState();
        auto.setFinal(debut);
        auto.setFinal(fin);

        //Ajout transition entre les etats debut et fin
        auto.addTransition(debut, debut, RIEN);
        auto.addTransition(fin, fin, RIEN);
        
        //Initialisation de tous les etats possibles (en terme de combinaisons de soins realisables apres l'Etat)
        Etat[][][] etatsNiveau = new Etat[p.getNombreDeGroupes()][][];
        for (int indiceGroupe = 0; indiceGroupe < p.getNombreDeGroupes(); indiceGroupe++) {
        	etatsNiveau[indiceGroupe] = new Etat[p.getGroupeSoins()[indiceGroupe].getNombreDeSoins()+1][];
			for (int indiceNiveau = 0; indiceNiveau < etatsNiveau[indiceGroupe].length; indiceNiveau++) {
				int nbEtatsNiveauIndiceNiveau = factoriel(p.getGroupeSoins()[indiceGroupe].getNombreDeSoins())/factoriel(p.getGroupeSoins()[indiceGroupe].getNombreDeSoins()-indiceNiveau);
				etatsNiveau[indiceGroupe][indiceNiveau]= new Etat[nbEtatsNiveauIndiceNiveau];
				for (int k = 0; k < nbEtatsNiveauIndiceNiveau; k++) {
					etatsNiveau[indiceGroupe][indiceNiveau][k]= new Etat();
				}
			}
		}
        
      //Creation des listes de possibilites (soins realisables) dans le groupe de soin 0 
        
        int[] etatFinalAncienGroupeInt = new int[1];
        etatFinalAncienGroupeInt[0] = debut; 
        
        //A modif
        for(int indiceGroupe = 0; indiceGroupe<p.getNombreDeGroupes(); indiceGroupe++){
        	ArrayList<CoupleIndiceSoinDuree> listPossibilitesGroupe = new ArrayList();
        	for (int j = 0; j < indiceSoins[indiceGroupe].length; j++) {
        		listPossibilitesGroupe.add(new CoupleIndiceSoinDuree(indiceSoins[indiceGroupe][j],p.getGroupeSoins()[indiceGroupe].getSoin(j).getDuree()));
        	}

    		etatsNiveau[indiceGroupe][0][0] = new Etat(listPossibilitesGroupe,auto.addState());

        	if(indiceGroupe == 0){
            	auto.addTransition(etatFinalAncienGroupeInt[0],etatsNiveau[indiceGroupe][0][0].getEtatInt() , RIEN);
        	}
        	else{
        		for (int j = 0; j < etatFinalAncienGroupeInt.length; j++) {
                	auto.addTransition(etatFinalAncienGroupeInt[j],etatsNiveau[indiceGroupe][0][0].getEtatInt() , PAUSE);
				}
        	}
        
        	
        	//Certainement a modifier par la suite (Pause voir meme pas de charactere du tout)
        	
        	Etat etatCourant;
        	for (int indiceNiveau = 0; indiceNiveau < etatsNiveau[indiceGroupe].length-1; indiceNiveau++) {
        		int indiceEtatsNiveauSuivantIndiceNiveau=0;
        		for (int indiceNoeudNiveauIndiceNiveau = 0; indiceNoeudNiveauIndiceNiveau < etatsNiveau[indiceGroupe][indiceNiveau].length; indiceNoeudNiveauIndiceNiveau++) {
        			etatCourant =etatsNiveau[indiceGroupe][indiceNiveau][indiceNoeudNiveauIndiceNiveau];
        			ArrayList<CoupleIndiceSoinDuree> listeSoinsRealisables = etatCourant.getListPossibilite();
        			for (int j = 0; j < listeSoinsRealisables.size(); j++) {
        				etatsNiveau[indiceGroupe][indiceNiveau+1][indiceEtatsNiveauSuivantIndiceNiveau] = etatCourant.etatSuivant(listeSoinsRealisables.get(j).getIndiceSoin());
                		int E[] = new int[listeSoinsRealisables.get(j).getDuree()];
                		for (int k = 0; k < E.length; k++) {
                			E[k] = auto.addState();
                		}
                		auto.addTransition(etatCourant.getEtatInt(), E[0], listeSoinsRealisables.get(j).getIndiceSoin());
                        for (int k = 0; k < E.length-1; k++) {
            				auto.addTransition(E[k], E[k+1], listeSoinsRealisables.get(j).getIndiceSoin());
            			}
                        etatsNiveau[indiceGroupe][indiceNiveau+1][indiceEtatsNiveauSuivantIndiceNiveau].setEtatInt(E[E.length-1]);
                        indiceEtatsNiveauSuivantIndiceNiveau++;
					}
        		}
			}
        	/*
        	int etatPause = auto.addState();
        	int dernierNiveau = etatsNiveau[indiceGroupe].length-1;
        	int nbNoeudsDernierNiveau = etatsNiveau[indiceGroupe][dernierNiveau].length;
        	for (int j = 0; j < nbNoeudsDernierNiveau; j++) {
        		auto.addTransition(etatsNiveau[indiceGroupe][dernierNiveau][j].getEtatInt(),etatPause,PAUSE);
			}
			*/
        	int[] EPauseMin = new int[Amin];
        	for (int j = 0; j < EPauseMin.length; j++) {
				EPauseMin[j] = auto.addState();
        	}
        	int dernierNiveau = etatsNiveau[indiceGroupe].length-1;
        	int nbNoeudsDernierNiveau = etatsNiveau[indiceGroupe][dernierNiveau].length;
        	for (int j = 0; j < nbNoeudsDernierNiveau; j++) {
        		auto.addTransition(etatsNiveau[indiceGroupe][dernierNiveau][j].getEtatInt(),EPauseMin[0],PAUSE);
			}
        	for (int j = 0; j < Amin-2; j++) {
				auto.addTransition(EPauseMin[j], EPauseMin[j+1], PAUSE);
			}
        	int[] EPauseMax = new int[Amax - Amin];
        	for (int j = 0; j < etatsNiveau.length; j++) {
				EPauseMax[j] = auto.addState();
			}
        	auto.addTransition(EPauseMin[Amin-1], EPauseMax[0], PAUSE);
        	auto(EPauseMin[Amin-1], EPauseMax[0], PAUSE);
        	etatFinalAncienGroupeInt = etatPause;
        	
        	
		}
       
        
        /*Penser a l'ajout de pause*/
        
      
        auto.addTransition(etatFinalAncienGroupeInt, fin, RIEN);
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
	
		//1. Initialisation des donnees a disposition des clients
				Donnees donnees = new Donnees();
				int nPeriodes =60*24;
				
				donnees.setNPeriodes(nPeriodes);
				donnees.setA_MAX(60);
				donnees.setA_MIN(5);
				donnees.setHFermeture(20*60);
				donnees.setHOuverture(6*60);
				
				//Ajout ressources matérielles
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "HDJ Obesite", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Box", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Box Prelevement", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Hors HDJ", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Bureau CS", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Box Soin", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Salle Pansement", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Explorations Fonctionnelles", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Piece Isolee Avec Fauteuil", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Bureau Sommeil", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Salle ETP Groupe", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Salle Avec Lit", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "HDJ Chimio", 10));

				//Ajout ressources humaines
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Obesite", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Nutritioniste", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Dieteticien", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Psychologue", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Interne Obesite", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Medecin Hepato", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Externe", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Cardio", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Cardiologue", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Orthoptiste", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Diabetologue", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Podologue", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Pansement", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Medecin", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Pompe Insuline", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Insulinotherapie", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Medecin Sommeil", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Sommeil", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Prestataire", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Chimio", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Generaliste", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Soignant", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Selon Profil", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Kine", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Agent Accueil", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Neurologue", 10));
				donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Neuropsy", 10));
				
				ArrayList<CoupleStringInt> listRessourceCapacite2 = new ArrayList<>();	
				listRessourceCapacite2.add(new CoupleStringInt("IDE", 1));	
				listRessourceCapacite2.add(new CoupleStringInt("Box", 1));	 	 	 	 	 	
				Soin ECG = Soin.creerSoin(donnees, listRessourceCapacite2, "ECG", 15);
				
				ArrayList<CoupleStringInt> listRessourceCapacite3 = new ArrayList<>();	
				listRessourceCapacite3.add(new CoupleStringInt("IDE", 1));	
				listRessourceCapacite3.add(new CoupleStringInt("Box Prelevement", 1));	 	 	 	 	 	
				Soin BilanBiologique = Soin.creerSoin(donnees, listRessourceCapacite3, "Bilan Biologique", 15);
				
				ArrayList<CoupleStringInt> listRessourceCapacite4 = new ArrayList<>();	 	 	 	 	
				Soin EchoHepathique = Soin.creerSoin(donnees, listRessourceCapacite4, "Echo Hepathique", 15);
				
				ArrayList<CoupleStringInt> listRessourceCapacite5 = new ArrayList<>();	
				listRessourceCapacite5.add(new CoupleStringInt("IDE Obesite", 1));	
				listRessourceCapacite5.add(new CoupleStringInt("HDJ Obesite", 1));	
				Soin Calorimetrie = Soin.creerSoin(donnees, listRessourceCapacite5, "Calorimetrie", 30);
				
				ArrayList<CoupleStringInt> listRessourceCapacite6 = new ArrayList<>();	
				listRessourceCapacite6.add(new CoupleStringInt("Psychologue", 1));	
				listRessourceCapacite6.add(new CoupleStringInt("Bureau CS", 1));	 	 	 	 	 	
				Soin EntretienPsy = Soin.creerSoin(donnees, listRessourceCapacite6, "Entretien Psy", 40);
				
				ArrayList<CoupleStringInt> listRessourceCapacite7 = new ArrayList<>();	
				listRessourceCapacite7.add(new CoupleStringInt("IDE Obesite", 1));	
				listRessourceCapacite7.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	 	 	
				Soin EntretienInfirmier = Soin.creerSoin(donnees, listRessourceCapacite7, "Entretien Infirmier", 30);
				
				ArrayList<CoupleStringInt> listRessourceCapacite8 = new ArrayList<>();	
				listRessourceCapacite8.add(new CoupleStringInt("Dieteticien", 1));	
				listRessourceCapacite8.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	 	 	
				Soin EntretienDiet = Soin.creerSoin(donnees, listRessourceCapacite8, "Entretien Diet", 60);
				
				ArrayList<CoupleStringInt> listRessourceCapacite10 = new ArrayList<>();	
				listRessourceCapacite10.add(new CoupleStringInt("IDE Obesite", 1));	
				listRessourceCapacite10.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	 	 	
				Soin RDVParamedical = Soin.creerSoin(donnees, listRessourceCapacite10, "RDV Paramedical", 20);
					
				ArrayList<CoupleStringInt> listRessourceCapacite11 = new ArrayList<>();	 	 	 	 	 	 	 	
				Soin TOGD = Soin.creerSoin(donnees, listRessourceCapacite11, "TOGD", 20);
			
				ArrayList<CoupleStringInt> listRessourceCapacite12 = new ArrayList<>();	 	 	 	 	 	 	 	
				Soin collation = Soin.creerSoin(donnees, listRessourceCapacite12, "Collation", 1);
				
				ArrayList<CoupleStringInt> listRessourceCapacite13 = new ArrayList<>();	
				listRessourceCapacite13.add(new CoupleStringInt("Nutritioniste", 1));	
				listRessourceCapacite13.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	
				Soin synthese = Soin.creerSoin(donnees, listRessourceCapacite13, "Synthese", 30);
				
				ArrayList<CoupleStringInt> listRessourceCapacite14 = new ArrayList<>();
				listRessourceCapacite14.add(new CoupleStringInt("Interne Obesite", 1));
				listRessourceCapacite14.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	
				Soin BilanAnthropometrique = Soin.creerSoin(donnees, listRessourceCapacite14, "Bilan Anthropometrique", 60);
				
				ArrayList<CoupleStringInt> listRessourceCapacite15 = new ArrayList<>();
				listRessourceCapacite15.add(new CoupleStringInt("Medecin Hepato", 1));	
				listRessourceCapacite15.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	 
				Soin Fibroscan = Soin.creerSoin(donnees, listRessourceCapacite15, "Fibroscan", 10);
				
				
				// On crée le Parcours 1
				Soin[] G1P1S = {RDVParamedical};
				GroupeSoins G1P1 = new GroupeSoins(G1P1S);
				Soin[] G2P1S = {ECG, BilanBiologique, EchoHepathique, Calorimetrie};
				GroupeSoins G2P1 = new GroupeSoins(G2P1S);
				Soin[] G3P1S = {collation};
				GroupeSoins G3P1 = new GroupeSoins(G3P1S);
				Soin[] G4P1S = {EntretienPsy, EntretienInfirmier, EntretienDiet};
				GroupeSoins G4P1 = new GroupeSoins(G4P1S);
				Soin[] G5P1S = {synthese};
				GroupeSoins G5P1 = new GroupeSoins(G5P1S);
				GroupeSoins[] P1G = {G1P1, G2P1, G3P1, G4P1, G5P1};
				Parcours P1  = new Parcours(P1G, 0);
				
		
		new Automate(P1);
		
	}
	
}
