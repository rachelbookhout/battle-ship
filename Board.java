import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public abstract class Board extends JPanel{

  enum Status { CREATED, READY_FOR_PLACEMENT,PLACED};
  protected int dimensions = 10;
  public JButton[][] jbtnBoard = new JButton[dimensions][dimensions];
  protected ArrayList<Ship> ships = new ArrayList<Ship>(5);
  protected Status status;

  public Board(int dimensions){
    this.dimensions = dimensions;
    createBoard(dimensions);
    this.status = Status.CREATED;
    //connectShipsToBoard();
  }

  private JButton[][] createBoard(int size){
    int count = 1;
    this.setLayout( new GridLayout(dimensions, dimensions));
    for (int o = 0; o < size; o++) {
      for (int i = 0; i < size; i++) {
        jbtnBoard[o][i] = new JButton(Integer.toString(i) + "," + Integer.toString(o));
        jbtnBoard[o][i].setForeground(Color.GREEN);
        jbtnBoard[o][i].setBackground( Color.BLACK );
        this.add(jbtnBoard[o][i]);
        count++;
      }
    }
  return jbtnBoard;
  }

  public JButton[][] getBoard(){
    return jbtnBoard;
  }

  public ArrayList<Ship> getShipsOnBoard(){
    System.out.println("s-h-i-p");
    System.out.println(ships.size());
    return ships;
  }

  public Status getStatus(){
    return status;
  }

  public void setStatus(Status newStatus){
    status = newStatus;
  }

  public void connectShipToBoard(Ship ship){
    System.out.println(ships.size());
    ships.add(ship);
  }

  //public void connectShipsToBoard(){
    //ships.add(new Carrier());
    //ships.add(new BattleShip());
    //ships.add(new Destroyer());
    //ships.add(new Cruiser());
    //ships.add(new Submarine());
  //}
}
