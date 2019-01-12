package PinHead.moteur;

/**
 * @author Hugo Croenne
 */

public class Coordonnees {

    private final int x;
    private final int y;

    /**
     * Constructeur Coordonnees
     * @param x : abscisse  
     * @param y : ordonne
     */
    public Coordonnees(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Fonction pour recuperer l'abscisse du coordonnee
     * @return la coordonnee en x
     */
    public int getX(){
        return this.x;
    }

    /**
     * Fonction pour recuperer l'ordonnee du coordonee
     * @return la ee en y
     */
    public int getY(){
        return this.y;
    }

    /**
     * Fonction pour recuperer les coordonnees du voisin en haut a droite
     * @return les coordonnees du voisin en haut a droite
     */
    public Coordonnees getHD() {
        return new Coordonnees(x+1, y);
    }

    /**
     * Fonction pour recuperer les coordonnees du voisin de droite
     * @return les coordonnees du voisin de droite
     */
    public Coordonnees getD() {
        return new Coordonnees(x+1, y+1);
    }

    /**
     * Fonction pour recuperer les coordonnees du voisin en bas a droite
     * @return les coordonnees du voisin en bas a droite
     */
    public Coordonnees getBD() {
        return new Coordonnees(x, y+1);
    }

    /**
     * Fonction pour recuperer les coordonnees du voisin en haut a gauche
     * @return les coordonnees du voisin en haut a gauche
     */
    public Coordonnees getHG() {
        return new Coordonnees(x, y-1);
    }

    /**
     * Fonction pour recuperer les coordonnees du voisin du gauche
     * @return les coordonnees du voisin de gauche
     */
    public Coordonnees getG() {
        return new Coordonnees(x-1, y-1);
    }

    /**
     * Fonction pour obtenir les coordonnees du voisin en bas a gauche
     * @return les coordonnees du voisin en bas a gauche
     */
    public Coordonnees getBG() {
        return new Coordonnees(x-1, y);
    }

    /**
     * Fonction pour recuperer les coordonnees de l'origine du repere
     * @return les coordonnees de l'origine du repere
     */
    public static Coordonnees getCentre() {
        return new Coordonnees(0,0);
    }

    /**
     * Fonction pour tester si une corodonenes est un voisin de celle a partir de laquelle la methode est appele
     * @param coordonnees : les coordonnees de la parcelle a tester si elle est voisine
     * @return true si la parcelle placee en attribut est voisine de celle a partir
     * de laquelle la methode est appelee
     */
    public boolean estUnVoisin(Coordonnees coordonnees){
        return (coordonnees.equals(this.getBD()) || coordonnees.equals(this.getBG()) ||
                coordonnees.equals(this.getD()) || coordonnees.equals(this.getG()) ||
                coordonnees.equals(this.getHD()) || coordonnees.equals(this.getHG()));
    }

    /**
     * Fonction pour tester si une coordonenes est sur la ligne de celle a partir de laquelle la methode appele
     * @param coordonnees : les coordonnees de la parcelle a tester si elle est sur la ligne
     * @return : true si la parcelle placee en attributs est sur la ligne de celle a partir de laquelle la methode appelee
     */
    boolean estSurLaLigne(Coordonnees coordonnees) {
        if(x == coordonnees.getX() || y == coordonnees.getY())
            return true;
        if((x - coordonnees.getX()) == (y - coordonnees.getY()))
            return true;
        return false;
    }

    /**
     * Fonction pour recuperer le numero d'un voisin
     * @param coordonnees : les coordonnees de la parcelle voisine dont on veut recuperer le numero voisin
     * @return 0...5 dans le sens de l'horloge a partir du HD , -1 si elle est pas voisine
     */
    int estLeVoisinNumero(Coordonnees coordonnees){
        if(this.getHD().equals(coordonnees)){
            return 0;
        } else if(this.getD().equals(coordonnees)){
            return 1;
        } else if(this.getBD().equals(coordonnees)){
            return 2;
        } else if(this.getBG().equals(coordonnees)){
            return 3;
        } else if(this.getG().equals(coordonnees)){
            return 4;
        } else if(this.getHG().equals(coordonnees)){
            return 5;
        }
        return -1;
    }

    /**
     * Les coordonnees sont egales ssi la coordonnees en x et en y sont egales
     * @param o : objet a comparer avec les coordonnees sur lesquelles la methode appelee
     * @return true si les coordonnees sont identiques false sinon
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof Coordonnees) {
            return (this.x == ((Coordonnees) o).getX() && this.y == ((Coordonnees) o).getY());
        }
        return false;
    }

    /**
     * Fonction toString
     * @return l'affichage des coordonnees
     */
    @Override
    public String toString(){
      return this.x+" : "+this.y;
    }

    /**
     * Fonction pour recuperer un code propre et unique a la coordonnee calculee a partir de son x et y
     * @return un code propre et unique a la coordonnee calculee a partir de son x et y 
     */
    @Override
    public int hashCode() {
      return this.x*15+y;
    }
}
