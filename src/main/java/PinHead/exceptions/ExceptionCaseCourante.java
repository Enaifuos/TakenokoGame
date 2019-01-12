package PinHead.exceptions;

@SuppressWarnings("serial")
public class ExceptionCaseCourante extends Exception {

	/**
	 * Constructeur : Erreur generee lorsque le joueur essaye de deplacer un element sur une parcelle ou cet element existe deja
	 * comme deplacer le Panda/Jardinier vers sa meme position
	 */
    public ExceptionCaseCourante() {
        super("L'élément se trouve déjà sur la case");
    }
}
