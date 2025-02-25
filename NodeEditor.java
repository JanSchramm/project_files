import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class NodeEditor extends JFrame {
    // private ArrayList<Knoten> nodes = new ArrayList<>();
    // private ArrayList<Kanten> edges = new ArrayList<>();
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
        graph = Beispiele.gibGraph(1);
        // if (graph == null || graph.knoten == null || graph.kanten == null) {
        // System.out.println("Fehler: Graph ist null oder enthält keine Knoten oder
        // enthält keine Kanten!");
        // } else {
        // setNodes(graph.knoten);
        // setEdges(graph.kanten);
        // }

        // Panel für Button oben in der Mitte
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        astarButton = new JButton("A* Algorithmus Ausführen");
        topPanel.add(astarButton);
        add(topPanel, BorderLayout.NORTH);

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        astarButton.addActionListener(e -> {
            int totalLength = calculatePathLength();
            System.out.println("Gesamtlänge des Pfades: " + totalLength);
        });
    }

    // // Methode zum Initialisieren der Knoten
    // public void setNodes(Knoten[] knoten) {
    // for (Knoten k : knoten) {
    // nodes.add(k);
    // }
    // }

    // // Methode zum Initialisieren der Kanten
    // public void setEdges(Kanten[] kanten) {
    // for (Kanten k : kanten) {
    // edges.add(k);
    // }
    // }

    private int calculatePathLength() {
        int totalLength = 0;

        for (int i = 0; i < selectedPath.size() - 1; i++) {
            Knoten current = selectedPath.get(i);
            Knoten next = selectedPath.get(i + 1);

            // Durchlaufe die Kanten und vergleiche die Knotenbezeichner
            for (int j = 0; j < graph.kanten.length; j++) {
                boolean isEdgeConnected = (graph.kanten[i][j].getX1() == current.getX()
                        && graph.kanten[i][j].getY1() == current.getY() &&
                        graph.kanten[i][j].getX2() == next.getX() && graph.kanten[i][j].getY2() == next.getY()) ||
                        (graph.kanten[i][j].getX2() == current.getX() && graph.kanten[i][j].getY2() == current.getY() &&
                                graph.kanten[i][j].getX1() == next.getX() && graph.kanten[i][j].getY1() == next.getY());

                // Wenn die Kante zwischen den Knoten existiert, dann die Länge hinzufügen
                if (isEdgeConnected) {
                    try {
                        totalLength += Integer.parseInt(graph.kanten[i][j].getGewichtString()); // Annahme, dass der Name die
                                                                                          // Länge ist
                    } catch (NumberFormatException e) {
                        System.out.println("Fehler beim Parsen des Kantenbezeichners als Länge.");
                    }
                    break;
                } else {
                    System.out.println("Die Kante ist nicht verbunden");
                }
            }
        }
        return totalLength;
    }

    private class DrawingPanel extends JPanel {
        public DrawingPanel() {

            addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        for (int i = 0; i < graph.knoten.length; i++) {
                            if (new Rectangle(graph.knoten[i].getX() - 15, graph.knoten[i].getY() - 15, 30, 30)
                                    .contains(e.getPoint())) {
                                if (!selectedPath.contains(graph.knoten[i])) {
                                    selectedPath.add(graph.knoten[i]);
                                    repaint();
                                }
                                break;
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

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            for (int i = 0; i < graph.kanten.length; i++) {
                for (int j = 0; j < graph.kanten.length; j++) {
                    g.drawLine(graph.kanten[i][j].getX1(), graph.kanten[i][j].getY1(), graph.kanten[i][j].getX2(), graph.kanten[i][j].getY2());
                    g.drawString(graph.kanten[i][j].getGewichtString(), (graph.kanten[i][j].getX2() + graph.kanten[i][j].getX1()) / 2,
                            (graph.kanten[i][j].getY2() + graph.kanten[i][j].getY1()) / 2);
                }
            }

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

    void neu(){
        
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

}
