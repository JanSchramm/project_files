import java.util.*;
import javax.swing.*;

@SuppressWarnings("unused")
public class Hauptprogramm extends NodeEditor {

    // Graph graph;
    // Knoten[] knoten;
    // int[][] matrix;
    // int anzahlKnoten;

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
}