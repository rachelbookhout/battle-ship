import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


class BattleShipGame{

  public PlayerBoard playerBoard;
  private PlayerBoard computerBoard;
  private TrackingBoard playerTrackingBoard;
  private Game game;
  //private TrackingBoard computerTrackingBoard;
  //private EasyPlayer computerPlayer;

  private int dimensions = 6;
  //JButton readytoPlayButton = new JButton("Play!");
  JPanel communicationPanel = new JPanel();
  //JButton carrierButton = new JButton("Carrier");
  //JButton cruiserButton = new JButton("Cruiser");
  //JButton submarineButton = new JButton("Submarine");
  //JButton battleShipButton = new JButton("BattleShip");
  //JButton destroyerButton = new JButton("Destroyer");
  BattleShipBackEnd backEnd = new BattleShipBackEnd();

  public static void main(String [] args) {
    BattleShipGame game = new BattleShipGame();
  }

  public BattleShipGame() {
    createGameBoard();
    //createComputerPlayer();
    playGame();
  }

  private void createGameBoard(){
    JFrame frame = new JFrame("BattleShip");
    frame.setLayout( new BorderLayout( 100, 100 ));
    frame.setSize (1000, 1000);
    playerBoard = new PlayerBoard(dimensions);
    playerTrackingBoard = new TrackingBoard(dimensions);
    game = new Game();
    //computerBoard = new PlayerBoard(dimensions);
    //computerTrackingBoard = new TrackingBoard(dimensions);
    JTextArea dialogueBox = new JTextArea();
    dialogueBox.setSize(245, 100);
    dialogueBox.setFont(new Font("Serif", Font.BOLD, 20));
    //dialogueBox.setText("Hi");
    dialogueBox.setEditable(false);
    //communicationPanel.add(dialogueBox);
    //communicationPanel.add(readytoPlayButton);
    frame.add (playerBoard,BorderLayout.WEST);
    frame.add(playerTrackingBoard,BorderLayout.EAST);
    frame.add(communicationPanel, BorderLayout.SOUTH);
    frame.setVisible (true);
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  }

  //private void createComputerPlayer(){
    //EasyPlayer computerPlayer = new EasyPlayer();
  //}

 // private void setUpShips(){
   // readytoPlayButton.setVisible(false);
    //dialogueBox.setText("Click a button to choose the ship you want to place");
    //createShipButtons();
  //}

  // private void createShipButtons(){
  //   carrierButton.addActionListener(new ActionListener(){
  //     public void actionPerformed(ActionEvent ae){
  //       playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
  //       Carrier carrier = new Carrier();
  //       playerBoard.connectShipToBoard(carrier);
  //       changeDisplayOnAllButtons(false);
  //       addShipToBoard(carrier);
  //     }
  //   });
  //   cruiserButton.addActionListener(new ActionListener(){
  //     public void actionPerformed(ActionEvent ae){
  //       playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
  //       Cruiser cruiser = new Cruiser();
  //       playerBoard.connectShipToBoard(cruiser);
  //       changeDisplayOnAllButtons(false);
  //       addShipToBoard(cruiser);
  //       System.out.println(cruiser.getStatus());
  //     }
  //   });
  //   submarineButton.addActionListener(new ActionListener(){
  //     public void actionPerformed(ActionEvent ae){
  //       playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
  //       Submarine sub = new Submarine();
  //       playerBoard.connectShipToBoard(sub);
  //       changeDisplayOnAllButtons(false);
  //       addShipToBoard(sub);
  //     }
  //   });
  //   battleShipButton.addActionListener(new ActionListener(){
  //     public void actionPerformed(ActionEvent ae){
  //       playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
  //       BattleShip bs = new BattleShip();
  //       playerBoard.connectShipToBoard(bs);
  //       changeDisplayOnAllButtons(false);
  //       addShipToBoard(bs);
  //     }
  //   });
  //   destroyerButton.addActionListener(new ActionListener(){
  //     public void actionPerformed(ActionEvent ae){
  //       playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
  //       Destroyer destroyer = new Destroyer();
  //       playerBoard.connectShipToBoard(destroyer);
  //       changeDisplayOnAllButtons(false);
  //       addShipToBoard(destroyer);
  //     }
  //   });
  //   communicationPanel.add(carrierButton);
  //   communicationPanel.add(cruiserButton);
  //   communicationPanel.add(submarineButton);
  //   communicationPanel.add(battleShipButton);
  //   communicationPanel.add(destroyerButton);
  // }

  // private void changeDisplayOnAllButtons(boolean state){
  //   carrierButton.setVisible(state);
  //   destroyerButton.setVisible(state);
  //   battleShipButton.setVisible(state);
  //   submarineButton.setVisible(state);
  //   cruiserButton.setVisible(state);
  // }

  //private void addShipToBoard(Ship ship){
    // write text prompt out
    //playerBoard.setTempShip(ship);
    //dialogueBox.setText("Pick your location to place the ship. Remember the ship uses " +
    //ship.numOfShotsToSink + "boxes");
  //}

  private void playGame(){
    //while (!hasWon()){
      Game.Status status = game.getStatus();
      JButton readyToPlay = game.getButton(Game.Status.NOT_STARTED);
      if(status == Game.Status.NOT_STARTED){
        // show ready to play button
        communicationPanel.add(readyToPlay);
        // display the boards without any action listeners
      }
      else if(status == Game.Status.PLACE_SHIPS){
        //diplay button for each ship
        System.out.println("PLACE SHOPS");
        communicationPanel.remove(readyToPlay);
        // display text to click a number of buttons
        // that will add to a temp array
        // we will pass it back
        // and then add the ship
        // once that is done, send out the next button
      }
      else if(status == Game.Status.SET_OPPONENT){
        // create computer player
        // create computer's player board
        // create both user's tracking board based on the player board
      }
      else if(status == Game.Status.STARTED){

      }
    //}
  }

  private boolean hasWon(){
    return false;
  }

  // private class LinkShipToBoard implements ActionListener{

  // public void actionPerformed(ActionEvent ae) {
  //     JButton button =  (JButton)ae.getSource();
  //     button.setVisible(false);
  //     //setTempShip(button.getText());
  //     //dialogueBox.setText("Pick the spot you want to place your ship");
  // }


  //   // figure out what ship we are looking at
  //   // save it to variable
  //   // send it to the action clicker for clicking
  // }
}


