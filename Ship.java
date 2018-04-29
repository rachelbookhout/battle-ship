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
   * This removes all location coordinates from the location array
  */
  public void removeLocations(){
    location.clear();
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
  private void setRandomLocation(Board board){
  // get the values that are setting our boundries on what numbers
  // we can use for coordinates
    int lowerBound = 0;
    int higherBound = 5;
    int num = getShotsToSink();
  // create an arrayList to keep track of the values we have used
    ArrayList<Integer> usedNum = new ArrayList<Integer>();
    Random rand = new Random();
    int xCoor;
    int yCoor;
    int value;
    int count = 0;
  // keep asking for random coordinates until we find one that hasn't been used yet
  // we have the count under 20 to prevent the method from ending up in a loop
  // if it can't get a random number we haven't located a ship on
    do {
      xCoor = rand.nextInt(6);
      yCoor = rand.nextInt(6);
      count += 1;
      value = Integer.parseInt(Integer.toString(xCoor) + Integer.toString(yCoor));
    } while (checkUsed(usedNum,value) && count < 20);

    System.out.println("Coordinates: " + xCoor + "," + yCoor);
  // this runs through checking if we can put the ship horizontally R/L
  // or vertical R/L
  // if it can't find any way to place the ship, it will just not do it
    if(!checkRightHorizontal(xCoor, yCoor, num, higherBound, usedNum)){
      boolean downVertical = checkDownVertical(xCoor, yCoor, num, higherBound, usedNum);
      if(!downVertical){
        boolean leftHorizontal = checkLeftHorizontal(xCoor, yCoor, num, lowerBound, usedNum);
        if(!leftHorizontal){
          boolean upVertical = checkUpVertical(xCoor, yCoor, num, lowerBound, usedNum);
          if(!leftHorizontal){
            System.out.println("Can't find placement");
          }
        }
      }
    }

  //prints out the location of the ships on the command line
  // to make it easier to debug if something went wrong
    System.out.println("printing out locations of the ship");
    ArrayList<int[]> locations = getLocation();
    for(int[] location : locations){
      System.out.println(location[0] + "," + location[1]);
    }
  }

  /**
   * This method checks against an arraylist of used locations for the ships
   * and if it finds that the ship can be added num sports from xCoor,yCoor to the
   * right horizontally, it returns true
   * @param xCoor, int, x coordinate for our starting value
   * @param yCoor, int, y coordinate for our starting value
   * @param num, int, numberOfShotsToSink or how many spaces our ship takes up
   * @param higherBound, int, the largest x or y could be
   * @param used, ArrayList<int[]>, contains a list of the integers we have used
   * all integers are placed as two digits, so coord 1,2 would be set as "12" in it
   * @return true or false if we can place the ship horizontally, adding to the right
  */
  private boolean checkRightHorizontal(int xCoor, int yCoor, int num, int higherBound, ArrayList<Integer>used){
    ArrayList<int[]> tempValues = new ArrayList<int[]>();
    if(xCoor + num - 1 <= higherBound){
      int tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString(yCoor));
      // if we haven't used the coordinates, iterate through the coordinates to the right horziontally
      if(!checkUsed(used,tempValue)){
        for(int i = 0; i < num; i++){
          int[] temp = new int[2];
          tempValue = Integer.parseInt(Integer.toString((xCoor + i)) + Integer.toString(yCoor));
          // make sure they haven't been used already
          if(!checkUsed(used,tempValue)){
            temp[0] = xCoor + i;
            temp[1]= yCoor;
            tempValues.add(temp);
            used.add(tempValue);
          }
          else{
            return false;
          }
        }
      // set our temporary array to the actual locations for the ship
      location = tempValues;
      return true;
      }
    }
    return false;
  }

    /**
   * This method checks against an arraylist of used locations for the ships
   * and if it finds that the ship can be added num sports from xCoor,yCoor to the
   * left horizontally, it returns true
   * @param xCoor, int, x coordinate for our starting value
   * @param yCoor, int, y coordinate for our starting value
   * @param num, int, numberOfShotsToSink or how many spaces our ship takes up
   * @param lowerBound, int, the smallest x or y could be
   * @param used, ArrayList<int[]>, contains a list of the integers we have used
   * all integers are placed as two digits, so coord 1,2 would be set as "12" in it
   * @return true or false if we can place the ship horizontally, adding to the left
  */
  private boolean checkLeftHorizontal(int xCoor, int yCoor, int num, int lowerBound, ArrayList<Integer>used){
    ArrayList<int[]> tempValues = new ArrayList<int[]>();
    // check that we have space to add locations to the left of coordinate
    if(xCoor - num + 1 >= lowerBound){
      int tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString(yCoor));
      if(!checkUsed(used,tempValue)){
        // iterate through each square to the left and make sure it's not in use
        for(int i = 0; i < num; i++){
          int[] temp = new int[2];
          tempValue = Integer.parseInt(Integer.toString((xCoor - i)) + Integer.toString(yCoor));
          if(!checkUsed(used,tempValue)){
            temp[0] = xCoor - i;
            temp[1]= yCoor;
            tempValues.add(temp);
            used.add(tempValue);
          }
          else{
            return false;
          }
        }
        location = tempValues;
        return true;
      }
    }
    return false;
  }

    /**
   * This method checks against an arraylist of used locations for the ships
   * and if it finds that the ship can be added num sports from xCoor,yCoor to the
   * south vertically, it returns true
   * @param xCoor, int, x coordinate for our starting value
   * @param yCoor, int, y coordinate for our starting value
   * @param num, int, numberOfShotsToSink or how many spaces our ship takes up
   * @param higherBound, int, the largest x or y could be
   * @param used, ArrayList<int[]>, contains a list of the integers we have used
   * all integers are placed as two digits, so coord 1,2 would be set as "12" in it
   * @return true or false if we can place the ship vertically, adding down
  */
  private boolean checkDownVertical(int xCoor, int yCoor, int num, int higherBound, ArrayList<Integer> used){
    ArrayList<int[]> tempValues = new ArrayList<int[]>();
    // check that we have the space to place ships down
    if(yCoor + num -1 <= higherBound){
      int tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString(yCoor));
      if(!checkUsed(used,tempValue)){
        // iterate through and check the spaces against the used spaces
        for(int i = 0; i < num; i++){
          int[] temp = new int[2];
          tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString((yCoor + i)));
          if(!checkUsed(used,tempValue)){
            temp[0] = xCoor;
            temp[1]= yCoor + i;
            tempValues.add(temp);
            used.add(tempValue);
          }
          else{
            return false;
          }
        }
        location = tempValues;
        return true;
      }
    }
    return false;
  }

    /**
   * This method checks against an arraylist of used locations for the ships
   * and if it finds that the ship can be added num sports from xCoor,yCoor to the
   * north vertically, it returns true
   * @param xCoor, int, x coordinate for our starting value
   * @param yCoor, int, y coordinate for our starting value
   * @param num, int, numberOfShotsToSink or how many spaces our ship takes up
   * @param lowerBound, int, the smallest x or y could be
   * @param used, ArrayList<int[]>, contains a list of the integers we have used
   * all integers are placed as two digits, so coord 1,2 would be set as "12" in it
   * @return true or false if we can place the ship vertically, adding upwards
  */
  private boolean checkUpVertical(int xCoor, int yCoor, int num, int lowerBound, ArrayList<Integer> used){
    ArrayList<int[]> tempValues = new ArrayList<int[]>();
    // check if we have the space to add ships above our current coordinate
    if(yCoor - num + 1 >= lowerBound){
      int tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString(yCoor));
      if(!checkUsed(used,tempValue)){
        // iterate through the values to the north, check they aren't used
        for(int i = 0; i < num; i++){
          int[] temp = new int[2];
          tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString((yCoor - i)));
          if(!checkUsed(used,tempValue)){
            temp[0] = xCoor;
            temp[1]= yCoor - i;
            tempValues.add(temp);
            used.add(tempValue);
          }
          else{
            return false;
          }
        }
        location = tempValues;
        return true;
      }
    }
    return false;
  }

    /**
   * This method checks against an arraylist of used locations for the ships
   * and if it finds that num is within the used locations, returns true
   * @param num, int, numberOfShotsToSink or how many spaces our ship takes up
   * @param used, ArrayList<int[]>, contains a list of the integers we have used
   * @return true or false if we found num within the used ArrayList
  */
  private boolean checkUsed(ArrayList<Integer> used, int num){
    for (int o = 0; o < used.size() - 1; o++) {
      if(used.get(o) == num){
        return true;
      }
    }
    return false;
  }
}
