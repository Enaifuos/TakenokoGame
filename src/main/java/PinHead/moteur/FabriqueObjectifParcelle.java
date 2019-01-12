package PinHead.moteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import PinHead.moteur.entites.Chemin;
import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Directions;
import PinHead.moteur.ObjectifParcelle;

public class FabriqueObjectifParcelle {

	private static final HashMap<String, Chemin> figures = new HashMap<>();
	static {
		figures.put("TriangleH", new Chemin(new ArrayList<Directions>(){{
				add(Directions.O);
				add(Directions.DH);
				add(Directions.DB);
		}}));
		figures.put("TriangleB", new Chemin(new ArrayList<Directions>(){{
				add(Directions.O);
				add(Directions.DB);
				add(Directions.DH);
		}}));
		figures.put("Losange", new Chemin(new ArrayList<Directions>(){{
				add(Directions.O);
				add(Directions.GB);
				add(Directions.DB);
				add(Directions.DH);

		}}));
		figures.put("ChevronG", new Chemin(new ArrayList<Directions>(){{
				add(Directions.O);
				add(Directions.GB);
				add(Directions.DB);
		}}));
		figures.put("ChevronD", new Chemin(new ArrayList<Directions>(){{
				add(Directions.O);
				add(Directions.DB);
				add(Directions.GB);
		}}));
		figures.put("LigneDD", new Chemin(new ArrayList<Directions>(){{
				add(Directions.O);
				add(Directions.DH);
				add(Directions.DH);
		}}));
		figures.put("LigneDG", new Chemin(new ArrayList<Directions>(){{
				Directions.O.ordinal();
				Directions.GH.ordinal();
				Directions.GH.ordinal();
		}}));
		figures.put("LigneH", new Chemin(new ArrayList<Directions>(){{
				add(Directions.O);
				add(Directions.D);
				add(Directions.D);
		}}));
	}

	/**
	 * Fonction pour cree un objectif Parcelle
	 * @param fig : la figure a realiser pour valider l'objectif en creation
	 * @return : un objectif Parcelle
	 */
	ObjectifParcelle CreerObjectifParcelle(String fig, Couleurs[]couleurs, int points){
		String couleur = "";
		for(int i = 0; i < couleurs.length; i++) {
			couleur = couleur + couleurs[i];
			if(i != couleurs.length - 1)
				couleur = couleur + ", ";
		}
		ObjectifParcelle o = new ObjectifParcelle(("Placement de parcelles en " + fig + " " + couleur), figures.get(fig),couleurs,points);
		return o ;
	}

	/**
	 * Fonction pour cree des exemplaires d'objectif Parcelle
	 * @param fig : la figure a realiser pour valider l'objectif en creation
	 * @param nbexemplaires : nombre d'exemplaires a crer
	 * @return : Une collection d'objectifs parcelle
	 */
	Collection<ObjectifParcelle> CreerObjectifsParcelle(String fig , int nbexemplaires,Couleurs[] couleurs,int points){
		Collection<ObjectifParcelle> resultat = new ArrayList<ObjectifParcelle>();
		for ( int i = 0; i < nbexemplaires; ++i){
			resultat.add(CreerObjectifParcelle(fig,couleurs,points));
		}
		return resultat;
	}
}
