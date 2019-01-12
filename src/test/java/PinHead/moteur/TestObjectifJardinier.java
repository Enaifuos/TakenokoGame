package PinHead.moteur;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import PinHead.automate.Bob;
import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Parcelle;

public class TestObjectifJardinier {

	Couleurs[] rose = { Couleurs.rose };
	Couleurs[] jaune = { Couleurs.jaune };

	Objectif objectif = new ObjectifJardinier("Une pile de 3 bambous",3,rose,1);
	Objectif objectif2 = new ObjectifJardinier("Une pile de 3 bambous",3,jaune,1);

	@Before
	public final void Initialisation() {
		MaitreDuJeu.getInstance().instancierPlateau();
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.rose), Coordonnees.getCentre().getD());
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.rose), Coordonnees.getCentre().getG());
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune), Coordonnees.getCentre().getHD());
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.vert), Coordonnees.getCentre().getHG());
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.rose), Coordonnees.getCentre().getBD());
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.rose), Coordonnees.getCentre().getBG());
	}

	@Test
	public final void testTailleBambouSuffisanteAvecLaBonneCouleur() {

		// Mettant 3 bambous sur la parcelle
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());

		assertTrue(objectif.estValide(new Joueur("j1",new Bob("Bob"))));
	}

	@Test
	public final void testTailleBambouSuffisanteAvecMauvaiseCouleur() {

		// Mettant 3 bambous sur la parcelle
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());

		// Mettre un nombre suffisant de bambous sur la parcelle
		for ( int i = 0 ; i < ( (ObjectifJardinier)objectif ).getNbBambou() ; i++ ) {
			Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());
		}

		assertFalse(objectif2.estValide(new Joueur("j1",new Bob("Bob"))));
	}

	@Test
	public final void testTailleBambouNonSuffisante() {

		// Mettant 2 bambous sur la parcelle
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());

		assertFalse(objectif.estValide(new Joueur("j1",new Bob("Bob"))));
		assertFalse(objectif2.estValide(new Joueur("j1",new Bob("Bob"))));
	}

	@Test
	public final void testTailleBambouPlusGrandeQueLaTailleDemandee() {

		// Mettant 4 bambous sur la parcelle
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());
		Plateau.getInstance().PousserBambous(Coordonnees.getCentre().getD());

		assertFalse(objectif.estValide(new Joueur("j1",new Bob("Bob"))));
		assertFalse(objectif2.estValide(new Joueur("j1",new Bob("Bob"))));
	}


}
