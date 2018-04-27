/**
 * This class inherits from the Board class and is designed for the player to use to choose
 * where they want to place their ships
 * @author Rachel Bookhout
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class PlayerBoard extends Board {
  public Ship tempShip;
  PlaceShip ps = new PlaceShip();

 /**
  * This constructor is to create our board and takes everything from the Board class
  * it also adds the action listeners to each JButton (square)
  * @param dimensions, integer of how big we make the board
 */
  public PlayerBoard(int dimensions){
      super(dimensions);
      addEvents(dimensions);
  }

  /**
   * This adds our PlaceShip action listener to each JButton
   * @param dimensions, integer of how big our board is
  */
  public void addEvents(int dimensions){
    System.out.println("Add Event");
    JButton[][] board = getBoard();
    for (int o = 0; o < dimensions; o++) {
      for (int i = 0; i < dimensions; i++) {
        board[o][i].setVisible(true);
        board[o][i].addActionListener(ps);
      }
    }
  }

  /**
   * This removes the action lisener PlaceShip from the JButtons
  */
  public void removeEvents(){
    JButton[][] board = getBoard();
    for (int o = 0; o < dimensions; o++) {
      for (int i = 0; i < dimensions; i++) {
        board[o][i].removeActionListener(ps);
      }
    }
  }

  /**
   * This sets the value of tempShip which represents which Ship the user wants to place
   * @param ship, Ship, the ship we want to place
  */
  public void setTempShip(Ship ship){
    tempShip = ship;
  }


  /**
   * This method sets up the ships for our computer
   * it chooses random locations for its ships
  */
  public void setShipPlacements(){
    Cruiser cruiser = new Cruiser();
    BattleShip battleShip = new BattleShip();
    Carrier carrier = new Carrier();
    Destroyer destroyer = new Destroyer();
    Submarine submarine = new Submarine();
    cruiser.setRandomLocation(this);
    carrier.setRandomLocation(this);
    destroyer.setRandomLocation(this);
    submarine.setRandomLocation(this);
    battleShip.setRandomLocation(this);
    connectShipToBoard(cruiser);
    connectShipToBoard(carrier);
    connectShipToBoard(destroyer);
    connectShipToBoard(submarine);
    connectShipToBoard(battleShip);
  }


  /**
   * This adds a ship to the location of the button that the user clicked
   * @param button, JButton, the button or square that the user clicked
   * @param ship, Ship -> the ship we are trying to place
  */
  private void addShip(JButton button, Ship ship){
    // figure out which button we clicked and set it as our coordinates
    String text = button.getText();
    int index = text.indexOf(',');
    int firstNumber = Integer.parseInt(text.substring(0,index));
    int secondNumber = Integer.parseInt(text.substring(index + 1));
    int[] location = {firstNumber, secondNumber};
    // add the location to the locations arrayList associated with the ship
    ship.setLocation(location);
    // note on the board that we are going to place a ship there
    JButton[][] board = getBoard();
    board[secondNumber][firstNumber].setForeground(ship.getColor());
    board[secondNumber][firstNumber].setBackground(ship.getColor());
    board[secondNumber][firstNumber].setOpaque(true);
    board[secondNumber][firstNumber].setEnabled(false);
    // if we have clicked enough squares that are next to each other to place the ship
    if(checkLocation(ship)){
      ship.setStatus(Ship.Status.PLACED);
      // mark "S" for ship on the square and remove it from being able to be clicked
      for(int[] s : ship.getLocation()){
        board[s[1]][s[0]].setForeground(ship.getColor());
        board[s[1]][s[0]].setText("S");
      }
        removeEvents();
    };
  }

    /**
   * This remove all locations for the Ship and changes the display so it can be chosen again
  */
  public void removeShips(){
    JButton[][] board = getBoard();
    for(int[] s : tempShip.getLocation()){
      board[s[1]][s[0]].setForeground(Color.GREEN);
      board[s[1]][s[0]].setBackground(Color.WHITE);
      board[s[1]][s[0]].setOpaque(false);
      board[s[1]][s[0]].setEnabled(true);
    }
    tempShip.removeLocations();
  }

  /**
   * This checks all of the location coordinates within locations for the Ship
   * and verifies that they are in order which means it's a valid placement
   * @param ship, Ship -> the ship we are trying to place
  */
  private boolean checkLocation(Ship ship){
    System.out.println("size:" + ship.getLocation().size());
    if (ship.getLocation().size() >= ship.getShotsToSink()){
      ArrayList<int[]> locations = ship.getLocation();
      // borrowed from https://stackoverflow.com/questions/19596950/sort-an-arraylist-of-integer-arrays
      // sort location array for horizontal, if all are, return true
      Collections.sort(locations, new Comparator<int[]>() {
          @Override
          public int compare(int[] o1, int[] o2) {
              return Integer.compare(o1[0], o2[0]);
          }
      });

      int hCount = locations.get(0)[0];
      int yValue = locations.get(0)[1];
      for(int[] s : locations){
        if(hCount == s[0] && yValue == s[1]){
          hCount++;
          continue;
        }
        else{
          break;
        }
      }
      if(hCount == locations.get(0)[0] + ship.getShotsToSink()){
        return true;
      }

      // if all slots are vertical to each other, return true
      Collections.sort(locations, new Comparator<int[]>() {
          @Override
          public int compare(int[] o1, int[] o2) {
              return Integer.compare(o1[1], o2[1]);
          }
      });
      int vCount = locations.get(0)[1];
      int xValue = locations.get(0)[0];
      for(int[] s : locations){
        if(vCount == s[1] && xValue == s[0]){
          vCount++;
          continue;
        }
        else{
          break;
        }
      }
      if(vCount == locations.get(0)[1] + ship.getShotsToSink()){
        return true;
    }
    }
    return false;
  }

  /**
  * This class implements the action listener for a user clicking on a square
  * on the board. It places the ship
  */
  private class PlaceShip implements ActionListener{
    public void actionPerformed(ActionEvent ae){
      addShip((JButton) ae.getSource(), tempShip);
    }
  }
}
