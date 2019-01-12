package PinHead.moteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import PinHead.moteur.entites.Chemin;
import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Directions;

/**
 * @author Maxime Bouis
 */
public class ObjectifParcelle extends Objectif implements interf{

	private Chemin chemin;

	/**
	 * Le constructeur
	 * @param objectif la description
	 * @param chemin un tableau contenant les directions de la figure a partir de l'origine
	 * @param couleurs un tableau contenant les couleurs des parcelles de la figure
	 * @param points le nombre de points a gagner en validant cet objectif
	 */
	public ObjectifParcelle(String objectif, Chemin chemin, Couleurs[] couleurs, int points) {
		super(objectif, points, couleurs);
		this.chemin = chemin;
	}

    /**
     * Fonction pour voir si un objectif est valide
     * @param joueur
     * @return true si le chemin et trouve sur le Plateau avec les couleurs coresspondantes, false sinon
     */
    @Override
    public boolean estValide(Joueur joueur) {
        boolean result = false;
        Collection<Coordonnees> toutesLesCoordonnees = joueur.obtenirToutesCoordonnées();
        for(Coordonnees coords : toutesLesCoordonnees) {
            result = true;
            Coordonnees coordsATester = coords;
            for(Directions dirCourante : chemin) {
                if(Directions.D == dirCourante ) {
                    coordsATester = coordsATester.getD();
                } else if(Directions.G == dirCourante ) {
                    coordsATester = coordsATester.getG();
                } else if(Directions.DH == dirCourante) {
                    coordsATester = coordsATester.getHD();
                } else if(Directions.GH == dirCourante ) {
                    coordsATester = coordsATester.getHG();
                } else if(Directions.DB == dirCourante ) {
                    coordsATester = coordsATester.getBD();
                } else if(Directions.GB == dirCourante ) {
                    coordsATester = coordsATester.getBG();
                }
                if( Plateau.getInstance().coordonneesValides(coordsATester)
                        && Plateau.getInstance().obtenirParcelle(coordsATester).getCouleur() == this.getCouleur()[0]) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
            if(result) break;
        }
        return result;
    }

    /**
     * Fonction pour recuperer le chemin de l'objectif 
     * @return Chemin 
     */
    public Chemin getChemin() {
        return this.chemin;
    }
    
   /**
    * Une fonction pour comparer deux listes de couleurs
    * @param couleursChemin : la liste de couleurs 1
    * @param couleursObjectif : la liste de couleurs 2
    * @return : true si les deux listes sont egales , false sinon
    */
   private boolean couleursCorrespondants(ArrayList<Couleurs> couleursChemin , ArrayList<Couleurs> couleursObjectif) {
	   boolean result = true ;
	   if ( couleursChemin.size() == couleursObjectif.size() ) {
		   for ( int i = 0 ; i < couleursChemin.size() && result ; i++ ) {
			   result = result && couleursChemin.get(i).equals(couleursObjectif.get(i));
		   }
	   }
	   else {
		   return false ;
	   }
	   
	   return result ; 
   }

@Override
public void sayHello() {
	// TODO Auto-generated method stub
	System.out.println("obj Parcelle vous dit Hello");
}

@Override
public void sayBye() {
	// TODO Auto-generated method stub
	System.out.println("obj Parcelle vous dit Bye");
}
   

public static void main(String[] args){
	Couleurs[] coul = {Couleurs.jaune,Couleurs.rose};
	 Chemin dir1 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
     add(Directions.DH);
     add(Directions.DH);}});
	 
	 
		Objectif objJardinier = new ObjectifJardinier("I'm a gardener objective",2,coul,10);
		interf objPanda = new ObjectifPanda("I'm a panda objective",2,coul,11);
		Objectif objParcelle = new ObjectifParcelle("I'm a parcel objective",dir1,coul,2);
		
		System.out.println(objJardinier.getCouleur());
		System.out.println(objPanda.equals(objPanda));
		System.out.println(objParcelle.getNbPoints());
		
		((interf)objJardinier).sayHello();
		objPanda.sayBye();
}
}
