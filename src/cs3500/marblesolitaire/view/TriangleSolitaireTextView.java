package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw02.SolitaireModel;
import java.io.IOException;

/**
 * This interface represents the implementation for the view of a triangular Marble solitaire game.
 */
public class TriangleSolitaireTextView extends AbstractSolitaireTextView {

  public TriangleSolitaireTextView(MarbleSolitaireModel modelState) {
    super(modelState);
  }

  public TriangleSolitaireTextView(SolitaireModel modelState, Appendable ap) {
    super(modelState, ap);
  }

  /**
   * Helper method to render the board to the provided Appendable.
   * @param appendable The Appendable to render the board state to.
   * @throws IOException If there is an issue appending to the provided Appendable.
   */
  protected void renderBoard(Appendable appendable) throws IOException {
    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      int lastMarbleIndex = this.getLastIndex(i);
      for (int j = 0; j < this.modelState.getBoardSize() - i - 1; j++) {
        appendable.append(" ");
      }
      this.appendBoard(lastMarbleIndex, appendable, i);
    }
  }
}
