
@SuppressWarnings("unused")
public class Hauptprogramm extends NodeEditor {

    Hauptprogramm() {
        super();
    }

    protected Graph loadGraph() {
        // Beispiel-Graph laden
        return Beispiele.gibGraph(1);
    }

}