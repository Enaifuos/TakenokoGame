package PinHead.moteur.entites;

import PinHead.moteur.Coordonnees;

/**
 * @author Hugo Croenne
 */
public class Parcelle {

    private int tailleBambou;
    private Coordonnees coordonnees;
    private final Couleurs Couleur;
    private boolean irrigation;

    /**
     * Constructeur de parcelle
     * @param couleur la couleur de parcelle
     */
   public Parcelle(Couleurs couleur){
        this.tailleBambou = 0;
        this.Couleur = couleur;
        this.irrigation = true;//false;
    }

    /**
     * Definit les coordonnees placees en argument comme le les coordonnees de cette parcelle
     * @param coordonnees les coordonnees ou poser la parcelle
     */
     public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }

    /**
     * Fonction pour recuperer les coordonnees d'une parcelle
     * @return les coordonnes de la parcelle
     */
     public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    /**
     * Fonction pour recuperer la taille de bambous sur une parcelle
     * @return le nombre de bambous sur la parcelle
     */
     public int getTailleBambou() {
        return tailleBambou;
    }

    /**
     * Fonction pour recuperer la couleur de la parcelle
     * @return la couleur de la parcelle
     */
     public Couleurs getCouleur() {
        return Couleur;
    }

    /**
     * Fonction pour verifier si la parcelle est irriguee 
     * @return true si la parcelle est irriguée false sinon
     */
     public boolean estIrriguée() { return irrigation;}

    /**
     * Fait pousser un bambou si le nombre de bambou est inférieur a la taille maximale
     */
     public void pousserBambou() {
        if(tailleBambou < 4){
            this.tailleBambou = tailleBambou + 1;
        }
    }

    /**
     * Recupere un bambou
     */
    public void recupererBambou() {
        if (tailleBambou > 0)
            this.tailleBambou = tailleBambou - 1;
    }

    /**
     * modifie l'etat de l'irrigation de la parcelle
     */
    public void setIrrigationTrue() {
        irrigation = true;
    }

    /**
     * les parcelles sont egales si et seulement si leur coordonnees sont identiques
     * @param o objet a comparer avec cette parcelle
     * @return true si les parcelle sont egales false sinon
     */
    @Override
    public boolean equals(Object o){
        if (!( o instanceof Parcelle)) return false;
        Parcelle parcelle = (Parcelle) o;
        if(this.coordonnees != null && parcelle.coordonnees != null){
          return this.coordonnees.equals(parcelle.coordonnees);
        }else return super.equals(o);
    }
}
