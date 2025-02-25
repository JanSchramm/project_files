import java.util.*;
import javax.swing.*;

@SuppressWarnings("unused")
public class Hauptprogramm extends NodeEditor {

    // Graph graph;
    // Knoten[] knoten;
    // int[][] matrix;
    // int anzahlKnoten;

    private HashMap<Knoten, Integer> heuristic;

    Hauptprogramm() {
        super();
    }

    protected Graph loadGraph() {
        // Beispiel-Graph laden
        return Beispiele.gibGraph(1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NodeEditor editor = new NodeEditor();
            editor.setVisible(true); // Fenster sichtbar machen
        });
    }

    // private void aStar(Knoten startKnoten, Knoten endKnoten) {
    // PriorityQueue<Knoten> openSet = new
    // PriorityQueue<>(Comparator.comparingInt(null));
    // HashMap<Knoten, Integer> gScore = new HashMap<>();
    // HashMap<Knoten, Knoten> cameFrom = new HashMap<>();

    // gScore.put(startKnoten, 0);
    // openSet.add(startKnoten);

    // while (!openSet.isEmpty()) {
    // Knoten current = openSet.poll();
    // if (current.equals(endKnoten)) {
    // return rekonstruiereWeg(startKnoten, endKnoten);
    // }

    // for (Map.Entry<Knoten, Integer> neighborEntry : current.neighbors.entrySet())
    // {
    // Knoten neightbor = neighborEntry.getKey();
    // int tentativeGScore = gScore.getOrDefault(current, Integer.MAX_VALUE) +
    // neighborEntry.getValue();

    // if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
    // cameFrom.put(neighbor, current);
    // gScore.put(neighbor, tentativeGScore);
    // if (!openSet.contains(neighbor)) {
    // openSet.add(neighbor);
    // }
    // }
    // }
    // }
    // }

    private void rekonstruiereWeg(Knoten startKnoten, Knoten endKnoten) {

    }
}