import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TrackingBoard extends Board {
    public TrackingBoard(int dimensions){
        super(dimensions);
        addEvents(dimensions);
    }

    private void addEvents(int dimensions){
      JButton[][] board = getBoard();
      for (int o = 0; o < dimensions; o++) {
        for (int i = 0; i < dimensions; i++) {
          board[o][i].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
              decideShipFate((JButton) ae.getSource());
              }
            });
        }
      }
    }

    private void decideShipFate(JButton button){
      // add ship
    }
}
