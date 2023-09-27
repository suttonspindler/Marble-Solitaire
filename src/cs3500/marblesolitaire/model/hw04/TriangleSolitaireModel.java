package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.SolitaireModel;

/**
 * This class represents the european marble solitaire model, where it is in the shape of a triangle
 */
public class TriangleSolitaireModel extends SolitaireModel {

  // Constructor 1: No parameters (default game board)
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  // Constructor 2: Empty slot position
  public TriangleSolitaireModel(int sRow, int sCol) {
    super(5, sRow, sCol);
  }

  // Constructor 3: Arm thickness
  public TriangleSolitaireModel(int armThickness) {
    super(armThickness, 0, 0);
  }

  // Constructor 4: Arm thickness and empty slot position
  public TriangleSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }

  @Override
  protected boolean inCorner(int row, int col) {
    return col > row;
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    // a move can still be made as long as there is the sequence: marble, marble, empty
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.checkSpot(i, j, SlotState.Marble) && this.getBoardSize() > 1) {
          if (!this.checkFourDirections(i, j)) {
            return false;
          }
          // check slot top-left
          if (i > 1 && j > 1) {
            if (this.checkSpot(i - 1, j - 1, SlotState.Marble)
                && this.checkSpot(i - 2, j - 2, SlotState.Empty)) {
              return false;
            }
          }
          // check slot bottom-right
          if (i < this.getBoardSize() - 2 && j < this.getBoardSize() - 2) {
            if (this.checkSpot(i + 1, j + 1, SlotState.Marble)
                && this.checkSpot(i + 2, j + 2, SlotState.Empty)) {
              return false;
            }
          }
        }
      }
    }
    // no marbles next to each other on the board were found, so game is over
    return true;
  }

  /**
   * check if to and from are correct distance
   *
   * @param fromRow row to check from
   * @param fromCol col to check from
   * @param toRow row to check to
   * @param toCol col to check to
   * @throws IllegalArgumentException if spots are not correct distance
   */
  @Override
  protected void checkPositions(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // Are the 'from' and 'to' positions not exactly two positions away?
    if ((Math.abs(fromRow - toRow) != 2 || Math.abs(fromCol - toCol) != 0)
        && (Math.abs(fromRow - toRow) != 0 || Math.abs(fromCol - toCol) != 2)
        && (Math.abs(fromRow - toRow) != 2 || Math.abs(fromCol - toCol) != 2)) {
      throw new IllegalArgumentException("To and from positions are not exactly two positions"
          + " away");
    }
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.armThickness;
  }
}
