package PinHead;

import PinHead.moteur.utils.Afficheur;
import PinHead.moteur.FabriqueDePartie;

/**
 * Hello world!
 *
 */
public class App {
    private static boolean infos;
    private static boolean actions;
    private static boolean erreur;
    private static String robots;
    private static int nbParties;
    static {
        infos = false;
        actions = false;
        erreur = false;
        nbParties = 1;
        robots = new String();
    }

    /**
     * Fonction pour decrire les commandes a utiliser
     * pour afficher les infos , les actions , indiquer le nombre de parties a jouer ... 
     */
    private static void help(){
        String help = "-h affiche cette aide.\n"+
        "-i affiche les infos\n"+
        "-a affiche les actions\n"+
        "-d affiche les erreurs\n"+
        "-n <nombreDeParties> indique le nombre de parties à jouer\n"+
        "-j <nomDuRobot>,<nomDuJoueur> ajoute un robot avec ce nom à la liste";
        System.out.println(help);
        System.exit(0);
    }
    
    /**
     * Fonction pour recuperer les parametres de la commande entree 
     * @param args : les parametres de la commande entree
     */
    private static void parametres(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            switch (args[i]) {
            case "-i":
                infos = true;
                break;
            case "-a":
                actions = true;
                break;
            case "-d":
                erreur = true;
                break;
            case "-n":
                nbParties = Integer.parseInt(args[++i]);
                break;
            case "-h":
                help();
                break;
            case "-j":
                String robot = args[++i];
                if (robot.contains(",")){
                    robots += robot+" ";
                }
                break;
            default:
                return;
            }
        }
    }

    /**
     * Fonction du benchmark 
     */
    private static void benchmark(){
        robots = "Bob,JoueurBob Marcel,JoueurMarcel";
        Afficheur.nouveauAfficheur();
        Afficheur.getInstance().setInfo(false);
        Afficheur.getInstance().setActions(false);
        Afficheur.getInstance().setErreur(false);
        FabriqueDePartie fab = new FabriqueDePartie(robots);
        fab.lancerNParties(1000);
        robots = "MarcelPanda,JoueurMP MarcelJardinier,JoueurMJ";
        fab = new FabriqueDePartie(robots);
        fab.lancerNParties(1000);
    }

    /**
     * Fonction Main
     * @param args
     */
    public static void main(String[] args) {
        if(args.length == 0){
            benchmark();
            return;
        }
        parametres(args);
        if (robots.length() == 0 || nbParties == 0)
            return;
        //System.out.println(robots);
        Afficheur.nouveauAfficheur();
        Afficheur.getInstance().setInfo(infos);
        Afficheur.getInstance().setActions(actions);
        Afficheur.getInstance().setErreur(erreur);
        FabriqueDePartie fab = new FabriqueDePartie(robots);
        fab.lancerNParties(nbParties);
    }
}
