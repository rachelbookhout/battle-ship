public class EasyPlayer implements Player {
  private boolean turn;

  public EasyPlayer(){
    turn = false;
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
