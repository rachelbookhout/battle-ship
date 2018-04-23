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
   * This sets the player board of the computer as our tracking board
   * @param board, PlayerBoard of the computer
  */
  public void setPlayerBoard(PlayerBoard board){
    playerBoard = board;
  }

  /**
   * This connects us to the dialogueBox within BattleShipGame.java
   * @param box, JTextArea
  */
  public void getDialogueBoxForGame(JTextArea box){
    dialogueBox = box;
  }

  /**
   * This sets the player board of the playng player so we display its UI
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

  /**
   * This method decides the display of the board when a user clicks on a particular square
   * it checks if a ship was hit or not
   * @param button - JButton, the button the player pressed to bomb
  */
  public void decideShipFate(JButton button){
    // get the text off of the button which will tell us which one was pressed
    String text = button.getText();
    int index = text.indexOf(',');
    int firstNumber = Integer.parseInt(text.substring(0,index));
    int secondNumber = Integer.parseInt(text.substring(index + 1));
    // convert it to coordinates
    int[] location = {firstNumber, secondNumber};
    // the Hashmap was the best data structure to group together the result of having a Ship
    // and what kind it was. I would have preferred a Tuple but they aren't in Java?
    HashMap<Boolean,Ship> isShipInLocation = checkShipLocation(location, playerBoard);
    // grab our board so we can change the display of location
    JButton[][] board = getBoard();
    // if there is a ship, make that box say "H", color it based on which ship it is
    // and remove the action listener
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
    // if there wasn't a ship, make that box with an x
    // then remove the action listener
    else{
      board[secondNumber][firstNumber].setFont(new Font("Arial", Font.BOLD, 20));
      board[secondNumber][firstNumber].setForeground(Color.BLACK);
      board[secondNumber][firstNumber].setText("X");
      board[secondNumber][firstNumber].removeActionListener(bombShip);
    }
  }

  /**
   * This method checks to see if on PlayerBoard, there is a ship at coordinate
   * @param coord, int[], our location that the user clicked
   * @param board, PlayerBoard, the board we clicked upon that has Ships on it
   * @return shipLocation, HashMap<Boolean,Ship>, contains the ship that is at coordinates or returns nothing
  */
  private HashMap<Boolean,Ship> checkShipLocation(int[] coord, PlayerBoard board){
    HashMap<Boolean,Ship> shipLocation = new HashMap<Boolean,Ship>();
    // grab our array of ships from our player board
    ArrayList<Ship> ships = board.getShipsOnBoard();
    // iterate through each ship and their location coordinates
    for(Ship ship: ships){
      ArrayList<int[]> locations = ship.getLocation();
       for(int[] location : locations){
          if(Arrays.equals(location, coord)){
            shipLocation.put(true,ship);
            return shipLocation;
          }
       }
     }
     return shipLocation;
  }

  /**
   * This method allows the computer to make moves with bombing the player's board
   * it chooses coordinates randomly. Operates under the same logic as decideShipFate()
  */
  private void makeComputerMove(){
    Random rand = new Random();
    int xCoor = rand.nextInt(5);
    int yCoor = rand.nextInt(5);
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

  /**
   * This method checks to see if a user is won and allows the game to end
  */
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


   /**
   * This class implements the action listener for a user clicking on a square
   * on the board. It bombs that square, checks if we won. If no, lets the computer play
   * then checks if they won.
  */
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
