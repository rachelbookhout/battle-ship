import java.awt.Color;


public class Destroyer extends Ship {
    protected int numOfShotsToSink;
    protected String name;
      protected Color color;

    public Destroyer(){
        super();
        numOfShotsToSink = 2;
        name = "Destroyer";
        color = Color.PINK;
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
