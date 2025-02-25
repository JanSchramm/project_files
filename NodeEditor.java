import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NodeEditor extends JFrame {
    private ArrayList<Knoten> nodes = new ArrayList<>();

    Knoten[] knoten;
    Beispiele beispiele = new Beispiele();
    Graph graph;
    int[][] matrixKanten;

    public NodeEditor() {
        setTitle("Graph-Knoten Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Beispiel-Graph laden
        Graph graph = Beispiele.gibGraph(1);
        if (graph == null || graph.knoten == null) {
            System.out.println("Fehler: Graph ist null oder enthält keine Knoten!");
        } else {
            setNodes(graph.knoten);
        }

        add(new DrawingPanel());
    }

    // Methode zum Initialisieren der Knoten
    public void setNodes(Knoten[] knoten) {
        for (Knoten k : knoten) {
            nodes.add(k);
        }
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            for (Knoten node : nodes) {
                int x = node.getX();
                int y = node.getY();

                // Knoten zeichnen
                g.fillOval(x - 15, y - 15, 30, 30);

                // Name in die Mitte setzen
                g.setColor(Color.WHITE);
                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(node.getName());
                g.drawString(node.getName(), x - textWidth / 2, y + fm.getAscent() / 2);
                g.setColor(Color.BLACK);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NodeEditor().setVisible(true));
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
