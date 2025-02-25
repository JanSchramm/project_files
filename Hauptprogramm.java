import java.util.*;
import javax.swing.*;

@SuppressWarnings("unused")
public class Hauptprogramm extends NodeEditor {

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