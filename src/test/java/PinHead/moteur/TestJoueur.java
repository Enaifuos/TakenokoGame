package PinHead.moteur;

import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Parcelle;
import PinHead.exceptions.ExceptionCaseCourante;
import PinHead.exceptions.ExceptionCaseInaccessible;
import PinHead.exceptions.ExceptionCaseInexistante;
import PinHead.moteur.utils.Afficheur;
import PinHead.automate.Robot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestJoueur {

    private Joueur joueur;
    private RobotTest robot;

    @Before
    public final void initPanda() {
        Afficheur.nouveauAfficheur();
        MaitreDuJeu.getInstance().instancierPanda();
        MaitreDuJeu.getInstance().instancierJardinier();
        MaitreDuJeu.getInstance().instancierPlateau();
        MaitreDuJeu.getInstance().instancierPiocheParcelle();
        MaitreDuJeu.getInstance().instancierPiocheObjectifs();
        MaitreDuJeu.getInstance().instancierPiocheIrrigations();
        Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.vert), Coordonnees.getCentre().getD());
        Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.vert), Coordonnees.getCentre().getBD());
        Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.vert), Coordonnees.getCentre().getBD().getD());
        Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.vert),
                Coordonnees.getCentre().getBD().getD().getHD());
        //Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.vert), new Coordonnees(0, 2));
        robot = new RobotTest();
        joueur = new Joueur("Joueur 1", robot);
    }

    @Test
    public final void testDeplacerPandaDansUneCaseValide() {
        try {
            joueur.deplacerPanda(new Coordonnees(0, 1));
        } catch (Exception e) {
            assertTrue(false);
        }

        assertTrue(true);
    }

    @Test
    public final void testDeplacerPandaDansLaCaseCourante(){
        robot.tourSuivant(new Actions[] {Actions.bougerPandaCaseCourante});
        joueur.jouer(1);
        assertTrue(robot.getRetour());
    }

    @Test
    public final void testDeplacerPandaDansUneCaseInaccessible(){
        robot.tourSuivant(new Actions[] {Actions.bougerPandaCaseInnaccessible});
        joueur.jouer(1);
        assertTrue(robot.getRetour());
    }

    @Test
    public final void testDeplacerPandaDansUneCaseInexistante() {
        robot.tourSuivant(new Actions[] {Actions.bougerPandaCaseInexistante});
        joueur.jouer(1);
        assertTrue(robot.getRetour());
    }

    @Test
    public void testPiocherObjectif() {
        assertEquals(joueur.obtenirMainObjectif().size(), 0); // Taille de départ de la main
        robot.tourSuivant(new Actions[]{Actions.piocherObjectif, Actions.piocherObjectifParcelle});
        joueur.jouer(2);
        assertEquals(joueur.obtenirMainObjectif().size(), 2); // sa taille aprés la pioche
        assertNotEquals(joueur.obtenirMainObjectif().get(0).getClass(),joueur.obtenirMainObjectif().get(1).getClass());
    }



    public final void testContenanceObjectifsRemis(TypesObjectifs t) {
        Joueur j = new Joueur("Joueur 1", robot); // Création du joueur pour reprendre d'une main vide

        PiocheObjectifs.nouvellePiocheObjectif();
        PiocheObjectifs p = PiocheObjectifs.getInstance();

        int tailleAvantPioche = 0;
        int tailleApresPioche = 0;

        tailleAvantPioche = p.getPiochePandaTaille() + p.getPiocheParcelleTaille();//+p.getPiocheJardinierTaille() ;

        assertEquals(j.objectifTaille(), 0);

        j.piocherObjectif(t);
        Objectif obj = j.obtenirMainObjectif().get(0); // L'objectif pioché
        tailleApresPioche = p.getPiochePandaTaille() + p.getPiocheParcelleTaille();//+p.getPiocheJardinierTaille() ;

        assertTrue(j.objectifContenu(obj)); // L'objectif pioché y est
        assertEquals(j.objectifTaille(), 1); // La taille de la main objectifs a augmenté de 1

        assertEquals(tailleApresPioche, tailleAvantPioche - 1);
    }



    @Test
    public void TestPlacerParcelle() {
        int nbParcelles = Plateau.getInstance().obtenirToutesCoordonnees().size();
        robot.tourSuivant(new Actions[] { Actions.parcelle });
        joueur.jouer(1);
        assertTrue(Plateau.getInstance().obtenirToutesCoordonnees().size() > nbParcelles);
    }

    @Test
    public void TestPlacerMauvaiseCoordonnee() {
        int nbParcelles = Plateau.getInstance().obtenirToutesCoordonnees().size();
        robot.tourSuivant(new Actions[] { Actions.caseOccupée });
        joueur.jouer(1);
        assertTrue(Plateau.getInstance().obtenirToutesCoordonnees().size() == nbParcelles);
        assertTrue(joueur.getVerou());
    }

    @Test
    public void TestDeplacerJardinier()
            throws ExceptionCaseCourante, ExceptionCaseInaccessible, ExceptionCaseInexistante {
        robot.tourSuivant(new Actions[] { Actions.bougerJardinier });
        joueur.jouer(1);
    }

    @Test
    public void TestDeplacerJardinierCaseCourante() {
        robot.tourSuivant(new Actions[] { Actions.deplacerJardinierCaseCourante });
        joueur.jouer(1);
        assertTrue(robot.getRetour());
    }

    @Test
    public void TestDeplacerJardinierCaseInaccessible()
    {
        robot.tourSuivant(new Actions[] { Actions.deplacerJardinierCaseInaccessible });
        joueur.jouer(1);
        assertTrue(robot.getRetour());
    }

    @Test
    public void TestDeplacerJardinierCaseInexistante() {
        robot.tourSuivant(new Actions[] { Actions.deplacerJardinierCaseInexistante });
        joueur.jouer(1);
        assertTrue(robot.getRetour());
    }

    @Test
    public void TestObjectifContenu(){
    	robot.tourSuivant(new Actions[] { Actions.piocherObjectif});
    	joueur.jouer(1);
    	Objectif obj = joueur.obtenirMainObjectif().get(0);
    	assertTrue(joueur.objectifContenu(obj));
    }
    
    @Test
    public void TestPiocherIrrigation() {
    	int cptIrrigationAvant = joueur.obtenirReserveIrrigation();
    	
    	robot.tourSuivant(new Actions[] { Actions.piocherIrrigation});
    	joueur.jouer(1);
    	
    	int cptIrrigationApres = joueur.obtenirReserveIrrigation();
    	assertEquals(cptIrrigationAvant,0);
    	assertEquals(cptIrrigationAvant+1,cptIrrigationApres);
    }

    private static enum Actions {
        parcelle, panda, caseOccupée, bougerJardinier, bougerPandaCaseInexistante, bougerPandaCaseCourante,
        bougerPandaCaseInnaccessible, deplacerJardinierCaseInexistante, deplacerJardinierCaseCourante,
        deplacerJardinierCaseInaccessible, piocherObjectif, piocherObjectifParcelle, piocherIrrigation,
    }

    private class RobotTest extends Robot {
        private Joueur joueur;
        private Actions actions[];
        private boolean retour;

        public RobotTest() {
            super("test");
            retour = false;
        }

        public boolean getRetour() {
            return retour;
        }

        @Override
        public void jouer(int nbAction) {
            for (int i = 0; i < nbAction; ++i) {
                executer(actions[i % actions.length]);
            }
        }

        @Override
        public void affecterUnJoueur(Joueur j) {
            this.joueur = j;
        }

        public void tourSuivant(Actions actions[]) {
            this.actions = actions;
        }

        private void placerParcelle() {
            Coordonnees coordonnee = Coordonnees.getCentre();
            while (Plateau.getInstance().coordonneesValides(coordonnee.getD())) {
                coordonnee = coordonnee.getD();
            }
            if (Plateau.getInstance().nbVoisin(coordonnee.getD()) >= 2)
                coordonnee = coordonnee.getD();
            else if (Plateau.getInstance().nbVoisin(coordonnee.getBD()) >= 2)
                coordonnee = coordonnee.getBD();
            else if (Plateau.getInstance().nbVoisin(coordonnee.getBG()) >= 2)
                coordonnee = coordonnee.getBG();
            else if (Plateau.getInstance().nbVoisin(coordonnee.getG()) >= 2)
                coordonnee = coordonnee.getG();
            else if (Plateau.getInstance().nbVoisin(coordonnee.getHG()) >= 2)
                coordonnee = coordonnee.getHG();
            else if (Plateau.getInstance().nbVoisin(coordonnee.getHD()) >= 2)
                coordonnee = coordonnee.getHD();
            joueur.piocherParcelle();
            joueur.placerParcelle(joueur.obtenirMainParcelles().get(0), coordonnee);
        }

        private void placerParcelleOccupée() {
            joueur.piocherParcelle();
            joueur.placerParcelle(joueur.obtenirMainParcelles().get(0), Coordonnees.getCentre());
        }

        private void bougerPanda() {
            try {
                joueur.deplacerPanda(Panda.getInstance().getPosition().getD());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void bougerPandaCaseInexistante() {
            try {
                joueur.deplacerPanda(Panda.getInstance().getPosition().getG());
            } catch (ExceptionCaseInexistante e) {
                retour = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void bougerPandaCaseCourante() {
            try {
                joueur.deplacerPanda(Panda.getInstance().getPosition());
            } catch (ExceptionCaseCourante e) {
                retour = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void bougerPandaCaseInnaccessible() {
            try {
                joueur.deplacerPanda(Panda.getInstance().getPosition().getBD().getD());
            } catch (ExceptionCaseInaccessible e) {
                retour = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void deplacerJardinier() {
            try {
                joueur.deplacerJardinier(Coordonnees.getCentre().getD());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void deplacerJardinierCaseInexistante(){
        	try {
                joueur.deplacerJardinier(Jardinier.getInstance().getPosition().getG());
            } catch (ExceptionCaseInexistante e) {
                retour = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void deplacerJardinierCaseCourante(){
        	 try {
                 joueur.deplacerPanda(Panda.getInstance().getPosition());
             } catch (ExceptionCaseCourante e) {
                 retour = true;
             } catch (Exception e) {
                 e.printStackTrace();
             }
        }

        private void deplacerJardinierCaseInaccessible(){
        	try {
                joueur.deplacerPanda(Panda.getInstance().getPosition().getBD().getD());
            } catch (ExceptionCaseInaccessible e) {
                retour = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void piocherObjectifParcelle(){
        	joueur.piocherObjectif(TypesObjectifs.PARCELLE);
        }

        private void piocherObjectif(){
        	joueur.piocherObjectif(TypesObjectifs.PANDA);
        }
        
        
        /*******													****/
        private void piocherIrrigation() {
        	joueur.piocherIrrigation();
        }



        private void executer(Actions action) {
            switch (action.ordinal()) {
            case 0:
                placerParcelle();
                break;
            case 1:
                bougerPanda();
                break;
            case 2:
                placerParcelleOccupée();
                break;
            case 3:
                deplacerJardinier();
                break;
            case 4:
                bougerPandaCaseInexistante();
                break;
            case 5:
                bougerPandaCaseCourante();
                break;
            case 6:
                bougerPandaCaseInnaccessible();
                break;
            case 7:
            	deplacerJardinierCaseInexistante();
            	break;
            case 8:
            	deplacerJardinierCaseCourante();
            	break;
            case 9:
            	deplacerJardinierCaseInaccessible();
            	break;
            case 10:
            	piocherObjectif();
            	break;
            case 11:
            	piocherObjectifParcelle();
            	break;
            case 12:
            	piocherIrrigation();
            	break;
            default:
                break;
            }
        }

    }

}
