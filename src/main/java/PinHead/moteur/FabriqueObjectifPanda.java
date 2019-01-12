package PinHead.moteur;

import java.util.ArrayList;
import java.util.Collection;

import PinHead.moteur.entites.Couleurs;

public class FabriqueObjectifPanda {

	/**
	 * Fonction pour cree un objectif Panda
	 * @param nb : nombre de bambous a manger pour realiser l'objectif Panda en creation
	 * @return un objectif Panda
	 */
	 ObjectifPanda CreerObjectifPanda(int nb,Couleurs c[], int points){
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
		ObjectifPanda o = new ObjectifPanda(("Manger " + nb + " bambous "+couleurs), nb, c, points) ;
		return o ;
	}

	/**
	 * Fonction pour creer un nombre d'exemplaires d'une objectif panda
	 * @param nb nombre de bambous a manger pour realiser l'objectif Panda en creation
	 * @param nbExemplaires nombre d'objectifs a creer
	 * @param c couleur
	 * @return Une collection d'objectifs
	 */
	Collection<ObjectifPanda> CreerObjectifsPanda(int nb, int nbExemplaires, Couleurs[] c, int points){
		Collection<ObjectifPanda> resultat = new ArrayList<ObjectifPanda>();
		for ( int i = 0; i < nbExemplaires; ++i){
			resultat.add(CreerObjectifPanda(nb,c, points));
		}
		return resultat;
	}
}
