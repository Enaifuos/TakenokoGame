package PinHead.moteur;

import PinHead.moteur.entites.Couleurs;

/**
 *
 * @author soufianeaourinmouche
 *
 */

public class ObjectifPanda extends Objectif implements interf{
	// Attributs
	private int nbBambouAManger ;

	/**
	 * Le constructeur
	 * @param objectif : description de l'objectif
	 * @param Bambou : nombre de bambou qu'il faut manger pour realiser l'objectif
	 * @param couleurs : tableau de couleurs pour realiser l'objectif
	 * @param points le nombre de points a gagner en validant cet objectif
	 */
	ObjectifPanda(String objectif,int Bambou, Couleurs[] couleurs, int points) {
		super(objectif,points, couleurs); 
		this.nbBambouAManger = Bambou ;
	}

	/** Fonction pour voir si un objectif est valide
	 * @param joueur  : le joueur qui valdie cet objectif
	 * @return true/false : valide ou pas
	 */
	boolean estValide(Joueur joueur){
		for (int i = 0; i < super.getCouleur().length; ++i){
			if(! (joueur.getNbBambou()[super.getCouleur()[i].ordinal()] >= nbBambouAManger)){
				return false;
			}
		}
		//super.valider(joueur);
		return true;
		// if ( joueur.getNbBambou() >= getNbBambouAManger() && getCouleur().equals(c)) {
		// 	super.valider(joueur);
		// 	return true ;
		// }
		// return false;
	}

	/**
	 * Fonction pour recuperer le nombre de bambou a manger pour valider cet objectif
	 * @return nombre de bambou a manger pour realiser cet objectif
	 */
	int getNbBambouAManger() {
		return this.nbBambouAManger ;
	}

	/**
	 * Fonction pour comparer si deux objectifs panda sont egaux
	 * @return true/false
	 */
	public boolean equals(Object object){
		 if ( !( object instanceof ObjectifPanda) ){
			 return false;
		 }
		 ObjectifPanda p = (ObjectifPanda)object ;
		 if ( super.getCouleur().length == p.getCouleur().length){
			 for ( int i = 0 ; i < super.getCouleur().length ; i++){
				 if ( ! super.getCouleur()[i].equals(p.getCouleur()[i])){
					 return false ;
				 }
			 }
			 return ( this.nbBambouAManger==p.nbBambouAManger)  ;
		 }
		 return false ;
	}

	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("obj Panda vous dit Hello");
	}

	@Override
	public void sayBye() {
		// TODO Auto-generated method stub
		System.out.println("obj Panda vous dit Bye");
	}

}
