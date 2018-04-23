/**
 * This class inherits from the Board class and is designed to track the status of the game
 * in terms of what squares have been bombed, were ships hit, etc.
 * @author Rachel Bookhout
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Random;

public class TrackingBoard extends Board {
  // these two instances of PlayerBoard represent
  //the bords that were set up by our players
  private PlayerBoard playerBoard;
  private PlayerBoard opposingBoard;
  // this is our action listener for a user clicking a box
  BombShip bombShip = new BombShip();
  // this is to connect us to the BattleShipGame class in terms of displaying dialogue
  JTextArea dialogueBox;

  /**
   * This constructor is to create our board and takes everything from the Board class
   * @param dimensions, integer of how big we make the board
  */
  public TrackingBoard(int dimensions){
    super(dimensions);
    addEvents(dimensions);
  }

  /**
   * This constructor sets the player board of the computer as our tracking board
   * @param board, PlayerBoard of the computer
  */
  public void setPlayerBoard(PlayerBoard board){
    playerBoard = board;
  }

  /**
   * This constructor connects us to the dialogueBox within BattleShipGame.java
   * @param box, JTextArea
  */
  public void getDialogueBoxForGame(JTextArea box){
    dialogueBox = box;
  }

  /**
   * This constructor sets the player board of the playng player so we display its UI
   * @param board, PlayerBoard of the player playing
  */
  public void setOpponentBoard(PlayerBoard board){
    opposingBoard = board;
  }

  /**
   * This methods adds the bombShip action listener to each cell
   * @param dimensions, integer to tell us how big the JButton array is so we can iterate through it
  */
  public void addEvents(int dimensions){
    JButton[][] board = getBoard();
    for (int o = 0; o < dimensions; o++) {
      for (int i = 0; i < dimensions; i++) {
        board[o][i].setVisible(true);
        // HACK: it was getting called twice but was unable to find the second caller
        // so we are deleting it so we only have the one we added
        board[o][i].removeActionListener(bombShip);
        board[o][i].addActionListener(bombShip);
      }
    }
  }

  public void decideShipFate(JButton button){
    String text = button.getText();
    System.out.println("Text is " + text);
    int index = text.indexOf(',');
    int firstNumber = Integer.parseInt(text.substring(0,index));
    int secondNumber = Integer.parseInt(text.substring(index + 1));
    System.out.println("First number:" + firstNumber + "secondNumber" + secondNumber);
    int[] location = {firstNumber, secondNumber};
    System.out.println(firstNumber + "," + secondNumber);
    HashMap<Boolean,Ship> isShipInLocation = checkShipLocation(location, playerBoard);
    System.out.println("Is Location a Ship?" + isShipInLocation);
    JButton[][] board = getBoard();
    if(!isShipInLocation.isEmpty()){
      board[secondNumber][firstNumber].setFont(new Font("Arial", Font.BOLD, 20));
      board[secondNumber][firstNumber].setForeground(Color.BLACK);
      board[secondNumber][firstNumber].setText("H");
      playerBoard.setHitsLeft(playerBoard.getHitsLeft() - 1);
      board[secondNumber][firstNumber].setForeground(isShipInLocation.get(true).getColor());
      board[secondNumber][firstNumber].setBackground(isShipInLocation.get(true).getColor());
      board[secondNumber][firstNumber].setOpaque(true);
      board[secondNumber][firstNumber].removeActionListener(bombShip);
    }
    else{
      board[secondNumber][firstNumber].setFont(new Font("Arial", Font.BOLD, 20));
      board[secondNumber][firstNumber].setForeground(Color.BLACK);
      board[secondNumber][firstNumber].setText("X");
      board[secondNumber][firstNumber].removeActionListener(bombShip);
    }
  }

  private HashMap<Boolean,Ship> checkShipLocation(int[] coord, PlayerBoard board){
    System.out.println("in ship location");
    HashMap<Boolean,Ship> shipLocation = new HashMap<Boolean,Ship>();
    ArrayList<Ship> ships = board.getShipsOnBoard();
    for(Ship ship: ships){
      ArrayList<int[]> locations = ship.getLocation();
       for(int[] location : locations){
             System.out.println("location:" + location[0] + "," + location[1]);
            if(Arrays.equals(location, coord)){
              shipLocation.put(true,ship);
              return shipLocation;
            }
       }
     }
     return shipLocation;
  }

  private void makeComputerMove(){
     Random rand = new Random();
     int xCoor = rand.nextInt(5);
     int yCoor = rand.nextInt(5);
     System.out.println("XCor" + xCoor + " yCoor" + yCoor);
     int[] location = new int[]{xCoor,yCoor};
     HashMap<Boolean, Ship> isShip = checkShipLocation(location,opposingBoard);
    JButton[][] board = opposingBoard.getBoard();
    if(!isShip.isEmpty()){
      board[yCoor][xCoor].setFont(new Font("Arial", Font.BOLD, 20));
      board[yCoor][xCoor].setText("H");
      opposingBoard.setHitsLeft(opposingBoard.getHitsLeft() - 1);
    }
    else{
      board[yCoor][xCoor].setFont(new Font("Arial", Font.BOLD, 20));
      board[yCoor][xCoor].setText("X");
    }
  }

  private boolean hasWon(boolean isComputerPlayer){
    if(isComputerPlayer){
      if(opposingBoard.getHitsLeft() == 0){
        return true;
      }
      return false;
    }
    else{
      if(playerBoard.getHitsLeft() == 0){
        return true;
      }
      return false;
    }
  }

  private class BombShip implements ActionListener{
    public void actionPerformed(ActionEvent ae){
            decideShipFate((JButton) ae.getSource());
            if(!hasWon(false)){
              dialogueBox.setText("Bombs Away, Player 2");
              makeComputerMove();
              if(!hasWon(true)){
                dialogueBox.setText("Bombs Away, Player 1!");
              }
              else{
                dialogueBox.setText("You lost!. Game over.");
              }
            }
            else{
              dialogueBox.setText("You won! Game done");
              JButton[][] board = playerBoard.getBoard();
              for (int o = 0; o < dimensions; o++) {
                for (int i = 0; i < dimensions; i++) {
                  board[o][i].removeActionListener(bombShip);
                }
              }
            }

    }

  }

}
