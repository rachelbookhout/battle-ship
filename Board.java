import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public abstract class Board extends JPanel{

  protected int dimensions = 10;
  public JButton[][] jbtnBoard = new JButton[dimensions][dimensions];

  public Board(int dimensions){
    this.dimensions = dimensions;
    createBoard(dimensions);
  }

  private JButton[][] createBoard(int size){
    int count = 1;
    this.setLayout( new GridLayout(dimensions, dimensions));
    for (int o = 0; o < size; o++) {
      for (int i = 0; i < size; i++) {
        jbtnBoard[o][i] = new JButton(Integer.toString(count));
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
}
