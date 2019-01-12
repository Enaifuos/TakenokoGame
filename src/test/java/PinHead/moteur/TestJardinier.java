package PinHead.moteur;

import org.junit.Test;

import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Parcelle;
import org.junit.Ignore;

import static org.junit.Assert.* ;

public class TestJardinier {
	@Test
	public final void testDeplacerReussi(){

		// Instance du plateau
		MaitreDuJeu.getInstance().instancierPlateau();

		// Instance du jardinier
		Jardinier.nouveauJardinier();
		Jardinier.getInstance() ;

		// Des coordonnes valides
		Coordonnees coordValides = Jardinier.getInstance().getPosition().getBD();

		// Placer la parcelle sur le plateau
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordValides);

		// Déplacement valide
		assertTrue(Jardinier.getInstance().deplacer(coordValides)) ;
	}

	@Test
	public final void testDeplacementSurUneParcelleIrriguee(){
		// Instance du plateau
		MaitreDuJeu.getInstance().instancierPlateau();

		// Instance du jardinier
		Jardinier.nouveauJardinier();
		Jardinier.getInstance() ;

		// Des coordonnes valides
		Coordonnees coordValides = Jardinier.getInstance().getPosition().getBD();

		// Placer la parcelle sur le plateau
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordValides);

		// Taille bambou avant déplacement du jardinier
		assertEquals(Plateau.getInstance().obtenirParcelle(coordValides).getTailleBambou(),0);

		// Irrigation de la parcelle d'arrivée du jardinier
		Plateau.getInstance().obtenirParcelle(coordValides).setIrrigationTrue();

		// Déplacement du jardinier
		Jardinier.getInstance().deplacer(coordValides);

		// Taille bambou aprés déplacement du jardinier
		assertEquals(Plateau.getInstance().obtenirParcelle(coordValides).getTailleBambou(),1);
	}

    @Ignore
	@Test
	public final void testDeplacementSurUneParcelleNonIrriguee(){
		// Instance du plateau
		MaitreDuJeu.getInstance().instancierPlateau();

		// Instance du jardinier
		Jardinier.nouveauJardinier();
		Jardinier.getInstance() ;

		// Des coordonnes valides
		Coordonnees coordValides = Jardinier.getInstance().getPosition().getBD();

		// Placer la parcelle sur le plateau
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordValides);

		// Taille bambou avant déplacement du jardinier
		assertEquals(Plateau.getInstance().obtenirParcelle(coordValides).getTailleBambou(),0);

		// Vérifier que la parcelle n'est pas irriguée
		assertFalse(Plateau.getInstance().obtenirParcelle(coordValides).estIrriguée());

		// Déplacement du jardinier
		Jardinier.getInstance().deplacer(coordValides);

		// Taille bambou aprés déplacement du jardinier
		assertEquals(Plateau.getInstance().obtenirParcelle(coordValides).getTailleBambou(),0);
	}


	@Test
	public final void testDeplacerEchecParcelleNonPlaceeAvecCoordsValides(){

		// Instance du plateau
		MaitreDuJeu.getInstance().instancierPlateau();

		// Instance du jardinier
		Jardinier.nouveauJardinier();
		Jardinier.getInstance() ;

		// Des coordonnes valides
		Coordonnees coordValides = Jardinier.getInstance().getPosition().getBD();

		// On ne place pas la parcelle sur le plateau , même si les coords sont valides

		// Déplacement invalide
		assertFalse(Jardinier.getInstance().deplacer(coordValides)) ;

	}

	@Test
	public final void testDeplacerEchecParcelleNonPlaceeAvecCoordsInavalides(){

		// Instance du plateau
		MaitreDuJeu.getInstance().instancierPlateau();

		// Instance du jardinier
		Jardinier.nouveauJardinier();
		Jardinier.getInstance() ;

		// Des coordonnes valides
		Coordonnees HG = Jardinier.getInstance().getPosition().getHG();
		Coordonnees HD = Jardinier.getInstance().getPosition().getHD();
		Coordonnees HGHD = HG.getHD();

		// Les placer
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),HD);
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),HG);

		// Déplacement invalide
		assertFalse(Jardinier.getInstance().deplacer(HGHD)) ;

	}

	@Test
	public final void testDeplacerEchecParcellePlaceeAvecCoordsInvalides(){

		// Instance du plateau
		MaitreDuJeu.getInstance().instancierPlateau();

		// Instance du jardinier
		Jardinier.nouveauJardinier();
		Jardinier.getInstance() ;

		// Des coordonnes valides
        Coordonnees G = Jardinier.getInstance().getPosition().getG();
        Coordonnees GHG = G.getHG();

		Coordonnees HG = Jardinier.getInstance().getPosition().getHG();
		Coordonnees HD = Jardinier.getInstance().getPosition().getHD();

		// Les placer
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),HD);
		Plateau.getInstance().obtenirParcelle(HD).setIrrigationTrue();

        Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune), G);
        Plateau.getInstance().obtenirParcelle(G).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),HG);
		Plateau.getInstance().obtenirParcelle(HG).setIrrigationTrue();

        Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune), GHG);
        Plateau.getInstance().obtenirParcelle(GHG).setIrrigationTrue();


		// Déplacement invalide
		assertFalse(Jardinier.getInstance().deplacer(GHG)) ;

	}

	@Test
	public final void testFairePousserBambou(){

		// Instance du plateau
		MaitreDuJeu.getInstance().instancierPlateau();

		// Instance du jardinier
		Jardinier.nouveauJardinier();
		Jardinier.getInstance() ;

		// Des coordonnes valides
		Coordonnees coordPositionJardinier = Jardinier.getInstance().getPosition();

		// Placer la parcelle sur le plateau & tous ses voisins , les irriguées toutes
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordPositionJardinier);
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordPositionJardinier.getBD());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getBD()).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordPositionJardinier.getBG());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getBG()).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordPositionJardinier.getD());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getD()).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordPositionJardinier.getG());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getG()).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordPositionJardinier.getHD());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getHD()).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordPositionJardinier.getHG());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getHG()).setIrrigationTrue();

		// Vérifier que les parcelles n'aient aucun bambou avant déplacement du jardinier
		int tailleBD = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getBD()).getTailleBambou() ;
		int tailleBG = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getBG()).getTailleBambou() ;
		int tailleD = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getD()).getTailleBambou() ;
		int tailleG = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getG()).getTailleBambou() ;
		int tailleHD = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getHD()).getTailleBambou() ;
		int tailleHG = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getHG()).getTailleBambou() ;

		// Déplacer le jardinier
		Jardinier.getInstance().deplacer(coordPositionJardinier.getD()) ;

		// Vérifier que les bambous aient poussées aprés déplacement du jardinier
		assertEquals(Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getBD()).getTailleBambou(),tailleBD+1) ;
		assertEquals(Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getBG()).getTailleBambou(),tailleBG) ;
		assertEquals(Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getD()).getTailleBambou(),tailleD+1) ;
		assertEquals(Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getG()).getTailleBambou(),tailleG) ;
		assertEquals(Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getHD()).getTailleBambou(),tailleHD+1) ;
		assertEquals(Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getHG()).getTailleBambou(),tailleHG) ;
	}


	@Test
	public final void testFairePousserBambouCouleursDifferentes(){

		// Instance du plateau
		MaitreDuJeu.getInstance().instancierPlateau();

		// Instance du jardinier
		Jardinier.nouveauJardinier();
		Jardinier.getInstance() ;

		// Des coordonnes valides
		Coordonnees coordPositionJardinier = Jardinier.getInstance().getPosition();

		// Placer la parcelle sur le plateau & tous ses voisins , les irriguées toutes
		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordPositionJardinier);
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordPositionJardinier.getBD());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getBD()).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordPositionJardinier.getBG());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getBG()).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.rose),coordPositionJardinier.getD());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getD()).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.rose),coordPositionJardinier.getG());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getG()).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.rose),coordPositionJardinier.getHD());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getHD()).setIrrigationTrue();

		Plateau.getInstance().placerParcelle(new Parcelle(Couleurs.jaune),coordPositionJardinier.getHG());
		Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getHG()).setIrrigationTrue();

		// Vérifier que les parcelles n'aient aucun bambou avant déplacement du jardinier

		//int tailleHG = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getHG()).getTailleBambou() ;
		//int tailleBG = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getBG()).getTailleBambou() ;
		//int tailleG = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getG()).getTailleBambou() ;

		int tailleHD = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getHD()).getTailleBambou() ;
		int tailleD = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getD()).getTailleBambou() ;
		int tailleBD = Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getBD()).getTailleBambou() ;

		// Déplacer le jardinier
		Jardinier.getInstance().deplacer(coordPositionJardinier.getD()) ;

		// Vérifier que les bambous aient poussées aprés déplacement du jardinier
		assertEquals(Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getD()).getTailleBambou(),tailleD+1) ;
		assertEquals(Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getHD()).getTailleBambou(),tailleHD+1) ;
		assertEquals(Plateau.getInstance().obtenirParcelle(coordPositionJardinier.getBD()).getTailleBambou(),tailleBD) ;

	}

}
