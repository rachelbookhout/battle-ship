/**
 * This class represents the Submarine type of Ship. It inherits most of its properties from Ship
 * but has particular things definied specifically for it like color, name
 * and number of shots that will sink it
 * @author Rachel Bookhout
*/

import java.awt.Color;

public class Submarine extends Ship {
  protected int num = 3;
  protected String name;
  protected Color color;

  /**
   * This constructor has Submarine inherit everything from the Ship class
   * but sets the name, color, and numOfShotsToSink since those are different
  */
  public Submarine(){
    super();
    numOfShotsToSink = num;
    name = "Submarine";
    color = Color.RED;
  }

  /**
   * This method is to grab the name of the ship (so we can tell the difference between the types of ships)
   * @return name - String that is set within the constructor for BattleShip
  */
  public String getName(){
    return name;
  }

  /**
   * This method is to grab how many shots will sink a Battleship
   * @return numOfShotsToSink - integer that is set within the constructor for BattleShip
  */
  public int getShotsToSink(){
    return numOfShotsToSink;
  }

  /**
   * This method is check if the shot has sunk
   * @return true or false - returns if the number of shots the ship took are the same
   * as its capacity to take hits
  */
  public boolean isShipSunk(){
    if(this.hits == numOfShotsToSink){
      return true;
    }
    return false;
  }
  /**
   * This method is get the Color associated with a BattleShip
   * @return color - Color set within the constructor for a BattleShip
  */
  public Color getColor(){
    return color;
  }
}
