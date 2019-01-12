package PinHead.moteur;

import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Etang;
import PinHead.moteur.entites.Parcelle;

import java.util.Set;
import java.util.Collection;
import java.util.ArrayList;

import java.util.List;
import java.util.HashSet;

/**
  *@author Corentin Artaud
  *
  *Singleton representant le plateau de jeu.
 **/
public class Plateau {
  private static Plateau instance;

  private Set<Parcelle> tableau;

  private List<Irrigation> irrigations;

  /**
    *Constructeur du singleton Plateau
    *
   **/
  private Plateau() {
    tableau = new HashSet<Parcelle>();
    tableau.add(new Etang(Coordonnees.getCentre()));
    irrigations = new ArrayList<Irrigation>();
    obtenirParcelle(Coordonnees.getCentre()).setIrrigationTrue();
  }

  /**
    *@return l'instance du singleton Plateau
  **/
  static Plateau getInstance() {
    return instance;
  }

  /**
    *@return true si une case avec ces coordonnees est presente
    *sur le plateau, false sinon.
    *
    *@param coordonnee : coordonnee a tester.
  **/
   public boolean coordonneesValides(Coordonnees coordonnee) {
    for (Parcelle parcelle : tableau) {
      if (parcelle.getCoordonnees().equals(coordonnee))
        return true;
    }
    return false;
  }

  /**
   * verifie si l irrigation que l on veut placer est bien entre deux parcelles placees sur le plateau
   * @param irrigation
   * @return true si les deux parcelles existent
   */
   boolean irrigationValides(Irrigation irrigation) {
       if (irrigation.getPremiere().equals(Coordonnees.getCentre()) ||
               irrigation.getSeconde().equals(Coordonnees.getCentre())) {
           return false;
       } else if ((coordonneesValides(irrigation.getPremiere()) && coordonneesValides(irrigation.getSeconde())) &&
               irrigation.getPremiere().estUnVoisin(irrigation.getSeconde())) {
           return true;
       }
       else {
           return false;
       }
   }



  /**
    *@return true si la coordonnee passee en parametre a au moins un voisin d'instancie,
    *false sinon.
    *
    *@param coordonnee : la coordonnee a tester.
  **/
   public int nbVoisin(Coordonnees coordonnee) {
    int res = 0;
    if (coordonneesValides(coordonnee.getHG()))
      ++res;
    if (coordonneesValides(coordonnee.getHD()))
      ++res;
    if (coordonneesValides(coordonnee.getG()))
      ++res;
    if (coordonneesValides(coordonnee.getD()))
      ++res;
    if (coordonneesValides(coordonnee.getBG()))
      ++res;
    if (coordonneesValides(coordonnee.getBD()))
      ++res;
    return res;
  }

  /**
   * verifie qu'une parcelle peut etre placee sur cette coordonnees
   * @param coordonnee
   * @return true si la parcelle peut etre placer false sinon
   */
   public boolean peutEtrePlace(Coordonnees coordonnee){
       return (!coordonneesValides(coordonnee)
        && (Coordonnees.getCentre().estUnVoisin(coordonnee) || nbVoisin(coordonnee) >= 2));
   }

    /**
     * Fonction pour recuperer l'ensemble de coordonnees placables
     * @return l'ensemble de coordonnees plaçables
     */
   public Set<Coordonnees> coordonneesValidesPlacementParcelle() {
       Set<Coordonnees> ensembleCoords = new HashSet<Coordonnees>();
       for(Parcelle parcelle : tableau) {
           if(peutEtrePlace(parcelle.getCoordonnees().getD()))
               ensembleCoords.add(parcelle.getCoordonnees().getD());
           if(peutEtrePlace(parcelle.getCoordonnees().getG()))
               ensembleCoords.add(parcelle.getCoordonnees().getG());
           if(peutEtrePlace(parcelle.getCoordonnees().getHD()))
               ensembleCoords.add(parcelle.getCoordonnees().getHD());
           if(peutEtrePlace(parcelle.getCoordonnees().getHG()))
               ensembleCoords.add(parcelle.getCoordonnees().getHG());
           if(peutEtrePlace(parcelle.getCoordonnees().getBD()))
               ensembleCoords.add(parcelle.getCoordonnees().getBD());
           if(peutEtrePlace(parcelle.getCoordonnees().getBG()))
               ensembleCoords.add(parcelle.getCoordonnees().getBG());
       }
       return ensembleCoords;
   }

  /**
   * Fonction pour trouver le complement intersections d'une irrigation
   * @param irrigation
   * @return les coordonnees qui sont voisines aux deux coordonnees de l'irrigation
   */
  public Coordonnees[] trouverComplementIntersections(Irrigation irrigation){
    Coordonnees res [] = new Coordonnees[2];
    if(irrigation.getPremiere().estLeVoisinNumero(irrigation.getSeconde()) == 0){
      res[0] = irrigation.getPremiere().getHG();
      res[1] = irrigation.getPremiere().getD();
    } else if(irrigation.getPremiere().estLeVoisinNumero(irrigation.getSeconde()) == 1){
      res[0] = irrigation.getPremiere().getHD();
      res[1] = irrigation.getPremiere().getBD();
    }else if(irrigation.getPremiere().estLeVoisinNumero(irrigation.getSeconde()) == 2){
      res[0] = irrigation.getPremiere().getD();
      res[1] = irrigation.getPremiere().getBG();
    }else if(irrigation.getPremiere().estLeVoisinNumero(irrigation.getSeconde()) == 3){
      res[0] = irrigation.getPremiere().getBD();
      res[1] = irrigation.getPremiere().getG();
    }else if(irrigation.getPremiere().estLeVoisinNumero(irrigation.getSeconde()) == 4){
      res[0] = irrigation.getPremiere().getBG();
      res[1] = irrigation.getPremiere().getHG();
    }else if(irrigation.getPremiere().estLeVoisinNumero(irrigation.getSeconde()) == 5){
      res[0] = irrigation.getPremiere().getG();
      res[1] = irrigation.getPremiere().getHD();
    }
    return res;
  }

