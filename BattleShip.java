import java.awt.Color;

public class BattleShip extends Ship {
    protected int num = 4;
    protected String name;
      protected Color color;

    public BattleShip(){
        super();
        numOfShotsToSink = num;
        name = "BattleShip";
        color = Color.CYAN;
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

    public Color getColor(){
    return color;
  }
}
