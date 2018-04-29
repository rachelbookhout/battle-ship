public class BattleShipGameTests{
  public static void main(String [] args) {
    checkLocationTest();
    decideShipFateTest();
    checkShipLocationTest();
    makeComputerMoveTest();
    checkUsedMovesTest();
    hasWonTest();
    setRandomLocationTest();
    clearSelectionTest();
  }

  private static void checkLocationTest()
  {
      // valid placements, vertical
      PlayerBoard board = new PlayerBoard(5);
      Destroyer goodDestroyer = new Destroyer();
      int[] good1 = new int[]{1,1};
      int[] good2 = new int[]{1,2};
      goodDestroyer.setLocation(good1);
      goodDestroyer.setLocation(good2);
      boolean isShipGood = board.checkLocation(goodDestroyer);
      assertEquals(true, isShipGood, "Should be good location");
      // valid placement horizontal
      Destroyer goodDestroyer2 = new Destroyer();
      int[] good3 = new int[]{2,0};
      int[] good4 = new int[]{3,0};
      goodDestroyer2.setLocation(good3);
      goodDestroyer2.setLocation(good4);
      boolean isShipGood2 = board.checkLocation(goodDestroyer2);
      assertEquals(true, isShipGood2, "Should be good location");

      // bad placements
      Destoyer badDestroyer = new Destoyer();
      int[] bad1 = new int[]{4,1};
      int[] bad2 = new int[]{0,0};
      badDestroyer.setLocation(bad1);
      badDestroyer.setLocation(bad2);
      boolean isShipBad = board.checkLocation(badDestroyer);
      assertEquals(false, isShipBad, "Should be bad location");
  }

  private static void decideShipFateTest(){

  }

  private static void checkShipLocationTest(){

  }

  private static void makeComputerMoveTest(){

  }

  private static void checkUsedMovesTest(){

  }

  private static void hasWonTest(){

  }

  private static void setRandomLocationTest(){

  }

  private static void clearSelectionTest(){

  }

}
