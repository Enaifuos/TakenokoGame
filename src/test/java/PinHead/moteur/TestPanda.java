package PinHead.moteur;

import PinHead.automate.Bob;
import PinHead.moteur.Coordonnees;
import PinHead.moteur.Joueur;
import PinHead.moteur.Panda;
import PinHead.moteur.Plateau;
import org.junit.Test;

import static org.junit.Assert.* ;

public class TestPanda {
	@Test
	public final void testConstructeur(){
		Panda.nouveauPanda() ;
		assertTrue(Panda.getInstance()!=null) ;
	}


	//test arrêté pour absence de Joueur
	@Test
	public final void testDeplacer(){
		//Plateau.nouveauPlateau();
		MaitreDuJeu.getInstance().instancierPlateau();
		// Le panda
		Panda.nouveauPanda();
		Panda.getInstance() ;

		// Le joueur & le bot
		Joueur joueur =  new Joueur("Joueur 1", new Bob("Bob"));

		// Les coordonnées
		Coordonnees coord = new Coordonnees(0,1) ;

		// Le Déplacement
		assertFalse(Panda.getInstance().deplacer(coord,joueur)) ;
	}


	//test arrêté pour absence de Joueur
		@Test
		public final void testDeplacerFAUX(){
			Plateau.nouveauPlateau();
			// Le panda
			Panda.nouveauPanda();
			Panda.getInstance() ;

			// Le joueur & le bot
			Joueur joueur =  new Joueur("Joueur 1", new Bob("Bob")) ;


			// Les coordonnées
			Coordonnees coord = new Coordonnees(1,1) ; // la position du panda est 0,0 il peut pas aller a 1,1

			// Le Déplacement
			assertFalse(Panda.getInstance().deplacer(coord,joueur)) ;
		}


}
