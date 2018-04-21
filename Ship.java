import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.Color;
import java.util.Random;

public abstract class Ship{

  enum Status { CREATED, PLACED, HIT, SUNK};
  protected ArrayList<int[]> location =new ArrayList<int[]>();
  protected int hits;
  protected int numOfShotsToSink;
  protected Status status;
  protected String name;
  protected Color color;

  public Ship(){
    hits = 0;
    status = Status.CREATED;
    name = "Ship";
    color = Color.GREEN;
    numOfShotsToSink = 0;
  }

  public Status getStatus(){
    return status;
  }
  public void setStatus(Status newStatus){
    status = newStatus;
  }
  public ArrayList<int[]> getLocation(){
      return location;
  }
  public void setLocation(int[] newLocation){
    location.add(newLocation);
    //for(int[] l:location){
      //System.out.println("first coord" + l[0]);
      //System.out.println("second coord" + l[1]);
    //}
  }
  public int getHits(){
    return hits;
  }
  public void setHits(int newHit){
    hits = newHit;
  }

  public int getShotsToSink(){
      return numOfShotsToSink;
  }

  public Color getColor(){
    return color;
  }

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
      break; // optional

   case 2 :
      // Statements
      setLocation(new int[]{1,0});
      setLocation(new int[]{2,0});

      break; // optional
     case 3 :
      setLocation(new int[]{2,1});
      setLocation(new int[]{2,2});
      setLocation(new int[]{2,3});

      // Statements
      break; // optional
     case 4 :
      setLocation(new int[]{4,0});
      setLocation(new int[]{4,1});
      setLocation(new int[]{4,2});
      setLocation(new int[]{4,3});

      // Statements
      break; // optional
     case 5 :
      setLocation(new int[]{0,1});
      setLocation(new int[]{0,2});
      setLocation(new int[]{0,3});
      setLocation(new int[]{0,4});
      setLocation(new int[]{0,5});
      // Statements
      break; // optional

   // You can have any number of case statements.
   default : // Optional
      // Statements
}
    // check if random coordinates work
    // find the places next to it
    // if they are taken, abort and try the other way
    // if those are taken, go the other direction

  }

}
