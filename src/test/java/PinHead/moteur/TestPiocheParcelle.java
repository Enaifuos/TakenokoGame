package PinHead.moteur;

import PinHead.moteur.PiocheParcelle;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestPiocheParcelle {

    @Test
    public final void testMelangePioche(){

    	PiocheParcelle.nouvellePiocheParcelle();
		PiocheParcelle pioche1 = PiocheParcelle.getInstance();

		PiocheParcelle.nouvellePiocheParcelle();
		PiocheParcelle pioche2 = PiocheParcelle.getInstance();

        int nbCartes = pioche1.parcellesRestantes();
        int cpt = 0;
        for(int i = 0; i < nbCartes; ++i){
            if (pioche1.piocher().getCouleur() == pioche2.piocher().getCouleur()) ++cpt;
        }
        // System.out.println(cpt);
        assertTrue(cpt != nbCartes);
    }

    @Test
    public final void testPiocher(){
    	PiocheParcelle.nouvellePiocheParcelle();
		PiocheParcelle pioche = PiocheParcelle.getInstance();

        int nbInitial = pioche.parcellesRestantes();
        pioche.piocher();
        assertFalse(nbInitial == pioche.parcellesRestantes());
    }

    @Test
    public final void testRemise(){
    	PiocheParcelle.nouvellePiocheParcelle();
		PiocheParcelle pioche = PiocheParcelle.getInstance();

        int nbInitial = pioche.parcellesRestantes();
        pioche.remettre(pioche.piocher());
        assertTrue(nbInitial == pioche.parcellesRestantes());
    }
}
