package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import java.io.IOException;

/**
 * This interface represents the implementation for the view of a typical Marble solitaire game.
 */
public abstract class AbstractSolitaireTextView implements MarbleSolitaireView {

  protected final MarbleSolitaireModelState modelState;
  protected final Appendable ap;

  public AbstractSolitaireTextView(MarbleSolitaireModelState modelState) {
    if (modelState == null) {
      throw new IllegalArgumentException("Provided model is null");
    }
    this.modelState = modelState;
    this.ap = System.out;
  }

  public AbstractSolitaireTextView(MarbleSolitaireModelState modelState, Appendable ap) {
    if (modelState == null) {
      throw new IllegalArgumentException("Provided model is null");
    }
    if (ap == null) {
      throw new IllegalArgumentException("Provided appendable is null");
    }
    this.modelState = modelState;
    this.ap = ap;
  }

  /**
   * Return a string that represents the current state of the board. The
   * string should have one line per row of the game board. Each slot on the
   * game board is a single character (O, _ or space for a marble, empty and
   * invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   * @return the game state as a string
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    try {
      renderBoard(sb);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return sb.toString();
  }

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderBoard() throws IOException {
    renderBoard(ap);
  }


  /**
   * Helper method to render the board to the provided Appendable.
   * @param appendable The Appendable to render the board state to.
   * @throws IOException If there is an issue appending to the provided Appendable.
   */
  protected void renderBoard(Appendable appendable) throws IOException {
    renderBoard(ap);
  }

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      ap.append(message);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Get index of last marble
   *
   * @param i current row
   * @return index of final marble in row
   */
  protected int getLastIndex(int i) {
    int lastMarbleIndex = -1;
    for (int j = 0; j < this.modelState.getBoardSize(); j++) {
      if (this.modelState.getSlotAt(i, j).equals(SlotState.Marble)
          || this.modelState.getSlotAt(i, j).equals(SlotState.Empty)) {
        lastMarbleIndex = j;
      }
    }
    return lastMarbleIndex;
  }

  /**
   * Append board to appendable object
   *
   * @param lastMarbleIndex index of final marble
   * @param appendable appendable to append board to
   * @param i current row
   * @throws IOException if cannot append to appender
   */
  protected void appendBoard(int lastMarbleIndex, Appendable appendable, int i) throws IOException {
    for (int j = 0; j < this.modelState.getBoardSize(); j++) {
      SlotState state = this.modelState.getSlotAt(i, j);
      switch (state) {
        case Empty:
          appendable.append("_");
          if (j < lastMarbleIndex) {
            appendable.append(" ");
          }
          break;
        case Marble:
          appendable.append("O");
          if (j < lastMarbleIndex) {
            appendable.append(" ");
          }
          break;
        case Invalid:
          if (j < lastMarbleIndex) {
            appendable.append("  ");
          }
          break;
      }
    }
    if (i != this.modelState.getBoardSize() - 1) {
      appendable.append("\n");
    }
  }
}