package PinHead.moteur;

import PinHead.moteur.PiocheObjectifs;
import PinHead.moteur.entites.* ;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author soufianeaourinmouche
 *
 */
public class TestPiocheObjectifs {

	@Test
	public final void TestTaillePioche(){
		
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		
		/*	Tester qu'on a fabriqué 15 = getNbObjectifsPanda() objectifs panda	*/
		assertFalse(pioche.PiochePandaEstVide()) ; 
		assertFalse(pioche.PiocheParcelleEstVide()) ;
		assertFalse(pioche.PiocheJardinierEstVide()) ;
		assertEquals(pioche.getPiochePandaTaille(),pioche.getNbObjectifsPanda()) ; 
		assertEquals(pioche.getPiocheParcelleTaille(),pioche.getNbObjectifsParcelle()) ;
		assertEquals(pioche.getPiocheJardinierTaille(),pioche.getNbObjectifsJardinier()) ;
	}
	
	
	@Test
	public final void TestActionPiocherPanda(){
		
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		
		/*	Tester qu'on pioche bien un objectifPanda	*/
		Objectif o = null ;
		
		/*	S'assurer que la pioche contient 15 = getNbObjectifsPanda() objectifs avant de piocher	*/
		assertEquals(pioche.getPiochePandaTaille(),pioche.getNbObjectifsPanda()) ;
		
		/*	Piocher	un objectif	*/
		TypesObjectifs t = TypesObjectifs.valueOf("PANDA") ;
		o = pioche.Piocher(t) ;
		
		/*	Tester que la pioche n'est pas null , et que la taille a diminué	*/
		assertFalse(o==null) ;
		assertEquals(pioche.getPiochePandaTaille(),pioche.getNbObjectifsPanda()-1) ;
	}
	
	@Test
	public final void TestActionPiocherParcelle(){
		
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		
		/*	Tester qu'on pioche bien un objectifParcelle	*/
		Objectif o = null ;
		
		/*	S'assurer que la pioche contient 9 = getNbObjectifsPanda() objectifs avant de piocher	*/
		assertEquals(pioche.getPiocheParcelleTaille(),pioche.getNbObjectifsParcelle()) ;
		
		/*	Piocher	un objectif	*/
		TypesObjectifs t = TypesObjectifs.PARCELLE ;
		o = pioche.Piocher(t) ;
		
		/*	Tester que la pioche n'est pas null , et que la taille a diminué	*/
		assertFalse(o==null) ;
		assertEquals(pioche.getPiocheParcelleTaille(),pioche.getNbObjectifsParcelle()-1) ;
	}
	
	
	@Test
	public final void TestActionPiocherJardinier(){
		
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		
		/*	Tester qu'on pioche bien un objectifJardinier	*/
		Objectif o = null ;
		
		assertEquals(pioche.getPiocheJardinierTaille(),pioche.getNbObjectifsJardinier()) ;
		
		/*	Piocher	un objectif	*/
		TypesObjectifs t = TypesObjectifs.JARDINIER ;
		o = pioche.Piocher(t) ;
		
		/*	Tester que la pioche n'est pas null , et que la taille a diminué	*/
		assertFalse(o==null) ;
		assertEquals(pioche.getPiocheJardinierTaille(),pioche.getNbObjectifsJardinier()-1) ;
	}
	
	
	@Test
	public final void TestActionPiocherPandaImpossible(){
		
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		
		/*	S'assurer que la pioche contient 15 = getNbObjectifsPanda() objectifs avant de piocher	*/
		assertEquals(pioche.getPiochePandaTaille(),pioche.getNbObjectifsPanda()) ;
		assertFalse(pioche.PiochePandaEstVide()) ;
		
		TypesObjectifs t = TypesObjectifs.PANDA ;
		
		for ( int i = 0 ; i < pioche.getNbObjectifsPanda()-1 ; i++){
			pioche.Piocher(t) ;
		}
		
		/*	Tester le cas ou on pioche dans une pioche vide	*/
		assertTrue(pioche.Piocher(t) != null) ; // La dernière pioche
		assertTrue(pioche.Piocher(t) == null) ; // et plus de pioche possible 
		assertEquals(pioche.getPiochePandaTaille(),0) ;
		assertTrue(pioche.PiochePandaEstVide()) ;
	}
	
