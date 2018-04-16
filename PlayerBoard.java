import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class PlayerBoard extends Board {
    public Ship tempShip;
    public PlayerBoard(int dimensions){
        super(dimensions);
        addEvents(dimensions);
    }

    private void addEvents(int dimensions){
      JButton[][] board = getBoard();
      for (int o = 0; o < dimensions; o++) {
        for (int i = 0; i < dimensions; i++) {
          board[o][i].setVisible(true);
          board[o][i].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(getStatus() == Status.READY_FOR_PLACEMENT){
                  addShip((JButton) ae.getSource(), tempShip);
                }
              }
            });
        }
      }
    }

    public void setTempShip(Ship ship){
      tempShip = ship;
    }

    private void addShip(JButton button, Ship ship){
      System.out.println("Adding ship");
      String text = button.getText();
      int index = text.indexOf(',');
      int firstNumber = Integer.parseInt(text.substring(0,index));
      int secondNumber = Integer.parseInt(text.substring(index + 1));
      int[] location = {firstNumber, secondNumber};
      ship.setLocation(location);
      JButton[][] board = getBoard();
      board[firstNumber][secondNumber].setBackground(ship.color);
      board[firstNumber][secondNumber].setOpaque(true);
    }
}
