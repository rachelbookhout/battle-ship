import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.HashMap;


public class TrackingBoard extends Board {
    private PlayerBoard playerBoard;
    BombShip bombShip = new BombShip();
    public TrackingBoard(int dimensions){
        super(dimensions);
        //addEvents(dimensions);
    }

    public void setPlayerBoard(PlayerBoard board){
      playerBoard = board;
    }

    public void addEvents(int dimensions){
      JButton[][] board = getBoard();
      for (int o = 0; o < dimensions; o++) {
        for (int i = 0; i < dimensions; i++) {
          board[o][i].setVisible(true);
          // needs to be removed, can't find the 2nd call
          board[o][i].removeActionListener(bombShip);
          board[o][i].addActionListener(bombShip);
        }
      }
    }

    public void decideShipFate(JButton button){
      // get the button location
      String text = button.getText();
      System.out.println("Text is " + text);
      int index = text.indexOf(',');
      int firstNumber = Integer.parseInt(text.substring(0,index));
      int secondNumber = Integer.parseInt(text.substring(index + 1));
      System.out.println("First number:" + firstNumber + "secondNumber" + secondNumber);
      int[] location = {firstNumber, secondNumber};
      System.out.println(firstNumber + "," + secondNumber);
      HashMap<Boolean,Ship> isShipInLocation = checkShipLocation(location);
      System.out.println("Is Location a Ship?" + isShipInLocation);
      JButton[][] board = getBoard();
      if(!isShipInLocation.isEmpty()){
        board[secondNumber][firstNumber].setText("H");
        board[secondNumber][firstNumber].setForeground(isShipInLocation.get(true).getColor());
        board[secondNumber][firstNumber].setBackground(isShipInLocation.get(true).getColor());
        board[secondNumber][firstNumber].setOpaque(true);
        board[secondNumber][firstNumber].removeActionListener(bombShip);
      }
      else{
        board[secondNumber][firstNumber].setText("X");
        board[secondNumber][firstNumber].removeActionListener(bombShip);
      }
      //board[secondNumber][firstNumber].setForeground(ship.getColor());
      // check if ship is on it (Check locations array)
      // if ship is not on it, change the display to an X
      // if is, add hit to Ship and display "H" with highlight
    }

    private HashMap<Boolean,Ship> checkShipLocation(int[] coord){
      System.out.println("in ship location");
      HashMap<Boolean,Ship> shipLocation = new HashMap<Boolean,Ship>();
      ArrayList<Ship> ships = playerBoard.getShipsOnBoard();
      for(Ship ship: ships){
        ArrayList<int[]> locations = ship.getLocation();
         for(int[] location : locations){
               System.out.println("location:" + location[0] + "," + location[1]);
              if(Arrays.equals(location, coord)){
                shipLocation.put(true,ship);
                return shipLocation;
              }
         }
       }
       return shipLocation;
    }

    // public void createTrackingBoard(PlayerBoard playerBoard){
    //   JButton[][] board = getBoard();
    //   ArrayList<Ship> ships = playerBoard.getShipsOnBoard();
    //   for(Ship ship: ships){
    //     ArrayList<int[]> locations = ship.getLocation();
    //     for(int[] location : locations){
    //           //board[location[1]][location[0]].setText("S");
    //           board[location[1]][location[0]].setForeground(ship.getColor());
    //           board[location[1]][location[0]].setBackground(ship.getColor());
    //           board[location[1]][location[0]].setOpaque(true);
    //     }
    //   }
    // }

      // take board
      // copy its ships and their location
      // add them to the board as not visible
    //}
    private class BombShip implements ActionListener{
      public void actionPerformed(ActionEvent ae){
              System.out.println("Count of listeners: " + ((JButton) ae.getSource()).getActionListeners().length);
              decideShipFate((JButton) ae.getSource());
      }
    }

}
