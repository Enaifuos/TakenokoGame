package PinHead.automate;

import PinHead.moteur.Joueur;

public abstract class Robot {
    private int moyenne;
    private int nbPartie;
    private int nbPartiesGagnees;
    protected Joueur joueur;
    private String nom;

    public Robot(String nom){
        moyenne = 0;
        nbPartie = 0;
        nbPartiesGagnees = 0;
        this.nom = nom;
    }

    public abstract void jouer(int nbActions);
    public void affecterUnJoueur(Joueur joueur){
        this.joueur = joueur;
    }

    public void ajoutPartieAMoyenne(int points){
        moyenne += points;
        if (nbPartie > 1) moyenne /= 2;
    }
    public void finPartie(boolean aGagne){
        ++nbPartie;
        ajoutPartieAMoyenne(joueur.getNbPoints());
        if(aGagne) ++nbPartiesGagnees;
    }

    public int getNbPartie(){
        return nbPartie;
    }

    public int getNbPartiesGagnees(){
        return nbPartiesGagnees;
    }
    public int getMoyenne(){
        return moyenne;
    }

    public int getNbPoints(){
        return joueur.getNbPoints();
    }

    public Joueur getJoueur(){
        return joueur;
    }

    @Override
    public String toString(){
        return nom;
    }

}
