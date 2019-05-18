package de.janroslan.getinitchallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;



/**
 * Implementation des Dijkstra-Algorithmus zum Lösen des kürzesten Pfades zweier Knoten in einem ungerichteten Graphen
 * 
 * @author Jan-Philipp Roslan
 */
public class DijkstraSolver {



    private final Node[] graph;
    
    private final int start;
    private final int end;


    private final PriorityQueue<Node> queue;

    
    /**
     * 
     * @param start - Index des Startknoten
     * @param end - Index des Endknoten
     * @param graph 
     */
    public DijkstraSolver(int start, int end, Node[] graph) {
        this.graph = graph;
        this.start = start;
        this.end = end;
        
        // Priorität in der Queue = Kleinste Distanz zur Erde hat höchste Priorität
        queue = new PriorityQueue<>((a, b) -> (a.getDistance() > b.getDistance()) ? 1 : -1);
    }

    public DijkstraResult solve() {

        // Distanz der Erde zu sich selber = 0
        graph[start].setDistance(0);

        // Hinzufügen der Erde in die Queue
        queue.add(graph[start]);
        
        Node endNode = graph[end];

        while (!queue.isEmpty()) {
            Node nearest = queue.poll();

            // Wenn der derzeitige Knoten bereits der Zielknoten ist, sind wir fertig
            if (nearest.equals(endNode)) {
                return new DijkstraResult(nearest.getDistance(), resolvePredecessors(nearest));
            }

            nearest.getNeighbours().forEach((index, cost) -> {
                Node neighbour = graph[index];
                
                if (!queue.contains(neighbour) && neighbour.getDistance() == Double.POSITIVE_INFINITY) {
                    // Nachbar wurde noch nie untersucht (=Distanz unendlich) -> Gefundene Distanz setzen und in Queue hinzufügen
                    
                    neighbour.setDistance(nearest.getDistance() + cost);
                    neighbour.setPredecessor(nearest);
                    queue.add(neighbour);
                } else if (queue.contains(neighbour) && nearest.getDistance() + cost < neighbour.getDistance()) {
                    // Nachbar befindet sich bereits in der Queue, aber es wurde ein kürzerer Weg gefunden -> Neue, bessere Distanz setzen
                    
                    neighbour.setDistance(nearest.getDistance() + cost);
                    neighbour.setPredecessor(nearest);
                }
            });
        }

        return new DijkstraResult(endNode.getDistance(), resolvePredecessors(endNode));
    }

    
    /**
     * Hilfmethode, welche alle Vorgänger bis zum Startknoten eines gegebenen Knoten auflöst und ihre Labels zurückgibt.
     * @param kn
     * @return 
     */
    private String[] resolvePredecessors(Node kn) {
        ArrayList<String> listResult = new ArrayList<>();
        Node currentKn = kn;
        listResult.add(currentKn.getLabel());
        do {
            listResult.add(currentKn.getPredecessor().getLabel());
            currentKn = currentKn.getPredecessor();
        } while (currentKn.getPredecessor() != null);

        Collections.reverse(listResult);
        String[] result = new String[listResult.size()];
        return listResult.toArray(result);
    }



}
