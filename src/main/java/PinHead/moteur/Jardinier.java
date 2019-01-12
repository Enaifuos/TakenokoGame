package PinHead.moteur;

import java.util.* ;

import PinHead.moteur.entites.Couleurs;

/**
 *
 * @author soufianeaourinmouche
 *
 */
 class Jardinier {
	/*	Attributs	*/
	private Coordonnees position ;
	private static Jardinier instance ;

	/*	Constructeurs	*/
	private Jardinier(){
		this.position = Coordonnees.getCentre() ; // Le jardinier est place initialement sur l'etang ( le centre )
	}

	/**
	 * Fonction pour deplacer le jardinir
	 * @param coord : les coordonnes oû deplacer le jardinier
	 * @param j : le joueur qui deplacer le jardinier
	 * @return boolean : true si deplacement effectue , false sinon
	 */
	 boolean deplacer(Coordonnees coord ){
		if ( Plateau.getInstance().coordonneesValides(coord) && coord.estSurLaLigne(this.position)){
			this.position = coord ; // Changer la position
			fairePousserBambou() ; // Faire pousser les bambous
			return true ;
		}
		return false ;
	}

	/**
	 * Fonction pour faire pousser un bambou sur les parcelles adjacentes de sa position( apres y avoir deplacer le jardinier )
	 * @param coord : les coordonnes ou faire pousser un bambou
	 * @return boolean : true si on y a fait pousse un bambou , false sinon
	 */
	private void fairePousserBambou(){

		// Liste de tous les voisins
		ArrayList<Coordonnees> voisins = new ArrayList<Coordonnees>(Plateau.getInstance().nbVoisin(this.position)) ;
		voisins.add(this.position.getBD()) ;
		voisins.add(this.position.getBG()) ;
		voisins.add(this.position.getD()) ;
		voisins.add(this.position.getG()) ;
		voisins.add(this.position.getHD()) ;
		voisins.add(this.position.getHG()) ;

		// Recuperer la couleur de la parcelle position du jardinier
		Couleurs couleurParcellePosition = Plateau.getInstance().obtenirParcelle(position).getCouleur() ;

		// Faire pousser un bambou dans la parcelle position du jardinier si elle est irriguee
		if ( Plateau.getInstance().obtenirParcelle(this.position).estIrriguée()){
			Plateau.getInstance().PousserBambous(this.position);
		}

		for ( Coordonnees coord : voisins){
			// Si la parcelle est placee et a la même couleur que la position du jardinier et irriguee
			if ( Plateau.getInstance().coordonneesValides(coord) &&
					Plateau.getInstance().obtenirParcelle(coord).getCouleur().equals(couleurParcellePosition)
						&& Plateau.getInstance().obtenirParcelle(coord).estIrriguée()){
				Plateau.getInstance().PousserBambous(coord);
			}
		}
	}

	/**
	 * Recuperer la position du jardinier
	 * @return Coordonnes : la position du jardinier
	 */
	 Coordonnees getPosition(){
		return this.position ;
	}

	/**
	 * Instancier un nouveau jardinier
	 */
	static void nouveauJardinier(){
		instance = new Jardinier() ;
	}

	public static Jardinier getInstance(){
		return instance ;
	}
}
