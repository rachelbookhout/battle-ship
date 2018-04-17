public class Carrier extends Ship {
    protected int num = 5;
    protected String name;
    public Carrier(){
        super();
        numOfShotsToSink = num;
        name = "Carrier";
    }

    public String getName(){
      return name;
    }

    public boolean isShipSunk(){
      if(this.hits == numOfShotsToSink){
        return true;
      }
      return false;
    }
}
