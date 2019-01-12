package PinHead.moteur.utils;

import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import static org.junit.Assert.*;

public class TestAfficheur{

  private ByteArrayOutputStream sortie;

  private static final String info = "Juste une action";
  private static final String action = "Une info au passage";
  private static final String erreur = "Une erreur";

  @Before
  public final void initialisation(){
    this.sortie = new ByteArrayOutputStream();
    Afficheur.nouveauAfficheur(sortie);
  }

  @Test
  public final void testAfficheurStandard(){
    Afficheur.getInstance().afficherInfo(info);
    Afficheur.getInstance().afficherAction(action);
    assertEquals(sortie.toString(), info+"\n"+action+"\n");
  }

  @Test
  public final void testAfficheurInfoSeulement(){
    Afficheur.getInstance().setActions(false);
    Afficheur.getInstance().setErreur(false);
    Afficheur.getInstance().afficherErreur(erreur);
    Afficheur.getInstance().afficherInfo(info);
    Afficheur.getInstance().afficherAction(action);
    assertEquals(sortie.toString(), info+"\n");
  }

  @Test
  public final void testAfficheurActionsSeulement(){
    Afficheur.getInstance().setInfo(false);
    Afficheur.getInstance().setErreur(false);
    Afficheur.getInstance().afficherInfo(info);
    Afficheur.getInstance().afficherAction(action);
    Afficheur.getInstance().afficherErreur(erreur);
    assertEquals(sortie.toString(), action+"\n");
  }

  @Test
  public final void testAfficheurErreursSeulement(){
    Afficheur.getInstance().setInfo(false);
    Afficheur.getInstance().setActions(false);
    Afficheur.getInstance().afficherInfo(info);
    Afficheur.getInstance().afficherAction(action);
    Afficheur.getInstance().afficherErreur(erreur);
    assertEquals(sortie.toString(), erreur+"\n");
  }

  @Test
  public final void testAfficheurRien(){
    Afficheur.getInstance().setActions(false);
    Afficheur.getInstance().setInfo(false);
    Afficheur.getInstance().setErreur(false);
    Afficheur.getInstance().afficherInfo(info);
    Afficheur.getInstance().afficherAction(action);
    Afficheur.getInstance().afficherErreur(erreur);
    assertEquals(sortie.toString(), "");
  }
}
