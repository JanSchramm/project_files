import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import javax.swing.*;
import java.util.Collections;

public class NodeEditor extends JFrame {

    private ArrayList<Knoten> selectedPath = new ArrayList<>();
    private JButton astarButton;
    private JLabel deinWeg;

    private String weg = new String();

    Beispiele beispiele = new Beispiele();
    Graph graph;

    public NodeEditor() {
        setTitle("Graph-Knoten Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Beispiel-Graph laden
        this.graph = Beispiele.gibGraph(1);

        // Panel für Button oben in der Mitte
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        astarButton = new JButton("Dijkstra Algorithmus starten!");
        topPanel.add(astarButton);

        // Panel für Dropdown (Graph Auswahl)
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Panel links ausrichten
        String[] antwortMöglichkeiten = { "Graph 1", "Graph 2", "Graph 3", "Graph 4", "Graph 5" };
        final JComboBox<String> graphComboSelect = new JComboBox<>(antwortMöglichkeiten);
        topLeftPanel.add(graphComboSelect);

        // Kombinieren der beiden Panels oben
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(topPanel, BorderLayout.CENTER);
        headerPanel.add(topLeftPanel, BorderLayout.WEST);

        // Zeigt deinen Weg an
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        deinWeg = new JLabel("Dein Weg: ");
        footerPanel.add(deinWeg, BorderLayout.WEST);

        // Füge den oberen Bereich zum Hauptfenster hinzu
        add(headerPanel, BorderLayout.NORTH);
        add(footerPanel, BorderLayout.SOUTH);

        // Panel für Knoten und Kanten
        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        // Graph Auswahl Event Listener
        graphComboSelect.addActionListener(e -> {
            int selectedGraph = graphComboSelect.getSelectedIndex() + 1; // Graph 1, 2 oder 3
            this.graph = Beispiele.gibGraph(selectedGraph); // Setze den ausgewählten Graphen
            selectedPath.clear(); // Leere den Pfad, wenn der Graph gewechselt wird
            drawingPanel.repaint(); // Panel neu zeichnen
        });

        // A* Algorithmus-Button hinzufügen
        astarButton.addActionListener(e -> {
            int totalLength = calculatePathLength();
            System.out.println("Gesamtlänge des Pfades: " + totalLength);
            starteDijkstra(null, null); // Test der Dijkstra-Methode

            // statt null den start und ziel Knoten angeben
            // >> 1. bzw. last element von selectedPath (siehe mouselistener)
        });
    }

    private int calculatePathLength() {
        int totalLength = 0;

        // Berechne den Pfad entlang der Kanten und addiere die Längen der Kanten
        for (int i = 0; i < selectedPath.size() - 1; i++) {
            Knoten current = selectedPath.get(i);
            Knoten next = selectedPath.get(i + 1);

            // Finde die Indexpositionen der Knoten im Graphen
            int indexCurrent = -1;
            int indexNext = -1;

            for (int k = 0; k < graph.knoten.length; k++) {
                if (graph.knoten[k] == current) {
                    indexCurrent = k;
                }
                if (graph.knoten[k] == next) {
                    indexNext = k;
                }
            }

            if (indexCurrent == -1 || indexNext == -1) {
                System.out.println("Fehler: Knoten nicht im Graphen gefunden!");
                return -1; // Fehlercode zurückgeben
            }

            // Prüfe, ob eine Kante zwischen den beiden Knoten existiert
            Kanten edge = graph.kanten[indexCurrent][indexNext];

            if (edge != null) {
                try {
                    totalLength += edge.getGewichtInteger(); // Direkt int verwenden
                } catch (NumberFormatException e) {
                    System.out.println("Fehler beim Parsen der Kantenlänge.");
                }
            } else {
                System.out.println("Keine Verbindung zwischen " + current.getName() + " und " + next.getName());
            }
        }
        return totalLength;
    }

    private class DrawingPanel extends JPanel {
        public DrawingPanel() {

            // Reagiert auf Mausklicks und entfernt gewählte Knoten aus der Auswahl / fügt
            // sie der Auswahl hinzu
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        for (int i = 0; i < graph.knoten.length; i++) {
                            if (graph.knoten[i] != null) {

                                // Überprüfung des Inputs auf Ort
                                // ToDo: Kanten edge = graph.kanten[indexCurrent][indexNext]; --> Nur
                                // verbundene Knoten sollen anklickbar sein
                                //  ToDo: Färbung des Weges (Über calculatePathLength)
                                if (new Rectangle(graph.knoten[i].getX() - 15, graph.knoten[i].getY() - 15, 30, 30)
                                        .contains(e.getPoint())) {
                                    if (!selectedPath.contains(graph.knoten[i])) {
                                        selectedPath.add(graph.knoten[i]);
                                        repaint();

                                        // Aktualisiert das Label deinWeg
                                        weg += graph.knoten[i].getName();
                                        deinWeg.setText("Dein Weg: " + weg + ", Weglänge: " + calculatePathLength());
                                        weg += " ";
                                    }
                                    break;
                                }
                            }
                        }
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        if (!selectedPath.isEmpty()) {
                            Knoten node = selectedPath.get(selectedPath.size() - 1);

                            if (new Rectangle(node.getX() - 15, node.getY() - 15, 30, 30).contains(e.getPoint())) {
                                selectedPath.remove(node);
                                repaint();

                                // Aktualisiert das Label deinWeg
                                weg = "";
                                for (int i = 0; i < selectedPath.size(); i++) {
                                    // *
                                    weg += selectedPath.get(i).getName();
                                }
                                deinWeg.setText("Dein Weg: " + weg + ", Weglänge: " + calculatePathLength());
                                weg += " ";

                            }
                        }
                    }
                }
            });
        }

        // Zeichnet Knoten und Kanten des Graphen
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            int versatz = 10; // Versatz zur Trennung der Kanten-Beschriftungen

            // Zeichne die Kanten
            for (int i = 0; i < graph.kanten.length; i++) {
                for (int j = 0; j < graph.kanten[i].length; j++) {
                    if (graph.kanten[i][j] != null) { // Überprüfung auf null

                        int x1 = graph.kanten[i][j].getX1();
                        int y1 = graph.kanten[i][j].getY1();
                        int x2 = graph.kanten[i][j].getX2();
                        int y2 = graph.kanten[i][j].getY2();

                        // Color color = graphen.kanten[i][j].getColor(); implementieren *

                        // Falls die Kante AB und BA verschiedene Gewichte haben
                        if (graph.matrix[i][j] != graph.matrix[j][i]) {
                            if (i < j) {
                                // Kante AB
                                // * hier farbe anwenden: g.setColor(Color.color);
                                g.drawLine(x1 - versatz, y1 - versatz, x2 - versatz, y2 - versatz);
                                // Beschriftung der Kante AB
                                g.drawString(graph.kanten[i][j].getGewichtString() + " " + graph.knoten[i].getName()
                                        + graph.knoten[j].getName(),
                                        (x1 + x2) / 2 - versatz, (y1 + y2) / 2 - versatz);

                            } else {
                                // Kante BA
                                g.drawLine(x1 + versatz, y1 + versatz, x2 + versatz, y2 + versatz);
                                // Beschriftung der Kante BA
                                g.drawString(graph.kanten[i][j].getGewichtString() + " " + graph.knoten[i].getName()
                                        + graph.knoten[j].getName(),
                                        (x1 + x2) / 2 + versatz, (y1 + y2) / 2 + versatz);

                            }
                        } else { // Falls die Kante ungerichtet ist (gleiche Gewichte)
                            g.drawLine(x1, y1, x2, y2);
                            g.drawString(
                                    graph.kanten[i][j].getGewichtString(),
                                    (x1 + x2) / 2, (y1 + y2) / 2);
                        }
                    }
                }
            }

            // Zeichne die ausgewählten Knoten
            for (int i = 0; i < graph.knoten.length; i++) {
                if (selectedPath.contains(graph.knoten[i])) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillOval(graph.knoten[i].getX() - 15, graph.knoten[i].getY() - 15, 30, 30);
                g.setColor(Color.WHITE);
                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(graph.knoten[i].getName());
                g.drawString(graph.knoten[i].getName(), graph.knoten[i].getX() - textWidth / 2,
                        graph.knoten[i].getY() + fm.getAscent() / 2);
                g.setColor(Color.BLACK);
            }
        }

    }

    /**
     * Erzeugt eine Liste von Knoten aus den gegebenen Namen und Koordinaten.
     */
    public static Knoten[] gibKnotenliste(String[] namen, int[] x, int[] y) {

        if (namen.length != x.length || namen.length != y.length) {
            throw new IllegalArgumentException("Ungleiche Anzahl an Namen und Koordinaten!");
        }

        Knoten[] liste = new Knoten[namen.length];
        for (int i = 0; i < namen.length; i++) {
            liste[i] = new Knoten(namen[i]);
            liste[i].setX(x[i]);
            liste[i].setY(y[i]);
        }

        return liste;
    }

    public void starteDijkstra(Knoten start, Knoten ziel) {
        // Zum Test wird der Start- und Zielknoten fest gewählt
        start = graph.knoten[0]; // Knoten A
        ziel = graph.knoten[4]; // Knoten E

        // Eine PriorityQueue, die Knoten nach ihrer Entfernung sortiert
        PriorityQueue<Knoten> pq = new PriorityQueue<>(Comparator.comparingInt(Knoten::getEntfernung));

        start.setEntfernung(0); // Startknoten hat Entfernung 0 von sich selbst
        pq.add(start); // Startknoten der PriorityQueue hinzufügen

        System.out.print(pq.toString());

        while (!pq.isEmpty()) { // solange die Warteschlange nicht leer ist
            Knoten aktuell = pq.poll(); // Entnehme den Knoten mit geringster Entfernung
            System.out.println("Aktueller Knoten: " + aktuell.getName());
            System.out.println("Entfernung: " + aktuell.getEntfernung());

            if (aktuell == ziel) {
                System.out.println("Ziel erreicht!");
                // break; // while-Schleife verlassen, wenn Zielknoten erreicht
            }
            // Alle verbundenen Knoten durchgehen
            for (int i = 0; i < graph.knoten.length; i++) {
                if (graph.matrix[graph.getIndex(aktuell)][i] != 0) {
                    Knoten nachbar = graph.knoten[i];
                    int neueEntfernung = aktuell.getEntfernung() + graph.matrix[graph.getIndex(aktuell)][i];
                    if (nachbar.getEntfernung() > neueEntfernung) {
                        nachbar.setEntfernung(neueEntfernung);
                        nachbar.setVorgaenger(aktuell); // Vorherigen Knoten merken
                        System.out.println("Nachbar: " + nachbar.getName());
                    }

                    // Nachbar in die Warteschlange einfügen, falls noch nicht besucht
                    if (nachbar.getBearbeitungsstatus() == 0) {
                        pq.add(nachbar);
                        nachbar.setBearbeitungsstatus(1); // Markiere als besucht
                    }
                }
            }
            graph.knotenAusgeben(); // Zwischenstand ausgeben
            // aktuellen Knoten als bearbeitet markieren
            aktuell.setBearbeitungsstatus(2);
        } // Ende der while-Schleife
        graph.knotenAusgeben(); // Endstand ausgeben
        druckeKuerzestenWeg(start, ziel);
    }

    private void druckeKuerzestenWeg(Knoten start, Knoten ziel) {
        ArrayList<Knoten> weg = new ArrayList<>();
        Knoten aktuell = ziel;

        while (aktuell != null) {
            weg.add(aktuell);
            aktuell = aktuell.getVorgaenger(); // Rückverfolgung
        }

        Collections.reverse(weg); // Da der Weg rückwärts gesammelt wurde, drehen wir ihn um

        System.out.println("Kürzester Weg von " + start.getName() + " nach " +
                ziel.getName() + ":");
        for (int i = 0; i < weg.size(); i++) {
            System.out.print(weg.get(i).getName());
            if (i < weg.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("\nGesamte Entfernung: " + ziel.getEntfernung());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NodeEditor editor = new NodeEditor();
            editor.setVisible(true); // Fenster sichtbar machen
        });
    }

}
