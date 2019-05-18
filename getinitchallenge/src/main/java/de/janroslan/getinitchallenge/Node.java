package de.janroslan.getinitchallenge;



import java.util.HashMap;



/**
 * Stellt einen Knoten in einem ungerichteten Graph dar, auf den der Dijkstra-Algorithmus ausgefürt werden kann.
 *
 * @author Jackj
 */
public class Node {



    private final String label;

    // Adjazenzliste der Nachbarn (Key: Nachbarindex, Value: Kosten)
    private final HashMap<Integer, Double> neighbours;

    // Distanz zum Startknoten (Default: unendlich)
    private double distance;

    // Vorgängerknoten im Pfad zum Ziel (Default: null)
    private Node predecessor;



    public Node(String label) {
        this.label = label;
        neighbours = new HashMap<>();
        predecessor = null;

        // Distanz wird anfangs auf unendlich gesetzt, da im Verlauf des Algorithmus die Distanz 
        distance = Double.POSITIVE_INFINITY;
    }



    public String getLabel() {
        return label;
    }



    public HashMap<Integer, Double> getNeighbours() {
        return neighbours;
    }



    public double getDistance() {
        return distance;
    }



    public Node getPredecessor() {
        return predecessor;
    }



    public void setDistance(double distance) {
        this.distance = distance;
    }



    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }
}
