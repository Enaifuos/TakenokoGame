package PinHead.moteur;

import java.util.Collection;

import PinHead.moteur.entites.Couleurs;

/**
 * 
 * @author soufianeaourinmouche
 *
 */

public class ObjectifJardinier extends Objectif implements interf {
	private int nbBambou ;
	
	/**
	 * 
	 * @param objectif : la description de l'objectif
	 * @param nbBambou : le nombre de bambou a avoir sur la parcelle
	 * @param couleurs : la couleur de la parcelle 
	 * @param points le nombre de points a gagner en validant cet objectif
	 */
	ObjectifJardinier(String objectif, int nbBambou, Couleurs[] couleurs, int points) {
		super(objectif, points, couleurs); 
		this.nbBambou = nbBambou;
	}
	
	/**
	 * Fonction pour récupérer le nombre de bambou à empiler pour valider cet objectif
	 * @return : le nombre de bambou a avoir sur la parcelle
	 */
	int getNbBambou() {
		return this.nbBambou;
	}
	
	/**
	 * @param : joueur qui valide l'objectif
	 * @return : true s'il existe une parcelle avec nbBambou de bambous dessus , false sinon
	 */
	@Override
	boolean estValide(Joueur joueur) {
		boolean valide = false ;
		Collection<Coordonnees> toutesLesCoordonnees = joueur.obtenirToutesCoordonnées();
		for ( Coordonnees coord : toutesLesCoordonnees ) {
			valide = ( super.comparerCouleurObjectif(coord, this.getCouleur()[0]) && 
							Plateau.getInstance().obtenirParcelle(coord).getTailleBambou() == this.nbBambou );
			if ( valide ) break ;
		}
		return valide ;
	}

	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("obj Jardinier vous dit Hello");
	}

	@Override
	public void sayBye() {
		// TODO Auto-generated method stub
		System.out.println("obj Jardinier vous dit bye");
	}

}
