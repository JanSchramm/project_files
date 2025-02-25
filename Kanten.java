@SuppressWarnings("unused")
public class Kanten {

    private int von, nach;
    private int gewicht;
    private int x1, y1, x2, y2;

    public Kanten(int von, int nach) {
        this.von = von;
        this.nach = nach;
        this.x1 = -1;
        this.y1 = -1;
        this.x2 = -1;
        this.y2 = -1;
    }

    public int getVon() {
        return von;
    }

    public void setVon(int von) {
        this.von = von;
    }

    public int getNach() {
        return nach;
    }

    public void setNach(int nach) {
        this.nach = nach;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public void setX1(int x) {
        this.x1 = x;
    }

    public void setY1(int y) {
        this.y1 = y;
    }

    public void setX2(int x) {
        this.x2 = x;
    }

    public int getX2() {
        return x2;
    }

    public void setY2(int y) {
        this.y2 = y;
    }

    public int getY2() {
        return y2;
    }

    public String getGewichtString() {
        return Integer.toString(gewicht);
    }

    public int getGewichtInteger() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }
}
