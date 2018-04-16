import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.Color;



public abstract class Ship{

  enum Status { CREATED, PLACED, HIT, SUNK};
  protected ArrayList<int[]> location =new ArrayList<int[]>();
  protected int hits;
  protected Status status;
  protected String name;
  protected Color color;

  public Ship(){
    hits = 0;
    status = Status.CREATED;
    name = "Ship";
    color = Color.GREEN;
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
    for(int[] l:location){
      System.out.println("first coord" + l[0]);
      System.out.println("second coord" + l[1]);
    }
  }
  public int getHits(){
    return hits;
  }
  public void setHits(int newHit){
    hits = newHit;
  }
}
