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

  public ShipCounter(ArrayList<Ship> ships){
    this.ships = ships;
    createDisplay(ships);
  }

  private void createDisplay(ArrayList<Ship> ships){
    //this.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
    for(Ship ship: ships){
      JPanel panel = new JPanel();
      JLabel name = new JLabel(ship.name);
      String hits = ship.hits + "hits out of " + ship.numOfShotsToSink;
      // display Ship name , hits and numOfShotsToSink
      JLabel info = new JLabel(hits);
      panel.add(name);
      panel.add(info);
      this.add(panel);
    }
    this.setVisible(true);
  }

  public JPanel getDisplay(){
    return this;
  }

  public JPanel updateDisplay(ArrayList<Ship> ships){
     createDisplay(ships);
     return getDisplay();
  }
}