	@Test
	public final void TestActionPiocherParcelleImpossible(){
		
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		
		/*	S'assurer que la pioche contient 9 = getNbObjectifsParcelle() objectifs avant de piocher	*/
		assertEquals(pioche.getPiocheParcelleTaille(),pioche.getNbObjectifsParcelle()) ;
		assertFalse(pioche.PiocheParcelleEstVide()) ;
		
		TypesObjectifs t = TypesObjectifs.PARCELLE ;
		
		for ( int i = 0 ; i < pioche.getNbObjectifsParcelle()-1 ; i++){
			pioche.Piocher(t) ;
		}
		
		/*	Tester le cas ou on pioche dans une pioche vide	*/
		assertTrue(pioche.Piocher(t) != null) ; // La dernière pioche 
		assertTrue(pioche.Piocher(t) == null) ; // et plus de pioche possible 
		assertEquals(pioche.getPiocheParcelleTaille(),0) ;
		assertTrue(pioche.PiocheParcelleEstVide()) ;
	}
	
	@Test
	public final void TestActionPiocherJardinierImpossible(){
		
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		
		assertEquals(pioche.getPiocheJardinierTaille(),pioche.getNbObjectifsJardinier()) ;
		assertFalse(pioche.PiocheJardinierEstVide()) ;
		
		TypesObjectifs t = TypesObjectifs.JARDINIER ;
		
		for ( int i = 0 ; i < pioche.getNbObjectifsJardinier()-1 ; i++){
			pioche.Piocher(t) ;
		}
		
		/*	Tester le cas ou on pioche dans une pioche vide	*/
		assertTrue(pioche.Piocher(t) != null) ; // La dernière pioche 
		assertTrue(pioche.Piocher(t) == null) ; // et plus de pioche possible 
		assertEquals(pioche.getPiocheJardinierTaille(),0) ;
		assertTrue(pioche.PiocheJardinierEstVide()) ;
	}
	
	
	@Test
	public final void TestMelangerPiochePanda(){
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		
		/*	S'assurer que la pioche contient 15 = getNbObjectifsPanda() objectifs	*/
		assertEquals(pioche.getPiochePandaTaille(),pioche.getNbObjectifsPanda()) ;
		assertFalse(pioche.PiochePandaEstVide()) ;
		
		pioche.melangerPiochePanda() ;
		
		/*	S'assurer que la pioche n'a pas perdu d'éléments	*/
		assertEquals(pioche.getPiochePandaTaille(),pioche.getNbObjectifsPanda()) ;
	}
	
	@Test
	public final void TestMelangerPiocheParcelle(){
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		
		/*	S'assurer que la pioche contient 9 = getNbObjectifsPanda() objectifs	*/
		assertEquals(pioche.getPiocheParcelleTaille(),pioche.getNbObjectifsParcelle()) ;
		assertFalse(pioche.PiocheParcelleEstVide()) ;
		
		pioche.melangerPiocheParcelle() ;
		
		/*	S'assurer que la pioche n'a pas perdu d'éléments	*/
		assertEquals(pioche.getPiocheParcelleTaille(),pioche.getNbObjectifsParcelle()) ;
	}
	
	@Test
	public final void TestMelangerPiocheJardinier(){
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		
		assertEquals(pioche.getPiocheJardinierTaille(),pioche.getNbObjectifsJardinier()) ;
		assertFalse(pioche.PiocheJardinierEstVide()) ;
		
		pioche.melangerPiocheJardinier() ;
		
		/*	S'assurer que la pioche n'a pas perdu d'éléments	*/
		assertEquals(pioche.getPiocheJardinierTaille(),pioche.getNbObjectifsJardinier()) ;
	}
	
	@Test
	public final void TestMelangerPiochePandaComparaison(){
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche1 = PiocheObjectifs.getInstance();
		
		/*	S'assurer que la pioche contient 15 = getNbObjectifsPanda() objectifs	*/
		assertEquals(pioche1.getPiochePandaTaille(),pioche1.getNbObjectifsPanda()) ;
		
		/* Récuperer les 3 premiers objectifs avant de mélanger	*/
		Objectif obj1 = pioche1.Piocher(TypesObjectifs.PANDA) ;
		Objectif obj2 = pioche1.Piocher(TypesObjectifs.PANDA) ;
		Objectif obj3 = pioche1.Piocher(TypesObjectifs.PANDA) ;
		
		/*	Créer une deuxième pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche2 = PiocheObjectifs.getInstance();
		
		/*	S'assurer que la pioche contient 15 = getNbObjectifsPanda() objectifs	*/
		assertEquals(pioche2.getPiochePandaTaille(),pioche2.getNbObjectifsPanda()) ;
		
		/*	S'assurer que au moins l'ordre des 3 premiers objectifs n'est pas le même	*/
		assertTrue(pioche2.Piocher(TypesObjectifs.PANDA)!=obj1 || pioche2.Piocher(TypesObjectifs.PANDA)!=obj2 || pioche2.Piocher(TypesObjectifs.PANDA)!=obj3);
	}
	
