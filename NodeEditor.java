import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class NodeEditor extends JFrame {

    private ArrayList<Knoten> selectedPath = new ArrayList<>();
    private JButton astarButton;

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
        astarButton = new JButton("A* Algorithmus starten");
        topPanel.add(astarButton);
        add(topPanel, BorderLayout.NORTH);

        // Panel für Knoten und Kanten
        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        // A* Algorithmus-Button hinzufügen
        astarButton.addActionListener(e -> {
            int totalLength = calculatePathLength();
            System.out.println("Gesamtlänge des Pfades: " + totalLength);
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
                                if (new Rectangle(graph.knoten[i].getX() - 15, graph.knoten[i].getY() - 15, 30, 30)
                                        .contains(e.getPoint())) {
                                    if (!selectedPath.contains(graph.knoten[i])) {
                                        selectedPath.add(graph.knoten[i]);
                                        repaint();
                                    }
                                    break;
                                }
                            }
                        }
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        for (Knoten node : selectedPath) {
                            if (new Rectangle(node.getX() - 15, node.getY() - 15, 30, 30).contains(e.getPoint())) {
                                selectedPath.remove(node);
                                repaint();
                                break;
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

                        // Falls die Kante AB und BA verschiedene Gewichte haben
                        if (graph.matrix[i][j] != graph.matrix[j][i]) {
                            if (i < j) {
                                // Kante AB
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

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NodeEditor editor = new NodeEditor();
            editor.setVisible(true); // Fenster sichtbar machen
        });
    }

}
