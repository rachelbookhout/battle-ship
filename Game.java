import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Game{

  enum Status { NOT_STARTED, PLACE_SHIPS, SET_OPPONENT, STARTED};
  protected Status status;

  public Game(){
    status = Status.NOT_STARTED;
  }

  public Status getStatus(){
    return status;
  }
  public void setStatus(Status newStatus){
    status = newStatus;
  }

  public String getDisplayValue(Status status){
      if(status == Game.Status.NOT_STARTED){
        // show ready to play button
        return "Click button to play!";
        // display the boards without any action listeners
      }
      else if(status == Game.Status.PLACE_SHIPS){
        //diplay button for each ship
        // display text to click a number of buttons
        // that will add to a temp array
        // we will pass it back
        // and then add the ship
        // once that is done, send out the next button
         return "Click button to play!";

      }
      else if(status == Game.Status.SET_OPPONENT){
        // create computer player
        // create computer's player board
        // create both user's tracking board based on the player board
                return "Click button to play!";

      }
      else if(status == Game.Status.STARTED){
                return "Click button to play!";

      }
      return "Nothing";
  };

  public JButton getButton(Status status){
       JButton fakeButton = new JButton("fake");
       if(status == Game.Status.NOT_STARTED){
        // show ready to play button
        JButton readyToPlayButton = new JButton("Ready to Play?");
        readyToPlayButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          setStatus(Game.Status.PLACE_SHIPS);
        }
        });
        return readyToPlayButton;
        // display the boards without any action listeners
      }
      else if(status == Game.Status.PLACE_SHIPS){
        //diplay button for each ship
        // display text to click a number of buttons
        // that will add to a temp array
        // we will pass it back
        // and then add the ship
        // once that is done, send out the next button
        return fakeButton;
      }
      else if(status == Game.Status.SET_OPPONENT){
        // create computer player
        // create computer's player board
        // create both user's tracking board based on the player board
        return fakeButton;

      }
      else if(status == Game.Status.STARTED){
          return fakeButton;
      }
      return fakeButton;
  }

  public void addActionListeners(JButton button){

  }
}
