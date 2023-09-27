package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import java.io.IOException;

/**
 * This interface represents the implementation for the view of a typical Marble solitaire game.
 */
public class MarbleSolitaireTextView extends AbstractSolitaireTextView {

  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState) {
    super(modelState);
  }

  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable ap) {
    super(modelState, ap);
  }

  /**
   * Helper method to render the board to the provided Appendable.
   * @param appendable The Appendable to render the board state to.
   * @throws IOException If there is an issue appending to the provided Appendable.
   */
  @Override
  protected void renderBoard(Appendable appendable) throws IOException {
    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      int lastMarbleIndex = this.getLastIndex(i);
      this.appendBoard(lastMarbleIndex, appendable, i);
    }
  }
}