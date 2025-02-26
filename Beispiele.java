import java.util.Random;

public class Beispiele {
    public static Graph gibGraph(int nr) {
        switch (nr) {
            case 1:
                return gibGraph1();
            case 2:
                return gibGraph2();
            case 3:
                return gibGraph3();
            case 4:
                return gibGraph4();
            case 5:
                return gibGraph5();
            case 6:
                return gibZufaelligenGraph();
            default:
                System.out.println("Dieses Beispiel gibt es nicht!");
                System.exit(-1);
        }
        return null; // Rückgabe
    }

    public static Knoten[] gibKnotenliste(String... name) {
        Knoten[] liste = new Knoten[name.length];
        for (int i = 0; i < name.length; i++)
            liste[i] = new Knoten(name[i]);
        return liste;
    }

    public static Graph gibGraph1() {
        Graph g = new Graph(5);
        g.knoten = new Knoten[5];
        g.kanten = new Kanten[5][5];

        String[] namen = { "A", "B", "C", "D", "E" };
        int[] xPos = { 100, 200, 300, 400, 500 };
        int[] yPos = { 150, 300, 200, 350, 400 };

        // Matrix für die Kanten initialisieren
        g.matrix = new int[][] {
                { 0, 8, 3, 0, 0 },
                { 9, 0, 5, 0, 2 },
                { 3, 5, 0, 1, 0 },
                { 0, 0, 1, 0, 4 },
                { 0, 2, 0, 4, 0 }
        };

        for (int i = 0; i < namen.length; i++) {
            // Knotenliste füllen, XYPos zuweisen
            g.knoten[i] = new Knoten(namen[i]);
            g.knoten[i].setX(xPos[i]);
            g.knoten[i].setY(yPos[i]);

            // Kantenliste füllen, XYPos zuweisen
            for (int j = 0; j < namen.length; j++) {
                if (g.matrix[i][j] != 0) {
                    g.kanten[i][j] = new Kanten(i, j);
                    g.kanten[i][j].setX1(xPos[i]);
                    g.kanten[i][j].setY1(yPos[i]);
                    g.kanten[i][j].setX2(xPos[j]);
                    g.kanten[i][j].setY2(yPos[j]);
                    g.kanten[i][j].setGewicht(g.matrix[i][j]);
                }
            }

        }

        return g;
    }

    public static Graph gibGraph2() {
        Graph g = new Graph(5);
        g.knoten = new Knoten[5];
        g.kanten = new Kanten[5][5];

        String[] namen = { "A", "B", "C", "D", "E" };
        int[] xPos = { 200, 200, 400, 400, 300 };
        int[] yPos = { 150, 300, 300, 150, 100 };

        g.matrix = new int[][] {
                { 0, 25, 0, 1, 2 },
                { 6, 0, 7, 4, 0 },
                { 0, 1, 0, 19, 0 },
                { 1, 3, 1, 0, 0 },
                { 5, 0, 0, 0, 0 }
        };

        // Befüllt die Knoten- und Kantenliste
        for (int i = 0; i < namen.length; i++) {
            // Knotenliste füllen, XYPos zuweisen
            g.knoten[i] = new Knoten(namen[i]);
            g.knoten[i].setX(xPos[i]);
            g.knoten[i].setY(yPos[i]);

            // Kantenliste füllen, XYPos zuweisen
            for (int j = 0; j < namen.length; j++) {
                if (g.matrix[i][j] != 0) {
                    g.kanten[i][j] = new Kanten(i, j);
                    g.kanten[i][j].setX1(xPos[i]);
                    g.kanten[i][j].setY1(yPos[i]);
                    g.kanten[i][j].setX2(xPos[j]);
                    g.kanten[i][j].setY2(yPos[j]);
                    g.kanten[i][j].setGewicht(g.matrix[i][j]);
                }
            }

        }

        return g;
    }

