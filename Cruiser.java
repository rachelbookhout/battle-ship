import java.awt.Color;

public class Cruiser extends Ship {
    protected String name;
    protected int num = 1;
    protected Color color;

    public Cruiser(){
        super();
        numOfShotsToSink = num;
        name = "Cruiser";
        color = Color.ORANGE;
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

    public Color getColor(){
    return color;
  }
}
