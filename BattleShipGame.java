import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class BattleShipGame{

  private PlayerBoard playerBoard;
  private PlayerBoard computerBoard;
  private TrackingBoard playerTrackingBoard;
  private TrackingBoard computerTrackingBoard;
  private int dimensions = 6;

  public static void main(String [] args) {
    BattleShipGame game = new BattleShipGame();
  }

  public BattleShipGame() {
    createGameBoard();
    createComputerPlayer();
    setUpShips();
    playGame();
  }

  private void createGameBoard(){
    JFrame frame = new JFrame("BattleShip");
    frame.setLayout( new BorderLayout( 100, 100 ));
    frame.setSize (1000, 1000);
    PlayerBoard playerBoard = new PlayerBoard(dimensions);
    TrackingBoard playerTrackingBoard = new TrackingBoard(dimensions);
    PlayerBoard computerBoard = new PlayerBoard(dimensions);
    TrackingBoard computerTrackingBoard = new TrackingBoard(dimensions);
    JTextArea dialogueBox = new JTextArea();
    dialogueBox.setSize(245, 100);
    dialogueBox.setLayout(new BoxLayout(dialogueBox, BoxLayout.X_AXIS));
    dialogueBox.setFont(new Font("Serif", Font.BOLD, 40));
    dialogueBox.setText("Let's play Battleship!");
    dialogueBox.setEditable(false);
    frame.add (playerBoard,BorderLayout.WEST);
    frame.add(playerTrackingBoard,BorderLayout.EAST);
    frame.add(dialogueBox, BorderLayout.SOUTH);
    frame.setVisible (true);
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  }

  private void createComputerPlayer(){

  }

  private void setUpShips(){

  }

  private void playGame(){

  }

}
