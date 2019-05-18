package de.janroslan.getinitchallenge;



import java.util.Arrays;



/**
 * Stellt Resultatsinformationen für den Dijkstra-Algorithmus bereit.
 *
 * @author Jackj
 */
public class DijkstraResult {



    private final double costs;
    private final String[] path;



    public DijkstraResult(double costs, String[] path) {
        this.costs = costs;
        this.path = path;
    }



    public double getCosts() {
        return costs;
    }



    public String[] getPath() {
        return path;
    }



    @Override
    public String toString() {
        return "Resultat:\t" + "Kosten zum Ziel= " + costs + ", Pfad zum Ziel= " + Arrays.toString(path);
    }
}
