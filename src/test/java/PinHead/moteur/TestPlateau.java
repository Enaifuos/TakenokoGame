package PinHead.moteur;

import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Parcelle;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPlateau {
  @Test
  public final void testObtenirParcelle() {
    Plateau.nouveauPlateau();
    Plateau plateau = Plateau.getInstance();
    Coordonnees coordonnees = Coordonnees.getCentre();
    Parcelle parcelle = plateau.obtenirParcelle(coordonnees);
    assertTrue(parcelle.getCoordonnees().equals(coordonnees));
  }

  @Test
  public final void testNePasObtenirParcelle() {
    Plateau.nouveauPlateau();
    Plateau plateau = Plateau.getInstance();
    Coordonnees coordonnees = Coordonnees.getCentre().getHG();
    Parcelle parcelle = plateau.obtenirParcelle(coordonnees);
    assertTrue(parcelle == null);
  }

  @Test
  public final void testAjoutSimple() {
    assertEquals(Coordonnees.getCentre(), Coordonnees.getCentre().getHD().getBG());
    Plateau.nouveauPlateau();
    Plateau plateau = Plateau.getInstance();
    Parcelle parcelle = new Parcelle(Couleurs.vert);
    assertTrue(plateau.placerParcelle(parcelle, Coordonnees.getCentre().getHD()));
    assertEquals(plateau.obtenirParcelle(parcelle.getCoordonnees()), parcelle);
  }

  @Test
  public final void testAjoutImpossiblePasDeVoisin() {
    Plateau.nouveauPlateau();
    Plateau plateau = Plateau.getInstance();
    Parcelle parcelle = new Parcelle(Couleurs.vert);
    assertFalse(plateau.placerParcelle(parcelle, Coordonnees.getCentre().getHD().getD()));
    assertNull(plateau.obtenirParcelle(parcelle.getCoordonnees()));
  }

  @Test
  public final void testAjoutImpossibleCoordonneeOccupee() {
    Plateau.nouveauPlateau();
    Plateau plateau = Plateau.getInstance();
    Parcelle parcelle = new Parcelle(Couleurs.vert);
    assertFalse(plateau.placerParcelle(parcelle, Coordonnees.getCentre()));
  }

  @Test
  public final void testCoordonneesValides() {
    Plateau.nouveauPlateau();
    Plateau plateau = Plateau.getInstance();
    assertTrue(plateau.coordonneesValides(Coordonnees.getCentre()));
  }

  @Test
  public final void testCoordonneesInvalides() {
    Plateau.nouveauPlateau();
    Plateau plateau = Plateau.getInstance();
    assertFalse(plateau.coordonneesValides(Coordonnees.getCentre().getBD()));
  }

  @Test
  public final void testnbVoisin() {
    Plateau.nouveauPlateau();
    assertTrue(Plateau.getInstance().nbVoisin(Coordonnees.getCentre().getD()) == 1);
    assertTrue(Plateau.getInstance().nbVoisin(Coordonnees.getCentre().getBD()) == 1);
    assertTrue(Plateau.getInstance().nbVoisin(Coordonnees.getCentre().getBG()) == 1);
    assertTrue(Plateau.getInstance().nbVoisin(Coordonnees.getCentre().getG()) == 1);
    assertTrue(Plateau.getInstance().nbVoisin(Coordonnees.getCentre().getHG()) == 1);
    assertTrue(Plateau.getInstance().nbVoisin(Coordonnees.getCentre().getHD()) == 1);
  }

  @Test
  public final void testNaPasDeVoisin() {
    Plateau.nouveauPlateau();
    assertFalse(Plateau.getInstance().nbVoisin(Coordonnees.getCentre().getD().getD()) > 0);
  }

  @Test
  public final void testObtenirToutesCoordonnees() {
    Plateau.nouveauPlateau();
    assertTrue(Plateau.getInstance().obtenirToutesCoordonnees().size() == 1);
    Parcelle parcelle = new Parcelle(Couleurs.vert);
    Plateau.getInstance().placerParcelle(parcelle, Coordonnees.getCentre().getHD());
    assertTrue(Plateau.getInstance().obtenirToutesCoordonnees().size() == 2);
  }

  @Test
  public final void testIrrigationValides(){
    Plateau.nouveauPlateau();
    Parcelle parcelle1 = new Parcelle(Couleurs.jaune);
    Parcelle parcelle2 = new Parcelle(Couleurs.jaune);
    Plateau.getInstance().placerParcelle(parcelle1, Coordonnees.getCentre().getHD());
    Plateau.getInstance().placerParcelle(parcelle2, Coordonnees.getCentre().getHG());
    Irrigation irrigation1 = new Irrigation(Coordonnees.getCentre(),Coordonnees.getCentre().getHD());
    Irrigation irrigation2 = new Irrigation(Coordonnees.getCentre(),Coordonnees.getCentre().getHG());
    Irrigation irrigation3 = new Irrigation(Coordonnees.getCentre().getHD(),Coordonnees.getCentre().getHG());
    Irrigation irrigation4 = new Irrigation(Coordonnees.getCentre(),Coordonnees.getCentre().getD());
    assertFalse(Plateau.getInstance().irrigationValides(irrigation1));
    assertFalse(Plateau.getInstance().irrigationValides(irrigation2));
    assertTrue(Plateau.getInstance().irrigationValides(irrigation3));
    assertFalse(Plateau.getInstance().irrigationValides(irrigation4));
  }

  @Test
  public final void testTrouverComplement(){
    Plateau.nouveauPlateau();
    Parcelle parcelle1 = new Parcelle(Couleurs.jaune);
    Parcelle parcelle2 = new Parcelle(Couleurs.jaune);
    Parcelle parcelle3 = new Parcelle(Couleurs.jaune);
    Parcelle parcelle4 = new Parcelle(Couleurs.jaune);
    Parcelle parcelle5 = new Parcelle(Couleurs.jaune);
    Parcelle parcelle6 = new Parcelle(Couleurs.jaune);
    Plateau.getInstance().placerParcelle(parcelle1, Coordonnees.getCentre().getHD());
    Plateau.getInstance().placerParcelle(parcelle2, Coordonnees.getCentre().getD());
    Plateau.getInstance().placerParcelle(parcelle3, Coordonnees.getCentre().getBD());
    Plateau.getInstance().placerParcelle(parcelle4, Coordonnees.getCentre().getBG());
    Plateau.getInstance().placerParcelle(parcelle5, Coordonnees.getCentre().getG());
    Plateau.getInstance().placerParcelle(parcelle6, Coordonnees.getCentre().getHG());
    Irrigation irrigation1 = new Irrigation(Coordonnees.getCentre(),parcelle1.getCoordonnees());
    Irrigation irrigation2 = new Irrigation(Coordonnees.getCentre(),parcelle2.getCoordonnees());
    Irrigation irrigation3 = new Irrigation(Coordonnees.getCentre(),parcelle3.getCoordonnees());
    Irrigation irrigation4 = new Irrigation(Coordonnees.getCentre(),parcelle4.getCoordonnees());
    Irrigation irrigation5 = new Irrigation(Coordonnees.getCentre(),parcelle5.getCoordonnees());
    Irrigation irrigation6 = new Irrigation(Coordonnees.getCentre(),parcelle6.getCoordonnees());
    assertTrue(Arrays.asList(Plateau.getInstance().trouverComplementIntersections(irrigation1)).containsAll(
            Arrays.asList(new Coordonnees[] {parcelle6.getCoordonnees(),parcelle2.getCoordonnees()})));
    assertTrue(Arrays.asList(Plateau.getInstance().trouverComplementIntersections(irrigation2)).containsAll(
            Arrays.asList(new Coordonnees[] {parcelle1.getCoordonnees(),parcelle3.getCoordonnees()})));
    assertTrue(Arrays.asList(Plateau.getInstance().trouverComplementIntersections(irrigation3)).containsAll(
            Arrays.asList(new Coordonnees[] {parcelle2.getCoordonnees(),parcelle4.getCoordonnees()})));
    assertTrue(Arrays.asList(Plateau.getInstance().trouverComplementIntersections(irrigation4)).containsAll(
            Arrays.asList(new Coordonnees[] {parcelle3.getCoordonnees(),parcelle5.getCoordonnees()})));
    assertTrue(Arrays.asList(Plateau.getInstance().trouverComplementIntersections(irrigation5)).containsAll(
            Arrays.asList(new Coordonnees[] {parcelle4.getCoordonnees(),parcelle6.getCoordonnees()})));
    assertTrue(Arrays.asList(Plateau.getInstance().trouverComplementIntersections(irrigation6)).containsAll(
            Arrays.asList(new Coordonnees[] {parcelle5.getCoordonnees(),parcelle1.getCoordonnees()})));
  }

  @Test
  public final void testIrrigationAdjacente(){
    Plateau.nouveauPlateau();
    Parcelle parcelle1 = new Parcelle(Couleurs.jaune);
    Parcelle parcelle2 = new Parcelle(Couleurs.jaune);
    Parcelle parcelle3 = new Parcelle(Couleurs.jaune);
    assertTrue(Plateau.getInstance().placerParcelle(parcelle1, Coordonnees.getCentre().getHD()));
    assertTrue(Plateau.getInstance().placerParcelle(parcelle2, Coordonnees.getCentre().getD()));
    assertTrue(Plateau.getInstance().placerParcelle(parcelle3, Coordonnees.getCentre().getD().getHD()));
    Irrigation irrigation1 = new Irrigation(parcelle1.getCoordonnees(), parcelle2.getCoordonnees());
    Irrigation irrigation2 = new Irrigation(parcelle1.getCoordonnees(), parcelle3.getCoordonnees());
    Irrigation irrigation3 = new Irrigation(parcelle2.getCoordonnees(), parcelle3.getCoordonnees());
    assertTrue(Plateau.getInstance().ajouterIrrigation(irrigation1));
    // System.out.println(irrigation1.hashCode());
    assertTrue(Plateau.getInstance().contiensIrrigation(new Irrigation(parcelle1.getCoordonnees(), parcelle2.getCoordonnees())));
    assertTrue(Plateau.getInstance().irrigationAdjacente(irrigation2));
    assertTrue(Plateau.getInstance().irrigationAdjacente(irrigation3));
    assertFalse(Plateau.getInstance().irrigationAdjacente(new Irrigation(parcelle2.getCoordonnees(), parcelle2.getCoordonnees().getD())));

  }

  @Test
  public final void testPeutPlacerIrrigation(){
    Plateau.nouveauPlateau();
    Parcelle parcelle1 = new Parcelle(Couleurs.jaune);
    Parcelle parcelle2 = new Parcelle(Couleurs.jaune);
    Parcelle parcelle3 = new Parcelle(Couleurs.jaune);
    assertTrue(Plateau.getInstance().placerParcelle(parcelle1, Coordonnees.getCentre().getHD()));
    assertTrue(Plateau.getInstance().placerParcelle(parcelle2, Coordonnees.getCentre().getD()));
    assertTrue(Plateau.getInstance().placerParcelle(parcelle3, Coordonnees.getCentre().getD().getHD()));
    Irrigation irrigation1 = new Irrigation(parcelle1.getCoordonnees(), parcelle2.getCoordonnees());
    Irrigation irrigation2 = new Irrigation(parcelle1.getCoordonnees(), parcelle3.getCoordonnees());
    Irrigation irrigation3 = new Irrigation(parcelle2.getCoordonnees(), parcelle3.getCoordonnees());
    Irrigation irrigation4 = new Irrigation(Coordonnees.getCentre(), parcelle2.getCoordonnees());
    Irrigation irrigation5 = new Irrigation(parcelle3.getCoordonnees(), parcelle3.getCoordonnees().getD());
    Plateau.getInstance().ajouterIrrigation(irrigation1);
    assertTrue(Plateau.getInstance().peutPlacerIrrigation(irrigation2));
    assertTrue(Plateau.getInstance().peutPlacerIrrigation(irrigation3));
    assertFalse(Plateau.getInstance().peutPlacerIrrigation(irrigation4));
    assertFalse(Plateau.getInstance().peutPlacerIrrigation(irrigation5));
  }
}
