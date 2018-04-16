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
    GameBoardConnector connector = new GameBoardConnector();
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
    createShipButtons();
  }

  private void createShipButtons(){
    dialogueBox.setText("Click a button to choose the ship you want to place");
    JButton carrierButton = new JButton("Carrier");
    JButton cruiserButton = new JButton("Cruiser");
    JButton submarineButton = new JButton("Submarine");
    JButton battleShipButton = new JButton("BattleShip");
    JButton destroyerButton = new JButton("Destroyer");
    carrierButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        Carrier carrier = new Carrier();
        playerBoard.connectShipToBoard(carrier);
        carrierButton.setVisible(false);
      }
    });
    cruiserButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        Cruiser cruiser = new Cruiser();
        playerBoard.connectShipToBoard(cruiser);
        cruiserButton.setVisible(false);
      }
    });
    submarineButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        Submarine sub = new Submarine();
        playerBoard.connectShipToBoard(sub);
        submarineButton.setVisible(false);
      }
    });
    battleShipButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        BattleShip bs = new BattleShip();
        playerBoard.connectShipToBoard(bs);
        battleShipButton.setVisible(false);
      }
    });
    destroyerButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        Destroyer destroyer = new Destroyer();
        playerBoard.connectShipToBoard(destroyer);
        destroyerButton.setVisible(false);
      }
    });
    communicationPanel.add(carrierButton);
    communicationPanel.add(cruiserButton);
    communicationPanel.add(submarineButton);
    communicationPanel.add(battleShipButton);
    communicationPanel.add(destroyerButton);
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


