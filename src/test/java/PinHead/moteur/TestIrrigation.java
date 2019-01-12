package PinHead.moteur;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestIrrigation{

  @Test
  public final void testIrrigationIdentiquesSimple(){
    Coordonnees coord1 = Coordonnees.getCentre().getBG();
    Coordonnees coord2 = coord1.getD();
    Irrigation i1 = new Irrigation(coord1, coord2);
    Irrigation i2 = new Irrigation(coord1, coord2);
    assertEquals(i1, i2);
  }

  @Test
  public final void testIrrigationIdentiquesCroisé(){
    Coordonnees coord1 = Coordonnees.getCentre().getBG();
    Coordonnees coord2 = coord1.getD();
    Irrigation i1 = new Irrigation(coord1, coord2);
    Irrigation i2 = new Irrigation(coord2, coord1);
    assertEquals(i1, i2);
  }

  @Test
  public final void testIrrigationDifférentes(){
    Coordonnees coord1 = Coordonnees.getCentre().getBG();
    Coordonnees coord2 = coord1.getD();

    Coordonnees coord3 = coord2.getD();
    Irrigation i1 = new Irrigation(coord1, coord2);
    Irrigation i2 = new Irrigation(coord2, coord3);
    assertNotEquals(i1, i2);
  }
}
