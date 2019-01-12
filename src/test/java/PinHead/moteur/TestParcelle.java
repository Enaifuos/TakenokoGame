package PinHead.moteur;


import org.junit.Test;
import PinHead.moteur.Coordonnees;
import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Etang;
import PinHead.moteur.entites.Parcelle;
import org.junit.Ignore;
import static org.junit.Assert.*;


public class TestParcelle {

    @Test
    public final void testParcelle(){
        Coordonnees mesCoordonnees = new Coordonnees(1, 3);
        Couleurs maCouleur = Couleurs.jaune;
        Parcelle maParcelle = new Parcelle(maCouleur);
        maParcelle.setCoordonnees(mesCoordonnees);
        assertTrue(maParcelle.getCoordonnees().equals(mesCoordonnees));
        assertTrue(maParcelle.getCouleur().equals(maCouleur));
    }

    @Test
    public final void testEquals(){
        Parcelle parcelle1 = new Parcelle(Couleurs.jaune);
        Parcelle parcelle2 = new Parcelle(Couleurs.vert);
        parcelle1.setCoordonnees(Coordonnees.getCentre());
        parcelle2.setCoordonnees(Coordonnees.getCentre());
        assertEquals(parcelle1, parcelle2);
        parcelle2.setCoordonnees(Coordonnees.getCentre().getBG());
        assertNotEquals(parcelle1, parcelle2);
    }

    @Test
    public final void testBambou(){
        Coordonnees mesCoordonnees = new Coordonnees(1, 3);
        Couleurs maCouleur = Couleurs.rose;
        Parcelle maParcelle = new Parcelle(maCouleur);
        maParcelle.setCoordonnees(mesCoordonnees);
        assertTrue(maParcelle.getTailleBambou()==0);
        for (int i= 1; i <= 4; i++){
            maParcelle.pousserBambou();
            assertTrue(maParcelle.getTailleBambou()==i);
        }
        assertTrue(maParcelle.getTailleBambou()==4);
        maParcelle.pousserBambou();
        assertTrue(maParcelle.getTailleBambou()==4);
        for (int i=1; i <= 4; i++){
            maParcelle.recupererBambou();
            assertTrue(maParcelle.getTailleBambou()==4-i);
        }
        assertTrue(maParcelle.getTailleBambou()==0);
        maParcelle.recupererBambou();
        assertTrue(maParcelle.getTailleBambou()==0);
    }
    @Ignore
    @Test
    public final void testIrrigationParcelle(){
        Etang etang = new Etang(Coordonnees.getCentre());
        Parcelle parcelle1 = new Parcelle(Couleurs.jaune);
        Parcelle parcelle2 = new Parcelle(Couleurs.vert);
        parcelle1.setIrrigationTrue();
        etang.setIrrigationTrue();
        assertTrue(etang.estIrriguée());
        assertTrue(parcelle1.estIrriguée());
        assertFalse(parcelle2.estIrriguée());
    }
}
