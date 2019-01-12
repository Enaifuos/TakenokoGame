package PinHead.automate;

import PinHead.exceptions.ExceptionCaseInexistante;
import PinHead.moteur.*;
import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Parcelle;
import PinHead.moteur.utils.Afficheur;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMarcel {

    private Robot marcel;
    private Joueur joueur;

    @Before
    public final void init() {
        MaitreDeTest.getInstance().instancierPlateau();
        MaitreDeTest.getInstance().instancierPanda();
        MaitreDeTest.getInstance().instancierPiocheObjectifs();
        Afficheur.nouveauAfficheur();
        marcel = new Marcel("Marcel");
        joueur = new Joueur("Marcel", marcel);
        Couleurs[] c = {Couleurs.rose};
        Objectif objectif = new FabriqueObjectifDeTestPanda().CreerObjectifPanda(1, c, 1);
        joueur.ajouterObjectif(objectif);
    }

    @Ignore
    public final void testBonDeplacement() {
        PlateauDeTest.getInstance().placerParcelle(new Parcelle(Couleurs.rose), new Coordonnees(1, 1));
        PlateauDeTest.getInstance().placerParcelle(new Parcelle(Couleurs.rose), new Coordonnees(1, 0));
        PlateauDeTest.getInstance().placerParcelle(new Parcelle(Couleurs.rose), new Coordonnees(2, 1));
        PlateauDeTest.getInstance().placerParcelle(new Parcelle(Couleurs.rose), new Coordonnees(2, 0));
        PlateauDeTest.getInstance().placerParcelle(new Parcelle(Couleurs.rose), new Coordonnees(3,1));
        PlateauDeTest.getInstance().obtenirParcelle(new Coordonnees(3, 1)).pousserBambou();
        joueur.jouer(1);
        assertEquals(new Coordonnees(1,1), joueur.obtenirCoordonneesPanda());
        joueur.jouer(1);
        assertEquals(new Coordonnees(3, 1), joueur.obtenirCoordonneesPanda());
    }
}
