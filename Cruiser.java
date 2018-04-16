import java.awt.Color;

public class Cruiser extends Ship {
    protected String name;
    protected int numOfShotsToSink;
      protected Color color;

    public Cruiser(){
        super();
        numOfShotsToSink = 3;
        name = "Cruiser";
        color = Color.ORANGE;
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
