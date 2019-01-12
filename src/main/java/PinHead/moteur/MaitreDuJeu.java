package PinHead.moteur;

import PinHead.moteur.utils.Afficheur;

import java.util.Set;
import java.util.HashSet;
import PinHead.automate.Robot;

/**
 * @author Maxime Bouis
 * @author Corentin Artaud
 */
public class MaitreDuJeu {

    private static MaitreDuJeu instance = new MaitreDuJeu();
    private int tour ;
    private Set<Joueur> joueurs;
    private int nbOBJECTIFS0jOUEURS;
    private Joueur gagnant;

    private MaitreDuJeu(){
      this.joueurs = new HashSet<Joueur>();
      this.tour = 1 ;
      this.nbOBJECTIFS0jOUEURS = 11;
      gagnant = null;
    }

    /**
     * Fonction pour set le nombre d'objectifs a realiser s'il y a 0 joueur dans la partie
     * @param nb : le nombre d'objectifs a realiser s'il y a 0 joueur dans la partie
     */
    public void setNbObjectifs0joueurs(int nb ){
        this.nbOBJECTIFS0jOUEURS = nb;
    }

    /**
     * Fonction pour instancier le Maitre du jeu
     * @return MaitreDuJeu
     */
    static MaitreDuJeu getInstance() {
        return instance;
    }

    /**
     * Fonction pour instancier le Plateau
     */
    public void instancierPlateau() {
        Plateau.nouveauPlateau();
    }

    /**
     * Fonction pour instancier le Panda
     */
    public void instancierPanda() {
        Panda.nouveauPanda();
    }

    /**
     * Fonction pour instancier le Jardinier
     */
    public void instancierJardinier() {
    	Jardinier.nouveauJardinier();
    }

    /**
     * Fonction pour instancier la pioche des parcelles
     */
    public void instancierPiocheParcelle() {
      PiocheParcelle.nouvellePiocheParcelle();
    }

    /**
     * Fonction pour instancier la pioche des objectifs
     */
    public void instancierPiocheObjectifs() {
      PiocheObjectifs.nouvellePiocheObjectif();
    }

    /**
     * Fonction pour instancier la pioche des irrigations
     */
    public void instancierPiocheIrrigations() {
    	PiocheIrrigations.nouvellePiocheIrrigations();
    }

    /**
     * Fonction pour ajouter un joueur dans la liste des joueurs du jeu
     * @param joueur : le joueur a ajouter dans la liste des joueurs du jeu
     */
    public void ajouterJoueur(Joueur joueur) {
        joueur.ajouterObjectif(PiocheObjectifs.getInstance().Piocher(TypesObjectifs.PANDA));
        joueur.ajouterObjectif(PiocheObjectifs.getInstance().Piocher(TypesObjectifs.PARCELLE));
        joueurs.add(joueur);
    }

    /**
     * Fonction pour recuperer le joueur gagnant d'une partie
     * @return Joueur : le joueur gagnant
     */
    public Joueur getGagnant(){
        Joueur res = null;
        int maxPoint = 0;
        int maxObjectif = 0;
        for (Joueur j : joueurs){
            if (maxPoint < j.getNbPoints()) {
                res = j;
                maxPoint = j.getNbPoints();
                maxObjectif = j.getNbObjectifValides();
            }else if (maxPoint == j.getNbPoints() && maxObjectif < j.getNbObjectifValides()){
                res = j;
                maxPoint = j.getNbPoints();
                maxObjectif = j.getNbObjectifValides();
            }else res = null;
        }
        return res;
    }

    /**
     * Fonction pour recuperer le nombre max de points d'un joueur
     * @return int
     */
    public int pointsMax(){
        int max = 0;
        for (Joueur j : joueurs){
            if (max < j.getNbPoints()) max = j.getNbPoints();
        }
        return max;
    }

    /**
     * Fonction pour modifier le joueur gagnant dans une partie
     */
    private void setGagnant(){
        int maxPoint = pointsMax();
        for (Joueur j : joueurs){
            if (j.getNbPoints() >= maxPoint ){
                if(gagnant == null){
                    gagnant = j;
                }else{
                     gagnant = null;
                    return;
                }
            }
        }
    }

    /**
     * Fonction pour tester si un robot est gagnant
     * @param r : le robot a tester s'il a gagne ou pas
     * @return boolean : true si le joueur r a gagne, false sinon
     */
    public boolean estGagnant(Robot r){
        if (gagnant == null) return false;
        return r.getJoueur() == gagnant;
    }

    /**
     * Gere les tours de chacun dans la partie
     * @return le joueur gagant
     */
    public void lancerPartie() {
        int nbObjectifAValider = nbOBJECTIFS0jOUEURS - joueurs.size();
        boolean finDePartie = false;
        while (!finDePartie) {
        	Afficheur.getInstance().afficherInfo("\nTOUR "+tour++ +"\n");
            for (Joueur j: joueurs) {
                j.jouer(2);
                if(!finDePartie && j.getNbObjectifValides() >= nbObjectifAValider) {
                    j.ajouterPoint(2); //simulation de l'empreur
                    finDePartie=true;
                }
            }
            if(tour >= 500) return;
          //  Plateau.getInstance().PousserTousBambous();
            Afficheur.getInstance().afficherInfo("\nScore :");
            joueurs.forEach(j -> Afficheur.getInstance().afficherInfo(j.getNom() +" : "+ j.getNbPoints()));
        }
        setGagnant();
        //joueurs.forEach(j -> Afficheur.getInstance().afficherInfo(j.getNom() +" : "+ j.getNbPoints()));
    }

    /**
     * Fonction pour mettre a zero le jeu
     */
    public void miseAZero(){
      joueurs.removeAll(joueurs);
      this.tour = 1;
      gagnant = null;
    }


}
