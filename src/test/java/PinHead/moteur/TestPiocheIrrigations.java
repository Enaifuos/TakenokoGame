package PinHead.moteur;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestPiocheIrrigations {

	@Test
	public final void testPiocherIrrigationReussie() {
		
		/*	Créer la pioche	*/
		PiocheIrrigations.nouvellePiocheIrrigations();
		PiocheIrrigations pioche = PiocheIrrigations.getInstance();
		
		int cptAvantPioche = pioche.getCpt();
		pioche.piocher();
	
		assertEquals(pioche.getCpt(),cptAvantPioche-1);	
	}
	
	@Test
	public final void testPiocherIrrigationEchec() {
		
		/*	Créer la pioche	*/
		PiocheIrrigations.nouvellePiocheIrrigations();
		PiocheIrrigations pioche = PiocheIrrigations.getInstance();
		
		/* Vider la pioche	*/
		for ( ; pioche.getCpt()>0 ; ) {
			pioche.piocher();
		}
		
		int cpt = pioche.getCpt();
		assertEquals(cpt,0);
		
		pioche.piocher();
		assertEquals(cpt,0);
	}
	
}