  /**
   * verifie s'il existe une irrigation adjacent a celle placee en parametre
   * @param irrigation
   * @return true s'il existe une irrigation adjacente
   */
   public boolean irrigationAdjacente(Irrigation irrigation){
    Coordonnees[] complement = trouverComplementIntersections(irrigation);
    if(irrigations.contains(new Irrigation(irrigation.getPremiere(),complement[0])) ||
            irrigations.contains(new Irrigation(irrigation.getPremiere(),complement[1])) ||
            irrigations.contains(new Irrigation(irrigation.getSeconde(),complement[0])) ||
            irrigations.contains(new Irrigation(irrigation.getSeconde(),complement[1]))){
      return true ;
    }
    return false;
  }

  public boolean contiensIrrigation(Irrigation irrigation){
    return irrigations.contains(irrigation);
  }

  /**
   * Fonction pour tester si on peut placer une irrigation
   * @param irrigation
   * @return true si on peut placer une irrigation false sinon
   */
  public boolean peutPlacerIrrigation(Irrigation irrigation){
    Coordonnees[] complement = trouverComplementIntersections(irrigation);
//    if (irrigation.getPremiere().equals(Coordonnees.getCentre()) ||
//            irrigation.getSeconde().equals(Coordonnees.getCentre())) {
//        return false;
//    }
    if(irrigationValides(irrigation) && (complement[0].equals(Coordonnees.getCentre()) ||
            complement[1].equals(Coordonnees.getCentre()))){
      return true ;
    } else if(irrigationValides(irrigation) && (irrigationAdjacente(irrigation))){
      return true ;
    }
    return false;
  }

  /**
   * Fonction pour ajouter une irrigation 
   * @param irrigation : l'irrigation a ajouter 
   * @return boolean : true si l'irrigation est ajoutee , false sinon
   */
  public boolean ajouterIrrigation(Irrigation irrigation) {
    if (peutPlacerIrrigation(irrigation)){
      obtenirParcelle(irrigation.getPremiere()).setIrrigationTrue();
      obtenirParcelle(irrigation.getSeconde()).setIrrigationTrue();
      return irrigations.add(irrigation);}
    return false;
  }

  /**
    *@return true si la parcelle a correctement ete ajoute au plateau, false sinon.
    *@param coordonne : les coordonnees ou placer la parcelle
    *@param parcelle : la parcelle a ajouter
   **/
   public boolean placerParcelle(Parcelle parcelle, Coordonnees coordonnee) {
    if (peutEtrePlace(coordonnee)) {
      parcelle.setCoordonnees(coordonnee);
      tableau.add(parcelle);
      return tableau.contains(parcelle);
    }
    return false;
  }

   /**
    * Fonction pour obtenir une parcelle a partir des coordonnees 
    * @param coordonnee : coordonnes a partir de laquelle recuperer une parcelle 
    * @return Parcelle : la parcelle obtenue par les coordonnees du parametre
    */
  public Parcelle obtenirParcelle(Coordonnees coordonnee) {
    if (!coordonneesValides(coordonnee)) {
      return null;
    }
    for (Parcelle parcelle : tableau) {
      if (parcelle.getCoordonnees().equals(coordonnee))
        return parcelle;
    }
    return null;
  }

  /**
   * Fonction pour recuperer toutes les coordonnees
   * @return Collection<Coordonnees> : collection de toutes les coordonnees 
   */
   Collection<Coordonnees> obtenirToutesCoordonnees() {
    ArrayList<Coordonnees> coordonnees = new ArrayList<Coordonnees>();
    for (Parcelle parcelle : tableau) {
      coordonnees.add(parcelle.getCoordonnees());
    }
    return coordonnees;
  }

  /**
   * Fonction pour obtenir toutes les coordonnees colorees
   * @param couleur
   * @return l'ensemble des parcelles d'une couleur choisie
   */
   public ArrayList<Parcelle> obtenirParcellesColorees(Couleurs couleur) {
    ArrayList<Parcelle> resultat = new ArrayList<Parcelle>();
    for(Parcelle parcelle : tableau) {
      if(parcelle.getCouleur() == couleur) resultat.add(parcelle);
    }
    return resultat;
  }

  /**
    *instancie un nouveau Plateau (Mise a zero)
   **/
  static void nouveauPlateau() {
    instance = new Plateau();
  }

 /* void PousserTousBambous() {
    for (Parcelle parcelle : tableau) {
      parcelle.pousserBambou();
    }
  }*/

  /**
   * Fonction pour faire pousser des bambous sur une parcelle passee en coordonnees en parametre
   * @param coord : les coordonnees de la parcelle sur laquelle faire pousser de bambous
   */
  void PousserBambous(Coordonnees coord){
	  for (Parcelle parcelle : tableau) {
		  if ( parcelle.getCoordonnees().equals(coord)) {
			  parcelle.pousserBambou();
		  }
	  }
  }

}
