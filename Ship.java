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
  public void setRandomLocation(Board board){
    // get random direction
    int lowerBound = 0;
    int higherBound = 5;
    int num = getShotsToSink();
    ArrayList<Integer> used = new ArrayList<Integer>();
    Random rand = new Random();
    int xCoor = rand.nextInt(5);
    int yCoor = rand.nextInt(5);
    System.out.println("This is our coord" + xCoor + "," + yCoor);
    // if x is lower than numOfShots to sink, add ships horizontally, checking against used array
    // then add to used array
    if(!checkRightHorizontal(xCoor, yCoor, num, higherBound, used)){
      System.out.println("Right failed. on to down");
      boolean downVertical = checkDownVertical(xCoor, yCoor, num, higherBound, used);
      System.out.println("Down is" + downVertical);
      if(!downVertical){
        boolean leftHorizontal = checkLeftHorizontal(xCoor, yCoor, num, lowerBound, used);
        if(!leftHorizontal){
          boolean upVertical = checkUpVertical(xCoor, yCoor, num, lowerBound, used);
          if(!leftHorizontal){
            System.out.println("Can't find placement");
          }
        }
      }
    }

    // if y is lower than numOfShots to sink, add ships veritcally, checking against used array
    // and add to used array
    System.out.println("printing out location at end of method");
    ArrayList<int[]> locations = getLocation();
      for(int[] location : locations){
        System.out.println(location[0] + "," + location[1]);
      }

    //ArrayList<Ship> ships = board.getShipsOnBoard();
    //ArrayList<int[]> shipCoordinates = new ArrayList<int[]>();
    //for(Ship ship: ships){
      //ArrayList<int[]> locations = ship.getLocation();
      //for(int[] location : locations){
        //shipCoordinates.add(location);
      //}
    }

    public boolean checkRightHorizontal(int xCoor, int yCoor, int num, int higherBound, ArrayList<Integer>used){
      System.out.println("in right horz");
      ArrayList<int[]> tempValues = new ArrayList<int[]>();
      System.out.println("xcoor:" + xCoor + " num:" + num + " highbound" + higherBound);
      if(xCoor + num - 1 <= higherBound){
        int tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString(yCoor));
        System.out.println("I'm tempValue" + tempValue);
        int[] tempArray = new int[2];
        if(!checkUsed(used,tempValue)){
          System.out.println("I'm not used!!!!");
          tempArray[0] = xCoor;
          tempArray[1]= yCoor;
          tempValues.add(tempArray);
          used.add(tempValue);
          System.out.println("added first value to tempValues and used");
          for(int i = 1; i < num; i++){
            int[] temp = new int[2];
            System.out.println("horz i = " + i);
            tempValue = Integer.parseInt(Integer.toString((xCoor + i)) + Integer.toString(yCoor));
            System.out.println("tempvalue within for statement" + tempValue);
            if(!checkUsed(used,tempValue)){
              System.out.println("I'm not used Round" + i);
              temp[0] = xCoor + i;
              temp[1]= yCoor;
              System.out.println("Temp array 0 value:" + temp[0] + " and 1 value" + temp[1]);
              tempValues.add(temp);
              used.add(tempValue);
            }
            else{
              return false;
            }
          }
        System.out.println("setting location");
        location = tempValues;
        return true;
        }
      }
      return false;
    }

    public boolean checkLeftHorizontal(int xCoor, int yCoor, int num, int lowerBound, ArrayList<Integer>used){
      System.out.println("in left horz");
      System.out.println("xcoor:" + xCoor + " num:" + num + " lowbound" + lowerBound);
      ArrayList<int[]> tempValues = new ArrayList<int[]>();
      if(xCoor - num + 1 >= lowerBound){
        System.out.println("doing this left");
        int tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString(yCoor));
        int[] tempArray = new int[2];
        if(!checkUsed(used,tempValue)){
          System.out.println("I'm not used!");
          tempArray[0] = xCoor;
          tempArray[1]= yCoor;
          tempValues.add(tempArray);
          used.add(tempValue);
          for(int i = 1; i < num; i++){
            int[] temp = new int[2];
            tempValue = Integer.parseInt(Integer.toString((xCoor - i)) + Integer.toString(yCoor));
            System.out.println("tempvalue within for statement" + tempValue);
            if(!checkUsed(used,tempValue)){
              System.out.println("I'm not used Round" + i);
              temp[0] = xCoor - i;
              temp[1]= yCoor;
              System.out.println("Temp array 0 value:" + temp[0] + "and 1 value" + temp[1]);
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

    public boolean checkDownVertical(int xCoor, int yCoor, int num, int higherBound, ArrayList<Integer> used){
      System.out.println("in down vert");
      ArrayList<int[]> tempValues = new ArrayList<int[]>();
      System.out.println("yCoor:" + yCoor + "num" + num + "higherBound:" + higherBound);
      if(yCoor + num -1 <= higherBound){
        int tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString(yCoor));
        System.out.println("This is tempValue" + tempValue);
        int[] tempArray = new int[2];
        if(!checkUsed(used,tempValue)){
          System.out.println("I'm not used!");
          tempArray[0] = xCoor;
          tempArray[1]= yCoor;
          tempValues.add(tempArray);
          used.add(tempValue);
          for(int i = 1; i < num; i++){
            int[] temp = new int[2];
            tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString((yCoor + i)));
            System.out.println("tempvalue within for statement" + tempValue);
            if(!checkUsed(used,tempValue)){
              System.out.println("I'm not used Round" + i);
              temp[0] = xCoor;
              temp[1]= yCoor + i;
              System.out.println("Temp array 0 value:" + temp[0] + " and 1 value" + temp[1]);
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


    public boolean checkUpVertical(int xCoor, int yCoor, int num, int lowerBound, ArrayList<Integer> used){
      System.out.println("in up vert");
      ArrayList<int[]> tempValues = new ArrayList<int[]>();
      System.out.println("ycoor" + yCoor + "num" + num + "lowerBound" + lowerBound);
      if(yCoor - num + 1 <= lowerBound){
        int tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString(yCoor));
        System.out.println("I'm tempvalue" + tempValue);
        int[] tempArray = new int[2];
        if(!checkUsed(used,tempValue)){
          System.out.println("I'm not used!");
          tempArray[0] = xCoor;
          tempArray[1]= yCoor;
          tempValues.add(tempArray);
          used.add(tempValue);
          for(int i = 1; i < num; i++){
            int[] temp = new int[2];
            tempValue = Integer.parseInt(Integer.toString(xCoor) + Integer.toString((yCoor - i)));
            System.out.println("tempvalue within for statement" + tempValue);
            if(!checkUsed(used,tempValue)){
              System.out.println("I'm not used Round" + i);
              temp[0] = xCoor;
              temp[1]= yCoor - i;
              System.out.println("Temp array 0 value:" + temp[0] + " and 1 value" + temp[1]);
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

    public boolean checkUsed(ArrayList<Integer> used, int num){
      System.out.println("Size of used:" + used.size() + "and our num is" + num);
      for (int o = 0; o < used.size() - 1; o++) {
        if(used.get(o) == num){
          return true;
        }
      }
      return false;
    }
    // switch(num) {
    // case 1 :
    //   setLocation(new int[]{0,0});
    //   break;
    // case 2 :
    //   setLocation(new int[]{1,0});
    //   setLocation(new int[]{2,0});
    //   break;
    //  case 3 :
    //   setLocation(new int[]{2,1});
    //   setLocation(new int[]{2,2});
    //   setLocation(new int[]{2,3});
    //   break;
    //  case 4 :
    //   setLocation(new int[]{4,0});
    //   setLocation(new int[]{4,1});
    //   setLocation(new int[]{4,2});
    //   setLocation(new int[]{4,3});
    //   break;
    //   case 5 :
    //   setLocation(new int[]{0,1});
    //   setLocation(new int[]{0,2});
    //   setLocation(new int[]{0,3});
    //   setLocation(new int[]{0,4});
    //   setLocation(new int[]{0,5});
    //   break;
    //   default :
    //   break;
    // }
  //}
}
