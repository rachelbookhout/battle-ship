/**
 * This class is to represent the UI counter of the status of the ships for each player
 * @author Rachel Bookhout
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ShipCounter extends JPanel{
  private ArrayList<Ship> ships;

   /**
   * This constructor is passed in the Ships that we are displaying info for
   * Then creates the UI
   * @param ships, ArrayList<Ship>, ships belonging to the PlayerBoard
  */
  public ShipCounter(ArrayList<Ship> ships){
    this.ships = ships;
    createDisplay(ships);
  }

  /**
   * This creates our display
   * @param ships, ArrayList<Ship>, ships belonging to the PlayerBoard
  */
  private void createDisplay(ArrayList<Ship> ships){
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    for(Ship ship: ships){
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      JLabel name = new JLabel(ship.getName());
      String hits = ":  " + ship.hits + " hits out of " + ship.numOfShotsToSink;
      JLabel info = new JLabel(hits);
      panel.add(name);
      panel.add(info);
      this.add(panel);
    }
    this.setVisible(true);
  }

  /**
   * This grabs our display
   * @return this, JPanel which is our display
  */
  public JPanel getDisplay(){
    return this;
  }

  /**
   * This clears our display so we can reprint it
  */
  private void clearDisplay(){
    this.removeAll();
  }

  /**
   * This clears our display then reprints it
   * @param ships, ArrayList<Ship>, ships belonging to the PlayerBoard
  */
  public void updateDisplay(ArrayList<Ship> ships){
     clearDisplay();
     createDisplay(ships);
  }
}
