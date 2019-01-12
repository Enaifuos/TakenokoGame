package PinHead.moteur;

import PinHead.automate.Bob;
import PinHead.automate.Robot;
import PinHead.exceptions.ExceptionCaseCourante;
import PinHead.exceptions.ExceptionCaseInaccessible;
import PinHead.exceptions.ExceptionCaseInexistante;
import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Parcelle;
import PinHead.moteur.utils.Afficheur;

import java.util.*;

/**
 * @author Maxime Bouis
 * @author Corentin Artaud
 * @author soufianeaourinmouche
 */
public class Joueur {

	private final int MAX_OBJECTIFS = 5 ;
    private ArrayList<Objectif> objectifs;
	private ArrayList<Objectif> objectifsValides;
    private final String nom;
    private int [] nbBambou;
    private int nbPoints;
    private Robot bot;
    private int reserveIrrigations ;
  //  private ArrayList<Objectif> mainObjectifs;  // Une liste intermediaire qui prend les 3 objectifs pioches , met l'objectif choisi dans Objectifs et remet le reste a la pioche , puis on la vide

    private boolean verou;
    private ArrayList<Parcelle> mainParcelles;
		private int actionsRestantes;

    public Joueur(String nom, Robot bot) {
        this.bot = bot;
        this.nom = nom;
        objectifs = new ArrayList<Objectif>();
		objectifsValides = new ArrayList<Objectif>();
        nbBambou = new int [3];
        nbPoints = 0;
        bot.affecterUnJoueur(this);
        mainParcelles = new ArrayList<Parcelle>();
        verou = false;
		actionsRestantes = 0;
		reserveIrrigations = 0;
    }

    /**
     * Demande au Plateau de placer une parcelle
     * @param parcelle : la parcelle a placer
     * @param coordonnees : les coordonnees ou placer la parcelle
     * @return boolean : true si la parcelle a ete placee , false sinon
     */
    public boolean placerParcelle(Parcelle parcelle, Coordonnees coordonnees) {
        if (! mainParcelles.contains(parcelle)) return false;
        if (Plateau.getInstance().placerParcelle(parcelle, coordonnees)){
          remettreParcellesSaufUne(parcelle);
					Afficheur.getInstance().afficherAction(nom+" place une Parcelle "+parcelle.getCouleur()+" en "+coordonnees);
          return true;
        }
        return false;
    }

    /**
     * Fonction pour obtenir les coordonnees de la position du Panda
     * @return Coordonnees : les coordonnees de la position du Panda
     */
	public Coordonnees obtenirCoordonneesPanda(){
		return Panda.getInstance().getPosition();
	}



    /**
     * Demande au Panda de se deplacer si les nouvelles coordonnees sont valides
     * @param coord
     * @throws Exception
     */
    public void deplacerPanda(Coordonnees coord) throws ExceptionCaseCourante,
            ExceptionCaseInaccessible,
            ExceptionCaseInexistante {
        if (verou || actionsRestantes <= 0) return;
				--actionsRestantes;
        if(coord.equals(Panda.getInstance().getPosition())) {
            throw new ExceptionCaseCourante();
        }
        if(!Panda.getInstance().getPosition().estSurLaLigne(coord)) {
            throw new ExceptionCaseInaccessible();
        }
        if(!Panda.getInstance().deplacer(coord, this)){
            throw new ExceptionCaseInexistante();
        }
        Afficheur.getInstance().afficherAction(nom+" deplace le Panda en "+coord);
    }

    /**
     * Valide un objectif
     * @param objectif
     */
   /* private void validerObjectif(Objectif objectif) {
        if (objectif.valider(this)){
						objectifs.remove(objectif);
						objectifsValides.add(objectif);
					 Afficheur.getInstance().afficherAction(nom+" valide l'objectif "+objectif.getObjectif());
				 }
		}*/

