package PinHead.automate;

import PinHead.exceptions.ExceptionCaseCourante;
import PinHead.exceptions.ExceptionCaseInaccessible;
import PinHead.exceptions.ExceptionCaseInexistante;
import PinHead.moteur.*;
import PinHead.moteur.entites.*;
import PinHead.moteur.utils.Afficheur;
import PinHead.moteur.utils.Paire;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Maxime Bouis
 */
public class Marcel extends Robot {

    private boolean aPioche;
    private boolean abougePanda;
    private boolean aPoseParcelle;
    private boolean abougeJardinier;
    private int propPanda;
    private int propJardinier;
    private int propParcelle;
    private int totProp;

    private Coordonnees derniereCoordDepPanda;
    private int nbRepMemeDepPanda;

    private Coordonnees derniereCoordDepJardinier;
    private int nbRepMemeDepJardinier;

    private static HashMap<Integer, ArrayList<Chemin>> cheminsFigures = new HashMap<>();
    static {
        ArrayList<Chemin> liste1 = new ArrayList<>();
        Chemin triangleH1 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                    add(Directions.DH);
                                                                    add(Directions.DB);}});
        Chemin triangleH2 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                    add(Directions.DB);
                                                                    add(Directions.G);}});
        Chemin triangleH3 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                    add(Directions.G);
                                                                    add(Directions.DH);}});
        liste1.add(triangleH1);
        liste1.add(triangleH2);
        liste1.add(triangleH3);
        cheminsFigures.put(triangleH1.hashCode(), liste1);

        ArrayList<Chemin> liste2 = new ArrayList<>();
        Chemin triangleB1 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                    add(Directions.DB);
                                                                    add(Directions.DH);}});
        Chemin triangleB2 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                    add(Directions.DH);
                                                                    add(Directions.G);}});
        Chemin triangleB3 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                    add(Directions.G);
                                                                    add(Directions.DB);}});
        liste2.add(triangleB1);
        liste2.add(triangleB2);
        liste2.add(triangleB3);
        cheminsFigures.put(triangleB1.hashCode(), liste2);

        ArrayList<Chemin> liste3 = new ArrayList<>();
        Chemin losange1 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                  add(Directions.GB);
                                                                  add(Directions.DB);
                                                                  add(Directions.DH);}});
        Chemin losange2 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                  add(Directions.DB);
                                                                  add(Directions.DH);
                                                                  add(Directions.GH);}});
        Chemin losange3 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                  add(Directions.DH);
                                                                  add(Directions.GH);
                                                                  add(Directions.GB);}});
        Chemin losange4 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                  add(Directions.GH);
                                                                  add(Directions.GB);
                                                                  add(Directions.DB);}});
        liste3.add(losange1);
        liste3.add(losange2);
        liste3.add(losange3);
        liste3.add(losange4);
        cheminsFigures.put(losange1.hashCode(), liste3);

        ArrayList<Chemin> liste4 = new ArrayList<>();
        Chemin chevronG1 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                   add(Directions.GB);
                                                                   add(Directions.DB);}});
        Chemin chevronG2 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                   add(Directions.GH);
                                                                   add(Directions.DH);}});
        liste4.add(chevronG1);
        liste4.add(chevronG2);
        cheminsFigures.put(chevronG1.hashCode(), liste4);

        ArrayList<Chemin> liste5 = new ArrayList<>();
        Chemin chevronD1 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                   add(Directions.DB);
                                                                   add(Directions.GB);}});
        Chemin chevronD2 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                   add(Directions.DH);
                                                                   add(Directions.GH);}});
        liste5.add(chevronD1);
        liste5.add(chevronD2);
        cheminsFigures.put(chevronD1.hashCode(), liste5);

        ArrayList<Chemin> liste6 = new ArrayList<>();
        Chemin ligneDD1 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                  add(Directions.DH);
                                                                  add(Directions.DH);}});
        Chemin ligneDD2 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                  add(Directions.GB);
                                                                  add(Directions.GB);}});
        liste6.add(ligneDD1);
        liste6.add(ligneDD2);
        cheminsFigures.put(ligneDD1.hashCode(), liste6);

        ArrayList<Chemin> liste7 = new ArrayList<>();
        Chemin ligneDG1 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                  add(Directions.GH);
                                                                  add(Directions.GH);}});
        Chemin ligneDG2 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                  add(Directions.DB);
                                                                  add(Directions.DB);}});
        liste7.add(ligneDG1);
        liste7.add(ligneDG2);
        cheminsFigures.put(ligneDG1.hashCode(), liste7);

        ArrayList<Chemin> liste8 = new ArrayList<>();
        Chemin ligneH1 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                 add(Directions.D);
                                                                 add(Directions.D);}});
        Chemin ligneH2 = new Chemin(new ArrayList<Directions>(){{add(Directions.O);
                                                                 add(Directions.G);
                                                                 add(Directions.G);}});
        liste8.add(ligneH1);
        liste8.add(ligneH2);
        cheminsFigures.put(ligneH1.hashCode(), liste8);
    }

    public Marcel(String nom){
        this(nom, 100, 0, 0);
    }

    public Marcel(String nom, int propPanda, int propJardinier, int propParcelle) {
        super(nom);
        derniereCoordDepPanda = null;
        nbRepMemeDepPanda = 0;
        derniereCoordDepJardinier = null;
        nbRepMemeDepJardinier = 0;
        this.propPanda = propPanda;
        this.propJardinier = propJardinier;
        this.propParcelle = propParcelle;
        totProp = this.propJardinier + this.propParcelle + this.propPanda;
    }

    private void mettreAJour() {
        aPioche = false;
        abougePanda = false;
        if(joueur.parcellesRestantesPioche() > 0) {
            aPoseParcelle = false;
        } else {
            aPoseParcelle = true;
        }
        abougeJardinier = false;
    }

    private void casSpecialParcelle() {
        Set<Coordonnees> coords = joueur.coordonneesValidesPlacementParcelle();
        Iterator<Coordonnees> iterator = coords.iterator();
        ArrayList<Couleurs> toutesLesCouleurs = new ArrayList<Couleurs>();
        toutesLesCouleurs.add(Couleurs.jaune);
        toutesLesCouleurs.add(Couleurs.rose);
        toutesLesCouleurs.add(Couleurs.vert);
        placerParcelle(iterator.next(), toutesLesCouleurs);
    }

    private void casSpecialPanda() {
        Random random = new Random(System.nanoTime());
        boolean doitChoisir = true;
        while(doitChoisir) {
            int choix = random.nextInt(6);
            switch (choix) {
                case 0:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesPanda().getD())) {
                        deplacerPanda(joueur.obtenirCoordonneesPanda().getD());
                        doitChoisir = false;
                    }
                    break;
                case 1:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesPanda().getG())) {
                        deplacerPanda(joueur.obtenirCoordonneesPanda().getG());
                        doitChoisir = false;
                    }
                    break;
                case 2:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesPanda().getHD())) {
                        deplacerPanda(joueur.obtenirCoordonneesPanda().getHD());
                        doitChoisir = false;
                    }
                    break;
                case 3:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesPanda().getHG())) {
                        deplacerPanda(joueur.obtenirCoordonneesPanda().getHG());
                        doitChoisir = false;
                    }
                    break;
                case 4:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesPanda().getBD())) {
                        deplacerPanda(joueur.obtenirCoordonneesPanda().getBD());
                        doitChoisir = false;
                    }
                    break;
                case 5:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesPanda().getBG())) {
                        deplacerPanda(joueur.obtenirCoordonneesPanda().getBG());
                        doitChoisir = false;
                    }
                    break;
            }
        }
    }

    private void casSpecialJardinier() {
        Random random = new Random(System.nanoTime());
        boolean doitChoisir = true;
        while(doitChoisir) {
            int choix = random.nextInt(6);
            switch (choix) {
                case 0:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesJardinier().getD())) {
                        deplacerJardinier(joueur.obtenirCoordonneesJardinier().getD());
                        doitChoisir = false;
                    }
                    break;
                case 1:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesJardinier().getG())) {
                        deplacerJardinier(joueur.obtenirCoordonneesJardinier().getG());
                        doitChoisir = false;
                    }
                    break;
                case 2:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesJardinier().getHD())) {
                        deplacerJardinier(joueur.obtenirCoordonneesJardinier().getHD());
                        doitChoisir = false;
                    }
                    break;
                case 3:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesJardinier().getHG())) {
                        deplacerJardinier(joueur.obtenirCoordonneesJardinier().getHG());
                        doitChoisir = false;
                    }
                    break;
                case 4:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesJardinier().getBD())) {
                        deplacerJardinier(joueur.obtenirCoordonneesJardinier().getBD());
                        doitChoisir = false;
                    }
                    break;
                case 5:
                    if(joueur.coordonneesValides(joueur.obtenirCoordonneesJardinier().getBG())) {
                        deplacerJardinier(joueur.obtenirCoordonneesJardinier().getBG());
                        doitChoisir = false;
                    }
                    break;
            }
        }
    }

    private void casSpecial() {
        if(!aPioche) {
            piocherObjectif();
        } else if(!aPoseParcelle) {
            casSpecialParcelle();
        } else if(!abougeJardinier) {
            casSpecialJardinier();
        } else if(!abougePanda) {
            casSpecialPanda();
        } else {
            Afficheur.getInstance().afficherErreur("ERREUR : PASSE SONT TOUR !!");
        }
    }

    /**
     * fait piocher un objectif au joueur de façon aléatoire
     */
    private void piocherObjectif() {
        if(joueur.obtenirMainObjectif().size() < 5) {
            Random random = new Random(System.nanoTime());
            int choix = random.nextInt(totProp);
            if (choix < propParcelle && joueur.parcellesRestantesPioche() > 0 && !joueur.piocheJardinierEstVide()) {
                joueur.piocherObjectif(TypesObjectifs.PARCELLE);
            } else if (choix < propParcelle + propPanda && !joueur.piochePandaEstVide()) {
                joueur.piocherObjectif(TypesObjectifs.PANDA);
            } else if (choix < totProp && !joueur.piocheJardinierEstVide()) {
                joueur.piocherObjectif(TypesObjectifs.JARDINIER);
            } else {
                aPioche = true;
                casSpecial();
            }
        } else {
            aPioche = true;
            casSpecial();
        }
        aPioche = true;
    }

    /**
     *
     * @param depart
     * @param arrivee
     * @return le chemin optimal pour atteindre une case
     */
    private ArrayList<Directions> trouverCheminOptimal(Coordonnees depart, Coordonnees arrivee) {
        ArrayList<Directions> chemin = new ArrayList<Directions>();
        int X_arrivee = arrivee.getX();
        int Y_arrivee = arrivee.getY();
        X_arrivee -= depart.getX();
        Y_arrivee -= depart.getY();

        if(X_arrivee == 0 && Y_arrivee == 0)
            chemin.add(Directions.O);

        for(; X_arrivee < 0 && Y_arrivee < 0; ++X_arrivee, ++Y_arrivee)
            chemin.add(Directions.G);

        for(; X_arrivee > 0 && Y_arrivee > 0; --X_arrivee, --Y_arrivee)
            chemin.add(Directions.D);

        for(; X_arrivee < 0; ++X_arrivee)
            chemin.add(Directions.GB);

        for(; X_arrivee > 0; --X_arrivee)
            chemin.add(Directions.DH);

        for(; Y_arrivee < 0; ++Y_arrivee)
            chemin.add(Directions.GH);

        for(; Y_arrivee > 0; --Y_arrivee)
            chemin.add(Directions.DB);

        return chemin;
    }

    /**
     *
     * @param pos
     * @param dir
     * @return le voisin de la position en fonction de la direction
     */
    private Coordonnees trouverVoisinFctDirection(Coordonnees pos, Directions dir) {
        switch (dir.ordinal()) {
            case 1:
                return pos.getD();
            case 2:
                return pos.getG();
            case 3:
                return pos.getHD();
            case 4:
                return pos.getHG();
            case 5:
                return pos.getBD();
            case 6:
                return pos.getBG();
            default:
                return pos;
        }
    }

    /**
     *
     * @param parcelles
     * @return la parcelle que devra atteindre le panda
     */
    private Parcelle trouverCaseAAtteindre(ArrayList<Parcelle> parcelles) {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        for(int i = 0; i < parcelles.size(); ++i) {
            Integer scoreBambou = parcelles.get(i).getTailleBambou();
            scores.add(i, scoreBambou);
        }
        int indMax = 0;
        for(int i = 0; i < scores.size(); i++) {
            if(scores.get(i) > scores.get(indMax)) indMax = i;
        }

        return parcelles.get(indMax);
    }

    private void deplacerPanda(Coordonnees coords) {
        try {
            joueur.deplacerPanda(coords);
            abougePanda = true;
            if(coords.equals(derniereCoordDepPanda)) {
                ++nbRepMemeDepPanda;
            } else {
                nbRepMemeDepPanda = 0;
            }
            derniereCoordDepPanda = coords;
        } catch (ExceptionCaseCourante exceptionCaseCourante) {
            exceptionCaseCourante.printStackTrace();
        } catch (ExceptionCaseInaccessible exceptionCaseInaccessible) {
            exceptionCaseInaccessible.printStackTrace();
        } catch (ExceptionCaseInexistante exceptionCaseInexistante) {
            exceptionCaseInexistante.printStackTrace();
        }
    }

    private void deplacerJardinier(Coordonnees coords) {
        try {
            joueur.deplacerJardinier(coords);
            abougeJardinier = true;
            if(coords.equals(derniereCoordDepJardinier)) {
                ++nbRepMemeDepJardinier;
            } else {
                nbRepMemeDepJardinier = 0;
            }
            derniereCoordDepJardinier = coords;
        } catch (ExceptionCaseCourante exceptionCaseCourante) {
            exceptionCaseCourante.printStackTrace();
        } catch (ExceptionCaseInaccessible exceptionCaseInaccessible) {
            exceptionCaseInaccessible.printStackTrace();
        } catch (ExceptionCaseInexistante exceptionCaseInexistante) {
            exceptionCaseInexistante.printStackTrace();
        }
    }

    private void choixDeplacementJardinierFctObjPanda (List<Parcelle> parcellesJardinier,
                                                       Coordonnees positionInitJardinier) {
        ArrayList<Directions> cheminJardinier = trouverCheminOptimal(positionInitJardinier,
                parcellesJardinier.get(0).getCoordonnees());
        for (Parcelle parc : parcellesJardinier) {
            cheminJardinier = trouverCheminOptimal(positionInitJardinier, parc.getCoordonnees());
            if (joueur.coordonneesValides(trouverVoisinFctDirection(positionInitJardinier, cheminJardinier.get(0)))) {
                Coordonnees premiereCaseJardinier = trouverVoisinFctDirection(positionInitJardinier,
                        cheminJardinier.get(0));
                for (int i = 1; i < cheminJardinier.size(); ++i) {
                    if (cheminJardinier.get(i) == cheminJardinier.get(i - 1)
                            && joueur.coordonneesValides(trouverVoisinFctDirection(premiereCaseJardinier, cheminJardinier.get(i)))) {
                        premiereCaseJardinier = trouverVoisinFctDirection(premiereCaseJardinier, cheminJardinier.get(i));
                    } else {
                        break;
                    }
                }
                deplacerJardinier(premiereCaseJardinier);
                return;
            }
        }
        if(!abougeJardinier) {
            if(!aPoseParcelle) {
                ArrayList<Couleurs> toutesLesCouleurs = new ArrayList<Couleurs>();
                toutesLesCouleurs.add(Couleurs.jaune);
                toutesLesCouleurs.add(Couleurs.rose);
                toutesLesCouleurs.add(Couleurs.vert);
                placerParcelle(trouverVoisinFctDirection(positionInitJardinier,
                        cheminJardinier.get(0)), toutesLesCouleurs);
            } else {
                casSpecial();
            }
        }
    }

    private void poserParcelleFctObjPanda (Coordonnees posDepart, ObjectifPanda objectif) {
        if(!aPoseParcelle) {
            ArrayList<Couleurs> couleurObjectif = new ArrayList<Couleurs>();
            couleurObjectif.add(objectif.getCouleur()[0]);
            Coordonnees nouvellesCoordsAPlacer;
            Random random = new Random(System.nanoTime());
            switch (random.nextInt(6)) {
                case 2:
                    nouvellesCoordsAPlacer = posDepart.getG();
                    break;
                case 3:
                    nouvellesCoordsAPlacer = posDepart.getHD();
                    break;
                case 4:
                    nouvellesCoordsAPlacer = posDepart.getHG();
                    break;
                case 5:
                    nouvellesCoordsAPlacer = posDepart.getBD();
                    break;
                case 6:
                    nouvellesCoordsAPlacer = posDepart.getBG();
                    break;
                default:
                    nouvellesCoordsAPlacer = posDepart.getD();
                    break;
            }

            placerParcelle(nouvellesCoordsAPlacer, couleurObjectif);
        } else {
            casSpecial();
        }
    }

    /**
     * Choisi quelle action effectuer pour valider un objectif Panda
     * @param objectif
     */
    private void comportementObjectifPanda(ObjectifPanda objectif) {
        Coordonnees posDepart = joueur.obtenirCoordonneesPanda();
        List<Parcelle> parcelles = joueur.obtenirParcellesColorees(objectif.getCouleur()[0]);
        ArrayList<Parcelle> parcellesASuppr = new ArrayList<Parcelle>();
        for(Parcelle parc : parcelles) {
            if(parc.getCoordonnees().equals(posDepart) || parc.getTailleBambou() == 0)
                parcellesASuppr.add(parc);
        }
        parcelles.removeAll(parcellesASuppr);

        if(parcelles.size() == 0 || abougePanda) {
            List<Parcelle> parcellesJardinier = joueur.obtenirParcellesColorees(objectif.getCouleur()[0]);
            Coordonnees positionInitJardinier = joueur.obtenirCoordonneesJardinier();
            parcellesASuppr = new ArrayList<Parcelle>();
            for(Parcelle parc : parcellesJardinier) {
                if(parc.getCoordonnees().equals(positionInitJardinier)) {
                    parcellesASuppr.add(parc);
                }
            }
            parcellesJardinier.removeAll(parcellesASuppr);
            if(!abougeJardinier && parcellesJardinier.size() > 0) {
                choixDeplacementJardinierFctObjPanda(parcellesJardinier, positionInitJardinier);
                return;
            } else if (!aPoseParcelle) {
                poserParcelleFctObjPanda(posDepart, objectif);
                return;
            } else if (!aPioche) {
                piocherObjectif();
                return;
            } else {
                casSpecial();
                return;
            }
        }

        Parcelle caseAAtteindre = trouverCaseAAtteindre(new ArrayList<Parcelle>(parcelles));
        ArrayList<Directions> chemin = trouverCheminOptimal(posDepart, caseAAtteindre.getCoordonnees());

        if(joueur.coordonneesValides(trouverVoisinFctDirection(posDepart, chemin.get(0)))) {
            Coordonnees premiereCaseAAtteindre = trouverVoisinFctDirection(posDepart, chemin.get(0));
            for (int i = 1; i < chemin.size(); ++i) {
                if (chemin.get(i) == chemin.get(i - 1)
                        && joueur.coordonneesValides(trouverVoisinFctDirection(premiereCaseAAtteindre, chemin.get(i)))) {
                    premiereCaseAAtteindre = trouverVoisinFctDirection(premiereCaseAAtteindre, chemin.get(i));
                } else {
                    break;
                }
            }
            deplacerPanda(premiereCaseAAtteindre);
        } else {
            if(!aPoseParcelle) {
                ArrayList<Couleurs> toutesLesCouleurs = new ArrayList<Couleurs>();
                toutesLesCouleurs.add(Couleurs.jaune);
                toutesLesCouleurs.add(Couleurs.rose);
                toutesLesCouleurs.add(Couleurs.vert);
                placerParcelle(trouverVoisinFctDirection(posDepart, chemin.get(0)), toutesLesCouleurs);
            } else {
                casSpecial();
            }
        }
    }

    /**
     *
     * @param depart
     * @param chemins
     * @param couleur
     * @return le score de chaque chemin possible pour une parcelle de départ
     */
    private ArrayList<Paire<Integer, Chemin>> calculerScoreFigure(Parcelle depart, ArrayList<Chemin> chemins, Couleurs couleur) {
        ArrayList<Paire<Integer, Chemin>> result = new ArrayList<>();
        for(Chemin chemin : chemins) {
            Coordonnees caseCourante = depart.getCoordonnees();
            Integer score = 0;
            for (Directions dir : chemin) {
                switch (dir.ordinal()) {
                    case 1:
                        if (!joueur.coordonneesValides(caseCourante.getD())) {
                            ++score;
                            if (!joueur.peutPlacerParcelle(caseCourante.getD())) {
                                score += 4;
                            }
                        } else if (joueur.obtenirParcelle(caseCourante.getD()).getCouleur() != couleur) {
                            score = 666;
                        }
                        caseCourante = caseCourante.getD();
                        break;
                    case 2:
                        if (!joueur.coordonneesValides(caseCourante.getG())) {
                            ++score;
                            if (!joueur.peutPlacerParcelle(caseCourante.getG())) {
                                score += 4;
                            }
                        } else if (joueur.obtenirParcelle(caseCourante.getG()).getCouleur() != couleur) {
                            score = 666;
                        }
                        caseCourante = caseCourante.getG();
                        break;
                    case 3:
                        if (!joueur.coordonneesValides(caseCourante.getHD())) {
                            ++score;
                            if (!joueur.peutPlacerParcelle(caseCourante.getHD())) {
                                score += 4;
                            }
                        } else if (joueur.obtenirParcelle(caseCourante.getHD()).getCouleur() != couleur) {
                            score = 666;
                        }
                        caseCourante = caseCourante.getHD();
                        break;
                    case 4:
                        if (!joueur.coordonneesValides(caseCourante.getHG())) {
                            ++score;
                            if (!joueur.peutPlacerParcelle(caseCourante.getHG())) {
                                score += 4;
                            }
                        } else if (joueur.obtenirParcelle(caseCourante.getHG()).getCouleur() != couleur) {
                            score = 666;
                        }
                        caseCourante = caseCourante.getHG();
                        break;
                    case 5:
                        if (!joueur.coordonneesValides(caseCourante.getBD())) {
                            ++score;
                            if (!joueur.peutPlacerParcelle(caseCourante.getBD())) {
                                score += 4;
                            }
                        } else if (joueur.obtenirParcelle(caseCourante.getBD()).getCouleur() != couleur) {
                            score = 666;
                        }
                        caseCourante = caseCourante.getBD();
                        break;
                    case 6:
                        if (!joueur.coordonneesValides(caseCourante.getBG())) {
                            ++score;
                            if (!joueur.peutPlacerParcelle(caseCourante.getBG())) {
                                score += 4;
                            }
                        } else if (joueur.obtenirParcelle(caseCourante.getBG()).getCouleur() != couleur) {
                            score = 666;
                        }
                        caseCourante = caseCourante.getBG();
                        break;
                }
            }
            result.add(new Paire<Integer, Chemin>(score, chemin));
        }
        return result;
    }

    /**
     *
     * @param parcelles
     * @param chemin
     * @param couleur
     * @return renvoie la parcelle à partir de laquelle la figure sera le plus simple à dessiner
     */
    private Paire<Parcelle, Chemin> trouverMeilleureCaseDeDepart(ArrayList<Parcelle> parcelles, Chemin chemin, Couleurs couleur) {
        HashMap<Parcelle, ArrayList<Paire<Integer, Chemin>>> scores = new HashMap<>();
        for(Parcelle parcelle : parcelles) {
            scores.put(parcelle, calculerScoreFigure(parcelle, cheminsFigures.get(chemin.hashCode()), couleur));
        }

        ArrayList<ArrayList<Paire<Integer, Chemin>>> listesScores = new ArrayList<>();
        for(Parcelle parcelle : scores.keySet()) {
            listesScores.add(scores.get(parcelle));
        }
        Integer min = 666;
        for(ArrayList<Paire<Integer, Chemin>> scoresParcelle : listesScores) {
            for(Paire<Integer, Chemin> unScore : scoresParcelle) {
                if(unScore.getGauche() < min) min = unScore.getGauche();
            }
        }
        if(min == 666) {
            return null;
        }
        for(Parcelle parcelle : scores.keySet()) {
            for(Paire<Integer, Chemin> unscore : scores.get(parcelle)) {
                if(unscore.getGauche() == min) {
                    return new Paire<Parcelle, Chemin>(parcelle, unscore.getDroit());
                }
            }
        }
        return null;
    }

    private void placerParcelle(Coordonnees coordonnees, ArrayList<Couleurs> couleurs) {
        if(!aPoseParcelle) {
            joueur.piocherParcelle();
            List<Parcelle> parcellesPiochees = joueur.obtenirMainParcelles();
            boolean estPosee = false;
            for (Parcelle parcelle : parcellesPiochees) {
                boolean bonneCouleur = false;
                for (Couleurs couleur : couleurs) {
                    if (couleur == parcelle.getCouleur()) {
                        bonneCouleur = true;
                        break;
                    }
                }
                if (bonneCouleur && joueur.peutPlacerParcelle(coordonnees)) {
                    joueur.placerParcelle(parcelle, coordonnees);
                    estPosee = true;
                    aPoseParcelle = true;
                    break;
                }
            }
            if (!estPosee) {
                Set<Coordonnees> coordsValides = joueur.coordonneesValidesPlacementParcelle();
                for (Coordonnees coords : coordsValides) {
                    if (!coords.equals(coordonnees)) {
                        joueur.placerParcelle(parcellesPiochees.get(0), coords);
                        aPoseParcelle = true;
                        break;
                    }
                }
            }
        } else {
            casSpecial();
        }
    }

    /**
     * Place une parcelle pour dessiner la figure de l'objectif
     * @param meilleureCase
     * @param couleur
     */
    private boolean placerParcelleFctMeilleureCaseDep(Paire<Parcelle, Chemin> meilleureCase, Couleurs couleur) {
        if(!aPoseParcelle) {
            Coordonnees caseCourante = meilleureCase.getGauche().getCoordonnees();
            for (Directions dir : meilleureCase.getDroit()) {
                switch (dir.ordinal()) {
                    case 1:
                        if (!joueur.coordonneesValides(caseCourante.getD()) && joueur.peutPlacerParcelle(caseCourante.getD())) {
                            ArrayList<Couleurs> couleurs = new ArrayList<Couleurs>();
                            couleurs.add(couleur);
                            placerParcelle(caseCourante.getD(), couleurs);
                            return true;
                        }
                        caseCourante = caseCourante.getD();
                        break;
                    case 2:
                        if (!joueur.coordonneesValides(caseCourante.getG()) && joueur.peutPlacerParcelle(caseCourante.getG())) {
                            ArrayList<Couleurs> couleurs = new ArrayList<Couleurs>();
                            couleurs.add(couleur);
                            placerParcelle(caseCourante.getG(), couleurs);
                            return true;
                        }
                        caseCourante = caseCourante.getG();
                        break;
                    case 3:
                        if (!joueur.coordonneesValides(caseCourante.getHD()) && joueur.peutPlacerParcelle(caseCourante.getHD())) {
                            ArrayList<Couleurs> couleurs = new ArrayList<Couleurs>();
                            couleurs.add(couleur);
                            placerParcelle(caseCourante.getHD(), couleurs);
                            return true;
                        }
                        caseCourante = caseCourante.getHD();
                        break;
                    case 4:
                        if (!joueur.coordonneesValides(caseCourante.getHG()) && joueur.peutPlacerParcelle(caseCourante.getHG())) {
                            ArrayList<Couleurs> couleurs = new ArrayList<Couleurs>();
                            couleurs.add(couleur);
                            placerParcelle(caseCourante.getHG(), couleurs);
                            return true;
                        }
                        caseCourante = caseCourante.getHG();
                        break;
                    case 5:
                        if (!joueur.coordonneesValides(caseCourante.getBD()) && joueur.peutPlacerParcelle(caseCourante.getBD())) {
                            ArrayList<Couleurs> couleurs = new ArrayList<Couleurs>();
                            couleurs.add(couleur);
                            placerParcelle(caseCourante.getBD(), couleurs);
                            return true;
                        }
                        caseCourante = caseCourante.getBD();
                        break;
                    case 6:
                        if (!joueur.coordonneesValides(caseCourante.getBG()) && joueur.peutPlacerParcelle(caseCourante.getBG())) {
                            ArrayList<Couleurs> couleurs = new ArrayList<Couleurs>();
                            couleurs.add(couleur);
                            placerParcelle(caseCourante.getBG(), couleurs);
                            return true;
                        }
                        caseCourante = caseCourante.getBG();
                        break;
                    default:
                        break;
                }
            }
        }
        return false;
    }

    /**
     * Choisi quelle action effectuer pour valider un objectif Parcelle
     * @param objectif
     */
    private void comportementObjectifParcelle(ObjectifParcelle objectif) {
        List<Parcelle> parcelles = joueur.obtenirParcellesColorees(objectif.getCouleur()[0]);
        Chemin chemin = objectif.getChemin();
        Paire<Parcelle, Chemin> meilleureCaseChemin = trouverMeilleureCaseDeDepart(new ArrayList<>(parcelles), chemin, objectif.getCouleur()[0]);
        if(meilleureCaseChemin == null) {
            if(!aPoseParcelle) {
                ArrayList<Couleurs> couleurObjectif = new ArrayList<Couleurs>();
                couleurObjectif.add(objectif.getCouleur()[0]);
                Set<Coordonnees> coords = joueur.coordonneesValidesPlacementParcelle();
                Iterator<Coordonnees> iterator = coords.iterator();
                placerParcelle(iterator.next(), couleurObjectif);
            } else {
                casSpecial();
            }
        } else {
            if(!placerParcelleFctMeilleureCaseDep(meilleureCaseChemin, objectif.getCouleur()[0]) && !aPoseParcelle) {
                ArrayList<Couleurs> couleurObjectif = new ArrayList<Couleurs>();
                couleurObjectif.add(objectif.getCouleur()[0]);
                Set<Coordonnees> coords = joueur.coordonneesValidesPlacementParcelle();
                Iterator<Coordonnees> iterator = coords.iterator();
                placerParcelle(iterator.next(), couleurObjectif);
            } else {
                casSpecial();
            }
        }
    }

    private void comportementObjectifJardinier(ObjectifJardinier objectif) {
        Coordonnees posJardinier = joueur.obtenirCoordonneesJardinier();
        List<Parcelle> parcelles = joueur.obtenirParcellesColorees(objectif.getCouleur()[0]);
        if(parcelles.size() == 0) {
            if(!aPoseParcelle) {
                ArrayList<Couleurs> couleurObjectif = new ArrayList<Couleurs>();
                couleurObjectif.add(objectif.getCouleur()[0]);
                Set<Coordonnees> coords = joueur.coordonneesValidesPlacementParcelle();
                Iterator<Coordonnees> iterator = coords.iterator();
                placerParcelle(iterator.next(), couleurObjectif);
            } else {
                casSpecial();
            }
        } else {
            Parcelle caseAAtteindre = trouverCaseAAtteindre(new ArrayList<Parcelle>(parcelles));
            ArrayList<Directions> chemin = trouverCheminOptimal(posJardinier, caseAAtteindre.getCoordonnees());

            if(joueur.coordonneesValides(trouverVoisinFctDirection(posJardinier, chemin.get(0)))) {
                Coordonnees premiereCaseAAtteindre = trouverVoisinFctDirection(posJardinier, chemin.get(0));
                for (int i = 1; i < chemin.size(); ++i) {
                    if (chemin.get(i) == chemin.get(i - 1)
                            && joueur.coordonneesValides(trouverVoisinFctDirection(premiereCaseAAtteindre, chemin.get(i)))) {
                        premiereCaseAAtteindre = trouverVoisinFctDirection(premiereCaseAAtteindre, chemin.get(i));
                    } else {
                        break;
                    }
                }
                if(premiereCaseAAtteindre.equals(posJardinier)) {
                    for(Coordonnees coords : joueur.obtenirToutesCoordonnées()) {
                        if(coords.estUnVoisin(posJardinier)) {
                            premiereCaseAAtteindre = coords;
                            break;
                        }
                    }
                }
                deplacerJardinier(premiereCaseAAtteindre);
            } else {
                if(!aPoseParcelle) {
                    ArrayList<Couleurs> toutesLesCouleurs = new ArrayList<Couleurs>();
                    toutesLesCouleurs.add(Couleurs.jaune);
                    toutesLesCouleurs.add(Couleurs.rose);
                    toutesLesCouleurs.add(Couleurs.vert);
                    placerParcelle(trouverVoisinFctDirection(posJardinier, chemin.get(0)), toutesLesCouleurs);
                } else {
                    casSpecial();
                }
            }
        }
    }

    /**
     * Choisi les actions générales à effectuer
     */
    private void choixActions() {
        if(nbRepMemeDepPanda > 7) {
            casSpecialPanda();
            return;
        } else if(nbRepMemeDepJardinier > 7) {
            casSpecialJardinier();
            return;
        }
        if(joueur.obtenirMainObjectif().size() == 0 && !aPioche) {
            piocherObjectif();
        } else {
            if(joueur.obtenirMainObjectif().size() > 0) {
                boolean ajoue = false;
                for(Objectif objectif : joueur.obtenirMainObjectif()) {
                    if (objectif instanceof ObjectifParcelle && joueur.parcellesRestantesPioche() > 0) {
                        comportementObjectifParcelle((ObjectifParcelle) objectif);
                        ajoue = true;
                        break;
                    } else if (objectif instanceof ObjectifPanda) {
                        comportementObjectifPanda((ObjectifPanda) objectif);
                        ajoue = true;
                        break;
                    } else if (objectif instanceof ObjectifJardinier) {
                        comportementObjectifJardinier((ObjectifJardinier) objectif);
                        ajoue = true;
                        break;
                    }
                }
                if(!ajoue) {
                    if(!aPioche) {
                        piocherObjectif();
                    } else {
                        casSpecial();
                    }
                }
            } else {
                casSpecial();
            }
        }
    }

    @Override
    public void jouer(int nbActions) {
        mettreAJour();
        if(this.joueur == null) return;
        for(; nbActions > 0; nbActions--) {
            choixActions();
            joueur.testerObjectifs();
        }
    }
}
