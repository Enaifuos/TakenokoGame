package PinHead.moteur;

import PinHead.moteur.entites.Couleurs;

public class FabriqueObjectifJardinier {

	/**
	 * Fonction pour cree un objectif jardinier 
	 * @param nb : nombre de bambous a empiler sur la parcelle pour realiser l'objectif Jardinier en creation
	 * @param c : tableau de couleurs de l'objectif Jardinier ( une seule couleure traitee comme un tableau de longueur 1)
	 * @return un objectif Jardinier
	 */
	 ObjectifJardinier CreerObjectifJardinier(int nb, Couleurs c[], int points){
         String couleurs = new String();
 		int cpt = 0;
 		for (Couleurs couleur : c){
 			if (cpt > 0 ){
 				couleurs += ", ";
 				if (cpt == c.length - 1) couleurs += "et ";
 			}
 			couleurs += couleur;
 			++cpt;
 		}
		ObjectifJardinier o = new ObjectifJardinier(("Emplier " + nb + " bambous  sur une parcelle de couleur : "+couleurs+" "),nb,c,points) ;
		return o ;
	}

}