	@Test
	public final void TestMelangerPiocheParcelleComparaison(){
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche1 = PiocheObjectifs.getInstance();
		
		/*	S'assurer que la pioche contient 9 = getNbObjectifsParcelle() objectifs	*/
		assertEquals(pioche1.getPiocheParcelleTaille(),pioche1.getNbObjectifsParcelle()) ;
		
		/* Récuperer les 3 premiers objectifs avant de mélanger	*/
		Objectif obj1 = pioche1.Piocher(TypesObjectifs.PARCELLE) ;
		Objectif obj2 = pioche1.Piocher(TypesObjectifs.PARCELLE) ;
		Objectif obj3 = pioche1.Piocher(TypesObjectifs.PARCELLE) ;
		
		/*	Créer une deuxième pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche2 = PiocheObjectifs.getInstance();
		
		/*	S'assurer que la pioche contient 15 = getNbObjectifsParcelle() objectifs	*/
		assertEquals(pioche2.getPiocheParcelleTaille(),pioche2.getNbObjectifsParcelle()) ;
		
		/*	S'assurer que au moins l'ordre des 3 premiers objectifs n'est pas le même	*/
		assertTrue(pioche2.Piocher(TypesObjectifs.PARCELLE)!=obj1 || pioche2.Piocher(TypesObjectifs.PARCELLE)!=obj2 || pioche2.Piocher(TypesObjectifs.PARCELLE)!=obj3);
	}
	
	@Test
	public final void TestMelangerPiocheJardinierComparaison(){
		/*	Créer la pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche1 = PiocheObjectifs.getInstance();
		
		assertEquals(pioche1.getPiocheParcelleTaille(),pioche1.getNbObjectifsParcelle()) ;
		
		/* Récuperer les 3 premiers objectifs avant de mélanger	*/
		Objectif obj1 = pioche1.Piocher(TypesObjectifs.JARDINIER) ;
		Objectif obj2 = pioche1.Piocher(TypesObjectifs.JARDINIER) ;
		Objectif obj3 = pioche1.Piocher(TypesObjectifs.JARDINIER) ;
		
		/*	Créer une deuxième pioche	*/
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche2 = PiocheObjectifs.getInstance();
		
		assertEquals(pioche2.getPiocheJardinierTaille(),pioche2.getNbObjectifsJardinier()) ;
		
		/*	S'assurer que au moins l'ordre des 3 premiers objectifs n'est pas le même	*/
		assertTrue(pioche2.Piocher(TypesObjectifs.JARDINIER)!=obj1 || pioche2.Piocher(TypesObjectifs.JARDINIER)!=obj2 || pioche2.Piocher(TypesObjectifs.JARDINIER)!=obj3);
	}
	
	private void testTousObjectifsPanda(ObjectifPanda p){
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		assertTrue(pioche.ObjectifPandaContenu(p)) ;
	}
	
	private void testNbExamplaireObjectifsPanda(ObjectifPanda p,int e){
		PiocheObjectifs.nouvellePiocheObjectif();
		PiocheObjectifs pioche = PiocheObjectifs.getInstance();
		assertEquals(pioche.NbExamplaireObjectifPanda(p),e) ;
	}
	
	@Test
	public final void testTousObjectifsPanda(){
		
		/*	Toutes les couleurs en array	*/
		Couleurs[] vert = {Couleurs.vert};
		Couleurs[] jaune = {Couleurs.jaune};
		Couleurs[] rose = {Couleurs.rose};
		Couleurs[] c = {Couleurs.jaune,Couleurs.rose,Couleurs.vert};
		

		/*	Tous les objectifs Panda	*/
		ObjectifPanda p = new ObjectifPanda("",2,vert,1) ;
		testTousObjectifsPanda(p) ;
		testNbExamplaireObjectifsPanda(p,5) ;
		
		p = new ObjectifPanda("",2,jaune,1) ;
		testTousObjectifsPanda(p) ;
		testNbExamplaireObjectifsPanda(p,4) ;
		
		p = new ObjectifPanda("",2,rose,1) ;
		testTousObjectifsPanda(p) ;
		testNbExamplaireObjectifsPanda(p,3) ;
		
		// Cet objectif testé a été viré de la pioche 
		/*p = new ObjectifPanda("",3,c) ;
		testTousObjectifsPanda(p) ;
		testNbExamplaireObjectifsPanda(p,3) ;*/
	}
	
}