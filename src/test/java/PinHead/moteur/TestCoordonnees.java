package PinHead.moteur;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestCoordonnees {

    @Test
    public final void testCoordonnes(){
        Coordonnees mesCoordonnees = new Coordonnees (1, 3);
        assertTrue(mesCoordonnees.getX()==1);
        assertTrue(mesCoordonnees.getY()==3);
    }

    @Test
    public final void testEquals(){
        Coordonnees coordonnees1 = new Coordonnees(1, 3);
        Coordonnees coordonnees2 = new Coordonnees(1, 3);
        Coordonnees coordonnees3 = new Coordonnees(1, 7);
        assertTrue(coordonnees1.equals(coordonnees2));
        assertFalse(coordonnees1.equals(coordonnees3));
        assertFalse(coordonnees2.equals(coordonnees3));
    }

    @Test
    public final void testVoisin(){
        Coordonnees mesCoordonnees = new Coordonnees(7,-3);
        Coordonnees CoordonneesHD = new Coordonnees(8,-3);
        Coordonnees CoordonneesD = new Coordonnees(8,-2);
        Coordonnees CoordonneesBD = new Coordonnees(7,-2);
        Coordonnees CoordonneesBG = new Coordonnees(6,-3);
        Coordonnees CoordonneesG = new Coordonnees(6,-4);
        Coordonnees CoordonneesHG = new Coordonnees(7,-4);
        assertTrue(mesCoordonnees.getHD().equals(CoordonneesHD));
        assertTrue(mesCoordonnees.getD().equals(CoordonneesD));
        assertTrue(mesCoordonnees.getBD().equals(CoordonneesBD));
        assertTrue(mesCoordonnees.getBG().equals(CoordonneesBG));
        assertTrue(mesCoordonnees.getG().equals(CoordonneesG));
        assertTrue(mesCoordonnees.getHG().equals(CoordonneesHG));

    }

    @Test
    public final void testEstLeVoisinNumero(){
        Coordonnees mesCoordonnees = new Coordonnees(7,-3);
        Coordonnees CoordonneesHD = new Coordonnees(8,-3);
        Coordonnees CoordonneesD = new Coordonnees(8,-2);
        Coordonnees CoordonneesBD = new Coordonnees(7,-2);
        Coordonnees CoordonneesBG = new Coordonnees(6,-3);
        Coordonnees CoordonneesG = new Coordonnees(6,-4);
        Coordonnees CoordonneesHG = new Coordonnees(7,-4);
        assertTrue(mesCoordonnees.estLeVoisinNumero(CoordonneesHD) == 0);
        assertTrue(mesCoordonnees.estLeVoisinNumero(CoordonneesD) == 1);
        assertTrue(mesCoordonnees.estLeVoisinNumero(CoordonneesBD) == 2);
        assertTrue(mesCoordonnees.estLeVoisinNumero(CoordonneesBG) == 3);
        assertTrue(mesCoordonnees.estLeVoisinNumero(CoordonneesG) == 4);
        assertTrue(mesCoordonnees.estLeVoisinNumero(CoordonneesHG) == 5);
    }
}
