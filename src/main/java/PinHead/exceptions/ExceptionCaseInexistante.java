package PinHead.exceptions;

@SuppressWarnings("serial")
public class ExceptionCaseInexistante extends Exception {

	/**
	 * Constructeur : Exception generee lorsque le joueur essaye d utiliser une case qui n existe pas sur le plateau
	 * comme deplacer le jardinier vers une parcelle qui n est pas encore placee sur le plateau
	 */
    public ExceptionCaseInexistante() {
        super("La case est inexistante");
    }
}
