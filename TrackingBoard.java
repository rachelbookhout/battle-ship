import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Random;

public class TrackingBoard extends Board {
    private PlayerBoard playerBoard;
    private PlayerBoard opposingBoard;
    BombShip bombShip = new BombShip();
    JTextArea dialogueBox;
    public TrackingBoard(int dimensions){
        super(dimensions);
        //addEvents(dimensions);
    }

    public void setPlayerBoard(PlayerBoard board){
      playerBoard = board;
    }

    public void getDialogueBoxForGame(JTextArea box){
      dialogueBox = box;
    }

    public void setOpponentBoard(PlayerBoard board){
      opposingBoard = board;
    }

    public void addEvents(int dimensions){
      JButton[][] board = getBoard();
      for (int o = 0; o < dimensions; o++) {
        for (int i = 0; i < dimensions; i++) {
          board[o][i].setVisible(true);
          // needs to be removed, can't find the 2nd call
          board[o][i].removeActionListener(bombShip);
          board[o][i].addActionListener(bombShip);
        }
      }
    }

    public void decideShipFate(JButton button){
      // get the button location
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
      //board[secondNumber][firstNumber].setForeground(ship.getColor());
      // check if ship is on it (Check locations array)
      // if ship is not on it, change the display to an X
      // if is, add hit to Ship and display "H" with highlight
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

    // public void createTrackingBoard(PlayerBoard playerBoard){
    //   JButton[][] board = getBoard();
    //   ArrayList<Ship> ships = playerBoard.getShipsOnBoard();
    //   for(Ship ship: ships){
    //     ArrayList<int[]> locations = ship.getLocation();
    //     for(int[] location : locations){
    //           //board[location[1]][location[0]].setText("S");
    //           board[location[1]][location[0]].setForeground(ship.getColor());
    //           board[location[1]][location[0]].setBackground(ship.getColor());
    //           board[location[1]][location[0]].setOpaque(true);
    //     }
    //   }
    // }

      // take board
      // copy its ships and their location
      // add them to the board as not visible
    //}
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
              // let it become computer's turn
              // computer makes a move
              // display it
              }
              else{
                dialogueBox.setText("You won! Game done");
                JButton[][] board = playerBoard.getBoard();
                for (int o = 0; o < dimensions; o++) {
                  for (int i = 0; i < dimensions; i++) {
                    // needs to be removed, can't find the 2nd call
                    board[o][i].removeActionListener(bombShip);
                  }
                }
              }

      }

    }

}
