package PinHead.moteur;


import PinHead.automate.Bob;
import PinHead.moteur.Coordonnees;
import PinHead.moteur.Joueur;
import PinHead.moteur.MaitreDuJeu;
import PinHead.moteur.ObjectifParcelle;
import PinHead.moteur.entites.Chemin;
import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Directions;
import PinHead.moteur.entites.Parcelle;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestObjectifParcelle {

    private ObjectifParcelle objP1;
    private ObjectifParcelle objP2;

    @Before
    public final void initObjectifsParcelle() {
        Chemin dir1 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                              add(Directions.DH);
                                                              add(Directions.DH);}});
        Chemin dir2 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                              add(Directions.GB);
                                                              add(Directions.DB);
                                                              add(Directions.DH);}});
        Couleurs[] c = {Couleurs.vert,Couleurs.vert,Couleurs.vert};

        objP1 = new ObjectifParcelle("ligne", dir1,c,1);
        objP2 = new ObjectifParcelle("losange", dir2,c,1);
    }

    @Before
    public final void initPlateau() {
        MaitreDuJeu.getInstance().instancierPlateau();
    }

    @Test
    public final void testEstValideLigneTrue() {
    	Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.vert), Coordonnees.getCentre().getHD());
    	Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.rose), Coordonnees.getCentre().getD());
        Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.rose), Coordonnees.getCentre().getD().getHD());
        Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.vert), Coordonnees.getCentre().getHD().getHD());
        Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.rose), Coordonnees.getCentre().getHD().getHD().getD());
        Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.vert), Coordonnees.getCentre().getHD().getHD().getHD());
        assertTrue(objP1.estValide(new Joueur("j1", new Bob("Bob"))));
    }

    @Test
    public final void testEstValideLigneFalse() {
        Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.rose), Coordonnees.getCentre().getHD());
        assertFalse(objP1.estValide(new Joueur("j1", new Bob("Bob"))));
    }

    @Test
    public final void testEstValiderLosangeTrue() {
        Plateau.getInstance().placerParcelle(
                new Parcelle(Couleurs.vert), Coordonnees.getCentre().getBG());
        Plateau.getInstance().placerParcelle(
                new Parcelle(Couleurs.vert), Coordonnees.getCentre().getG());
        Plateau.getInstance().placerParcelle(
                new Parcelle(Couleurs.vert), Coordonnees.getCentre().getG().getBG());
        Plateau.getInstance().placerParcelle(
                new Parcelle(Couleurs.vert), Coordonnees.getCentre().getBG().getBG());
        assertTrue(objP2.estValide(new Joueur("j1", new Bob("Bob"))));
    }

    @Test
    public final void testEstValiderLosangeFalse() {
        Plateau.getInstance().placerParcelle(
                new Parcelle(Couleurs.rose), Coordonnees.getCentre().getBG());
        Plateau.getInstance().placerParcelle(
                new Parcelle(Couleurs.rose), Coordonnees.getCentre().getBG().getBD());
        assertFalse(objP2.estValide(new Joueur("j1", new Bob("Bob"))));
    }

    @Test
    public final void testToString() {
        FabriqueObjectifParcelle fab = new FabriqueObjectifParcelle();
        Couleurs[] c = {Couleurs.rose,Couleurs.jaune,Couleurs.vert};
        ObjectifParcelle objectif = fab.CreerObjectifParcelle("Losange", c,2);
        String desc = "Placement de parcelles en Losange rose, jaune, vert";
        System.out.println(objectif.toString());
        assertTrue(objectif.toString().equals(desc));
    }
}
