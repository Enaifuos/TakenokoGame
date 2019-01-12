package PinHead.moteur;

import PinHead.moteur.Joueur;
import PinHead.moteur.ObjectifParcelle;
import PinHead.moteur.entites.Chemin;
import PinHead.moteur.entites.Couleurs;
import PinHead.automate.Bob;
import PinHead.moteur.entites.Directions;
import org.junit.Test;

import java.util.ArrayList;

import static PinHead.moteur.entites.Couleurs.vert;
import static org.junit.Assert.* ;

/**
 *
 * @author soufianeaourinmouche
 *
 */
public class TestObjectifPanda {
	@Test
	public final void testConstructeur(){
		String desc = "test" ;
		int nbBambou = 2 ;
		Couleurs[] c = {Couleurs.jaune};
		ObjectifPanda op = new ObjectifPanda(desc,nbBambou,c,1);

		assertFalse(op==null) ;
		assertEquals(op.getObjectif(),desc) ;
		assertEquals(op.getNbBambouAManger(),nbBambou) ;
		assertEquals(op.getNbPoints(),1) ; // parce que tout objectif fait gagner 1 points pour l'instant
	}


	@Test
	public final void testValider(){

		// L'objectif
		int nbBambou = 1 ;
		int nbPointsAgagner = 7;
		Couleurs[] c = {Couleurs.rose};
		ObjectifPanda op = new ObjectifPanda("test",nbBambou,c,nbPointsAgagner) ;

		// robot & joueur
		Joueur joueur = new Joueur("Joueur 1", new Bob("Bob"));

		// Un nombre suffisant de bambous
		joueur.ajouterBambou(Couleurs.rose);
		joueur.ajouterBambou(Couleurs.rose);

		// Le nombre de points du jouer au départ
		int pointsDuJoueurDepart = joueur.getNbPoints() ;

		assertTrue(op.valider(joueur)); // Objectif validé dans ce cas
		assertEquals(joueur.getNbBambou()[Couleurs.rose.ordinal()],2); // Le joueur a 2 bambou dans l'inventaire dans la case rose
		assertEquals(op.getNbPoints(),nbPointsAgagner);
		assertEquals(joueur.getNbPoints(),pointsDuJoueurDepart+op.getNbPoints()); // Le joueur a bien augumenté ses points
	}

	@Test
	public final void testValiderFAUXpourCouleurDifferent(){

		// L'objectif
		int nbBambou = 1 ;
		Couleurs[] c = {Couleurs.rose};
		ObjectifPanda op = new ObjectifPanda("test",nbBambou,c,1) ;

		// robot & joueur
		Joueur joueur = new Joueur("Joueur 1", new Bob("Bob"));

		// Un nombre suffisant de bambous
		joueur.ajouterBambou(Couleurs.jaune);
		joueur.ajouterBambou(Couleurs.jaune);

		// Le nombre de points du jouer au départ
		int pointsDuJoueurDepart = joueur.getNbPoints() ;

		assertFalse(op.estValide(joueur)); // Objectif validé dans ce cas
		assertEquals(joueur.getNbBambou()[Couleurs.jaune.ordinal()],2); // Le joueur a 2 bambou dans l'inventaire
		assertEquals(joueur.getNbPoints(),pointsDuJoueurDepart); // Le joueur n'a pas augumenté ses points

	}

	@Test
	public final void testValiderFAUXpourCouleurEgaleMaisNombreBambouDifferent(){

		// L'objectif
		int nbBambou = 3 ;
		Couleurs[] c = {Couleurs.rose};
		ObjectifPanda op = new ObjectifPanda("test",nbBambou,c,1) ;

		// robot & joueur
		Joueur joueur = new Joueur("Joueur 1", new Bob("Bob"));

		// Un nombre suffisant de bambous
		joueur.ajouterBambou(Couleurs.rose);
		joueur.ajouterBambou(Couleurs.rose);

		// Le nombre de points du jouer au départ
		int pointsDuJoueurDepart = joueur.getNbPoints() ;

		assertFalse(op.estValide(joueur)); // Objectif validé dans ce cas
		assertEquals(joueur.getNbBambou()[Couleurs.rose.ordinal()],2); // Le joueur a 2 bambou dans l'inventaire
		assertEquals(joueur.getNbPoints(),pointsDuJoueurDepart); // Le joueur n'a pas augumenté ses points

	}

	@Test
	public final void testValiderFAUX(){

		// L'objectif
		int nbBambou = 3 ;
		Couleurs[] c = {vert};
		ObjectifPanda op = new ObjectifPanda("test",nbBambou,c,1) ;

		// Le robot et joueur
		Joueur joueur = new Joueur("Joueur 1", new Bob("Bob"));

		// Un nombre INSUFFISANT de bambous
		joueur.ajouterBambou(vert);
		joueur.ajouterBambou(vert);

		// Test
		assertFalse(op.getValide());
		assertTrue(joueur.getNbBambou()[vert.ordinal()]<op.getNbBambouAManger());
		assertFalse(op.estValide(joueur)); // Objectif non validé dans ce cas
		assertTrue(joueur.getNbBambou()[vert.ordinal()]==2);
		assertFalse(joueur.getNbPoints()>0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public final void testCouleur(){
		Couleurs[] col = {Couleurs.valueOf("vert")};
		ObjectifPanda op = new ObjectifPanda("test",3,col,1) ;
		Couleurs[] c = new Couleurs[1] ;
		c[0] = vert ;
		assertEquals(op.getCouleur(),c) ;
		c[0] = Couleurs.jaune ;
		assertNotEquals(op.getCouleur(),c) ;
	}

	@Test
	public final void testEquals(){
		Couleurs[] couleurJaune = {Couleurs.jaune};
		Couleurs[] couleurRose = {Couleurs.rose};

		ObjectifPanda op = new ObjectifPanda("test",3,couleurJaune,1) ;
		ObjectifPanda opEqual = new ObjectifPanda("test",3,couleurJaune,1) ;
		ObjectifPanda opNotEqualColor = new ObjectifPanda("test",3,couleurRose,1) ;
		ObjectifPanda opNotEqualValue = new ObjectifPanda("test",2,couleurJaune,1) ;

		Chemin chemin = new Chemin(new ArrayList<Directions>(){{add(Directions.D);
																add(Directions.G);
																add(Directions.G);}});
		ObjectifParcelle oparcelle = new ObjectifParcelle("",chemin,couleurJaune,1) ;

		assertTrue(op.equals(opEqual)) ;
		assertFalse(op.equals(opNotEqualColor)) ;
		assertFalse(op.equals(opNotEqualValue)) ;
		assertFalse(op.equals(oparcelle)) ;
	}

	@Test
	public final void testToString() {
		FabriqueObjectifPanda fab = new FabriqueObjectifPanda();
		Couleurs[] c = {Couleurs.rose};
		ObjectifPanda objectif = fab.CreerObjectifPanda(1, c,1);
		String desc = "Manger 1 bambous rose";
		assertTrue(objectif.toString().equals(desc));
	}

}
