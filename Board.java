/**
 * This class creates the Board object that PlayerBoard and TrackingBoard inherit from
 * and is the crux of BattleShip
 * @author Rachel Bookhout
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public abstract class Board extends JPanel{

  enum Status { CREATED, READY_FOR_PLACEMENT,PLACED};

  protected int dimensions;
  public JButton[][] jbtnBoard = new JButton[dimensions][dimensions];
  protected ArrayList<Ship> ships = new ArrayList<Ship>(5);
  protected Status status;
  protected int hitsLeft;

  /**
   * This constructor creates our board and intalizies all of its properties it has by default
   * @param dimensions - integer which is how big our board will be (it is set up to be 6x6)
  */
  public Board(int dimensions){
    this.dimensions = dimensions;
    createBoard(dimensions);
    this.status = Status.CREATED;
    this.hitsLeft = 15;
  }

  /**
   * This method gets how many hits all of our ships can take (15 is all 5 ships total to)
   * @return hitsLeft - integer of all many hits we have left
  */
  public int getHitsLeft(){
    return hitsLeft;
  }

  /**
   * This method sets the number of hits left
   * @param hits - integer, new number of hits left
  */
  public void setHitsLeft(int hits){
    hitsLeft = hits;
  }

  /**
   * This method creates our board
   * @param size - integer, how big our board will be
   * @return jbtnBoard - JButton[][] our array of JButtons that represent the board
  */
  private JButton[][] createBoard(int size){
    int count = 1;
    this.setLayout( new GridLayout(dimensions, dimensions));
    for (int o = 0; o < size; o++) {
      for (int i = 0; i < size; i++) {
        jbtnBoard[o][i] = new JButton(Integer.toString(i) + "," + Integer.toString(o));
        String name = Integer.toString(i) + "," + Integer.toString(o);
        jbtnBoard[o][i].setName(name);
        jbtnBoard[o][i].setForeground(Color.GREEN);
        this.add(jbtnBoard[o][i]);
        count++;
      }
    }
    return jbtnBoard;
  }

  /**
   * This method grabs our board
   * @return jbtnBoard - JButton[][] our array of JButtons that represents the board
  */
  public JButton[][] getBoard(){
    return jbtnBoard;
  }

  /**
   * This method returns the ships that are associated with the board
   * @return ships - ArrayList<Ship> arraylist of the ships we have associated with the board
  */
  public ArrayList<Ship> getShipsOnBoard(){
    return ships;
  }

  /**
   * This method gets the status of our board
   * @return status - Status, status of our board
  */
  public Status getStatus(){
    return status;
  }

  /**
   * This method sets a new status for our board
   * @param newStatus - Status, new status for our board
  */
  public void setStatus(Status newStatus){
    status = newStatus;
  }

  /**
   * This method adds a Ship to our board
   * @param ship - Ship, the ship we want added to our board
  */
  public void connectShipToBoard(Ship ship){
    ships.add(ship);
  }
}
