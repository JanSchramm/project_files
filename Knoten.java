class Knoten {
   private String name;
   private int x, y;  // Position im Zeichenfenster
   private int entfernung; // Entfernung zum Startknoten
   private Knoten vorgaenger; // Vorgaengerknoten auf dem Weg zum Startknoten
   int bearbeitungsstatus; // 0 = unbesucht, 1 = in Bearbeitung, 2 = fertig

   public Knoten(String name) {
      this.name = name;
      this.x = -1;
      this.y = -1;
      vorgaenger = null;
      entfernung = Integer.MAX_VALUE;
      bearbeitungsstatus = 0;
   }

   public String getName() {
      return name;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public void setX(int x) {
      this.x = x;
   }

   public void setY(int y) {
      this.y = y;
   }

   public int getEntfernung() {
      return entfernung;
   }  

   public void setEntfernung(int entfernung) {
      this.entfernung = entfernung;
   }  

   public Knoten getVorgaenger() {
      return vorgaenger;
   }  

   public void setVorgaenger(Knoten vorgaenger) {
      this.vorgaenger = vorgaenger;
   }

   public int getBearbeitungsstatus() {
      return bearbeitungsstatus;
   }  

   public void setBearbeitungsstatus(int bearbeitungsstatus) {
      this.bearbeitungsstatus = bearbeitungsstatus;
   }  
   
   /**
    * Setzt die Entfernung auf den Wert unendlich und den Vorgaenger auf null.
    */
   public void initialisieren() {
      entfernung = Integer.MAX_VALUE;
      vorgaenger = null;
      bearbeitungsstatus = 0;
   }
}
