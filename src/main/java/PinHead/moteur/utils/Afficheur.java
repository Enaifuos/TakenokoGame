package PinHead.moteur.utils;

import java.io.PrintStream;
import java.io.OutputStream;

/**
 * Un afficheur pour decrire le deroulement de chaque partie , le score ...
 *
 */
public class Afficheur {
  private static Afficheur instance;

  private boolean actions;
  private boolean info;
  private boolean erreur;
  private boolean resume;
  private PrintStream flux;

  /**
   * Constructeur
   * @param flux la sortie flux pour faire des affichage
   */
  private Afficheur(OutputStream flux) {
    this.actions = true;
    this.info = true;
    this.erreur = true;
    this.resume = true;
    this.flux = new PrintStream(flux, true);
  }

  /**
   * Fonction pour afficher une information
   * @param message le message de l'information a afficher
   */
  public void afficherInfo(String message) {
    if (this.info) {
        flux.println(message);
    }
  }

  /**
   * Fonction pour afficher une action
   * @param message la message/description de l'action a afficher
   */
  public void afficherAction(String message) {
    if (this.actions){
      flux.println(message);
    }
  }

  /**
   * Fonction pour afficher une erreur
   * @param message le message de l'erreur a afficher
   */
  public void afficherErreur(String message){
      if(erreur){
          flux.println(message);
      }
  }

  /**
   * Fonction pour afficher le resume
   */
  public void afficherResume(String message){
      if(resume){
          flux.println(message);
      }
  }

  /**
   * Fonction pour mettre a vrai le boolean info
   * @param info boolean 
   */
  public void setInfo(boolean info) {
    this.info = info;
  }

  /**
   * Fonction pour mettre a vrai le boolean actions
   * @param actions boolean
   */
  public void setActions(boolean actions) {
    this.actions = actions;
  }

  /**
   * Fonction pour mettre a vrai le boolean erreur
   * @param erreur boolean
   */
  public void setErreur(boolean erreur){
      this.erreur = erreur;
  }

  /**
   * Fonction pour mettre a vrai le boolean resume
   * @param resume boolean
   */
  public void setResume(boolean resume){
      this.resume = resume;
  }

  /**
   * Instancier l afficheur
   * @return Afficheur
   */
  public static Afficheur getInstance() {
    //System.err.println("action = "+instance.actions+" info = "+instance.info);
    return instance;
  }

  /**
   * Creation d un nouveau afficheur par defaut
   */
  public static void nouveauAfficheur() {
    instance = new Afficheur(System.out);
  }

  /**
   * Creation d un nouveau afficheur avec un flux 
   * @param flux OutputStream
   */
  public static void nouveauAfficheur(OutputStream flux) {
    instance = new Afficheur(flux);
  }

}
