package PinHead.moteur;

import PinHead.automate.*;
import PinHead.moteur.utils.Afficheur;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;


public class FabriqueDePartie {
    private static HashMap<String, String> equi = new HashMap<String, String>();
    static {
        equi.put("Bob", new Bob("Bob").getClass().getName());
        equi.put("Marcel", new Marcel("Marcel").getClass().getName());
        equi.put("MarcelPanda", new MarcelPanda("MarcelPanda").getClass().getName());
        equi.put("MarcelJardinier", new MarcelJardinier("MarcelJardinier").getClass().getName());
    }

    private ArrayList<Robot> robots;

    /**
     * Fonction pour creer un robot a partri de son nom
     * @param name : le nom du robot a crer
     * @return Robot : un robot cree a partir de son nom
     * @throws Exception : 
     */
    private Robot createRobot(String name) throws Exception {
        String[] names = name.split(",");
        return (Robot) Class.forName(equi.get(names[0])).getConstructor(String.class).newInstance(names[1]);
    }

    /**
     * Fonction pour fabriquer une partie 
     * @param robots : chaine de caracteres contenant les noms des robots de la partie separes par un espace
     */
    public FabriqueDePartie(String robots) {
        this.robots = new ArrayList<Robot>();
        Arrays.asList(robots.split(" ")).forEach(r -> {
            try {
                Robot rob = createRobot((String)r);
                this.robots.add(rob);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Procedure qui instancie les singletons pour preparer une nouvelle partie
     */
    private void PreparePartie(){
        MaitreDuJeu.getInstance().miseAZero();
        MaitreDuJeu.getInstance().instancierPlateau();
        MaitreDuJeu.getInstance().instancierPanda();
        MaitreDuJeu.getInstance().instancierJardinier();
        MaitreDuJeu.getInstance().instancierPiocheParcelle();
        MaitreDuJeu.getInstance().instancierPiocheObjectifs();
        robots.forEach(r -> MaitreDuJeu.getInstance().ajouterJoueur(new Joueur(r.toString(),r)));

    }

    /**
     * Procedure pour lancer une partie apres l'avoir preparee
     */
    public void lancerPartie(){
        PreparePartie();
        MaitreDuJeu.getInstance().lancerPartie();
    }

    /**
     * Procedure pour lancer N parties
     * @param n : nombre de parties a lancer
     */
    public void lancerNParties(int n){
        for (int i = 0; i < n; ++i) {
            lancerPartie();
            //int maxPoints = MaitreDuJeu.getInstance().pointsMax();
            robots.forEach(r -> r.finPartie(MaitreDuJeu.getInstance().estGagnant(r)));

        }
        Afficheur.getInstance().afficherResume("");
        robots.forEach(r -> Afficheur.getInstance().afficherResume(r+" Score moyen: "+String.format("%-3.3s",r.getMoyenne())+ "\t" + "Taux de vitoire: "+ String.format("%-12.12s",r.getNbPartiesGagnees() + "/" + r.getNbPartie()) + " soit " +(r.getNbPartiesGagnees()*100/r.getNbPartie())+"%"));

        int pourCentVictoires = 0;
        int nbParties = 0;
        for(Robot r : robots) {
            pourCentVictoires += r.getNbPartiesGagnees()*100/r.getNbPartie();
            nbParties = r.getNbPartie();
        }
        Afficheur.getInstance().afficherResume("Taux d'egalite : " +(100 -pourCentVictoires)+"%");
        Afficheur.getInstance().afficherResume("Nombre de parties jouees : " + nbParties);

    }

}
