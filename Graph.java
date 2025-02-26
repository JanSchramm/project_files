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

    public int getIndex(Knoten gesuchterKnoten) {
        for (int i = 0; i < knoten.length; i++) {
            if (knoten[i].equals(gesuchterKnoten)) { 
                return i;
            }
        }
        return -1;
    }

    public void knotenAusgeben() {
        System.out.println("Knoten:");
        for (int i = 0; i < knoten.length; i++) {
            System.out.print(knoten[i].getName()+" "+ 
            knoten[i].getEntfernung()+" ");
            if(knoten[i].getVorgaenger()!=null){
                System.out.println(knoten[i].getVorgaenger().getName());
            }else{
                System.out.println("null");
            }

        }
    }
}