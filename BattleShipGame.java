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

  private int dimensions = 6;
  JTextArea dialogueBox = new JTextArea();
  JButton readytoPlayButton = new JButton("Play!");
  JPanel communicationPanel = new JPanel();
  JButton carrierButton = new JButton("Carrier");
  JButton cruiserButton = new JButton("Cruiser");
  JButton submarineButton = new JButton("Submarine");
  JButton battleShipButton = new JButton("BattleShip");
  JButton destroyerButton = new JButton("Destroyer");
  JButton doneButton = new JButton("Done");
  JButton finalizeBoardButton = new JButton("GO!!!");
  JButton startGameButton = new JButton("Start Game");
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
    readytoPlayButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        setUpShips();
      }
    });
    dialogueBox.setSize(245, 100);
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
    communicationPanel.add(carrierButton);
  }

  private void createShipButtons(){

    carrierButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
        Carrier carrier = new Carrier();
        playerBoard.connectShipToBoard(carrier);
        addShipToBoard(carrier);
        carrierButton.setVisible(false);
        communicationPanel.remove(carrierButton);
        doneButton.addActionListener(new FinalizeShipListener(carrier,cruiserButton,communicationPanel, doneButton));
        doneButton.setVisible(true);
        communicationPanel.add(doneButton);
        //if(carrier.getStatus() == Ship.Status.PLACED){
          //  communicationPanel.add(cruiserButton);
            //cruiserButton.setVisible(true);
        //}
      }
    });
    cruiserButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
        Cruiser cruiser = new Cruiser();
        playerBoard.connectShipToBoard(cruiser);
        addShipToBoard(cruiser);
        cruiserButton.setVisible(false);
        communicationPanel.remove(cruiserButton);
        doneButton.addActionListener(new FinalizeShipListener(cruiser,submarineButton,communicationPanel, doneButton));
        doneButton.setVisible(true);
        communicationPanel.add(doneButton);
        //if(cruiser.getStatus() == Ship.Status.PLACED){
          //cruiserButton.setVisible(false);
          //communicationPanel.add(submarineButton);
          //submarineButton.setVisible(true);
        //}
      }
    });
    submarineButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        //playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
        Submarine sub = new Submarine();
        playerBoard.connectShipToBoard(sub);
        addShipToBoard(sub);
        submarineButton.setVisible(false);
        communicationPanel.remove(submarineButton);
        doneButton.addActionListener(new FinalizeShipListener(sub,battleShipButton,communicationPanel, doneButton));
        doneButton.setVisible(true);
        communicationPanel.add(doneButton);
      }
    });
    battleShipButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        //playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
        BattleShip bs = new BattleShip();
        playerBoard.connectShipToBoard(bs);
        addShipToBoard(bs);
        battleShipButton.setVisible(false);
        communicationPanel.remove(battleShipButton);
        doneButton.addActionListener(new FinalizeShipListener(bs, destroyerButton,communicationPanel, doneButton));
        doneButton.setVisible(true);
        communicationPanel.add(doneButton);
      }
    });
    destroyerButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        //playerBoard.setStatus(Board.Status.READY_FOR_PLACEMENT);
        Destroyer destroyer = new Destroyer();
        playerBoard.connectShipToBoard(destroyer);
        addShipToBoard(destroyer);
        destroyerButton.setVisible(false);
        communicationPanel.remove(destroyerButton);
        doneButton.addActionListener(new FinalizeShipListener(destroyer,finalizeBoardButton,communicationPanel, doneButton));
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
          //playerTrackingBoard.createTrackingBoard(computerBoard);
          //computerTrackingBoard.createTrackingBoard(playerBoard);
          finalizeBoardButton.setVisible(false);
          System.out.println("setting start Game Button visible");
          dialogueBox.setText("Player 1 - Bombs Away!");
          playGame();
        }
      });
    //communicationPanel.add(carrierButton);
    //communicationPanel.add(cruiserButton);
    //communicationPanel.add(submarineButton);
    //communicationPanel.add(battleShipButton);
    //communicationPanel.add(destroyerButton);
  }

  private void changeDisplayOnAllButtons(boolean state){
    carrierButton.setVisible(state);
    destroyerButton.setVisible(state);
    battleShipButton.setVisible(state);
    submarineButton.setVisible(state);
    cruiserButton.setVisible(state);
  }

  private void addShipToBoard(Ship ship){
    playerBoard.setTempShip(ship);
    dialogueBox.setText("Pick your location to place the ship. Remember the ship uses " +
    ship.numOfShotsToSink + " boxes. Click done when you are finished");
  }

  private void playGame(){
    EasyPlayer computerPlayer = new EasyPlayer();
    HumanPlayer humanPlayer = new HumanPlayer();
    // display button start game
    // when this button is clicked, it becomes HumanPlayer turn
    // add action handler
    playerTrackingBoard.setPlayerBoard(computerBoard);
    playerTrackingBoard.addEvents(dimensions);
     // allow user to click their tracking board
     // display results of the clicking
     // switch turn and remove the event handlers
    // if computer has the turn, player picks a hit
    // display results
    // switch turn, reenable action handlers
  }

  // keep getting cruiser
  // can't click on the next ship after the first
  // next step can't color
  private class FinalizeShipListener implements ActionListener {
    private Ship ship;
    private JButton next;
    private JPanel panel;
    private JButton doneButton;
    private JButton current;


    public FinalizeShipListener(Ship ship, JButton next, JPanel panel, JButton doneButton) {
        this.ship = ship;
        this.next = next;
        this.panel = panel;
        this.doneButton = doneButton;
    }

    public void actionPerformed(ActionEvent e) {
      if(ship.getStatus() == Ship.Status.PLACED){
          doneButton.setVisible(false);
          panel.remove(doneButton);
          next.setVisible(true);
          panel.add(next);
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

  // private class BombAway implements ActionListener {
  //   private TrackingBoard board;
  //   public BombAway(TrackingBoard board) {
  //     board = board;
  //   }

  //   public void actionPerformed(ActionEvent ae) {
  //     System.out.println("within Bomb Away");
  //     System.out.println("Bombs Away!");
  //     board.decideShipFate((JButton) ae.getSource());
  //     // check if we won
  //     // switch turn to computer
  //     // computer makes turn
  //     // check if they won
  //   }
  // }
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


