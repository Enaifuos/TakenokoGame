package PinHead.moteur.entites;

import PinHead.moteur.Coordonnees;

public class Etang extends Parcelle {

	/**
	 * Une parcelle speciale qui est le point de depart , toujours posees dans le plateau initialement
	 * @param coordonnees les coordonnees ou poser cet etang
	 */
    public Etang(Coordonnees coordonnees) {
        super(Couleurs.etang);
        super.setCoordonnees(coordonnees);
    }

    @Override
    public void pousserBambou(){return;}
}
