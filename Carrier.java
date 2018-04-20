import java.awt.Color;

public class Carrier extends Ship {
    protected int num = 5;
    protected String name;
    protected Color color;

    public Carrier(){
        super();
        numOfShotsToSink = num;
        name = "Carrier";
        color = Color.DARK_GRAY;
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
