import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class TrackingBoard extends Board {
    public TrackingBoard(int dimensions){
        super(dimensions);
        //addEvents(dimensions);
    }

    // private void addEvents(int dimensions){
    //   JButton[][] board = getBoard();
    //   for (int o = 0; o < dimensions; o++) {
    //     for (int i = 0; i < dimensions; i++) {
    //       board[o][i].setVisible(true);
    //       board[o][i].addActionListener(new ActionListener(){
    //         public void actionPerformed(ActionEvent ae){
    //           decideShipFate((JButton) ae.getSource());
    //           }
    //         });
    //     }
    //   }
    // }

    private void decideShipFate(JButton button){
      // add ship
    }

    public void createTrackingBoard(PlayerBoard playerBoard){
      JButton[][] board = getBoard();
      ArrayList<Ship> ships = playerBoard.getShipsOnBoard();
      for (int o = 0; o < dimensions; o++) {
        for (int i = 0; i < dimensions; i++) {
          board[o][i].setVisible(true);
          board[o][i].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
              decideShipFate((JButton) ae.getSource());
              }
            });
        }
      }
      for(Ship ship: ships){
        ArrayList<int[]> locations = ship.getLocation();
        for(int[] location : locations){
              board[location[1]][location[0]].setText("S");
              board[location[1]][location[0]].setForeground(ship.getColor());
              board[location[1]][location[0]].setBackground(ship.getColor());
              board[location[1]][location[0]].setOpaque(true);
        }
      }

      // take board
      // copy its ships and their location
      // add them to the board as not visible
    }
}
