import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class PlayerBoard extends Board {
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
                  addShip((JButton) ae.getSource());
                }
              }
            });
        }
      }
    }

    private void addShip(JButton button){
      System.out.println("Adding ship");
    }
}
