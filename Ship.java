/**
 * This class represents the Ship class which all other ships inherit from
 * @author Rachel Bookhout
*/

import java.io.*;
import java.util.*;
import java.awt.Color;
import java.util.Random;

public abstract class Ship{

  enum Status { CREATED, PLACED, HIT, SUNK};
  // keeps track of the locations on the board that the ship is placed
  protected ArrayList<int[]> location = new ArrayList<int[]>();
  protected int hits;
  protected int numOfShotsToSink;
  protected Status status;
  protected String name;
  protected Color color;

  /**
   * This constructor creates the default Ship
  */
  public Ship(){
    hits = 0;
    status = Status.CREATED;
    name = "Ship";
    color = Color.GREEN;
    numOfShotsToSink = 0;
  }

  /**
   * This methods get the status of the ship
   * @return Status, status of Ship
  */
  public Status getStatus(){
    return status;
  }

  /**
   * This methods sets the status of the ship
   * @param newStatus, status that our Ship will now have
  */
  public void setStatus(Status newStatus){
    status = newStatus;
  }

  /**
   * This methods returns the coordinates that the ship occupies
   * @return location, ArrayList<int[]>
  */
  public ArrayList<int[]> getLocation(){
      return location;
  }

  /**
   * This adds a coordinates to our location arrayList
   * @param newLocation, int[], coordinates where our ship is placed
  */
  public void setLocation(int[] newLocation){
    location.add(newLocation);
  }

  /**
   * This methods get the hits done to a ship
   * @return hits, integer
  */
  public int getHits(){
    return hits;
  }

 /**
   * This methods sets the hits done to a ship
   * @param newHit, integer, new number of hits on a ship
  */
  public void setHits(int newHit){
    hits = newHit;
  }

  /**
   * This method is to grab how many shots will sink a Battleship
   * @return numOfShotsToSink - integer that is set within the constructor for BattleShip
  */
  public int getShotsToSink(){
      return numOfShotsToSink;
  }

  /**
   * This method is to return the name of the ship
   * @return name, String, name of ship
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

  /**
   * This method chooses random location for a ship on the board
   * used by the computer to set up the board
   * @param board, will be a PlayerBoard where we are placing the ships
  */
  public void setRandomLocation(Board board){
    // get random direction
    int lowerBound = 0;
    int higherBound = 5;
    int num = getShotsToSink();
    Random rand = new Random();
    int xCoor = rand.nextInt(5);
    int yCoor = rand.nextInt(5);
    ArrayList<Ship> ships = board.getShipsOnBoard();
    ArrayList<int[]> shipCoordinates = new ArrayList<int[]>();
    for(Ship ship: ships){
      ArrayList<int[]> locations = ship.getLocation();
      for(int[] location : locations){
        shipCoordinates.add(location);
      }
    }
    switch(num) {
    case 1 :
      setLocation(new int[]{0,0});
      break;
    case 2 :
      setLocation(new int[]{1,0});
      setLocation(new int[]{2,0});
      break;
     case 3 :
      setLocation(new int[]{2,1});
      setLocation(new int[]{2,2});
      setLocation(new int[]{2,3});
      break;
     case 4 :
      setLocation(new int[]{4,0});
      setLocation(new int[]{4,1});
      setLocation(new int[]{4,2});
      setLocation(new int[]{4,3});
      break;
      case 5 :
      setLocation(new int[]{0,1});
      setLocation(new int[]{0,2});
      setLocation(new int[]{0,3});
      setLocation(new int[]{0,4});
      setLocation(new int[]{0,5});
      break;
      default :
      break;
    }
  }
}
