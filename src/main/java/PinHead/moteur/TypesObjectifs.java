package PinHead.moteur;

/**
 * @author soufianeaourinmouche
 */
public enum TypesObjectifs {
	PANDA(0), PARCELLE(1), JARDINIER(2); 
	
	@SuppressWarnings("unused")
	private int valeur ; 
	
	TypesObjectifs(int v){
		this.valeur = v ; 
	}
	
}