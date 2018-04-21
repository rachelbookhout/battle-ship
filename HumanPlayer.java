public class HumanPlayer implements Player {
  private boolean turn;

  public HumanPlayer(){
    turn = true;
  }

   public void makeMove() {

   }

   public void setTurn(){
    if(turn == true){
      turn = false;
    }
    turn = true;
   }

   public boolean getTurn(){
    return turn;
   }
}
