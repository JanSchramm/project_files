public class Beispiele {
    public static Graph gibGraph(int nr) {
        switch (nr) {
            case 1:
                return gibGraph1();
            case 2:
                return gibGraph2();
            case 3:
                return gibGraph3();
            // case 4 : return gibGraph4();
            // case 5 : return gibGraph5();
            // case 6 : return gibGraph6();
            // case 7 : return gibGraph7();
            // case 8 : return gibGraph8();
            // case 9 : return gibGraph9();
            // case 10 : return gibGraph10();

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

        g.matrix = new int[][] {
                { 0, 0, 3, 0, 0 },
                { 0, 0, 5, 0, 2 },
                { 3, 5, 0, 1, 0 },
                { 0, 0, 1, 0, 4 },
                { 0, 2, 0, 4, 0 }
        };

        System.out.println("");
        for (int i = 0; i < namen.length; i++) {
            g.knoten[i] = new Knoten(namen[i]);
            g.knoten[i].setX(xPos[i]);
            g.knoten[i].setY(yPos[i]);
            // Kantenliste füllen, XYPos zuweisen
            for (int j = 0; j < namen.length; j++) {
                if (g.matrix[i][j] != 0) {
                    System.out.println(i+" "+j+" "+g.matrix[i][j]);
                    g.kanten[i][j] = new Kanten(i,j);
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
        g.knoten = gibKnotenliste("A", "B", "C", "D", "E");
        g.matrix = new int[][] {
                { 0, 25, 0, 1, 2 },
                { 6, 0, 7, 4, 0 },
                { 0, 1, 0, 19, 0 },
                { 1, 3, 1, 0, 0 },
                { 5, 0, 0, 0, 0 }
        };
        return g;
    }

    public static Graph gibGraph3() {
        Graph g = new Graph(5);
        g.knoten = gibKnotenliste("A", "B", "C", "D", "E");
        g.matrix = new int[][] {
                { 0, 1, 1, 0, 0 },
                { 1, 0, 1, 0, 0 },
                { 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 1 },
                { 0, 0, 0, 1, 0 }
        };
        return g;
    }
}
