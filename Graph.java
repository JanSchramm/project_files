import java.util.ArrayList;

class Graph {
    Knoten[] knoten;
    Kanten[][] kanten;
    int[][] matrix;

    public Graph(int size) {
        knoten = new Knoten[size];
    }

    public ArrayList<Kanten> getKantenVon(Knoten gesuchterKnoten) {
        // Index des Knotens k bestimmen
        int index=-1;
        for (int i = 0; i < knoten.length; i++) {
            if (knoten[i].equals(gesuchterKnoten)) { 
                index=i;
                break;
            }
        }        
        ArrayList<Kanten> kantenArray= new ArrayList<Kanten>();
        for (int i = 0; i < this.kanten.length; i++) {
            if (this.kanten[index][i] !=null) {
                kantenArray.add(this.kanten[index][i]);
            }
        }
        return kantenArray;
    }
}