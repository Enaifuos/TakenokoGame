package PinHead.moteur;

public class Irrigation {
  private Coordonnees premiere;
  private Coordonnees seconde;

  /**
   * Le constructeur
   * @param premier : les coordonnées de la première parcelle irriguée
   * @param second : les coordonnées de la deuxième parcelle irriguée
   */
  public Irrigation(Coordonnees premier, Coordonnees second) {
    this.premiere = premier;
    this.seconde = second;
  }

  /**
   * 
   * @return Coordonnes : les coordonnees de la première parcelle irriguée
   */
  Coordonnees getPremiere() {
    return premiere;
  }

  /**
   * 
   * @return Coordonnes : les coordonnees de la deuxième parcelle irriguée
   */
  Coordonnees getSeconde() {
    return seconde;
  }

  @Override
  public boolean equals(Object o){
    if (o instanceof Irrigation){
      Irrigation irrigation = (Irrigation) o;
      return (this.premiere.equals(irrigation.premiere) && this.seconde.equals(irrigation.seconde))
        || (this.premiere.equals(irrigation.seconde) && this.seconde.equals(irrigation.premiere));
    }
    return false;
  }

  /**
   * @return int : un code propre et unique à l'irrigation calculée à partir des hash code des deux parcelles irriguées
   */
  @Override
  public int hashCode() {
    return premiere.hashCode()+seconde.hashCode();
  }
}
