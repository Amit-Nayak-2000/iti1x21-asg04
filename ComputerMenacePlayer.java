import java.util.*;

public class ComputerMenacePlayer extends Player {

  // WRITE CODE HERE
  // Hint: if you need to track something about this player
  //       then you need instance variables

  /**
   * A menace player needs to know the size of the game before starting
   * Only optimized for a 3x3 board
   *
   * @param aNumRows the number of lines in the game
   * @param aNumColumns the number of columns in the game
   * @param aSizeToWin the number of cells that must be aligned to win.
   */
  public ComputerMenacePlayer(int aNumRows, int aNumColumns, int aSizeToWin) {

    // WRITE CODE HERE
    // Hint: look at ComputerPerfectPlayer

  }

  // Hint: You might need to overwrite
  //       endGame to help tell your MenanceGame
  //       about the outcome of the game
  //       (so it can learn)

  public boolean play(TicTacToe game) {

    // WRITE CODE HERE
    // Hint: look at ComputerPerfectPlayer
    //       BUT DO NOT use PerfectGame (use MenaceGame)

    // This is only here to get code to compile, update as necessary
    return false;
  }

}