import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PlayerBoard extends Board {
    public PlayerBoard(int dimensions){
        super(dimensions);
        addEvents(dimensions);
    }

    private void addEvents(int dimensions){
      JButton[][] board = getBoard();
      for (int o = 0; o < dimensions; o++) {
        for (int i = 0; i < dimensions; i++) {
          board[o][i].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
              addShip((JButton) ae.getSource());
              }
            });
        }
      }
    }

    private void addShip(JButton button){
      // add ship
    }
}