    public static Graph gibGraph3() {
        Graph g = new Graph(8);
        g.knoten = new Knoten[8];
        g.kanten = new Kanten[8][8];

        String[] namen = { "A", "B", "C", "D", "E", "F", "G", "H" };
        int[] xPos = { 100, 200, 300, 400, 500, 400, 200, 300 };
        int[] yPos = { 150, 200, 150, 250, 400, 150, 350, 400 };

        g.matrix = new int[][] {
                { 0, 6, 1, 0, 0, 0, 0, 0 },
                { 6, 0, 2, 9, 0, 0, 4, 8 },
                { 1, 2, 0, 0, 0, 4, 0, 0 },
                { 0, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 7, 0, 0, 0, 0 },
                { 0, 0, 4, 5, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 3, 0, 0, 4 },
                { 0, 0, 0, 5, 0, 0, 0, 0 }
        };

        // Befüllt die Knoten- und Kantenliste
        for (int i = 0; i < namen.length; i++) {
            // Knotenliste füllen, XYPos zuweisen
            g.knoten[i] = new Knoten(namen[i]);
            g.knoten[i].setX(xPos[i]);
            g.knoten[i].setY(yPos[i]);

            // Kantenliste füllen, XYPos zuweisen
            for (int j = 0; j < namen.length; j++) {
                if (g.matrix[i][j] != 0) {
                    g.kanten[i][j] = new Kanten(i, j);
                    g.kanten[i][j].setX1(xPos[i]);
                    g.kanten[i][j].setY1(yPos[i]);
                    g.kanten[i][j].setX2(xPos[j]);
                    g.kanten[i][j].setY2(yPos[j]);
                    g.kanten[i][j].setGewicht(g.matrix[i][j]);
                }
            }

        }

        return g;
    }

    public static Graph gibGraph4() {
        Graph g = new Graph(10); // 10 Knoten für einen komplexeren Graphen
        g.knoten = new Knoten[10];
        g.kanten = new Kanten[10][10];

        String[] namen = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
        int[] xPos = { 100, 200, 300, 400, 500, 600, 350, 500, 300, 200 };
        int[] yPos = { 100, 200, 150, 200, 250, 200, 300, 400, 350, 300 };

        g.matrix = new int[][] {
                { 0, 10, 20, 0, 0, 0, 0, 0, 0, 0 },
                { 10, 0, 5, 15, 0, 0, 0, 0, 0, 0 },
                { 20, 5, 0, 30, 0, 10, 0, 0, 0, 0 },
                { 0, 15, 30, 0, 10, 0, 10, 0, 0, 0 },
                { 0, 0, 0, 10, 0, 5, 0, 15, 0, 0 },
                { 0, 0, 10, 0, 5, 0, 0, 0, 0, 10 },
                { 0, 0, 0, 10, 0, 0, 0, 20, 10, 0 },
                { 0, 0, 0, 0, 15, 0, 20, 0, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 10, 5, 0, 25 },
                { 0, 0, 0, 0, 0, 10, 0, 0, 25, 0 }
        };

        for (int i = 0; i < namen.length; i++) {
            // Knotenliste füllen, XYPos zuweisen
            g.knoten[i] = new Knoten(namen[i]);
            g.knoten[i].setX(xPos[i]);
            g.knoten[i].setY(yPos[i]);

            // Kantenliste füllen, XYPos zuweisen
            for (int j = 0; j < namen.length; j++) {
                if (g.matrix[i][j] != 0) {
                    g.kanten[i][j] = new Kanten(i, j);
                    g.kanten[i][j].setX1(xPos[i]);
                    g.kanten[i][j].setY1(yPos[i]);
                    g.kanten[i][j].setX2(xPos[j]);
                    g.kanten[i][j].setY2(yPos[j]);
                    g.kanten[i][j].setGewicht(g.matrix[i][j]);
                }
            }
        }

        return g;
    }

