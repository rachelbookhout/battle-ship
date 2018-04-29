/**
 * This class represents the Cruiser type of Ship. It inherits most of its properties from Ship
 * but has particular things definied specifically for it like color, name
 * and number of shots that will sink it
 * @author Rachel Bookhout
*/

import java.awt.Color;


public class Cruiser extends Ship {
  protected String name;
  protected int shots = 1;
  protected Color color;

   /**
   * This constructor has Cruiser inherit everything from the Ship class
   * but sets the name, color, and numOfShotsToSink since those are different
  */
  public Cruiser(){
    super();
    numOfShotsToSink = shots;
    name = "Cruiser";
    color = Color.ORANGE;
  }

  /**
   * This method is to grab how many shots will sink a Battleship
   * @return numOfShotsToSink - integer that is set within the constructor for BattleShip
  */
  public int getShotsToSink(){
    return numOfShotsToSink;
  }

  /**
   * This method is to grab the name of the ship (so we can tell the difference between the types of ships)
   * @return name - String that is set within the constructor for BattleShip
  */
  public String getName(){
    return name;
  }

  /**
   * This method is get the Color associated with a BattleShip
   * @return color - Color set within the constructor for a BattleShip
  */
  public Color getColor(){
    return color;
  }
}
