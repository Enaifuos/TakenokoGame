package PinHead.moteur;

import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.FabriqueObjectifPanda;
import PinHead.moteur.FabriqueObjectifParcelle;

import java.util.* ;

/**
 *
 * @author soufianeaourinmouche
 *
 */
public class PiocheObjectifs {

	 private static PiocheObjectifs piocheObjectifs ;

	/* Attributs */

	private LinkedList<ObjectifPanda> piochePanda ; // Un deck pour stocker les objectifs panda
	private LinkedList<ObjectifParcelle> piocheParcelle ; // Un deck .. Parcelle 
	private LinkedList<ObjectifJardinier> piocheJardinier ; // Un deck .. Jardinier

	private FabriqueObjectifPanda objectifsPanda ;
	private FabriqueObjectifParcelle objectifsParcelle ;
	private FabriqueObjectifJardinier objectifsJardinier ;

	/* Constantes	*/
	private final int NBOBJECTIFSPANDA = 12 ;
	private final int NBOBJECTIFSPARCELLE = 12 ;
	private final int NBOBJECTIFJARDINIER = 12;
	
	/**
	 * Constructeur pour construire une pioche d'objectifs
	 */
	private PiocheObjectifs(){

		/*	Creation des 3 pioches	*/
		this.piochePanda = new LinkedList<ObjectifPanda>() ;
		this.piocheParcelle = new LinkedList<ObjectifParcelle>() ;
		this.piocheJardinier = new LinkedList<ObjectifJardinier>() ;

		/*	Declaration des fabriques	*/
		this.objectifsPanda = new FabriqueObjectifPanda() ;
		this.objectifsParcelle = new FabriqueObjectifParcelle() ;
		this.objectifsJardinier = new FabriqueObjectifJardinier() ;

		/*	Tous les objectifs Panda	*/
		Couleurs[] vert = {Couleurs.vert};
		Couleurs[] jaune = {Couleurs.jaune};
		Couleurs[] rose = {Couleurs.rose};
		Couleurs[] c = {Couleurs.jaune,Couleurs.rose,Couleurs.vert};
		

		this.piochePanda.addAll(this.objectifsPanda.CreerObjectifsPanda(2,5,vert,2));	// obj: manger 2 bambous verts , y'en a 5 exemplaires
		this.piochePanda.addAll(this.objectifsPanda.CreerObjectifsPanda(2,4,jaune,4));	// obj: manger 2 bambous jaunes , y'en a 4 exemplaires
		this.piochePanda.addAll(this.objectifsPanda.CreerObjectifsPanda(2,3,rose,5));	// obj: manger 2 bambous roses , y'en a 3 exemplaires
		//this.piochePanda.addAll(this.objectifsPanda.CreerObjectifsPanda(3,3,c,6));	// obj: manger (3 bambous) 1 bamboou de chaque couleur , y'en a 3 exemplaires

		/*	Tous les objectifs Parcelle	*/
		
		Couleurs[] verts = {Couleurs.vert,Couleurs.vert,Couleurs.vert};
		Couleurs[] jaunes = {Couleurs.jaune,Couleurs.jaune,Couleurs.jaune};
		Couleurs[] roses = {Couleurs.rose,Couleurs.rose,Couleurs.rose};
		
		// Objectifs parcelles lignes horizontales
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("LigneH",1,verts,2)); //  parcelles droites vertes
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("LigneH",1,jaunes,3)); //  parcelles droites jaunes
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("LigneH",1,roses,4)); //  parcelles droites roses

		// Objectifs parcelles en losange
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("Losange",1,verts,3)); // parcelles en diamond vertes
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("Losange",1,jaunes,4)); // parcelles en diamond jaunes
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("Losange",1,roses,5)); // parcelles en diamond roses

		// Objectifs parcelles en triangle vers le haut
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("TriangleH",1,verts,2)); // parcelles en triangle vertes
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("TriangleH",1,jaunes,3)); // parcelles en triangle jaunes
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("TriangleH",1,roses,4)); // parcelles en triangle roses

		// Objectifs parcelles en chevron droit
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("ChevronD",1,verts,2)); // parcelles en chevron vertes
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("ChevronD",1,jaunes,3)); // parcelles en chevron jaunes
		this.piocheParcelle.addAll(this.objectifsParcelle.CreerObjectifsParcelle("ChevronD",1,roses,4)); // parcelles en chevron roses
		
		
		// ILS MANQUENT LES OBJECTIFS PARCELLES EN LOSANGE AVEC DIFFERENTES COULEURS...

		
		/*	Tous les objectifs jardinier	*/
		/* le nombre de points de ces objectifs sont ceux d'un objectif avec engrais 
			Donc tous les objectifs de même couleur font gagner le même nombre points
			 ( 3 pour vert , 4 jaune , 5 rose )
		*/
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,vert,3)); // cense être avec amenagement 
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,vert,3)); // cense être avec amenagement
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,vert,3)); // cense être avec amenagement
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,vert,3));
		
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,jaune,4)); // cense être avec amenagement	
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,jaune,4)); // cense être avec amenagement
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,jaune,4)); // cense être avec amenagement
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,jaune,4));
		
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,rose,5)); // cense être avec amenagement
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,rose,5)); // cense être avec amenagement
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,rose,5)); // cense être avec amenagement
		this.piocheJardinier.add(this.objectifsJardinier.CreerObjectifJardinier(4,rose,5));
		
		
		/*	Melanger les pioches	*/
		this.melangerPiochePanda() ;
		this.melangerPiocheParcelle() ;
		this.melangerPiocheJardinier() ;

	}


	/**
	 * Fonction pour piocher
	 * @return un objectif ( panda , parcelle ou jardinier selon le parametre )
	 */
	 Objectif Piocher(TypesObjectifs choix){
		switch (choix) {
		case PANDA :
			if ( !PiochePandaEstVide()){
				return this.piochePanda.pollFirst();
			}
			break ;
		case PARCELLE :
			if ( !PiocheParcelleEstVide()){
				return this.piocheParcelle.pollFirst() ;
			}
			break ;
		case JARDINIER :
			if ( !PiocheJardinierEstVide()){
				return this.piocheJardinier.pollFirst() ;
			}
			break ;
		}
		return null ;
	}

	 
	/**
	* Fonction qui verifie si la pioche dans objectifs jardinier est vide
	* @return boolean : true si la pioche d'objectifsjardinier est vide , false sinon
	*/
	boolean PiocheJardinierEstVide() {
		return ( this.piocheJardinier.size() == 0 );
	}


	/**
	 * Fonction qui verifie si la pioche dans objectifs panda est vide
	 * @return boolean : true si la pioche d'objectif Panda est vide , false sinon
	 */
	 boolean PiochePandaEstVide(){
		return ( this.piochePanda.size() == 0 ) ;
	}

	/**
	 * Fonction qui verifie si la pioche dans objectifs parcelle est vide
	 * @return boolean : true si la pioche d'objectifs Parcelle est vide , false sinon
	 */
	 boolean PiocheParcelleEstVide(){
		return ( this.piocheParcelle.size() == 0 ) ;
	}

	/**
	 * Fonction qui retourne la taille de la pioche des objectifs panda
	 * @return int : la taille de la pioche d'objectifs Panda
	 */
	int getPiochePandaTaille(){
		return this.piochePanda.size();
	}

	/**
	 * Fonction qui retourne la taille de la pioche des objectifs parcelle
	 * @return int : la taille de la pioche d'objectifs parcelle
	 */
	int getPiocheParcelleTaille(){
		return this.piocheParcelle.size();
	}
	
	/**
	 * Fonction qui retourne la taille de la pioche des objectifs Jardinier
	 * @return int : la taille de la pioche d'objectifs jardinier
	 */
	int getPiocheJardinierTaille(){
		return this.piocheJardinier.size();
	}

	/**
	 * Fonction pour melanger la pioche des objectifs Panda
	 */
	 void melangerPiochePanda(){
		ObjectifPanda stock ;
		int index = 0 ;
		Random random = new Random(System.nanoTime());
		for ( int i = 0 ; i < getPiochePandaTaille() ; i ++){
			index = random.nextInt(getPiochePandaTaille()) ;
			stock = piochePanda.get(index) ;
			piochePanda.set(index, piochePanda.get(i)) ;
			piochePanda.set(i, stock) ;
		}
	}

	/**
	 * Fonction pour melanger la pioche des objectifs Parcelle
	 */
	 void melangerPiocheParcelle(){
		ObjectifParcelle stock ;
		int index = 0 ;
		Random random = new Random(System.nanoTime());
		for ( int i = 0 ; i < getPiocheParcelleTaille() ; i ++){
			index = random.nextInt(getPiocheParcelleTaille()) ;
			stock = piocheParcelle.get(index) ;
			piocheParcelle.set(index, piocheParcelle.get(i)) ;
			piocheParcelle.set(i, stock) ;
		}
	}
	 
	 /**
	  * Fonction pour melanger la pioche des objectifs Jardinier
	  */
	 void melangerPiocheJardinier() {
		ObjectifJardinier stock ;
		int index = 0 ;
		Random random = new Random(System.nanoTime());
		for ( int i = 0 ; i < getPiocheJardinierTaille() ; i ++){
			index = random.nextInt(getPiocheJardinierTaille()) ;
			stock = piocheJardinier.get(index) ;
			piocheJardinier.set(index, piocheJardinier.get(i)) ;
			piocheJardinier.set(i, stock) ;
		}
	 }


	/**
	 * Fonction pour recuperer le nombre d'objectifs Panda
	 * @return nombre d'objectifs panda dans la pioche
	 */
	int getNbObjectifsPanda(){
		return this.NBOBJECTIFSPANDA ;
	}

	/**
	 * Fonction qui verifie si p est contenu dans la pioche ( cad il est fabrique )
	 * @param p : objectifPanda recherche
	 * @return true/false
	 */
	boolean ObjectifPandaContenu(ObjectifPanda p){
		return this.piochePanda.contains(p) ;
	}

	/**
	 * Fonction pour recuperer le nombre d'examplaires d'un objectif Panda
	 * @param p : l'objectif panda dont on veut calculer le nombre d'examplaires
	 * @return int : le nombre d'examplaires de l'objectif Panda passe en parametre
	 */
	int NbExamplaireObjectifPanda(ObjectifPanda p){
		int nb = 0 ;
		for ( ObjectifPanda op : this.piochePanda ){
			if ( op.equals(p) ) {
				nb ++ ;
			}
		}
		return nb ;
	}

	/**
	 * Fonction pour recuperer le nombre d'objectifs parcelle
	 * @return nombre d'objectifs parcelle dans la pioche
	 */
	 int getNbObjectifsParcelle(){
		return this.NBOBJECTIFSPARCELLE ;
	}
	 

	 /**
	  * Fonction pour recuperer le nombre d'objectifs jardinier
	  * @returnnombre d'objectifs jardinier dans la pioche
	  */
	public int getNbObjectifsJardinier() {
		return this.NBOBJECTIFJARDINIER;
	}

/**
 * Fonction pour instancier la pioche d'objectifs
 * @return
 */
	 static PiocheObjectifs getInstance(){
		return piocheObjectifs ;
	 }

	static void nouvellePiocheObjectif(){
		piocheObjectifs = new PiocheObjectifs();
	}

}
