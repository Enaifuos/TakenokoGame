package PinHead.moteur;

import PinHead.moteur.entites.Parcelle;

/**
 *
 * @author soufianeaourinmouche
 *
 */

 class Panda{

	/**
	 * Attributs
	 */
	private Coordonnees position ;
	private static Panda panda ;
	/**
	 * Constructeur
	 */
	private Panda(){
		this.position = Coordonnees.getCentre(); // La position initiale du panda est celle de l'etang
	}

	/**
	 * Fonction pour instancier le Panda
	 * @return : Panda
	 */
	public static Panda getInstance(){
		return panda ;
	}
	
	/**
	 * Instancier un nouveau panda
	 */
	 static void nouveauPanda(){
		    panda = new Panda();
	 }


	/**
	 * Fonction pour deplacer le panda
	 * @param nouvellesCoord :les nouveles coordonnees
	 * @param joueur :  le joueur qui a deplacer le panda
	 */
	 boolean deplacer(Coordonnees nouvellesCoord, Joueur joueur){
		if ( Plateau.getInstance().coordonneesValides(nouvellesCoord) ){ // Si le deplacement est valide
			this.position = nouvellesCoord; // Changement de coordonnees
			manger(joueur) ; // Une fois deplace , le panda a faim
			return true ;
		}
		return false ;
	}

	/**
	 * Fonction pour manger un bambou 
	 * @param LeJoueur : le joueur qui a deplace le panda
	 */
	private void manger(Joueur LeJoueur){
		Parcelle parcelle = Plateau.getInstance().obtenirParcelle(this.position);
		if ( parcelle != null && parcelle.getTailleBambou() > 0){ // S'il y a un bambou a manger
			LeJoueur.ajouterBambou(parcelle.getCouleur()); // Rajouter le bambou a l'inventaire du joueur
			parcelle.recupererBambou(); // Supprimer le bambou mange sur la parcelle
		}
	}

	/**
	 *
	 * @return les coordonnees du panda
	 */
	 Coordonnees getPosition(){
		return this.position ;
	}


}
