package de.janroslan.getinitchallenge;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/**
 * Helferklasse, die die JSON-Datei der Challenge einliest und eine Graphdarstellung konstruiert, auf die der Algorithmus arbeiten kann.
 * 
 * Zum einlesen der JSON-Datei wird die "json-simple"-Bibliothek verwendet, welche unter Apache License 2.0 zur Verfügung steht.
 * https://github.com/fangyidong/json-simple/blob/master/LICENSE.txt
 * 
 * @author Jan-Philipp Roslan
 */
public class JsonGraphParser {



    private final String fileLocation;

    public JsonGraphParser(String fileLocation) {
        this.fileLocation = fileLocation;
    }


    /**
     * Kostruiert den Graph aus der JSON-Datei
     * @return - Der Graph auf dem der Dijkstra-Algorithmus ausgeführt werden kann, dargestellt als Array der Knoten
     * @throws IOException - 
     * @throws ParseException 
     */
    public Node[] parse() throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(fileLocation)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray nodes = (JSONArray) jsonObject.get("nodes");

            Node[] graph = new Node[nodes.size()];

            // Iteration über die JSON-"Nodes" zum Initialisieren der Knoten
            for (int i = 0; i < nodes.size(); i++) {
                JSONObject jsonNode = (JSONObject) nodes.get(i);
                String label = (String) jsonNode.get("label");
                graph[i] = new Node(label);
            }

            // Iteration über JSON-"Edges" zum Auflösen der Nachbarknoten
            JSONArray jsonEdges = (JSONArray) jsonObject.get("edges");

            for (Object obj : jsonEdges) {
                JSONObject edge = (JSONObject) obj;

                int source = ((Long) edge.get("source")).intValue();
                int target = ((Long) edge.get("target")).intValue();
                double cost = (double) edge.get("cost");

                graph[source].getNeighbours().put(target, cost);
                graph[target].getNeighbours().put(source, cost);
            }

            return graph;
        }

    }
}