    /**
     * Teste si les objectifs sont remplis et si oui les fait valider
     */
    public void testerObjectifs() {
				ArrayList<Objectif> objectifsValidesIntermediaires = new ArrayList<>();
				for (Objectif o : objectifs) {
					if(o.valider(this)) {
						objectifsValidesIntermediaires.add(o);
						Afficheur.getInstance().afficherAction(nom+" valide l'objectif "+o.getObjectif());
					}
				}
				objectifs.removeAll(objectifsValidesIntermediaires);
				objectifsValides.addAll(objectifsValidesIntermediaires);
        // for(int i = 0; i < objectifs.size(); ++i) {
        //     if(objectifs.get(i).estValide(this)) {
        //         validerObjectif(objectifs.get(i));
        //     }
        // }
    }

    /**
     * Fonction pour piocher une irrigation , et incrementer le compteur d'irrigations du joueur
     */
    public void piocherIrrigation() {
    	PiocheIrrigations.getInstance().piocher();
    	this.reserveIrrigations++;
    }



    /**
     * Fonction pour recuperer toute la liste des irrigations dans la reserve du joueur
     * @return int : le nombre d'irrigations que possede ce joueur
     */
    public int obtenirReserveIrrigation() {
    	return this.reserveIrrigations;
    }

    /**
     * Fonction pour placer une irrigation
     * @param irrigation : l'irrigation a placer 
     */
    public void placerIrrigation(Irrigation irrigation){
			Plateau.getInstance().ajouterIrrigation(irrigation);
		}

    /**
     * Fonction pour tester si on peut placer une irrigation passee en parametre
     * @param irrigation
     * @return
     */
    public boolean peutPlacerIrrigation(Irrigation irrigation){
        return Plateau.getInstance().peutPlacerIrrigation(irrigation);
    }
    
    /**
     * Fonction pour ajouter une irrigation dans le plateau
     * @param irrigation : l'irrigation a ajouter
     * @return boolena : true si l'irrigation a bien ete ajoutee , false sinon
     */
    public boolean ajouterIrrigation(Irrigation irrigation){
        return Plateau.getInstance().ajouterIrrigation(irrigation);
    }

    /**
     * Fonction pour tester si le plateau contient une irrigation passee en parametre
     * @param irrigation : l'irrigation a tester la contenance dans le plateau
     * @return boolean : true si le plateau contient cette irrigation , false sinon
     */
    public boolean contientIrrigation(Irrigation irrigation){
        return Plateau.getInstance().contiensIrrigation(irrigation);
    }

    /**
     * Fonction pour tester si l'irrigation est valide
     * @param irrigation : l'irrigation a tester la validation
     * @return : true si l'irrigation est valide,  false sinon
     */
    public boolean irrigationValides(Irrigation irrigation){
        return Plateau.getInstance().irrigationValides(irrigation);
    }

    /**
     * Fonction pour recuperer le nombre de voisins d'une coordonnee
     * @param coordonnee : la coordonnee a compter les voisins 
     * @return int : le nombre de voisins d'une parcelle dont les coordonnees sont passees en parametre
     */
    public int nbVoisins(Coordonnees coordonnee){
        return Plateau.getInstance().nbVoisin(coordonnee);
    }



    /**
     * Fonction pour tester si une coordonnee est valide
     * @param coordonnees : les coordonnees a tester la validite
     * @return boolean : true si les coordonnees sont valides , false sinon
     */
	public boolean coordonneesValides(Coordonnees coordonnees){
		return Plateau.getInstance().coordonneesValides(coordonnees);
	}

	/**
	 * Fonction pour récupérer le tableau de bambous du joueur
	 * @return int[] , tableau de bambous du joueur
	 */
    public int [] getNbBambou() {
        return nbBambou;
    }
    
    /**
     * Fonction pour recuperer le nombre de points du joueur
     * @return int : nombre de points du joueur 
     */
    public int getNbPoints() {
        return nbPoints;
    }
    
    /**
     * Fonction pour recuperer le nom du joueur
     * @return String : nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Fonction pour recuperer le nombre d'objectifs valides par le joueur
     * @return int : le nombre d'objectifs valides par le joueur
     */
    public int getNbObjectifValides(){
        return objectifsValides.size();
    }

