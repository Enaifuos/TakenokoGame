package PinHead.moteur;

import java.util.ArrayList;
import java.util.Collection;

import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Parcelle;

public class FabriqueParcelle {

	/**
	 * Fonction pour fabriquer une parcelle
	 * @param couleur : la couleur de parcelle a fabriquer
	 * @param nbParcelle : le nombre de parcelles a creer avec la couleur du parametre
	 * @return
	 */
    Collection<Parcelle> fabriquer(Couleurs couleur,int nbParcelle){
        ArrayList<Parcelle> resultat = new ArrayList<Parcelle>();
        for(int i = 0; i < nbParcelle; ++i){
            resultat.add(new Parcelle(couleur));
        }
        return resultat;
    }
}
