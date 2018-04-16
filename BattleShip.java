import java.awt.Color;

public class BattleShip extends Ship {
    protected int numOfShotsToSink;
    protected String name;
      protected Color color;

    public BattleShip(){
        super();
        numOfShotsToSink = 4;
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
}
