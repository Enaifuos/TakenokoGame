package PinHead.automate;


import PinHead.moteur.MaitreDeTest;
import PinHead.moteur.Joueur;

import PinHead.moteur.utils.Afficheur;

import PinHead.moteur.Coordonnees;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;


public class TestBob {
  Joueur joueur;

  @Before
  public final void init(){
    Afficheur.nouveauAfficheur();
    // Afficheur.getInstance().setInfo(false);
    // Afficheur.getInstance().setActions(false);
    MaitreDeTest.getInstance().miseAZero();
    MaitreDeTest.getInstance().setNbObjectifs0joueurs(3);
    MaitreDeTest.getInstance().instancierPlateau();
    MaitreDeTest.getInstance().instancierPanda();
    MaitreDeTest.getInstance().instancierJardinier();
    MaitreDeTest.getInstance().instancierPiocheParcelle();
    MaitreDeTest.getInstance().instancierPiocheObjectifs();
    joueur = new Joueur("Bob", new Bob("Bob"));
    MaitreDeTest.getInstance().ajouterJoueur(joueur);
  }

  @Ignore
  @Test
  public final void testPartie(){
    for (int i = 0; i < 100; ++i){
      init();
      MaitreDeTest.getInstance().lancerPartie();
      assertTrue(joueur.nbVoisins(Coordonnees.getCentre())>0);
      assertTrue(joueur.getNbPoints() >= 1);
      assertTrue(joueur.ObtenirObjectifsValides().size() > 1);
    }
  }

  @Ignore
  @Test
  public final void loop(){
    joueur = new Joueur("Bob", new Bob("Bob"));
    for (int i = 0; i < 100; ++i){
      init();
      TestJouer();
    }
  }

  private final void TestJouer(){
    joueur.jouer(3);
    assertTrue(joueur.nbVoisins(Coordonnees.getCentre())>0);
    assertTrue(! joueur.obtenirCoordonneesPanda().equals(Coordonnees.getCentre()));
  }
}
