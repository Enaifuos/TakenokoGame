package PinHead.moteur;

import PinHead.moteur.entites.Couleurs;
import java.util.Collection;

public class FabriqueObjectifDeTestPanda{
  private FabriqueObjectifPanda fabrique;
  public FabriqueObjectifDeTestPanda(){
    fabrique = new FabriqueObjectifPanda();
  }

public Collection<ObjectifPanda> CreerObjectifsPanda(int nb, int nbExemplaires, Couleurs[] c, int points){
  return fabrique.CreerObjectifsPanda(nb, nbExemplaires, c, points);
}

public ObjectifPanda CreerObjectifPanda(int nb, Couleurs c[], int points){
  return fabrique.CreerObjectifPanda(nb, c, points);
}
}