    /**
     * Fonction pour ajouter un nombre de points au score du joueur
     * @param points : nombre de points a ajouter au joueur
     */
    public void ajouterPoint(int points) {
        nbPoints += points;
    }

    /**
     * Fonction pour ajouter une bambou au joueur 
     * @param couleur : la couleur du bombou a ajouter au joueur
     */
    public void ajouterBambou(Couleurs couleur) {
        ++nbBambou[couleur.ordinal()];
    }

    /**
     * Fonction pour ajouter un objectif au joueur
     * @param objectif : l'objectif a ajouter au joueur
     */
    public void ajouterObjectif(Objectif objectif) {
        objectifs.add(objectif);
    }

    /**
     * Fonction pour faire joueur le joueur 
     * @param nbActions : nombre d'actions a faire 
     */
    public void jouer(int nbActions) {
        actionsRestantes = nbActions;
        bot.jouer(nbActions);
    }

    /**
     *
     * @param objectif : l'objectif recherche
     * @return : true si l'objectif recherche existe parmi les objectifs du joueur
     */
    public boolean objectifContenu(Objectif objectif){
    	return this.objectifs.contains(objectif);
    }

    /**
     *
     * @return la taille des objectifs du joueur QUI NE DOIT PAS DEPASSER 5
     */
    public int objectifTaille(){
    	return this.objectifs.size();
    }

    /**
     * Fonction permettant au joueur de piocher un objectif selon le choix du parametre
     * @param typeObjectif : type d'objectifs a piocher {PANDA, PARCELLE, JARDINIER}
     */
    public void piocherObjectif(TypesObjectifs typeObjectif){
    	// On ne peut pas avoir plus que 5 objectifs dans la main
				if(actionsRestantes <= 0) return;
				--actionsRestantes;
        if (objectifTaille()<MAX_OBJECTIFS) {
					Objectif o = PiocheObjectifs.getInstance().Piocher(typeObjectif);
					if (o != null) {
						ajouterObjectif(o);
						Afficheur.getInstance().afficherAction(nom+" pioche l'objectif "+o.getObjectif());
					}
        }
      }

    /**
     * Fonction pour recuperer la liste d'objectifs dans la main du joueur
     * @return la main des objectifs
     */
    public List<Objectif> obtenirMainObjectif(){
    	return this.objectifs;
    }

    /**
     * Fonction pour recuperer la liste d'objectifs valides 
     * @return List<Objectif> : liste d'objectifs valides
     */
	public List<Objectif> ObtenirObjectifsValides(){
		return Collections.unmodifiableList(objectifsValides);
	}


	/**
	 * Fonction pour piocher une parcelle
	 */
    public void piocherParcelle(){
			if(actionsRestantes <= 0) return;
      if ((! mainParcelles.isEmpty()) || PiocheParcelle.getInstance().parcellesRestantes() == 0) return;
			--actionsRestantes;
      for (int i = 0; i < (PiocheParcelle.getInstance().parcellesRestantes() > 3 ? 3
				: PiocheParcelle.getInstance().parcellesRestantes()); ++i){
      mainParcelles.add(PiocheParcelle.getInstance().piocher());
      }
      verou = true;
    }

    /**
     * Fonction pour remettre toutes les parcelles sauf celle choisie
     * @param parcelle : la parcelle a ne pas remettre ( a garder )
     */
    private void remettreParcellesSaufUne(Parcelle parcelle) {
      for (Parcelle p : mainParcelles){
        if(p != parcelle) { // la parcelle pose n'est pas remise en jeu
            PiocheParcelle.getInstance().remettre(p);
        }
      }
      mainParcelles.removeAll(mainParcelles);
      verou = false;
    }

    /**
     * Fonction pour tester si on peut placer une parcelle 
     * @param coordonnees : les coordonnees de la prcelle a tester si on peut placer
     * @return
     */
    public boolean peutPlacerParcelle(Coordonnees coordonnees){
        return Plateau.getInstance().peutEtrePlace(coordonnees);
    }

