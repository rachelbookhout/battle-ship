/**
 * This class is the entry point into the BattleShipGame and has
 * the main method which it runs from. Its job is to set up the board/game
 * @author Rachel Bookhout
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


class BattleShipGame{

  // variables to represent all of the boards in BattleShip
  public PlayerBoard playerBoard;
  private PlayerBoard computerBoard;
  private TrackingBoard playerTrackingBoard;
  private TrackingBoard computerTrackingBoard;

  private int dimensions = 6;

  // intializing our buttons here so all methods have access to them
  JTextArea dialogueBox = new JTextArea();
  JButton readytoPlayButton = new JButton("Play!");
  JButton clearButton = new JButton("Clear");
  JPanel communicationPanel = new JPanel();
  JButton carrierButton = new JButton("Carrier");
  JButton cruiserButton = new JButton("Cruiser");
  JButton submarineButton = new JButton("Submarine");
  JButton battleShipButton = new JButton("BattleShip");
  JButton destroyerButton = new JButton("Destroyer");
  JButton doneButton = new JButton("Done");
  JButton finalizeBoardButton = new JButton("GO!!!");
  JButton startGameButton = new JButton("Start Game");

  // creates the game and gets it started
  public static void main(String [] args) {
    BattleShipGame game = new BattleShipGame();
  }

  /**
   * This constructor creates everything we need to start the game (UI-wise)
   * and creates everything we need to play the game once ships are chosen
  */
  public BattleShipGame() {
    createGameBoard();
  }

  /**
   * This method creates the UI and also creates our boards for our players
  */
  private void createGameBoard(){
    JFrame frame = new JFrame("BattleShip");
    frame.setLayout( new BorderLayout( 100, 100 ));
    frame.setSize (1000, 1000);
    // create the boards
    playerBoard = new PlayerBoard(dimensions);
    playerTrackingBoard = new TrackingBoard(dimensions);
    computerBoard = new PlayerBoard(dimensions);
    computerTrackingBoard = new TrackingBoard(dimensions);
    // this is the button that will need to be pressed to start the game
    readytoPlayButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        setUpShips();
      }
    });
    dialogueBox.setSize(245, 100);
    dialogueBox.setFont(new Font("Serif", Font.BOLD, 18));
    dialogueBox.setText("Ready to Play?");
    dialogueBox.setEditable(false);
    communicationPanel.add(dialogueBox);
    communicationPanel.add(readytoPlayButton);
    frame.add (playerBoard,BorderLayout.WEST);
    frame.add(playerTrackingBoard,BorderLayout.EAST);
    frame.add(communicationPanel, BorderLayout.SOUTH);
    frame.setVisible (true);
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  }

  /**
   * This method transitions us to the UI that allows the user to pick where
   * on the board they would like to place their ships
  */
  private void setUpShips(){
    readytoPlayButton.setVisible(false);
    dialogueBox.setText("Click Carrier button to get started placing ships!");
    createShipButtons();
    communicationPanel.add(carrierButton);
    communicationPanel.add(clearButton);
  }

  /**
   * This method adds action listeners to each button that represents a ship
   * that will get added to the board. The flow is that one by one, a ship button will appear
   * once it is clicked, a user can choose the location they want their ship
   * then once they click done, it finalizes the location of the ship and the next button to
   * place the ship will appear
  */
  private void createShipButtons(){
    carrierButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        Carrier carrier = new Carrier();
        // this adds the ship to the ship array within the PlayerBoard class
        playerBoard.connectShipToBoard(carrier);
        // this connects the ship to the player board so we can do the checks on its location
        // as the user chooses it. It also changes the text.
        addShipToBoard(carrier);
        // disappear the carrier button
        carrierButton.setVisible(false);
        communicationPanel.remove(carrierButton);
        // add the done button which will transition us to the next round of adding a ship
        doneButton.addActionListener(new FinalizeShipListener(carrier,cruiserButton,communicationPanel, doneButton));
        clearButton.setVisible(true);
        doneButton.setVisible(true);
        communicationPanel.add(doneButton);
      }
    });
    cruiserButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        // create a Cruiser and attach it to our player board
        Cruiser cruiser = new Cruiser();
        playerBoard.connectShipToBoard(cruiser);
        addShipToBoard(cruiser);
        cruiserButton.setVisible(false);
        communicationPanel.remove(cruiserButton);
        doneButton.addActionListener(new FinalizeShipListener(cruiser,submarineButton,communicationPanel, doneButton));
        clearButton.setVisible(true);
        doneButton.setVisible(true);
        playerBoard.addEvents(dimensions);
        communicationPanel.add(doneButton);
      }
    });
    submarineButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        // create a Submarine and attach it to the PlayerBoard
        Submarine sub = new Submarine();
        playerBoard.connectShipToBoard(sub);
        addShipToBoard(sub);
        submarineButton.setVisible(false);
        communicationPanel.remove(submarineButton);
        doneButton.addActionListener(new FinalizeShipListener(sub,battleShipButton,communicationPanel, doneButton));
        clearButton.setVisible(true);
        playerBoard.addEvents(dimensions);
        doneButton.setVisible(true);
        communicationPanel.add(doneButton);
      }
    });
    battleShipButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        // create a BattleShip and attach it to the PlayerBoard
        BattleShip bs = new BattleShip();
        playerBoard.connectShipToBoard(bs);
        addShipToBoard(bs);
        battleShipButton.setVisible(false);
        communicationPanel.remove(battleShipButton);
        playerBoard.addEvents(dimensions);
        doneButton.addActionListener(new FinalizeShipListener(bs, destroyerButton,communicationPanel, doneButton));
        clearButton.setVisible(true);
        doneButton.setVisible(true);
        communicationPanel.add(doneButton);
      }
    });
    destroyerButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        // create a destroyer and attach it to our PlayerBoard
        Destroyer destroyer = new Destroyer();
        playerBoard.connectShipToBoard(destroyer);
        addShipToBoard(destroyer);
        destroyerButton.setVisible(false);
        communicationPanel.remove(destroyerButton);
        // the done button will transition us to a finalize button instead of another ship
        // since this is the last ship to be placed
        doneButton.addActionListener(new FinalizeShipListener(destroyer,finalizeBoardButton,communicationPanel, doneButton));
        playerBoard.addEvents(dimensions);
        clearButton.setVisible(true);
        doneButton.setVisible(true);
        communicationPanel.add(doneButton);
      }
    });
    finalizeBoardButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          // remove all action listeners from player board
          playerBoard.removeEvents();
          // create computer board with random placements
          computerBoard.setShipPlacements();
          // make our button not visible
          finalizeBoardButton.setVisible(false);
          communicationPanel.remove(dialogueBox);
          // start the game
          playGame();
        }
      });
      clearButton.addActionListener(new RemoveShipLocation());
  }

  /**
   * This method give the PlayerBoard access to the ship we are trying to place
   * and prints out dialogue to make sense of what we need
  */
  private void addShipToBoard(Ship ship){
    playerBoard.setTempShip(ship);
    dialogueBox.setText("Pick your location to place the ship. Remember the ship uses " +
    ship.numOfShotsToSink + " boxes. Click done when you are finished");
  }

  /**
   * This method sets us up so the player and the computer can alterate moves
  */
  private void playGame(){
    // send the playerBoard and the computer's playerBoard to trackingBoard
    // so we can track what we need to do to them
    playerTrackingBoard.setPlayerBoard(computerBoard);
    computerBoard.resetHitsLeft();
    playerTrackingBoard.setOpponentBoard(playerBoard);
    playerTrackingBoard.addEvents(dimensions);
    playerTrackingBoard.getDialogueBoxForGame(dialogueBox);
    ShipCounter againstPlayer = new ShipCounter(playerBoard.getShipsOnBoard());
    ShipCounter forPlayer = new ShipCounter(computerBoard.getShipsOnBoard());
    playerTrackingBoard.setOpponentShipCounter(againstPlayer);
    playerTrackingBoard.setPlayerShipCounter(forPlayer);
    communicationPanel.add(againstPlayer.getDisplay());
    communicationPanel.add(dialogueBox);
    dialogueBox.setText("Player 1 - Bombs Away!");
    communicationPanel.add(forPlayer.getDisplay());
  }

  /**
   * This class is the action listener that exists on each Ship button
   * Its job is to switch out the buttons on the UI
  */
  private class FinalizeShipListener implements ActionListener {
    private Ship ship;
    private JButton nextButton;
    private JPanel panel;
    private JButton doneButton;
    private JButton current;

    /**
     * This constructor sets all the variables we need to work with from the BattleShipGame class
     * @param ship - Ship or a class that inherits from it. It's the ship that we are in placing
     * @param nextButton - JButton, the next Button we want to see in the display
     * @param panel - JPanel, the panel that we want to attach the button to
     * @param doneButton - JButton - our done Button which we need to disable at this point in the flow
    */
    public FinalizeShipListener(Ship ship, JButton nextButton, JPanel panel, JButton doneButton) {
        this.ship = ship;
        this.nextButton = nextButton;
        this.panel = panel;
        this.doneButton = doneButton;
    }
    /**
     * This method changes up the display
    */
    public void actionPerformed(ActionEvent e) {
      if(ship.getStatus() == Ship.Status.PLACED){
          doneButton.setVisible(false);
          clearButton.setVisible(false);
          panel.remove(doneButton);
          nextButton.setVisible(true);
          panel.add(nextButton);
          doneButton.removeActionListener(this);
          if(playerBoard.getShipsOnBoard().size() != 5){
            dialogueBox.setText("Pick your next ship");
          }
          else{
            dialogueBox.setText(" ");
          }
      }
    }
  }

    /**
   * This class is the action listener that exists on the remove button
   * it will clear the location array of whatever ship we are working with at the time
  */
  private class RemoveShipLocation implements ActionListener {
    /**
     * This removes the ship
    */
    public void actionPerformed(ActionEvent e) {
      playerBoard.removeShips();
    }
  }
}


