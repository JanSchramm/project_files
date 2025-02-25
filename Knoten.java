class Knoten {
   private String name;
   private int x, y;

   public Knoten(String name) {
      this.name = name;
      this.x = -1;
      this.y = -1;
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
}
