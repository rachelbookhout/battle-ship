public class Carrier extends Ship {
    protected int numOfShotsToSink;
    protected String name;
    public Carrier(){
        super();
        numOfShotsToSink = 5;
        name = "Carrier";
    }

    public String getName(){
      return name;
    }

    public int getShotsToSink(){
      return numOfShotsToSink;
    }

    public boolean isShipSunk(){
      if(this.hits == numOfShotsToSink){
        return true;
      }
      return false;
    }
}
