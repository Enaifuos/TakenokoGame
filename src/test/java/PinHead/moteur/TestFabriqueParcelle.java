package PinHead.moteur;

import org.junit.Test;

import PinHead.moteur.FabriqueParcelle;
import PinHead.moteur.entites.Couleurs;
import PinHead.moteur.entites.Parcelle;

import java.util.Collection;

import static org.junit.Assert.*;

public class TestFabriqueParcelle {

    @Test
    public final void testFabriquerParcelles(){
        FabriqueParcelle fabrique = new FabriqueParcelle();
        Couleurs couleurs [] = Couleurs.values();
        for (int i = 0; i < couleurs.length; ++i) {
            Collection<Parcelle> parcelles = fabrique.fabriquer(couleurs[i], 7);
            assertTrue(parcelles.size() == 7);
            int finalI = i;
            parcelles.forEach(p -> assertTrue(p.getCouleur() == couleurs[finalI]));
        }
    }
}
