package PinHead.moteur;

import PinHead.moteur.entites.Couleurs;

/**
 *
 * @author soufianeaourinmouche
 *
 */
public abstract class Objectif{
	/**
	 * Attributs
	 */
	private String objectif ; // Description de l'objectif ;
	private boolean valide  ; // objectif valide ou pas ?
	private int points ; // Nombre de points a ajouter en validant l'objectif
	private Couleurs[] couleurs;

	/**
	 *
	 * @param objectif : description de l'objectif
	 * @param points : le nombre de points que rapporte cet objectif si realise
	 * @param couleurs : le tableau de couleurs pour realiser cet objectif
	 */
	 Objectif(String objectif,int points, Couleurs[] couleurs){
		this.objectif = objectif ;
		this.valide = false ;
		this.points = points ;
		this.couleurs = couleurs;
	}

	/**
	 *
	 * @param j : le joueur qui a valide l'objectif
	 */
	 boolean valider(Joueur j){
		if (! this.valide && estValide(j)){
			this.valide = true;
			j.ajouterPoint(this.points); // Ajouter les points correspondants au joueur ayant valider l'objectif
			return true;
		}
		return false;
	}

	// n'a pas a exister
	//public abstract boolean estValide(Joueur joueur, Couleurs[] couleur);

	 /**
	  * Fonction pour recuperer le tableau de couleur d'un objectif
	  * @return  couleurs[] 
	  */
	 public Couleurs[] getCouleur() {
	 	return couleurs;
	 }

	/**
	 *
	 * @return nombre de points a gagner
	 */
	int getNbPoints(){
		return this.points ;
	}

	/**
	 * Fonction pour voir si l'objectif est valide ou pas
	 */
	public boolean getValide() {
		return this.valide;
	}

	/**
	 * Fonction pour recuperer la description de l'objectif
	 * @return String : la description de cet objectif 
	 */
	String getObjectif() {
		return this.objectif;
	}

	boolean estValide(Joueur joueur) {
		// TODO Auto-generated method stub
		return false;
	}
	
	 /**
     * 
     * @param coord : les coordonnees de la parcelle dont on veut comparer la couleur
     * @param couleur : la couleur de la parcelle courante de l'objectif
     * @return : true si la parcelle & la couleur de l'objectif correspondent 
     */
    public boolean comparerCouleurObjectif(Coordonnees coord , Couleurs couleur){
    	return couleur.equals(Plateau.getInstance().obtenirParcelle(coord).getCouleur());
    }

	@Override
	public String toString(){
		return objectif;
	}
}
