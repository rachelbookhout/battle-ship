import java.awt.Color;


public class Submarine extends Ship {
    protected int numOfShotsToSink;
    protected String name;
      protected Color color;

    public Submarine(){
        super();
        numOfShotsToSink = 3;
        name = "Submarine";
        color = Color.RED;
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
