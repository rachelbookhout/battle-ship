import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class PlayerBoard extends Board {
    public Ship tempShip;
    PlaceShip ps = new PlaceShip();

    public PlayerBoard(int dimensions){
        super(dimensions);
        addEvents(dimensions);
    }

    private void addEvents(int dimensions){
      JButton[][] board = getBoard();
      for (int o = 0; o < dimensions; o++) {
        for (int i = 0; i < dimensions; i++) {
          board[o][i].setVisible(true);
          board[o][i].addActionListener(ps);
        }
      }
    }

    public void setTempShip(Ship ship){
      tempShip = ship;
    }

    // this needs to be more hard -> can't survive if you go over the
    // num of tiles entered, can click when the done button isn't up
    private void addShip(JButton button, Ship ship){
      String text = button.getText();
      int index = text.indexOf(',');
      int firstNumber = Integer.parseInt(text.substring(0,index));
      int secondNumber = Integer.parseInt(text.substring(index + 1));
      int[] location = {firstNumber, secondNumber};
      ship.setLocation(location);
      JButton[][] board = getBoard();
      board[secondNumber][firstNumber].setForeground(ship.getColor());
      board[secondNumber][firstNumber].setBackground(ship.getColor());
      board[secondNumber][firstNumber].setOpaque(true);
      if(checkLocation(ship)){
        ship.setStatus(Ship.Status.PLACED);
        for(int[] s : ship.getLocation()){
          board[s[1]][s[0]].removeActionListener(ps);
          board[s[1]][s[0]].setText("S");
        }

      };
    }

    private boolean checkLocation(Ship ship){
      if (ship.getLocation().size() >= ship.getShotsToSink()){
        ArrayList<int[]> locations = ship.getLocation();
        // borrowed from https://stackoverflow.com/questions/19596950/sort-an-arraylist-of-integer-arrays
        // sort location array for horizontal
        Collections.sort(locations, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        // if all slots are in the same row
        //[0,0][1,0][2,0][3,0]
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
        // if all slots are vertical to each other
        //[0,0][0,1],[0,2][0,3]
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

    private class PlaceShip implements ActionListener{
      public void actionPerformed(ActionEvent ae){
        if(getStatus() == Status.READY_FOR_PLACEMENT){
          addShip((JButton) ae.getSource(), tempShip);
        }
      }
    }
}