    /**
     * 
     * @return Set<Coordonnees> : l'ensemble des coordonnes placables
     */
    public Set<Coordonnees> coordonneesValidesPlacementParcelle() {
        return Plateau.getInstance().coordonneesValidesPlacementParcelle();
    }

    /**
     * 
     * @param coordonnees : coordonnees a partir desquelles on obtient une parcelle
     * @return Parcelle 
     */
    public Parcelle obtenirParcelle(Coordonnees coordonnees){
        return Plateau.getInstance().obtenirParcelle(coordonnees);
    }

    /**
     * 
     * @param couleur : couleur a partir desquelles on obtient une parcelle
     * @return Parcelle
     */
    public List<Parcelle> obtenirParcellesColorees(Couleurs couleur){
        return Plateau.getInstance().obtenirParcellesColorees(couleur);
    }

    /**
     * Fonction pour obtenir toutes les coordonnees du plateau
     * @return Collection<Coordonnees> : toute les coordonnees
     */
    public Collection<Coordonnees> obtenirToutesCoordonnées(){
        return Plateau.getInstance().obtenirToutesCoordonnees();
    }

    /**
     * Fonction pour obtenir la main Parcelles du joueur
     * @return List<Parcelle> 
     */
    public List<Parcelle> obtenirMainParcelles(){
      return Collections.unmodifiableList(mainParcelles);
    }

    /**
     * 
     *  @return : l'attribut verou du joueur
     */
    public boolean getVerou(){
      return verou;
    }


    /**
     * Fonction pour obtenir les coordonnees du jardinier
     * @return : coordonnees : les coordonnees du jardinier
     */
    public Coordonnees obtenirCoordonneesJardinier(){
    	return Jardinier.getInstance().getPosition() ;
    }

    /**
     * Fonction pour recuperer le nombre de parcelles restantes dans la pioche
     * @return int : nombre de parcelles restantes dans la pioche
     */
    public int parcellesRestantesPioche() {
        return PiocheParcelle.getInstance().parcellesRestantes();
    }

    /**
     * Fonction pour tester si la pioche des objectifs Jardinier est vide
     * @return boolean :  true si la pioche des objectifs jardinier est vide, false sinon
     */
    public boolean piocheJardinierEstVide() {
        return PiocheObjectifs.getInstance().PiocheJardinierEstVide();
    }

    /**
     * Fonction pour tester si la pioche des objectifs panda est vide
     * @return boolean :  true si la pioche des objectifs panda est vide, false sinon
     */
    public boolean piochePandaEstVide() {
        return PiocheObjectifs.getInstance().PiochePandaEstVide();
    }

    /**
     * Fonction pour tester si la pioche des objectifs parcelles est vide
     * @return boolean :  true si la pioche des objectifs parcelles est vide, false sinon
     */
    public boolean piocheParcelleEstVide() {
        return PiocheObjectifs.getInstance().PiocheParcelleEstVide();
    }

    /**
     *
     * @param coord : coordonnees de la parcelle oû deplacer le jardinier
     * @throws ExceptionCaseCourante
     * @throws ExceptionCaseInaccessible
     * @throws ExceptionCaseInexistante
     */
    public void deplacerJardinier(Coordonnees coord) throws ExceptionCaseCourante,
    ExceptionCaseInaccessible,
    ExceptionCaseInexistante {
    	if (verou || actionsRestantes <= 0) return;
			--actionsRestantes;
    	if( coord.equals(Jardinier.getInstance().getPosition()) ) {
    		throw new ExceptionCaseCourante();
    	}
    	if( !Jardinier.getInstance().getPosition().estSurLaLigne(coord)) {
    		throw new ExceptionCaseInaccessible();
    	}
    	if( !Jardinier.getInstance().deplacer(coord) ){
    		throw new ExceptionCaseInexistante();
    	}
		Afficheur.getInstance().afficherAction(nom+" deplace le Jardinier en "+coord);
    }
}
