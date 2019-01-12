package PinHead.moteur;

/**
 * 
 * @author soufianeaourinmouche
 *
 */
public class PiocheIrrigations {
	
	/*	Attributs	*/
	private static PiocheIrrigations piocheIrrigations;
	private int cpt;
	
	/*	Constantes	*/
	private final int NBMAX = 20;
	
	/*	Constructeur	*/
	private PiocheIrrigations() {
		this.cpt = NBMAX;
	}
	
	/**
	 * Fonction pour piocher une irrigation
	 * @param premiere : coordonnees de la premiere parcelle a irriguer 
	 * @param seconde : coordonnees de la deuxieme parcelle a irriguer
	 * @return : Une irrigation entre premiere et seconde si le cpt > 0 , NULL sinon 
	 */
	void piocher() {
		if ( this.getCpt() > 0 ) this.decrementeCpt();
	}
	
	/**
	 * Procedure pour decrementer le compteur d'irrigations
	 */
	private void decrementeCpt() {
		if ( this.getCpt() > 0 ) this.cpt--;
	}
	
	/**
	 * Fonction pour recuperer le compteur d'irrigations
	 * @return : int : le nombre d'irrigations restantes dans la pioche
	 */
	int getCpt() {
		return this.cpt;
	}
	
	static PiocheIrrigations getInstance() {
		return piocheIrrigations;
	}
	
	static void nouvellePiocheIrrigations() {
		piocheIrrigations = new PiocheIrrigations();
	}
}