    public static Graph gibGraph5() {
        Graph g = new Graph(12); // 12 Knoten für noch mehr Komplexität
        g.knoten = new Knoten[12];
        g.kanten = new Kanten[12][12];

        String[] namen = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
        int[] xPos = { 50, 150, 250, 350, 450, 550, 650, 650, 450, 350, 500, 650 };
        int[] yPos = { 100, 200, 100, 200, 300, 400, 300, 200, 100, 50, 50, 100 };

        g.matrix = new int[][] {
                { 0, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 10, 0, 15, 5, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 5, 15, 0, 10, 0, 0, 10, 0, 0, 0, 0, 0 },
                { 0, 5, 10, 0, 20, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 20, 0, 5, 15, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 5, 0, 10, 0, 0, 0, 0, 0 },
                { 0, 0, 10, 0, 15, 10, 0, 20, 5, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 20, 0, 10, 0, 0, 25 },
                { 0, 0, 0, 0, 0, 0, 5, 10, 0, 15, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 25, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 25, 0, 25, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        for (int i = 0; i < namen.length; i++) {
            // Knotenliste füllen, XYPos zuweisen
            g.knoten[i] = new Knoten(namen[i]);
            g.knoten[i].setX(xPos[i]);
            g.knoten[i].setY(yPos[i]);

            // Kantenliste füllen, XYPos zuweisen
            for (int j = 0; j < namen.length; j++) {
                if (g.matrix[i][j] != 0) {
                    g.kanten[i][j] = new Kanten(i, j);
                    g.kanten[i][j].setX1(xPos[i]);
                    g.kanten[i][j].setY1(yPos[i]);
                    g.kanten[i][j].setX2(xPos[j]);
                    g.kanten[i][j].setY2(yPos[j]);
                    g.kanten[i][j].setGewicht(g.matrix[i][j]);
                }
            }
        }

        return g;
    }

    public static Graph gibZufaelligenGraph() {
        Random rand = new Random();
        int knotenAnzahl = 6 + rand.nextInt(5); // Zufällige Anzahl von Knoten zwischen 6 und 11
        Graph g = new Graph(knotenAnzahl);
        g.knoten = new Knoten[knotenAnzahl];
        g.kanten = new Kanten[knotenAnzahl][knotenAnzahl];

        String[] namen = new String[knotenAnzahl];
        int[] xPos = new int[knotenAnzahl];
        int[] yPos = new int[knotenAnzahl];

        // Zufällige Knotennamen (A, B, C, ...)
        for (int i = 0; i < knotenAnzahl; i++) {
            namen[i] = Character.toString((char) ('A' + i));
            xPos[i] = rand.nextInt(601) + 50; // x-Werte zwischen 0 und 650
            yPos[i] = rand.nextInt(351) + 50; // y-Werte zwischen 0 und 400
        }

        // Adjazenzmatrix mit zufälligen Verbindungen und Gewichten
        g.matrix = new int[knotenAnzahl][knotenAnzahl];

        for (int i = 0; i < knotenAnzahl; i++) {
            for (int j = i + 1; j < knotenAnzahl; j++) {
                if (rand.nextDouble() < 0.5) { // 50% Wahrscheinlichkeit für eine Verbindung
                    int gewicht = 1 + rand.nextInt(20); // Zufälliges Gewicht zwischen 1 und 20
                    g.matrix[i][j] = gewicht;
                    g.matrix[j][i] = gewicht; // Ungerichteter Graph
                }
            }
        }

        // Knoten und Kanten initialisieren
        for (int i = 0; i < knotenAnzahl; i++) {
            g.knoten[i] = new Knoten(namen[i]);
            g.knoten[i].setX(xPos[i]);
            g.knoten[i].setY(yPos[i]);

            for (int j = 0; j < knotenAnzahl; j++) {
                if (g.matrix[i][j] != 0) {
                    g.kanten[i][j] = new Kanten(i, j);
                    g.kanten[i][j].setX1(xPos[i]);
                    g.kanten[i][j].setY1(yPos[i]);
                    g.kanten[i][j].setX2(xPos[j]);
                    g.kanten[i][j].setY2(yPos[j]);
                    g.kanten[i][j].setGewicht(g.matrix[i][j]);
                }
            }
        }

        return g;
    }

}
