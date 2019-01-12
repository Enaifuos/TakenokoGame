package PinHead.exceptions;

@SuppressWarnings("serial")
public class ExceptionCaseInaccessible extends Exception {

	/**
	 * Constructeur : Exception generee lorsque le joueur essaye d acceder ou d utiliser une case  existante sur le plateau mais qui n est pas accessible 
	 * comme deplacer le Panda/Jardinier vers une case qui existe mais pas sur la ligne avec sa position courante
	 */
    public ExceptionCaseInaccessible() {
        super("La case est inaccessible");
    }
}
