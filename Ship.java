import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public abstract class Ship{

  enum Status { CREATED, PLACED, HIT, SUNK};
  protected int[][] location;
  protected int hits;
  protected Status status;

  public Ship(){
    hits = 0;
    status = Status.CREATED;
  }

  public Status getStatus(){
    return status;
  }
  public void setStatus(Status newStatus){
    status = newStatus;
  }
  public int[][] getLocation(){
      return location;
  }
  public void setLocation(int[][] newLocation){
    location = newLocation;
  }
  public int getHits(){
    return hits;
  }
  public void setHits(int newHit){
    hits = newHit;
  }
}
