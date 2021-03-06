import java.util.Random;
public class MenaceGame {

  TicTacToe game;
  int[] boardOdds;
  int totalOdds;
  int currentPosition;

  /**
   * A menace game keeps an instance of a TicTacToe game
   * instead of extending it. We can chat about why
   * during our Monday meetups.
   *
   * Hint: Take a look at the implementation of a PerfectGame
   *       for ideas on how to structure a MenaceGame.
   */
  public MenaceGame(TicTacToe aGame) {
    game = aGame;
    int currentPosition =  game.lastPlayedPosition;
    boardOdds = new int[] {0,0,0,0,0,0,0,0,0};
    resetOdds();
  }

  /**
   * The game is over.
   * If you won, then add three beads to the current position's odds.
   * If you tied, only add 1 bead
   * If you lost, then remove a bead.
   *
   * Note: You can never have 0 beads in a game
   *       and do not forget to correctly update your totalOdds
   * @param outcome The outcome of the game.
   */
  public void setOutcome(GameOutcome outcome) {

    switch(outcome) {

      case WIN:
        boardOdds[currentPosition-1] += 3;
        totalOdds +=3;
        break;

      case LOSE:
        if(totalOdds == 1){
          break;
        }
        else{
          boardOdds[currentPosition-1] -= 1;
          totalOdds -= 1;
        }
        break;

      case DRAW:
        boardOdds[currentPosition-1] += 1;
        totalOdds +=1;
        break;

      default:
       return;
    }

  }

  /**
   * Roll the dice, and set the current position
   * If no positions are available, then return 0
   * (which is an invalid position)
   *
   * @return The current positionThe random number rolled.
   */
  public int pickCurrentPosition() {
    int [] emptyPositions = game.emptyPositions();
    if(emptyPositions.length == 0){
      return 0;
    }

    currentPosition = rollDice();
    return currentPosition;
  }

  /**
   * Generate a random number.
   *
   * Consider the following 3x3 board.
   *
   *    | X |
   * -----------
   *  O |   |
   * -----------
   *    |   |
   *
   * If we had the following beads (I left the Xs and Os off)
   *
   *  5 |  | 6
   * -----------
   *    | 1 | 1
   * -----------
   *  3 | 4 | 8
   *
   * Then our total odds are 28 (5+6+1+1+3+4+8) and we
   * want our random number generator to generate numbers
   * between 1 and 28.
   *
   * @return The random number rolled.
   */
  public int rollDice() {
    int [] emptyPositions = game.emptyPositions();

    if(emptyPositions.length == 0){
      return 0;
    }

    Random generator;
    generator = new Random();

    int roll = generator.nextInt(totalOdds) + 1;
    
    return roll;
  }

  /**
   * Based on the diceRoll, calculate which position
   * on the board should be played based on the current odds (beads)
   * in each available cell.
   *
   * On a 3x3 board.
   *
   *    | X |
   * -----------
   *  O |   |
   * -----------
   *    |   |
   *
   * If we had the following beads (I left the Xs and Os off)
   *
   *  5 |  | 6
   * -----------
   *    | 1 | 1
   * -----------
   *  3 | 4 | 8
   *
   * Here are some expected outputs based on sample diceRolls
   *
   * diceRoll 3 => returns 1
   * diceRoll 11 => returns 3
   * diceRoll 12 => returns 5
   * diceRoll 14 => return 7
   *
   * @return int which position on the board should we choose
   */
  public int calculatePosition(int diceRoll) {
    int [] emptyPositions = game.emptyPositions();
    if(emptyPositions.length == 0 || diceRoll < 0 || diceRoll > totalOdds){
      return 0;
    }
    int position = diceRoll;
    int i = 0;
    while(position > 0){
      position -= boardOdds[i];
      i++;
    }

    return i;

  }

  /**
   * Reset the odds back to an un-trained set based on
   * Menace algorithm.
   */
  public void resetOdds() {
    boardOdds = new int[] {0,0,0,0,0,0,0,0,0};
    totalOdds = 0;
    int [] emptyPositions = game.emptyPositions();
    
    int odd = 0;
    for(int position: emptyPositions){
      int index = position - 1;
      if(index == 0 || index == 1){
        odd = 8;
      }
      else if (index == 2 || index == 3){
        odd = 4;
      }
      else if (index == 4 || index == 5){
        odd = 2;
      }
      else if (index == 6 || index == 7 || index == 8){
        odd = 1;
      }
      boardOdds[index] = odd;
      totalOdds += odd;
    }
  }

//"POSITION: 0 (Odds 11)\n"


  public String toString() {
    StringBuilder b = new StringBuilder();
    int maxRowsIndex = game.numRows - 1;
    int maxColumnsIndex = game.numColumns - 1;

    String lineSeparator = Utils.repeat("---", game.numColumns) + Utils.repeat("-", game.numColumns - 1);

    b.append("POSITION: " + currentPosition + " (Odds "+ totalOdds +")\n");
    for (int i = 0; i < game.numRows; i++) {
      for (int j = 0; j < game.numColumns; j++) {
        int index = i*game.numColumns + j;

        b.append(" ");
        b.append(toString(index));
        b.append(" ");

        if (j < maxColumnsIndex) {
          b.append("|");
        }
      }

      // Line separator after each row, except the last
      if (i < maxRowsIndex) {
        b.append("\n");
        b.append(lineSeparator);
        b.append("\n");
      }
    }

    return b.toString();
  }
//game.boardIndexes[
  private String toString(int index) {
    CellValue lookupIndex = game.board[index];
    switch (lookupIndex) {
      case X:
        return lookupIndex.toString();
      case O:
        return lookupIndex.toString();
      case EMPTY:
        return String.valueOf(boardOdds[index]);
      default:
        return null;
    }
  }



}