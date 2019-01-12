package PinHead.moteur;

import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Parcelle;

import java.util.Collections;
import java.util.LinkedList;

/**
  *@author Hugo Croenne
  *@author Corentin Artaud
  **/

public class PiocheParcelle {

    private static PiocheParcelle instance;

    private LinkedList<Parcelle> deckParcelles;

    /**
     * Constructeur de la pioche de parcelle
     */
    private PiocheParcelle(){
        deckParcelles = new LinkedList<Parcelle>();
        FabriqueParcelle fabrique = new FabriqueParcelle();
        deckParcelles.addAll(fabrique.fabriquer(Couleurs.vert, 11));
        deckParcelles.addAll(fabrique.fabriquer(Couleurs.rose, 7));
        deckParcelles.addAll(fabrique.fabriquer(Couleurs.jaune, 9));
        Collections.shuffle(deckParcelles);
    }

    /**
     * @return la parcelle au dessus de la pioche
     */
    Parcelle piocher(){
        return deckParcelles.pop();
    }

    /**
     * repose la parcelle au dessous de la pioche
     * @param parcelle
     */
    void remettre(Parcelle parcelle){
        deckParcelles.addLast(parcelle);
    }

    /**
     * @return le nombre de parcelle restantes dans la pioche
     */
    int parcellesRestantes(){
        return deckParcelles.size();
    }

    /**
     * créé une nouvelle pioche parcelle
     */
    public static void nouvellePiocheParcelle(){
      instance = new PiocheParcelle();
    }
    
    /**
     * Fonction pour instancier une pioche Parcelle
     * @return
     */
    public static PiocheParcelle getInstance(){
      return instance;
    }

}
