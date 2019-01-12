package PinHead.automate;

import PinHead.moteur.Coordonnees;
import PinHead.moteur.Joueur;
import PinHead.moteur.Objectif;
import PinHead.moteur.TypesObjectifs;
import PinHead.moteur.entites.Parcelle;
import PinHead.moteur.utils.Afficheur;

import java.util.List;
import java.util.Random;

/**
  *@author Corentin Artaud
 **/

public class Bob extends Robot {
    //private Joueur joueur;
    //private int choix;
    private int compteur;
    Random random;

    public Bob(String nom){
        super(nom);
        this.random = new Random(System.nanoTime());
        compteur = 0;
    }

    /**
    *@return une nouvelle coordonnée en fonction de la Direction
    *@param coordonnee la coordonnée d'origine
    *@param direction l'entier représentant ladirection
     **/
    private Coordonnees nouvelleCoordonnees(Coordonnees coordonnee, int direction) {
        Coordonnees nouvelle = null;
        switch (direction) {
        case 0:
            nouvelle = coordonnee.getD();
            break;
        case 1:
            nouvelle = coordonnee.getBD();
            break;
        case 2:
            nouvelle = coordonnee.getBG();
            break;
        case 3:
            nouvelle = coordonnee.getG();
            break;
        case 4:
            nouvelle = coordonnee.getHG();
            break;
        case 5:
            nouvelle = coordonnee.getHD();
            break;
        }
        return nouvelle;
    }

    /**
    *Pose une parcelle de manière aléatoire
     **/
    private boolean poserParcelle() {
        Coordonnees pos = Coordonnees.getCentre();
        Random random = new Random(System.nanoTime());
        int cpt = 0;
        while (!joueur.peutPlacerParcelle(pos)) {
            int direction = random.nextInt(6);
            pos = nouvelleCoordonnees(pos, direction);
            ++cpt;
            if (cpt > 100) {
                pos = Coordonnees.getCentre();
                cpt = 0;
            }
        }
        joueur.piocherParcelle();
        List<Parcelle> mainParcelle = joueur.obtenirMainParcelles();
        if (mainParcelle.size() > 0) {
            joueur.placerParcelle(mainParcelle.get(random.nextInt(mainParcelle.size())), pos);
            return true;
        }
        return false;
    }

    /**
    *pioche un objectif aléatoire
    *
    **/
    private void piocherObjectif() {
        TypesObjectifs[] valeurs = TypesObjectifs.values();
        joueur.piocherObjectif(valeurs[random.nextInt(valeurs.length)]);
    }

    private boolean bougerPandaMimicJardinier() {
        try {
            joueur.deplacerPanda(joueur.obtenirCoordonneesJardinier());
            return true;
            //System.out.println("Bob en tant que "+joueur.getNom()+" déplace le Panda en "+coordPanda.getX()+" : "+coordPanda.getY());
        } catch (Exception e) {
            Afficheur.getInstance().afficherErreur(e.getMessage());
            return false;
        }
    }

    /**
     * Bouge le panda de manière aléatoire
     **/
    private boolean bougerPanda() {
        if (joueur.obtenirToutesCoordonnées().size() < 2)
            return false;
        Coordonnees coordPanda = null;
        Random random = new Random(System.nanoTime());
        while (coordPanda == null || coordPanda.equals(joueur.obtenirCoordonneesPanda())
                || !joueur.coordonneesValides(coordPanda)) {
            coordPanda = joueur.obtenirCoordonneesPanda();
            coordPanda = nouvelleCoordonnees(coordPanda, random.nextInt(6));
        }
        try {
            joueur.deplacerPanda(coordPanda);
            return true;
            //System.out.println("Bob en tant que "+joueur.getNom()+" déplace le Panda en "+coordPanda.getX()+" : "+coordPanda.getY());
        } catch (Exception e) {
            Afficheur.getInstance().afficherErreur(e.getMessage());
            return false;
        }
    }

    /**
     * Bouge le jardinier de manière aléatoire
     **/
    private boolean bougerJardinier() {
        if (joueur.obtenirToutesCoordonnées().size() < 2)
            return false;
        Coordonnees coordJardinier = null;
        Random random = new Random(System.nanoTime());
        while (coordJardinier == null || coordJardinier.equals(joueur.obtenirCoordonneesJardinier())
                || !joueur.coordonneesValides(coordJardinier)) {
            coordJardinier = joueur.obtenirCoordonneesJardinier();
            coordJardinier = nouvelleCoordonnees(coordJardinier, random.nextInt(6));
        }
        try {
            joueur.deplacerJardinier(coordJardinier);
            return true;
            //System.out.println("Bob en tant que "+joueur.getNom()+" déplace le Jardinier en "+coordJardinier.getX()+":
            // "+coordJardinier.getY());
        } catch (Exception e) {
            Afficheur.getInstance().afficherErreur(e.getMessage());
        }
        return false;
    }

    /**
    *comportement du robot
     **/
    private void comportement(int nbActions) {
        joueur.testerObjectifs();
        if (joueur.objectifTaille() < 5 && compteur == 0)
            nbActions = 3;
        switch (nbActions) {
        case 0:
            if (!poserParcelle())
                bougerPanda();
            break;
        case 2:
            if (! bougerPandaMimicJardinier()) if (!bougerPanda())
                poserParcelle();
            break;
        case 1:
            if (!bougerJardinier())
                poserParcelle();
            break;
        case 3:
            piocherObjectif();
            break;
        }
    }

    public void jouer(int nbActions) {
        if (this.joueur == null)
            return;
        for (int i = 0; i < nbActions; ++i) {
            compteur = compteur%3;
            comportement(compteur);
            ++compteur;
        }
        joueur.testerObjectifs();
    }

    // public void affecterUnJoueur(Joueur joueur) {
    //     this.joueur = joueur;
    // }

}
