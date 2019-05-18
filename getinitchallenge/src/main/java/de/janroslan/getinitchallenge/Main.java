package de.janroslan.getinitchallenge;

import java.io.IOException;
import org.json.simple.parser.ParseException;


/**
 * @author Jan-Philipp Roslan
 */
public class Main {
    
    
    public static void main(String[] args) {
        
        
        JsonGraphParser parser = new JsonGraphParser("F:\\Dev\\GetInItChallenge\\Java\\generatedGraph.json");
        
        try {
            Node[] graph = parser.parse();
            
            // Index der Erde: 18
            // Index von b3-r7-r4nd7 : 245
            DijkstraSolver solver = new DijkstraSolver(18, 246, graph);
            
            DijkstraResult result = solver.solve();
            
            System.out.println(result);
            
        } catch (IOException ex) {
            System.out.println("Fehler beim einlesen der Datei - ");
        } catch (ParseException ex) {
            System.out.println("Fehler beim parsen der JSON-Datei - Formatierung nicht korrekt");
        }

    }
        
    }

