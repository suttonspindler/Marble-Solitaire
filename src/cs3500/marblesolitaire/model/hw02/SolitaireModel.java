package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

/**
 * This abstract class represents the implementations of the MarbleSolitaireModel, to abstract
 * the english, european, and triangle solitaire classes
 */
public abstract class SolitaireModel implements MarbleSolitaireModel {
  // game board represented as 2d ArrayList with SlotState enum as values
  protected ArrayList<ArrayList<SlotState>> board = new ArrayList<ArrayList<SlotState>>();

  // length of each arm
  protected final int armThickness;
  // row index of empty init slot
  protected final int sRow;
  // col index of empty init slot
  protected final int sCol;

  // Constructor 1: No parameters (default game board)
  public SolitaireModel() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sCol = 3;

    this.initBoard();
  }

  // Constructor 2: Empty slot position
  public SolitaireModel(int sRow, int sCol) {
    this.armThickness = 3;
    this.sRow = sRow;
    this.sCol = sCol;
    // Is the empty cell position invalid?
    if (this.inCorner(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow
          + "," + sCol + ")");
    }

    this.initBoard();
  }

  // Constructor 3: Arm thickness
  public SolitaireModel(int armThickness) {
    // Is the arm thickness invalid (an even or non-positive number)?
    if (armThickness % 2 == 0 || armThickness <= 0) {
      throw new IllegalArgumentException("Thickness is not a positive odd number");
    }
    this.armThickness = armThickness;
    this.sRow = (this.armThickness - 1) + (this.armThickness - 1) / 2;
    this.sCol = (this.armThickness - 1) + (this.armThickness - 1) / 2;

    this.initBoard();
  }

  // Constructor 4: Arm thickness and empty slot position
  public SolitaireModel(int armThickness, int sRow, int sCol) {
    // Is the arm thickness invalid (an even or nonpositive number)?
    if (armThickness % 2 == 0 || armThickness <= 0) {
      throw new IllegalArgumentException("Thickness is not a positive odd number");
    }
    this.armThickness = armThickness;
    this.sRow = sRow;
    this.sCol = sCol;
    // Is the empty cell position invalid?
    if (this.inCorner(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow
          + "," + sCol + ")");
    }

    this.initBoard();
  }

  /**
   * initializes the board
   */
  private void initBoard() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      this.board.add(new ArrayList<SlotState>());
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.inCorner(i, j)) {
          this.board.get(i).add(SlotState.Invalid);
        }
        else if (i == this.sRow && j == this.sCol) {
          this.board.get(i).add(SlotState.Empty);
        }
        else {
          this.board.get(i).add(SlotState.Marble);
        }
      }
    }
  }

  /**
   * Returns true if coordinates are in the four corners, in an invalid space
   *
   * @param row row coordinate to check
   * @param col col coordinate to check
   * @return boolean whether coordinates are in 4 corners
   */
  protected abstract boolean inCorner(int row, int col);

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // Is the 'from' position invalid?
    // First is it in one of four corners, then is it outside the board?
    if (this.inCorner(fromRow, fromCol)
        || fromRow < 0 || fromRow >= this.getBoardSize()
        || fromCol < 0 || fromCol >= this.getBoardSize()) {
      throw new IllegalArgumentException("Invalid from position (" + fromRow
          + "," + fromCol + ")");
    }
    // Is the 'to' position invalid?
    // First is it in one of four corners, then is it outside the board?
    if (this.inCorner(toRow, toCol)
        || toRow < 0 || toRow >= this.getBoardSize()
        || toCol < 0 || toCol >= this.getBoardSize()) {
      throw new IllegalArgumentException("Invalid to position (" + toRow
          + "," + toCol + ")");
    }
    // Is there no marble present in the 'from' position?
    if (this.checkSpot(fromRow, fromCol, SlotState.Empty)) {
      throw new IllegalArgumentException("No marble at specified from position");
    }
    // Is there already a marble in the 'to' position?
    if (this.checkSpot(toRow, toCol, SlotState.Marble)) {
      throw new IllegalArgumentException("To position is not empty");
    }
    this.checkPositions(fromRow, fromCol, toRow, toCol);
    // Is there no marble present between the 'from' and 'to' positions?
    if (this.checkSpot((fromRow + toRow) / 2, (fromCol + toCol) / 2, SlotState.Empty)) {
      throw new IllegalArgumentException("There is no marble in the slot between the to and from"
          + " positions");
    }
    // As no exceptions were made, we can now make a move by modifying the board :D
    this.board.get(fromRow).set(fromCol, SlotState.Empty);
    this.board.get((fromRow + toRow) / 2).set((fromCol + toCol) / 2, SlotState.Empty);
    this.board.get(toRow).set(toCol, SlotState.Marble);
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
  protected void checkPositions(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // Are the 'from' and 'to' positions not exactly two positions away?
    if ((Math.abs(fromRow - toRow) != 2 || Math.abs(fromCol - toCol) != 0)
        && (Math.abs(fromRow - toRow) != 0 || Math.abs(fromCol - toCol) != 2)) {
      throw new IllegalArgumentException("To and from positions are not exactly two positions"
          + " away");
    }
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
        }
      }
    }
    // no marbles next to each other on the board were found, so game is over
    return true;
  }

  /**
   * Helper method that checks four directions
   *
   * @param i row to check
   * @param j column to check
   * @return false if game is not over based on four basic directions
   */
  protected boolean checkFourDirections(int i, int j) {
    // check slot above
    if (i > 1) {
      if (this.checkSpot(i - 1, j, SlotState.Marble)
          && this.checkSpot(i - 2, j, SlotState.Empty)) {
        return false;
      }
    }
    // check slot below
    if (i < this.getBoardSize() - 2) {
      if (this.checkSpot(i + 1, j, SlotState.Marble)
          && this.checkSpot(i + 2, j, SlotState.Empty)) {
        return false;
      }
    }
    // check slot to the left
    if (j > 1) {
      if (this.checkSpot(i, j - 1, SlotState.Marble)
          && this.checkSpot(i, j - 2, SlotState.Empty)) {
        return false;
      }
    }
    // check slot to the right
    if (j < this.getBoardSize() - 2) {
      if (this.checkSpot(i, j + 1, SlotState.Marble)
          && this.checkSpot(i, j + 2, SlotState.Empty)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Check if the spot of provided coordinates matches provided state
   *
   * @param row row of board to check spot from
   * @param col column of board to check spot from
   * @param state check of spot equals state
   * @return true if spot equals state, else false
   */
  protected boolean checkSpot(int row, int col, SlotState state) {
    return this.board.get(row).get(col).equals(state);
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.armThickness + (this.armThickness - 1) * 2;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *         the dimensions of the board
   */
  @Override
  public SlotState getSlotAt(int row, int col)
      throws IllegalArgumentException {
    if (row < 0 || row >= this.getBoardSize() || col < 0 || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("Slot out of range");
    }
    return this.board.get(row).get(col);
  }

  /**
   * Return arm thickness
   *
   * @return armThickness
   */
  public int getArmThickness() {
    return this.armThickness;
  }

  /**
   * Returns row coordinate of initial empty slot
   *
   * @return row coordinate
   */
  public int getSRow() {
    return this.sRow;
  }

  /**
   * Returns col coordinate of initial empty slot
   *
   * @return col coordinate
   */
  public int getSCol() {
    return this.sCol;
  }

  /**
   * get the 2d ArrayList board for testing purposes
   *
   * @return board
   */
  public ArrayList<ArrayList<SlotState>> getBoard() {
    return this.board;
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.board.get(i).get(j).equals(SlotState.Marble)) {
          score++;
        }
      }
    }
    return score;
  }
}
