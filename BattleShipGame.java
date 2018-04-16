import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


class BattleShipGame{

  public PlayerBoard playerBoard;
  private PlayerBoard computerBoard;
  private TrackingBoard playerTrackingBoard;
  private TrackingBoard computerTrackingBoard;
  private EasyPlayer computerPlayer;
  private GameBoardConnector connector;

  private int dimensions = 6;
  JTextArea dialogueBox = new JTextArea();
  JButton readytoPlayButton = new JButton("Play!");
  JPanel communicationPanel = new JPanel();
      JButton carrierButton = new JButton("Carrier");
    JButton cruiserButton = new JButton("Cruiser");
    JButton submarineButton = new JButton("Submarine");
    JButton battleShipButton = new JButton("BattleShip");
    JButton destroyerButton = new JButton("Destroyer");

  public static void main(String [] args) {
    BattleShipGame game = new BattleShipGame();
  }

  public BattleShipGame() {
    createGameBoard();
    createComputerPlayer();
    playGame();
  }

  private void createGameBoard(){
    JFrame frame = new JFrame("BattleShip");
    frame.setLayout( new BorderLayout( 100, 100 ));
    frame.setSize (1000, 1000);
    playerBoard = new PlayerBoard(dimensions);
    playerTrackingBoard = new TrackingBoard(dimensions);
    computerBoard = new PlayerBoard(dimensions);
    computerTrackingBoard = new TrackingBoard(dimensions);
    GameBoardConnector connector = new GameBoardConnector(playerBoard);
    readytoPlayButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        setUpShips();
      }
    });
    dialogueBox.setSize(245, 100);
    //dialogueBox.setLayout(new BoxLayout(dialogueBox, BoxLayout.X_AXIS));
    dialogueBox.setFont(new Font("Serif", Font.BOLD, 20));
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

  private void createComputerPlayer(){
    EasyPlayer computerPlayer = new EasyPlayer();
  }

  private void setUpShips(){
    readytoPlayButton.setVisible(false);
    dialogueBox.setText("Click a button to choose the ship you want to place");
    createShipButtons();
  }

  private void createShipButtons(){
    carrierButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
        Carrier carrier = new Carrier();
        playerBoard.connectShipToBoard(carrier);
        changeDisplayOnButton(false);
        addShipToBoard(carrier);
        //changeDisplayOnButton(true);
      }
    });
    cruiserButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
        Cruiser cruiser = new Cruiser();
        playerBoard.connectShipToBoard(cruiser);
        changeDisplayOnButton(false);
        addShipToBoard(cruiser);
        //changeDisplayOnButton(true);
      }
    });
    submarineButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
        Submarine sub = new Submarine();
        playerBoard.connectShipToBoard(sub);
        changeDisplayOnButton(false);
        addShipToBoard(sub);
        //changeDisplayOnButton(true);
      }
    });
    battleShipButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
        BattleShip bs = new BattleShip();
        playerBoard.connectShipToBoard(bs);
        changeDisplayOnButton(false);
        addShipToBoard(bs);
        //changeDisplayOnButton(true);
      }
    });
    destroyerButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
        Destroyer destroyer = new Destroyer();
        playerBoard.connectShipToBoard(destroyer);
        changeDisplayOnButton(false);
        addShipToBoard(destroyer);
        //changeDisplayOnButton(true);
      }
    });
    communicationPanel.add(carrierButton);
    communicationPanel.add(cruiserButton);
    communicationPanel.add(submarineButton);
    communicationPanel.add(battleShipButton);
    communicationPanel.add(destroyerButton);
  }

  private void changeDisplayOnButton(boolean state){
    carrierButton.setVisible(state);
    destroyerButton.setVisible(state);
    battleShipButton.setVisible(state);
    submarineButton.setVisible(state);
    cruiserButton.setVisible(state);
  }

  private void addShipToBoard(Ship ship){
    // write text prompt out
    playerBoard.setTempShip(ship);
    dialogueBox.setText("Pick location to place");
    // save ship to temp ship within board class
  }

  private void playGame(){

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


