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
        return null;
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

        String[] namen = { "A", "B", "C", "D", "E" };
        int[] xPos = { 100, 200, 300, 400, 500 };
        int[] yPos = { 150, 250, 200, 350, 400 };

        for (int i = 0; i < namen.length; i++) {
            g.knoten[i] = new Knoten(namen[i]);
            g.knoten[i].setX(xPos[i]);
            g.knoten[i].setY(yPos[i]);
        }

        return g;
    }

    public static Graph gibGraph2() {
        Graph g = new Graph(5);
        g.knoten = gibKnotenliste("A", "B", "C", "D", "E");
        g.matrix = new int[][] {
                { 0, 1, 0, 1, 1 },
                { 1, 0, 1, 1, 0 },
                { 0, 1, 0, 1, 0 },
                { 1, 1, 1, 0, 0 },
                { 1, 0, 0, 0, 0 }
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
