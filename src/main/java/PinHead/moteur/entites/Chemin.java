package PinHead.moteur.entites;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Chemin implements Iterable<Directions> {

    private final ArrayList<Directions> directions;

    /**
     * Constructeur
     * @param directions liste des directions pour creer le chemin
     */
    public Chemin(ArrayList<Directions> directions) {
        this.directions = directions;
    }

    @Override
    public Iterator<Directions> iterator() {
        return directions.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Chemin) {
            Chemin comp = (Chemin) o;
            Iterator<Directions> it1 = this.iterator();
            Iterator<Directions> it2 = comp.iterator();
            while(it1.hasNext() && it2.hasNext()) {
                if(it1.next() != it2.next()) return false;
            }
            if(!it1.hasNext() && !it2.hasNext()) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        for(Directions dir : directions) {
            result += dir + " ";
        }
        return result;
    }

    @Override
    public int hashCode () {
        return Objects.hash(directions);
    }
}
