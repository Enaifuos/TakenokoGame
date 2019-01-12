package PinHead.moteur.utils;

public class Paire<T, U> {

    private T gauche;
    private U droit;

    public Paire (T gauche, U droit) {
        this.gauche = gauche;
        this.droit = droit;
    }

    public T getGauche() {
        return gauche;
    }

    public U getDroit() {
        return droit;
    }
}